package org.example.simpledms.model.entity.shop.simpleproduct;

import jakarta.persistence.*;
import lombok.*;
import org.example.simpledms.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.simpledms.model.entity.shop.simpleproduct
 * fileName : SimpleOrder
 * author : hayj6
 * date : 2024-04-11(011)
 * description : 주문 테이블(부모)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11(011)         hayj6          최초 생성
 */
@Entity
@Table(name = "TB_SIMPLE_ORDER")
@SequenceGenerator(
        name = "SQ_SIMPLE_ORDER_GENERATOR"
        , sequenceName = "SQ_SIMPLE_ORDER"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_SIMPLE_ORDER SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE SONO = ?")
public class SimpleOrder extends BaseTimeEntity2 {
    //    sono	number	주문번호
//    order_date	varchar2(1000 byte)	주문일자 : yyyy-mm-dd hh24:mi:ss
//    order_status	number	주문상태(50001: 주문완료, 50002: 결재완료, 50003: 상품준비중, 50004: 배송준비중, 50007:배송중, 50006:배송완료, 50007:주문확정 50011: 결재취소, 50012: 재고부족취소, 50013:고객취소)
//    product_amount	number	총 상품금액
//    delivery_amount	number	배송비
//    order_amount	number	주문금액 = 총 상품금액 + 배송비
//    delivery_addr	varchar2(1000 byte)	배송지 주소
//    delivery_msg	varchar2(400 byte)	배송지 메모
//    delete_yn	varchar2(1 byte)	삭제여부
//    insert_time	varchar2(255 byte)	추가일시
//    update_time	varchar2(255 byte)	수정일시
//    delete_time	varchar2(255 byte)	삭제일시
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_SIMPLE_ORDER_GENERATOR")
    private Integer sono;        // 주문번호, 기본키, 시퀀스

    private String orderDate;   // 주문일자
    private Integer orderStatus; // 주문상태(50001: 주문완료, 50002: 결재완료,  50011: 결재취소)
    private Integer productAmount; // 총 상품금액
    private Integer deliveryAmount; // 배송비
    private Integer orderAmount;    // 주문금액 = 총 상품금액 + 배송비
    private String deliveryAddr;   // 배송지 주소
    private String deliveryMsg;   // 배송지 메모
}
