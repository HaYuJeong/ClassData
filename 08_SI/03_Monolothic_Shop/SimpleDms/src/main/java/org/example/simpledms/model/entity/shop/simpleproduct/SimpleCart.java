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
 * fileName : SimpleCart
 * author : hayj6
 * date : 2024-04-09(009)
 * description : 장바구니 엔티티
 * 요약 :
 *      모델링 : 마스터성 테이블 vs 히스토리성(이력) 테이블(개수가 무한정 증가)
 *                              [ 추가/수정/삭제 일시를 관리하는게 좋음 ]
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09(009)         hayj6          최초 생성
 */
@Entity
@Table(name="TB_SIMPLE_CART")
@SequenceGenerator(
        name = "SQ_SIMPLE_CART_GENERATOR"
        , sequenceName = "SQ_SIMPLE_CART"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_SIMPLE_CART SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE SCNO = ?")
public class SimpleCart extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_SIMPLE_CART_GENERATOR"
    )
    private Integer scno; // 기본키, 시퀀스, 장바구니번호

    private Integer spno; // 상품번호, 참조키(외래키:FK)
    private Integer cartCount; // 장바구니 개수
}

