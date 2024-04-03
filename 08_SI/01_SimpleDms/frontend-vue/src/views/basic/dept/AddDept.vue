<template>
  <div>
    <div class="row mt-5">
      <div v-if="!submitted">
        <div class="col-6 mx-auto">
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

          <div class="row g-3 mt-3 mb-3">
            <button class="btn btn-outline-primary ms-2 col" @click="saveDept">
              Submit
            </button>
          </div>
        </div>
      </div>

      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" @click="newDept">저장페이지</button>
      </div>
    </div>
  </div>
</template>

// js 작성
<script>
// DeptService 에 있는 함수를 쓰기 위해 import 시키기
import DeptService from "@/services/basic/DeptService";

export default {
  // TODO: data binding 속성 정의
  data() {
    return {
      dept: {
        dname: "",
        loc: "",
      }, // 조회는 여러개나와서 배열, 추가는 객체 하나만 추가하는거. 객체 하나 초기화시키는건 빈 객체로 생성
      submitted: false, // 저장버튼 클릭하면 true 로 바뀜
    };
  },
  // TODO: 함수 정의
  methods: {
    // TODO: 저장(추가:insert) 함수 : save : CRUD는 전부 비동기로 만들어야 함(백엔드와 통신하는 건 전부 비동기)
    // TODO: 비동기 코딩 : async ~ await 이용
    async saveDept() {
      
    
         try {
        let data = {
           dname: this.dept.dname, // html 태그와 바인딩 할 예정
           loc: this.dept.loc, // html 태그와 바인딩 할 예정
         };
        if (data.dname == "" || data.loc == "") {
          return;
        }else{
             // 임시 객체 변수
          // TODO: 백엔드로 객체 추가 요청할 함수
          let response = await DeptService.create(data); // 위에서 만든 임시 객체 변수를 넣으면 insert 해줌, 백엔드와 통신하니까 비동기로 작성
          // TODO: 콘솔에 결과 출력
          console.log(response);
          this.submitted = true; // 저장 유무 변수에 true 넣기
         
        }
            } catch (e) {
          console.log(e);}
    
   
  },
      // TODO: 저장페이지 열기 함수 : 화면 초기화
    newDept() {
      // TODO: vue/react : 변수값을 조작하면 화면이 자동 갱신됨. 저장 페이지 url로 이동해도 되지만 변수를 바꿔서 이동하는게 더 쉬움
      this.submitted = false; // 다시 최초의 false 값으로 바뀜
      this.dept = {}; // dept 도 초기화 됨
    },
}
}
</script>

// css 작성
<style></style>