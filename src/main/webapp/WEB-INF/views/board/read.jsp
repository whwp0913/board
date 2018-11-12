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
		<h1>Board 상세 조회</h1>
	</div>
	
	<form id="del_form" action="/board/delete" method="post">
		<input type="hidden" name="id" value="${board.id}">
		<div class="form-group">
      		<label>TITLE</label>      		
   			<input class="form-control input-lg" value="${board.title }" readonly>      		
    	</div>
    	
    	<div class="form-group">
    		<label>CONTENT</label>
    		<textarea class="form-control" rows="10" readonly>${board.content}</textarea>
    	</div>
    	
    	<div class="form-group">    		
    		<label>WRITER</label>
     		<input type="text" class="form-control" value="${board.writer }" readonly>      	
  		</div>
  		
  		<div class="form-group">
  			
  		</div>
  		
  		<div class="form-group text-center">  			
  			<a class="btn btn-default" href="/board/list?page=${param.page }" role="button">LIST</a>
  			<a class="btn btn-default" href="/board/modify?page=${param.page}&id=${param.id}" role="button">MODIFY</a>
  			<button class="btn btn-default" id="del_btn" type="submit">DELETE</button>		
		</div>
	</form>
	
</div>
<script src="/resources/jquery.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/jquery.validate.min.js"></script>
<script type="text/javascript">
var message = '${message}';
if(message.length > 0) {
	alert(message);
}

$(document).ready(function(){
	
	$("#del_btn").one("click",function(e) {
		e.preventDefault();
		e.target.innerText = "OK";
		var groupBox = $(".form-group");
		var addInput = "<label>PASSWORD</label>"
			 	 	  +"<input type='password' class='form-control' name='password'>";
		groupBox[3].innerHTML += addInput;
	});
	
	$("#del_form").validate({
		debug: false,
		onfocusout: false,
		rules: {
			password: {
				required: true,
				
			}
		}, messages: {
			password: "비밀번호를 입력해주세요."
		}
	});
	
});
</script>
</body>
</html>