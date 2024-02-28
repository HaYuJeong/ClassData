-- 05_Function_2.sql
-- 숫자 함수 / 날짜 함수
-- 숫자 함수
-- 1) 반올림 : ROUND()
-- 사용법) ROUND(숫자, 자리수) 또는 ROUND(컬럼, 자리수)
-- 참고) 자리수가 음수일경우 정수 1st 자리부터 반올림이 됨
SELECT 98.7654
    , ROUND(98.7654)     -- 소수점 1st 자리에서 반올림
    , ROUND(98.7654, 2)  -- 소수점 3rd 자리에서 반올림
    , ROUND(98.7654, 1)  -- 소수점 2nd 자리에서 반올림
    , ROUND(98.7654, -1) -- 정수 1st 자리에서 반올림
FROM DUAL;

-- 2) 버림 : TRUNC()
-- 사용법) TRUNC(숫자, 자리수) 또는 TRUNC(컬럼, 자리수)
SELECT 98.7654
    , TRUNC(98.7654) 
    , TRUNC(98.7654, 2) 
    , TRUNC(98.7654, -1)
FROM DUAL; 

-- 3) (참고) 내림 : FLOOR()
SELECT 98.7654
    , FLOOR(98.7654)
FROM DUAL;

-- 4) 올림 : CEIL()
-- 사용법) CEIL(숫자) 또는 CEIL(컬럼) 
SELECT 98.7654
    ,CEIL(98.7654)
FROM DUAL;

-- 5) 나머지 연산 함수 : MOD()
-- 사용법) 
SELECT MOD(31, 2)
FROM DUAL;

-- 연습 1) 모든 사원의 급여를(SALARY) 각각 500으로 나눈 나머지를 계산해서 출력하세요
-- 사원 : EMPLOYEE
-- 함수 : MOD() 이용
SELECT MOD(SALARY, 500) FROM EMPLOYEE;

-- 날짜함수 (**)
-- 1) 현재 날짜보기 함수(*****) : SYSDATE
SELECT SYSDATE FROM DUAL;

-- 예제 1) 오늘, 어제, 내일 날짜를 출력해보세요
SELECT SYSDATE    AS 오늘
    , SYSDATE -1  AS 어제
    , SYSDATE +1  AS 내일
FROM DUAL;

-- 예제 2) 사원테이블에서 근무일수 계산해서 조회하기
-- 공식 : 현재시간 - 입사일(HIREDATE) : 근무일수
-- 사원 : EMPLOYEE
SELECT ROUND(SYSDATE - HIREDATE) FROM EMPLOYEE;