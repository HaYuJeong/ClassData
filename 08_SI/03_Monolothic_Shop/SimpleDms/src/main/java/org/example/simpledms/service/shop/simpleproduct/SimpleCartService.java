package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.repository.shop.simpleproduct.SimpleCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SimpleCart save(SimpleCart simpleCart){
        SimpleCart simpleCart1 = simpleCartRepository.save(simpleCart);
        return simpleCart1 ;
    }
}
