<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/data/employee.css">
<script type="text/javascript" src="js/data/employee.js"></script>
<div class="employeePage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>资料管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>员工</cite></a>
	  </div>
	</div>
	
	<div class="operating">
		<div class="layui-btn-group">
	        <button class="layui-btn layui-btn-sm add" title="新增" v-on:click="add">
	          <i class="layui-icon"></i>
	        </button>
	        <button class="layui-btn layui-btn-sm del" title="删除" v-on:click="del">
	          <i class="layui-icon"></i>
	        </button>
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
										<th class="item photourl">
											<div class="layui-table-cell">
												<span>头像</span>
											</div>
										</th>
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
										<th class="item department">
											<div class="layui-table-cell">
												<span>所属门店</span>
											</div>
										</th>
										<th class="item value">
											<div class="layui-table-cell">
												<span>是否使用</span>
											</div>
										</th>
										<th class="item creator">
											<div class="layui-table-cell">
												<span>创建人</span>
											</div>
										</th>
										<th class="item createtime time-item">
											<div class="layui-table-cell">
												<span>创建时间</span>
											</div>
										</th>
										<th class="item modifier">
											<div class="layui-table-cell">
												<span>修改人</span>
											</div>
										</th>
										<th class="item modifytime time-item">
											<div class="layui-table-cell">
												<span>修改时间</span>
											</div>
										</th>
										<th class="item memo">
											<div class="layui-table-cell">
												<span>备注</span>
											</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="layui-table-body layui-table-main employee-data pageTable" name="employee-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="e.id" v-bind:data-index="index" class="" v-for="(e, index) in employee">
										<td class="item photourl">
											<div class="layui-table-cell laytable-cell-1-0-0">
												<img v-bind:src="e.photourl" onerror="defaultImg(this)" class="layui-nav-img circular">
											</div>
										</td>
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{e.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{e.name}}</div>
										</td>
										<td class="item department">
											<div class="layui-table-cell laytable-cell-1-0-1">{{e.department}}</div>
										</td>
										<td class="item value">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="e.useflag == 1"></i>
											</div>
										</td>
										<td class="item creator">
											<div class="layui-table-cell laytable-cell-1-0-3">{{e.creator}}</div>
										</td>
										<td class="item createtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(e.createtime)}}</div>
										</td>
										<td class="item modifier">
											<div class="layui-table-cell laytable-cell-1-0-3">{{e.modifier}}</div>
										</td>
										<td class="item modifytime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(e.modifytime)}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{e.memo}}</div>
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

