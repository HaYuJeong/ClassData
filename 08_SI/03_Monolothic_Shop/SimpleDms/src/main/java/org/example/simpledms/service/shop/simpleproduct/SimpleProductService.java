package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.repository.shop.simpleproduct.SimpleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.shop.simpleproduct
 * fileName : SimpleProductService
 * author : hayj6
 * date : 2024-04-09(009)
 * description : 상품 서비스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09(009)         hayj6          최초 생성
 */
@Service
public class SimpleProductService {
    @Autowired
    SimpleProductRepository simpleProductRepository;    // DI

//    TODO : 전체조회
    public Page<SimpleProduct> findAllByTitleContaining(String title, Pageable pageable){
        Page<SimpleProduct> page = simpleProductRepository.findAllByTitleContaining(title, pageable);
        return page;
    };

//    TODO: 상세조회
    public Optional<SimpleProduct> findById(int spno){
        Optional<SimpleProduct> simpleProduct = simpleProductRepository.findById(spno);
        return simpleProduct ;
    }
}
