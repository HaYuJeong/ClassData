<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-20(020)
  Time: 오후 3:37
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
    <table class="table table-success table-striped">
        <thead>
        <tr>
            <th scope="col">dno</th>
            <th scope="col">dname</th>
            <th scope="col">loc</th>
            <th scope="col">insertTime</th>
            <th scope="col">updateTime</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${dept.dno}</td>
                <td>${dept.dname}</td>
                <td>${dept.loc}</td>
                <td>${dept.insertTime}</td>
                <td>${dept.updateTime}</td>
            </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
