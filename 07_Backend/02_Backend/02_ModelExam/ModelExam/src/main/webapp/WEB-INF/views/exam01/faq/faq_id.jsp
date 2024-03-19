<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-18(018)
  Time: 오전 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../../common/header.jsp"/>

<div class="container">
    <h2> 상세 조회</h2>
    <p>${list}</p>

    <table class="table table-warning table-striped">
        <thead>
        <tr>
            <th scope="col">fno</th>
            <th scope="col">subject</th>
            <th scope="col">text</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${faq.fno}</td>
            <td>${faq.subject}</td>
            <td>${faq.text}</td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
