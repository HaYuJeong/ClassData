-- 24_View.sql
-- 뷰(View) : 1개 이상의 테이블이나 다른 뷰를 이용해서 생성하는 가상의 테이블
-- 목적) 1) 보안을 유지하기 위해 사용 : 
--         예) 실무 : 사원테이블 (급여:SALARY, 대외비(비밀)) 
--       2) SQL 문을 미리 코딩해놓고 재활용 : 코딩 생산성 향상
-- 예제 1) JOB 이 SALESMAN 인 사원테이블에서 
--        ENO(사원번호), ENAME(사원명), DNO(부서번호), JOB(직위) 
--        컬럼만 화면에 출력하는 뷰생성
-- 사용법) CREATE OR REPLACE VIEW 뷰이름(컬럼명, 컬럼명2,...)
--         AS
--         SELECT 원본컬러명, 원본컬럼명2, ...
--         FROM 테이블명
--         WHERE 조건식;
--  참고) 뷰이름(컬럼명, 컬럼명2,...) => 뷰이름 : 생략가능(컬럼명)
CREATE OR REPLACE VIEW VW_EMP_JOB(ENO, ENAME, DNO, JOB)
AS
SELECT ENO, ENAME, DNO, JOB
FROM EMPLOYEE
WHERE JOB LIKE 'SALESMAN%';

-- 예제 2) 위에서 만든 뷰를 조회해 보세요
-- 사용법) SELECT * FROM 뷰이름;
SELECT * FROM VW_EMP_JOB;

-- 연습 1) 부서테이블과(DEPARTMENT) 사원테이블을(EMPLOYEE) 
--        조인한 결과를 뷰로 만들어서 출력하세요
--  뷰이름 : VW_EMP_COMPLEX
-- 열명은 중복이 안되게 지정
-- 힌트) SELECT EMP.*, DEP.DNAME, DEP.LOC
--      FROM EMPLOYEE EMP
--          ,DEPARTMENT DEP
--      WHERE EMP.DNO = DEP.DNO (공통컬럼(DNO) =(이퀄조인))
-- 힌트) 뷰 생성시 (컬럼명 생략)
CREATE OR REPLACE VIEW VW_EMP_COMPLEX
AS
SELECT EMP.*, DEP.DNAME, DEP.LOC
FROM EMPLOYEE EMP
    ,DEPARTMENT DEP
WHERE EMP.DNO = DEP.DNO;

-- 뷰 조회
SELECT * FROM VW_EMP_COMPLEX;

-- 시스템 테이블 : 데이터사전(Dictionary view) : USER_VIEWS
SELECT * FROM USER_VIEWS;

-- *) (심화) 뷰에 INSERT 가능?
-- 예제 2) VW_EMP_JOB 에 INSERT 하기
-- 뷰컬럼 : ENO(사원번호), ENAME(사원명), DNO(부서번호), JOB(직위)
INSERT INTO VW_EMP_JOB
VALUES(8000, '윤정화', 30, 'SALESMAN');

-- 데이터 확인
SELECT * FROM VW_EMP_JOB;

-- 확정
COMMIT;

-- 예제 3) 뷰에 그룹함수를 사용해서 만들기
-- 부서별(DNO) 급여합계(SUM(SALARY)), 급여평균(AVG(SALARY))
--  을 출력하는 뷰를 생성하세요
-- 뷰이름 : VW_EMP_SALARY
-- 컬럼 별칭 :  급여합계(SUM(SALARY)) AS SAL_SUM
-- 컬럼 별칭 :  급여평균(AVG(SALARY)) AS SAL_AVG
CREATE OR REPLACE VIEW VW_EMP_SALARY
AS
SELECT SUM(SALARY) AS SAL_SUM
      ,AVG(SALARY) AS SAL_AVG
FROM EMPLOYEE
GROUP BY DNO;

-- 데이터 확인
SELECT * FROM VW_EMP_SALARY;

-- 확정
COMMIT;

-- 그룹함수 사용하는 뷰에서 INSERT 가능한가?
INSERT INTO VW_EMP_SALARY
VALUES(1000, 3000);

-- 예제 3) 뷰(View) 삭제
-- 사용법) DROP VIEW 뷰이름;
DROP VIEW VW_EMP_SALARY;

-- (참고) 뷰에 INSERT 가능 : (그룹함수 없을때)
-- 뷰에 INSERT 기능 제한 : WITH READ ONLY
-- 예제 4) WITH READ ONLY 옵셔을 붙여서 뷰 만들기
--  SQL : 직위가 MANAGER 인 사원을 출력하는 뷰 만들기
CREATE OR REPLACE VIEW VW_EMP_JOB_READ
AS
SELECT ENO, ENAME, DNO, JOB
FROM EMPLOYEE
WHERE JOB LIKE '%MANAGER%' WITH READ ONLY;
-- INSERT 테스트 
INSERT INTO VW_EMP_JOB_READ
VALUES(9000,'김주현',30, 'MANAGER'); -- 에러 발생