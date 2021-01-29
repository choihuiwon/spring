<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").click(function(){
			var param = $("#frm").serialize();
			$.ajax({
				url : "register.do",
				data : param,
				method : "get",
				dataType:"json",
				success:function(d){
					var arr = d.result;
					var text = "";
					for(i=0; i<arr.length;i++){
						text += arr[i].eno + " " + arr[i].name + " " + arr[i].department + " " + arr[i].position + "<br>";
					}
					
					$("div").html(text);
				},
				error:function(xhr,text,error){
					switch(xhr.status){
					case 1001:
					case 1002:
					case 1003:
						alert(xhr.responseText + " " + text);
						break;
					}
//					$("div").html(xhr.status + " " + xhr.responseText + " " + text);
				}
			});
		});
	});
</script>
</head>
<body>
<form id="frm">
	사번 : <input type="text" name="eno"><br>
	이름 : <input type="text" name="name"><br>
	부서 : <input type="text" name="department"><br>
	직급 : <select name="position">
			<option value="1">사원</option>
			<option value="2">주임</option>
			<option value="3">대리</option>
			<option value="4">과장</option>
			<option value="5">차장</option>
			<option value="6">부장</option>
			<option value="7">사장</option>
		</select>
	<button type="button">등록</button>
</form>
<hr>
<div>

</div>
</body>
</html>