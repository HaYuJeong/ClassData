<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-21(021)
  Time: 오후 2:35
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

    <%--    TODO: 검색어 입력 창--%>
    <form class="row g-3 justify-content-center" action="/basic/faq" method="get">
        <div class="col-auto mt-5 mb-5">
            <input type="text"
                   class="form-control"
                   id="title"
                   name="title"
                   placeholder="제목입력">
        </div>

    <%--    TODO: 검색 버튼 --%>
        <div class="col-auto mt-5 mb-5">
            <button type="submit" class="btn btn-dark">search</button>
        </div>
    </form>

    <%--    TODO: 테이블 --%>
    <table class="table table-primary table-striped">
        <thead>
        <tr>
            <th scope="col">fno</th>
            <th scope="col">title</th>
            <th scope="col">content</th>
            <th scope="col">insertTime</th>
            <th scope="col">updateTime</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="data" items="${faq}">
            <tr>
                <td><a href="/basic/faq/edition/${data.fno}">${data.fno}</a></td>
                <td>${data.title}</td>
                <td>${data.content}</td>
                <td>${data.insertTime}</td>
                <td>${data.updateTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--    TODO: 페이지번호 --%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <li class="page-item ${(startPage + 1 == 1)? 'disabled' : ''}">
                <a href="/basic/faq?page=${startPage-1}&size=${3}" class="page-link">Previous</a>
            </li>

            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link" href="/basic/faq?page=${data}&size=${3}">${data + 1}</a></li>
            </c:forEach>

            <li class="page-item ${(endPage + 1 == totalPages)? 'disabled' : ''}">
                <a href="/basic/faq?page=${endPage+1}&size=${3}" class="page-link">Next</a>
            </li>
        </ul>
    </div>

    <%--    TODO: Add 버튼--%>
    <div class="text-center">
        <a href="/basic/faq/addition" class="btn btn-primary">Add</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
