<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<%--header--%>
<jsp:include page="../common/header.jsp"/>

<div class="container">
    <%--    todo: 테이블 반복문 시작--%>
    <table class="table">
        <tbody>
        <c:forEach var="data" items="${list}">
            <tr>
                <td><a href="/basic/emp/edition/${data.eno}">${data.eno}</a></td>
                <td>${data.ename}</td>
                <td>${data.job}</td>
                <td>${data.manager}</td>
                <td>${data.hiredate}</td>
                <td>${data.salary}</td>
                <td>${data.commission}</td>
                <td>${data.department.dno}</td>
                <td>${data.department.dname}</td>
                <td>${data.insertTime}</td>
                <td>${data.updateTime}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <%--    todo: 테이블 반복문 끝--%>
</div>

<%--footer--%>
<jsp:include page="../common/footer.jsp"/>

</body>
</html>