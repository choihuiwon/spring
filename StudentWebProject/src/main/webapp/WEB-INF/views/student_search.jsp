<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		margin:0;
		padding:0;
		text-align: center;
	}
	table{
		width: 400px;
		margin: 20px auto;
	}
	td{
		width: 200px;
	}
</style>
<script src="/lib/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		// 리셋
		$(".btn_reset").click(function(){
			location.href="/";
		});
		
		// 검색
		$(".btn_search").click(function(e){
			var data = $("#frm").serialize();
			$.ajax({
				url : "studentSearch.do",
				data : data,
				method : 'get',
				success : function(d){
					var json = JSON.parse(d);
					var text = "<table>";
					var arr = json.result;
					if(arr.length > 0){
						for(i=0; i<arr.length; i++){
							text += "<tr><td>" + arr[i].sno + "</td>"
								  + "<td>" + arr[i].name + "</td>"
								  + "<td>" + arr[i].major + "</td>"
								  + "<td>" + arr[i].score + "</td></tr>";
						}
						text += "</table>";
						
						$("#content_area").html(text);
					}
					else{
						$("#content_area").html("<p>조회된 데이터가 없습니다.</p>");
					}
					
					
				}
			});
			e.preventDefault();	
		});
	});
</script>
</head>
<body>
<form id="frm">
	<select name="kind">
		<option value="sno">학번</option>
		<option value="name">이름</option>
		<option value="major">학과</option>
	</select>
	<input type="text" name="search">
	<button class="btn_search">검색</button>
	<input type="button" class="btn_reset" value="리셋">
</form>
<hr>
<div id="content_area">
	<table>
		<c:forEach var="dto" items="${requestScope.list }"> 
			<tr>
				<td>${dto.sno }</td>
				<td>${dto.name }</td>
				<td>${dto.major }</td>
				<td>${dto.score }</td>
			</tr>	
		</c:forEach>
	</table>
</div>
</body>
</html>