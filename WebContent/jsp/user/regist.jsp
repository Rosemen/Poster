<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>用户注册</title>
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
        //提交时检验
         function checkForm(){
        		    //trigger 事件执行完后，浏览器会为submit按钮获得焦点
        		    $("input").trigger("blur"); 
        		    var numError = $("b.error").length;
        		    if(numError){
        		        return false;
        		    }else return true;		
         }

       //加载页面时启动
       $(document).ready(function(){
               //校验用户名
         $("#username").blur( function () {
            var $msg = $("#msg_name");
            var nameVal = $("#username").val();
            var regName = /[~#^$@%&!*()<>:;'"{}【】  ]/;
                if(nameVal == "" || nameVal.length > 20 || regName.test(nameVal)){
                    var errorMsg = "用户名错误!";
                    $msg.empty();
                    $msg.append("<b class='error'>"+errorMsg+"</b>");
                }else{
                    $msg.empty();
                    $msg.append("输入正确");
                }
          } );

          //校验密码
         $("#password").blur( function () {
            var $msg = $("#msg_password");
            var passwordVal = $("#password").val();
            var regName = /^[A-Za-z0-9]+$/;
                if(passwordVal == "" || passwordVal.length > 10 || !regName.test(passwordVal)){
                    var errorMsg = "密码错误!";
                    $msg.empty();
                    $msg.append("<b class='error'>"+errorMsg+"</b>"); 
                }else{
                    $msg.empty();
                    $msg.append("输入正确");
                }
          } );

          //校验手机
         $("#phone").blur( function () {
            var $msg = $("#msg_phone");
            var phoneVal = $("#phone").val();
            var regName = /^[\d]{5,20}$/;
                if(phoneVal == "" || phoneVal.length != 11 || !regName.test(phoneVal)){
                    var errorMsg = "手机号码格式错误！";
                    $msg.empty();
                    $msg.append("<b class='error'>"+errorMsg+"</b>");   
                }else{
                    $msg.empty();
                    $msg.append("输入正确");
                }
          } );
          

          //校验邮箱
         $("#email").blur( function () {
            var $msg = $("#msg_email");
            var emailVal = $("#email").val();
            var regName = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
                if(!regName.test(emailVal)){
                    var errorMsg = "邮箱格式错误！";
                    $msg.empty();
                    $msg.append("<b class='error'>"+errorMsg+"</b>");  
                }else{
                    $msg.empty();
                    $msg.append("输入正确");
                }
          } );

          //校验社团或组织
         $("#org").blur( function () {
            var $msg = $("#msg_org");
            var orgVal = $("#org").val();

                if(orgVal == "" || orgVal.length > 50){
                    var errorMsg = "社团或组织名错误！";
                    $msg.empty();
                    $msg.append("<b class='error'>"+errorMsg+"</b>");
                }else{
                    $msg.empty();
                    $msg.append("输入正确");
                }
          } );

       });
        </script>
<style type="text/css">
body {
	background: url('<c:url value='/image/background-1.jpg '/>') no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-sm-offset-3 col-md-offset-3"
			style="margin-top: 5rem /* 80px */">
			<span
				style="text-align: center; color: white; font-size: 30px; text-shadow: 8px 2px 3px white;">海报粘贴申请系统</span>

			<form id="regist_form" class="form-horizontal" role="form" action="<c:url value='/user/regist.action'/>" method="post"
				style="width: 500px; background-color: #FFFAFA; opacity: 0.9; padding-top: 1.875rem /* 30px */; border-radius: 10px;">
				<span
					style="padding-left: 1.25rem /* 20px */; font-size: 2.1875rem /* 35px */"><b>用户注册</b></span>
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
							id="password" placeholder="数字、字母组成,小于10位" value="${user.user_password }">
					</div>
					<span id="msg_password" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label"><b
						style="color: red">*</b>手 机:</label>
					<div class="col-sm-5">
						<input name="user_phone" type="text" class="form-control"
							id="phone" placeholder="11位手机号" value="${user.user_phone }">
					</div>
					<span id="msg_phone" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-3 control-label"><b
						style="color: red">*</b>邮 箱:</label>
					<div class="col-sm-5">
						<input name="user_email" type="text" class="form-control"
							id="email" placeholder="如:158@163.com" value="${user.user_email }">
					</div>
					<span id="msg_email" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				<div class="form-group">
					<label for="org" class="col-sm-3 control-label"><b
						style="color: red">*</b>组 织:</label>
					<div class="col-sm-5">
						<input name="user_org" type="text" class="form-control" id="org"
							placeholder="组织或社团名:" value="${user.user_org }">
					</div>
					<span id="msg_org" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				<div class="form-group">
					<div class="col-sm-8 text-right">
						<input id="send" type="submit" value="注册" class="btn btn-success"
							onclick="return checkForm()"> <input type="reset"
							value="重置" class="btn btn-success">
					</div>
				</div>
				<a href="<c:url value='/jsp/user/login.jsp'/>" data-toggle="modal" data-dismiss="modal">已有账号？点我登录</a>
			</form>
		</div>
	</div>
</body>
</html>