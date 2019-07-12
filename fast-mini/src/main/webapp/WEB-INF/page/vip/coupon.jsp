<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/vip/coupon.css">
<script type="text/javascript" src="js/vip/coupon.js"></script>
<div class="couponPage">
	<div class="layui-tab layui-tab-brief nav-bar">
		<ul class="layui-tab-title">
		    <li class="layui-this">优惠券</li>
		</ul>
		<div class="layui-tab-content"></div>
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
										<th class="item totalquantity">
											<div class="layui-table-cell">
												<span>发放总数</span>
											</div>
										</th>
										<th class="item useflag">
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
						<div class="layui-table-body layui-table-main coupon-data" name="coupon-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="v.id" v-bind:data-index="index" class="" v-for="(v, index) in coupon">
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{v.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{v.name}}</div>
										</td>
										<td class="item totalquantity">
											<div class="layui-table-cell laytable-cell-1-0-1">{{v.totalquantity}}</div>
										</td>
										<td class="item useflage">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="v.useflag == 1"></i>
											</div>
										</td>
										<td class="item creator">
											<div class="layui-table-cell laytable-cell-1-0-3">{{v.creator}}</div>
										</td>
										<td class="item createtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(v.createtime)}}</div>
										</td>
										<td class="item modifier">
											<div class="layui-table-cell laytable-cell-1-0-3">{{v.modifier}}</div>
										</td>
										<td class="item modifytime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(v.modifytime)}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{v.memo}}</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

