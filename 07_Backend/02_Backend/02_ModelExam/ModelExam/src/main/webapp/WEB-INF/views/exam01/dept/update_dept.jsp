<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-14(014)
  Time: 오후 5:01
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
<%-- insert : post 방식   : @PostMapping --%>
<%-- update : put 방식    : @PutMapping (최근에 나와서 밑에 직접 추가해줘야 함)--%>
<%-- delete : delete 방식 : @DeleteMapping (최근에 나와서 밑에 직접 추가해줘야 함)--%>
<%-- select : get 방식    : @GetMapping --%>
<div class="container">
    <form action="" method="post">
        <%--            TODO: 아래 input(hidden) 사용하면 put 방식으로 전송됨 --%>
        <input type="hidden" name="_method" value="put"/>
        <%--            TODO: 상세조회 값을 input 태그에 출력 : value=값 --%>
        <%--            TODO: 부서번호(기본키) 숨김 : 수정불가 --%>
        <input type="hidden" name="dno" value="${dept.dno}"/>
        <%--        부서명  입력 양식 --%>
        <div class="mb-3">
            <label for="dname" class="form-label">dname</label>
            <input type="text"
                   class="form-control"
                   id="dname"
                   name="dname"
                   value="${dept.dname}"
                   placeholder="부서명입력">
        </div>
        <%--        부서위치 입력 양식 --%>
            <div class="mb-3">
                <label for="loc" class="form-label">loc</label>
                <input type="text"
                       class="form-control"
                       id="loc"
                       name="loc"
                       value="${dept.loc}"
                       placeholder="부서위치입력">
            </div>
        <%--            수정 버튼 --%>
            <div class="mb-3">
                <button type="submit" class="btn btn-warning">수정</button>
            </div>
    </form>
</div>
<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
