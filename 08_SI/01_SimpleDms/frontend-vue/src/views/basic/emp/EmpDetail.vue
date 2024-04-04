<template>
  <div>
    <!-- null -> 거짓(false) -->
    <div v-if="emp">
      <div class="col-6 mx-auto">
        <div>
          <!-- ename -->
          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="ename" class="col-form-label"> ename </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="ename"
                required
                class="form-control"
                placeholder="ename"
                name="ename"
                v-model="emp.ename"
              />
            </div>
          </div>
          <!-- job -->
          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="job" class="col-form-label"> job </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="job"
                required
                class="form-control"
                placeholder="job"
                name="job"
                v-model="emp.job"
              />
            </div>
          </div>

          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="manager" class="col-form-label"> manager </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="manager"
                required
                class="form-control"
                placeholder="manager"
                name="manager"
                v-model="emp.manager"
              />
            </div>
          </div>

          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="hiredate" class="col-form-label">
                hiredate
              </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="hiredate"
                required
                class="form-control"
                placeholder="hiredate"
                name="hiredate"
                v-model="emp.hiredate"
              />
            </div>
          </div>

          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="salary" class="col-form-label"> salary </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="salary"
                required
                class="form-control"
                placeholder="salary"
                name="salary"
                v-model="emp.salary"
              />
            </div>
          </div>

          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="commission" class="col-form-label"
                >commission</label
              >
            </div>

            <div class="col-9">
              <input
                type="text"
                id="commission"
                required
                class="form-control"
                placeholder="commission"
                name="commission"
                v-model="emp.commission"
              />
            </div>
          </div>

          <div class="row g-3 align-items-center mb-3">
            <div class="col-3">
              <label htmlFor="dno" class="col-form-label"> dno </label>
            </div>

            <div class="col-9">
              <input
                type="text"
                id="dno"
                required
                class="form-control"
                placeholder="dno"
                name="dno"
                v-model="emp.dno"
              />
            </div>
          </div>
        </div>

        <div class="row g-3 mt-3 mb-3">
          <button
            type="submit"
            class="btn btn-outline-danger ms-3 col"
            @click="deleteEmp"
          >
            Delete
          </button>

          <button
            type="submit"
            class="btn btn-outline-success ms-2 col"
            @click="updateEmp"
          >
            Update
          </button>
        </div>

        <!-- TODO: message 있으면 true, "" 이면 false -->
        <p v-if="message != ''" class="alert alert-success mt-3 text-center">
          {{ message }}
        </p>
      </div>
    </div>

    <div v-else>
      <br />
      <p>Please click on a Emp...</p>
    </div>
  </div>
</template>

<script>
import EmpService from "@/services/basic/EmpService";

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      emp: null, //  초기값
      message: "", // 수정 성공 시 화면에 성공 메세지 출력하는 변수
    };
  },
  // TODO: 함수 정의
  methods: {
    // TODO: 상세조회 요청하는 함수 : 화면 뜰 때
    // TODO: 비동기 코딩 : async ~ await
    async getEmp(eno) {
      try {
        // TODO: 상세조회 공통함수 실행 : DeptService.get()
        let response = await EmpService.get(eno);
        this.emp = response.data; // 전체조회에서는 배열이었으나, 여기서는 객체 한개다. spring 결과를 바인딩 속성변수 dept 에 저장
        // 로깅
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 수정요청 함수
    async updateEmp() {
      try {
        // TODO: 공통 수정함수 : DeptService.update()
        // TODO: 비동기 코딩 : async ~ await
        let response = await EmpService.update(this.emp.eno, this.emp);
        // 로깅
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        this.message = "수정이 성공했습니다.";
      } catch (e) {
        console.log(e);
      }
    },

    // TODO: 삭제요청 함수
    async deleteEmp() {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          // 삭제 동작을 수행하는 코드
          let response = await EmpService.delete(this.emp.eno);
          console.log(response);
          alert("삭제되었습니다.");
          this.$router.push("/emp"); // 강제이동 코드. 지정한 url로 강제이동 해준다.
        } else {
          // 삭제를 취소하는 코드
          console.log("삭제가 취소되었습니다.");
          this.$router.push("/emp");
        }
      } catch (e) {
        console.log(e);
      }
    },
  },
  // TODO: 화면이 뜰 때 자동으로 실행되는 함수
  mounted() {
    this.message = ""; // 변수 초기화
    // TODO: 뷰 사용법 : 주소(라우터주소)에서 변수 가져오기 방법
    // TODO: 사용법 : this.$route.params.변수명
    // TODO: 변수명 확인 : router/index.js 에서 url 정의한곳에서 확인(/emp/:eno)
    this.getEmp(this.$route.params.eno); // 상세조회 함수 실행
  },
};
</script>

<style></style>
