-- 샘플 데이터 입력
-- 코드성 테이블 : 상품 샘플 정보
-- 상품종류 유형 코드
INSERT INTO TB_CODE_CATEGORY
VALUES (100, '상품종류');

-- 상품상태 유형 코드
INSERT INTO TB_CODE_CATEGORY
VALUES (200, '상품상태');

-- 상품종류 코드
INSERT INTO TB_CODE
VALUES (10001, '아우터', 100, 'Y');

-- 상품상태 코드
INSERT INTO TB_CODE
VALUES (20001, '신상', 200, 'Y');
INSERT INTO TB_CODE
VALUES (20002, '이월상품', 200, 'Y');
INSERT INTO TB_CODE
VALUES (20003, '전시품', 200, 'Y');

INSERT INTO TB_SIMPLE_PRODUCT
VALUES (SQ_SIMPLE_PRODUCT.nextval, 10001, 'biker jacket', 'https://simple-coding-edu.s3.ap-northeast-2.amazonaws.com/image/jacket1.jpg', 10000, 50, 'Y');
INSERT INTO TB_SIMPLE_PRODUCT
VALUES (SQ_SIMPLE_PRODUCT.nextval, 10001, 'blazzer', 'https://simple-coding-edu.s3.ap-northeast-2.amazonaws.com/image/jacket2.jpg',  20000, 40, 'Y');
INSERT INTO TB_SIMPLE_PRODUCT
VALUES (SQ_SIMPLE_PRODUCT.nextval, 10001, 'trench coat', 'https://simple-coding-edu.s3.ap-northeast-2.amazonaws.com/image/jacket3.jpg',  30000, 30, 'Y');

INSERT INTO TB_PRODUCT
VALUES (SQ_PRODUCT.nextval, 10001, 'biker jacket', 'https://simple-coding-edu.s3.ap-northeast-2.amazonaws.com/image/jacket1.jpg', 10000, 10, 'Y');
INSERT INTO TB_PRODUCT
VALUES (SQ_PRODUCT.nextval, 10001, 'blazzer', 'https://simple-coding-edu.s3.ap-northeast-2.amazonaws.com/image/jacket2.jpg',  20000, 20, 'Y');
INSERT INTO TB_PRODUCT
VALUES (SQ_PRODUCT.nextval, 10001, 'trench coat', 'https://simple-coding-edu.s3.ap-northeast-2.amazonaws.com/image/jacket3.jpg',  30000, 30, 'Y');

COMMIT;