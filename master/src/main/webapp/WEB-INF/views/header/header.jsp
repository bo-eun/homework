<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  *{
      margin: 0;
      padding: 0;
      box-sizing: border-box;
  }

  .nav-container{
      display: flex;
      background-color: #1a1a1a;
      height: 80px;
      line-height: 45px;
      justify-content: space-between;
      align-items: center;

  }
  .nav-container a {color:#fff; padding: 0px 16px;}
  .nav-container a:hover, .nav-container a:focus {color:#fff;}

  .nav-menu{
      display: flex;
      list-style-type: none;
      gap: 20px;
	margin: 0 2rem;
	padding: 0;
  }

  .nav-link{
      color: white;
      text-decoration: none;
      border-radius: 5px;
  }
  .nav-link:hover{
      background-color: #333;
  }

  .nav-link.active{
      background-color: tomato;
  }
  
</style>
</head>
<body>
	<!-- 전역변수 만들기, isLogin이 true면 로그인 상태 -->
	<c:set var="isLogin" value="${not empty sessionScope.userInfo}" scope="session" />
	<c:set var="user" value="${sessionScope.userInfo != null ? sessionScope.userInfo : null}" scope="session" />
	 <header>
	    <nav>
	        <div class="nav-container">
	            <ul class="nav-menu">
	                <li class="nav-item">
	                    <a href="/board/list.do" data-page="naver" class="nav-link active">게시판1</a>
	                </li>
	                <li class="nav-item">
	                    <a href="/board/list2.do" data-page="daum" class="nav-link">게시판2</a>
	                </li>
	                <li class="nav-item">
	                    <a href="/user/list.do" data-page="daum" class="nav-link">사용자 리스트</a>
	                </li>	                
	            </ul>
	            <ul class="nav-menu">
	                <li class="nav-item">
	                	<c:if test="${!isLogin}">
	                    	<a href="/user/login.do" class="nav-link">로그인</a>
	                    </c:if>
	                    
	                    <c:if test="${isLogin}">
	                    	<p style="color: white">${user.userName}님 환영합니다.</p>
	                    	<span style="color: white"><a href="/user/login.do" class="nav-link">로그아웃</a></span>
	                    </c:if> 
	                </li>	                
	            </ul>
	        </div>
	    </nav>
	</header>
</body>

<script>

    function move(site){
        const menuLinks = document.querySelectorAll('.nav-link');

        menuLinks.forEach( link => {
            link.classList.remove('active');
            id = link.getAttribute('data-page');
            if(site === id) {
                link.classList.add('active');
            }
        });
    }
</script>
</html>