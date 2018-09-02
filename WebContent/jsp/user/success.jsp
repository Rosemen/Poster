<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
<script type="text/javascript">
   if(confirm("注册成功,是否前往登录?")){
	   window.location = "<c:url value='/jsp/user/login.jsp'/>";
   }else {
	   window.location = "<c:url value='/jsp/user/regist.jsp'/>";
   }
</script>
</head>
<body>
    
</body>
</html>