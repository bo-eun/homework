<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/common/common.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/5.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/userList.css">
</head>
<body>
	<%@ include file="../header/header.jsp" %>
	<main class="container">
	
		<section class="contents">
			<header class="text-center">
				<h2>사용자 리스트</h2>
			</header>
			
			<section class="sch">
				<section>
					<select class="form-select" id="schType" name="schType">
						<option value="userId" selected>아이디</option>
						<option value="userName">이름</option>
					</select>				
				</section>
				
				<div class="sch-input">
					<input type="text" id="schText" name="schText" class="form-control" onkeydown="searchEnter(event)" />
				</div>
				<button type="button" class="btn btn-success" onclick="searchEvt()">검색</button>
				<button type="button" class="btn btn-primary" onclick="addUserEvt()">등록</button>
			</section>
			
		</section>

		<section class="data-list">
			<input type="hidden" id="currentPage" name="currentPage" value="${data.currentPage}" />
			<table class="table">
				<colgroup>
					<col width="20%">
					<col width="20%">
					<col width="10%">
					<col width="25%">
					<col width="25%">
				</colgroup>
				<thead class="table-dark text-center">
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>생년월일</th>
						<th>연락처</th>
						<th>이메일</th>
					</tr>
				</thead>
				<tbody id="boardBody">
				</tbody>
			</table>
		</section>
		
		<section class="page">
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center" id="page">
			  </ul>
			</nav>				
		</section>		
	</main>
	
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/5.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">

// 서버에 데이터 요청
function getBoardData() {
	// 비동기로 서버에 데이터 요청
	$.ajax({
		url: '/user/list/data.do', //요청 경로
		type: 'get', // 전송 방식
		dataType: 'json', // 돌려받을 데이터 타입
		data: {
			currentPage : $('#currentPage').val()
		}
	}).done(function(res) {
		console.log(res);
		drawTable(res);
	}).fail(function(xhr, status, error) {
		alert(status);
	});
}

const addUserEvt = () => {
	location.href="/user/register.do";
}

const searchEvt = () => {
	const type = document.querySelector('#schType').value;
	const schText = document.querySelector('#schText').value;
	
	if(schText.trim().length === 0) {
		alert('검색할 내용을 작성해주세요.');
		return false;
	}
	searchUser(type, schText);
}

function searchUser(type, searchText) {
	$.ajax({
		url: '/user/search.do',
		type: 'get',
		dataType: 'json',
		data: {
			type,
			searchText
		}
	}).done((res) => {
		drawTable(res);
	}).fail((xhr, status, error) => {
		alert('검색 실패');
	})
}

function drawTable(data) {
	
	const tbody = $('#boardBody');
	tbody.empty();
	
	// 테이블 만들기
	$.each(data.userList, function(index, obj) {
		const tr = $('<tr></tr>');
		const link = $('<a href="javascript:void(0);"></a>');
		
		tr.append(makeTD('', obj.userId));
		tr.append(makeTD('', link.append(obj.userName)));
		tr.append(makeTD('', obj.birth));
		tr.append(makeTD('', obj.phone));
		tr.append(makeTD('', obj.email));
		
		tbody.append(tr);
	});
	
	const page = $('#page');
	page.empty();
	page.append(data.pageHTML);
	
	// 검색 후 첫 페이지 지정
	$('#currentPage').val(data.currentPage);
}

const movePage = (pageNum) => {
	$('#currentPage').val(pageNum);
	location.href = '/user/list.do?currentPage=' + pageNum;
}

const searchEnter = (e) => {
	if(e.keyCode === 13) {
		searchEvt();
	}
}
$(document).ready(function() {
	getBoardData();
	
});
</script>
</html>