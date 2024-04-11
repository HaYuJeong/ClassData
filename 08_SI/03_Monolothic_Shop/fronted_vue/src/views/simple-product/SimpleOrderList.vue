// SimpleOrderList.vue // 주문 전체 조회 페이지(핵심:복잡) // vueInit
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
     <div class="row" >
      <div class="card mb-3" v-for="(data, index) in simpleCart" :key="index">
        <div class="row g-0 p-3">
          <div class="col-md-4 p-3 border">
            <img
                :src="data.imgPath"
              class="img-fluid rounded-start"
              alt="..."
              style="{ height: 15 + 'vh', width: 5 + 'vw' }"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">상품 : {{ data.title }}</h5>
              <h5 class="card-title">상품 가격 : {{ data.unitPrice }}</h5>
              <h5 class="card-title">상품 개수 : {{ data.cartCount }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- {/* 배송지 시작 */} -->
    <div class="col-12 mx-auto">
      <div class="row g-3 align-items-center mb-3">
        <div class="col-3">
          <label htmlFor="deliveryAddr" class="col-form-label">
            배송주소
          </label>
        </div>

        <div class="col-9">
          <input
            type="text"
            id="deliveryAddr"
            required
            class="form-control"
            placeholder="배송지 주소"
            name="deliveryAddr"
            v-model="deliveryAddr"
          />
        </div>
      </div>

      <div class="row g-3 align-items-center mb-3">
        <div class="col-3">
          <label htmlFor="deliveryMsg" class="col-form-label">
            배송 메세지
          </label>
        </div>
        <div class="col-9">
          <input
            type="text"
            id="deliveryMsg"
            required
            class="form-control"
            placeholder="배송 메세지"
            name="deliveryMsg"
            v-model="deleveryMsg"
          />
        </div>
      </div>
    </div>

    <!-- {/* 배송지 끝 */} {/* 버튼 시작 */} -->
    <div class="row d-flex justify-content-end">
      <!-- {/* 취소 버튼 시작 */} -->
      <button
        type="button"
        class="btn btn-danger w-25 me-3"
        @click="cancelOrder"
      >
        주문 취소
      </button>
      <!-- {/* 취소 버튼 끝 */} {/* 결재 버튼 시작 */} -->
      <button type="button" class="btn btn-warning w-25" @click="goApproval">
        결제
      </button>
      <!-- {/* 결재 버튼 끝 */} -->
    </div>
    <!-- {/* 버튼 끝 */} -->
  </div>
</template>

<script>
import SimpleOrderService from "@/services/shop/simple-product/SimpleOrderService";
import SimpleCartService from '@/services/shop/simple-product/SimpleCartService';

export default {
  data() {
    return {
      simpleCart: [], // 장바구니 객체배열
      searchTitle: "", // 검색어

      // 배송관련된 입력 속성 정의
      deliveryAmount: 3000, // 배송비 : 하드코딩 : 3000원으로 고정
      deliveryAddr: "", // 배송지 주소
      deliveryMsg: "", // 배송 메세지

      // 공통 페이징 속성
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },
  methods: {
    // TODO: 장바구니 전체 조회 : 화면이 뜰 때 실행하기
    async retrieveSimpleProduct() {
      try {
        // TODO: 공통 전체조회 서비스 함수 실행
        // TODO: spring 통신 : 비동기 코딩 : async ~ await
        let response = await SimpleCartService.getAll(
          this.searchTitle,
          this.page - 1,
          this.pageSize
        );
        const { simpleCart, totalItems } = response.data;
        this.simpleCart = simpleCart; // spring 전달 객체배열
        this.count = totalItems; // 전체페이지개수
        // 로깅(디버깅)
        console.log(response.data); // 웹브라우저 콘솔탭에 spring 전달 객체배열이 표시됨
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러메세지가 표시됨
      }
    },
    // TODO: 주문 함수 : 1) 주문 테이블 + 주문 상세 테이블 동시에 insert 하기
    // TODO:            2) 결제 페이지로 이동
    async goApproval() {
      // TODO: 1) 주문날짜 : 현재 날짜
      let now = new Date(); // js 날짜 객체
      // 날짜포맷 : yyyy-mm-dd hh:mi:ss 형태
      // 년도 : now.getFullYear()
      // 월 : now.getMonth()
      // 일 : noew.getDate()
      // 시 : now.getHours()
      // 분 : now.getMinutes()
      // 초 : noww.getSeconds()
      let formatNow = `${now.getFullYear()}-${now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;

      //   TODO: 2) 장바구니의 상품별 금액 = 단가(unitPrice) * 개수(cartCount) -> 모든 상품별 총금액
      // 함수형 프로그래밍 3총사 : map, foreach, filter 등 + reduce
      // reduce : 배열변수에 있는 매개변수의 값들을 계속 누적시켜 합 또는 차를 구할 수 있는 함수
      // 누적합 알고리즘 : sum(누적변수) = sum(누적변수) + count(배열값)
      let totalPrice = this.simpleCart // 배열만 사용할 수 있는 함수. 내부적으로 반복문이 돈다. simpleCart에는 unitPrice, cartCount 다 있다.
        .map((data) => data.unitPrice * data.cartCount)
        // 사용법 : .reduce((누적변수, 배열값) => 누적변수 + 배열값);
        .reduce((acc, cur) => acc + cur); // 총금액

      // TODO: 3) 주문상세 객체 정의 (장바구니에 담고 한번에 주문. 주문번호는 같은데 상품이 다 다르니까 배열로 해야함)
      // TODO:   => ex) 주문번호(1) -> 주문상태(50001) : 주문(부모)
      // TODO:   =>     주문번호(1) -> 상품명(연필), 상품이미지, 장바구니개수 등
      // TODO:   =>     주문번호(1) -> 상품명(샤프), 상품이미지, 장바구니개수 등
      // TODO:   =>     주문번호(1) -> 상품명(지우개), 상품이미지, 장바구니개수 등
      let simpleOrderDetail = {
        // 객체 초기화시키기
        sono: null, // 주문번호는 일단 없음(null -> 이후 시퀀스 사용)
        spno: 0, // 상품번호도 일단 초기화
        productCount: 0, // 장바구니 개수 0개
      };
      // TODO: 주문상세 배열
      let simpleOrderDetailList = []; // 주문상세 객체배열 정의

      // 위의 주문상세 배열 만들기
      // 반복문 : java의 향상된 for문과 비슷한 javascript의 향상된 for문 : forof
      for (const data of this.simpleCart) {
        // simpleCart에 모든 정보가 다 있다.
        simpleOrderDetail.spno = data.spno; // 상품번호
        simpleOrderDetail.productCount = data.cartCount; // 장바구니 갯수 = 상품 갯수
        // 주문상세 배열에 주문상세 객체를 넣기
        // 사용법 : 배열변수.push(값);   // 배열에 값 넣기 함수
        simpleOrderDetailList.push(simpleOrderDetail); // 객체배열
      }

      // TODO: 4) 백엔드(spring) insert 요청 : 비동기 코딩 async ~ await
      try {
        // TODO: 임시 주문 객체 : 주문 상세 객체 배열 속성이 있음
        // 주문페이지, 주문상세페이지를 둘다 백엔드로 보내야함
        // 객체 안에 객체 배열을 넣어야 함
        let data = {
          // 백엔드의 이름과 동일해야 통신을 할 수 있음
          simpleOrderDetailList: simpleOrderDetailList, // 주문상세 객체배열(주문 상세 테이블 insert)
          orderDate: formatNow, // 주문날짜
          // 주문상태(50001: 주문완료, 50002: 결재완료, 50011:결재취소)
          orderStatus: 50001, // 주문상태
          productAmount: totalPrice, // 상품 총금액
          deliveryAmount: this.deliveryAmount, // 배달비(3000원으로 하드코딩 해놓음)
          orderAmount: totalPrice + this.deliveryAmount, // 총 주문 금액 : 상품 총금액 + 배달비 - 포인트(포인트있으면 포인트절감)
          deliveryAddr: this.deliveryAddr, // 배달 주소
          deliveryMsg: this.deliveryMsg, // 배달 메세지
        };
        // TODO: 공통 주문추가(create) 서비스 함수 실행
        let response = await SimpleOrderService.create(data);
        console.log(response.data);     // 로깅
        // 결제 페이지로 이동 : 결제 api 사용하면 여기에 코딩하기
        // -> 주문번호(response.data.sono) 도 전송하기
        this.$router.push("/simple-approval" + response.data.sono);
      } catch (e) {
        console.log(e); // 에러발생하면 출력하기
      }
    },
    // TODO: 주문 취소 : 장바구니 전체 페이지로 다시 이동
    cancelOrder() {
      this.$router.push("/simple-cart");
    },
    // TODO: 공통 페이징 함수
    pageSizeChange() {
      this.page = 1; // 현재 페이지 번호 : 1
      this.retrieveSimpleProduct(); // 재조회
    },
  },
  mounted() {
    this.retrieveSimpleProduct(); // 전체조회
  },
};
</script>
<style lang=""></style>
