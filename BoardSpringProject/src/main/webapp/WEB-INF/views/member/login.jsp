<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../template/header.jsp" flush="false"/>
	<form action="loginAction.do">
		<input type="text" name="id" placeholder="아이디"><br>
		<input type="password" name="pass" placeholder="암호"><br>
		<button>로그인</button>	
	</form>
	<jsp:include page="../template/footer.jsp" flush="false"/>
</body>
</html>