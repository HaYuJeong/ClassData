package org.example.modelexam.model;

import lombok.*;

/**
 * packageName : com.example.modelexam.model
 * fileName : Board
 * author : kangtaegyung
 * date : 2022/10/16
 * description : 게시판 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/16         kangtaegyung          최초 생성
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Faq extends BaseTimeEntity {
    private Integer fno;
    private String subject;
    private String text;

    @Builder
    public Faq(Integer fno, String subject, String text) {
        super();
        this.fno = fno;
        this.subject = subject;
        this.text = text;
    }
}
