<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>记录列表</title>
<script type="text/javascript">
  function changePage(page_num){
	  $("#currentPage").val(page_num);
	  $("#page").submit();
  }
</script>
</head>
<body>
<table class="table table-hover table-striped" style="table-layout: fixed;">
	<caption><a href="<c:url value='/jsp/welcome.jsp'/>"><span style="color: blue;text-decoration: underline;">返回</span></a></caption>
	<thead>
	    <tr>
	    <th colspan="2">
	      <form action="#">
                <input style="height: 30px" type="text" name="record_createtime" placeholder="根据日期查询">
                <input  type="button" value="搜索" class="btn btn-success btn-sm">
          </form>
          </th>
        </tr>
		<tr>
			<th>用户名</th>
			<th>邮箱</th>
			<th>手机号码</th>
			<th>组织/社团</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${pageBean.records }" var="myUser">
		<tr class="active">
			<td class="col-sm-5" style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${myUser.user_name }</td>
			<td>${myUser.user_email }</td>
			<td>${myUser.user_phone }</td>
			<td>
			   ${myUser.user_org }
			</td>
			<td>
			   <a href="<c:url value='/poster/getAllRecords.action'/>?userExtend.user_id=${myUser.user_id }">他的申请</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="navbar-fixed-bottom">
<ul class="pagination">
    <li><a href="javascript:void(0)" onclick="changePage('${pageBean.current_page-1}')">上一页</a></li>
    <c:choose>  
    <c:when test="${pageBean.total_page <= 5 }">  
        <c:set var="begin" value="1"></c:set>  
        <c:set var="end" value="${pageBean.total_page }"/>  
    </c:when>  
    <c:otherwise>  
        <c:set var="begin" value="${pageBean.current_page-2 }"/>  
        <c:set var="end" value="${pageBean.current_page + 2}"/>  
        <c:if test="${begin < 1 }">  
          <c:set var="begin" value="1"/>  
          <c:set var="end" value="5"/>  
        </c:if>  
        <c:if test="${end > pageBean.total_page }">  
          <c:set var="begin" value="${pb.tp-4 }"/>  
          <c:set var="end" value="${pageBean.total_page }"/>  
        </c:if>         
    </c:otherwise>  
 </c:choose>
    <c:forEach begin="${begin }" end="${end }" step="1" var="i">
    <li class="<c:if test='${pageBean.current_page eq i }'>active</c:if>">
    <a href="javascript:void(0)" onclick="changePage('${i}')">${i }</a>
    </li>
    </c:forEach>
    <li><a href="javascript:void(0)" onclick="changePage('${pageBean.current_page+1}')">下一页</a></li>
    <li><a>共<span style="color:red">${pageBean.total_record }</span>条</a></li>
    <li><a>共<span style="color:red">${pageBean.total_page }</span>页</a></li>
</ul>
</div>
</body>
</html>