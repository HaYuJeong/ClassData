<template>
  <div>
    <div v-if="dept">
      <div class="col-6 mx-auto">
        <div>
          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="dname" class="col-form-label"> Dname </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="dname"
                required
                class="form-control"
                placeholder="dname"
                name="dname"
                v-model="dept.dname"
              />
            </div>
          </div>

          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="loc" class="col-form-label"> Loc </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="loc"
                required
                class="form-control"
                placeholder="loc"
                name="loc"
                v-model="dept.loc"
              />
            </div>
          </div>
        </div>

        <div class="row g-3 mt-3 mb-3">
          <button class="btn btn-outline-danger ms-3 col" @click="deleteDept">
            Delete
          </button>

          <button
            type="submit"
            class="btn btn-outline-primary ms-2 col"
            @click="updateDept"
          >
            Update
          </button>
        </div>

        <!-- TODO: 메세지가 있으면 true라서 {{message}}가 보이고, 없어서 ""이면 false-->
        <p v-if="message" class="alert alert-success mt-3 text-center">
          {{ message }}
        </p>
      </div>
    </div>

    <div v-else>
      <br />
      <p>Please click on a Dept...</p>
    </div>
  </div>
</template>

<script>
import DeptService from "@/services/basic/DeptService";

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      dept: null, // dept 초기값
      message: "", // 수정 성공 시 화면에 성공 메세지 출력하는 변수
    };
  },
  // TODO: 함수 정의
  methods: {
    // TODO: 상세조회 요청하는 함수 : 화면 뜰 때
    // TODO: 비동기 코딩 : async ~ await
    async getDept(dno) {
      try {
        // TODO: 상세조회 공통함수 실행 : DeptService.get()
        let response = await DeptService.get(dno);
        this.dept = response.data; // 전체조회에서는 배열이었으나, 여기서는 객체 한개다. spring 결과를 바인딩 속성변수 dept 에 저장
        // 로깅
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 수정요청 함수 : 시점은 상세조회가 끝난 후이기 때문에 부서객체가 있음. 바로 갖다쓰면 됨
    async updateDept() {
      try {
        // TODO: 공통 수정함수
        // TODO: 비동기 코딩 : async ~ await
        let response = await DeptService.update(this.dept.dno, this.dept); // 수정버튼을 누른다는것은, 이미 상세조회가 끝난이후며 dno에 값이 다 들어와있음.
        console.log(response.data); // 로깅
        this.message = "수정이 성공했습니다."; // 화면에 성공메세지 출력 : message 바인딩 변수
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 삭제요청 함수
    deleteDept() {},
  },
  // TODO: 화면이 뜰 때 자동으로 실행되는 함수
  mounted() {
    this.message = ""; // 변수 초기화
    // TODO: 뷰 사용법 : 주소(라우터주소)에서 변수 가져오기 방법
    // TODO: 사용법 : this.$route.params.변수명
    // TODO: 변수명 확인 : router/index.js 에서 url 정의한곳에서 확인(/dept/:dno)
    this.getDept(this.$route.params.dno); // 상세조회 함수 실행
  },
};
</script>

<style></style>
