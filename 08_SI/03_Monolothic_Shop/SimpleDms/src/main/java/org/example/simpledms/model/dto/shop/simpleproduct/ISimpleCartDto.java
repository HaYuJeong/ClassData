package org.example.simpledms.model.dto.shop.simpleproduct;

/**
 * packageName : org.example.simpledms.model.dto.shop.simpleproduct
 * fileName : ISimpleCartDto
 * author : GGG
 * date : 2024-04-11
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11         GGG          최초 생성
 */
public interface ISimpleCartDto {
    Integer getScno();       // 기본키, 시퀀스, 장바구니 번호

    Integer getSpno();       // 기본키, 시퀀스, 상품 번호
    Integer getCodeId();     // 코드번호

    String getTitle();
    String getImgPath();
    Integer getUnitPrice();
    Integer getCartCount();
}
