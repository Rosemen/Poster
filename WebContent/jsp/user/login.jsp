<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>用户登录</title>
<!--用百度的静态资源库的cdn安装bootstrap环境-->
<!-- Bootstrap 核心 CSS 文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!--font-awesome 核心我CSS 文件-->
<link
	href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- 在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
        
</script>
<style type="text/css">
body {
	background: url('<c:url value='/image/background-1.jpg'/>') no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-sm-offset-3 col-md-offset-3"
			style="margin-top: 120px;">
			<span
				style="text-align: center; color: white; font-size: 30px; text-shadow: 8px 2px 3px white;">海报粘贴申请系统</span>

			<form id="regist_form" class="form-horizontal" role="form" action="<c:url value='/user/login.action'/>" method="post"
				style="width: 500px;height:300px; background-color: #FFFAFA; opacity: 0.9; padding-top: 1.875rem /* 30px */; border-radius: 10px;">
				<span
					style="padding-left: 1.25rem /* 20px */; font-size: 2.1875rem /* 35px */"><b>用户登录</b></span>
				<hr>
				<div class="form-group">
					<label for="username" class="col-sm-3 control-label"><b
						style="color: red">*</b>用户名:</label>
					<div class="col-sm-5">
						<input name="user_name" type="text" class="form-control"
							id="username" placeholder="不含特殊字符,小于20位" value="${user.user_name }">
					</div>
					<span id="msg_name" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;">${error }</span>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label"><b
						style="color: red">*</b>密 码:</label>
					<div class="col-sm-5">
						<input name="user_password" type="password" class="form-control"
							id="password" placeholder="数字、字母组成,小于10位">
					</div>
					<span id="msg_password" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				
				<div class="form-group">
					<div class="col-sm-8 text-right">
						<input id="send" type="submit" value="登录" class="btn btn-success"
							>
					</div>
				</div>
				<a href="<c:url value='/jsp/user/regist.jsp'/>" data-toggle="modal" data-dismiss="modal">没有账号？点我注册</a>
			</form>
		</div>
	</div>
</body>
</html>