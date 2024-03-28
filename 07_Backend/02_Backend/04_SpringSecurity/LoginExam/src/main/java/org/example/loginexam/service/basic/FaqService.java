package org.example.loginexam.service.basic;

import org.example.loginexam.model.entity.basic.Faq;
import org.example.loginexam.repository.basic.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : FaqService
 * author : GGG
 * date : 2024-03-21
 * description :
 * 요약 :
 * IOC : 제어의역전 (INVERSION OF CONTROL)
 * 스프링이 객체를 생성해줌
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         GGG          최초 생성
 */
@Service
public class FaqService {

    @Autowired
    FaqRepository faqRepository; // DI

    //    전체조회
    public Page<Faq> findAllByTitleContaining(
            String title, Pageable pageable
    ) {
//        like 검색
        Page<Faq> page = faqRepository
                .findAllByTitleContaining(title, pageable);
        return page;
    }

    //    상세 조회
    public Optional<Faq> findById(int fno) {
//        JPA 상세조회 함수 실행
        Optional<Faq> optionalFaq = faqRepository.findById(fno);
        return optionalFaq;
    }

    //    저장/수정 함수
    public Faq save(Faq faq) {
//        TODO: 기본키 없으면 insert, 있으면 update
        Faq faq2 = faqRepository.save(faq);
        return faq2;
    }

    //    삭제 함수
    public boolean removeById(int fno) {
        if (faqRepository.existsById(fno) == true) {
            faqRepository.deleteById(fno); // DB 삭제
            return true;
        } else {
            return false;
        }
    }
}
