<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/goods/goodscategory.css">
<script type="text/javascript" src="js/goods/goodscategory.js"></script>
<div class="goodscategoryPage">
	<div class="layui-card layadmin-header nav-bar">
		<div class="layui-breadcrumb" lay-filter="breadcrumb"
			style="visibility: visible;">
			<a><cite>商品管理</cite></a> <span lay-separator="">/</span> <a><cite>分类</cite></a>
		</div>
	</div>
	
	<div class="layui-tab-item layui-show">
		<div class="layui-tab layui-tab-card" lay-filter="categoryTab">
			<ul class="layui-tab-title">
				<li class="layui-this">大类</li>
				<li>中类</li>
				<li>小类</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item big layui-show">
					<div class="operating">
						<div class="layui-btn-group">
							<button class="layui-btn layui-btn-sm add" title="新增"
								v-on:click="add">
								<i class="layui-icon"></i>
							</button>
							<button class="layui-btn layui-btn-sm del" title="删除"
								v-on:click="del">
								<i class="layui-icon"></i>
							</button>
						</div>
					</div>
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
									<div class="layui-table-body layui-table-main goodscategory-data pageTable" name="goodscategory-data">
										<table cellspacing="0" cellpadding="0" border="0"
											class="layui-table">
											<tbody>
												<tr v-bind:data-id="g.id" v-bind:data-index="index" class="" v-for="(g, index) in bigcategory">
													<td class="item code">
														<div class="layui-table-cell laytable-cell-1-0-0">
														<a href="javascript:void(0);" v-on:click="edit">{{g.code}}</a>
														</div>
													</td>
													<td class="item name">
														<div class="layui-table-cell laytable-cell-1-0-1">{{g.name}}</div>
													</td>
													<td class="item value">
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
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="operating parentCategory1">
						<div class="layui-btn-group">
							<button class="layui-btn layui-btn-sm add" title="新增"
								v-on:click="add">
								<i class="layui-icon"></i>
							</button>
							<button class="layui-btn layui-btn-sm del" title="删除"
								v-on:click="del">
								<i class="layui-icon"></i>
							</button>
						</div>
						<div class="selectView" >
					     	<form class="layui-form layTableCell" action="">
								<div class="layui-form-item">
									<div class="selectItem">
										<div class="layui-input-inline selectBox">
											<select lay-filter="parentCategory1" data-type="middle">
												<option value="" data-id="" data-index="" class="">请选择</option>
												<option v-bind:value="p.id" v-bind:data-id="p.id" v-bind:data-index="index" class="" v-for="(p, index) in parentcategory">{{p.name}}</option>
											</select>
										</div>
									</div>
								</div>
							</form>
					     </div>
					</div>
					<div class="layui-main middle">
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
									<div class="layui-table-body layui-table-main goodscategory-data pageTable" name="goodscategory-data">
										<table cellspacing="0" cellpadding="0" border="0"
											class="layui-table">
											<tbody>
												<tr v-bind:data-id="g.id" v-bind:data-index="index" class="" v-for="(g, index) in middlecategory">
													<td class="item code">
														<div class="layui-table-cell laytable-cell-1-0-0">
														<a href="javascript:void(0);" v-on:click="edit">{{g.code}}</a>
														</div>
													</td>
													<td class="item name">
														<div class="layui-table-cell laytable-cell-1-0-1">{{g.name}}</div>
													</td>
													<td class="item value">
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
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="operating">
						<div class="layui-btn-group">
							<button class="layui-btn layui-btn-sm add" title="新增"
								onclick="smallAdd()">
								<i class="layui-icon"></i>
							</button>
							<button class="layui-btn layui-btn-sm del" title="删除"
								onclick="smallDel()">
								<i class="layui-icon"></i>
							</button>
						</div>
						<div class="selectView" >
					     	<form class="layui-form layTableCell" action="">
								<div class="layui-form-item">
									<div class="selectItem">
										<div class="layui-input-inline selectBox parentCategory2" style="padding-right:5px;">
											<select lay-filter="parentCategory2" data-type="small1">
												<option value="" data-id="" data-index="" class="">请选择</option>
												<option v-bind:value="p.id" v-bind:data-id="p.id" v-bind:data-index="index" class="" v-for="(p, index) in parentcategory">{{p.name}}</option>
											</select>
										</div>
										<div class="layui-input-inline selectBox chilesCategory">
											<select lay-filter="parentCategory2sub" data-type="small2">
												<option value="" data-id="" data-index="" class="">请选择</option>
												<option v-bind:value="p.id" v-bind:data-id="p.id" v-bind:data-index="index" class="" v-for="(p, index) in chilescategory">{{p.name}}</option>
											</select>
										</div>
									</div>
								</div>
							</form>
					     </div>
					</div>
					<div class="layui-main small">
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
									<div class="layui-table-body layui-table-main goodscategory-data pageTable" name="goodscategory-data">
										<table cellspacing="0" cellpadding="0" border="0"
											class="layui-table">
											<tbody>
												<tr v-bind:data-id="g.id" v-bind:data-index="index" class="" v-for="(g, index) in smallcategory">
													<td class="item code">
														<div class="layui-table-cell laytable-cell-1-0-0">
														<a href="javascript:void(0);" v-on:click="edit">{{g.code}}</a>
														</div>
													</td>
													<td class="item name">
														<div class="layui-table-cell laytable-cell-1-0-1">{{g.name}}</div>
													</td>
													<td class="item value">
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
		</div>
	</div>
</div>
