<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Update View</title>
<style>
	#main{
		height: 400px;
	}
	#register_area{
		width:400px;
		margin: 0 auto;
		margin-top:200px;
		text-align: center;
	}
	table, td{
		border-collapse: collapse;
		font-size: 22px;
		font-size: 22px;
		text-align: left;
		padding: 5px;
	}
	input, button{
		font-size: 22px;
	}
</style>
</head>
<body>
	<jsp:include page="../template/header.jsp" flush="false"/>
	<div id="main">
		<div id="register_area">
			<form action="memberUpdateAction.do" method="post">
			<table>
				<tr>
					<td><label for="id">아이디</label></td>
					<td><input type="text" name="id" id="id" value="${requestScope.dto.id }" readonly></td>
				</tr>
				<tr>
					<td><label for="passwd">암호</label></td>
					<td><input type="password" name="passwd" id="passwd"></td>
				</tr>
				<tr>
					<td><label for="name">이름</label></td>
					<td><input type="text" name="name" id="name" value="${requestScope.dto.name }"></td>
				</tr>
				<tr>
					<td><label for="age">나이</label></td>
					<td><input type="text" name="age" id="age" value="${requestScope.dto.age }"></td>
				</tr>
				<tr>
					<td><button type="submit">수정</button></td>
					<td style="text-align: right;"><button type="button" onclick="history.back()">뒤로가기</button></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	<jsp:include page="../template/footer.jsp" flush="false"/>
</body>
</html>