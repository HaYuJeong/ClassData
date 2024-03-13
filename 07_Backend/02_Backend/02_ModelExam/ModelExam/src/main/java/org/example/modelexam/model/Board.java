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
public class Board extends BaseTimeEntity {
    private Integer id;     // 게시판 번호
    private String title;   // 게시판 제목
    private String content; // 게시판 내용

    @Builder
    public Board(Integer id, String title, String content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
