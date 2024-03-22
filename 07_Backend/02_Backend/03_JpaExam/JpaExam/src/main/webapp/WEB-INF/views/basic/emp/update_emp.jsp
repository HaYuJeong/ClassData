<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-21(021)
  Time: 오전 11:21
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
        <form action="/basic/emp/edit/${emp.eno}" method="post">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="eno" value="${emp.eno}"/>

            <div class="mb-3">
                <label for="ename" class="form-label">ename</label>
                <input type="ename"
                       class="form-control"
                       id="ename"
                       name="ename"
                       value="${emp.ename}"
                       placeholder="사원명입력">
            </div>

            <div class="mb-3">
                <label for="job" class="form-label">job</label>
                <input type="job"
                       class="form-control"
                       id="job"
                       name="job"
                       value="${emp.job}"
                       placeholder="직위입력">
            </div>
            <div class="mb-3">
                <label for="manager" class="form-label">manager</label>
                <input type="manager"
                       class="form-control"
                       id="manager"
                       name="manager"
                       value="${emp.manager}"
                       placeholder="관리자입력">
            </div>
            <div class="mb-3">
                <label for="hireDate" class="form-label">hireDate</label>
                <input type="hireDate"
                       class="form-control"
                       id="hireDate"
                       name="hireDate"
                       value="${emp.hiredate}"
                       placeholder="입사일입력">
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">salary</label>
                <input type="salary"
                       class="form-control"
                       id="salary"
                       name="salary"
                       value="${emp.salary}"
                       placeholder="연봉입력">
            </div>
            <div class="mb-3">
                <label for="commission" class="form-label">commission</label>
                <input type="commission"
                       class="form-control"
                       id="commission"
                       name="commission"
                       value="${emp.commission}"
                       placeholder="상여금입력">
            </div>
            <%--        input 태그 : 수정 버튼 --%>
            <form class="mb-3 d-flex justify-content-center" action="/emp/edit/{eno}">
                <button type="submit" class="btn btn-warning mb-3">수정</button>
            </form>
        </form>
        <form action="/basic/emp/delete/${emp.eno}" method="post">
            <input type="hidden" name="_method" value="delete"/>
            <button type="submit" class="btn btn-danger">삭제</button>
        </form>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
