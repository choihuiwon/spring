<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<h1>회원 로그인</h1>
<form action="loginAction.do" method="post">
	아이디 : <input type="text" name="id" placeholder="아이디를 입력하세요."><br>
	암호  : <input type="password" name="pass" placeholder="암호를 입력하세요."><br>
	<button>로그인</button>
</form>
<hr>
<h1>사원 로그인</h1>
<form action="employeeLoginAction.do" method="post">
	사번 : <input type="text" name="eno" placeholder="사번을 입력하세요."><br>
	이름  : <input type="text" name="name" placeholder="이름을 입력하세요."><br>
	<button>로그인</button>
</form>
</body>
</html>