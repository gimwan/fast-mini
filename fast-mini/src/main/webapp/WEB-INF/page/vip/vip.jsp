<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/vip/vip.css">
<script type="text/javascript" src="js/vip/vip.js"></script>
<div class="vipPage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>会员管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>会员</cite></a>
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
										<th class="item mobilephone">
											<div class="layui-table-cell">
												<span>手机号</span>
											</div>
										</th>
										<th class="item department">
											<div class="layui-table-cell">
												<span>所属门店</span>
											</div>
										</th>
										<th class="item type">
											<div class="layui-table-cell">
												<span>类别</span>
											</div>
										</th>
										<th class="item registtime time-item">
											<div class="layui-table-cell">
												<span>注册时间</span>
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
						<div class="layui-table-body layui-table-main vip-data pageTable" name="vip-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="v.id" v-bind:data-index="index" class="" v-for="(v, index) in vip">
										<td class="item photourl">
											<div class="layui-table-cell laytable-cell-1-0-0">
												<img v-bind:src="v.photourl==null?'':v.photourl" onerror="defaultImg(this)" class="layui-nav-img circular">
											</div>
										</td>
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{v.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{v.name}}</div>
										</td>
										<td class="item mobilephone">
											<div class="layui-table-cell laytable-cell-1-0-1">{{v.mobilephone}}</div>
										</td>
										<td class="item department">
											<div class="layui-table-cell laytable-cell-1-0-3">{{v.department}}</div>
										</td>
										<td class="item type">
											<div class="layui-table-cell laytable-cell-1-0-3">{{v.type}}</div>
										</td>
										<td class="item registtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(v.registtime)}}</div>
										</td>
										<!-- <td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{v.memo}}</div>
										</td> -->
										<td class="item operationbtn">
											<div class="layui-btn-group">
												<button
													class="layui-btn layui-btn-normal layui-btn-xs send"
													title="更多" v-on:click="send">赠送</button>
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

