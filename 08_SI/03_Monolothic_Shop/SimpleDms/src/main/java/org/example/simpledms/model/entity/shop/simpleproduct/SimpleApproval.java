package org.example.simpledms.model.entity.shop.simpleproduct;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.net.Inet4Address;

/**
 * packageName : org.example.simpledms.model.entity.shop.simpleproduct
 * fileName : SimpleApproval
 * author : GGG
 * date : 2024-04-12
 * description : 결재 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-12         GGG          최초 생성
 */
@Entity
@Table(name = "TB_SIMPLE_APPROVAL")
@SequenceGenerator(
        name = "SQ_SIMPLE_APPROVAL_GENERATOR"
        , sequenceName = "SQ_SIMPLE_APPROVAL"
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
public class SimpleApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_SIMPLE_APPROVAL_GENERATOR"
    )
    private Integer sano;            // 결재번호, 기본키, 시퀀스

    private Integer sono;            // 주문번호
    private String  approvalDate;    // 결재날짜
    private Integer approvalAmount;  // 결재금액
}
