package org.example.jpacustomexam.model.entity.exam01;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpacustomexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpacustomexam.model.entity.exam01
 * fileName : Emp
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
@Table(name = "TB_EMP")
@SequenceGenerator(
        name = "SQ_EMP_GENERATOR"
        , sequenceName = "SQ_EMP"
        , initialValue = 1
        , allocationSize = 1
)
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Emp extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMP_GENERATOR"
    )
    private Integer eno;

    private String ename;

    private String job;

    private Integer manager;

    private String hiredate;

    private Integer salary;

    private Integer commission;

    private Integer dno;
}