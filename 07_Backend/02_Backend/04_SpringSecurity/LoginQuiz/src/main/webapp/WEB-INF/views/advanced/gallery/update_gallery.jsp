<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-26(026)
  Time: 오전 10:02
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
        <form action="/advanced/gallery/edit/${gallery.uuid}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="uuid" value="${fileDb.uuid}">

            <%--            제목 : galleryTitle --%>
            <div class="mb-3">
                <label for="galleryTitle" class="form-label">galleryTitle</label>
                <input type="text"
                       class="form-control"
                       id="galleryTitle"
                       name="galleryTitle"
                       required
                       value="${gallery.galleryTitle}"
                       placeholder="제목입력">
            </div>

            <%--            기존 이미지 보기 --%>
            <div class="mb-3 col-12" style="width: 18rem;">
                <img src="${gallery.galleryFileUrl}"
                     class="card-img-top"
                     alt="강의"/>
            </div>

            <%--            업로드 버튼 --%>
            <div class="input-group">
                <input type="file"
                       class="form-control"
                       name="image"
                       required>
                <button class="btn btn-outline-secondary"
                        type="submit">Button
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>