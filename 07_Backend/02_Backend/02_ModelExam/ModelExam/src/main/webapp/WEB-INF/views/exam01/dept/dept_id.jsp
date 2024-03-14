<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-14(014)
  Time: 오전 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--머리말--%>
<jsp:include page="../../common/header.jsp" />

<%--본문 : 부트스트랩 테이블 --%>
<div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">dno</th>
        <th scope="col">dname</th>
        <th scope="col">loc</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${dept.dno}</td>
        <td>${dept.dname}</td>
        <td>${dept.loc}</td>
    </tr>
    </tbody>
</table>
</div>


<script>
    <%--    js 코딩 부분 --%>
    let obj = "${dept}";
    console.log(obj);
</script>

<%--꼬리말--%>
<jsp:include page="../../common/footer.jsp" />
</body>
</html>
