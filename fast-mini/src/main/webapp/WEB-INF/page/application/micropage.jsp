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
				<div class="editView">
					<div v-for="(d, index) in setdata" v-bind:data-index="index" class="setItem" v-bind:key="d.id">
						<div class="layui-carousel" v-bind:data-index="index" v-bind:id="'carouselView'+index" v-bind:lay-filter="'carouselView'+index" v-if="d.kind == 1">
							<div class="carouselBox" carousel-item="">
								<div v-for="(dt, index) in d.detail"><img v-bind:src="dt.photourl" onerror="defaultImg(this)"></div>
							</div>
						</div>
						<div class="layui-search" v-else-if="d.kind == 2">
							<i class="layui-icon layui-icon-search"> </i>
							<input class="layui-input" placeholder="搜索" disabled="disabled"/>
						</div>
						<div class="layui-navigation" v-else-if="d.kind == 3">
							<div class="navigation-img-box" v-for="(dt, index) in d.detail">
								<img alt="" v-bind:src="dt.photourl" onerror="defaultImg(this)">
							</div>
						</div>
						<div class="layui-notice" v-else-if="d.kind == 4">
							<div class="notice">{{d.detail[0].text}}</div>
						</div>
						<div class="layui-title" v-else-if="d.kind == 5">
							<div class="title">{{d.detail[0].text}}</div>
							<i class="layui-icon layui-icon-right"></i>
						</div>
						<div class="layui-blank" v-else-if="d.kind == 6"></div>
						<div class="layui-group" v-else-if="d.kind == 7">
							<div class="group-title" v-if="d.detail.length > 1">
								<div class="tab" v-for="(dt, index) in d.detail">{{dt.grouping}}</div>
							</div>
							<div class="group-list">
								<div class="goods" v-for="(l, idx) in d.detail[0].list">
									<div class="goodsimg">
										<img v-bind:src="l.photourl" onerror="defaultImg(this)">
									</div>
									<div class="goodsinfo">
										<div class="">{{l.name}}</div>
										<div class="">{{l.kind==1?'¥'+l.price:l.point+'分'}}</div>
									</div>
								</div><div class="goods" v-if="d.detail[0].list.length<1">
									<div class="goodsimg">
										<img src="" onerror="defaultImg(this)">
									</div>
									<div class="goodsinfo">
										<div class="">商品名称</div>
										<div class="">¥888.88</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-classify" v-else-if="d.kind == 8">
							<div class="classify-title" v-if="d.detail.length > 1">
								<div class="tab" v-for="(dt, index) in d.detail">{{dt.category}}</div>
							</div>
							<div class="classify-list">
								<div class="goods" v-for="(l, idx) in d.detail[0].list">
									<div class="goodsimg">
										<img v-bind:src="l.photourl" onerror="defaultImg(this)">
									</div>
									<div class="goodsinfo">
										<div class="">{{l.name}}</div>
										<div class="">{{l.kind==1?'¥'+l.price:l.point+'分'}}</div>
									</div>
								</div><div class="goods" v-if="d.detail[0].list.length<1">
									<div class="goodsimg">
										<img src="" onerror="defaultImg(this)">
									</div>
									<div class="goodsinfo">
										<div class="">商品名称</div>
										<div class="">¥888.88</div>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-classify" v-else-if="d.kind == 9">
							<div class="classify-list">
								<div class="goods" v-for="(dt, index in d.detail">
									<div class="goodsimg">
										<img v-bind:src="dt.photourl" onerror="defaultImg(this)">
									</div>
									<div class="goodsinfo">
										<div class="">{{dt.goodsname}}</div>
										<div class="">{{dt.type==1?'¥'+dt.price:dt.point+'分'}}</div>
									</div>
								</div><div class="goods" v-if="d.detail.length<1">
									<div class="goodsimg">
										<img src="" onerror="defaultImg(this)">
									</div>
									<div class="goodsinfo">
										<div class="">商品名称</div>
										<div class="">¥888.88</div>
									</div>
								</div>
							</div>
						</div>
						<div class="chooseBox" v-bind:class="d.choose == 1 ? 'choose-view' : ''"></div>
						<i class="layui-icon layui-icon-delete deleteIcon"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="configurePanel rightPanel">
			<div class="editBox">
				<div class="editItem" v-for="(e, index) in editdata" v-bind:data-index="index" v-bind:key="e.id">
					<!-- 广告 -->
					<div class="layTable uploadField ad" v-if="e.kind == 1 && e.choose == 1" v-for="(dt, i) in e.detail" v-bind:data-index="index" v-bind:data-idx="i" v-bind:key="e.id+dt.id+index+i+i">
						<div class="uploadBox">
							<div class="layui-upload-drag layTableCell" v-bind:id="'ad'+index+i" v-bind:key="index+i" v-bind:lay-filter="index+i">
								<img alt="" v-bind:src="dt.photourl" onerror="defaultImg(this)" v-if="dt.photourl">
								<i class="layui-icon" v-else>&#xe62f;</i>
							</div>
							<i class="layui-icon layui-icon-delete"></i>
						</div>
						<form class="layui-form layTableCell" action="">
							<div class="layui-form-item">
								<div class="selectItem">
									<label class="layui-form-label layui-inline selectTitle">链接类型：</label>
									<div class="layui-input-inline selectBox">
										<select id="linkType" lay-filter="linkType" v-bind:data-pindex="index" v-bind:data-index="i">
											<option value="0" v-if="dt.type==0" selected="">无</option>
											<option value="0" v-else>无</option>
											<option value="1" v-if="dt.type==1" selected>微页面</option>
											<option value="1" v-else>微页面</option>
											<option value="2" v-if="dt.type==2" selected>商品分组</option>
											<option value="2" v-else>商品分组</option>
											<option value="3" v-if="dt.type==3" selected>商品分类</option>
											<option value="3" v-else>商品分类</option>
											<option value="4" v-if="dt.type==3" selected>小程序页面</option>
											<option value="4" v-else>小程序页面</option>
										</select>
									</div>
								</div>
								<div class="selectItem">
									<label class="layui-form-label layui-inline selectTitle"
										v-if="dt.type==1||dt.type==2||dt.type==3||dt.type==4">{{dt.type==1?'微页面':(dt.type==2?'商品分组':(dt.type==3?'商品分类':(dt.type==4?'小程序页面':'')))}}：</label>
									<div class="layui-input-inline selectBox popup" v-if="dt.type==1">
										<div class="edit-title" style="display:none;"><span class="name">微页面</span></div>
										<input type="text" v-bind:data-id="dt.first" v-bind:value="dt.grouping"
											data-url="./data/page?table=micropage"
											class="layui-input value" readonly="readonly"
											onchange="saveMicropage(this)" v-bind:data-pindex="index"
											v-bind:data-index="i">
									</div>
									<div class="layui-input-inline selectBox popup" v-else-if="dt.type==2">
										<div class="edit-title" style="display:none;"><span class="name">商品分组</span></div>
										<input type="text" v-bind:data-id="dt.first" v-bind:value="dt.grouping"
											data-url="./data/page?table=goodsgrouping"
											class="layui-input value" readonly="readonly"
											onchange="saveGrouping(this)" v-bind:data-pindex="index"
											v-bind:data-index="i">
									</div>
									<div class="layui-input-inline selectBox cascade" v-else-if="dt.type==3">
										<div class="cascade-value">
											<div class="edit-value" data-field="bigcategory">
												<input type="text" v-bind:data-id="dt.first"
													v-bind:value="dt.categoryone"
													data-url="./goodscategory/list?grade=1&pagesize=100"
													data-grade="1" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="i" onchange="saveFirst(this)">
											</div>
											<div class="edit-value" data-field="middlecategory">
												<input type="text" v-bind:data-id="dt.second"
													v-bind:value="dt.categorytwo"
													data-url="./goodscategory/list?grade=2&pagesize=100"
													data-grade="2" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="i" onchange="saveSecond(this)">
											</div>
											<div class="edit-value" data-field="smallcategory">
												<input type="text" v-bind:data-id="dt.third"
													v-bind:value="dt.categorythree"
													data-url="./goodscategory/list?grade=3&pagesize=100"
													data-grade="3" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="i" onchange="saveThird(this)">
											</div>
										</div>
									</div>
									<div class="layui-input-inline selectBox" v-else-if="dt.type==4">
										<input type="text" name="targetpath" class="layui-input"
											onblur="saveTargetPath(this)" v-bind:value="dt.targetpath"
											v-bind:data-pindex="index" v-bind:data-index="i">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="uploadField ad iconAdd" v-if="e.kind == 1 && e.choose == 1 && e.detail.length!=5" key="adAdd">
						<i class="layui-icon layui-icon-add-circle" v-bind:data-kind="e.kind" v-bind:data-index="index" v-on:click="addItem"></i>
					</div>
					<!-- 导航 -->
					<div class="layTable uploadField ad" v-if="e.kind == 3 && e.choose == 1" v-for="(dt, i) in e.detail" v-bind:data-index="index" v-bind:data-idx="i" v-bind:key="e.id+dt.id+index+i">
						<div class="uploadBox">
							<div class="layui-upload-drag layTableCell">
								<img alt="" v-bind:src="dt.photourl" onerror="defaultImg(this)" v-if="dt.photourl">
								<i class="layui-icon" v-else></i>
							</div>
							<i class="layui-icon layui-icon-delete"></i>
						</div>
						<form class="layui-form layTableCell" action="">
							<div class="layui-form-item">
								<div class="selectItem">
									<label class="layui-form-label layui-inline selectTitle">链接类型：</label>
									<div class="layui-input-inline selectBox">
										<select id="linkType"  lay-filter="linkType" v-bind:data-pindex="index" v-bind:data-index="i">
											<option value="0" v-if="dt.type==0" selected="">无</option>
											<option value="0" v-else>无</option>
											<option value="1" v-if="dt.type==1" selected>微页面</option>
											<option value="1" v-else>微页面</option>
											<option value="2" v-if="dt.type==2" selected>商品分组</option>
											<option value="2" v-else>商品分组</option>
											<option value="3" v-if="dt.type==3" selected>商品分类</option>
											<option value="3" v-else>商品分类</option>
											<option value="4" v-if="dt.type==3" selected>小程序页面</option>
											<option value="4" v-else>小程序页面</option>
										</select>
									</div>
								</div>
								<div class="selectItem">
									<label class="layui-form-label layui-inline selectTitle" v-if="dt.type==1||dt.type==2||dt.type==3||dt.type==4">{{dt.type==1?'微页面':(dt.type==2?'商品分组':(dt.type==3?'商品分类':(dt.type==4?'小程序页面':'')))}}：</label>
									<div class="layui-input-inline selectBox popup" v-if="dt.type==1">
										<div class="edit-title" style="display:none;"><span class="name">微页面</span></div>
										<input type="text" v-bind:data-id="dt.first" v-bind:value="dt.grouping"
											data-url="./data/page?table=micropage"
											class="layui-input value" readonly="readonly"
											onchange="saveMicropage(this)" v-bind:data-pindex="index"
											v-bind:data-index="i">
									</div>
									<div class="layui-input-inline selectBox popup" v-else-if="dt.type==2">
										<div class="edit-title" style="display:none;"><span class="name">商品分组</span></div>
										<input type="text" v-bind:data-id="dt.first" v-bind:value="dt.grouping"
											data-url="./data/page?table=goodsgrouping"
											class="layui-input value" readonly="readonly"
											onchange="saveGrouping(this)" v-bind:data-pindex="index"
											v-bind:data-index="i">
									</div>
									<div class="layui-input-inline selectBox cascade" v-else-if="dt.type==3">
										<div class="cascade-value">
											<div class="edit-value" data-field="bigcategory">
												<input type="text" v-bind:data-id="dt.first"
													v-bind:value="dt.categoryone"
													data-url="./goodscategory/list?grade=1&pagesize=100"
													data-grade="1" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="i" onchange="saveFirst(this)">
											</div>
											<div class="edit-value" data-field="middlecategory">
												<input type="text" v-bind:data-id="dt.second"
													v-bind:value="dt.categorytwo"
													data-url="./goodscategory/list?grade=2&pagesize=100"
													data-grade="2" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="i" onchange="saveSecond(this)">
											</div>
											<div class="edit-value" data-field="smallcategory">
												<input type="text" v-bind:data-id="dt.third"
													v-bind:value="dt.categorythree"
													data-url="./goodscategory/list?grade=3&pagesize=100"
													data-grade="3" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="i" onchange="saveThird(this)">
											</div>
										</div>
									</div>
									<div class="layui-input-inline selectBox" v-else-if="dt.type==4">
										<input type="text" name="targetpath" class="layui-input"
											onblur="saveTargetPath(this)" v-bind:value="dt.targetpath"
											v-bind:data-pindex="index" v-bind:data-index="i">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="uploadField ad iconAdd" v-if="e.kind == 3 && e.choose == 1 && e.detail.length!=4" key="navigationAdd">
						<i class="layui-icon layui-icon-add-circle" v-bind:data-kind="e.kind" v-bind:data-index="index" v-on:click="addItem"></i>
					</div>
					<!-- 公告 -->
					<div class="notice" v-if="e.kind == 4 && e.choose == 1">
						<form class="layui-form">
							<label class="layui-form-label layui-inline blockTitle">公告：</label>
							<div class="layui-input-block blockInput">
								<input type="text" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入公告" class="layui-input"
									oninput="spellChange(this)"
									onporpertychange="spellChange(this)"
									v-bind:value="e.detail[0].text">
							</div>
						</form>
					</div>
					<!-- 栏目标题 -->
					<div class="columntitle" v-if="e.kind == 5 && e.choose == 1">
						<form class="layui-form">
							<label class="layui-form-label layui-inline blockTitle">标题：</label>
							<div class="layui-input-block blockInput">
								<input type="text" name="title" lay-verify="title"
									autocomplete="off" placeholder="请输入标题" class="layui-input"
									oninput="spellChange(this)"
									onporpertychange="spellChange(this)"
									v-bind:value="e.detail[0].text">
							</div>
							<div class="layui-form-item">
								<div class="selectItem">
									<label class="layui-form-label layui-inline selectTitle">链接类型：</label>
									<div class="layui-input-inline selectBox">
										<select id="linkType"  lay-filter="linkType" v-bind:data-pindex="index" v-bind:data-index="0">
											<option value="" v-if="e.detail[0].type==0" selected="">无</option>
											<option value="" v-else>无</option>
											<option value="1" v-if="e.detail[0].type==1" selected>微页面</option>
											<option value="1" v-else>微页面</option>
											<option value="2" v-if="e.detail[0].type==2" selected>商品分组</option>
											<option value="2" v-else>商品分组</option>
											<option value="3" v-if="e.detail[0].type==3" selected>商品分类</option>
											<option value="3" v-else>商品分类</option>
											<option value="4" v-if="e.detail[0].type==4" selected>小程序页面</option>
											<option value="4" v-else>小程序页面</option>
										</select>
									</div>
								</div>
								<div class="selectItem">
									<label class="layui-form-label layui-inline selectTitle"
										v-if="e.detail[0].type==1||e.detail[0].type==2||e.detail[0].type==3||e.detail[0].type==4">{{e.detail[0].type==1?'微页面':(e.detail[0].type==2?'商品分组':(e.detail[0].type==3?'商品分类':(e.detail[0].type==4?'小程序页面':'')))}}：</label>
									<div class="layui-input-inline selectBox popup" v-if="e.detail[0].type==1">
										<div class="edit-title" style="display:none;"><span class="name">微页面</span></div>
										<input type="text" v-bind:data-id="e.detail[0].first" v-bind:value="dt.grouping"
											data-url="./data/page?table=micropage"
											class="layui-input value" readonly="readonly"
											onchange="saveMicropage(this)" v-bind:data-pindex="index"
											v-bind:data-index="0">
									</div>
									<div class="layui-input-inline selectBox popup" v-else-if="e.detail[0].type==2">
										<div class="edit-title" style="display:none;"><span class="name">商品分组</span></div>
										<input type="text" v-bind:data-id="e.detail[0].first" v-bind:value="e.detail[0].grouping"
											data-url="./data/page?table=goodsgrouping"
											class="layui-input value" readonly="readonly"
											onchange="saveGrouping(this)" v-bind:data-pindex="index"
											v-bind:data-index="0">
									</div>
									<div class="layui-input-inline selectBox cascade" v-else-if="e.detail[0].type==3">
										<div class="cascade-value">
											<div class="edit-value" data-field="bigcategory">
												<input type="text" v-bind:data-id="e.detail[0].first"
													v-bind:value="e.detail[0].categoryone"
													data-url="./goodscategory/list?grade=1&pagesize=100"
													data-grade="1" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="0" onchange="saveFirst(this)">
											</div>
											<div class="edit-value" data-field="middlecategory">
												<input type="text" v-bind:data-id="e.detail[0].second"
													v-bind:value="e.detail[0].categorytwo"
													data-url="./goodscategory/list?grade=2&pagesize=100"
													data-grade="2" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="0" onchange="saveSecond(this)">
											</div>
											<div class="edit-value" data-field="smallcategory">
												<input type="text" v-bind:data-id="e.detail[0].third"
													v-bind:value="e.detail[0].categorythree"
													data-url="./goodscategory/list?grade=3&pagesize=100"
													data-grade="3" class="layui-input value"
													readonly="readonly" v-bind:data-pindex="index"
													v-bind:data-index="0" onchange="saveThird(this)">
											</div>
										</div>
									</div>
									<div class="layui-input-inline selectBox" v-else-if="e.detail[0].type==4">
										<input type="text" name="targetpath" class="layui-input"
											onblur="saveTargetPath(this)" v-bind:value="e.detail[0].targetpath"
											v-bind:data-pindex="index" v-bind:data-index="0">
									</div>
								</div>
							</div>
						</form>
					</div>
					<!-- 商品分类 -->
					<div class="category" v-if="e.kind == 8 && e.choose == 1">
						<form class="layui-form">
						    <div class="selectBlock cascade" v-for="(dt, i) in e.detail" v-bind:data-index="index" v-bind:data-idx="i" v-bind:key="e.id+dt.id+index+i">
								<label class="layui-form-label layui-inline selectTitle">商品分类：</label>
								<div class="cascade-value">
									<div class="edit-value" data-field="bigcategory">
										<input type="text" v-bind:data-id="dt.first"
											v-bind:value="dt.categoryone"
											data-url="./goodscategory/list?grade=1&pagesize=100"
											data-grade="1" class="layui-input value"
											readonly="readonly" v-bind:data-pindex="index"
											v-bind:data-index="0" onchange="saveFirst(this)">
									</div>
									<div class="edit-value" data-field="middlecategory">
										<input type="text" v-bind:data-id="dt.second"
											v-bind:value="dt.categorytwo"
											data-url="./goodscategory/list?grade=2&pagesize=100"
											data-grade="2" class="layui-input value"
											readonly="readonly" v-bind:data-pindex="index"
											v-bind:data-index="0" onchange="saveSecond(this)">
									</div>
									<div class="edit-value" data-field="smallcategory">
										<input type="text" v-bind:data-id="dt.third"
											v-bind:value="dt.categorythree"
											data-url="./goodscategory/list?grade=3&pagesize=100"
											data-grade="3" class="layui-input value"
											readonly="readonly" v-bind:data-pindex="index"
											v-bind:data-index="0" onchange="saveThird(this)">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="category iconAdd" v-if="e.kind == 8 && e.choose == 1 && e.detail.length!=4" key="navigationAdd">
						<i class="layui-icon layui-icon-add-circle" v-bind:data-kind="e.kind" v-bind:data-index="index" v-on:click="addItem"></i>
					</div>
					<!-- 商品分组 -->
					<div class="group" v-if="e.kind == 7 && e.choose == 1">
						<form class="layui-form">
						    <div class="selectBlock popup" v-for="(dt, i) in e.detail" v-bind:data-index="index" v-bind:data-idx="i" v-bind:key="e.id+dt.id+index+i">
								<label class="layui-form-label layui-inline selectTitle">商品分组：</label>
								<div class="group-value">
									<div class="edit-title" style="display:none;"><span class="name">商品分组</span></div>
									<input type="text" v-bind:data-id="dt.first" v-bind:value="dt.grouping"
											data-url="./data/page?table=goodsgrouping"
											class="layui-input value" readonly="readonly"
											onchange="saveGrouping(this)" v-bind:data-pindex="index"
											v-bind:data-index="i">
								</div>
							</div>
						</form>
					</div>
					<div class="category iconAdd" v-if="e.kind == 7 && e.choose == 1 && e.detail.length!=4" key="categoryAdd">
						<i class="layui-icon layui-icon-add-circle" v-bind:data-kind="e.kind" v-bind:data-index="index" v-on:click="addItem"></i>
					</div>
					<!-- 商品 -->
					<div class="goods" v-if="e.kind == 9 && e.choose == 1">
						<form class="layui-form">
						    <div class="selectBlock popup">
								<label class="layui-form-label layui-inline selectTitle">商品：</label>
								<div class="goodsBox">
									<div class="uploadBox uploadField" v-for="(dt, i) in e.detail" v-bind:data-index="index" v-bind:data-idx="i" v-bind:key="e.id+dt.id+index+i">
										<div class="layui-upload-drag layTableCell">
											<img alt="" v-bind:src="dt.photourl" onerror="defaultImg(this)" v-if="dt.photourl">
											<i class="layui-icon" v-else></i>
										</div>
										<i class="layui-icon layui-icon-delete"></i>
									</div>
									<div class="category iconAdd" v-if="e.detail.length!=10" key="categoryAdd">
										<i class="layui-icon layui-icon-add-circle" v-bind:data-kind="e.kind" v-bind:data-index="index" v-on:click="addItem"></i>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="pageid" value="${pageid}">
</div>