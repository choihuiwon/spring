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
	.search_box{
		width:1200px;
		margin: 30px auto;
		text-align: center;
	}
	.text{
		width: 500px;
		heigth: 38px;
		font-size: 28px;
		border: 1px solid gray;
		border-radius: 4px;
	}
	.btn_search{
		idth: 100px;
		heigth: 32px;
		font-size: 28px;
		border: 1px solid gray;
		border-radius: 4px;
	}
	.container{
		width: 1000px;
		margin: 30px auto;
	}
	.item{
		width: 1000px;
		height: 250px;
	}
	.image{
		width: 100px;
		height: 150px;
		float:left;
		border : 0.5px solid gray;
	}
	.info{
		margin-left: 130px;
	}
	.info a{
		text-decoration: none;
		color: black;
	}
	.info a:hover{
		text-decoration: underline;
	}
</style>
<script src="/lib/jquery-3.5.1.min.js"></script>
<script>
var start = 1;
var more = 1;
var display = 10;
var flag_search = false;	// 검색 누른 경우 true

function btn_search(){
	if(flag_search){
		more = 1;
		display = 10;
	}
	var data = "text=" + $("#text").val() + "&start="+start+"&display="+display;
	$.ajax({
		url : "search.do",
		data : data,
		method: 'get',
		success : function(result){
			var html = "";
			var d = JSON.parse(result);
			if(d.responseCode == "200"){
				// 데이터 읽어서 화면 출력
				var arr = d.items;
				for(i=0; i<arr.length; i++){
					html += "<div class='item'>"
						  + "<img class='image' src='"+arr[i].image+"'>"
						  + "<div class='info'>"
						  + "<a href='"+arr[i].link+"'>"+arr[i].title+"</a><br>"
						  + "저자 : "+arr[i].author+", 출판사 : "+arr[i].publisher+"<br>"
						  +arr[i].description+"</div></div>";
				}
			}else{
				// 실패한 메세지 출력
				html += "에러 코드 : " + d.responsecode + ", 에러 메세지 : " + d.responseMessage;
			}
			$(".container").html(html);
		}
	});	// ajax
}

function btn_more(){
	if(more<100){
		more++;
		display = 10*more;
	}
	btn_search();
}

$(function(){
	$(".btn_search").click(function(){
		flag_search = true;
		btn_search();
	});
	
	$(".btn_more").click(function(){
		flag_search = false;
		btn_more();		
	});
});
</script>
</head>
<body>
	<div class="search_box">
		<form id="frm">
			<input type="text" name="text" id="text" class="text" placeholder="도서명을 입력하세요.">
			<input type="button" class="btn_search" value="검색">
		</form>
	</div>	
	<hr>
	<div class="container">
		<!-- 반복 구간 -->
		<div class="item">
			<img class="image" src="../img/book1.jpg">
			<div class="info">
				<a href="#">책제목</a><br>
				저자 : 000, 출판사 : 00000<br>
				내용 요약
			</div>
		</div>
		<div class="item">
			<img class="image" src="../img/book1.jpg">
			<div class="info">
				<a href="#">책제목</a><br>
				저자 : 000, 출판사 : 00000<br>
				내용 요약
			</div>
		</div>
	</div>
	<button class="btn_more">더보기</button>
</body>
</html>