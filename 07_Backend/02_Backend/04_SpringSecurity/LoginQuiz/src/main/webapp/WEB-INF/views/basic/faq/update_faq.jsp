<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-21(021)
  Time: 오후 2:36
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
        <form action="/basic/faq/edit/${faq.fno}" method="post">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="eno" value="${faq.fno}"/>

            <div class="mb-3">
                <label for="title" class="form-label">title</label>
                <input type="title"
                       class="form-control"
                       id="title"
                       name="title"
                       value="${faq.title}"
                       placeholder="제목입력">
            </div>

            <div class="mb-3">
                <label for="content" class="form-label">content</label>
                <input type="content"
                       class="form-control"
                       id="content"
                       name="content"
                       value="${faq.content}"
                       placeholder="내용입력">
            </div>

            <%--        input 태그 : 수정 버튼 --%>
            <form class="mb-3 d-flex justify-content-center" action="/faq/edit/{fno}">
                <button type="submit" class="btn btn-warning mb-3">수정</button>
            </form>
        </form>
<%--        삭제 form : 삭제 버튼 --%>
        <form action="/basic/faq/delete/${faq.fno}" method="post">
            <input type="hidden" name="_method" value="delete"/>
            <button type="submit" class="btn btn-danger">삭제</button>
        </form>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>