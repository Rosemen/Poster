<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作失败页面</title>
<script type="text/javascript">
window.onload = function(){
	alert("您还没有登录!");
	window.open("<c:url value='/jsp/user/login.jsp'/>", "_parent");
}
</script>
</head>
<body>
  
</body>
</html>