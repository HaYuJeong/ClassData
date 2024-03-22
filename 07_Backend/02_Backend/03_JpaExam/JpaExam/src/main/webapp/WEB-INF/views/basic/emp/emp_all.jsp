<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-19
  Time: 오후 5:39
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
<jsp:include page="../../common/header.jsp"/>
<%--본문--%>
<div class="container">
    <%--        TODO: 검색어 입력 상자 --%>
    <form class="row g-3 justify-content-center" action="/basic/emp" method="get">
        <%--        TODO: 검색 input 태그 --%>
        <div class="col-auto mt-5">
            <input type="text"
                   class="form-control"
                   id="ename"
                   name="ename"
                   placeholder="사원명입력">
            <%--            page(현재페이지번호), size(1페이지당개수) --%>
            <input type="hidden" id="page" name="page" value="0"/>
            <input type="hidden" id="size" name="size" value="3"/>
        </div>
        <%--        TODO: 검색 버튼--%>
        <div class="col-auto mt-5 mb-3">
            <button type="submit" class="btn btn-primary">검색</button>
        </div>
    </form>

    <%--        TODO: 테이블 --%>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">eno</th>
            <th scope="col">ename</th>
            <th scope="col">job</th>
            <th scope="col">manager</th>
            <th scope="col">hiredate</th>
            <th scope="col">salary</th>
            <th scope="col">commission</th>
            <th scope="col">dno</th>
            <th scope="col">insertTime</th>
            <th scope="col">updateTime</th>
        </tr>
        </thead>
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
                <td>${data.dno}</td>
                <td>${data.insertTime}</td>
                <td>${data.updateTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--        TODO: 페이지번호 --%>
    <%--  복습 : class="d-flex justify-content-center" : 중앙정렬 --%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <%--          TODO: startPage   : 0부터 시작 (블럭 시작페이지번호)--%>
            <%--          TODO: endPage     : (블럭 끝페이지번호)--%>
            <%--          TODO: currentPage : 현재페이지번호 0부터 시작 --%>
            <%--        이전 버튼 : 시작페이지가 0이면 버튼 비활성화 아니면 활성화 --%>
            <li class="page-item ${(startPage+1==1)? 'disabled' : ''}">
                <a href="/basic/emp?page=${startPage-1}&size=${3}&ename=${ename}" class="page-link">이전</a>
            </li>
            <%--       TODO: 반복문으로 실행 --%>
            <%--        사용법 : <c:forEach var="data" begin="시작값" end="끝값">반복문</c:forEach>--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link" href="/basic/emp?page=${data}&size=${3}&ename=${ename}">${data+1}</a></li>
            </c:forEach>

            <%--        다음 버튼 : 끝페이지가 전체페이지와 같으면 비활성화 아니면 활성화 --%>
            <li class="page-item ${(endPage+1==totalPages)? 'disabled' : ''}">
                <a href="/basic/emp?page=${endPage+1}&size=${3}&ename=${ename}" class="page-link">다음</a>
            </li>
        </ul>
    </div>


    <%--        TODO: Add 버튼 --%>
    <div class="text-center">
        <a href="/basic/emp/addition" class="btn btn-primary">Add</a>
    </div>
</div>
<%--꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>