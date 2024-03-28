<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-27(027)
  Time: 오전 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div content="container">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card mt-5">
                <div class="card-body p-0">
                    <!-- {/* Nested Row within Card Body */} -->
                    <div class="row">
                        <div class="col-lg-6 bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 mb-4">Welcome Back!</h1>
                                </div>
                                <%--          TODO: 백엔드 전송(id/password) : auth/login 으로 보냄, post 방식 --%>
                                <form class="user" action="/auth/login" method="post">

                                    <%--      TODO: email 입력 : 로그인 ID input 양식 --%>
                                    <div class="form-group">
                                        <input
                                                type="text"
                                                class="form-control form-control-user mb-3"
                                                id="userId"
                                    <%--       TODO: (참고) 웹접근성 속성--%>
                                                aria-describedby="usernameHelp"
                                                placeholder="로그인 ID 입력"
                                        <%--   TODO: 스프링시큐리티 사용 로그인 ID 속성 : username --%>
                                                name="username"
                                        />
                                    </div>

                                    <%--      TODO: password 입력 : 로그인 password input 양식 --%>
                                    <div class="form-group">
                                        <input
                                                type="password"
                                                class="form-control form-control-user mb-3"
                                                id="password"
                                                placeholder="password"
                                                name="password"
                                        />
                                    </div>

                                    <%--                          TODO: 로그인 버튼 --%>
                                    <button type="submit" class="btn btn-primary btn-user w-100 mb-3">Login</button>

                                    <hr/>
                                    <a href="/" class="btn btn-google btn-user w-100 mb-2">
                                        <i class="fab fa-google fa-fw"></i>&nbsp;Login with
                                        Google
                                    </a>
                                    <a href="/" class="btn btn-naver btn-user w-100 mb-2">
                                        <i class="fa-solid fa-n"></i>&nbsp;Login with Naver
                                    </a>
                                    <a href="/" class="btn btn-kakao btn-user w-100 mb-3">
                                        <i class="fa-solid fa-k"></i>&nbsp;Login with Kakao
                                    </a>
                                    <%--             TODO: csrf 보안 토큰 : 해킹 방지 토큰 --%>
                                    <%--              언제 : post, put, delete 방식 사용시 넣어주어야 인증됨--%>
                                    <%--                           ex) 로그인/로그아웃, 부서추가/수정, 사원추가 등--%>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>

                                <hr/>
                                <div class="text-center">
                                    <a class="small" href="">
                                        Forgot Password?
                                    </a>
                                </div>
                                <%--                  TODO: 회원가입 url--%>
                                <div class="text-center">
                                    <a class="small" href="/auth/register">
                                        회원가입
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
