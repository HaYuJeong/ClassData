COMMENT ON COLUMN TB_SIMPLE_PRODUCT."SPNO" IS '상품번호';
COMMENT ON COLUMN TB_SIMPLE_PRODUCT."CODE_ID" IS '상품종류코드';
COMMENT ON COLUMN TB_SIMPLE_PRODUCT."TITLE" IS '상품명';
COMMENT ON COLUMN TB_SIMPLE_PRODUCT."IMG_PATH" IS '이미지 경로';
COMMENT ON COLUMN TB_SIMPLE_PRODUCT."UNIT_PRICE" IS '단가';
COMMENT ON COLUMN TB_SIMPLE_PRODUCT."INVENTORY_COUNT" IS '재고수량';
COMMENT ON COLUMN TB_SIMPLE_PRODUCT."USE_YN" IS '사용여부';

COMMENT ON COLUMN TB_SIMPLE_CART."SCNO" IS '장바구니번호';
COMMENT ON COLUMN TB_SIMPLE_CART."SPNO" IS '상품번호';
COMMENT ON COLUMN TB_SIMPLE_CART."CART_COUNT" IS '장바구니 상품개수';
COMMENT ON COLUMN TB_SIMPLE_CART."DELETE_YN" IS '삭제여부';
COMMENT ON COLUMN TB_SIMPLE_CART."INSERT_TIME" IS '추가일시';
COMMENT ON COLUMN TB_SIMPLE_CART."UPDATE_TIME" IS '수정일시';
COMMENT ON COLUMN TB_SIMPLE_CART."DELETE_TIME" IS '삭제일시';

COMMENT ON COLUMN TB_SIMPLE_ORDER."SONO" IS '주문번호';
COMMENT ON COLUMN TB_SIMPLE_ORDER."ORDER_DATE" IS '주문일자 : YYYY-MM-DD HH24:MI:SS';
COMMENT ON COLUMN TB_SIMPLE_ORDER."ORDER_STATUS" IS '주문상태(50001: 주문완료, 50002: 결재완료, 50003: 상품준비중, 50004: 배송준비중, 50007:배송중, 50006:배송완료, 50007:주문확정 50011: 결재취소, 50012: 재고부족취소, 50013:고객취소)';
COMMENT ON COLUMN TB_SIMPLE_ORDER."PRODUCT_AMOUNT" IS '총 상품금액';
COMMENT ON COLUMN TB_SIMPLE_ORDER."DELIVERY_AMOUNT" IS '배송비';
COMMENT ON COLUMN TB_SIMPLE_ORDER."ORDER_AMOUNT" IS '주문금액 = 총 상품금액 + 배송비';
COMMENT ON COLUMN TB_SIMPLE_ORDER."DELIVERY_ADDR" IS '배송지 주소';
COMMENT ON COLUMN TB_SIMPLE_ORDER."DELIVERY_MSG" IS '배송지 메모';
COMMENT ON COLUMN TB_SIMPLE_ORDER."DELETE_YN" IS '삭제여부';
COMMENT ON COLUMN TB_SIMPLE_ORDER."INSERT_TIME" IS '추가일시';
COMMENT ON COLUMN TB_SIMPLE_ORDER."UPDATE_TIME" IS '수정일시';
COMMENT ON COLUMN TB_SIMPLE_ORDER."DELETE_TIME" IS '삭제일시';

COMMENT ON COLUMN TB_SIMPLE_ORDER_DETAIL."SONO" IS '주문번호(PK), FK';
COMMENT ON COLUMN TB_SIMPLE_ORDER_DETAIL."SPNO" IS '상품번호(PK2), FK';
COMMENT ON COLUMN TB_SIMPLE_ORDER_DETAIL."PRODUCT_COUNT" IS '상품수량';

