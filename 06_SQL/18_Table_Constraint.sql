-- 18_Table_Constraint.sql (*)
-- 테이블 제약조건 : 테이블에 입력될 값을 제한하는 것
-- 제약조건 기본적으로 컬럼별로 각각 지정할 수 있음
-- 1) UNIQUE(유일한) 제약조건 : 유일한 값만 입력될 수 있음(중복 금지), NULL 값은 허용
-- 1-2) NOT NULL 제약조건(*) : 입력될 값으로 NULL 값은 허용안됨 
-- 사용법) CREATE TABLE 테이블명(
--             컬럼명 자료형 제약조건
--             ...
--        );

-- 예제 1) CUSTOMER 테이블을 정의하고 ID 컬럼에 UNIQUE 제약조건을 지정하세요
--    그리고, PWD, NAME 에는 NOT NULL 제약조건을 추가하세요
-- (ID       VARCHAR2(20) -> UNIQUE 제약조건 지정
--  PWD     VARCHAR2(20)
--  NAME    VARCHAR2(20)
--  PHONE   VARCHAR2(30)
-- ADDRESS VARCHAR2(1000))
CREATE TABLE CUSTOMER(
    ID       VARCHAR2(20) UNIQUE
    ,PWD     VARCHAR2(20) NOT NULL
    ,NAME    VARCHAR2(20) NOT NULL
    ,PHONE   VARCHAR2(30)
    ,ADDRESS VARCHAR2(1000)
);

-- 2) 기본키 제약조건(***) : Primary key(PK : 약어)
-- 특징 : 유일한 값만 입력됨(중복금지) + NOT NULL 제약조건포함 
--        + (참고 : INDEX 도 자동생성(조회속도 높임))
-- 실무) 주로 테이블 설계시 PK(기본키)는 1개는 거의 생성함
-- 사용법) CREATE TABLE 테이블명(
--         컬러명 자료형(자리수) 
--         ...
--         ,CONSTRAINT 제약조건이름 PRIMARY KEY(컬럼명)
-- );
CREATE TABLE CUSTOMER2(
    ID       VARCHAR2(20) 
    ,PWD     VARCHAR2(20) NOT NULL
    ,NAME    VARCHAR2(20) NOT NULL
    ,PHONE   VARCHAR2(30)
    ,ADDRESS VARCHAR2(1000)
    ,CONSTRAINT PK_CUSTOMER2_ID PRIMARY KEY(ID)
);

-- 데이터 확인
SELECT * FROM CUSTOMER2;
-- INSERT 테스트 : 기본키(PK: UNIQUE(유일) + NOT NULL(NULL제외) )
INSERT INTO CUSTOMER2(ID, PWD, NAME, PHONE, ADDRESS)
VALUES(NULL, NULL, NULL, '010-123-4567','SEOUL'); -- X (PK 때문에 안됨)

