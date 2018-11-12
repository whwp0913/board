<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css" >
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap-theme.css" >
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="page-header">
		<h1>Board 글 등록</h1>
	</div>
	
	<form id="wrt_form" action="/board/write" method="post">

		<div class="form-group">
      		<label>TITLE</label>      		
   			<input class="form-control input-lg" name="title" value="<c:out value=''/>">      		
    	</div>
    	
    	<div class="form-group">
    		<label>CONTENT</label>
    		<textarea class="form-control" rows="10" name="content"><c:out value=''/></textarea>
    	</div>
    	
    	<div class="form-group">    		
    		<label>WRITER</label>
     		<input type="text" class="form-control" name="writer" value="<c:out value=''/>">      	
  		</div>
  		
  		<div class="form-group">    		
    		<label>PASSWORD</label>
     		<input type="password" class="form-control" name="password">      	
  		</div>
  		
  		<div class="form-group text-center">
  			<button class="btn btn-default" id="wrt_btn" type="submit">OK</button>  			
  			<a class="btn btn-default" href="/board/list" role="button">CANCLE</a>  					
		</div>
	</form>
	
</div>
<script src="/resources/jquery.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/jquery.validate.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$.validator.addMethod("blankCheck", function(value, element){
		return (value.substring(0, 1) == " ") ? false : true;
	});
	
	$("#wrt_form").validate({
		debug: false,
		onfocusout: false,
		rules: {
			title: {
				required: true,
				minlength: 2,
				blankCheck: true
			}, writer: {
				required: true,
				minlength: 2,
				blankCheck: true
			},password: {
				required: true
			}
		}, messages: {
			title: {
				required: "제목을 입력해주세요.",
				minlength: $.validator.format("제목은 최소 {0} 글자 이상 입력해주세요."),
				blankCheck: "공백으로 시작할 수 없습니다."
			}, writer: {
				required: "작성자를 입력해주세요.",
				minlength: $.validator.format("작성자는 최소 {0} 글자 이상 입력해주세요."),
				blankCheck: "공백으로 시작할 수 없습니다."
			}, password: {
				required: "패스워드를 입력해주세요."
			}
		}
	});	
});
</script>
</body>
</html>