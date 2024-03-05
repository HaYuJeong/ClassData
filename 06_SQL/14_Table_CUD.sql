-- 14_Table_CUD.sql
-- 테이블 생성 : DDL문 (데이터 정의어, Data Deficition Language)
-- CRUD : 약어 , DBA 또는 개발자가 실무에서 가장 많이 사용하는 용어
-- C : Create (생성, 추가 == Insert 문), R : Read==Retrieve(조회, 검색 == Select 문)
-- U : Update (수정 == Update 문),       D : Delete (삭제 == Delete 문)
-- 테이블 의미 : DB(데이터베이스,Database)에 자료를 저장하는 최소단위
-- 계정 : 개발자 계정(scott 계정)
-- sql문 : 특징 : 1) 대소문자 구분 없음

-- 예제 1) 테이블 생성 : DEPT (부서)
-- *) DB 자료형
--    NUMBER(자리수, 자리수) : 실수, 정수 등을 나타내는 자료형
--      자리수 : 숫자의 자리수를 의미
--    예) NUMBER(2), NUMBER => (자리수 상관없이 자료가 들어옴)
--        NUMBER(7,2) => (정수가 7자리, 소수점 2자리수 숫자가 들어옴)
--    VARCHAR2(자리수) : 문자열형 나타내는 자료형
--      자리수 : 문자열의 자리수를 의미
--    DATE     : 날짜형을 나타내는 자료형, 자리수 없음
-- 사용법 : CREATE TABLE 테이블명(
--          컬럼명   자료형(자리수),
--          ...
--          );
CREATE TABLE DEPT(
    DNO   NUMBER(2),
    DNAME VARCHAR2(14),
    LOC   VARCHAR2(13)
);

-- 예제 1) 사원테이블 : EMP
--        컬럼명 : ENO(사원번호, 숫자(4,0)
--                ENAME (사원명, 문자형(10)
--                JOB(직위, 문자형(9)
--                MANAGER(관리자) 숫자(4,0)
--                HIREDATE 입사일, 날짜형 (DATE)
--                SALARY 월급, 숫자형(7,2)
--                COMMISSION 상여금, 숫자형(7,2)
--                DNO  부서번호,  숫자형(2,0)  
CREATE TABLE EMP(
    ENO        NUMBER(4,0),
    ENAME      VARCHAR2(10),
    JOB        VARCHAR2(9),
    MANEGER    NUMBER(4,0),
    HIREDATE   DATE,
    SALARY     NUMBER(7,2),
    COMMISSION NUMBER(7,2),
    DNO        NUMBER(2,0)
);

-- 실무 예 : 테이블만들기 #2 : 테이블 복사(copy)
-- 예제 2) DEPARTMENT 테이블 복사하기 : DEPT_COPY 테이블 생성
-- 1) 테이블 생성 + 데이터 복사
CREATE TABLE DEPT_COPY
AS 
SELECT * FROM DEPARTMENT;

-- 2) 테이블 생성 (데이터 복사 안함)
CREATE TABLE DEPT_COPY2
AS 
SELECT * FROM DEPARTMENT
WHERE 1=2;                 -- 거짓 조건

-- 2) 컬럼/테이블 주석 만들기 : 
-- 예제 3) DEPT 테이블에 주석 붙이기
-- 1) 테이블 주석 달기 :
-- 사용법) COMMENT ON TABLE 테이블명 IS '테이블주석';
    COMMENT ON TABLE DEPT IS '부서정보';

-- 2) 컬럼   주석 달기 :
-- 사용법) COMMENT ON COLUMN 테이블명.컬럼명 IS '컬럼주석';
    COMMENT ON COLUMN DEPT.DNO IS   '부서번호';
    COMMENT ON COLUMN DEPT.DNAME IS '부서명';
    COMMENT ON COLUMN DEPT.LOC IS   '부서위치';

-- 연습 2) 사원테이블의 EMP 의 테이블 주석/컬럼주석을 작성해서
--      만들어 주세요
--    보기 : EMP ( 테이블주석 : 사원정보 )
--       컬럼주석 : ENO(사원번호), 
--                 ENAME(사원명), 
--                 JOB(직위), 
--                 MANAGER(관리자), 
--                 HIREDATE(입사일), 
--                 SALARY(월급), 
--                 COMMISSION(상여금), 
--                 DNO(부서번호)
-- 1) 테이블 주석
    COMMENT ON TABLE EMP IS '사원정보';

-- 2) 컬럼 주석
    COMMENT ON COLUMN EMP.ENO IS '사원번호';
    COMMENT ON COLUMN EMP.ENAME IS '사원명';
    COMMENT ON COLUMN EMP.JOB IS '직위';
    COMMENT ON COLUMN EMP.MANEGER IS '관리자';
    COMMENT ON COLUMN EMP.HIREDATE IS '입사일';
    COMMENT ON COLUMN EMP.SALARY IS '월급';
    COMMENT ON COLUMN EMP.COMMISSION IS '상여금';
    COMMENT ON COLUMN EMP.DNO IS '부서번호';
    