-- 3) 외래키 제약조건(*) : FOREIGN KEY(FK: 약어)
-- 참조키 : 부서(DEPARTMENT) 테이블에 부서번호(10 ~ 40) : 부모테이블
--          사원(EMPLOYEE) 테이블에 부서번호 (10 ~ 30) : 자식테이블
-- 관계 : 1) 회사에서는 사원들은 무조건 1개의 부서에 소속됨 
--        2) 회사에 있는 부서만 소속될수 있음
--       => 부서(기준 : 부모역할) -> 사원(소속 : 자식역할)
-- 참조키 특징 : 1) 부모테이블의 컬럼중에 데이터만 자식테이블의 컬럼
--                데이터로 입력될수 있게 제약을 거는것
--              2) 부모테이블의 그 컬럼은 유일한 조건이어야함(기본키, UNIQUE 제약조건)
--              3) 삭제하실때는 자식테이블 데이터 먼저 삭제하고, 부모테이블 데이터를 삭제해야함
-- (참고) 실무에서 참조키를 만들어서 프로젝트를 하기도 하고 안만들고 하기도 함
-- 사용법) CREATE TABLE 테이블명(
--             컬럼명 자료형(자리수) CONSTRAINT 제약조건명 PRIMARY KEY
--             ,컬럼명2 자료형2(자리수) CONSTRAINT 제약조건명 REFERENCES 부모테이블명
--         );
-- 예제 3) EMP_SECOND 테이블을 만들고, 
--         ENO NUMBER(4) (기본키 #2)
--         ENAME VARCHAR2(10), 
--         JOB VARCHAR2(9)
--         DNO NUMBER(2) (참조키 : 부모(DEPARTMENT))
-- 지정하세요
CREATE TABLE EMP_SECOND(
        ENO NUMBER(4) CONSTRAINT PK_EMP_SECOND_ENO PRIMARY KEY
        ,ENAME VARCHAR2(10)
        ,JOB VARCHAR2(9)
        ,DNO NUMBER(2) CONSTRAINT FK_EMP_SECOND_DNO REFERENCES DEPARTMENT
);

-- 실습 테이블 삭제
DROP TABLE EMP_SECOND;

-- 기타 제약조건 :
-- 4) CHECK 제약조건(참고) : CK (약어)
-- 정의 : 컬럼에서 허용 가능한 데이터 범위 또는 조건을 지정할수 있음
-- 예제 4) EMP_SECOND 테이블에 CHECK 제약조건을 지정하세요 (SALARY>0 값만 INSERT 될수 있도록)
-- 사용법) CREATE TABLE 테이블명(
    --     ,컬럼명 자료형(자리수) CONSTRAINT 제약조건명 CHECK(조건식)
    -- );
CREATE TABLE EMP_SECOND(
    ENO NUMBER(4) CONSTRAINT PK_EMP_SECOND_ENO PRIMARY KEY
    ,SALARY NUMBER(7,2) CONSTRAINT CK_EMP_SECOND_SALARY CHECK(SALARY>0)
);

-- CHECK 제약조건 테스트
INSERT INTO EMP_SECOND(ENO, SALARY)
VALUES (8000, -200);                  -- 에러 발생(음수)

-- 테이블 삭제
DROP TABLE EMP_SECOND;

-- 5) DEFAULT 제약조건 : 
-- 정의 : NULL 값이 INSERT 될때 기본적으로 다른 값으로 생성하는 제약조건
CREATE TABLE EMP_SECOND(
    ENO NUMBER(4) CONSTRAINT PK_EMP_SECOND_ENO PRIMARY KEY
    ,SALARY NUMBER(7,2) DEFAULT 1000
);
-- (1) 디폴트 제약조건 테스트
-- 컬럼(급여컬럼) 을 생략하면 NULL이 자동으로 입력됨
INSERT INTO EMP_SECOND(ENO)
VALUES(8000);
-- 확정
COMMIT;

-- (2) 명시적으로 SALARY 컬럼에 NULL 넣기 : DEFAULT 제약조건이 실행되지 않음
INSERT INTO EMP_SECOND(ENO, SALARY)
VALUES(8001, NULL);

-- 데이터 확인
SELECT * FROM EMP_SECOND;
-- 확정
COMMIT;

-- (참고) 외래키 == 참조키 == FK 
-- 1) 데이터 무결성 제약조건 : 기본키(***) / 외래키(*) 제약조건
-- 무결성 : 데이터에 결점이 없는것
-- 2) 외래키(참조키) : #2
-- 2-1) 컬럼에 바로 외래키지정
CREATE TABLE EMP_SECOND(
        ENO NUMBER(4) CONSTRAINT PK_EMP_SECOND_ENO PRIMARY KEY
        ,ENAME VARCHAR2(10)
        ,JOB VARCHAR2(9)
        ,DNO NUMBER(2) CONSTRAINT FK_EMP_SECOND_DNO  REFERENCES DEPARTMENT
);
-- 2-2) 따로 지정(참고)
CREATE TABLE EMP_SECOND(
        ENO NUMBER(4) CONSTRAINT PK_EMP_SECOND_ENO PRIMARY KEY
        ,ENAME VARCHAR2(10)
        ,JOB VARCHAR2(9)
        ,DNO NUMBER(2) 
        ,CONSTRAINT FK_EMP_SECOND_DNO FOREIGN KEY(DNO) REFERENCES DEPARTMENT
);

