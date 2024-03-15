package org.example.modelexam.testdata;

import lombok.Getter;
import lombok.Setter;
import org.example.modelexam.model.Faq;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.example.modelexam.testdata
 * fileName : SampleMember
 * author : kangtaegyung
 * date : 2022/10/14
 * description : 샘플 회원 데이터 클래스(DB 테이블 대체)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/14         kangtaegyung          최초 생성
 */
@Component
@Getter
@Setter
public class SampleFaq {
    List<Faq> list = new ArrayList<>();

    public SampleFaq() {

        int iCount = 1;

        Faq faq = Faq.builder()
                .fno(iCount)
                .subject("제목")
                .text("내용")
                .build();

        list.add(faq);

        faq = Faq.builder()
                .fno(++iCount)
                .subject("제목2")
                .text("내용2")
                .build();

        list.add(faq);

        faq = Faq.builder()
                .fno(++iCount)
                .subject("제목3")
                .text("내용3")
                .build();

        list.add(faq);

        faq = Faq.builder()
                .fno(++iCount)
                .subject("제목4")
                .text("내용4")
                .build();

        list.add(faq);

        faq = Faq.builder()
                .fno(++iCount)
                .subject("제목5")
                .text("내용5")
                .build();

        list.add(faq);
    }
}
