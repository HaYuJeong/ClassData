// SimpleOrderService.js
// 주문 공통 CRUD 함수
import http from "@/utils/http-common";

class SimpleOrderService{
    // TODO: 장바구니 저장함수
    create(data) {
        return http.post("/shop/simple-order", data);
    }
    // TODO: 주문 상세조회 함수 (주문은 하나씩 일어남, 전체조회 할 필요 없음)
    get(sono){
        return http.get(`/shop/simple-order/${sono}`);
    }
}

export default new SimpleOrderService;