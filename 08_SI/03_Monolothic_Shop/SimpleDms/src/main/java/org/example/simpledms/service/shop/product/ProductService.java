package org.example.simpledms.service.shop.product;

import org.example.simpledms.model.entity.shop.product.Product;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.repository.shop.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.shop.product
 * fileName : ProductService
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
public class ProductService {
    @Autowired
    ProductRepository productRepository ;

    //    TODO: 전체조회
    public Page<Product> findAll(String title, Pageable pageable){
        Page<Product> page = productRepository.findAllByPnameContaining(title, pageable);
        return page;
    }

    //    TODO: 상세조회
    public Optional<Product> findById(int pno){
        Optional<Product> product = productRepository.findById(pno);
        return product;
    }
}
