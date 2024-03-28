<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-28
  Time: 오후 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 머리말 --%>
<jsp:include page="../common/header.jsp"/>
<%-- 본문 --%>
<div class="container">
    <div>
        <div class="card mt-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <%--                       TODO:  회원가입 form --%>
                            <form class="user" action="/auth/regist" method="post">
                                <%--                   TODO:  email 입력상자 --%>
                                <div class="form-group">
                                    <input
                                            type="text"
                                            class="form-control form-control-user mb-3"
                                            id="userId"
                                            placeholder="userId 입력"
                                            name="userId"
                                    />
                                </div>
                                <%--                     패스워드, re-패스워드, 이름 --%>
                                <div class="form-group row">
                                    <%--               TODO:      패스워드 --%>
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input
                                                type="password"
                                                class="form-control form-control-user mb-3"
                                                id="exampleInputPassword"
                                                placeholder="Password"
                                                name="password"
                                        />
                                    </div>
                                    <%--               TODO:      re-패스워드 --%>
                                    <div class="col-sm-6">
                                        <input
                                                type="password"
                                                class="form-control form-control-user mb-3"
                                                id="exampleRepeatPassword"
                                                placeholder="Repeat Password"
                                        />
                                    </div>
                                    <%--                TODO:       이름 --%>
                                    <div class="form-group">
                                        <input
                                                type="text"
                                                class="form-control form-control-user mb-3"
                                                id="name"
                                                placeholder="Full Name"
                                                name="name"
                                        />
                                    </div>
                                    <%--                TODO:    권한 선택상자(select태그) 디자인 --%>
                                    <div class="form-group">
                                        <select class="form-select mb-3 form-control-select"
                                                aria-label="Default select example"
                                                name="codeName"
                                        >
                                            <option value="ROLE_USER" selected>ROLE_USER</option>
                                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                                        </select>
                                    </div>
                                </div>
                                <button
                                        type="submit"
                                        class="btn btn-primary btn-user w-100 mb-3"
                                >
                                    회원가입
                                </button>
                                <%--                               TODO:     csrf 보안 토큰 --%>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>

                            <hr/>
                            <div class="text-center">
                                <a href=""> Forgot Password? </a>
                            </div>
                            <div class="text-center">
                                <a href=""> Already have an account? Login! </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<%-- 꼬리말 --%>
<jsp:include page="../common/footer.jsp"/>

</body>
</html>
