<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/goods/goods.css">
<script type="text/javascript" src="js/goods/goods.js"></script>
<div class="goodsPage">
	<div class="layui-card layadmin-header nav-bar">
	  <div class="layui-breadcrumb" lay-filter="breadcrumb" style="visibility: visible;">
	    <a><cite>商品管理</cite></a>
	    <span lay-separator="">/</span>
	    <a><cite>商品</cite></a>
	  </div>
	</div>
	
	<div class="operating">
		<div class="layui-btn-group">
	        <button class="layui-btn layui-btn-sm add" title="新增" v-on:click="add">
	          <i class="layui-icon"></i>
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
												<span>缩略图</span>
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
										<th class="item onsale">
											<div class="layui-table-cell">
												<span>上架</span>
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
										<th class="item operationbtn">
											<div class="layui-table-cell">
												<span>操作</span>
											</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="layui-table-body layui-table-main goods-data pageTable" name="goods-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="g.id" v-bind:data-index="index" class="" v-for="(g, index) in goods">
										<td class="item photourl">
											<div class="layui-table-cell laytable-cell-1-0-0">
												<img v-bind:src="g.photourl" onerror="defaultImg(this)" class="layui-nav-img">
											</div>
										</td>
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{g.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{g.name}}</div>
										</td>
										<td class="item onsale">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="g.onsale == 1"></i>
											</div>
										</td>
										<td class="item useflag">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="g.useflag == 1"></i>
											</div>
										</td>
										<td class="item creator">
											<div class="layui-table-cell laytable-cell-1-0-3">{{g.creator}}</div>
										</td>
										<td class="item createtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(g.createtime)}}</div>
										</td>
										<td class="item modifier">
											<div class="layui-table-cell laytable-cell-1-0-3">{{g.modifier}}</div>
										</td>
										<td class="item modifytime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(g.modifytime)}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{g.memo}}</div>
										</td>
										<td class="item operationbtn">
											<div class="layui-btn-group">
												<button
													class="layui-btn layui-btn-normal layui-btn-xs image"
													title="更多" v-on:click="images">更多</button>
												<button
													class="layui-btn layui-btn-warm layui-btn-xs onsale"
													title="上架" v-on:click="onsale" v-if="g.onsale!=1">上架</button>
												<button
													class="layui-btn layui-btn-xs onsale"
													title="下架" v-on:click="unsale" v-if="g.onsale==1">下架</button>
												<button
													class="layui-btn layui-btn-primary layui-btn-xs sku"
													title="库存" v-on:click="sku" v-if="g.onsale!=1">库存</button>
												<button
													class="layui-btn layui-btn-danger layui-btn-xs del"
													title="删除" v-on:click="del" v-if="g.onsale!=1">删除</button>
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

