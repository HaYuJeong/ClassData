<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-13
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <!--    bootstrap css cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <%-- main.css import --%>
    <link href="/resources/css/main.css" rel="stylesheet">
    <%--    login.css import --%>
    <link href="/resources/css/login.css" rel="stylesheet">
</head>
<body>
<%--부트스트렙 메뉴--%>
<nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <%--        1번째 메뉴 : Navbar -> Simple --%>
        <a class="navbar-brand" href="/">
            <%--            TODO: 이미지 넣기 --%>
            <img src="/resources/img/simple-coding.png" width="20" height="20"/>
            Simple</a>
        <%--    부트스트랩 메뉴 : 화면이 작아지면 단축메뉴로 바뀜(햄버거 버튼) --%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <%--    메뉴 시작 --%>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%--                Dept(부서) 시작--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Dept(부서)
                    </a>
                    <%--                    소메뉴--%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/dept">Dept</a></li>
                        <li><a class="dropdown-item" href="/basic/dept/addition">Add Dept</a></li>
                    </ul>
                </li>
                <%--                Emp(사원) --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Emp(사원)
                    </a>
                    <%--                    소메뉴--%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/emp">Emp</a></li>
                        <li><a class="dropdown-item" href="/basic/emp/addition">Add Emp</a></li>
                    </ul>
                </li>
                <%--                Faq(사원) --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Faq(종합문제)
                    </a>
                    <%--                    소메뉴--%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/faq">Faq</a></li>
                        <li><a class="dropdown-item" href="/basic/faq/addition">Add Faq</a></li>
                    </ul>
                </li>
                <%--                FileDb #4 --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        FileDb
                    </a>
                    <%--                    소메뉴--%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/advanced/fileDb">FileDb</a></li>
                        <li><a class="dropdown-item" href="/advanced/fileDb/addition">Add FileDb</a></li>
                    </ul>
                </li>
                    <%--                FileDb #4 --%>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Gallery
                        </a>
                        <%--                    소메뉴--%>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/advanced/gallery">Gallery</a></li>
                            <li><a class="dropdown-item" href="/advanced/gallery/addition">Add Gallery</a></li>
                        </ul>
                    </li>
            </ul>
            <%--            TODO: 우측 메뉴 : ms-auto(왼쪽은 자동으로 마진 부여 : 오른쪽으로 붙음) --%>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <sec:authorize access="hasRole('ADMIN')">
                <%--       TODO:  소메뉴 : Admin #1 : 관리자면 이 메뉴가 보이고, 사용자는 안보임--%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Admin</a>
                </li>
                </sec:authorize>

                    <sec:authorize access="isAnonymous()">
                <%--       TODO:      회원가입 #2 : register --%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/auth/register">Register</a>
                </li>
                <%--       TODO:       로그인 #3 : Login --%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/auth/customLogin">Login</a>
                </li>
                    </sec:authorize>

                <%--       TODO:     로그아웃 #4 : Logout : 로그아웃하면 나오는 페이지 없음, form 태그 달기(post 방식) --%>
                    <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <form action="/auth/customLogout" method="post">
                        <button type="submit" class="btn">Logout</button>
                        <%--             TODO: csrf 보안 토큰 : 해킹 방지 토큰 --%>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </li>
                    </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>