<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-11
  Time: 오후 11:39
  To change this template use File | Settings | File Templates.
--%>
<%-- jsp 페이지 : html 에 기능을 확장해서 사용하는 페이지 --%>
<%-- 주석 단축키 : ctrl + / --%>
<%-- page : jsp 페이지임을 알려는 표현식 --%>
<%-- contentType="text/html;charset=UTF-8" : 문서 타입 --%>
<%-- language="java" : java 사용법을 이용한다는 표시 --%>
<%-- 내부 변형 : java 서블릿(웹페이지 표현) -> jsp(자동으로 서블릿 변환) --%>
<%-- jsp 파일 : xxx.jsp (확장자 항상 jsp 로 작성)  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--  부트스트랩 css cdn link  --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%--  jsp 에 부트스트랩을 추가하기 : head 태그에 추가 , cdn link --%>
<%--  예) 뷰 화면 출력 : 콧수염표기법 {{문자열}} --%>
<%--  예) jsp 화면 출력 : ${변수명} --%>
<%--  jsp el(Expression Language) 표현식 : ${변수명}  --%>
<%--    => 컨트롤러 함수에서 전송한 키 값을(변수명) 출력할 수 있음 --%>
<p>${greeting}</p>
<p>${greeting2}</p>
<p>${greeting3}</p>

</body>
</html>
