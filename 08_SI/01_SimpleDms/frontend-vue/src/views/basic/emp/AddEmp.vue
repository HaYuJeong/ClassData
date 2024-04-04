// AddEmp.vue // 연습 ) 저장페이지 기능 + spring 저장 컨트롤러함수 // (/emp,
post 방식) // vueInit // 참고) 부서참고
<template>
  <div class="row">
    <div v-if="!submitted">
      <div class="col-6 mx-auto">
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

        <!-- manager -->
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

        <!-- hiredate -->
        <div class="row g-3 align-items-center mb-3">
          <div class="col-3">
            <label htmlFor="hiredate" class="col-form-label"> hiredate </label>
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

        <!-- salary -->
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

        <!-- commission -->
        <div class="row g-3 align-items-center mb-3">
          <div class="col-3">
            <label htmlFor="commission" class="col-form-label">
              commission
            </label>
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

        <!-- dno -->
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

        <div class="row g-3 mt-3 mb-3">
          <button class="btn btn-outline-primary ms-2 col" @click="saveEmp">
            저장
          </button>
        </div>
      </div>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEmp">저장페이지</button>
    </div>
  </div>
</template>
<script>
import EmpService from '@/services/basic/EmpService';

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      emp: {},         // 빈 객체
      submitted: false // 저장버튼 클릭하면 true 바뀜
    };
  },
  // TODO: 함수 정의
  methods: {
    // TODO: 저장(추가:insert) 함수 : CRUD (비동기)
    // TODO: 비동기 코딩 : async ~ await
    async saveEmp() {
      try {
        // 임시 객체 변수
        let data = {
          ename: this.emp.ename,
          job: this.emp.job,
          manager: this.emp.manager,
          hiredate: this.emp.hiredate,
          salary: this.emp.salary,
          commission: this.emp.commission,
          dno: this.emp.dno
        };

        // TODO: 벡엔드로 객체 추가 요청
        let response = await EmpService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true; // 저장유무변수=true 변경
      } catch (e) {
        console.log(e);
      }
    },
    // 저장페이지 열기 함수 : 화면 초기화
    newEmp() {
      // TODO: 뷰/리액트 : 변수값을 조작하면 화면이 자동 갱신됨
      this.submitted = false;
      this.emp = {};
    },
  },
};
</script>
<style></style>