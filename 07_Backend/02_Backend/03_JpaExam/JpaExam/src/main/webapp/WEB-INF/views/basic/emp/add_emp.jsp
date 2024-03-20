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
            <%--        input 태그 : 저장 버튼 --%>
            <div class="mb-3 d-flex justify-content-center">
                <button type="submit" class="btn btn-primary mb-3">저장</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
