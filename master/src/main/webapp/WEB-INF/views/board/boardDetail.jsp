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
	.container {display: flex; flex-direction: column; height: 90vh; margin-top: 7vh; align-items: center;}
	.write-form {width: 600px; margin-top: 20px;}
	#contents {resize: none; width: 500px; height: 100px; border-radius: 7px;}
	.table th {text-align: right; padding: 15px 10px;}
	.table td {padding: 15px 10px;}
	.file-list {list-style-type: none; margin: 0; padding: 0;}
	ul {list-style: none; padding: 0; margin: 0;}
	.file-list a {text-decoration: none; color: black;}
	.file-list a:hover {color: #ddd;}
	.file-list a:visited {color:#000;}
	.form-label {margin: 0;}
</style>
</head>
<body>
	<%@ include file="../header/header.jsp" %>
	<main class="container">
		<header class="text-center">
			<h2>게시글 보기</h2>
		</header>
		<section class="write-form">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}" />
			<input type="hidden" id="brdId" name="brdId" value="${vo.brdId}" />
			<table class="table">
				<colgroup>
					<col width="15%" />
					<col width="55%" />
					<col width="15%" />
					<col width="15%" />
				</colgroup>
				<tbody>
					<tr>
						<th><label class="form-label">제목</label></th>
						<td colspan="3">
							${vo.title}
						</td>
					</tr>
					<tr>
						<th><label class="form-label">글쓴이</label></th>
						<td colspan="3">
							${vo.writer}
						</td>
					</tr>					
					<tr>
						<th><label class="form-label">조회수</label></th>
						<td>
							${vo.readCount}
						</td>
						<th><label class="form-label">좋아요</label></th>
						<td>
							<span id="likeCount">${vo.likeCount}</span>
						</td>						
					</tr>					
					<tr>
						<th><label class="form-label">첨부파일</label></th>
						<td colspan="3">
							<c:if test="${vo.files.size() == 0}">없음</c:if>
							<c:if test="${vo.files.size() > 0}">
								<ul>
									<c:forEach items="${vo.files}" var="item">
										<li class="file-item">
											<a class="file-link" href="/board/down.do?bfId=${item.bfId}">${item.fileName}</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
					</tr>
					<tr>
						<th><label class="form-label">내용</label></th>
						<td colspan="3">
							${vo.contents}
						</td>
					</tr>					
				</tbody>
			</table>
		</section>
		<section class="text-center pb-4">
			<button type="button" class="btn btn-warning me-2" onclick="updateLike();">좋아요</button>
		</section>
		<section class="text-center">
			<c:if test="${user.userId eq vo.writer}">
				<button type="button" class="btn btn-primary me-2" onclick="writeBoard();">수정</button>
				<button type="button" class="btn btn-danger me-2" onclick="deleteBoard();">삭제</button>
			</c:if>
			<button type="button" class="btn btn-secondary" onclick="goList()">목록</button>
		</section>
	</main>
</body>
<script type="text/javascript">
	const goList = () => {
		const currentPage = $('#currentPage').val();
		location.href = "/board/list2.do?currentPage=" + currentPage; 
	}
	
	const updateLike = () => {
		const likeCount = parseInt($('#likeCount').text());
		const updateCount = likeCount + 1;		
		
		const dataParam = {
				likeCount : updateCount,
				brdId : $('#brdId').val()
			};
		// get, post일 떄는 contentType, data를 JSON.stringify없이 그냥 보낼 수 있지만, put patch는 아래 처럼 해줘야함
		$.ajax({
			url: '/board/like.do',
			type: 'patch',
			dataType: 'json',
			contentType: 'application/json', // 기본은 application/x-form-urlencoded
			data: JSON.stringify(dataParam) // 자바스크립트 객체를 json 형식으로 변환
		}).done(function(res) {
			$('#likeCount').text(updateCount);
		}).fail(function(xhr, status, error) {
			if(xhr.status === 500) {
				const res = JSON.parse(xhr.responseText); // JSON.parse >> json객체를 자바스크립트 객체로 변환
				alert(res.msg[0]);
			}
		});
	}
	
	
	const writeBoard = () => {
		location.href="/board/write/view.do?brdId=" + $('#brdId').val();
	}
	
	const deleteBoard = () => {
		const isConfirm = confirm("정말 삭제하시겠습니까?");
		
		if(isConfirm) {
			location.href="/board/delete.do?brdId=" + $('#brdId').val();
		}
	}

</script>

</html>