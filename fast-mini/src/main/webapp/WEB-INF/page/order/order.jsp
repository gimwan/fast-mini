<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/order/order.css">
<script type="text/javascript" src="js/order/order.js"></script>
<div class="orderPage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>订单管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>订单</cite></a>
	  </div>
	</div>
	<!-- 
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
	 -->
	<div class="layui-tab-item layui-show">
		<div class="layui-main">
			<div id="lay_preview">
				<div class="layui-form layui-border-box layui-table-view">
					<div class="layui-table-box">
						<div class="layui-table-header">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<thead>
									<tr>
										<th class="item no" data-minwidth="100">
											<div class="layui-table-cell">
												<span>单号</span>
											</div>
										</th>
										<th class="item vip">
											<div class="layui-table-cell">
												<span>会员</span>
											</div>
										</th>
										<th class="item quantity">
											<div class="layui-table-cell">
												<span>总数量</span>
											</div>
										</th>
										<th class="item amount">
											<div class="layui-table-cell">
												<span>总金额</span>
											</div>
										</th>
										<th class="item createtime time-item">
											<div class="layui-table-cell">
												<span>下单时间</span>
											</div>
										</th>
										<th class="item status">
											<div class="layui-table-cell">
												<span>状态</span>
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
						<div class="layui-table-body layui-table-main order-data pageTable" name="order-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="o.id" v-bind:data-index="index" class="" v-for="(o, index) in order">
										<td class="item no">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{o.no}}</a>
											</div>
										</td>
										<td class="item vip">
											<div class="layui-table-cell laytable-cell-1-0-1">{{o.vip}}</div>
										</td>
										<td class="item quantity">
											<div class="layui-table-cell laytable-cell-1-0-1">{{o.quantity}}</div>
										</td>
										<td class="item amount">
											<div class="layui-table-cell laytable-cell-1-0-3">{{o.amount}}</div>
										</td>
										<td class="item createtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(o.createtime)}}</div>
										</td>
										<td class="item status">
											<div class="layui-table-cell laytable-cell-1-0-3">{{o.status==0?'已取消':(o.status==1?'待付款':(o.status==2?'待发货':(o.status==3?'待收货':(o.status==4?'已完成':''))))}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{o.memo}}</div>
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

