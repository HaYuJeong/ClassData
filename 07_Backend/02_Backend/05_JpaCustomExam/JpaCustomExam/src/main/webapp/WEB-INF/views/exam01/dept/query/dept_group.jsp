<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-29
  Time: 오전 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--머리말 --%>
<jsp:include page="../../../common/header.jsp"/>
<%--본문 --%>
<div class="container">
    <%-- 테이블 반복문--%>
    <%--    todo: 테이블 반복문 시작--%>
    <table class="table">
        <tbody>
        <c:forEach var="data" items="${list}">
            <tr>
                <td>${data.avgVar}</td>
                <td>${data.maxVar}</td>
                <td>${data.minVar}</td>
                <td>${data.sumVar}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <%--    todo: 테이블 반복문 끝--%>
</div>
<%--꼬리말 --%>
<jsp:include page="../../../common/footer.jsp"/>

</body>
</html>