-- 기타사항(참고) : 제약조건 보기 테이블들 소개
-- 용도) 현재 생성된 테이블의 정보, 제약조건, 크기 등을 확인하는 용도
-- 테이블 : USER_CONSTRAINTS
SELECT TABLE_NAME, CONSTRAINT_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME IN ('EMPLOYEE', 'DEPARTMENT')
ORDER BY TABLE_NAME;

-- 테이블 복사 (CREATE TABLE ~ AS SELECT * FROM 테이블명)
-- =>  제약조건은 복사되지 않음!
DROP TABLE DEPT_COPY;
DROP TABLE EMP_COPY;

CREATE TABLE DEPT_COPY
AS
SELECT * FROM DEPARTMENT;

CREATE TABLE EMP_COPY
AS
SELECT * FROM EMPLOYEE;

-- 6) 테이블 복사 후에는 제약조건이 복사되지 않음
-- 그래서 제약조건만 따로 추가해보세요 (미리 만들어놨던 테이블에 제약조건 추가하기)
-- 예제1) 사원복사본 테이블에(EMP_COPY) 기본키(PRIMARY KEY) 추가하기
-- 사용법) ALTER TABLE 테이블명
--        ADD CONSTRAINT 제약조건명 PRIMARY KEY(컬럼)
ALTER TABLE EMP_COPY
ADD CONSTRAINT PK_EMP_COPY_ENO PRIMARY KEY(ENO);

-- 연습1) 부서복사본(DEPT_COPY) 테이블에 기본키(PK) 추가하기
-- 기본키 : DNO(부서번호),(테이블의 데이터 중에서 유일한 값을 가져야 되는것)
ALTER TABLE DEPT_COPY
ADD CONSTRAINT PK_DEPT_COPY_DNO PRIMARY KEY(DNO);

-- 예제2) 테이블 생성후에 외래키(참조키:FK) 추가하기 : EMP_COPY
-- 사용법) ALTER TABLE 테이블명
--        ADD CONSTRAINT 제약조건명
--        FOREIGN KEY(컬럼명) REFERENCES 부모테이블명(부모컬럼명);
ALTER TABLE EMP_COPY
ADD CONSTRAINT FK_EMP_COPY_DNO
FOREIGN KEY(DNO) REFERENCES DEPT_COPY(DNO);

-- 예제 3) 테이블 생성후 제약조건 삭제하기
-- 1) 외래키(참조키) 삭제하기
-- 사용법) ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명;
ALTER TABLE EMP_COPY 
DROP CONSTRAINT FK_EMP_COPY_DNO;

-- 2) 기본키 삭제하기
ALTER TABLE DEPT_COPY DROP PRIMARY KEY;

-- (참고) 예제 4) 제약조건 변경
--  EMP_COPY(사원) : ENAME (NULL 허용) -> ENAME (NOT NULL 제약조건으로 변경)
-- 사용법) ALTER TABLE 테이블명 MODIFY 컬럼명 NOT NULL;
ALTER TABLE EMP_COPY
MODIFY ENAME NOT NULL;

-- 참고사항 : 1) 실무에서 외래키(참조키:FK) 관련 논란이 있음
--             (1) DB에서 참조키를 만들자 파     :
--                 1-1) db 성능이 저하(부모에 데이터있으면 자식에도 데이터 넣을수있게 DB가 막음,
--                                     그걸 로직이 계속 체크함. 그래서 INSERT 할때마다 느려짐)
--                 1-2) db 테이블에 테스트 데이터만들때 제약
--             (2) DB에서 참조키를 만들지 말자 파 :
--                 1-1) 참조키 : 자바에서 코딩으로 참조키 역할하게 만듬
--                   if(부모테이블에 해당 데이터가 있는지 확인) {
--                         INSERT 자식테이블;
--                   }