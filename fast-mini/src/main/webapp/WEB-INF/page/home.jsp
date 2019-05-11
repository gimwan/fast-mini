<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>FAST</title>
<%@ include file="common/head.jsp"%>
<link rel="stylesheet" href="css/home.css">
<script type="text/javascript" src="js/home.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<c:import url="nav.jsp"></c:import>
		<c:import url="menu.jsp"></c:import>
		<div class="layui-body home"></div>
	</div>
</body>
</html>