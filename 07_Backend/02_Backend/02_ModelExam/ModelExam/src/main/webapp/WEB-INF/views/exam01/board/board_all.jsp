<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-15(015)
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <!--    bootstrap css cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
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
            <th scope="col">id</th>
            <th scope="col">title</th>
            <th scope="col">content</th>
        </tr>
        </thead>
        <tbody>
        <%--            JSP 반복문(jstl 표현식 import 하기) : <c:forEach var="변수" items="${배열}"> --%>
        <c:forEach var="data" items="${list}">
            <tr>
                <td>
                        <a href="/exam01/board/edition/${data.id}">${data.id}</a></td>
                <td>${data.title}</td>
                <td>${data.content}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--     TODO: 추가 버튼을 추가 : a href="이동페이지주소" 태그 사용 --%>
    <div class="text-center">
        <a href="/exam01/board/addition" class="btn btn-primary">추가</a>
    </div>

</div>
<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp" />
</body>
</html>
