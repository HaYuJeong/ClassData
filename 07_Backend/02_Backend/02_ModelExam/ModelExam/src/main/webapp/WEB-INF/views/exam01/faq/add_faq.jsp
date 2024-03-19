<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-18(018)
  Time: 오후 12:18
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
    <%--    action의 url은, 저장버튼 누르면 이동되는 주소--%>
    <form action="/exam01/faq/add" method="post">
        <%--        부트스트랩 input 양식 쓰기 --%>
        <%--        부서명 입력  --%>
        <div class="mb-3">
            <label for="subject" class="form-label">subject</label>
            <input type="text"
                   class="form-control"
                   id="subject"
                   name="subject"
                   placeholder="제목입력">
        </div>
        <%--        부서위치 입력 --%>
        <div class="mb-3">
            <label for="text" class="form-label">text</label>
            <input type="text"
                   class="form-control"
                   id="text"
                   name="text"
                   placeholder="내용입력">
        </div>
        <%--        바닥에 마진 주기--%>
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">저장</button>
        </div>
    </form>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
