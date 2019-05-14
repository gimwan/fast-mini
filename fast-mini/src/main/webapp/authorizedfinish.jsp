<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>授权成功</title>
<style type="text/css">
	body {
		font-family: "微软雅黑";
	}
	
	.color-4a7cac {
		color: #009688;
	}
	
	.accredit-modal {
		position: fixed;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		background-color: #000;
		opacity: 0.75;
		fiter: alpha(opacity=75);
	}
	
	.accredit-container {
		width: 600px;
		height: 240px;
		padding-top: 60px;
		background-color: #fff;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-top: -150px;
		margin-left: -300px;
		text-align: center;
	}
	
	.accredit-container i {
		font-size: 60px;
	}
	
	.accredit-container i + div {
		font-size: 24px;
		margin-top: 20px;
	}
	
	.accredit-container i + div + div {
		font-size: 18px;
		margin-top: 20px;
	}
	
	.accredit-firefox {
		padding-top: 80px;
		height: 220px;
	}
	
	.accredit-tips {
		font-size: 24px;
		font-weight: bold;
		padding-top: 40px;
	}
	
	#accredit-time {
		font-size: 14px;
		padding-top: 20px;
		color: #666666;
	}
	
	.time-out-color {
		color: red;
	}

</style>
<script src="static/scripts/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function() {
		closeTarget(5000);
	};
	function closeTarget(second) {
		var time = Math.floor(second);
		if(navigator.userAgent.indexOf("Firefox") > 0) {
			document.getElementById("accredit-time").innerHTML = '';
			document.getElementsByClassName("accredit-container")[0].setAttribute("class","accredit-container accredit-firefox")
	
		}else {
			document.getElementById("accredit-time").innerHTML = '本页将在<label class="time-out-color">'+time/1000+'</label>秒后自动关闭';
		}
		setInterval(function(){
			time -= 1000;
			if(time > 0) {
				navigator.userAgent.indexOf("Firefox") < 0 ? document.getElementById("accredit-time").innerHTML = '本页将在<label class="time-out-color">'+time/1000+'</label>秒后自动关闭' : '';
			}
		},1000);
		setTimeout(function() {
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
					window.opener = null;
					window.close();
				} else {
					window.open('', '_top');
					window.top.close();
				}
			} else {
				window.opener = null;
				window.open('', '_self', '');
				window.close();
			}
		},time);
	}
</script>
</head>
<body>
	<div class="accredit-modal"></div>
	<div class="accredit-container">
		<div class="color-4a7cac fontColor_1003 accredit-tips">恭喜，授权成功</div>
		<div id="accredit-time">本页将在<label class="time-out-color">5</label>秒后自动关闭</div>
	</div>
</body>
</html>