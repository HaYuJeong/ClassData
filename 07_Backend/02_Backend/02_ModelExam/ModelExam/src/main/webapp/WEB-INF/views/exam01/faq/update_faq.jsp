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

    <form action="/exam01/faq/edit/${faq.fno}" method="post">
        <input type="hidden" name="_method" value="put"/>
        <input type="hidden" name="id" value="${faq.fno}"/>
        <%--        제목 입력 양식 --%>
        <div class="mb-3">
            <label for="subject" class="form-label">subject</label>
            <input type="text"
                   class="form-control"
                   id="subject"
                   name="subject"
                   value="${faq.subject}"
                   placeholder="제목입력">
        </div>
        <%--        내용 입력 양식 --%>
        <div class="mb-3">
            <label for="text" class="form-label">text</label>
            <input type="text"
                   class="form-control"
                   id="text"
                   name="text"
                   value="${faq.text}"
                   placeholder="내용입력">
        </div>
        <%--            수정 버튼 --%>
        <div class="mb-3">
            <button type="submit" class="btn btn-warning">수정</button>
        </div>
    </form>
    <%--    삭제 form --%>
    <form action="/exam01/faq/delete/${faq.fno}" method="post">
        <%--        delete 방식은 아래 input 태그가 필요함 --%>
        <input type="hidden" name="_method" value="delete"/>
        <%--        삭제 버튼 추가 : submit --%>
        <button type="submit" class="btn btn-danger" onsubmit="return confirmDelete();">삭제</button>
    </form>
</div>

<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
