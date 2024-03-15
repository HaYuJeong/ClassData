<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-15(015)
  Time: 오후 7:45
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

<div class="container">
    <%--    수정 form--%>
    <form action="/exam01/board/edit/${board.id}" method="post">
        <%--            TODO: 아래 input(hidden) 사용하면 put 방식으로 전송됨 --%>
        <input type="hidden" name="_method" value="put"/>
        <%--            TODO: 부서번호(기본키) 숨김 : 수정불가 --%>
        <input type="hidden" name="id" value="${board.id}"/>
        <%--        제목 입력 양식 --%>
        <div class="mb-3">
            <label for="title" class="form-label">title</label>
            <input type="text"
                   class="form-control"
                   id="title"
                   name="title"
                   value="${board.title}"
                   placeholder="제목입력">
        </div>
        <%--        내용 입력 양식 --%>
        <div class="mb-3">
            <label for="content" class="form-label">content</label>
            <input type="text"
                   class="form-control"
                   id="content"
                   name="content"
                   value="${board.content}"
                   placeholder="내용입력">
        </div>
        <%--            수정 버튼 --%>
        <div class="mb-3">
            <button type="submit" class="btn btn-warning">수정</button>
        </div>
    </form>
    <%--    삭제 form --%>
    <form action="/exam01/board/delete/${board.id}" method="post">
        <%--        delete 방식은 아래 input 태그가 필요함 --%>
        <input type="hidden" name="_method" value="delete"/>
        <%--        삭제 버튼 추가 : submit --%>
        <button type="submit" class="btn btn-danger">삭제</button>
</div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
