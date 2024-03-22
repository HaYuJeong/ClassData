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
    <div>
        <%--        TODO: 저장(insert - post 방식 - @PostMapping) 버튼 누르면 "/basic/dept/add" url로 감--%>
        <form action="/basic/faq/add" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">title</label>
                <input type="title"
                       class="form-control"
                       id="title"
                       name="title"
                       placeholder="제목입력">
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">content</label>
                <input type="content"
                       class="form-control"
                       id="content"
                       name="content"
                       placeholder="내용입력">
            </div>

            <form class="mb-3 d-flex justify-content-center" action="/faq/add">
                <button type="submit" class="btn btn-primary mb-3">저장</button>
            </form>
        </form>
    </div>
</div>


<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
