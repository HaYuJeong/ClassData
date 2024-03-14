<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-14(014)
  Time: 오전 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--머리말--%>
<jsp:include page="../../common/header.jsp" />

<%--본문--%>
<div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">eno</th>
            <th scope="col">ename</th>
            <th scope="col">job</th>
            <th scope="col">manager</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${member.eno}</td>
            <td>${member.ename}</td>
            <td>${member.job}</td>
            <td>${member.manager}</td>
        </tr>
        </tbody>
    </table>
</div>

<%--꼬리말--%>
<jsp:include page="../../common/footer.jsp" />
</body>
</html>
