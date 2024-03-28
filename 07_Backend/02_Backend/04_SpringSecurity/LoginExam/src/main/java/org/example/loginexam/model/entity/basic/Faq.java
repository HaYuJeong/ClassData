package org.example.loginexam.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.loginexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpaexam.model.entity.basic
 * fileName : Faq
 * author : GGG
 * date : 2024-03-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         GGG          최초 생성
 */
@Entity
@Table(name = "TB_FAQ")
@SequenceGenerator(
        name = "SQ_FAQ_GENERATOR"
        , sequenceName = "SQ_FAQ"
        , initialValue = 1
        , allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
// 롬북 어노테이션 : setter, getter, 생성자, tostring 등
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Faq extends BaseTimeEntity {
//    TODO: 종합 연습 문제 : 부서게시판 샘플을 참고하여
//     Faq 게시판을 만드세요 : 함수명 , url 자유롭게 코딩하세요
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FAQ_GENERATOR"
    )
    private Integer fno; // 번호(기본키, 시퀀스)

    private String title; // 내용

    private String content; // 내용

}
