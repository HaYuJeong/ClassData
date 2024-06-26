package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.dto.shop.simpleproduct.ISimpleCartDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.repository.shop.simpleproduct.SimpleCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.simpleproduct
 * fileName : SimpleCartService
 * author : hayj6
 * date : 2024-04-09(009)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09(009)         hayj6          최초 생성
 */
@Service
public class SimpleCartService {
    @Autowired
    SimpleCartRepository simpleCartRepository;

    //    TODO: 저장(수정) 함수
    public SimpleCart save(SimpleCart simpleCart) {
        SimpleCart simpleCart1 = simpleCartRepository.save(simpleCart);
        return simpleCart1;
    }

    //    TODO : 전체조회
    public Page<ISimpleCartDto> selectByTitleContaining(String title, Pageable pageable) {
        Page<ISimpleCartDto> page = simpleCartRepository.selectByTitleContaining(title, pageable);
        return page;
    }

    ;

    //    TODO: 삭제 함수
    public boolean removeById(int scno) {
        if (simpleCartRepository.existsById(scno)) {
            simpleCartRepository.deleteById(scno);
            return true;
        } else {
            return false;
        }
    }
}
