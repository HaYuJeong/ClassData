-- 08_Group_Func.sql (*)
-- 그룹 함수 : 데이터를 전체 또는 부분 집계하는 함수
-- 예) 총액, 평균, 최고값, 최소값, 총개수 함수 
-- 1) 전체 그룹 함수 : 테이블의 특정 컬럼의 전체 데이터에 대해 집계를 냄
-- 2) 특징 : 항상 1건이 결과로 나옴
-- 사용법) SUM(컬럼명) : 총액
--         AVG(컬럼명) : 평균
--         MAX(컬럼명) : 최대값
--         MIN(컬럼명) : 최소값
--         COUNT(컬럼명) : 전체 데이터 개수
-- 예제 1) 사원들의 급여(SALARY) 총액, 평균, 최고, 최소액을 출력
SELECT SUM(SALARY) AS 총액
    , ROUND(AVG(SALARY))  AS 평균
    , MAX(SALARY)         AS 최고액
    , MIN(SALARY)         AS 최소액
FROM EMPLOYEE;

-- 연습 1) 최근에 입사한(HIREDATE) 사원과(EMPLOYEE) 
--        가장 오래전에 입사한 사원의 입사일 출력하기
-- 힌트) 위의 집계함수들중 2개 사용해야함
-- 최근 입사일 : MAX(입사일)
-- 오래전 입사일 : MIN(입사일)
SELECT MAX(HIREDATE)
    , MIN(HIREDATE)
FROM EMPLOYEE;

-- 예제2) 사원들의(EMPLOYEE) 상여금(COMMISSION) 총액 출력하기
SELECT SUM(COMMISSION) FROM EMPLOYEE;

-- 예제3) 사원들의(EMPLOYEE) 총인원을 출력하세요
-- 사용법) COUNT(컬럼명[*])
SELECT COUNT(*) AS 사원수
FROM EMPLOYEE;

-- 예제4) 상여금을(COMMISSION) 받는 사원의 수 계산하기
-- 힌트) 기본적으로 그룹함수는 NULL 값은 제외하고 계산됨
SELECT COUNT(COMMISSION) AS 사원수
FROM EMPLOYEE;

-- 예제5) 상여금을(COMMISSION) 못받는 사원수 구하기
--  (상여금이 NULL 인 데이터만 계산하기)
SELECT COUNT(*) AS 사원수
FROM EMPLOYEE
WHERE COMMISSION IS NULL;

-- 예제 6) 직위(JOB)의 종류가 몇개인지 출력하기
-- 사원 : EMPLOYEE
-- 중복제거 예약어 : DISTINCT 
-- 사용법) 중복제거 COUNT : COUNT(DISTINCT 컬럼)
SELECT COUNT(DISTINCT JOB) AS 직업개수
FROM EMPLOYEE;

-- 전체 그룹함수 사용 시 주의점) 1) 결과는 1건
--                             2) 일반 컬럼과 같이 사용불가
-- SELECT ENAME, MAX(SALARY)
-- FROM EMPLOYEE;             -- X (일반컬럼(ENAME), MAX 안됨)

-- 2) 부분 집계 하기 : GROUP BY (예약어)
-- 사용법) SELECT 컬럼,컬럼2, 컬럼3,... 그룹함수(컬럼)
--        FROM 테이블명
--        GROUP BY 컬럼,컬럼2, 컬럼3, ...
-- 예제 7) 소속 부서별(DNO) 평균 급여를(SALARY) 부서번호와 함계 출력
-- 평균 : 버림(절삭)
SELECT DNO 
    ,TRUNC(AVG(SALARY)) AS 평균급여
FROM EMPLOYEE
GROUP BY DNO;

-- 예제 8) 부서번호별(DNO), 직위별(JOB) 데이터 건수(COUNT) 및 급여(SALARY) 총액(SUM) 구하기
-- 사원 : EMPLOYEE
-- 힌트 : GROUP BY DNO, JOB
SELECT DNO, JOB, COUNT(*), SUM(SALARY)
FROM EMPLOYEE
GROUP BY DNO, JOB;

-- *) 그룹함수에서 조건절 사용하기 : HAVING (그룹함수만 사용가능)
-- 사용법) HAVING 그룹함수(컬럼명) >= 값 (조건식)
-- 예제 9) 부서번호별(DNO) 최고급여가(SALARY) 3000 이상인 부서의 번호(DNO)와
--       최고급여금액 구하기
--     1) 부서번호별(DNO) 최고급여 : ?
--     2) 최고급여를 각각 비교해서 3000 이상인 값만 화면 출력
SELECT DNO, MAX(SALARY)
FROM EMPLOYEE
GROUP BY DNO
HAVING MAX(SALARY) >= 3000;

-- 연습 1) 사원테이블에서(EMPLOYEE) JOB(직위)이 1) MANAGER 인
--       값을 제외하고 3) 급여(SALARY) 총액(SUM)이 5000 이상인
--       2) 직급별(JOB) 총액 구하기
-- 힌트) 1) MANAGER 인 값 제외 : WHERE 컬럼 NOT LIKE '%문자%'
--       2) 직급별(JOB) 총액 구하기
--       3) 급여(SALARY) 총액(SUM)이 5000 이상이 사원(조건2) : HAVING
SELECT JOB, SUM(SALARY)
FROM EMPLOYEE
WHERE JOB NOT LIKE '%MANAGER%'
GROUP BY JOB
HAVING SUM(SALARY) >= 5000;