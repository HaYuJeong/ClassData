<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-13
  Time: 오후 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--TODO: jstl 표현식 사용을 위한 import : 반복문/조건문 등 사용 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <!--    bootstrap css cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%-- JSP 파일 끼워넣기 사용법 --%>
<%-- <jsp:include page="jsp경로" /> --%>
<%-- 머리말 --%>
<jsp:include page="../../common/header.jsp" />
<%-- 본문 --%>
    <div class="container">
        <h2>전체 조회</h2>
        <p>${list}</p>

        <%--        부트스트랩 테이블 --%>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">dno</th>
                <th scope="col">dname</th>
                <th scope="col">loc</th>
            </tr>
            </thead>
            <tbody>
            <%--            JSP 반복문(jstl 표현식 import 하기) : <c:forEach var="변수" items="${배열}"> --%>
            <c:forEach var="data" items="${list}">
                <tr>
                    <td>${data.dno}</td>
                    <td>${data.dname}</td>
                    <td>${data.loc}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp" />
</body>
</html>
