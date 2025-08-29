<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/common/common.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container {display: flex; flex-direction: column; margin-top: 7vh; align-items: center;}
	.write-form {width: 600px; margin-top: 20px;}
	#contents {resize: none; width: 500px; height: 100px; border-radius: 7px;}
</style>
</head>
<body>
	<%@ include file="../header/header.jsp" %>
	<main class="container">
		<header class="text-center">
			<h2>게시글 쓰기</h2>
		</header>
		<section class="write-form">
		<!-- 
			enctype는 기본 타입이 application/x-www-form-urlencoded
			get방식처럼 a=3&name=철수
			
			multipart/form-data : 첨부파일이 있는 방식 > file 객체가 있을 때 동작
									해당 타입은 post일 때만 가능
		 -->
			<form id="frm" action="/board/write.do" method="POST" enctype="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<th><label for="title" class="form-label pt-2">제목</label></th>
							<td>
								<input type="text" id="title" name="title" class="form-control" />
							</td>
						</tr>
						<tr>
							<th><label for="file" class="form-label pt-2">첨부파일</label></th>
							<td>
								<input type="file" id="file" name="file" class="form-control" />
							</td>
						</tr>
						<tr>
							<th><label for="contents" class="form-label pt-2">내용</label></th>
							<td>
								<textarea id="contents" name="contents"></textarea>
							</td>
						</tr>					
					</tbody>
				</table>
			</form>
		</section>
		<section class="text-center">
			<button type="button" class="btn btn-primary me-2" onclick="writeBoard();">글쓰기</button>
			<button type="button" class="btn btn-secondary" onclick="goList()">취소</button>
		</section>
	</main>
</body>
<script>

	function validated() {
		
		const title = document.querySelector('#title');
		const contents = document.querySelector('#contents');
		
		if(title.value.trim().length === 0) {
			alert('제목을 입력하세요.');
			title.focus();
			return false;
		}
		
		if(contents.value.trim().length === 0) {
			alert('내용을 입력하세요.');
			contents.focus();
			return false;
		}
		
		const file = document.querySelector('#file');
		// 파일 선택했을 경우
		if(file.files.length > 0) {
			const maxSize = 50 * 1024 * 1024;
			
			if(file.files[0].size > maxSize) {
				alert('업로드 최대 용량은 50MB를 초과할 수 없습니다');
				return false;
			}
		}
		
		return true;
		
	}

	const writeBoard = () => {
		// 유효성 통과 후 form 전송
		if(validated()) {
			$('#frm').submit();
		}
	}
	
	const goList = () => {
		location.href = "/board/list2.do";
	}
</script>

</html>