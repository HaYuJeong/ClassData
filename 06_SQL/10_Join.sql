-- 10_Join.sql
-- 조인(*****) : 
-- why? 2개 이상의 sql 문을 1개의 문장으로 속도를 증진시키는 방법
--   2개 이상 sql 문 단점 : 1) 2번 실행되므로 속도저하
--                         2) 네트웍 통해 db 서버로 전송됨
-- 예제1) 사원번호가(ENO) 7788 인 사원의 부서명은(DNAME) 뭘까요?
-- 사원 : EMPLOYEE
-- 부서 : DEPARTMENT
SELECT * 
FROM EMPLOYEE
WHERE ENO = 7788;
-- 결과 : 부서명 없음, 부서번호 있음(DNO) => 부서테이블을 또 조회 필요
-- 부서번호 : 20

SELECT *
FROM DEPARTMENT
WHERE DNO = 20;

-- *) 조인 방법 : 
--   1) 이퀄(equal, =(같음))조인
--   2) 2개의 테이블을 같은 데이터끼리 연결하는 방법
--  특징 : 데이터 건수가 작은 테이블이 데이터 중복이 발생함
--         (조인된 총건수는 큰 테이블의 데이터 건수와 같음)
-- 사용법 : SELECT 테이블1.*, 테이블2.*
--          FROM 테이블1
--              ,테이블2
--          WHERE 테이블1.공통컬럼 = 테이블2.공통컬럼;
SELECT EMPLOYEE.*, DEPARTMENT.*
FROM EMPLOYEE
    ,DEPARTMENT
WHERE EMPLOYEE.DNO = DEPARTMENT.DNO;

-- 2) 컬럼 별칭(별명) : 컬럼명 AS 별명
--  테이블 별칭(별명) : FROM 테이블명 별명
SELECT EM.*, DE.*
FROM EMPLOYEE   EM
    ,DEPARTMENT DE
WHERE EM.DNO = DE.DNO
AND   EM.ENO = 7788;

-- 연습 1) 사원번호가 7499 인 사원들에 소속된 부서정보를 모두 출력하세요
-- 부서 : DEPARTMENT
-- 사원 : EMPLOYEE
-- 부서컬럼 : 부서번호(DNO), 부서명(DNAME), 부서위치(LOC)
SELECT EM.*, DE.*
FROM EMPLOYEE   EM
    ,DEPARTMENT DE
WHERE EM.DNO = DE.DNO
AND   EM.ENO = 7499;

-- 연습 2) 사원번호가 7499 또는 7900 인 사원들에 소속된 부서정보를 모두 출력하세요
-- 부서 : DEPARTMENT
-- 사원 : EMPLOYEE
-- 부서컬럼 : 부서번호(DNO), 부서명(DNAME), 부서위치(LOC)
-- 힌트 : 컬럼명 IN ()
-- 풀이 1) 추천
SELECT EM.*, DE.*
FROM EMPLOYEE   EM
    ,DEPARTMENT DE
WHERE EM.DNO = DE.DNO
AND   EM.ENO IN (7499, 7900);

-- 풀이 2) 참고 : 연산자 우선순위 때문에 () 사용
SELECT EM.*, DE.*
FROM EMPLOYEE   EM
    ,DEPARTMENT DE
WHERE EM.DNO = DE.DNO
AND   (EM.ENO = 7499 OR EM.ENO = 7900);

-- 연습 3) 조인하고 아래 조건을 추가하세요
--     1-1) 사원번호가(테이블명.ENO) 7500 ~ 7700 사이에 있는 사원들의 소속된 부서정보를 출력하되
--      조건(범위) : 테이블명.컬럼명 BETWEEN A AND B
--     1-2) 부서이름이(테이블명.DNAME) SALES 인 부서만 출력하세요
-- 대상 : EMPLOYEE(사원), DEPARTMENT(부서)
-- 부서컬럼 : 부서번호(DNO), 부서명(DNAME), 부서위치(LOC)
-- 공통컬럼 : 1) 이름이 같거나 데이터 형태가 같은 컬럼 : DNO(부서번호)
SELECT EM.*, DE.*
FROM EMPLOYEE   EM
    ,DEPARTMENT DE
