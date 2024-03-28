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
        <form action="/basic/emp/edit/${emp.eno}" method="post">
            <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  put 방식으로 인식해서 연결해줌    --%>
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="eno" value="${emp.eno}"/>
            <%--            input 태그 : 사원명 --%>
            <div class="mb-3">
                <label for="ename" class="form-label">ename</label>
                <input type="text"
                       class="form-control"
                       id="ename"
                       name="ename"
                       value="${emp.ename}"
                       placeholder="사원명입력">
            </div>
            <%--            input 태그 : 직위 --%>
            <div class="mb-3">
                <label for="job" class="form-label">job</label>
                <input type="text"
                       class="form-control"
                       id="job"
                       name="job"
                       value="${emp.job}"
                       placeholder="직위입력">
            </div>
            <%--            input 태그 : 관리자사원번호 --%>
            <div class="mb-3">
                <label for="manager" class="form-label">manager</label>
                <input type="text"
                       class="form-control"
                       id="manager"
                       name="manager"
                       value="${emp.manager}"
                       placeholder="관리자사원번호입력">
            </div>
            <%--            input 태그 : 입사일 --%>
            <div class="mb-3">
                <label for="hiredate" class="form-label">hiredate</label>
                <input type="text"
                       class="form-control"
                       id="hiredate"
                       name="hiredate"
                       value="${emp.hiredate}"
                       placeholder="입사일입력">
            </div>
            <%--            input 태그 : 급여 --%>
            <div class="mb-3">
                <label for="salary" class="form-label">salary</label>
                <input type="text"
                       class="form-control"
                       id="salary"
                       name="salary"
                       value="${emp.salary}"
                       placeholder="급여입력">
            </div>
            <%--            input 태그 : 상여금 --%>
            <div class="mb-3">
                <label for="commission" class="form-label">commission</label>
                <input type="text"
                       class="form-control"
                       id="commission"
                       name="commission"
                       value="${emp.commission}"
                       placeholder="상여금입력">
            </div>
            <%--            input 태그 : 부서번호 --%>
            <div class="mb-3">
                <label for="dno" class="form-label">dno</label>
                <input type="text"
                       class="form-control"
                       id="dno"
                       name="dno"
                       value="${emp.dno}"
                       placeholder="부서번호입력">
            </div>
            <%--             수정 버튼 --%>
            <div class="mb-3">
                <button type="submit" class="btn btn-warning">수정</button>
            </div>
        </form>

        <%--        삭제 form 태그 --%>
        <form action="/basic/emp/delete/${emp.eno}" method="post">
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
