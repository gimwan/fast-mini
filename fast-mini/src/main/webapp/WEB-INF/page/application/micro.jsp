<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/application/micro.css">
<script type="text/javascript" src="js/application/micro.js"></script>
<div class="microPage">
	<div class="layui-tab layui-tab-brief nav-bar">
		<ul class="layui-tab-title">
		    <li class="layui-this">微页面</li>
		</ul>
	</div>
	
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
			<button class="layui-btn layui-btn-sm set" title="配置"
				v-on:click="set">
				<i class="layui-icon">&#xe716;</i>
			</button>
		</div>
	     
	     <div class="selectView publicPlatform" >
	     	<form class="layui-form layTableCell" action="">
				<div class="layui-form-item">
					<div class="selectItem">
						<div class="layui-input-inline selectBox">
							<select lay-filter="publicPlatformID">
								<option v-bind:value="p.id" v-bind:data-id="p.id" v-bind:data-index="index" class="" v-for="(p, index) in publicplatform">{{p.name}}</option>
							</select>
							<div class="layui-unselect layui-form-select">
								<div class="layui-select-title">
									<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
									<i class="layui-edge"></i>
								</div>
								<dl class="layui-anim layui-anim-upbit" style="">
									<dd lay-value="" class="layui-select-tips">请选择</dd>
									<dd class="" v-for="(p, index) in publicplatform">{{p.name}}</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
			</form>
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
										<th class="item homeflag">
											<div class="layui-table-cell">
												<span>是否首页</span>
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
						<div class="layui-table-body layui-table-main micro-data pageTable" name="micro-data">
							<table cellspacing="0" cellpadding="0" border="0"
								class="layui-table">
								<tbody>
									<tr v-bind:data-id="m.id" v-bind:data-index="index" class="" v-for="(m, index) in micro">
										<td class="item code">
											<div class="layui-table-cell laytable-cell-1-0-0">
											<a href="javascript:void(0);" v-on:click="edit">{{m.code}}</a>
											</div>
										</td>
										<td class="item name">
											<div class="layui-table-cell laytable-cell-1-0-1">{{m.name}}</div>
										</td>
										<td class="item homeflag">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="m.homeflag == 1"></i>
											</div>
										</td>
										<td class="item useflag">
											<div class="layui-table-cell laytable-cell-1-0-2">
												<i class="layui-icon layui-icon-ok" v-if="m.useflag == 1"></i>
											</div>
										</td>
										<td class="item creator">
											<div class="layui-table-cell laytable-cell-1-0-3">{{m.creator}}</div>
										</td>
										<td class="item createtime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(m.createtime)}}</div>
										</td>
										<td class="item modifier">
											<div class="layui-table-cell laytable-cell-1-0-3">{{m.modifier}}</div>
										</td>
										<td class="item modifytime time-item">
											<div class="layui-table-cell laytable-cell-1-0-3">{{formatDate(m.modifytime)}}</div>
										</td>
										<td class="item memo">
											<div class="layui-table-cell laytable-cell-1-0-3">{{m.memo}}</div>
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

