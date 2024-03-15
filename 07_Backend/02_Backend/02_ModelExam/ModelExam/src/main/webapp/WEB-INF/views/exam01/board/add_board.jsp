<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-15(015)
  Time: 오후 4:18
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
<jsp:include page="../../common/header.jsp" />

<%-- 본문 --%>
<div class="container">
    <%--    action의 url은, 저장버튼 누르면 이동되는 주소--%>
    <form action="/exam01/board/add" method="post">
        <%--        부트스트랩 input 양식 쓰기 --%>

        <%--        게시판 제목 입력 --%>
        <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text"
                   class="form-control"
                   id="title"
                   name="title"
                   placeholder="게시판 제목 입력">
        </div>
            <%--        게시판 내용 입력 --%>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <input type="text"
                       class="form-control"
                       id="content"
                       name="content"
                       placeholder="게시판 내용 입력">
            </div>
        <%--        바닥에 마진 주기--%>
        <div class="mb-3">
<%--            저장 버튼 삽입 --%>
            <button type="submit" class="btn btn-primary">저장</button>
        </div>
    </form>
</div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp" />
</body>
</html>
