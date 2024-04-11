// SimpleOrderService.js
// 주문 공통 CRUD 함수
import http from "@/utils/http-common";

class SimpleOrderService{
    // TODO: 장바구니 저장함수
    create(data) {
        return http.post("/shop/simple-order", data);
    }
}

export default new SimpleOrderService;