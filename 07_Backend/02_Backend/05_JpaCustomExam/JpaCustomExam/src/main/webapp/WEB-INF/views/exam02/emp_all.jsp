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
        <c:forEach var="data" items="${emp}">
            <tr>
                <td><a href="/basic/emp/edition/${data.eno}">${data.eno}</a></td>
                <td>${data.ename}</td>
                <td>${data.job}</td>
                <td>${data.manager}</td>
                <td>${data.hiredate}</td>
                <td>${data.salary}</td>
                <td>${data.commission}</td>
                <td>${data.dept2.dno}</td>
                <td>${data.dept2.dname}</td>
                <td>${data.insertTime}</td>
                <td>${data.updateTime}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <%--    todo: 테이블 반복문 끝--%>

    <%--    todo: 페이지 번호 시작--%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <%--                todo: 첫페이지 번호 --%>
            <%--                 startPage : 0부터 시작 --%>
            <%--                 currentPage : 0부터 시작--%>
            <li class="page-item ${(startPage+1==1)? 'disabled': ''}">
                <a class="page-link" href="/exam02/emp/join/paging2?page=${startPage-1}&size=${3}">Previous</a>
            </li>
            <%--                todo: 실제 페이지 번호들 --%>
            <%--                  사용법 : <c:forEach var="data" begin="시작값" end="끝값">반복문</c:forEach>--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item ${(currentPage==data)? 'active': ''}">
                    <a class="page-link" href="/exam02/emp/join/paging2?page=${data}&size=${3}">
                            ${data+1}
                    </a>
                </li>
            </c:forEach>
            <%--                todo: 끝페이지 번호--%>
            <li class="page-item ${(endPage+1==totalPages)? 'disabled': ''}">
                <a class="page-link" href="/exam02/emp/join/paging2?page=${endPage+1}&size=${3}">Next</a>
            </li>
        </ul>
    </div>
    <%--    todo: 페이지 번호 끝--%>

</div>

<%--footer--%>
<jsp:include page="../common/footer.jsp"/>

</body>
</html>