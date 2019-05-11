<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/application/micropage.css">
<script type="text/javascript" src="js/application/micropage.js"></script>
<div class="microPage">
	<div class="configureView">
		<div class="configurePanel leftPanel">
			<div class="itemBox">
				<div class="item" v-bind:class="i.target" v-bind:title="i.name" v-bind:data-index="index" v-on:click="addItem" v-for="(i, index) in item">
					<img alt="" v-bind:src="i.image" class="itemImg">
					<div class="itemName">{{i.name}}</div>
				</div>
			</div>
		</div>
		<div class="configurePanel middlePanel">
			<div class="phoneBox">
				<div class="editView">
				</div>
			</div>
		</div>
		<div class="configurePanel rightPanel">
			<div class="editBox">
				<div class="editItem ad">
					<div class="layui-upload-drag" id="test10">
					  <i class="layui-icon"></i>
					  <p>点击上传，或将文件拖拽到此处</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>