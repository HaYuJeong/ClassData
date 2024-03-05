-- 15_Table_CUD_2.sql
-- Update(수정)
-- 대상 : DEPT_COPY 실습
-- 예제 1) 10 번 부서의 이름 수정하기 : 'ACCOUNTING' -> 'PROGRAMMING'
-- 사용법) UPDATE 테이블명
--         SET
--            컬럼명 = 수정할값,
--            컬럼명2 = 수정할값2,
--            ...
--         WHERE 컬럼명3 = 값; -- (조건식)
--  조건식에 해당하는 자료만 수정됨
UPDATE DEPT_COPY
SET
    DNAME = 'PROGRAMMING'
WHERE DNO = 10;

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- 연습 1) 20 번 부서 이름(DNAME) 수정하기 : 'HR'  (인력팀)
UPDATE DEPT_COPY
SET
    DNAME = 'HR'
WHERE DNO = 20;

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- 연습(응용) 2) 컬럼 값을 여러개 수정하기
--        10 번 부서의(DNO) 부서명을(DNAME) 'PROGRAMMING2', 부서위치를(LOC) 'SEOUL' 로 수정하세요
-- 힌트) SET 컬럼명 = 값 , 컬럼명2 = 값2 ...
UPDATE DEPT_COPY
SET
    DNAME = 'PROGRAMMING2',
    LOC   = 'SEOUL'
WHERE DNO = 10;

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;