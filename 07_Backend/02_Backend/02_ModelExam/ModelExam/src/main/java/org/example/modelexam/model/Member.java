package org.example.modelexam.model;

import lombok.*;

/**
 * packageName : com.example.modelexam.model
 * fileName : Member
 * author : kangtaegyung
 * date : 2022/10/14
 * description : 회원 정보 테이블
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/14         kangtaegyung          최초 생성
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    private Integer eno;        // 사원번호
    private String ename;       // 사원명
    private String job;         // 직위
    private Integer manager;    // 관리자 사원번호

    @Builder
    public Member(Integer eno, String ename, String job, Integer manager) {
        super();
        this.eno = eno;
        this.ename = ename;
        this.job = job;
        this.manager = manager;
    }
}
