<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$(".btn_update").click(function(){
			$(this).parent().find("input[name='command']").val("update");
		});
		$(".btn_delete").click(function(){
			$(this).parent().find("input[name='command']").val("delete");
		});
	});
</script>
<style type="text/css">
	form{
	text-align: center;
	}
</style>
</head>
<body>
	<form id="input_frm" action="register.do" method="post">
		<input type="text" name="eno" placeholder="사번">
		<input type="text" name="name" placeholder="이름">
		<input type="text" name="department" placeholder="부서">
		<select name="position">
			<option value="1">사원</option>
			<option value="2">주임</option>
			<option value="3">대리</option>
			<option value="4">과장</option>
			<option value="5">차장</option>
			<option value="6">부장</option>
			<option value="7">사장</option>
		</select>
		<button>등록</button>
	</form>
	<hr>
	<c:forEach var="dto" items="${requestScope.list }"> 
	<form class="data_frm" action="dataUpdate.do">
		<p>
			<input type="text" name="eno" placeholder="사번" value="${dto.eno }" readonly>
			<input type="text" name="name" placeholder="이름" value="${dto.name }">
			<input type="text" name="department" placeholder="부서" value="${dto.department }">
			<select name="position">
				<option value="1" <c:if test="${dto.position == 1 }">selected</c:if>>사원</option>
				<option value="2" <c:if test="${dto.position == 2 }">selected</c:if>>주임</option>
				<option value="3" <c:if test="${dto.position == 3 }">selected</c:if>>대리</option>
				<option value="4" <c:if test="${dto.position == 4 }">selected</c:if>>과장</option>
				<option value="5" <c:if test="${dto.position == 5 }">selected</c:if>>차장</option>
				<option value="6" <c:if test="${dto.position == 6 }">selected</c:if>>부장</option>
				<option value="7" <c:if test="${dto.position == 7 }">selected</c:if>>사장</option>
			</select>
			<input type="hidden" name="command" id="command" value="">
			<button class="btn_update">수정</button>
			<button class="btn_delete">삭제</button>
		</p>	
	</form>
	</c:forEach>
</body>
</html>