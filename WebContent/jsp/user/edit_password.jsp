<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>修改密码</title>
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
	function checkForm(){
				//trigger 事件执行完后，浏览器会为submit按钮获得焦点
    		    $("input").trigger("blur"); 
    		    var numError = $("b.error").length;
    		    if(numError){
    		        return false;
    		    }else return true;
			}
	
	//确认提交表单
    function submitForm(){
    	if(confirm("确认修改")){
    		return true;
    	}else return false;
    }
	
	function goBack(){
		$(location).attr("href", "<c:url value='/jsp/welcome.jsp'/>");
	}
			
	$(document).ready(
			function() {
				$("#old").blur(function() {
					//检验旧密码
                    var $msg = $("#msg_old");
							var passwordVal = $("#old").val();
							var regName = /^[A-Za-z0-9]+$/;
							if (passwordVal == "" || passwordVal.length > 10
									|| !regName.test(passwordVal)) {
								var errorMsg = "密码格式错误!";
								$msg.empty();
								$msg.append("<b class='error'>" + errorMsg
										+ "</b>");
							} else {
								$msg.empty();
								$msg.append("<b style='color:green'>密码格式正确</b>");
							}
				});
				$("#new").blur(
						function() {
							var $msg = $("#msg_new");
							var passwordVal = $("#new").val();
							var regName = /^[A-Za-z0-9]+$/;
							if (passwordVal == "" || passwordVal.length > 10
									|| !regName.test(passwordVal)) {
								var errorMsg = "密码格式错误!";
								$msg.empty();
								$msg.append("<b class='error'>" + errorMsg
										+ "</b>");
							} else {
								$msg.empty();
								$msg.append("<b style='color:green'>密码格式正确</b>");
							}
						});
				$("#again").blur(function(){
					   var $msg = $("#msg_again");
					   var first = $("#new").val();
					   var second = $("#again").val();
					   if(first!="" && second!="" && first==second){
						   $msg.empty();
						   $msg.append("<b style='color:green'>两次密码相同</b>");
					   }else{
						   var errorMsg = "两次密码不相同!";
							$msg.empty();
							$msg.append("<b class='error'>" + errorMsg
									+ "</b>"); 
					   }
				});

			});
</script>
<style type="text/css">
body {
	background: url('<c:url value='/image/background-2.jpg'/>') no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<span>当前位置》》<span style="color: red"><b>修改密码</b></span></span>
	<div class="container">
		<div class="col-sm-offset-3 col-md-offset-3"
			style="margin-top: 120px;">
			<form id="edit_form" class="form-horizontal" role="form" onsubmit="return submitForm()"
				 action="<c:url value='/user/updatePassword.action'/>" method="post"
				style="width: 500px; height: 300px; background-color: #FFFAFA; opacity: 0.9; padding-top: 1.875rem /* 30px */; border-radius: 10px;">
				<input type="hidden" name="userExtend.user_id"
					value="${user.user_id }"> <span
					style="padding-left: 1.25rem /* 20px */; font-size: 2.1875rem /* 35px */"><b>修改密码</b></span>
				<hr>
				<div class="form-group">
					<label for="old" class="col-sm-3 control-label"><b
						style="color: red">*</b>输入旧密码:</label>
					<div class="col-sm-5">
						<input name="old_password" type="password" class="form-control"
							id="old" value="">
					</div>
					<span id="msg_old" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;">${error }</span>
				</div>
				<div class="form-group">
					<label for="new" class="col-sm-3 control-label"><b
						style="color: red">*</b>输入新密码:</label>
					<div class="col-sm-5">
						<input name="new_password" type="password" class="form-control"
							id="new" placeholder="数字、字母组成,小于10位">
					</div>
					<span id="msg_new" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				<div class="form-group">
					<label for="again" class="col-sm-3 control-label"><b
						style="color: red">*</b>再输入一次:</label>
					<div class="col-sm-5">
						<input name="again_password" type="password" class="form-control"
							id="again" placeholder="数字、字母组成,小于10位">
					</div>
					<span id="msg_again" class="col-sm-4 help-block"
						style="color: red; font-size: 8px; padding-left: 0px;"></span>
				</div>
				<div class="form-group">
					<div class="col-sm-8 text-right">
						<input id="send" type="submit" value="保存" class="btn btn-success" onclick="return checkForm()">
						<input type="button" value="取消" class="btn btn-success" onclick="goBack()">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>