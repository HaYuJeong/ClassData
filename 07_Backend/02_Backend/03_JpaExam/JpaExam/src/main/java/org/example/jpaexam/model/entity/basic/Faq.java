package org.example.jpaexam.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpaexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpaexam.model.entity.basic
 * fileName : Faq
 * author : hayj6
 * date : 2024-03-21(021)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21(021)         hayj6          최초 생성
 */
@Entity
@Table(name="TB_FAQ")
@SequenceGenerator(
        name = "SQ_FAQ_GENERATOR"
        , sequenceName = "SQ_FAQ"
        , initialValue = 1
        , allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Faq extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
    , generator = "SQ_FAQ_GENERATOR"
    )
    private Integer fno;
    private String title;
    private String content;
}
