-- 16_Table_CUD_Exam.sql
-- 연습문제
-- 1) EMPLOYEE 테이블의 구조만 복사하여 
--    EMP_INSERT 란 이름의 빈 테이블을 만드시오.
CREATE TABLE EMP_INSERT
AS
SELECT * FROM EMPLOYEE
WHERE 1=2;

-- 2) 본인을 EMP_INSERT 테이블에 추가하되 SYSDATE를 이용해서 입사일을 
--    오늘로 입력하세요.
--   사용법) INSERT INTO 테이블명(컬럼명, 컬럼명2, ...)
--           VALUES(값, 값2, ...);
--   예) ENO(사번) : 1, ENAME(사원명) : 'KTG', JOB(직위): 'STUDENT',
--      MANAGER(관리자) : NULL, HIREDATE(입사일) : SYSDATE, 
--      SALARY(월급): 2700, COMMISSION(상여금): 230, DNO(부서번호):10
INSERT INTO EMP_INSERT(ENO, ENAME, JOB, MANAGER, HIREDATE, SALARY, COMMISSION, DNO)
VALUES(1, 'KTG', 'STUDENT', NULL, SYSDATE, 2700, 230, 10);

-- 데이터 확인
SELECT * FROM EMP_INSERT;

-- 확정
COMMIT;

-- 3) EMP_INSERT 테이블에 옆(아시는분) 사람을 추가하되 
--    TO_DATE 함수를 이용해서 입사일을 어제로 입력하세요.
-- ex) 문자열 -> 날짜형변경 함수 : TO_DATE(SYSDATE, 'YYYY/MM/DD')
-- 참고) INSERT 시 EMP_INSERT(컬럼명 ...) : 컬럼명 생략가능( 모든 데이터를 INSERT 할때만 가능 )
-- 용어) 가라데이터 == 임시데이터(테스트데이터)
INSERT INTO EMP_INSERT(ENO, ENAME, JOB, MANAGER, HIREDATE, SALARY, COMMISSION, DNO)
VALUES(2, 'HONG', 'STUDENT', NULL, TO_DATE(SYSDATE-1,'YYYY/MM/DD'), 2000, 100, 10);

-- 데이터 확인
SELECT * FROM EMP_INSERT;

-- 확정
COMMIT;

-- 4) EMPLOYEE 테이블의 구조와 내용을 복사하여 
--    EMP_COPY 란 이름의 테이블을 만드세요.
-- 단, EMP_COPY 테이블이 있으면 테이블을 삭제하고 다시 만드세요
-- 힌트) CREATE TABLE ~ AS SELECT ~ ; (WHERE 필요없음)
DROP TABLE EMP_COPY;

CREATE TABLE EMP_COPY
AS
SELECT * FROM EMPLOYEE;

-- 5) 사원번호가(ENO) 7788인 사원의 부서번호를(DNO) 10번으로 수정하세요.
--   ( 대상테이블 : EMP_COPY )
-- 힌트) UPDATE 테이블명 SET 컬러명 = 값 WHERE 조건;
UPDATE EMP_COPY
SET
    DNO = 10
WHERE ENO = 7788;

-- 데이터 확인
SELECT * FROM EMP_COPY;

-- 확정
COMMIT;

-- 6) 사원번호가 7788의 담당 업무(JOB) 및 급여를(SALARY) 
--    사원번호 7499의 담당 업무(JOB) 및 급여와(SALARY) 
--    일치하도록 갱신하세요.( 대상테이블 : EMP_COPY )
--  힌트 : UPDATE 서브쿼리
-- 힌트) UPDATE 테이블명 SET 컬럼명 = (서브쿼리) WHERE 조건;
-- 1) 7499의 JOB, SALARY 구하기
SELECT JOB
FROM EMP_COPY
WHERE ENO = 7499;

-- 2)7499의 SALARY 구하기
SELECT SALARY
FROM EMP_COPY
WHERE ENO = 7499;

-- 3) UPDATE
UPDATE EMP_COPY
SET
    (JOB, SALARY) = (SELECT JOB, SALARY FROM EMP_COPY WHERE ENO = 7499)
WHERE ENO = 7788;

-- 데이터 확인
SELECT * FROM EMP_COPY;

-- 확정
COMMIT;

-- 7) DEPARTMENT 테이블의 구조와 내용을 복사하여 
--    DEPT_COPY 란 이름의 테이블을 만드세요.
-- 단, DEPT_COPY 있으면 DROP TABLE 명령어로 삭제하고 복사하세요(테이블 설계(구조) 삭제)
DROP TABLE DEPT_COPY;

CREATE TABLE DEPT_COPY
AS
SELECT * FROM DEPARTMENT;

-- 8) DEPT_COPY 테이블에서 부서명이(DNAME) RESEARCH 인 부서를 제거하세요.
DELETE FROM DEPT_COPY
WHERE DNAME = 'RESEARCH';

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- 9) DEPT_COPY 테이블에서 부서 번호가(DNO) 10이거나 40인 부서를 제거하세요.
DELETE FROM DEPT_COPY
WHERE DNO IN (10, 40);

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;