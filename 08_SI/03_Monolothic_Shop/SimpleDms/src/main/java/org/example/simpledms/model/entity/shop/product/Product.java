package org.example.simpledms.model.entity.shop.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.shop.product
 * fileName : Product
 * author : hayj6
 * date : 2024-04-09(009)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09(009)         hayj6          최초 생성
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
public class Product {
//    pno	number
//    kind_code	number
//    pname	varchar2(255 byte)
//    image	varchar2(255 byte)
//    unit_price	number
//    inventory_count	number
//    use_yn	varchar2(1 byte)
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE
        , generator = "SQ_SIMPLE_PRODUCT_GENERATOR"
)
    private Integer pno;
    private Integer kindCode;
    private String pname;
    private String image;
    private Integer unitPrice;
    private Integer inventoryCount;
    private String useYn;

}
