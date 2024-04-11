// SimpleCartService.js
// 장바구니 공통 CRUD 함수
import http from "@/utils/http-common";

class SimpleCartService{
    // TODO: 상품 전체 조회
    getAll(title, page, size){
        return http.get(`/shop/simple-cart?title=${title}&page=${page}&size=${size}`);
    }
    // TODO: 장바구니 저장함수
    create(data) {
        return http.post("/shop/simple-cart", data);
    }
    // TODO: 장바구니 삭제함수 : 장바구니번호(scno)
    remove(scno){
        return http.delete(`/shop/simple-cart/deletion/${scno}`);
    }
}

export default new SimpleCartService;