WHERE EM.DNO = DE.DNO
AND   EM.ENO BETWEEN 7500 AND 7700
AND   DE.DNAME = 'SALES';

-- (참고) 그 밖에 특수한 조인들
-- 1) 조건없이(공통컬럼 조인없이) 조인하기 : 카티션 프로덕트(곱(하기) 조인)
-- 단점 : 건수가 늘어나서 성능 대폭 저하 (테이블1 * 테이블2 = 14 * 4 = 56)
SELECT EM.*, DE.*
FROM EMPLOYEE   EM
    ,DEPARTMENT DE;

-- 2) 범위 조인 : BETWEEN A AND B (A ~ B 사이의 값만 조인)
-- 단점 : 이퀄조인보다 건수가 늘어나서 성능 저하
-- 예제 2) 급여 등급 테이블(SALGRADE)의 낮은 등급(LOSAL) ~ 높은 등급(HISAL) 사이의 
--         급여에 대해서 사원테이블과 조인하여 사원정보를 화면에 출력
-- 사용법) SELECT 테이블1.*, 테이블2.*
--         FROM 테이블1
--            , 테이블2
--         WHERE 테이블1.공통컬럼 BETWEEN 컬럼1 AND 컬럼2;
-- 공통컬럼 : 데이터 성격이 같은 컬럼 : 사원컬럼(SALARY) , 등급컬럼(LOSAL, HISAL)
SELECT ENAME, SALARY, GRADE
FROM EMPLOYEE EM
    ,SALGRADE SA
WHERE EM.SALARY BETWEEN SA.LOSAL AND SA.HISAL;

-- 3) 아우터 조인(OUTER JOIN)(*) : 
-- 이퀄조인(=) : 두 테이블의 데이터가 같은 성격을 가진것을(공통컬럼) 연결하는것
-- 특징 : 두 테이블에 공통컬럼에 null 값이 있으면 null 은 제외하고 연결됨
--   예) 부서(Department, dno(부서번호)), 사원(Employee, dno(부서번호))
-- 아우터 조인 사용 why? 조인을 하되 null 값을 포함해서 조회하고 싶을때 사용
-- 예제 4) 같은 테이블을 조인할 수 있음(셀프 조인)
-- 용도 : NULL을 포함한 데이터도 화면에 표시하고 싶을 때 사용
-- 사용법 : 
SELECT EM.ENAME
    ,MA.ENAME 
FROM EMPLOYEE EM
    ,EMPLOYEE MA
WHERE EM.MANAGER = MA.MANAGER(+);

-- *) 조인 시 조회 성능 팁 : 1) 인덱스가 컬럼에 생성된 것끼리 이퀄조인을 하면 성능향상 MANAGER
-- *                       2) 조인시 FROM 큰테이블   (데이터 건수가 많은 테이블)
-- *                                     ,작은테이블 (데이터 건수가 작은 테이블)
-- *                                WHERE 큰테이블.공통컬럼 = 작은테이블.공통컬럼

-- 4) SQL-99 표준 표기법 : DB SQL 명령어를 세계기구에서 표준을 제정함
-- 4-1) 오라클 조인 : 부서 x 사원
SELECT EM.*, DE.*
FROM EMPLOYEE EM, DEPARTMENT DE
WHERE EM.DNO = DE.DNO;

-- 4-2) SQL-99 조인 : DB상에 100% 호환됨, 모든 DB에서 사용 가능
-- 사용법) SELECT 테이블1.*, 테이블2.*
--        FROM 테이블1 JOIN 테이블2 ON (테이블1.공통컬럼=테이블2.공통컬럼)
-- 1) 이퀄조인 :
SELECT EM.*, DE.*
FROM EMPLOYEE EM JOIN DEPARTMENT DE ON (EM.DNO = DE.DNO);

-- 2) 아우터조인 :
-- 오라클 조인 :
SELECT EM.ENAME
    ,MA.ENAME 
FROM EMPLOYEE EM
    ,EMPLOYEE MA
WHERE EM.MANAGER = MA.MANAGER(+);
-- SQL-99 : (+) 없는쪽을 의미 => LEFT OUTER JOIN
SELECT EM.ENAME
    ,MA.ENAME
FROM EMPLOYEE EM LEFT OUTER JOIN EMPLOYEE MA ON (EM.MANAGER = MA.MANAGER);