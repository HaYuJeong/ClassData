// ProductList.vue // 상품 전체 페이지 // 연습 : SimpleProductList.vue 참고해서
// 상품전체 페이지 와 장바구니 상세페이지(저장)까지 완성하세요 // TB_PRODUCT,
TB_CART
<template>
  <div class="mt-5">
    <!-- {/* dname start */} -->
    <div class="row mb-5 justify-content-center">
      <!-- {/* w-50 : 크기 조정, mx-auto : 중앙정렬(margin: 0 auto), justify-content-center */} -->
      <div class="col-12 w-50 input-group mb-3">
        <!-- 검색 입력박스 -->
        <input
          type="text"
          class="form-control"
          placeholder="Search by Title"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <!-- 검색 버튼 -->
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="retrieveProduct"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- {/* dname end */} -->

    <!-- {/* paging 시작 */} -->
    <div class="col-12 w-25 mb-3">
      1페이지당 개수 :
      <select
        class="form-select form-select-sm"
        v-model="pageSize"
        @change="pageSizeChange"
      >
        <option v-for="(data, index) in pageSizes" :key="index" :value="data">
          {{ data }}
        </option>
      </select>
    </div>

    <b-pagination
      class="col-12 mb-3"
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      @click="retrieveProduct"
    ></b-pagination>
    <!-- {/* paging 끝 */} -->

    <div class="row">
      <div
        class="ms-5 col-lg-3 col-md-3 mt-5"
        v-for="(data, index) in product"
        :key="index"
      >
        <!-- 반복되는 태그 : card -->
        <div class="card">
          <!-- 카드 이미지 -->
          <img :src="data.image" class="card-img-top" alt="..." />
          <div class="card-body">
            <!-- 제목 #1 : 상품명 -->
            <h5 class="card-title">{{ data.pname }}</h5>
            <!-- 제목 #2 : 단가 -->
            <h5 class="card-title">₩ {{ data.unitPrice }}</h5>
            <!-- 카드 상세페이지 이동 링크 -->
            <router-link
              :to="'/product-cart/' + data.pno"
              class="btn btn-primary"
            >
              Product Cart
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/shop/product/ProductService";

export default {
  data() {
    return {
      product: [],
      searchTitle: "",

      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },
  methods: {
    async retrieveProduct() {
      try {
        // TODO: 공통 전체조회 서비스 함수 실행
        let response = await ProductService.getAll(
          this.searchTitle,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product; // spring 전달 객체배열
        this.count = totalItems; // 전체페이지개수
        // 로깅(디버깅)
        console.log(response.data); // 웹브라우저 콘솔탭에 spring 전달 객체배열이 표시됨
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러메세지가 표시됨
      }
    },
  },
  mounted() {
    this.retrieveProduct();
  },
};
</script>
<style></style>
