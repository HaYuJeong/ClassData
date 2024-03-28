<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-26
  Time: 오전 10:01
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
<%--    본문--%>
<div class="container">
    <div>
        <form action="/advanced/fileDb/edit/${fileDb.uuid}"
              method="post"
              enctype="multipart/form-data">
            <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  put 방식으로 인식해서 연결해줌    --%>
            <input type="hidden" name="_method" value="put"/>

            <%--            hidden : uuid 전송 -> spring(벡엔드)     --%>
            <input type="hidden" name="uuid" value="${fileDb.uuid}">
            <%--            제목 : fileTitle --%>
            <div class="mb-3">
                <label for="fileTitle" class="form-label">fileTitle</label>
                <input type="text"
                       class="form-control"
                       id="fileTitle"
                       name="fileTitle"
                <%--                      required : 필수 입력 (유효성 체크) --%>
                       required
                <%--                      value     : 화면에 값이 보이는 속성 --%>
                       value="${fileDb.fileTitle}"
                       placeholder="제목입력">
            </div>
            <%--            내용 : fileContent --%>
            <div class="mb-3">
                <label for="fileContent" class="form-label">fileContent</label>
                <input type="text"
                       class="form-control"
                       id="fileContent"
                       name="fileContent"
                <%--                      required : 필수 입력 (유효성 체크) --%>
                       required
                <%--                      value     : 화면에 값이 보이는 속성 --%>
                       value="${fileDb.fileContent}"
                       placeholder="내용입력">
            </div>
            <%--                기존 이미지 보기 --%>
            <div class="mb-3 col-12" style="width: 18rem;">
                <img src="${fileDb.fileUrl}"
                     class="card-img-top"
                     alt="강의"
                />
            </div>
            <%--            업로드버튼--%>
            <div class="input-group">
                <input type="file"
                       class="form-control"
                       name="image"
<%--                   유효성체크 : 필수입력 : required   --%>
                       required
                >
                <button class="btn btn-outline-secondary"
                        type="submit"
                >Button</button>
            </div>
        </form>
    </div>
</div>
<%--    꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
