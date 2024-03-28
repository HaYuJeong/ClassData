<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-21
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말 --%>
<jsp:include page="../../common/header.jsp"/>
<%--    본문 --%>
<div class="container">
    <div>
        <%--        수정 form 태그 --%>
        <form action="/basic/faq/edit/${faq.fno}" method="post">
            <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  put 방식으로 인식해서 연결해줌    --%>
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="fno" value="${faq.fno}"/>
            <%--            input 태그 : 제목 --%>
            <div class="mb-3">
                <label for="title" class="form-label">title</label>
                <input type="text"
                       class="form-control"
                       id="title"
                       name="title"
                       value="${faq.title}"
                       placeholder="부서명입력">
            </div>
            <%--            input 태그 : 내용 --%>
            <div class="mb-3">
                <label for="content" class="form-label">content</label>
                <input type="text"
                       class="form-control"
                       id="content"
                       name="content"
                       value="${faq.content}"
                       placeholder="내용입력">
            </div>
            <%--             수정 버튼 --%>
            <div class="mb-3">
                <button type="submit" class="btn btn-warning">수정</button>
            </div>
        </form>

        <%--        삭제 form 태그--%>
        <form action="/basic/faq/delete/${faq.fno}" method="post">
            <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  delete 방식으로 인식해서 연결해줌    --%>
            <input type="hidden" name="_method" value="delete"/>
            <button type="submit" class="btn btn-danger">삭제</button>
        </form>
    </div>
</div>
<%--    꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
