package org.example.simpledms.controller.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.product.Cart;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.service.shop.product.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : org.example.simpledms.controller.shop.product
 * fileName : CartController
 * author : hayj6
 * date : 2024-04-10(010)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10(010)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class CartController {
    @Autowired
    CartService cartService ;

    //    TODO: 저장 함수
//    저장(insert) -> post 방식 -> @PostMapping
    @PostMapping("/product-cart")
    public ResponseEntity<Object> create(@RequestBody Cart cart){
        try{
            Cart cart1 = cartService.save(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
