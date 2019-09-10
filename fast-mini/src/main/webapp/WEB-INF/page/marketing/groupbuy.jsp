<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/marketing/groupbuy.css">
<script type="text/javascript" src="js/marketing/groupbuy.js"></script>
<div class="groupbuyPage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>营销管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>拼团</cite></a>
	  </div>
	</div>
	
	<div class="operating">
		<div class="layui-btn-group">
	        <button class="layui-btn layui-btn-sm add" title="新增" v-on:click="add">
	          <i class="layui-icon layui-icon-add-1"></i>
	        </button><!-- 
	        <button class="layui-btn layui-btn-sm del" title="删除" v-on:click="del">
	          <i class="layui-icon layui-icon-delete"></i>
	        </button> -->
	     </div>
	</div>
	
	<div class="layui-tab-item layui-show">
		<div class="layui-main">
			<div id="lay_preview">
				<div class="layui-form layui-border-box layui-table-view">
					<div class="layui-table-box">
						<div class="layui-table-header">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<thead>
									<tr>
										<th class="item code" data-minwidth="100">
											<div class="layui-table-cell">
												<span>编号</span>
											</div>
										</th>
										<th class="item name">
											<div class="layui-table-cell">
												<span>名称</span>
											</div>
										</th>
										<th class="item minimum">
											<div class="layui-table-cell">
												<span>成团人数</span>
											</div>
										</th>
										<th class="item useflag">
											<div class="layui-table-cell">
												<span>使用</span>
											</div>
										</th>
										<th class="item begintime time-item">
											<div class="layui-table-cell">
												<span>生效时间</span>
											</div>
										</th>
										<th class="item endtime time-item">
											<div class="layui-table-cell">
												<span>失效时间</span>
											</div>
										</th>
										<th class="item ordernumber">
											<div class="layui-table-cell">
												<span>订单笔数</span>
											</div>
										</th>
										<th class="item orderamount">
											<div class="layui-table-cell">
												<span>订单金额</span>
											</div>
										</th>
										<!-- <th class="item memo">
											<div class="layui-table-cell">
												<span>备注</span>
											</div>
										</th> -->
										<th class="item operationbtn">
											<div class="layui-table-cell">
												<span>操作</span>
											</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="layui-table-body layui-table-main groupbuy-data pageTable" name="groupbuy-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="g.id" v-bind:data-index="index" class="" v-for="(g, index) in groupbuy">
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{g.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{g.name}}</div>
										</td>
										<td class="item minimum">
											<div class="layui-table-cell laytable-cell-1-0-1">{{g.minimum}}</div>
										</td>
										<td class="item useflag">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="g.useflag == 1"></i>
											</div>
										</td>
										<td class="item begintime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(g.begintime)}}</div>
										</td>
										<td class="item endtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(g.endtime)}}</div>
										</td>
										<td class="item ordernumber">
											<div class="layui-table-cell laytable-cell-1-0-3">{{g.ordernumber}}</div>
										</td>
										<td class="item orderamount">
											<div class="layui-table-cell laytable-cell-1-0-3">{{g.orderamount}}</div>
										</td>
										<!-- <td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{g.memo}}</div>
										</td> -->
										<td class="item operationbtn">
											<div class="layui-btn-group">
												<button
													class="layui-btn layui-btn-normal layui-btn-xs goods"
													title="商品" v-on:click="goods">商品</button>
												<button
													class="layui-btn layui-btn-danger layui-btn-xs del"
													title="删除" v-on:click="del" v-if="(g.useflag!=1 || g.active!=1) && g.over!=1">删除</button>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="layPage" id="layPage"></div>
			</div>
		</div>
	</div>
</div>
