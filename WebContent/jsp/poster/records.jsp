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
	  <c:choose>
	   <c:when test="${not empty current_userId }">
	   $("#page").attr("action","<c:url value='/poster/getAllRecords.action'/>");
	   </c:when>
	   <c:otherwise>
	   $("#page").attr("action","<c:url value='/poster/getAll.action'/>");
	   </c:otherwise>
	</c:choose>
	  $("#page").submit();
  }
</script>
</head>
<body>
<table class="table table-hover table-striped" style="table-layout: fixed;">
	<caption>
	<c:if test="${empty user or user.user_type eq 0 }">
	<a href="<c:url value='/jsp/welcome.jsp'/>">
	<span style="color: blue;text-decoration: underline;">返回</span>
	</a>
	</c:if>
	<c:if test="${user.user_type eq 1 }">
	<a href="<c:url value='/user/getAllUser.action'/>">
	<span style="color: blue;text-decoration: underline;">返回</span>
	</a>
	</c:if>
	</caption>
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
			<th>海报内容</th>
			<th>申请人</th>
			<th>申请单位</th>
			<th>申请时间</th>
			<th>申请状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${pageBean.records }" var="record">
		<tr class="active">
			<td class="col-sm-5" style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${record.record_poster.poster_content }</td>
			<td>${record.record_poster.poster_user }</td>
			<td>${record.record_poster.poster_org }</td>
			<td>
			<fmt:formatDate value="${record.record_createtime }" 
			pattern="yyyy-MM-dd"/>
			</td>
			<td>
			 <c:choose>
			    <c:when test="${record.record_status eq 0 }"><span style="color:blue"><b>待处理</b></span></c:when>
			    <c:otherwise>
			        <c:if test="${record.record_result eq 0 }"><span style="color:red"><b>未通过</b></span></c:if>
			        <c:if test="${record.record_result eq 1 }"><span style="color:green"><b>通过</b></span></c:if>
			    </c:otherwise>
			 </c:choose>
			</td>
			<td>
			<form action="<c:url value='/jsp/poster/show.jsp'/>" style="padding:0px;margin:0px;" method="post">
			    <input type="hidden" name="user_id" value="${current_userId }">
			    <input type="hidden" name="record_user" value="${record.record_user.user_id }">
			    <input type="hidden" name="poster_user" value="${record.record_poster.poster_user }">
			    <input type="hidden" name="poster_org" value="${record.record_poster.poster_org }">
			    <input type="hidden" name="poster_phone" value="${record.record_poster.poster_phone }">
			    <input type="hidden" name="poster_location" value="${record.record_poster.poster_location }">
			    <input type="hidden" name="poster_time" value="${record.record_poster.poster_time }">
			    <input type="hidden" name="poster_support" value="${record.record_poster.poster_support }">
			    <input type="hidden" name="poster_content" value="${record.record_poster.poster_content }">
			    <input type="hidden" name="poster_pic" value="${record.record_poster.poster_pic }">
			    <input type="hidden" name="poster_anotherpic" value="${record.record_poster.poster_anotherpic }">
			    <input type="hidden" name="record_id" value="${record.record_id }">
			    <input type="hidden" name="record_status" value="${record.record_status }">
			    <c:if test="${user.user_type eq 0 }">
			    <input type="button" class="btn btn-info btn-xs" onclick="this.form.submit()" value="查看详情">
			    </c:if>
			    <c:if test="${user.user_type eq 1 }">
			    <input type="button" class="btn btn-info btn-xs" onclick="this.form.submit()" value="查看并审批">
			    </c:if>
			</form>
			</td>
		</tr>
	</c:forEach>
<tr style="background-color:white;border-bottom: 0px;">
<td><form action="" id="page">
     <input type="hidden" name="userExtend.user_id" value="${current_userId }">
     <input type="hidden" id="currentPage" name="current_page" value="">
   </form></td>
</tr>
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