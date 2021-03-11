<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>번역 페이지</title>
<style type="text/css">
	*{
		margin:0;
		padding : 0;
		font-size: 18px;
	}
	section{
		width:800px;
		position:relative;
		margin:0 auto;
		text-align: center;
	}
	article{
		width:400px;
		padding : 10px 60px;
		box-sizing: border-box;
		float:left;
	}
	select{
		padding:15px 30px;
	}
	article:first-child{
		border-right: 2px solid #a8a8a8;
	}
	h1{
		font-size: 24px;
		padding:20px 0px;
		text-align: center;
	}
	textarea {
		width:100%;
		height:500px;
		padding:10px;
		box-sizing:border-box;
		resize: none;
	}
	#btn_translate{
		width: 50px;
		height: 50px;
		background-color: black;
		font-weight: bold;
		color:white;
		border-radius: 50px;
		position: absolute;
		left: 375px;
		top: 300px;
		outline: none;
		border:none;
	}
	
</style>
<script src="/lib/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$("#btn_translate").click(function(){
			if($("#target").val() == $("#source").val())
				$("#result").val($("#text").val());
			else{
				var param = "";
				param += "target="+$("#target").val();
				param += "&source="+$("#source").val();
				param += "&text="+$("#text").val();
				$.ajax({
					url : "translate.do",
					data : param,
					method : "get",
					success: function(d){
						var json = JSON.parse(d);
						var responseCode = json.responseCode;						
						var text = json.resultText;
						if(responsecode == 200)
							$("#result").val(text);
						else		
							$("#result").val("번역 오류가 생겼습니다.");
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<h1>파파고 번역 페이지</h1>
	<section>
		<article>
			<select name="source" id="source">
				<option value="ko">한국어</option>
				<option value="en">영어</option>
				<option value="de">독일어</option>
				<option value="ja">일본어</option>
				<option value="zh-CN">중국어</option>
			</select>
			<h1>번역할 내용</h1>
			<textarea name="text" id="text"></textarea>
		</article>
		<article>
			<select name="target" id="target">
				<option value="ko">한국어</option>
				<option value="en">영어</option>
				<option value="de">독일어</option>
				<option value="ja">일본어</option>
				<option value="zh-CN">중국어</option>
			</select>
			<h1>번역된 내용</h1>
			<textarea name="result" id="result"></textarea>
		</article>
		<button id="btn_translate">>></button>
	</section>
</body>
</html>