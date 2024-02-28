-- 04_Function_1.sql
-- 04_Function_1.sql
-- 오라클 내장 함수들 소개
-- 1) 문자 함수
-- 사용법) 대문자 변환 : UPPER('문자열') 또는 UPPER(컬럼명)
--        소문자 변환 : LOWER('문자열') 또는 LOWER(컬럼명)
--        첫글자대문자 변환 : INITCAP('문자열') 또는 INITCAP(컬럼명)
-- 예제 1) 대문자/소문자/1글자 대문자로 바꾸세요
-- 테스트 테이블 : DUAL
-- 복습 : SQL 자료형 : 문자열('글자'), 숫자(1,2)
SELECT 'Oracle mania'
        , UPPER('Oracle mania') AS 대문자
        , LOWER('Oracle mania') AS 소문자
        , INITCAP('Oracle mania') AS 첫글자대문자
FROM DUAL;

-- 연습 1) 사원테이블에서(EMPLOYEE) 사원명(ENAME) 을 소문자로 출력하고,
--        직위(JOB) 은 첫글자만 대문자로 출력하세요
SELECT LOWER(ENAME), INITCAP(JOB) FROM EMPLOYEE;

-- 연습 2) SCOTT 사원을 조회해서 사원번호(ENO), 사원명(ENAME), 부서번호(DNO) 출력하기
-- 단, 사원명이 대소문자 구분없이 조건절에 사용된다고 가정하고 작성하세요
SELECT ENO, ENAME, DNO FROM EMPLOYEE
WHERE ENAME = UPPER('SCOTT');  -- 만약 소문자 scott으로 들어오면 찾을수없어서 UPPER 사용

-- 1-2) 문자의 길이를 출력하는 함수
-- DUAL 
-- 사용법) 문자열의 길이 : LENGTH('문자열') 또는 LENGTH(컬럼명)
SELECT LENGTH('Oracle mania')
    ,LENGTH('오라클 매니아')
FROM DUAL;

-- 1-3) 문자 조작 함수 : 문자열 붙이기 함수 또는 예약어
-- DUAL
SELECT 'Oracle'
    ,'mania'
    , CONCAT('Oracle','mania') AS 함수
    , 'Oracle' || 'mania'
FROM DUAL;

-- 1-4) 문자 조작 함수 : 문자열 자르기 함수
-- SQL : 인덱스번호 : 1부터 시작
-- 사용법) 문자열 자르기 함수 : SUBSTR('문자열', 시작위치, 자를개수)
--    단, 시작위치가 음수이면 뒤에서 셈
SELECT SUBSTR('Oracle mania', 4, 3)
    , SUBSTR('Oracle mania', -1, 1)
FROM DUAL;

-- 1-5) 문자열에서 대상 문자를 찾아서 인덱스번호를 출력하는 함수
-- SQL 인덱스번호 : 1부터 시작
-- 사용법) 문자열에서 특정문자를 찾아 인덱스번호를 출력 : 
--         INSTR('문자열', '특정문자') 또는 INSTR(컬럼명, '특정문자')
SELECT INSTR('Oracle mania', 'a')
    ,INSTR('오라클매니아', '라')
FROM DUAL;

-- 1-6) 컬럼에 어떤 문자를 (왼쪽/오른쪽)붙이기 함수 : LPAD, RPAD
-- LPAD 함수
-- 사용법) 왼쪽 채움 : LPAD(컬럼명, 자리수, '채울문자')
SELECT LPAD(SALARY, 10, '*')
FROM EMPLOYEE;
-- RPAD 함수
-- 사용법) 오른쪽 채움 : RPAD(컬럼명, 자리수, '채울문자')
SELECT RPAD(SALARY, 10, '*')
FROM EMPLOYEE;

-- 1-7) 문자열의 양옆에 공백 제거하기 함수 : LTRIM, RTRIM, TRIM
-- 사용법)
SELECT 'Oracle mania'
    , LTRIM('  Oracle mania  ')
    , RTRIM('  Oracle mania  ')
    , TRIM('  Oracle mania  ')
FROM DUAL;

-- 연습 1) SUBSTR 함수를 사용하여 사원들의 
--  입사한 년도와(HIREDATE) 입사한 달만(HIREDATE) 출력하세요.
-- 사용법) SUBSTR(컬럼명, 인덱스위치, 잘라낼개수) 
-- : 문자열 자르기 함수, 날짜형 컬럼도 자르기가 가능
-- SQL 인덱스 번호 : 1 ~ N
-- 사원테이블 : EMPLOYEE
SELECT SUBSTR(HIREDATE, 1, 2), SUBSTR(HIREDATE, 4,2)
FROM EMPLOYEE;