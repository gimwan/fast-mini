<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/system/config.css">
<script type="text/javascript" src="js/system/config.js"></script>
<div class="configPage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>系统管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>参数</cite></a>
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
										<th class="item value">
											<div class="layui-table-cell">
												<span>参数值</span>
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
						<div class="layui-table-body layui-table-main config-data" name="config-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="c.id" v-bind:data-index="index" class="" v-for="(c, index) in config">
										<td class="item code">
											<div class="layui-table-cell">
											<a href="javascript:void(0);" v-on:click="edit">{{c.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell">{{c.name}}</div>
										</td>
										<td class="item value">
											<div class="layui-table-cell" v-if="c.type==2">
												<div class="colorValue" v-bind:style="'height:26px;width:26px;background-color:'+c.value"></div>
											</div>
											<div class="layui-table-cell" v-else-if="c.type==4">
												<i class="layui-icon layui-icon-ok" v-if="c.value == 1"></i>
											</div>
											<div class="layui-table-cell" v-else-if="c.type==5">
												<img v-bind:src="c.value" onerror="defaultImg(this)" class="imgValue" style="height:26px;width:26px;">
											</div>
											<div class="layui-table-cell" v-else>{{c.code=='6001'?c.department:c.value}}</div>
										</td>
										<td class="item modifier">
											<div class="layui-table-cell">{{c.modifier}}</div>
										</td>
										<td class="item modifytime time-item">
											<div class="layui-table-cell">{{formatDate(c.modifytime)}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell">{{c.memo}}</div>
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

