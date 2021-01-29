<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	*{
		padding:0;
		margin:0;
	}
	nav{
		width: 1400px;
		margin: 0 auto;
		border: 1px solid black;
		text-align: center;
	}
	nav ul{
		list-style: none;
	}
	nav li {
		display: inline-block;
		margin: 15px 20px;
	}
	nav a:link, nav a:visited{
		text-decoration: none;
		color: black;
		font-size: 28px;
	}
	nav a:hover {
		font-weight: bold;
	}
	
	#main{
		width: 100%;
	}
	#status{
		text-align: right;
	}
	#logout{
		font-size:18px;
	}
	#update{
		font-size:18px;
	}
	#grade_img{
		width:60px;
		height: 60px;
		margin-bottom: -30px;
	}
	
</style>

<nav>
	<ul>
		<li><a href="/">HOME</a>
		<li><a href="qnaView.do">문의하기</a>
		<li><a href="boardListView.do">게시판</a>
<c:choose>
<c:when test="${sessionScope.dto eq null }">
		<li><a href="login.do">로그인</a></li>
		<li><a href="member/register.jsp">회원가입</a></li>
</c:when>
<c:otherwise>
		<c:if test="${sessionScope.dto.id eq 'admin' }">
			<li><a href="adminManageMember.do">회원관리</a></li>
		</c:if>
		<li id="status"><img id="grade_img" src="img/grade_icon/${sessionScope.dto.grade }.png">${sessionScope.dto.name }님 로그인 하셨습니다.<br><a href="logout.do" id="logout">로그아웃</a> | <a href="update_view.do" id="update">정보수정</a></li>
</c:otherwise>
</c:choose>
	</ul>		
</nav>