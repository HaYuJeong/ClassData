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
    <form action="/advanced/gallery/edit/${gallery.uuid}"
          method="post"
          enctype="multipart/form-data">
      <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  put 방식으로 인식해서 연결해줌    --%>
      <input type="hidden" name="_method" value="put"/>

      <%--            hidden : uuid 전송 -> spring(벡엔드)     --%>
      <input type="hidden" name="uuid" value="${gallery.uuid}">
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
      <%--                기존 이미지 보기 --%>
      <div class="mb-3 col-12" style="width: 18rem;">
        <img src="${gallery.galleryFileUrl}"
             class="card-img-top"
             alt="강의"
        />
      </div>
      <%--            업로드버튼--%>
      <div class="input-group">
        <input type="file"
               class="form-control"
               name="image"
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
