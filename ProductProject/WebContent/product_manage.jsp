<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		margin:0;
		padding:0;
	}
	body{
		text-align: center;
		width:1400px;
		margin: 0 auto;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").click(function(){
			var param = $("#frm").serialize();
			$.ajax({
				url : "input.do",
				data : param,
				method : "get",
				dataType:"json",
				success:function(d){
					alert("여기까진");
					var arr = d.result;
					var text = "";
					for(i=0; i<arr.length;i++){
						text += arr[i].product_no + " " + arr[i].product_name + " " + arr[i].price + " " + arr[i].ea + " " + arr[i].maker + "<br>";
					}
					
					$("div").html(text);
				},
				error:function(xhr,text,error){
					switch(xhr.status){
					case 1001:
					case 1002:
					case 1003:
						alert(xhr.responseText);
						break;
					}
				}
			});
		});
	});
</script>
</head>
<body>
<form id="frm">
	<input type="text" name="product_no" placeholder="상품번호 입력">
	<input type="text" name="product_name" placeholder="상품명 입력">
	<input type="text" name="maker" placeholder="제조사 입력">
	<input type="text" name="price" placeholder="금액 입력">
	<input type="text" name="ea" placeholder="재고 개수 입력">
	<button type="button">상품등록</button>
</form>
<hr>
<div>

</div>
</body>
</html>