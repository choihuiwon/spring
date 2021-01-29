<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN PAGE</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#goCalendar").click(function(){
			$.ajax({
				url : "ajax.do",
				method :"get",
				success:function(d){
					$("div").html(d);
				}
			});
		});
	});
</script>
</head>
<body>
	첫번째 페이지
	
<c:choose>
	<c:when test="${sessionScope.id ne null }">
		${sessionScope.id } 님 로그인
	</c:when>
	<c:otherwise>
		<form action="login.do">
			<button>로그인</button>
		</form>
	</c:otherwise>
</c:choose>
<button id="goCalendar">날짜보기</button>
<div>

</div>
</body>
</html>