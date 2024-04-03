// DeptList.vue // vue 템플릿 단축키 : vueInit 
// html 작성 부분
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
          placeholder="부서명 검색"
          v-model="searchDname"
        />
        <button class="btn btn-outline-secondary" 
                type="button"
                @click="retrieveDept">검색</button>
      </div>
    </div>

    <!-- 페이징 번호 -->
    <div class="row">
      <!-- 1페이지당 화면에 보일 개수 조정(select태그) -->
      <div class="col-12 w-25 mb-3">
        1페이지당 개수 :
        <!-- 복습 : v-model="pageSize" : 화면에 보일 초기값이 지정됨 -->
        <select class="form-select form-select-sm" 
                v-model="pageSize"
                @change="pageSizeChange">
          <!-- TODO: vue 반복문 실행 -->
          <option v-for="(data, index) in pageSizes" :key="index" :value="data">
            {{ data }}
          </option>
        </select>
      </div>
      <!-- 페이지 번호 : b-pagination태그 -->
      <!-- 현재페이지(page), 1페이지당개수(pageSize)
           전체데이터개수(count)
       -->
      <!-- TODO: 사용법 
                  <b-pagination
                        v-model="현재페이지번호"       
                        :total-rows="전체데이터개수"
                        :per-page="1페이지당개수"
                    ></b-pagination>
        -->
      <b-pagination
        class="col-12 mb-3"
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        @click="retrieveDept"
      ></b-pagination>
    </div>

    <!-- 테이블 -->
    <div class="row">
      <div class="col-12">
        <!-- 테이블 디자인 -->
        <table class="table">
          <thead>
            <tr>
              <th scope="col">dno</th>
              <th scope="col">dname</th>
              <th scope="col">loc</th>
              <th scope="col">action</th>
            </tr>
          </thead>
          <tbody>
            <!-- TODO: 반복문 -->
            <tr v-for="(data, index) in dept" :key="index">
              <td>{{ data.dno }}</td>
              <td>{{ data.dname }}</td>
              <td>{{ data.loc }}</td>
              <!-- 수정페이지 링크 버튼 -->
              <td>
                <!-- TODO: 링크 : a 태그(전체 새로고침(성능저하) -> 페이지전환) -->
                <!-- TODO: 뷰에서 제공하는 링크 : router-link (부분만 새로고침 : 성능향상) -->
                <router-link :to="'/dept/' + data.dno">
                <span class="badge rounded-pill text-bg-success">수정</span>
                </router-link>
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
// TODO: 공통 CRUD 함수 import
import DeptService from "@/services/basic/DeptService";

export default {
  // TODO: data binding 속성 정의
  data() {
    return {
      dept: [], // spring으로 전송할 변수(속성)
      searchDname: "", // 부서명 검색 변수(속성)(input 태그와 바인딩)

      // 공통 속성(현재페이지, 전체데이터개수, 1페이지당개수)
      page: 1, // 현재페이지번호(아무것도 없을 시 1로 저장)
      count: 3, // 전체데이터개수
      pageSize: 3, // 1페이지당개수(select 태그와 바인딩)

      pageSizes: [3, 6, 9], // 1페이지당개수를 보여줄 배열(select 태그 - option과 바인딩)
    };
  },
  // TODO: vue 함수 정의 : 클릭/더블클릭 등
  methods: {
    // 공통 함수 : 페이징 관련 함수들
    // TODO: 페이지번호 변경 클릭 시 실행될 함수
    // TODO: b-pagination 태그와 연결
    pageNoChange(value) {
      // this.속성 => data() 안의 속성들 접근
      this.page = value; // 1) 현재 페이지 변경
      this.retrieveDept(); // 2) 재조회 요청
    },
    // TODO: select 박스 변경 시 실행될 함수
    // TODO: select 태그와 연결
    pageSizeChange() {
      // TODO: js : event.target.value : 화면에서 선택 또는 클릭한 값
    //   this.pageSize = event.target.value; // 1) 화면 값을 페이지 크기 변수에 저장
      this.page = 1; // 2) 현재페이지번호 초기화(값은 1)
      this.retrieveDept(); // 3) 화면 재조회 요청
    },
    // TODO: spring(백엔드) 전체 조회 요청 함수 : ★ 핵심 ★
    // TODO: 전체 조회 함수 : 비동기 함수(숙제를 다 내드리고 먼저 제출받는것부터 처리. 결과오는게 섞일수도 있음)
    // TODO: 사용법 : async 함수명 () { 변수 = await 함수명2(); console.log(변수) }
    async retrieveDept() {
      try {
        // TODO: 1) 공통 전체 조회 함수 실행 : DeptService에 만들어놨음(이름 : getAll)
        let response = await DeptService.getAll(
          this.searchDname, // 검색어
          this.page - 1,    // 화면에서는 0번부터 보여주면 안되지만 백엔드에서는 0번부터 시작
          this.pageSize);   // 1페이지당개수(size)

        // const dept = response.data.dept;             // 부서배열(백엔드 전송)
        // const totalItems = response.data.totalItems; // 전체페이지수(백엔드 전송)

        // TODO: 2) es6 복습 : 객체분할 할당 (간략하게 쓰기 위함)
        const { dept, totalItems } = response.data; // 부서 배열(백엔드 전송)

        // TODO: 3) 바인딩 변수에 저장
        this.dept = dept; // 부서 배열(백엔드 전송)
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
       this.retrieveDept();
   }
};
</script>

// css 작성 부분
<style></style>