-- 11_Join_Exam.sql
-- 연습문제
-- 1) = 조인을 이용해서 SCOTT 사원의(ENAME)(조건) 부서번호와(DNO) 
--    부서이름을(DNAME) 출력하시오.
-- 대상 : EMPLOYEE(사원) EMP, DEPARTMENT(부서) DEP
-- 공통컬럼 : EMP.DNO = DEP.DNO
SELECT EMP.DNO, DEP.DNAME
FROM EMPLOYEE EMP, DEPARTMENT DEP
WHERE EMP.DNO = DEP.DNO
AND EMP.ENAME = 'SCOTT';

-- 2) 모든 사원의 사원이름과(ENAME) 그 사원이 소속된 부서이름(DNAME)과 
--   지역명(LOC)을 출력하시오
-- 사원테이블 : EMPLOYEE
-- 부서테이블 : DEPARTMENT 
-- 조인 : 공통컬럼 : 1) 이름이 똑같은 컬럼 + 자료형도 똑같은 컬럼
-- 부서:DNO(부서번호) , 사원:DNO(부서번호)
SELECT EMPLOYEE.ENAME, DEPARTMENT.DNAME, DEPARTMENT.LOC
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO;

-- 3) 10번 부서에(DNO) 속하는(조건) 사원(번호)에(ENO)(사원) 대해 직급과(JOB)(사원) 
--      지역명(LOC)(부서)을 출력하시오. 
-- 사원테이블 : EMPLOYEE
-- 부서테이블 : DEPARTMENT
-- 공통컬럼 : DNO(부서번호)
SELECT EMPLOYEE.JOB, DEPARTMENT.LOC
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
WHERE DNO = 10;

-- 4) 커미션을(COMMISSION) 받는 모든사원의(조건) 이름(ENAME), 
--      부서이름(DNAME), 지역명(LOC)을 출력하시오.
-- 힌트) 커미션 받는다 의미 : COMMISSION 이 NULL 이 아니다라는 의미
-- 공통컬럼 : DNO(부서번호)
SELECT EMPLOYEE.ENAME, DEPARTMENT.DNAME, DEPARTMENT.LOC
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
AND COMMISSION IS NOT NULL;

-- 5) = 조인과 Like 검색(와일드카드(%))를 사용하여 
--     이름에(ENAME) A가 포함된(조건) 모든 사원의 이름과(ENAME)
--    부서명을(DNAME) 출력하시오.
SELECT EMPLOYEE.ENAME, DEPARTMENT.DNAME
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
AND EMPLOYEE.ENAME LIKE '%A%';

-- 6) NEW YORK 에 근무하는(LOC)(부서, 조건) 모든 사원의 이름(ENAME), 
--     업무(JOB), 부서번호(DNO) 
--     및 부서명을(DNAME) 출력하시오.
-- 사원테이블 : EMPLOYEE
-- 부서테이블 : DEPARTMENT
SELECT EMPLOYEE.ENAME, EMPLOYEE.JOB, EMPLOYEE.DNO, DEPARTMENT.DNAME
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
AND DEPARTMENT.LOC = 'NEW YORK';

-- 고급 응용 문제(챌린지)
-- 7) 각 부서에 대해 부서번호(DNO)별, 부서명(DNAME)별, 위치(LOC)별로
--   사원수(COUNT), 부서 내의 모든 사원의 평균 급여(AVG)를 출력하세요
--   컬럼별칭을 사용해서 부서번호, 부서명, 위치, 사원수(COUNT),  출력하세요
-- 평균급여는 소수점 2째자리에서 반올림하세요.
-- 사원테이블 : EMPLOYEE EMP
-- 부서테이블 : DEPARTMENT DEP
-- 힌트 : 그룹함수 사용, GROUP BY 사용
-- 조인 : 공통컬럼으로 조인
SELECT DEP.DNO AS 부서번호, 
      DEP.DNAME AS 부서명, 
      DEP.LOC AS 위치, 
      COUNT(*) AS 사원수, 
      ROUND(AVG(EMP.SALARY),1) AS 평균급여
FROM EMPLOYEE EMP, DEPARTMENT DEP
WHERE EMP.DNO = DEP.DNO
GROUP BY DEP.DNO, DEP.DNAME, DEP.LOC;

-- 8) 각 부서명(DNAME)별, 급여별(SALARY) 사원수(COUNT)를 출력하세요
--   단, 부서명, 급여, 사원수의 별칭을 써서 출력하세요
-- 사원테이블 : EMPLOYEE
-- 부서테이블 : DEPARTMENT
SELECT DEPARTMENT.DNAME AS 부서명, EMPLOYEE.SALARY AS 급여, COUNT(*) AS 사원수
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
GROUP BY DEPARTMENT.DNAME, EMPLOYEE.SALARY;

-- 9) 각 부서명(DNAME)별, 담당업무별(JOB) 
--   급여(SALARY) 총액에서 5000 이상인 결과만(조건) 출력하세요
-- 사원테이블 : EMPLOYEE
-- 부서테이블 : DEPARTMENT
-- 힌트) HAVING 사용
SELECT DEPARTMENT.DNAME, EMPLOYEE.JOB, SUM(EMPLOYEE.SALARY) AS 급여총액
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
GROUP BY DEPARTMENT.DNAME, EMPLOYEE.JOB
HAVING SUM(EMPLOYEE.SALARY) >= 5000;

-- 10) 급여가(SALARY) 2000 초과인 사원들의(조건) 
--  부서번호(DNO), 부서명(DNAME), 사원번호(ENO), 사원명(ENAME), 급여를 출력하세요
-- 그룹핑 없음 : 그냥 조인 + 조건 쿼리
SELECT DEPARTMENT.DNO, DEPARTMENT.DNAME, EMPLOYEE.ENO, EMPLOYEE.ENAME, EMPLOYEE.SALARY
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
AND EMPLOYEE.SALARY > 2000;

-- 11) 각 부서별(DNO) 평균 급여(SALARY), 최대급여, 최소급여, 사원수를 출력해 보세요
SELECT EMPLOYEE.DNO, ROUND(AVG(EMPLOYEE.SALARY)) AS 평균급여, 
                    MAX(EMPLOYEE.SALARY) AS 최대급여, 
                    MIN(EMPLOYEE.SALARY) AS 최소급여, 
                    COUNT(*) AS 사원수
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO
GROUP BY EMPLOYEE.DNO;