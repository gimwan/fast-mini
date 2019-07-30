package com.fast.base.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.fast.util.Common;
import com.fast.util.ReflectHelper;

/**
 * 
 * Mybatis的分页查询插件，通过拦截StatementHandler的prepare方法来实现。
 * 只有在参数列表中包括Page类型的参数时才进行分页查询。 在多参数的情况下，只对第一个Page类型的参数生效。
 * 另外，在参数列表中，Page类型的参数无需用@Param来标注
 * 
 * @version 1.0v
 */
@SuppressWarnings("unchecked")
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagingPlugin implements Interceptor {
	// 数据库方言
	private static PagingDialect dialectObject = null;
	// mybaits的数据库xml映射文件中需要拦截的ID(正则匹配)
	private static String pageSqlId = "";

	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
					"delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
					"mappedStatement");
			/**
			 * 方法1：通过ＩＤ来区分是否需要分页．.*query.* 方法2：传入的参数是否有page参数，如果有，则分页，
			 */
			// if (mappedStatement.getId().matches(pageSqlId)) { // 拦截需要分页的SQL
			BoundSql boundSql = delegate.getBoundSql();
			// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
				// throw new
				// NullPointerException("boundSql.getParameterObject() is null!");
				return ivk.proceed();
			} else {

				PagingView PagingView = null;
				if (parameterObject instanceof PagingView) { // 参数就是Pages实体
					PagingView = (PagingView) parameterObject;
				} else if (parameterObject instanceof Map) {
					for (Entry entry : (Set<Entry>) ((Map) parameterObject).entrySet()) {
						if (entry.getValue() instanceof PagingView) {
							PagingView = (PagingView) entry.getValue();
							break;
						}
					}
				}
				// 参数为某个实体，该实体拥有Pages属性
				else {
					PagingView = ReflectHelper.getValueByFieldType(parameterObject, PagingView.class);

				}
				if (PagingView == null) {
					return ivk.proceed();
				}
				String sql = boundSql.getSql();
				PreparedStatement countStmt = null;
				ResultSet rs = null;
				try {
					String temp1 = sql.toLowerCase().replace('\n', ' ').replace('\t', ' ').replaceAll("  by", " by");
					String ss[] = temp1.split(" from");
					if (ss != null && ss.length > 1) {
						if (ss[ss.length - 1].indexOf("order by") > 0) {
							ss[ss.length - 1] = ss[ss.length - 1].split("order by")[0];
							String temp2 = "";
							for (int j = 0; j < ss.length; j++) {
								if (temp2 != "") {
									temp2 += " from";
								}
								temp2 += ss[j];
							}

							temp1 = temp2;
						}
					}

					Connection connection = (Connection) ivk.getArgs()[0];
					String countSql = "select count(1) from (" + temp1 + ") tmp_count"; // 记录统计
					countStmt = connection.prepareStatement(countSql);
					ReflectHelper.setValueByFieldName(boundSql, "sql", countSql);
					DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
							parameterObject, boundSql);
					parameterHandler.setParameters(countStmt);
					rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = ((Number) rs.getObject(1)).intValue();
					}
					PagingView.settotalCount(count);
				} finally {
					try {
						rs.close();
					} catch (Exception e) {
					}
					try {
						countStmt.close();
					} catch (Exception e) {
					}
				}
				String pageSql = generatePagesSql(sql, PagingView);
				// 将分页sql语句反射回BoundSql.
				ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
			}
		}
		return ivk.proceed();
	}

	/**
	 * 根据数据库方言，生成特定的分页sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePagesSql(String sql, PagingView page) {
		if (page != null && dialectObject != null) {
			// pageNow默认是从1，而已数据库是从0开始计算的．所以(page.getPageNow()-1)
			int pageNow = page.getpageNo();
			return dialectObject.getLimitString(sql, (pageNow <= 0 ? 0 : pageNow - 1) * page.getPageSize(),
					page.getPageSize(), page.getOrderBy());
		}
		return sql;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties p) {
		// 数据库方言
		String dialect = "";
		dialect = p.getProperty("dialect");
		if (Common.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		} else {
			try {

				dialectObject = (PagingDialect) Class.forName(dialect).getDeclaredConstructor().newInstance();

			} catch (Exception e) {
				throw new RuntimeException(dialect + ", init fail!\n" + e);
			}
		}
		// 根据id来区分是否需要分页
		pageSqlId = p.getProperty("pageSqlId");
		if (Common.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
}
