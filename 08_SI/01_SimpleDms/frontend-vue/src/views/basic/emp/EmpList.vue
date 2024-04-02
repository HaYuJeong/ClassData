<template>
  <div>
    <!-- 검색어 -->
    <div class="row mb-5 mt-5 justify-content-center">
      <!-- 입력박스 -->
      <!-- w-50 : w(width)-퍼센트 (25, 50, 75, 100) -->
      <div class="col-12 w-50 input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="사원명 검색"
          v-model="searchDname"
        />
        <button class="btn btn-outline-secondary" type="button">Search</button>
      </div>
    </div>

    <!-- 페이징 번호 -->
    <div class="row">
      <!-- 1페이지당 화면에 보일 개수 조정(select태그) -->
      <div class="col-12 w-25 mb-3">
        1페이지당 개수 :
        <!-- 복습 : v-model="pageSize" : 화면에 보일 초기값이 지정됨 -->
        <select class="form-select form-select-sm" v-model="pageSize">
          <!-- TODO: vue 반복문 실행 -->
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
      ></b-pagination>
    </div>

    <!-- 테이블 -->
    <div class="row">
      <div class="col-12">
        <!-- 테이블 디자인 -->
        <table class="table">
          <thead>
            <tr>
              <th scope="col">eno</th>
              <th scope="col">ename</th>
              <th scope="col">job</th>
              <th scope="col">manager</th>
              <th scope="col">hiredate</th>
              <th scope="col">salary</th>
              <th scope="col">commission</th>
              <th scope="col">dno</th>
              <th scope="col">action</th>
            </tr>
          </thead>
          <tbody>
            <!-- TODO: 반복문 -->
            <tr v-for="(data, index) in emp" :key="index">
              <td>{{ data.ename }}</td>
              <td>{{ data.job }}</td>
              <td>{{ data.manager }}</td>
              <td>{{ data.hiredate }}</td>
              <td>{{ data.salary }}</td>
              <td>{{ data.commission }}</td>
              <td>{{ data.dno }}</td>
              <td>{{ data.action }}</td>
              <!-- 수정페이지 링크 버튼 -->
              <td>
                <span class="badge rounded-pill text-bg-info">수정</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

// js 작성 부분
<script>
import EmpService from "../../../services/basic/EmpService";

export default {
  // TODO: data binding 속성 정의
  data() {
    return {
      dept: [], // spring으로 전송할 변수(속성)
      searchEname: "", // 부서명 검색 변수(속성)(input 태그와 바인딩)

      // 공통 속성(현재페이지, 전체데이터개수, 1페이지당개수)
      page: 1, // 현재페이지번호(아무것도 없을 시 1로 저장)
      count: 0, // 전체데이터개수
      pageSize: 3, // 1페이지당개수(select 태그와 바인딩)

      pageSizes: [3, 6, 9], // 1페이지당개수를 보여줄 배열(select 태그 - option과 바인딩)
    };
  },
  // TODO: vue 함수 정의 : 클릭/더블클릭 등
  methods: {
    // 공통 함수 : 페이징 관련 함수들
    // TODO: 페이지번호 변경 클릭 시 실행될 함수
    pageNoChange(value) {
      // this.속성 => data() 안의 속성들 접근
      this.page = value; // 1) 현재 페이지 변경
      this.retrieveEmp(); // 2) 재조회 요청
    },
    // TODO: select 박스 변경 시 실행될 함수
    pageSizeChange() {
      // TODO: js : event.target.value : 화면에서 선택 또는 클릭한 값
    //   this.pageSize = event.target.value; // 1) 화면 값을 페이지 크기 변수에 저장
      this.page = 1; // 2) 현재페이지번호 초기화(값은 1)
      this.retrieveEmp(); // 3) 화면 재조회 요청
    },
    // TODO: spring(백엔드) 전체 조회 요청 함수 : ★ 핵심 ★
    // TODO: 전체 조회 함수 : 비동기 함수(숙제를 다 내드리고 먼저 제출받는것부터 처리. 결과오는게 섞일수도 있음)
    // TODO: 사용법 : async 함수명 () { 변수 = await 함수명2(); console.log(변수) }
    async retrieveEmp() {
      try {
        // TODO: 1) 공통 전체 조회 함수 실행 : DeptService에 만들어놨음(이름 : getAll)
        let response = await EmpService.getAll(
          this.searchEname, // 검색어
          this.page - 1,    // 화면에서는 0번부터 보여주면 안되지만 백엔드에서는 0번부터 시작
          this.pageSize);   // 1페이지당개수(size)

        // const dept = response.data.dept;             // 부서배열(백엔드 전송)
        // const totalItems = response.data.totalItems; // 전체페이지수(백엔드 전송)

        // TODO: 2) es6 복습 : 객체분할 할당 (간략하게 쓰기 위함)
        const { emp, totalItems } = response.data; // 부서 배열(백엔드 전송)

        // TODO: 3) 바인딩 변수에 저장
        this.emp = emp; // 부서 배열(백엔드 전송)
        this.count = totalItems; // 전체페이지수(백엔드 전송)

        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
  },
//   TODO: 화면에 뜰 때 자동 실행되는 함수
    mounted(){
        // TODO: 최초 화면이 뜰 때 전체조회 실행
        this.retrieveEmp();
    }
};
</script>

// css 작성 부분
<style></style>