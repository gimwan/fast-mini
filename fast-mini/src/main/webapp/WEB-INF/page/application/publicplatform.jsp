<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/application/publicplatform.css">
<script type="text/javascript" src="js/application/publicplatform.js"></script>
<div class="publicplatformPage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>应用管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>公众号</cite></a>
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
										<th class="item useflag">
											<div class="layui-table-cell">
												<span>使用</span>
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
						<div class="layui-table-body layui-table-main publicplatform-data pageTable" name="publicplatform-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="p.id" v-bind:data-index="index" class="" v-for="(p, index) in publicplatform">
										<td class="item photourl">
											<div class="layui-table-cell laytable-cell-1-0-0">
												<img v-bind:src="p.photourl==null?'':p.photourl" onerror="defaultImg(this)" class="layui-nav-img circular">
											</div>
										</td>
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{p.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{p.name}}</div>
										</td>
										<td class="item useflag">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="p.useflag == 1"></i>
											</div>
										</td>
										<td class="item creator">
											<div class="layui-table-cell laytable-cell-1-0-3">{{p.creator}}</div>
										</td>
										<td class="item createtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(p.createtime)}}</div>
										</td>
										<td class="item modifier">
											<div class="layui-table-cell laytable-cell-1-0-3">{{p.modifier}}</div>
										</td>
										<td class="item modifytime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(p.modifytime)}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{p.memo}}</div>
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

