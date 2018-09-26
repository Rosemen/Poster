<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>注册成功</title>
<script type="text/javascript">
function changePage(page_num){
	  $("#currentPage").val(page_num);
	  $("#page").attr("action","<c:url value='/poster/getAllMessages.action'/>");
	  $("#page").submit();
}

function setMsgStatus(msg_id){
	$("#msgId").val(msg_id);
	$("#msg_status").attr("action","<c:url value='/poster/updateMsgStatus.action'/>");
	$("#msg_status").submit();
}
</script>
</head>
<body>
	<table class="table table-hover table-striped"
		style="table-layout: fixed;">
		<caption>
			<a href="<c:url value='/jsp/welcome.jsp'/>"> <span
				style="color: blue; text-decoration: underline;">返回</span>
			</a>
		</caption>
		<thead>
			<tr>
				<th>消息内容</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.records }" var="msg">
				<tr class="active">
					<td class="col-sm-5"
						style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
						${msg.msg_content }</td>
					<td><input type="button" class="btn btn-info btn-xs"
						onclick="setMsgStatus('${msg.msg_id}')" value="标记为已读"></td>
				</tr>
			</c:forEach>
			<tr style="background-color: white; border-bottom: 0px;">
				<td>
				   <form action="" id="page">
						<input type="hidden" id="currentPage" name="current_page" value="">
					</form>
				</td>
				<td>
				   <form action="" id="msg_status" method="post">
						<input type="hidden" id="msgId" name="msg_id" value="">
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="navbar-fixed-bottom">
		<ul class="pagination">
			<li><a href="javascript:void(0)"
				onclick="changePage('${pageBean.current_page-1}')">上一页</a></li>
			<c:choose>
				<c:when test="${pageBean.total_page <= 5 }">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${pageBean.total_page }" />
				</c:when>
				<c:otherwise>
					<c:set var="begin" value="${pageBean.current_page-2 }" />
					<c:set var="end" value="${pageBean.current_page + 2}" />
					<c:if test="${begin < 1 }">
						<c:set var="begin" value="1" />
						<c:set var="end" value="5" />
					</c:if>
					<c:if test="${end > pageBean.total_page }">
						<c:set var="begin" value="${pb.tp-4 }" />
						<c:set var="end" value="${pageBean.total_page }" />
					</c:if>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${begin }" end="${end }" step="1" var="i">
				<li
					class="<c:if test='${pageBean.current_page eq i }'>active</c:if>">
					<a href="javascript:void(0)" onclick="changePage('${i}')">${i }</a>
				</li>
			</c:forEach>
			<li><a href="javascript:void(0)"
				onclick="changePage('${pageBean.current_page+1}')">下一页</a></li>
			<li><a>共<span style="color: red">${pageBean.total_record }</span>条
			</a></li>
			<li><a>共<span style="color: red">${pageBean.total_page }</span>页
			</a></li>
		</ul>
	</div>
</body>
</html>