<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-29(029)
  Time: 오전 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../../common/header.jsp"/>

<div class="container">
    <%-- 테이블 반복문--%>
    <%--    todo: 테이블 반복문 시작--%>
    <table class="table">
        <tbody>
        <c:forEach var="data" items="${list}">
            <tr>
                <td>${data.dno}</td>
                <td>${data.dname}</td>
                <td>${data.loc}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <%--    todo: 테이블 반복문 끝--%>
</div>


<jsp:include page="../../../common/footer.jsp"/>
</body>
</html>
