<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-22(022)
  Time: 오후 3:24
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
    <%--    검색어 --%>
    <%--        TODO: 검색어 입력 상자 --%>
    <form class="row g-3 justify-content-center" action="/advanced/fileDb" method="get">
        <%--        TODO: 검색 input 태그 : fileTitle--%>
        <div class="col-auto mt-5">
            <input type="text"
                   class="form-control"
                   id="fileTitle"
                   name="fileTitle"
                   placeholder="제목입력">
            <input type="hidden" id="page" name="page" value="0"/>
            <input type="hidden" id="size" name="size" value="3"/>
        </div>
        <%--        TODO: 검색 버튼--%>
        <div class="col-auto mt-5 mb-3">
            <button type="submit" class="btn btn-primary">검색</button>
        </div>
    </form>
    <%--    TODO: 부트스트랩 카드(반복문) --%>
    <%--    복습 : row : 행, col-숫자 : 열(숫자 : 총 12칸 중의 임의숫자) --%>
    <div class="row">
        <%--        TODO: forEach 반복문 시작 : 열 반복 --%>
        <c:forEach var="data" items="${fileDb}">
            <div class="col-3">
                <div class="card" style="width: 18rem;">
                        <%--  TODO: 카드 이미지 : http는 인터넷 주소로도 사진을 가지고 올 수 있음 : 인터넷 주소에 접근해서 이미지 가져옴--%>
                    <img src="${data.fileUrl}" class="card-img-top" alt="강의">
                    <div class="card-body">
                            <%--   TODO: 카드 제목 : 클릭하면 수정페이지 url로 가게 추가 --%>
                        <h5 class="card-title">
                            <a href="/advanced/fileDb/edition/$[data.uuid}">${data.fileTitle}</a>
                        </h5>
                            <%--   TODO: 카드 본문--%>
                        <p class="card-text">${data.fileContent}</p>
                            <%--   TODO: 삭제 버튼 : 클릭 시 삭제 : form 태그 추가 : delete 방식--%>
                        <form action="/advanced/fileDb/delete/${data.uuid}" method="post">
                                <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  delete 방식으로 인식해서 연결해줌    --%>
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%--    페이지 번호 --%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <%--        이전 버튼 : 시작페이지가 0이면 버튼 비활성화 아니면 활성화 --%>
            <li class="page-item ${(startPage+1==1)? 'disabled' : ''}">
                <a href="/advanced/fileDb?page=${startPage-1}&size=${3}&fileTitle=${fileTitle}" class="page-link">이전</a>
            </li>
            <%--       TODO: 반복문으로 실행 --%>
            <%--        사용법 : <c:forEach var="data" begin="시작값" end="끝값">반복문</c:forEach>--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link"
                                         href="/advanced/fileDb?page=${data}&size=${3}&fileTitle=${fileTitle}">${data+1}</a>
                </li>
            </c:forEach>

            <%--        다음 버튼 : 끝페이지가 전체페이지와 같으면 비활성화 아니면 활성화 --%>
            <li class="page-item ${(endPage+1==totalPages)? 'disabled' : ''}">
                <a href="/advanced/fileDb?page=${endPage+1}&size=${3}&fileTitle=${fileTitle}" class="page-link">다음</a>
            </li>
        </ul>
    </div>
    <%--    Add 버튼 --%>
    <div class="text-center">
        <a href="/advanced/fileDb/addition" class="btn btn-primary">Add</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp"/>
</body>
</html>