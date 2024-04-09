// SimpleProductList.vue // vueInit
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
            @click="retrieveSimpleProduct"
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
      @click="retrieveSimpleProduct"
    ></b-pagination>
    <!-- {/* paging 끝 */} -->

    <div class="row">
      <div
        class="ms-5 col-lg-3 col-md-3 mt-5"
        v-for="(data, index) in simpleProduct"
        :key="index"
      >
        <!-- 반복되는 태그 : card -->
        <div class="card">
          <!-- 카드 이미지 -->
          <img :src="data.imgPath" class="card-img-top" alt="..." />
          <div class="card-body">
            <!-- 제목 #1 : 상품명 -->
            <h5 class="card-title">{{ data.title }}</h5>
            <!-- 제목 #2 : 단가 -->
            <h5 class="card-title">₩ {{ data.unitPrice }}</h5>
            <!-- 카드 상세페이지 이동 링크 -->
            <router-link
              :to="'/simple-cart/' + data.spno"
              class="btn btn-primary"
            >
              SimpleProduct Cart
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import SimpleProductService from "@/services/shop/simple-product/SimpleProductService";

export default {
  // TODO: 바인딩 속성(변수) 정의
  data() {
    return {
      simpleProduct: [], // spring 보내줄 배열변수
      searchTitle: "", // 검색어

      // 공통 페이징 속성
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },
  //   TODO: 함수 정의
  methods: {
    // TODO: 전체조회 함수    : 검색어 버튼 태그
    async retrieveSimpleProduct() {
      try {
        // TODO: 공통 전체조회 서비스 함수 실행
        // TODO: spring 통신 : 비동기 코딩 : async ~ await
        let response = await SimpleProductService.getAll(
                                                    this.searchTitle,
                                                    this.page - 1,
                                                    this.pageSize
                                                    );
        const { simpleProduct, totalItems } = response.data;
        this.simpleProduct = simpleProduct; // spring 전달 객체배열
        this.count         = totalItems;    // 전체페이지개수
        // 로깅(디버깅)
        console.log(response.data);         // 웹브라우저 콘솔탭에 spring 전달 객체배열이 표시됨
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러메세지가 표시됨
      }
    },
    // TODO: 공통 페이징 함수 : select 태그
    pageSizeChange() {
      this.page = 1; // 현재페이지번호 : 1
      this.retrieveSimpleProduct(); // 재조회
    },
  },
  //   TODO: 화면이 뜰때 자동 실행 함수
  mounted() {
    // TODO: 전체 조회 자동 실행
    this.retrieveSimpleProduct();
  },
};
</script>
<style></style>