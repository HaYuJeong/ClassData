-- 12_Subquery.sql(*****)
-- 서브쿼리 : sql 안에 sql 이 있는 명령문
-- 예제 1) SCOTT 사원보다 급여를 많이 받는 사원이 누구인지 출력
-- 1) SCOTT 사원이 얼마의 급여를 받는지 확인?
SELECT SALARY
FROM EMPLOYEE
WHERE ENAME = 'SCOTT';

-- 2) 그 숫자를 찾으면 그 숫자로 급여를 조회 : 3000
SELECT ENAME, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000;

-- => 개선 : 서브쿼리 사용
-- 사용법) SELECT 컬럼명
--        FROM 테이블명
--        WHERE 비교컬럼 > (SELECT 비교컬럼 FROM 테이블명)
--  주의점 : 1) 서브쿼리 사용시 위의 비교컬럼명은 일치해야함(좌/우)
--          2) (서브쿼리문) 사용 해야함
SELECT ENAME, SALARY
FROM EMPLOYEE
WHERE SALARY > (SELECT SALARY
                FROM EMPLOYEE
                WHERE ENAME = 'SCOTT');

-- 예제1) SCOTT 사원과(1개) 동일한 부서에서(DNO) 근무하는 사원(ENAME)(2개) 출력하기
-- 힌트) 1) 2개의 SQL 만들고 
--         1-1) SCOTT 사원의 부서번호를 찾기
SELECT DNO
FROM EMPLOYEE
WHERE ENAME = 'SCOTT';

--         1-2) 위의 부서번호로 다른 사원 찾기
SELECT ENAME
FROM EMPLOYEE
WHERE DNO = 20;

--       2) 1개로 합치기
SELECT ENAME
FROM EMPLOYEE
WHERE DNO = (SELECT DNO
            FROM EMPLOYEE
            WHERE ENAME = 'SCOTT');

-- 예제 2) 30번 부서에서 최소급여를(MIN) 구한 후) 2)그 최소 급여보다(MIN) 
-- 부서별(DNO) 최소 급여가 큰 부서만 출력하세요.
-- 서브쿼리를 사용하세요.
-- 1) 30번 부서에서 최소급여를 구한 후 : 950
SELECT MIN(SALARY)
FROM EMPLOYEE
WHERE DNO = 30;

-- 2) 그 최소 급여보다 부서별 최소 급여가 큰 부서 구하기
SELECT MIN(SALARY)
FROM EMPLOYEE
GROUP BY DNO
HAVING MIN(SALARY) > 950;

-- 3) 합치기
FROM EMPLOYEE
GROUP BY DNO
HAVING MIN(SALARY) > (SELECT MIN(SALARY)
                      FROM EMPLOYEE
                      WHERE DNO = 30);

-- 그 밖의 특수한 서브쿼리 사용 예시
-- 1) SELECT 예약어 다음에도 서브쿼리 사용가능 : (서브쿼리)
SELECT 2*3 , (SELECT 4*3 FROM DUAL)
FROM DUAL;

-- 2) FROM 예약어 다음에도 서브쿼리 사용 가능
SELECT EM.ENAME, EM.ENO
FROM (SELECT ENAME, ENO FROM EMPLOYEE) EM;

-- 4) 다중 행 서브쿼리 : 서브쿼리의 결과로 여러건이 나옴
-- 예제4) (1)부서별 최소 급여를 받는 사원 중에서 (2) 사원번호와 이름을 출력하세요
-- (1) 부서별(DNO) 최소 급여(MIN(SALARY)) : 
SELECT MIN(SALARY)
FROM EMPLOYEE
GROUP BY DNO;

SELECT ENO, ENAME
FROM EMPLOYEE
WHERE SALARY IN (SELECT MIN(SALARY)
FROM EMPLOYEE
GROUP BY DNO);

-- (2) 그 값들과 같은 급여를 받는 사원번호, 이름 출력 : 
-- 힌트 : IN (서브쿼리) [= 안됨]