<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-21(021)
  Time: 오후 2:36
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
    <div class="col-auto mt-5 mb-5"> </div>
    <table class="table table-primary table-striped">
        <thead>
        <tr>
            <th scope="col">fno</th>
            <th scope="col">title</th>
            <th scope="col">content</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${faq.fno}</td>
                <td>${faq.title}</td>
                <td>${faq.content}</td>
            </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
