<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="layui-header">
	<div class="layui-logo system-name">Fast Management</div>
	<ul class="layui-nav layui-layout-right">
		<li class="layui-nav-item">
			<a href="javascript:void(0);"> 
				<img src="${user.photourl}" class="layui-nav-img">
				<label class="username">${user.name}
				<span class="layui-nav-more"></span></label>
			</a>
			<!-- <dl class="layui-nav-child">
				<dd>
					<a href="javascript:void(0);">基本资料</a>
				</dd>
				<dd>
					<a href="javascript:void(0);">安全设置</a>
				</dd>
			</dl> -->
		</li>
		<li class="layui-nav-item"><a href="javascript:logout();">退出</a></li>
		<!-- <span class="layui-nav-bar"
			style="left: 54px; top: 55px; width: 0px; opacity: 0;"></span> -->
	</ul>
</div>