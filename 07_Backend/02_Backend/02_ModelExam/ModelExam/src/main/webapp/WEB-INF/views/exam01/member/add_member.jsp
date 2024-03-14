<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-14(014)
  Time: 오후 2:06
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
<jsp:include page="../../common/header.jsp"/>

<%-- 본문 --%>
<%-- html : 입력(insert/update) : form/input 태그 --%>
<div class="container">
    <form action="/exam01/member/add" method="post">
        <%--        부트스트랩 input 양식 쓰기 --%>
        <%--        부서명 입력  --%>
        <div class="mb-3">
            <label for="ename" class="form-label">ename</label>
            <input type="text"
                   class="form-control"
                   id="ename"
                   name="ename"
                   placeholder="사원명입력">
        </div>
        <%--        직위 입력 --%>
        <div class="mb-3">
            <label for="job" class="form-label">job</label>
            <input type="text"
                   class="form-control"
                   id="job"
                   name="job"
                   placeholder="직위입력">
        </div>
            <%--        관리자 사원번호 입력 --%>
            <div class="mb-3">
                <label for="manager" class="form-label">manager</label>
                <input type="text"
                       class="form-control"
                       id="manager"
                       name="manager"
                       placeholder="관리자 사원번호입력">
            </div>
        <%--        바닥에 마진 주기--%>
        <div class="mb-3">
        </div>
        <button type="submit" class="btn btn-primary">저장</button>
    </form>
</div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
