package org.example.simpledms.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.simpledms.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.simpledms.model.entity.basic
 * fileName : Dept
 * author : hayj6
 * date : 2024-04-02(002)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02(002)         hayj6          최초 생성
 */
@Entity
@Table(name="TB_DEPT")  // 테이블명 : 자바파일 - 테이블명을 연결하는 어노테이션
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_DEPT"
        , initialValue = 1
        , allocationSize = 1
)

@DynamicInsert      // 옵션
@DynamicUpdate      // 옵션
// 롬북 어노테이션 : setter, getter, 생성자, toString 등
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept extends BaseTimeEntity {
    //    부서 속성필드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_GENERATOR"
    )
    private Integer dno;   // 부서번호(기본키)

    private String  dname; // 부서명

    private String  loc;   // 부서위치
}