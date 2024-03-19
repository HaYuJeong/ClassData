<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-19(019)
  Time: 오전 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 머리말 --%>
<jsp:include page="../../common/header.jsp"/>

<%-- 본문 --%>
<div class="container">
    <%--    TODO: 검색어 입력 상자--%>

    <%--    TODO: 테이블--%>
    <table class="table table-warning">
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
        <c:forEach var="data" items="${dept}">
            <tr>
                <td><a href="/basic/dept/edition/${data.dno}">${data.dno}</a></td>
                <td>${data.dname}</td>
                <td>${data.loc}</td>
                <td>${data.insertTime}</td>
                <td>${data.updateTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--    TODO: 페이지번호--%>
    <%--  복습 : class="d-flex justify-content-center" : 중앙정렬 --%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
<%--            TODO: startPage : 0부터 시작 (블럭 시작페이지 번호) --%>
<%--            TODO: endPage : (블럭 끝페이지 번호) --%>
<%--            TODO: currentPage : 0부터 시작 (현재 페이지 번호) --%>
<%--            이전 버튼 : startPage가 0이면 0 + 1 = 1 이니까 버튼 비활성화, 아니면 활성화 --%>
            <li class="page-item ${(startPage + 1 == 1)? 'disabled' : ''}">
                <a href="/basic/dept?page=${startPage-1}&size=${3}" class="page-link">이전</a>
            </li>

<%--            TODO: 1, 2, 3 없애고 반복문으로 실행 --%>
<%--             사용법 : <c:forEach var="data" begin="시작값" end="끝값">반복문</c:forEach>--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link" href="/basic/dept?page=${data}&size=${3}">${data + 1}</a></li>
            </c:forEach>

<%--            다음 버튼 : 끝페이지가 전체 페이지와 같으면 비활성화, 아니면 활성화 --%>
            <li class="page-item ${(endPage + 1 == totalPages)? 'disabled' : ''}">
                <a href="/basic/dept?page=${endPage+1}&size=${3}" class="page-link">다음</a>
            </li>
        </ul>
    </div>

    <%--    TODO: Add 버튼--%>
    <div class="text-center">
        <a href="/basic/dept/add" class="btn btn-primary">Add</a>
    </div>
</div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>