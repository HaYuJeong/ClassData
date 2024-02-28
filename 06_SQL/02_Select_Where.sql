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

-- *) NOT IN 예약어
-- 예제 9) 상여금(COMMISSION) 300 이 아니고, 
--          500 이 아니고, 1400 아닌 사원 조회하기
-- => 반대 : Not (300 이거나(OR) 500 이거나 1400 이거나)
-- 사원테이블 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE COMMISSION NOT IN (300, 500, 1400);


-- *) LIKE 검색(*****)
-- 의미 : 일부 키워드(영어, 한글)만 사용해서 비슷한 것들만 조회하기
--  예) 이름이 K 시작하는 사원들 조회
-- 예제 10) 이름이(ENAME) 'F' 로 시작하는 사원 조회하기(전체조회:*)
-- 사원 테이블 : EMPLOYEE
-- 사용법) SELECT 컬럼명,... FROM 테이블명
--        WHERE 컬럼명 LIKE '%문자%';
-- % : 아무 글자나 올 수 있음의 의미, 문자를 기준으로 앞/뒤 사용가능
SELECT * FROM EMPLOYEE
WHERE ENAME LIKE 'F%';

-- 연습 13) 이름에 'M' 이 포함되어 있는 사원 조회하기(전체 조회)
-- 사원 : EMPLOYEE
-- 사원명 컬럼 : ENAME
-- 결과 : 5건
SELECT * FROM EMPLOYEE
WHERE ENAME LIKE '%M%';

-- 연습 14) 이름이 'N' 으로 끝나는 사원 조회하기(전체 조회)
-- 사원 : EMPLOYEE
-- 사원명 컬럼 : ENAME
-- 결과 : 2건
SELECT * FROM EMPLOYEE
WHERE ENAME LIKE '%N';

-- 예제 11) (참고) 이름(ENAME) 의 두번째 글자가 'A'인 사원 조회하기
-- LIKE 기호 : % (어떤 문자열을 의미)
--             _ (어떤 1문자를 의미)
-- 사원 테이블 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE ENAME LIKE '_A%';

-- 연습 15) 이름의 세번째 글자가 'A' 인 사원 조회하기
-- 사원 테이블 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE ENAME LIKE '__A%';

-- *) NOT LIKE 예약어(*)
-- BETWEEN, IN 앞에 NOT 부정의 의미, 마찬가지로 LIKE 앞에 NOT 붙이면 반대(부정)의 의미
-- 예제 11) 이름에 'A' 가 포함되지 않는 사원을 조회
-- 사원 테이블 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE ENAME NOT LIKE '%A%';

-- *) NULL 검색(조회)
-- NULL : SQL 에서 NULL 은 아무것도 없는 값, 연산이 안됨
-- 예제 12) 상여금(COMMISSION) 이 NULL 인 사원을 조회
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
WHERE COMMISSION = NULL; -- X (결과 없음)

-- 1) IS NULL , IS NOT NULL 예약어
-- IS NULL     : NULL 인 정보만 조회
-- IS NOT NULL : NULL 이 아닌 정보만 조회
-- 수정
SELECT * FROM EMPLOYEE
WHERE COMMISSION IS NULL; -- O (결과 나옴) 

-- 예제 13) 상여금(COMMISSION) 이 NULL 이 아닌 사원을 조회
SELECT * FROM EMPLOYEE
WHERE COMMISSION IS NOT NULL;

-- *) ORDER BY : 정렬 기능
-- 사용 : 속도 저하
-- 예제 14) 사원(EMPLOYEE) 테이블 오름차순 정렬하기
--  단, 월급(SALARY)으로 정렬하세요
-- 오름차순 : 작은순부터 큰순으로 정렬하는것
-- 내림차순 : 큰순부터 작은순으로 정렬하는것
-- 사용법) SELECT * FROM 테이블명
--         ORDER BY 컬럼명 ASC[DESC];
-- ASC  : asending  , 오름차순, 생략가능
-- DESC : desending , 내림차순
SELECT * FROM EMPLOYEE
ORDER BY SALARY ASC; -- ASC(생략가능)

-- 예제 15) 사원(EMPLOYEE) 테이블 내림차순 정렬하기
--  단, 월급(SALARY)으로 정렬하세요
SELECT * FROM EMPLOYEE
ORDER BY SALARY DESC; -- DESC (생략불가)

-- 연습 16) 사원 테이블에서(EMPLOYEE) 사원명으로(ENAME) 오름차순 정렬해서 조회하세요
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
ORDER BY ENAME ASC;

-- 연습 17) 입사일(HIREDATE) 데이터로 내림차순 정렬하세요
-- 사원 : EMPLOYEE
SELECT * FROM EMPLOYEE
ORDER BY HIREDATE DESC;

-- 연습 9) 사원테이블에서(EMPLOYEE) 급여는(SALARY) 내림차순으로 정렬하고,
--         사원명은(ENAME) 오름차순으로 정렬하세요
-- 사용법 응용) ORDER BY 컬럼명 ASC[DESC], 컬럼명2 ASC[DESC] ...
SELECT * FROM EMPLOYEE
ORDER BY SALARY DESC, ENAME ASC;