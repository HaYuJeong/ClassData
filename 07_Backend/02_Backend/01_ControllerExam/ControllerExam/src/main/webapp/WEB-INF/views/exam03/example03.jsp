<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-12
  Time: 오후 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--TODO: jstl 표현식 사용을 위한 import --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>매개변수 4개 전달 : 향상된 배열 전달</h2>
    <p>${list}</p>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">eno</th>
            <th scope="col">ename</th>
            <th scope="col">salary</th>
            <th scope="col">job</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="data" items="${list}">
            <td>${data}</td>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