-- 3) 테이블 삭제 : DEPT_COPY 
-- 사용법) DROP TABLE 테이블명;
DROP TABLE DEPT_COPY;

-- 연습 3) DEPT_COPY2 테이블 삭제하세요
DROP TABLE DEPT_COPY2;

-- CUD : Create(추가), Update(수정), Delete(삭제)
-- DML 문 : (데이터 조작어, Data manipulation language)
-- 실습 임시테이블 생성(데이터제외) : DEPT_COPY
CREATE TABLE DEPT_COPY
AS
SELECT * FROM DEPARTMENT
WHERE 1=2;     -- 거짓조건

-- 예제 6) DEPT_COPY 테이블에 데이터를 INSERT(추가) 하세요
-- 부서 : DEPT_COPY
-- 컬럼 : DNO(부서번호,NUMBER(2)), 
--        DNAME(부서명, VARCHAR2(14))
--        LOC(부서위치, VARCHAR2(13))
-- 사용법) INSERT INTO 테이블명(컬럼명, 컬럼명2, ...)
--         VALUES(값, 값2, ...)
INSERT INTO DEPT_COPY(DNO, DNAME, LOC)
VALUES(10, 'ACCOUNTING', 'NEW YORK');

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 취소 : ROLLBACK;
ROLLBACK;

-- 확정 : DB에 영구반영(취소 안됨)
-- 확정을 해야 다른 계정(유저)이 이 데이터를 볼 수 있음
COMMIT;

-- 예제 7) DEPT_COPY에 INSERT 할때 일부 컬럼을 제외하고 추가하기
INSERT INTO DEPT_COPY(DNO, DNAME)
VALUES(30, 'SALES');

-- 데이터 확인
SELECT * FROM DEPT_COPY

-- 확정
COMMIT;

-- 예제 8) INSERT 할때 명시적으로 NULL 넣기
INSERT INTO DEPT_COPY(DNO, DNAME, LOC)
VALUES(10, 'ACCOUNTING', NULL);

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- 연습 2) DEPT_COPY 테이블에 50 부서 'COMPUTING' 만들고,
--        LOC 는 ' ' 공백으로 INSERT 하세요( 영구 반영 )
INSERT INTO DEPT_COPY(DNO, DNAME, LOC)
VALUES(50, 'COMPUTING', ' ');

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- 연습용 사원 테이블 복사(데이터 제외) : EMP_COPY
CREATE TABLE EMP_COPY
AS
SELECT * FROM EMPLOYEE
WHERE 1=2;               -- 거짓 조건

-- 연습 2) EMP_COPY 테이블에 데이터 입력하기
--  ENO - 7000, ENAME - 'CANDY', JOB - 'MANAGER',
--  HIREDATE - '2012/05/01', DNO - 10
INSERT INTO EMP_COPY(ENO, ENMAE, JOB, HIREDATE, DNO)
VALUES(7000, 'CANDY', 'MANAGER', '2012/05/01', 10);

-- 데이터 확인
SELECT * FROM EMP_COPY;

-- 확정
COMMIT;

-- 연습 3) EMP_COPY 테이블에 데이터 입력하기
--  ENO - 7020, ENAME - 'CANDY3', JOB - 'MANAGER',
--  HIREDATE - SYSDATE, DNO - 10
INSERT INTO EMP_COPY(ENO, ENAME, JOB, HIREDATE, DNO)
VALUES(7020, 'CANDY3', 'MANAGER', SYSDATE, 10);

-- 데이터 확인
SELECT * FROM EMP_COPY;

-- 확정
COMMIT;

-- 예제 9) 빠른 삭제 : TRUNCATE TABLE
SELECT * FROM DEPT_COPY;

-- 빠른 삭제 : 전체 삭제, 취소 불가
-- 사용법) TRUNCATE TABLE 테이블명
TRUNCATE TABLE DEPT_COPY;

-- 8. 다른 테이블의 데이터 복사
INSERT INTO DEPT_COPY
SELECT * FROM DEPARTMENT;

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- (참고) INSERT INTO 테이블명(컬럼, 컬럼2 ...) 
--   (컬럼, 컬럼2 ...) 생략가능 : 전체 컬럼이 내부적으로 선택됨
INSERT INTO DEPT_COPY(DNO, DNAME, LOC)
VALUES(50, 'COMPUTING', ' ');

-- 위의 것과 동일(생략)
INSERT INTO DEPT_COPY
VALUES(50, 'COMPUTING', ' ');