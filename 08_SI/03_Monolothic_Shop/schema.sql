-- Table , 시퀀스 등 구조 정의
DROP SEQUENCE SQ_SIMPLE_PRODUCT;
CREATE SEQUENCE SQ_SIMPLE_PRODUCT START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_SIMPLE_ORDER;
CREATE SEQUENCE SQ_SIMPLE_ORDER START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_SIMPLE_APPROVAL;
CREATE SEQUENCE SQ_SIMPLE_APPROVAL START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_SIMPLE_CART;
CREATE SEQUENCE SQ_SIMPLE_CART START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_PRODUCT;
CREATE SEQUENCE SQ_PRODUCT START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_ORDER;
CREATE SEQUENCE SQ_ORDER START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_APPROVAL;
CREATE SEQUENCE SQ_APPROVAL START WITH 1 INCREMENT BY 1;

DROP SEQUENCE SQ_CART;
CREATE SEQUENCE SQ_CART START WITH 1 INCREMENT BY 1;

-- 공통코드 테이블은 시퀀스는 사용하지 않음
-- 공통코드 테이블의 등록된 코드는 향후에 않쓰이더라도 삭제/수정하지 않음 : 데이터가 많지않아 오버헤드가 없음
DROP TABLE TB_CODE_CATEGORY CASCADE CONSTRAINT;
DROP TABLE TB_CODE CASCADE CONSTRAINT;

DROP TABLE TB_SIMPLE_PRODUCT CASCADE CONSTRAINT;
DROP TABLE TB_SIMPLE_CART CASCADE CONSTRAINT;
DROP TABLE TB_SIMPLE_ORDER CASCADE CONSTRAINT;
DROP TABLE TB_SIMPLE_ORDER_DETAIL CASCADE CONSTRAINT;
DROP TABLE TB_SIMPLE_APPROVAL CASCADE CONSTRAINT;

DROP TABLE TB_PRODUCT CASCADE CONSTRAINT;
DROP TABLE TB_CART CASCADE CONSTRAINT;
DROP TABLE TB_ORDER CASCADE CONSTRAINT;
DROP TABLE TB_ORDER_DETAIL CASCADE CONSTRAINT;
DROP TABLE TB_APPROVAL CASCADE CONSTRAINT;

-- 코드성 테이블 : 공통 코드 유형(대분류) 테이블
CREATE TABLE TB_CODE_CATEGORY
(
    CATEGORY_ID   NUMBER NOT NULL PRIMARY KEY,
    CATEGORY_NAME VARCHAR2(255)
);

-- 코드성 테이블 : 공통 코드 테이블
CREATE TABLE TB_CODE
(
    CODE_ID     NUMBER NOT NULL PRIMARY KEY,
    CODE_NAME   VARCHAR2(255),
    CATEGORY_ID NUMBER NOT NULL,
    USE_YN      VARCHAR(1) DEFAULT 'Y',
    FOREIGN KEY (CATEGORY_ID) REFERENCES TB_CODE_CATEGORY (CATEGORY_ID)
);

-- 마스터성 테이블 : 점이력 관리 않함
-- 1) 추가/수정만 가능
-- 2) 삭제 않하고 사용여부만 관리
-- 사용않하는 레코드는 향후에 배치잡으로 일괄 삭제
-- 심픔 상품 테이블
CREATE TABLE TB_SIMPLE_PRODUCT
(
    SPNO            NUMBER NOT NULL PRIMARY KEY, -- 상품번호
    CODE_ID         NUMBER,                      -- 상품종류코드
    TITLE           VARCHAR2(255),               -- 상품명
    IMG_PATH        VARCHAR2(255),               -- 이미지 경로
    UNIT_PRICE      NUMBER,                      -- 단가
    INVENTORY_COUNT NUMBER,                      -- 재고수량
    USE_YN          VARCHAR2(1) DEFAULT 'Y'      -- 사용여부
);

-- 심플 장바구니 테이블
CREATE TABLE TB_SIMPLE_CART
(
    SCNO        NUMBER NOT NULL PRIMARY KEY, -- 장바구니번호
    SPNO        NUMBER NOT NULL,             -- 상품번호
    CART_COUNT  NUMBER DEFAULT 0,            -- 장바구니 상품개수
    DELETE_YN   VARCHAR2(1) DEFAULT 'N',
    INSERT_TIME VARCHAR2(255),
    UPDATE_TIME VARCHAR2(255),
    DELETE_TIME VARCHAR2(255),
    FOREIGN KEY(SPNO) REFERENCES TB_SIMPLE_PRODUCT (SPNO)
);

-- 주문 테이블
CREATE TABLE TB_SIMPLE_ORDER
(
    SONO            NUMBER NOT NULL PRIMARY KEY, -- 주문번호
    ORDER_DATE      VARCHAR2(1000) NOT NULL,     -- 주문일자 : YYYY-MM-DD HH24:MI:SS
    ORDER_STATUS    NUMBER NOT NULL,             -- 주문상태(50001: 주문완료, 50002: 결재완료, 50003: 상품준비중, 50004: 배송준비중, 50007:배송중, 50006:배송완료, 50007:주문확정 50011: 결재취소, 50012: 재고부족취소, 50013:고객취소)
    PRODUCT_AMOUNT  NUMBER NOT NULL,             -- 총 상품금액
    DELIVERY_AMOUNT NUMBER,                      -- 배송비
    ORDER_AMOUNT    NUMBER,                      -- 주문금액 = 총 상품금액 + 배송비
    DELIVERY_ADDR   VARCHAR2(1000),              -- 배송지 주소
    DELIVERY_MSG    VARCHAR2(400),               -- 배송지 메모
    DELETE_YN       VARCHAR2(1) DEFAULT 'N',
    INSERT_TIME     VARCHAR2(255),
    UPDATE_TIME     VARCHAR2(255),
    DELETE_TIME     VARCHAR2(255)
);

