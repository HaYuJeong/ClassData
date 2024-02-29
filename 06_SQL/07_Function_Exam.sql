-- 07_Function_Exam.sql
-- 연습 1) 입사일(HIREDATE) 연도는 2자리(YY), 월은 (MON)로 표시하고, 
--    일(DD), 요일은 (DY)로 표기하세요. (날짜포맷)
-- TO_CHAR(날짜컬럼, '날짜포맷') : 날짜 -> 문자로 바꾸는 함수 사용
-- 추가 날짜포맷 : MON => 월
--                DY  => 요일
-- 출력 예) 80/12월/17 수
-- 사원테이블 : EMPLOYEE
SELECT HIREDATE
    , TO_CHAR(HIREDATE,'YY/MON/DD DY')
    FROM EMPLOYEE;

-- 연습 2) 올해 며칠이 지났는지 출력하시오. 
--    현재 날짜에서 올해 1월1일을 뺀 결과를 출력하고 
--    TO_DATE 함수를 사용하여 데이터 형을 일치시켜서 연산하세요
-- 힌트) 현재날짜 - '2024/01/01'(날짜로 변환)
-- 단, 포맷은 'YYYY/MM/DD' (참고), 버림 적용(TRUNC())
SELECT SYSDATE - TO_DATE('2024/01/01', 'YYYY/MM/DD')
FROM DUAL;

-- 연습 3) 사원들의(EMPLOYEE) 매니저(MANAGER) 사번을(ENO) 출력하되 매니저가 없는 
--    사원에 대해서는 NULL값 대신에 0을 출력하세요. ( NVL함수 사용하라 )
SELECT NVL(MANAGER,0), ENO
FROM EMPLOYEE;

-- 연습 4) DECODE(CASE WHEN) 함수로 직급에(JOB) 따라 
--    급여를(SALARY) 인상하도록 하세요.
--    직급이(JOB) 'ANALYST' 이면 200, 
--    'SALESMAN' 이면 180, 'MANAGER' 이면 150
--    'CLERK' 이면 100을 인상하세요.
-- 출력 : ENO(사원번호), ENAME(사원명), JOB(직급), SALARY(월급),
--       급여인상 
-- 사원테이블 : EMPLOYEE
-- 힌트) DECODE(JOB, 'SALESMAN', SALARY + 180 ...)
-- SELECT ENO, ENAME, JOB, DECODE(JOB, 'ANALYST', SALARY+20
--                                     , 'SALESMAN', SALARY+180
--                                     , 'MANAGER' , SALARY+150
--                                     , 'CLERK'   , SALARY+100
--                                     , SALARY
-- ) AS 급여인상
-- FROM EMPLOYEE;

SELECT ENO, ENAME, JOB
        , CASE WHEN JOB='ANALYST' THEN SALARY+20
        , WHEN JOB='SALESMAN'THEN SALARY+180
        , WHEN JOB='MANAGER'THEN SALARY+150
        , WHEN JOB='CLERK'THEN SALARY+100
        ELSE SALARY
        END
FROM EMPLOYEE;

-- 연습 5) 사원테이블에서(EMPLOYEE) 사원들의 월 평균 근무일 수는 21.5일입니다.(working day)
-- 하루 근무 시간을 8시간으로 보았을 때 
-- 사원들의 하루 급여와 시급을 계산하여 
-- 사원번호(ENO), 사원명(ENAME), 급여(SALARY), 하루급여, 시급 결과를 출력합니다.
-- 단 하루 급여는 소수점 세 번째 자리에서 버리고, 시급은 두 번째 소수점에서 반올림하세요
-- 공식 : 하루 급여 : 급여(SALARY) / 21.5
--       시급     : 급여 / 21.5 / 8
-- 힌트) TRUNC(급여(SALARY) / 21.5, 자리수)
--      ROUND(급여(SALARY) / 21.5 / 8, 자리수)
SELECT ENO, ENAME, SALARY, TRUNC(SALARY/21.5), ROUND(SALARY/21.5/8)
FROM EMPLOYEE;