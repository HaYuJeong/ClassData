<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--    bootstrap css cdn 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-lg mb-4" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <%--         todo: img 기본 경로 : resources/static 임 : 절대경로로 넣을 것   --%>
            <img src="/resources/img/simple-coding.png" width="20" height="20"/>&nbsp;
            Navbar
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%--                부서 시작--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Dept
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/dept">Dept</a></li>
                        <li><a class="dropdown-item" href="/basic/dept/addition">Add Dept</a></li>
                    </ul>
                </li>
                <%--                부서 끝--%>

                <%--                회원 시작--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Emp
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/emp">Emp</a></li>
                        <li><a class="dropdown-item" href="/basic/emp/addition">Add Emp</a></li>
                    </ul>
                </li>
                <%--                회원 끝--%>

                <%--                Faq 시작--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Faq
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/faq">Faq</a></li>
                        <li><a class="dropdown-item" href="/basic/faq/addition">Add Faq</a></li>
                    </ul>
                </li>
                <%--                Faq 끝--%>

                <%--                업로드 시작--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        FileDb
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/advanced/fileDb">FileDb</a></li>
                        <li><a class="dropdown-item" href="/advanced/fileDb/addition">Add FileDb</a></li>
                    </ul>
                </li>
                <%--                업로드 끝--%>

                <%--                업로드 시작--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Gallery
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/advanced/gallery">Gallery</a></li>
                        <li><a class="dropdown-item" href="/advanced/gallery/addition">Add Gallery</a></li>
                    </ul>
                </li>
                <%--                업로드 끝--%>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>