// SimpleCartList.vue // 장바구니 전체 페이지(장바구니 가기 버튼) // vueInit
<template>
  <div>
    <!-- {/* dname start */} -->
    <div class="row mb-5 justify-content-center">
      <!-- {/* w-50 : 크기 조정, mx-auto : 중앙정렬(margin: 0 auto), justify-content-center */} -->
      <div class="col-12 w-50 input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="제목검색"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="retrieveSimpleCart"
          >
            검색
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
      @click="retrieveSimpleCart"
    ></b-pagination>
    <!-- {/* paging 끝 */} -->

    <div class="row">
      <div v-for="(data, index) in simpleCart" :key="index" class="card mb-3">
        <!-- 반복문 실행 : card  -->
        <div class="row g-0 p-3">
          <div class="col-md-4 p-3 border">
            <!-- 카드 이미지 -->
            <img
              :src="data.imgPath"
              class="img-fluid rounded-start"
              alt="..."
              style="{ height: 15 + 'vh', width: 5 + 'vw' }"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">물품명 : {{ data.title }}</h5>
              <h5 class="card-title">가격 : {{ data.unitPrice }}</h5>
              <h5 class="card-title">장바구니 개수: {{ data.cartCount }}</h5>
              <div class="mt-3">
                <!-- {/* 삭제 버튼 시작 */} -->
                <button
                  type="button"
                  class="btn btn-danger w-25"
                  @click="deleteSimpleCart(data.scno)"
                >
                  장바구니삭제
                </button>
                <!-- {/* 삭제 버튼 끝 */} -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="simpleCart" class="row d-flex justify-content-end">
      <!-- {/* 주문페이지 이동 버튼 시작 */} -->
      <button type="button" class="btn btn-warning w-25" @click="goOrder">
        주문
      </button>
      <!-- {/* 주문페이지 이동 버튼 끝 */} -->
    </div>
  </div>
</template>
<script>
import SimpleCartService from "@/services/shop/simple-product/SimpleCartService";
export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      simpleCart: [], // 장바구니 객체배열
      searchTitle: "", // 검색어
      // 공통 페이징 속성 정의
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },
  //   TODO: 함수 정의
  methods: {
    // TODO: 전체조회(장바구니) 함수 : 검색어 버튼, 화면이뜰때 자동 실행
    async retrieveSimpleCart() {
      try {
        // TODO: 공통 장바구니 전체 조회 서비스 함수 실행
        // TODO: 비동기 코딩 : async ~ await
        let response = await SimpleCartService.getAll(
          this.searchTitle,
          this.page - 1,
          this.pageSize
        );
        const { simpleCart, totalItems } = response.data;
        this.simpleCart = simpleCart;
        this.count = totalItems;
        // 로깅
        console.log(response.data); // 웹브라우저 콘솔탭에 벡엔드 데이터 표시
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러 표시
      }
    },
    // TODO: 장바구니 삭제 함수 : delete 버튼 태그
    async deleteSimpleCart(scno) {
      try {
        // TODO: 공통 장바구니 삭제 서비스 함수 실행
        let response = await SimpleCartService.remove(scno);
        // 로깅
        console.log(response.data);
        // alert 대화상자
        alert("정상적으로 삭제되었습니다.");
        // 삭제 후 재조회
        this.retrieveSimpleCart();
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 주문페이지 이동 함수
    goOrder() {
        this.$router.push("/simple-order");
    },
    // TODO: 공통 페이징 함수 : select 태그
    pageSizeChange() {
      this.page = 1; // 현재페이지번호 : 1
      this.retrieveSimpleCart(); // 재조회
    },
  },
  //   TODO: 화면이 뜰때 자동 실행 함수
  mounted() {
    // TODO: 화면이 뜰때 전체조회 실행
    this.retrieveSimpleCart();
  },
};
</script>
<style></style>