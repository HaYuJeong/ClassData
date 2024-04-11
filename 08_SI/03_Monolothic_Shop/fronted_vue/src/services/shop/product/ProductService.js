import http from "@/utils/http-common";

class ProductService {
  // TODO: 전체 조회
  getAll(pname, page, size) {
    return http.get(`/shop/product?pname=${pname}&page=${page}&size=${size}`);
  }
  // TODO: 상품 상세 조회 : 기본키(상품번호(spno))
  get(pno) {
    return http.get(`/shop/product/${pno}`);
  }
}

export default new ProductService();