COMMENT ON COLUMN TB_SIMPLE_APPROVAL."SANO" IS '결재번호(PK), 시퀀스';
COMMENT ON COLUMN TB_SIMPLE_APPROVAL."SONO" IS '주문번호(FK)';
COMMENT ON COLUMN TB_SIMPLE_APPROVAL."APPROVAL_DATE" IS '결재일자 : YYYY-MM-DD HH24:MI:SS';
COMMENT ON COLUMN TB_SIMPLE_APPROVAL."APPROVAL_AMOUNT" IS '결재일자 : YYYY-MM-DD HH24:MI:SS';

COMMENT ON COLUMN TB_PRODUCT."SPNO" IS '상품번호';
COMMENT ON COLUMN TB_PRODUCT."KIND_CODE" IS '상품종류코드';
COMMENT ON COLUMN TB_PRODUCT."PNAME" IS '상품명';
COMMENT ON COLUMN TB_PRODUCT."IMAGE" IS '이미지 경로';
COMMENT ON COLUMN TB_PRODUCT."UNIT_PRICE" IS '단가';
COMMENT ON COLUMN TB_PRODUCT."INVENTORY_COUNT" IS '재고수량';
COMMENT ON COLUMN TB_PRODUCT."USE_YN" IS '사용여부';

COMMENT ON COLUMN TB_CART."CNO" IS '장바구니번호';
COMMENT ON COLUMN TB_CART."PNO" IS '상품번호';
COMMENT ON COLUMN TB_CART."AMOUNT" IS '장바구니 상품개수';
COMMENT ON COLUMN TB_CART."DELETE_YN" IS '삭제여부';
COMMENT ON COLUMN TB_CART."INSERT_TIME" IS '추가일시';
COMMENT ON COLUMN TB_CART."UPDATE_TIME" IS '수정일시';
COMMENT ON COLUMN TB_CART."DELETE_TIME" IS '삭제일시';

COMMENT ON COLUMN TB_ORDER."ONO" IS '주문번호';
COMMENT ON COLUMN TB_ORDER."ORDER_DATE" IS '주문일자 : YYYY-MM-DD HH24:MI:SS';
COMMENT ON COLUMN TB_ORDER."ORDER_STATUS" IS '주문상태(50001: 주문완료, 50002: 결재완료, 50003: 상품준비중, 50004: 배송준비중, 50007:배송중, 50006:배송완료, 50007:주문확정 50011: 결재취소, 50012: 재고부족취소, 50013:고객취소)';
COMMENT ON COLUMN TB_ORDER."PRODUCT_AMOUNT" IS '총 상품금액';
COMMENT ON COLUMN TB_ORDER."DELIVERY_AMOUNT" IS '배송비';
COMMENT ON COLUMN TB_ORDER."ORDER_AMOUNT" IS '주문금액 = 총 상품금액 + 배송비';
COMMENT ON COLUMN TB_ORDER."DELIVERY_ADDR" IS '배송지 주소';
COMMENT ON COLUMN TB_ORDER."DELIVERY_MSG" IS '배송지 메모';
COMMENT ON COLUMN TB_ORDER."DELETE_YN" IS '삭제여부';
COMMENT ON COLUMN TB_ORDER."INSERT_TIME" IS '추가일시';
COMMENT ON COLUMN TB_ORDER."UPDATE_TIME" IS '수정일시';
COMMENT ON COLUMN TB_ORDER."DELETE_TIME" IS '삭제일시';

COMMENT ON COLUMN TB_ORDER_DETAIL."SONO" IS '주문번호(PK), FK';
COMMENT ON COLUMN TB_ORDER_DETAIL."SPNO" IS '상품번호(PK2), FK';
COMMENT ON COLUMN TB_ORDER_DETAIL."PRODUCT_COUNT" IS '상품수량';

COMMENT ON COLUMN TB_APPROVAL."ANO" IS '결재번호(PK), 시퀀스';
COMMENT ON COLUMN TB_APPROVAL."ONO" IS '주문번호(FK)';
COMMENT ON COLUMN TB_APPROVAL."APPROVAL_DATE" IS '결재일자 : YYYY-MM-DD HH24:MI:SS';
COMMENT ON COLUMN TB_APPROVAL."APPROVAL_AMOUNT" IS '결재일자 : YYYY-MM-DD HH24:MI:SS';