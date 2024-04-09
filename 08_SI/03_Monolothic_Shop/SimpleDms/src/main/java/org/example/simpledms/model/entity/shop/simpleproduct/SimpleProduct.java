package org.example.simpledms.model.entity.shop.simpleproduct;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.shop.simpleproduct
 * fileName : SimpleProduct
 * author : GGG
 * date : 2024-04-09
 * description : 상품 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09         GGG          최초 생성
 */
@Entity
@Table(name = "TB_SIMPLE_PRODUCT")
@SequenceGenerator(
        name = "SQ_SIMPLE_PRODUCT_GENERATOR"
        , sequenceName = "SQ_SIMPLE_PRODUCT"
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
public class SimpleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_SIMPLE_PRODUCT_GENERATOR"
    )
    private Integer spno; // 기본키, 시퀀스, 상품번호
    private Integer codeId; // 상품종류코드
    private String  title; // 상품명
    private String  imgPath; // 이미지 경로
    private Integer  unitPrice; // 단가
    private Integer  inventoryCount; // 재고수량
    private String  useYn; // 사용여부
}
