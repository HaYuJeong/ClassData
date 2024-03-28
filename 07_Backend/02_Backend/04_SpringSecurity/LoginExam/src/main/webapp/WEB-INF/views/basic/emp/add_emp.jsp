<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-20
  Time: 오후 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 머리말 --%>
<jsp:include page="../../common/header.jsp"/>
<%-- 본문 --%>
<div class="container">
    <div>
        <%--     TODO: 저장(insert - post 방식 - @PostMapping) --%>
        <form action="/basic/emp/add" method="post">
            <%--            input 태그 : 사원명 --%>
            <div class="mb-3">
                <label for="ename" class="form-label">ename</label>
                <input type="text"
                       class="form-control"
                       id="ename"
                       name="ename"
                       placeholder="사원명입력">
            </div>
            <%--            input 태그 : 직위 --%>
            <div class="mb-3">
                <label for="job" class="form-label">job</label>
                <input type="text"
                       class="form-control"
                       id="job"
                       name="job"
                       placeholder="직위입력">
            </div>
            <%--            input 태그 : 관리자사원번호 --%>
            <div class="mb-3">
                <label for="manager" class="form-label">manager</label>
                <input type="text"
                       class="form-control"
                       id="manager"
                       name="manager"
                       placeholder="관리자사원번호입력">
            </div>
            <%--            input 태그 : 입사일 --%>
            <div class="mb-3">
                <label for="hiredate" class="form-label">hiredate</label>
                <input type="text"
                       class="form-control"
                       id="hiredate"
                       name="hiredate"
                       placeholder="입사일입력">
            </div>
            <%--            input 태그 : 급여 --%>
            <div class="mb-3">
                <label for="salary" class="form-label">salary</label>
                <input type="text"
                       class="form-control"
                       id="salary"
                       name="salary"
                       placeholder="급여입력">
            </div>
            <%--            input 태그 : 상여금 --%>
            <div class="mb-3">
                <label for="commission" class="form-label">commission</label>
                <input type="text"
                       class="form-control"
                       id="commission"
                       name="commission"
                       placeholder="상여금입력">
            </div>
            <%--            input 태그 : 부서번호 --%>
            <div class="mb-3">
                <label for="dno" class="form-label">dno</label>
                <input type="text"
                       class="form-control"
                       id="dno"
                       name="dno"
                       placeholder="부서번호입력">
            </div>
            <%--            저장 버튼--%>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary">저장</button>
            </div>

        </form>
    </div>
</div>
<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
