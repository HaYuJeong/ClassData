<template>
  <div>
    <div>
      <h1>SimpleApproval</h1>

      <div class="col-md-12">
        <!-- {/* table start */} -->
        <table class="table">
          <thead>
            <tr>
              <th scope="col">sono<br />&nbsp;</th>
              <th scope="col">order<br />Date</th>
              <th scope="col">order<br />Status</th>
              <th scope="col">product<br />Amount</th>
              <th scope="col">delivery<br />Amount</th>
              <th scope="col">order<br />Amount</th>
              <th scope="col">delivery<br />Addr</th>
              <th scope="col">delivery<br />Msg</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <!-- es6 사용법 : 변수? :변수가 null 이면 undefined 로 바꿔줌 : null 방지 방법 -->
              <!-- 프론트 객체 = 백엔드 simpleOrder 엔티티클래스의 속성명 -->
              <td>{{ simpleOrder?.sono }}</td>
              <td>{{ simpleOrder?.orderDate }}</td>
              <td>{{ simpleOrder?.orderStatus }}</td>
              <td>{{ simpleOrder?.productAmount }}</td>
              <td>{{ simpleOrder?.deliveryAmount }}</td>
              <td>{{ simpleOrder?.orderAmount }}</td>
              <td>{{ simpleOrder?.deliveryAddr }}</td>
              <td>{{ simpleOrder?.deliveryMsg }}</td>
            </tr>
          </tbody>
        </table>
        <!-- {/* table end */} -->
      </div>

      <h1>SimpleApproval Admin Panel</h1>

      <div class="row d-flex justify-content-end">
        <button
          type="button"
          class="btn btn-danger w-25 me-3"
          @click="cancelApproval"
        >
          결제 취소
        </button>

        <button
          type="button"
          class="btn btn-warning w-25"
          @click="confirmApproval"
        >
          결제 확정
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import SimpleOrderService from "@/services/shop/simple-product/SimpleOrderService";
import SimpleApprovalService from "@/services/shop/simple-product/SimpleApprovalService";

export default {
  data() {
    return {
      simpleOrder: null, // 주문 객체
      simpleApproval: null, // 결제 객체
      sono: this.$route.params.sono, // 주문번호
    };
  },
  methods: {
    // TODO: 주문 상세조회 함수 : 화면이 뜰 때 실행
    async retrieveSimpleOrder() {
      try {
        let response = await SimpleOrderService.get(this.sono);
        this.simpleOrder = response.data;
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 결제 확정 함수★     : 결제 버튼
    async confirmApproval() {
      // TODO: 1) 결제날짜 : 현재날짜
      let now = new Date();
      // 날짜 포맷 : yyyy-mm-dd hh:mi:ss
      let formatNow = `${now.getFullYear()}-${now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;

      // TODO: 2) 임시 객체 정의
      let data = {
        sano: null,                     // 결제번호    
        sono: this.simpleOrder?.sono,   // 상세조회했기 때문에 값을 가지고 있음
        approvalDate: formatNow,        // 결제날짜
        approvalAmount: this.simpleOrder?.orderAmount // 주문금액 = 결제금액이라서 값을 가지고 옴옴
      }
      // TODO: 3) 결제 테이블에 insert 실행
      // TODO: 공통 결제 추가 서비스 함수 실행
      try{
        let response = await SimpleApprovalService.create(data);
        console.log(response.data);
        alert("결제가 완료되었습니다.");
        // TODO: 주문상태 : 50001(주문완료) -> 50002(결제완료)로 바뀜 : 주문테이블 상세조회페이지 재조회
        this.retrieveSimpleOrder();
      } catch(e){
        console.log(e);
      }
    },
    // TODO: 결제 취소 함수       : 결제 취소 버튼
    cancelApproval() {
        alert("결제가 취소되었습니다.")
      this.$router.push("/simple-order");
    },
  },
  mounted() {
    this.retrieveSimpleOrder(); // 화면 뜰 때 주문전체 조회 실행
  },
};
</script>
<style lang=""></style>