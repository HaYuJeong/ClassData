package org.example.simpledms.model.entity.shop.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.simpledms.model.entity.shop.product
 * fileName : Cart
 * author : hayj6
 * date : 2024-04-10(010)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10(010)         hayj6          최초 생성
 */
@Entity
@Table(name="TB_CART")
@SequenceGenerator(
        name = "SQ_CART_GENERATOR"
        , sequenceName = "SQ_CART"
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
@SQLDelete(sql = "UPDATE TB_CART SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE CNO = ?")
public class Cart {
//    cno	number
//    pno	number
//    amount	number
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE
        , generator = "SQ_CART_GENERATOR"
)
    private Integer cno;
    private Integer pno;
    private Integer amount;
}
