<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>登录</title>
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div class="login">
		<div class="login-wrapper">
			<div class="login-box login-login">
				<div class="login-box-inner">
					<div class="login-group">
						<label>用户</label>
						<input type="text" name="code" class="login-input" autocomplete="off" v-model="code">
					</div>
					<div class="login-group password-group">
						<label>密码</label>
						<input type="password" name="password" class="login-input" autocomplete="off" v-model="password">
					</div>
					<button class="login-btn login-btn" v-on:click="loginCheck">登录</button>
					<div class="text-foot"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
