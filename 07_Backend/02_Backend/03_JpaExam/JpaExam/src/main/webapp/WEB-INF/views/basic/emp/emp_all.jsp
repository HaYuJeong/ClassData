<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-19(019)
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../common/header.jsp" />

<div class="container">
  ${list}
</div>

<%--    TODO: Add 버튼--%>
<div class="text-center">
    <a href="/basic/emp/add" class="btn btn-primary">Add</a>
</div>

<jsp:include page="../../common/footer.jsp" />
</body>
</html>
