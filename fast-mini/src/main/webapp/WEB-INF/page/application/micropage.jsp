<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/application/micropage.css">
<script type="text/javascript" src="js/application/micropage.js"></script>
<div class="microPage">
	<div class="configureView">
		<div class="configurePanel leftPanel">
			<div class="itemBox">
				<div class="item" v-bind:class="i.target" v-bind:title="i.name"
					v-bind:data-index="index" v-on:click="addItem"
					v-for="(i, index) in item">
					<img alt="" v-bind:src="i.image" class="itemImg">
					<div class="itemName">{{i.name}}</div>
				</div>
			</div>
		</div>
		<div class="configurePanel middlePanel">
			<div class="phoneBox">
				<div class="editView"></div>
			</div>
		</div>
		<div class="configurePanel rightPanel">
			<div class="editBox">
				<div class="editItem layTable uploadField ad">
					<div class="layui-upload-drag layTableCell">
						<i class="layui-icon"></i>
					</div>
					<form class="layui-form layTableCell" action="">
						<div class="layui-form-item">
							<div class="selectItem">
								<label class="layui-form-label layui-inline selectTitle">链接类型：</label>
								<div class="layui-input-inline selectBox">
									<select>
										<option value="" selected="">请选择</option>
										<option value="1">微页面</option>
										<option value="2">商品分组</option>
										<option value="3">商品分类</option>
										<option value="4">小程序页面</option>
									</select>
									<div class="layui-unselect layui-form-select">
										<div class="layui-select-title">
											<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
											<i class="layui-edge"></i>
										</div>
										<dl class="layui-anim layui-anim-upbit" style="">
											<dd lay-value="" class="layui-select-tips">请选择</dd>
											<dd lay-value="1" class="">微页面</dd>
											<dd lay-value="2" class="">商品分组</dd>
											<dd lay-value="3" class="">商品分类</dd>
											<dd lay-value="4" class="">小程序页面</dd>
										</dl>
									</div>
								</div>
							</div>
							<div class="selectItem">
								<label class="layui-form-label layui-inline selectTitle">微页面：</label>
								<div class="layui-input-inline selectBox">
									<select>
										<option value="" selected=""></option>
										<option value="0">写作</option>
										<option value="1">阅读</option>
										<option value="2">游戏</option>
										<option value="3">音乐</option>
										<option value="4">旅行</option>
									</select>
									<div class="layui-unselect layui-form-select">
										<div class="layui-select-title">
											<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
											<i class="layui-edge"></i>
										</div>
										<dl class="layui-anim layui-anim-upbit" style="">
											<dd lay-value="" class="layui-select-tips">请选择</dd>
											<dd lay-value="0" class="">写作</dd>
											<dd lay-value="1" class="layui-this">阅读</dd>
											<dd lay-value="2" class="">游戏</dd>
											<dd lay-value="3" class="">音乐</dd>
											<dd lay-value="4" class="">旅行</dd>
										</dl>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="editItem notice">
					<form class="layui-form">
						<label class="layui-form-label layui-inline blockTitle">公告：</label>
						<div class="layui-input-block blockInput">
					    	<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入公告" class="layui-input">
					    </div>
					</form>
				</div>
				<div class="editItem columntitle">
					<form class="layui-form">
						<label class="layui-form-label layui-inline blockTitle">标题：</label>
						<div class="layui-input-block blockInput">
					    	<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
					    </div>
					    <div class="selectItem selectBlock">
							<label class="layui-form-label layui-inline selectTitle">链接类型：</label>
							<div class="layui-input-inline selectBox">
								<select>
									<option value="" selected="">请选择</option>
									<option value="1">微页面</option>
									<option value="2">商品分组</option>
									<option value="3">商品分类</option>
									<option value="4">小程序页面</option>
								</select>
								<div class="layui-unselect layui-form-select">
									<div class="layui-select-title">
										<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
										<i class="layui-edge"></i>
									</div>
									<dl class="layui-anim layui-anim-upbit" style="">
										<dd lay-value="" class="layui-select-tips">请选择</dd>
										<dd lay-value="1" class="">微页面</dd>
										<dd lay-value="2" class="">商品分组</dd>
										<dd lay-value="3" class="">商品分类</dd>
										<dd lay-value="4" class="">小程序页面</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="selectItem selectBlock">
							<label class="layui-form-label layui-inline selectTitle">微页面：</label>
							<div class="layui-input-inline selectBox">
								<select>
									<option value="" selected=""></option>
									<option value="0">写作</option>
									<option value="1">阅读</option>
									<option value="2">游戏</option>
									<option value="3">音乐</option>
									<option value="4">旅行</option>
								</select>
								<div class="layui-unselect layui-form-select">
									<div class="layui-select-title">
										<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
										<i class="layui-edge"></i>
									</div>
									<dl class="layui-anim layui-anim-upbit" style="">
										<dd lay-value="" class="layui-select-tips">请选择</dd>
										<dd lay-value="0" class="">写作</dd>
										<dd lay-value="1" class="layui-this">阅读</dd>
										<dd lay-value="2" class="">游戏</dd>
										<dd lay-value="3" class="">音乐</dd>
										<dd lay-value="4" class="">旅行</dd>
									</dl>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="editItem category">
					<form class="layui-form">
					    <div class="selectBlock">
							<label class="layui-form-label layui-inline selectTitle">商品分类：</label>
							<div class="layui-input-inline selectBox cascadeSelect">
								<select>
									<option value="" selected="">请选择</option>
									<option value="1">上衣</option>
									<option value="2">裤子</option>
									<option value="3">鞋子</option>
									<option value="4">箱包</option>
								</select>
								<div class="layui-unselect layui-form-select">
									<div class="layui-select-title">
										<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
										<i class="layui-edge"></i>
									</div>
									<dl class="layui-anim layui-anim-upbit" style="">
										<dd lay-value="" class="layui-select-tips">请选择</dd>
										<dd lay-value="1" class="">上衣</dd>
										<dd lay-value="2" class="">裤子</dd>
										<dd lay-value="3" class="">鞋子</dd>
										<dd lay-value="4" class="">箱包</dd>
									</dl>
								</div>
							</div>
							<div class="layui-input-inline selectBox cascadeSelect">
								<select>
									<option value="" selected="">请选择</option>
									<option value="1">卫衣</option>
									<option value="2">T恤</option>
									<option value="3">长裤</option>
									<option value="4">短裤</option>
								</select>
								<div class="layui-unselect layui-form-select">
									<div class="layui-select-title">
										<input type="text" placeholder="请选择" class="layui-input layui-unselect"> 
										<i class="layui-edge"></i>
									</div>
									<dl class="layui-anim layui-anim-upbit" style="">
										<dd lay-value="" class="layui-select-tips">请选择</dd>
										<dd lay-value="1" class="">卫衣</dd>
										<dd lay-value="2" class="">T恤</dd>
										<dd lay-value="3" class="">长裤</dd>
										<dd lay-value="4" class="">短裤</dd>
									</dl>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>