package org.example.simpledms.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.simpledms.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity
 * fileName : Dept
 * author : GGG
 * date : 2024-04-02
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02         GGG          최초 생성
 */
@Entity
@Table(name = "TB_DEPT")
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_DEPT"
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
public class Dept extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_GENERATOR"
    )
    private Integer dno;   // 부서번호(기본키)

    private String  dname; // 부서명

    private String  loc;   // 부서위치

}

