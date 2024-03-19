<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-18(018)
  Time: 오전 10:58
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
    <h2>전체 조회</h2>
    <p>${list}</p>

    <table class="table table-warning table-striped">
        <thead>
        <tr>
            <th scope="col">fno</th>
            <th scope="col">subject</th>
            <th scope="col">text</th>
            <th scope="col">FAQ 수정</th>
            <th scope="col">FAQ 삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="data" items="${list}">
            <tr>
                <td>
                    <a href="/exam01/faq/edition/${data.fno}">${data.fno}</a></td>
                <td>${data.subject}</td>
                <td>${data.text}</td>
                <td><a class="btn btn-outline-warning ml-auto" href="/exam01/faq/edition/${data.fno}" role="button" >수정</a></td>
                <td> <form action="/exam01/faq/delete/${data.fno}" method="post" class="container" onsubmit="return confirmDelete();">
                    <input type="hidden" name="_method" value="delete"/>
                    <div><button type="submit" class="btn btn-outline-warning">삭제</button></div>
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-center">
        <a href="/exam01/faq/addition" class="btn btn-primary">추가</a>
    </div>
</div>

<script>
    function confirmDelete() {
        return confirm("정말로 삭제하시겠습니까?");
    }
</script>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>