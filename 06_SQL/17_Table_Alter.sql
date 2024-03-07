-- (참고) 17_Table_Alter.sql 
-- DBA (DB 관리자, Database Administrater)
-- 테이블 구조(설계) 변경하기 
-- 실습 테이블 : DEPT20 (구조만 복사)
CREATE TABLE DEPT20
AS
SELECT * FROM DEPARTMENT
WHERE 1=2;                 -- 거짓조건

-- 데이터 확인
SELECT * FROM DEPT20;

-- 예제 1) 부서 테이블에(DEPT20) 날짜 자료형을(DATE) 가지는 BIRTH 컬럼 추가하기
-- 사용법) ALTER TABLE 테이블명 ADD(컬럼명 자료형);
ALTER TABLE DEPT20
ADD(BIRTH DATE);

-- 데이터 확인
SELECT * FROM DEPT20;

-- 예제 2) 부서테이블(DEPT20)에 부서명(DNAME)의 크기를 변경
-- 사용법) ALTER TABLE 테이블명 MODIFY 컬럼명 자료형(자리수)
ALTER TABLE DEPT20
MODIFY DNAME VARCHAR2(30);
-- 테이블 구조 보기 명령어
DESC DEPT20;

-- 예제 3) 부서테이블(DEPT20) BIRTH 컬럼 삭제하기
-- 사용법) ALTER TABLE 테이블명 DROP COLUMN 컬럼명
ALTER TABLE DEPT20 
DROP COLUMN BIRTH;
-- 테이블 구조 보기 명령어
DESC DEPT20;

-- 예제 4) 테이블 명 변경 : DEPT20 -> DEPT30
-- RENAME 원본테이블명 TO 바꿀테이블명;
RENAME DEPT20 TO DEPT30;
-- 테이블 구조 보기 명령어
DESC DEPT30;