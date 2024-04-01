package org.example.jpacustomexam.model.entity.exam02;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpacustomexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpacustomexam.model.entity.exam01
 * fileName : Dept
 * author : hayj6
 * date : 2024-03-29(029)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29(029)         hayj6          최초 생성
 */
@Entity
@Table(name="TB_DEPT2")
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_DEPT"
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
public class Dept2 extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_GENERATOR"
    )
    private Integer dno;    // 기본키, 시퀀스
    private String dname;
    private String loc;
}