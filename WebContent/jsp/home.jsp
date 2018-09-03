<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>系统主页</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
		$(".nav li").click(function() {
			$(".active").removeClass('active');
			$(this).addClass("active");
		});
        
        
        function changePic(){
			$("#upload").click();
            $("#upload").change(function(){
        	var size = this.files.length;
        	if(size == 1){
        		$("#pic_form").submit();
        	}
        	
            });
		}
		
		$(document).ready(function(){
			 <c:choose>
			    <c:when test="${not empty user.user_pic}">
			      $("#head").attr("src","${user.user_pic}");
			    </c:when>
			    <c:otherwise>
			    $("#head").attr("src","/pic/image/1.jpg");
			    </c:otherwise>
			 </c:choose>
			});
		
	</script>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	margin: 0;
	overflow: hidden;
	background-color: #FFFFFF;
	font-family: "Microsoft YaHei", sans-serif;
}

.pageSidebar {
	width: 240px;
	height: 100%;
	padding-bottom: 30px;
	overflow: auto;
	background-color: #e3e3e3;
}

.splitter {
	width: 5px;
	height: 100%;
	bottom: 0;
	left: 240px;
	position: absolute;
	overflow: hidden;
	background-color: #fff;
}

.pageContent {
	height: 100%;
	min-width: 768px;
	left: 246px;
	top: 0;
	right: 0;
	z-index: 3;
	padding-bottom: 30px;
	position: absolute;
}

.pageContainer {
	bottom: 0;
	left: 0;
	right: 0;
	top: 53px;
	overflow: auto;
	position: absolute;
	width: 100%;
}

.footer {
	width: 100%;
	height: 30px;
	line-height: 30px;
	margin-top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	position: absolute;
	z-index: 10;
	background-color: #DFDFDF;
}
</style>
</head>

<body>
	<!--顶部导航栏部分-->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<span class="navbar-brand" title="logoTitle" style="color: red">欢迎使用</span>
			</div>
			
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
				    <li role="presentation">
				       <img id="head" title="点击更换头像" src="" width="50px" height="50px" style="border-radius: 30px" onclick="changePic()">
				   </li>
					<li role="presentation"><a href="#">
			<c:choose>
			        <c:when test="${empty user or user.user_type eq 0 }">
			                              普通用户:
			       	</c:when>
			        <c:otherwise>
			                             管理员:
			        </c:otherwise>
			</c:choose>
					<span
							class="badge">${user.user_name }</span></a></li>
					<li><a href="<c:url value='/user/exit.action'/>"><span class="glyphicon glyphicon-lock"></span>退出登录
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<!-- 更换头像表单 -->
	<form action="<c:url value='/user/uploadPic.action'/>" method="post" id="pic_form" enctype="multipart/form-data">
	   <input id="upload" type="file" style="display: none;" name="picture" >
	</form>

	<!-- 中间主体内容部分 -->
	<div class="pageContainer">
		<!-- 左侧导航栏 -->
		<div class="pageSidebar">
			<ul class="nav nav-stacked nav-pills">
			<c:choose>
			    <c:when test="${empty user or user.user_type eq 0 }">
				<li role="presentation"><a href="<c:url value='/jsp/poster/apply.jsp'/>" target="mainFrame">海报申请</a>
				</li>
				<li role="presentation"><a href="<c:url value='/poster/getAllRecords.action'/>" target="mainFrame">我的申请</a>

				</li>
				<li role="presentation"><a href="<c:url value='/'/>" target="mainFrame">我的通知</a>
				</li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> 个人设置<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/jsp/user/edit_password.jsp'/>" target="mainFrame">修改密码</a></li>
						<li><a href="<c:url value='/jsp/user/edit.jsp'/>" target="mainFrame">修改个人信息</a></li>
					</ul></li>
				<li role="presentation"><a href="<c:url value='/'/>" target="mainFrame">联系管理员</a>
				</li>
			    </c:when>
			    <c:otherwise>
			    <li role="presentation"><a href="<c:url value='/'/>" target="mainFrame">查看申请</a>
				</li>
				<li role="presentation"><a href="<c:url value='/user/getAllUser.action'/>" target="mainFrame">查看用户</a>

				</li>
				<li role="presentation"><a href="<c:url value='/'/>" target="mainFrame">我的消息</a>
				</li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> 个人设置<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/jsp/user/edit_password.jsp'/>" target="mainFrame">修改密码</a></li>
						<li><a href="<c:url value='/jsp/user/edit.jsp'/>" target="mainFrame">修改个人信息</a></li>
						<li><a href="<c:url value='#'/>" target="mainFrame">查看个人信息</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> 管理中心<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='#'/>" target="mainFrame">修改申请</a></li>
						<li><a href="<c:url value='#'/>" target="mainFrame">删除申请</a></li>
					</ul></li>
			    </c:otherwise>
			</c:choose>
			</ul>
		</div>
		<!-- 左侧导航和正文内容的分隔线 -->
		<div class="splitter"></div>
		<!-- 正文内容部分 -->
		<div class="pageContent">
			<iframe src="<c:url value='/jsp/welcome.jsp'/>" id="mainFrame" name="mainFrame"
				width="100%" height="100%" frameBorder="0"></iframe>
		</div>
	</div>
	<!-- 底部页脚部分 -->
	<div class="footer">
		<p class="text-center" style="color: gray">@泰山区自管会</p>
	</div>
</body>
</html>