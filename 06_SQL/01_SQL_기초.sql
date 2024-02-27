-- SQL 주석 단축기 : CTRL + /

-- 1) 계정 : system : 관리자 계정 (실습 시 최초 한번만 쓴것)
--           scott : 개발자 계정 (실습 계정)
-- 2) SQL 기초 특징 : 1) 대소문자 구분이 없음
--                  2) 명령어의 끝은 ;(세미콜론)
-- 3) 테이블 : 컴퓨터 폴더와 비슷하며 DB의 가장 작은 공간
--       ex) 부서정보 테이블, 사원정보 테이블 등
-- 예제 1) 부서정보 테이블을 전체 조회 하세요
SELECT * FROM DEPARTMENT;

-- 4) 테이블 구조 : 세로 - 컬럼(정보의 각 소제목들이 표현)
--            ex) Department 테이블 구조 : DNO(부서번호), DNAME(부서명), LOC(부서위치) => 컬럼(소제목)
--                                           10        ACCOUNTING     NEW YORK   => 데이터(정보)

-- 연습 1) 사원정보 테이블을(EMPLOYEE) 전체 조회 하세요
-- 조회 사용법) SELECT * FROM 테이블명;
-- 조회 사용법) SELECT 컬럼명, 컬럼명2 ...  FROM 테이블명;
--         * : 모든 컬럼
-- 실행 단축키 : 1) 1개 실행(ctrl + ENTER), 2) 여러줄 실행(F5)
SELECT * FROM EMPLOYEE;

-- 예제 2) 사원 테이블의(EMPLOYEE) 컬럼중에서 ENO, ENAME 컬럼만 조회하는 SQL 작성
SELECT ENO, ENAME FROM EMPLOYEE;

-- 연습 2) 부서테이블의 컬럼중에서 DNO(부서번호), LOC(부서위치) 컬럼만 조회하기
-- 부서테이블 : DEPARTMENT
SELECT DNO, LOC FROM DEPARTMENT;

-- 연습 3) 보통 컬럼에 산술연산자(/,+,-, *:산술연산자) 연산해서 화면에 출력할 수 있습니다.
-- 샘플은 아래와 같습니다.
SELECT DNO * 10 FROM DEPARTMENT;
-- 사원테이블 : EMPLOYEE
-- 월급 컬럼  : SALARY
-- 사원명 컬럼 : ENAME
-- 사원테이블에서 사원명과 1년연봉을 화면에 출력해 보세요, 산술연산자를 이용하면 됩니다.
SELECT ENAME, SALARY*12 FROM EMPLOYEE;

-- 예제 3) 사원테이블에서 COMMISSION(커미션 : 보너스, 인센티브) 을 더한 연봉 구하기
-- 사원테이블 : EMPLOYEE
-- 월급 컬럼  : SALARY
-- 사원명 컬럼 : ENAME
-- 상여금 컬럼 : COMMISSION
-- 문제점 : DB에서 NULL은 아무것도 없는 값을 의미, 특징은 산술연산이 안됨
--  해결 : NULL -> 숫자로 바꾸는 함수를 사용해서 개선할 수 있음 : 
--         NVL(컬럼, 0) => 컬럼의 NULL 값이 0으로 변경됨
-- 컬럼의 자료형 : 문자열, 숫자, 날짜자료형 등
SELECT ENAME, SALARY*12 + COMMISSION FROM EMPLOYEE;

-- 예제 3 개선
SELECT ENAME, SALARY*12 + NVL(COMMISSION,0) FROM EMPLOYEE;

-- 예제 4) 위의 연봉정보 컬럼에 별명 붙이기
-- 사용법) SELECT 컬럼명 AS 별명 FROM 테이블명; 
SELECT ENAME, SALARY*12 + NVL(COMMISSION,0) AS 연봉 FROM EMPLOYEE;
-- 참고) 위의 연봉정보 컬럼에 별명 붙이기 2 : 별명에 공백이 있으면 쌍따옴표를 사용할 것
SELECT ENAME, SALARY *12 + NVL(COMMISSION,0) AS "연 봉" FROM EMPLOYEE;

-- 예제 5) 중복된 정보(데이터)를 1번만 출력하게 하는 예제
-- 사원 테이블 : EMPLOYEE
-- 부서번호 컬럼 : DNO
-- 사용법) SELECT DISTINCT 컬럼명 FROM 테이블명;
SELECT DISTINCT DNO FROM EMPLOYEE;

-- 예제 6) 간단한 산술연산 및 테스트 용도 테이블 소개
-- 테스트 테이블 : DUAL
-- 자료형 : 문자열, 숫자, 날짜형 등

-- 숫자 예시
SELECT 4*5+1 FROM DUAL;
-- 문자열 예시 : 홑따옴표로 표현 ex)'홍길동'
SELECT '홍길동' FROM DUAL;
-- 날짜형 예시 : 현재날짜 보기 속성 : SYSDATE
SELECT SYSDATE FROM DUAL;