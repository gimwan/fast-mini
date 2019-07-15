<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/application/micropage.css">
<script type="text/javascript" src="js/application/micropage.js"></script>
<div class="microPage">
	<div class="layui-tab layui-tab-brief nav-bar">
		<ul class="layui-tab-title">
		    <li class="layui-this">微页面</li>
		</ul>
	<div class="layui-tab-content"></div>
	</div>
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
				<div class="editView">
					<div class="layui-carousel" id="test1" lay-filter="test1">
						<div class="carouselBox" carousel-item="">
							<div><img src="http://n.sinaimg.cn/front/700/w960h540/20190110/31WY-hrkkweh9859087.jpg"></div>
						    <div><img src="https://wx2.sinaimg.cn/wap720/8c803935ly1fu3tsffel1j21120kun2u.jpg"></div>
						    <div><img src="http://n.sinaimg.cn/sinacn15/570/w1849h1121/20180817/e807-hhvciiw7088691.jpg"></div>
						    <div><img src="https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=84ec943a20738bd4db21b431918a876c/f7246b600c338744b5a0c49b5f0fd9f9d62aa0f4.jpg"></div>
						    <div><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563045387471&di=e28ab7e50b5edf792065259f77a35d9a&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F83025aafa40f4bfbb5163db50d4f78f0f6361808.jpg"></div>
						    <div><img src="https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8ba24e57aeec08fa390015a769ef3d4d/b17eca8065380cd7ed824805af44ad34588281be.jpg"></div>
						    <div><img src="https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8b41ea430d3b5bb5a1d726fe06d2d523/a6efce1b9d16fdfa0c242d06ba8f8c5495ee7b8a.jpg"></div>
						</div>
					</div>
					
					<div class="layui-search">
						<i class="layui-icon layui-icon-search"> </i>
						<input class="layui-input" placeholder="搜索" disabled="disabled"/>
					</div>
					
					<div class="layui-navigation">
						<div class="navigation-img-box">
							<img alt="" src="https://wx2.sinaimg.cn/wap720/8c803935ly1fu3tsffel1j21120kun2u.jpg" onerror="defaultImg(this)">
						</div>
						<div class="navigation-img-box">
							<img alt="" src="https://next.fuxi.com/images/microformfiles/2019-07-14-18-233426.jpg" onerror="defaultImg(this)">
						</div>
						<div class="navigation-img-box">
							<img alt="" src="http://n.sinaimg.cn/front/700/w960h540/20190110/31WY-hrkkweh9859087.jpg" onerror="defaultImg(this)">
						</div>
						<div class="navigation-img-box">
							<img alt="" src="http://n.sinaimg.cn/sinacn15/570/w1849h1121/20180817/e807-hhvciiw7088691.jpg" onerror="defaultImg(this)">
						</div>
					</div>
					
					<div class="layui-blank"></div>
					
					<div class="layui-notice">
						<div class="notice">公告213456</div>
					</div>
					
					<div class="layui-blank"></div>
					
					<div class="layui-title">
						<div class="title">栏目标题</div>
						<i class="layui-icon layui-icon-right"></i>
					</div>
					
					<div class="layui-blank"></div>
					
					<div class="layui-group">
						<div class="group-title">
							<div class="tab">tab1</div>
							<div class="tab">tab2</div>
							<div class="tab">tab3</div>
							<div class="tab">tab4</div>
						</div>
						<div class="group-list">
							<div class="goods">
								<div class="goodsimg">
									<img alt="" src="" onerror="defaultImg(this)">
								</div>
								<div class="goodsinfo">
									<div class="">商品名称</div>
									<div class="">¥199.00</div>
								</div>
							</div><div class="goods">
								<div class="goodsimg">
									<img alt="" src="https://next.fuxi.com/images/microformfiles/2019-07-14-18-233426.jpg" onerror="defaultImg(this)">
								</div>
								<div class="goodsinfo">
									<div class="">商品名称商品名称商品名称商品名称商品名称</div>
									<div class="">¥199.00</div>
								</div>
							</div><div class="goods">
								<div class="goodsimg">
									<img alt="" src="https://wx2.sinaimg.cn/wap720/8c803935ly1fu3tsffel1j21120kun2u.jpg" onerror="defaultImg(this)">
								</div>
								<div class="goodsinfo">
									<div class="">商品名称</div>
									<div class="">¥199.00</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
			</div>
		</div>
	</div>
</div>