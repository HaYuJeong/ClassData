<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-14(014)
  Time: 오후 5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 머리말 --%>
<jsp:include page="../../common/header.jsp"/>

<%-- 본문 : 수정/추가 : form/input 태그 이용 --%>
<div class="container">
    <form action="/exam01/member/edit/${member.eno}" method="post">
        <%--            TODO: 아래 input(hidden) 사용하면 put 방식으로 전송됨 --%>
        <input type="hidden" name="_method" value="put"/>
        <%--            TODO: 상세조회 값을 input 태그에 출력 : value=값 --%>
        <%--            TODO: 부서번호(기본키) 숨김 : 수정불가 --%>
        <input type="hidden" name="eno" value="${member.eno}"/>
        <%--        ename  입력 양식 --%>
        <div class="mb-3">
            <label for="ename" class="form-label">ename</label>
            <input type="text"
                   class="form-control"
                   id="ename"
                   name="ename"
                   value="${member.ename}"
                   placeholder="부서명입력">
        </div>
        <%--        job 입력 양식 --%>
        <div class="mb-3">
            <label for="job" class="form-label">job</label>
            <input type="text"
                   class="form-control"
                   id="job"
                   name="job"
                   value="${member.job}"
                   placeholder="부서위치입력">
        </div>
        <%--       manager 입력 양식 --%>
        <div class="mb-3">
            <label for="manager" class="form-label">manager</label>
            <input type="text"
                   class="form-control"
                   id="manager"
                   name="manager"
                   value="${member.manager}"
                   placeholder="부서위치입력">
        </div>
        <%--            수정 버튼 --%>
        <div class="mb-3">
            <button type="submit" class="btn btn-warning">수정</button>
        </div>
    </form>

    <%--    삭제 form--%>
    <form action="/exam01/member/delete/${member.eno}" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <%--            삭제 버튼 --%>
        <button type="submit" class="btn btn-danger">삭제</button>
    </form>
</div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
