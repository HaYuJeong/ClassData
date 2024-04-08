// FileDbList.vue // vueInit
<template>
  <div>
    <div>
      <div class="col-md-8">
        <!-- {/* 검색어 start */} -->
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="제목을 검색하세요"
            v-model="searchTitle"
          />
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" 
                    type="button"
                    @click="retrieveFileDb">
              Search
            </button>
          </div>
        </div>
        <!-- {/* 검색어 end */} -->
      </div>

      <div class="col-md-12 mt-3">
        <h4>FileDb List</h4>
        <!-- {/* page start */} -->
        <div class="mb-3">
          Items per Page:
          <select v-model="pageSize" @change="pageSizeChange">
            <option v-for="(data,index) in pageSizes" :key="index" :value="data">
                {{data}}
            </option>
          </select>
        </div>

        <!-- b-pagination : 부트스트랩 - 페이지 번호 컨트롤 -->
        <!-- total-rows : 전체 데이터 개수 -->
        <!-- per-page : 1페이지 당 개수 -->
        <!-- change : handlePageChange(), 페이지 번호 변경 시 실행되는 이벤트 -->
        <b-pagination
          v-model="page"
          :total-rows="count"
          :per-page="pageSize"
          @click="retrieveFileDb"
        ></b-pagination>
        <!-- {/* page end */} -->

        <!-- {/* 쇼핑카트 이미지 start */} -->
        <div class="row">
          <div v-for="(data,index) in fileDb" :key="index" class="col-sm-6">    <!-- 반복문 돌리기 -->
            <div class="card">
                <!-- 카드 이미지(data.fileUrl) -->
              <img :src="data.fileUrl" class="card-img-top" alt="강의" />   <!-- :src 속성 작성-->
              <!-- 본문 : 제목 + 상세글 -->
              <div class="card-body">
                <!-- 제목 -->
                <h5 class="card-title">{{data.fileTitle}}</h5>
                <!-- 상세글(내용) -->
                <p class="card-text">{{data.fileContent}}</p>
                <!-- 링크 걸기, 변수와 쓰기 위해 : 붙여서 :to -->
                <router-link :to="'/fileDb/' + data.uuid">
                <span class="badge bg-warning">수정</span>
                </router-link>
                <a
                  style="
                     {
                      color: inherit;
                    }
                  "
                  class="ms-2"
                  @click="deleteFileDb(data.uuid)"      
                >
                <!-- @click : 클릭이벤트 함수 걸기-->
                  <span class="badge bg-danger">삭제</span>
                </a>
              </div>
            </div>
          </div>
        </div>
        <!-- {/* 쇼핑카트 이미지 end */} -->
      </div>
    </div>
  </div>
</template>

<script>
import FileDbService from "@/services/advanced/FileDbService";

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      fileDb: [], // spring 전송 파일 배열
      searchTitle: "", // 제목 검색할 변수
      // 공통 속성(현재페이지, 전체데이터개수, 1페이지당개수)
      page: 1, // 현재페이지번호(아무것도 없을 시 1로 저장)
      count: 0, // 전체데이터개수
      pageSize: 3, // 1페이지당개수(select 태그와 바인딩)

      pageSizes: [3, 6, 9], // 1페이지당개수를 보여줄 배열(select 태그 - option과 바인딩)
    };
  },
  // TODO: 함수 정의
  methods: {
    // TODO: 파일 전체 조회 함수
    async retrieveFileDb() {
        try{
        // TODO: 공통 전체 조회 함수 : getAll()
        // TODO: 비동기 코딩 : async ~ await
        let response = await FileDbService.getAll(this.searchTitle, this.page - 1, this.pageSize); // 제목 검색어, 현재페이지번호, 1페이지당개수
        // TODO: 객체 분할 할당 , try catch 로 에러발생하면 console.log 로 출력
        const {fileDb, totalItems} = response.data;
        this.fileDb = fileDb;           // 파일 배열 : 위에서 반복문 돌림
        this.totalItems = totalItems;   // 전체 데이터 개수
        } catch(e){
            console.log(e);
        }
    },
    // TODO: 삭제 함수
    async deleteFileDb(uuid) {
      try {
        let response = await FileDbService.delete(uuid);  // 이미 전체조회 페이지라서 강제이동 할 필요가 없음
        console.log(response.data);  // 로깅 : 성공하던 실패하던 적어야 에러탐지가 편함
        alert("삭제 완료");
        this.retrieveFileDb();       // 삭제하고 나서 전체조회 페이지를 재조회하기
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 공통함수(페이징)
    // TODO: select 박스 변경시 실행될 함수
    // TODO: select 태그 연결
    pageSizeChange() {
      this.page = 1; // 2) 현재 페이지번호 초기화(1)
      this.retrieveFileDb(); // 3) 재조회 요청
    },
  },
  // TODO: 화면이 뜰 때 자동으로 실행되는 함수 이벤트 함수. 라이프사이클 함수. 
  // created(화면에 랜더링안ㅇ됨) / mounted(랜더링:화면에 html태그 보이고 나서 보임.)
  // 화면에 뜰때 전체조회를 하고싶을떄 사용. 개발자의 선택.
  mounted() {
    // TODO: 화면이 뜰 때 전체 조회하기
    this.retrieveFileDb();
  },
};
</script>

<style></style>