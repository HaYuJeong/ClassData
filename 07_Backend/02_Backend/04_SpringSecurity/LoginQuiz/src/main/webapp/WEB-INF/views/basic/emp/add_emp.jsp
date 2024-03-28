<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-20(020)
  Time: 오후 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>

<div class="container">
    <div>
        <%--        TODO: 저장(insert - post 방식 - @PostMapping) 버튼 누르면 "/basic/dept/add" url로 감--%>
        <form action="/basic/emp/add" method="post">
            <div class="mb-3">
                <label for="ename" class="form-label">ename</label>
                <input type="ename"
                       class="form-control"
                       id="ename"
                       name="ename"
                       placeholder="사원명입력">
            </div>
            <div class="mb-3">
                <label for="loc" class="form-label">loc</label>
                <input type="loc"
                       class="form-control"
                       id="loc"
                       name="loc"
                       placeholder="부서위치입력">
            </div>
            <div class="mb-3">
                <label for="job" class="form-label">job</label>
                <input type="job"
                       class="form-control"
                       id="job"
                       name="job"
                       placeholder="직위입력">
            </div>
            <div class="mb-3">
                <label for="manager" class="form-label">manager</label>
                <input type="manager"
                       class="form-control"
                       id="manager"
                       name="manager"
                       placeholder="관리자입력">
            </div>
            <div class="mb-3">
                <label for="hireDate" class="form-label">hireDate</label>
                <input type="hireDate"
                       class="form-control"
                       id="hireDate"
                       name="hireDate"
                       placeholder="입사일입력">
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">salary</label>
                <input type="salary"
                       class="form-control"
                       id="salary"
                       name="salary"
                       placeholder="연봉입력">
            </div>
            <div class="mb-3">
                <label for="commission" class="form-label">commission</label>
                <input type="commission"
                       class="form-control"
                       id="commission"
                       name="commission"
                       placeholder="상여금입력">
            </div>
            <%--        input 태그 : 저장 버튼 --%>
            <form class="mb-3 d-flex justify-content-center" action="/emp/add">
                <button type="submit" class="btn btn-primary mb-3">저장</button>
            </form>
        </form>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
