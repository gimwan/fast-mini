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
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript" src="js/login.js"></script>

</head>
<body>
	<div class="loginView">
		<span class="test">{{message}}</span>
	</div>
</body>
</html>