<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap-theme.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="container">

	<div class="page-header">
		<h1>Board 목록 조회</h1>
	</div>

	<table class="table">
		<tr>
			<td>제목</td>
			<td>작성자</td>
			<td>작성날짜</td>	
		</tr>
		<c:forEach items="${list }" var="board">
			<tr>
				<td><a href="/board/read?page=${empty param.page ? 1 : param.page }&id=${board.id }">${board.title }</a></td>
				<td>${board.writer }</td>
				<td>${board.sysdate }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="right">
				<a class="btn btn-default" href="/board/write" role="button">등록</a>
			</td>
		</tr>
	</table>
	
	<%@include file="/WEB-INF/views/board/paging.jsp"%>
	

	
</div>

<script src="/resources/jquery.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/jquery.validate.min.js"></script>
<script type="text/javascript">
var message = '${message}';
if(message.length > 0) {
	alert(message);
}
</script>
</body>
</html>