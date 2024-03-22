<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-21(021)
  Time: 오전 10:45
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
        <%--        TODO: 저장(insert - post 방식 - @PostMapping) 버튼 누르면 "/basic/dept/add" url로 감--%>
        <form action="/basic/dept/edit/${dept.dno}" method="post">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="dno" value="${dept.dno}"/>

            <%--        input 태그 : 부서명  --%>
            <div class="mb-3">
                <label for="dname" class="form-label">dname</label>
                <input type="dname"
                       class="form-control"
                       id="dname"
                       name="dname"
                       value="${dept.dname}"
                       placeholder="부서명입력">
            </div>
            <%--        input 태그 : 부서위치 --%>
            <div class="mb-3">
                <label for="loc" class="form-label">loc</label>
                <input type="loc"
                       class="form-control"
                       id="loc"
                       name="loc"
                       value="${dept.loc}"
                       placeholder="부서위치입력">
            </div>
            <%--        input 태그 : 수정 버튼 --%>
            <form class="mb-3 d-flex justify-content-center" action="/dept/edit/{dno}">
                <button type="submit" class="btn btn-warning mb-3">수정</button>
            </form>
        </form>
        <%--        input 태그 : 삭제 버튼 --%>
        <form action="/basic/dept/delete/${dept.dno}" method="post">
            <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  delete 방식으로 인식해서 연결해줌    --%>
            <input type="hidden" name="_method" value="delete"/>
            <button type="submit" class="btn btn-danger mb-3">삭제</button>
        </form>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
