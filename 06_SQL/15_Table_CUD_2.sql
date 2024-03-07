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

-- 예제 3) 전체 부서에 대해 값 수정하기 : 조건절 빼기
--        부서명을(DNAME) 'PROGRAMMING2', 부서위치를(LOC) 'SEOUL' 로 수정하세요
UPDATE DEPT_COPY
SET
    DNAME = 'PROGRAMMING2',
    LOC   = 'SEOUL';

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 취소 
ROLLBACK;

-- 예제 4) 10 번 부서의 지역명을 (20번 부서의 지역명으로) 수정하기
-- 1) 20번 부서의 지역명 ? DALLAS
SELECT LOC FROM DEPT_COPY
WHERE DNO = 20;

-- 2) 10 번 부서의 지역명(LOC) DALLAS 로 수정
UPDATE DEPT_COPY
SET
    LOC = (SELECT LOC FROM DEPT_COPY WHERE DNO = 20)
WHERE DNO = 10;

-- 데이터 확인
SELECT * FROM DEPT_COPY;
-- 확정
COMMIT;

-- 연습 3) 10 번 부서의 부서명, 지역명을 
--         30번 부서의 부서명, 지역명으로 변경하기
-- 힌트) 서브쿼리
-- 1) 30번 부서의 부서명(DNAME), 지역명(LOC) ?
SELECT DNAME FROM DEPT_COPY WHERE DNO = 30; -- SALES
SELECT LOC FROM DEPT_COPY WHERE DNO = 30; -- CHICAGO

-- 2) update 
UPDATE DEPT_COPY
SET 
    DNAME = (SELECT DNAME FROM DEPT_COPY WHERE DNO = 30),
    LOC   = (SELECT LOC FROM DEPT_COPY WHERE DNO = 30)
WHERE DNO = 10;
-- 데이터 확인
SELECT * FROM DEPT_COPY;
-- 확정
COMMIT;

-- 3) UPDATE : 다른 풀이(개선)
UPDATE DEPT_COPY
SET 
    (DNAME, LOC) = (SELECT DNAME, LOC FROM DEPT_COPY WHERE DNO = 30)
WHERE DNO = 10;
-- 확정
COMMIT;

-- *) Delete(삭제) : 부분삭제, 전체삭제, 취소 가능
-- vs TRUNCATE TABLE 테이블(빠른삭제, 전체삭제)

-- 예제 6) 10번 부서를 삭제하세요
-- 사용법 
DELETE FROM DEPT_COPY
WHERE DNO = 10;

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정 
COMMIT;

-- 연습 1) 20번 부서를 삭제하세요
DELETE FROM DEPT_COPY
WHERE DNO = 20;

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정 
COMMIT;

-- 예제 7) 영업부에(SALES) 에 근무하는 사원(부서번호:DNO) 삭제하기
-- 1) 영업부에(SALES) 에 해당하는 부서번호를 찾고 : 30
SELECT DNO FROM DEPT_COPY WHERE DNAME = 'SALES';

-- 2) 30 번 부서를 삭제하기
DELETE DEPT_COPY
WHERE DNO = (SELECT DNO FROM DEPT_COPY WHERE DNAME = 'SALES');

-- 데이터 확인
SELECT * FROM DEPT_COPY;

-- 확정
COMMIT;

-- (참고) 테이블 구조 명령어 : 컬럼, 자료형 등을 볼수 있음
-- 사용법) DESC 테이블명;
DESC DEPT_COPY;