-- 주문 상세 테이블 : 중간 테이블(상품(M) <-> 주문(M))
CREATE TABLE TB_SIMPLE_ORDER_DETAIL
(
    SONO          NUMBER NOT NULL ,   -- 주문번호(PK), FK
    SPNO          NUMBER NOT NULL , -- 상품번호(PK2), FK
    PRODUCT_COUNT NUMBER DEFAULT 0,                                                -- 상품수량
    PRIMARY KEY (SONO, SPNO),
    FOREIGN KEY(SONO) REFERENCES TB_SIMPLE_ORDER (SONO) ON DELETE CASCADE,
    FOREIGN KEY(SPNO) REFERENCES TB_SIMPLE_PRODUCT (SPNO)
);

-- 결재 정보 테이블
CREATE TABLE TB_SIMPLE_APPROVAL
(
    SANO            NUMBER NOT NULL PRIMARY KEY,                                   -- 결재번호(PK), 시퀀스
    SONO            NUMBER NOT NULL , -- 주문번호(FK)
    APPROVAL_DATE   VARCHAR2(1000) ,                                       -- 결재일자 : YYYY-MM-DD HH24:MI:SS
    APPROVAL_AMOUNT NUMBER NOT NULL,
    FOREIGN KEY(SONO) REFERENCES TB_SIMPLE_ORDER (SONO)
);

-- 마스터성 테이블 : 점이력 관리 않함
-- 1) 추가/수정만 가능
-- 2) 삭제 않하고 사용여부만 관리
-- 사용않하는 레코드는 향후에 배치잡으로 일괄 삭제
-- 상품테이블
CREATE TABLE TB_PRODUCT
(
    PNO             NUMBER NOT NULL PRIMARY KEY, -- 상품번호
    KIND_CODE       NUMBER,                      -- 상품종류코드
    PNAME           VARCHAR2(255),               -- 상품명
    IMAGE           VARCHAR2(255),               -- 이미지 경로
    UNIT_PRICE      NUMBER,                      -- 단가
    INVENTORY_COUNT NUMBER,                       -- 재고수량
    USE_YN          VARCHAR2(1) DEFAULT 'Y'      -- 사용여부
);

-- 장바구니 테이블
CREATE TABLE TB_CART
(
    CNO         NUMBER NOT NULL PRIMARY KEY,                    -- 장바구니번호
    PNO         NUMBER , -- 상품번호
    AMOUNT      NUMBER DEFAULT 0,                               -- 장바구니 상품개수
    DELETE_YN   VARCHAR2(1) DEFAULT 'N',
    INSERT_TIME VARCHAR2(255),
    UPDATE_TIME VARCHAR2(255),
    DELETE_TIME VARCHAR2(255),
    FOREIGN KEY(PNO) REFERENCES TB_PRODUCT (PNO)
);

-- 주문 테이블
CREATE TABLE TB_ORDER
(
    ONO             NUMBER NOT NULL PRIMARY KEY, -- 주문번호
    ORDER_DATE      VARCHAR2(1000) NOT NULL,     -- 주문일자 : YYYY-MM-DD HH24:MI:SS
    ORDER_STATUS    VARCHAR2(10) NOT NULL,       -- 주문상태(50001: 주문완료, 50002: 결재완료, 50003: 상품준비중, 50004: 배송준비중, 50007:배송중, 50006:배송완료, 50007:주문확정 50011: 결재취소, 50012: 재고부족취소, 50013:고객취소)
    PRODUCT_AMOUNT  VARCHAR2(1000) NOT NULL,     -- 총 상품금액
    DELIVERY_AMOUNT VARCHAR2(1000),              -- 배송비
    ORDER_AMOUNT    VARCHAR2(1000),              -- 주문금액 = 총 상품금액 + 배송비
    DELIVERY_ADDR   VARCHAR2(1000),              -- 배송지 주소
    DELIVERY_MSG    VARCHAR2(400),               -- 배송지 메모
    DELETE_YN       VARCHAR2(1) DEFAULT 'N',
    INSERT_TIME     VARCHAR2(255),
    UPDATE_TIME     VARCHAR2(255),
    DELETE_TIME     VARCHAR2(255)
);

-- 주문 상세 테이블 : 중간 테이블(상품(M) <-> 주문(M))
CREATE TABLE TB_ORDER_DETAIL
(
    ONO           NUMBER NOT NULL ,               -- 주문번호(PK)
    PNO           NUMBER NOT NULL ,               -- 상품번호(PK2)
    PRODUCT_COUNT NUMBER DEFAULT 0,               -- 상품수량
    PRIMARY KEY (ONO, PNO),
    FOREIGN KEY(ONO) REFERENCES TB_ORDER (ONO),
    FOREIGN KEY(PNO) REFERENCES TB_PRODUCT (PNO)
);

-- 결재 정보 테이블
CREATE TABLE TB_APPROVAL
(
    ANO             NUMBER NOT NULL PRIMARY KEY,                           -- 결재번호(PK)
    ONO             NUMBER NOT NULL , -- 주문번호(FK)
    APPROVAL_DATE   VARCHAR2(1000) ,                               -- 결재일자 : YYYY-MM-DD HH24:MI:SS
    APPROVAL_AMOUNT NUMBER NOT NULL,
    FOREIGN KEY(ONO) REFERENCES TB_ORDER (ONO)
);