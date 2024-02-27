-- 파일명 : 02_Select_Where.sql
-- 검색 시 조건을 붙여 조회하기
-- 1) 비교연산자로 조건절(Where) 사용하기
-- 예제 1) 사원테이블(EMPLOYEE)에서 월급(SALARY) 이 1500 이상인 사원(ENAME) 조회하기
-- 조회 대상 컬럼 : ENAME(사원명 컬럼), SALARY(월급 컬럼)
-- 비교연산자 : > , < , >=, <=, =(같다), <>(같지않다)
SELECT ENAME, SALARY FROM EMPLOYEE
WHERE SALARY >= 1500;

-- 연습 1) 10번 부서(번호)의 소속 사원을 출력하세요(전체출력 : *)
-- 사원 테이블   : EMPLOYEE
-- 부서번호 컬럼 : DNO
-- 출력 컬럼     : *
SELECT * FROM EMPLOYEE
WHERE DNO = 10;

-- 연습 2) 사원테이블에서 월급이 5000 인 사람의 이름을 출력하세요
-- 사원 테이블   : EMPLOYEE
-- 월급 컬럼     : SALARY
-- 출력 컬럼     : 사원명(ENAME)
SELECT ENAME FROM EMPLOYEE
WHERE SALARY = 5000;

-- 예제 2) 사원명(ENAME) 이 SCOTT 사원을 출력하세요( 전체출력 )
-- 사원 테이블   : EMPLOYEE
-- 출력 컬럼     : *
SELECT * FROM EMPLOYEE
WHERE ENAME = 'SCOTT';

-- 연습 3) 사원명(ENAME) 이 ALLEN 사원을 출력하세요( 전체출력 )
-- 사원 테이블   : EMPLOYEE
-- 출력 컬럼     : *
-- 문자열 표현 : 홑따옴표 사용
SELECT * FROM EMPLOYEE
WHERE ENAME = 'ALLEN';

-- 연습 4) JOB(직위) 이 CLERK 인 사원을 출력하세요( 전체출력 )
-- 사원 테이블   : EMPLOYEE
-- 출력 컬럼     : *
-- 문자열 표현 : 홑따옴표 사용
SELECT * FROM EMPLOYEE
WHERE JOB = 'CLERK';

-- 예제 3) 입사일(HIREDATE)이 '1981/01/01' 이전(이하)인 사원만 출력하기 ( 전체출력 )
-- HIREDATE : 날짜 자료형 비교도 문자열과 비교하면 됨
SELECT * FROM EMPLOYEE
WHERE HIREDATE <= '1981/01/01';

SELECT * FROM EMPLOYEE
WHERE HIREDATE <= '81/01/01';

-- 연습 5) 입사일(HIREDATE) 이 '1987/01/01' 이상인 사원만 출력하기 (전체출력:*)
SELECT * FROM EMPLOYEE
WHERE HIREDATE >= '1987/01/01';

-- *) 논리 연산자와 함께 조건절(WHERE) 사용하기
-- 복습 : 논리 연산자 : ★ AND(논리곱, 이고, 그리고), OR(논리합, 이거나, 또는), NOT(부정, 반대)
-- 예제 4) 부서번호(DNO) 가 10이고(AND) 직급이(JOB) 'MANAGER' 사원을 출력하세요
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE DNO = 10
AND JOB = 'MANAGER';

-- 연습 6) 급여가(SALARY) 1000 과 1500 사이의 사원 조회하기(전체 조회 : *)
--         ( 1000 <= 급여 <= 1500 )
-- 사원 : EMPLOYEE
-- 비교연산자 : < , > 등
-- 논리연산자 : AND, OR, NOT
SELECT * FROM EMPLOYEE
WHERE SALARY >= 1000
AND   SALARY <= 1500;

-- 연습 7) 부서번호가(DNO) 10이거나(OR) 직급이(JOB) 'MANAGER' 인 사원만 출력하기
-- 사원 : EMPLOYEE
-- 비교연산자 : < , > 등
-- 논리연산자 : OR
SELECT * FROM EMPLOYEE
WHERE DNO = 10
OR JOB ='MANAGER';

-- 예제 5) 10번 부서에 소속된 사원을 제외하고(NOT) 나머지 사원 출력하기
-- 1) NOT 사용하는 방법 : 논리연산자
SELECT * FROM EMPLOYEE
WHERE NOT DNO = 10;

-- 2) <> (같지않다) 사용 : 비교연산자
SELECT * FROM EMPLOYEE
WHERE DNO <> 10;

-- 연습 8) 급여가(SALARY) 1000 미만이거나 1500 초과인 사원 출력하기
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE SALARY < 1000
OR SALARY > 1500;

-- 연습 9) 커미션(COMMISSION) 이 300 이거나(OR) 500 이거나(OR) 1400 인 사원 출력하기
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE COMMISSION = 300
OR COMMISSION = 500
OR COMMISSION = 1400;

-- *) BETWEEN 예약어 소개
-- 예제 6) 급여가(SALARY) 1000 과 1500 사이의 사원 조회하기
-- 사원 : EMPLOYEE
-- 1) 비교연산자
SELECT * FROM EMPLOYEE
WHERE SALARY >= 1000
AND   SALARY <= 1500;

-- 2) BETWEEN 사용(* : 실무)
-- 사용법) SELECT 컬럼명 FROM 테이블명 WHERE 컬럼명 BETWEEN 작은값 AND 큰값;
-- 사용 : 작은값 ~ 큰값 사이의 결과를 조회할때 사용
SELECT * FROM EMPLOYEE
WHERE SALARY BETWEEN 1000 AND 1500;

-- 연습 10) 부서번호(DNO)가 10 ~ 20 번 사이에 있는 
--          사원들을 출력하세요(전체조회: *), 단 BETWEEN ~ AND 사용하세요
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE DNO BETWEEN 10 AND 20;

-- *) NOT BETWEEN 예약어
-- 예제 7) 급여가(SALARY) 1000 미만이거나 1500 초과인 사원 조회하기
-- 1) 비교연산자 사용
SELECT * FROM EMPLOYEE
WHERE SALARY < 1000
OR    SALARY > 1500;

-- 2) NOT BETWEEN 사용
SELECT * FROM EMPLOYEE
WHERE SALARY NOT BETWEEN 1000 AND 1500;

-- 연습 11) 1982 년에 입사한(HIREDATE) 사원 조회하기
-- 단 BETWEEN ~ AND 사용해서 작성하세요
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN '1982/01/01' AND '1982/12/31';

-- *) IN 예약어
-- 예제 8) 상여금(COMMISSION) 300 이거나 500 이거나 1400 인 사원 조회하기
-- 사원 : EMPLOYEE
-- 1) 비교연산자 사용
SELECT * FROM EMPLOYEE
WHERE COMMISSION = 300
OR    COMMISSION = 500
OR    COMMISSION = 1400;

-- 2) IN 예약어 사용 (* : 실무)
-- 사용법) SELECT 컬럼명 FROM 테이블명
--         WHERE 컬럼명 IN (값,값2 ...);
--  위의 의미 : 컬럼명이 값이거나 값2이거나(OR) ...
SELECT * FROM EMPLOYEE
WHERE COMMISSION IN (300,500,1400);

-- 연습 12) 부서번호가(DNO) 10 이거나 20 인 사원들 조회하기(전체조회: *)
--        단, IN 예약어 사용하세요
-- 사원 테이블 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE DNO IN (10, 20);