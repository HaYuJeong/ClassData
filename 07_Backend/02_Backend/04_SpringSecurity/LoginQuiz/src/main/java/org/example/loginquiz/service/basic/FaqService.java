package org.example.loginquiz.service.basic;

import org.example.loginquiz.model.entity.basic.Faq;
import org.example.loginquiz.repository.basic.FaqRepository;
import org.example.loginquiz.model.entity.basic.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : FaqService
 * author : hayj6
 * date : 2024-03-21(021)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21(021)         hayj6          최초 생성
 */
@Service
public class FaqService {
    @Autowired
    FaqRepository faqRepository;

//    TODO: 1. 전체 조회
    public List<Faq> findAll(){
        List<Faq> list = faqRepository.findAll();
        return list;
    }

//    페이징 처리를 위한 Page 객체
    public Page<Faq> findAllByTitleContaining(String title, Pageable pageable){
        Page<Faq> page = faqRepository.findAllByTitleContaining(title, pageable);
        return page;
    }

//    TODO: 2. 상세 조회
    public Optional<Faq> findById(int fno){
        Optional<Faq> optionalFaq = faqRepository.findById(fno);
        return optionalFaq;
    }

//   TODO: 3. 저장/수정
    public Faq save(Faq faq){
        Faq faq2 = faqRepository.save(faq);
        return faq2;
    }

//    TODO: 4. 삭제
public boolean removeById(int fno) {
//        JPA 삭제 함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true로 리턴
//        2) 없으면 false 리턴
//        TODO: 사용법 : jpa레포지토리.existById(기본키)
//                     => 기본키가 테이블에 있는지 확인 -> 있으면 true, 없으면 false
    if (faqRepository.existsById(fno) == true) {
        faqRepository.deleteById(fno);
        return true;
    } else {
        return false;
    }
}
}