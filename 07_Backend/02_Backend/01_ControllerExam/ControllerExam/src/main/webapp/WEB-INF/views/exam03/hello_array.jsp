<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-12
  Time: 오전 11:06
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
    <h2>URL 매개변수 2개 전달 : 향상된 배열 전달</h2>

<%--    jsp el 표현식 : ${변수명} --%>
    <p>${list}</p>
    <%--    부트스트랩 테이블 사용--%>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
        </tr>
        </thead>
        <tbody>
        <tr>
        <%--    jsp 반복문 예제 : jstl 표현식 (조건문/반복문 등등) --%>
        <%--    jstl 표현식 사용 : 제일 위에 추가 : taglib --%>
        <%--    사용법 : <c:forEach var="변수" items="${배열}"> --%>
        <%--         1) 배열의 끝까지 자동으로 반복됨      --%>
        <%--         2) 배열에서 1개씩 꺼내서 변수에 저장함 --%>
            <c:forEach var = "data" items="${list}">
                <td>${data}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>
