<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-25
  Time: 오후 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말--%>
<jsp:include page="../../common/header.jsp"/>
<%--    본문 --%>
<div class="container">
    <div>
        <%--      TODO: 파일업로드 : 전송형태: multipart/form-data 전송  --%>
        <form action="/advanced/fileDb/add"
              method="post"
              enctype="multipart/form-data"
        >
            <%--            TODO: 제목(fileTitle) --%>
            <div class="mb-3">
                <label for="fileTitle" class="form-label">fileTitle</label>
                <input type="text"
                       class="form-control"
                       id="fileTitle"
                       name="fileTitle"
                       placeholder="제목입력">
            </div>
            <%--            TODO: 내용(fileContent) --%>
            <div class="mb-3">
                <label for="fileContent" class="form-label">fileContent</label>
                <input type="text"
                       class="form-control"
                       id="fileContent"
                       name="fileContent"
                       placeholder="내용입력">
            </div>
            <%--            TODO: 파일 업로드 버튼 + 대화상자 (type=file) --%>
            <div class="input-group mb-3">
<%--                파일 선택(대화)상자 : 벡엔드 전송 --%>
                <input type="file"
                       class="form-control"
                       name="image"
                >
<%--            업로드(저장) 버튼 --%>
                <button class="btn btn-outline-secondary"
                        type="submit">업로드</button>
            </div>
        </form>
    </div>
</div>
<%--    꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
