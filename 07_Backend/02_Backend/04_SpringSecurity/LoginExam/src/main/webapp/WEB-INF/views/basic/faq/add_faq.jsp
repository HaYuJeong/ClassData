<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-20
  Time: 오후 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 머리말 --%>
<jsp:include page="../../common/header.jsp"/>
<%-- 본문 --%>
<div class="container">
    <div>
        <form action="/basic/faq/add" method="post">
            <%--            input 태그 : 제목 --%>
            <div class="mb-3">
                <label for="title" class="form-label">title</label>
                <input type="text"
                       class="form-control"
                       id="title"
                       name="title"
                       placeholder="제목입력">
            </div>
            <%--            input 태그 : 내용--%>
            <div class="mb-3">
                <label for="content" class="form-label">content</label>
                <input type="text"
                       class="form-control"
                       id="content"
                       name="content"
                       placeholder="내용입력">
            </div>
            <%--            저장 버튼--%>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary">저장</button>
            </div>

        </form>
    </div>
</div>
<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
