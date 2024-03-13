<%--
  Created by IntelliJ IDEA.
  User: hayj6
  Date: 2024-03-12
  Time: 오전 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <%-- bootstrap css cdn   --%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%-- 부트스트랩 복습 : container 클래스 : 가상의 페이지 중앙정렬  --%>
<div class="container">
  <h2>산술연산자 / 테스팅 </h2>
  <%--    부트스트랩 복습 : ms-3 클래스 : m(margin), s(start==left) : 왼쪽마진 --%>
  <%--    부트스트랩 복습 : mt-3 클래스 : m(margin), t(top) : 위쪽마진 --%>
  <div class="ms-3 mt-3">
    <p>문자열 : ${"문자열 출력됩니까? O "}</p>
    <%--            <p>문자열 더하기 : ${"문자열1" + "문자열2"} : X </p>--%>
    <p>${"문자열1"} ${"문자열2"}</p>
    <%--          문자열 "10" : 자동으로 숫자로 변환됨 --%>
    <p> 덧셈("10") : ${strNum + 10}</p>
    <%--            사칙연산 테스트 : O --%>
    <p> 덧셈(10) : ${iNum + 10}</p>
    <p> 뺄셈(10) : ${iNum - 10}</p>
    <p> 곱셈(10) : ${iNum * 10}</p>
    <p> 나눗셈(10) : ${iNum / 10}</p>
    <p> 나머지(10) : ${iNum % 10}</p>

    <%--            논리연산(||(or) , &&(and)) 테스트 : O --%>
    <p>논리합(or:true) : ${bVal || false} </p>
    <p>논리곱(and:true) : ${bVal && false} </p>

    <%--            비교연산(==(같다), !=(같지않다), > <) : --%>
    <p> 같음(10)      : ${iNum == 100} </p>
    <p> 같지않음(10)   : ${iNum != 100} </p>
    <p> 크거나같음(10) : ${iNum >= 100} </p>
    <p> 작거나같음(10) : ${iNum <= 100} </p>

    <%--            3항연산자(조건문) : (조건식)? "실행1": "실행2"--%>
    <p> 조건(3항연산자:10) : ${(iNum == 100)? "100" : "100 이 아님"}</p>

    <%--            jsp 특수 연산자(예약어) : empty : 변수의 값이 비었으면 true, 아니면 false --%>
    <%--                비었음 : null, 0, "" 이런 것들 --%>
    <%--            jsp 특수 연산자(예약어) : not empty : 변수의 값이 안비었으면 true, 아니면 false --%>
    <p> empty     : ${empty iNum}</p>
    <p> not empty : ${not empty iNum}</p>

  </div>

</div>
</body>
</html>