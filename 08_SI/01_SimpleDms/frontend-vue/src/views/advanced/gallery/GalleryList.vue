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
            placeholder="제목 검색"
            v-model="searchTitle"
          />
          <div class="input-group-append">
            <button
              class="btn btn-outline-secondary"
              type="button"
              @click="retrieveGallery"
            >
              Search
            </button>
          </div>
        </div>
        <!-- {/* 검색어 end */} -->
      </div>

      <div class="col-md-12 mt-3">
        <h4>Gallery List</h4>
        <!-- {/* page start */} -->
        <div class="mb-3">
          1페이지당 개수:
          <select v-model="pageSize" @change="pageSizeChange">
            <option
              v-for="(data, index) in pageSizes"
              :key="index"
              :value="data"
            >
              {{ data }}
            </option>
          </select>
        </div>

        <!-- b-pagination : 부트스트랩 - 페이지 번호 컨트롤 -->
        <!-- total-rows : 전체 데이터 개수 -->
        <!-- per-page : 1페이지 당 개수 -->
        <!-- click : retrieveFileDb(), 페이지 번호 변경 시 실행되는 이벤트 -->
        <b-pagination
          class="col-12 mb-3"
          v-model="page"
          :total-rows="count"
          :per-page="pageSize"
          @click="retrieveGallery"
        ></b-pagination>
        <!-- {/* page end */} -->

        <!-- {/* 쇼핑카트 이미지 start */} -->
        <div class="row">
          <!-- TODO: v-for 반복문 실행 -->
          <div v-for="(data, index) in gallery" :key="index" class="col-sm-6">
            <div class="card">
              <!-- 카드 이미지(data.galleryFileUrl) -->
              <img :src="data.galleryFileUrl" class="card-img-top" alt="강의" />
              <!-- 본문 : 제목 + 내용 -->
              <div class="card-body">
                <!-- 제목 -->
                <h5 class="card-title">{{ data.galleryTitle }}</h5>
                <router-link :to="'/gallery/' + data.uuid">
                  <span class="badge bg-warning">수정</span>
                </router-link>
                <a
                  style="
                     {
                      color: inherit;
                    }
                  "
                  class="ms-2"
                  @click="deleteGallery(data.uuid)"
                >
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
import GalleryService from "@/services/advanced/GalleryService";

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      gallery: [], // spring 전송 파일 배열
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
    async retrieveGallery() {
      try {
        // TODO: 공통 전체 조회 함수 : getAll()
        // TODO: 비동기 코딩 : async ~ await
        let response = await GalleryService.getAll(
          this.searchTitle,
          this.page - 1,
          this.pageSize
        ); // 제목 검색어, 현재페이지번호, 1페이지당개수
        // TODO: 객체 분할 할당 , try catch 로 에러발생하면 console.log 로 출력
        const { gallery, totalItems } = response.data;
        this.gallery = gallery; // 파일 배열 : 위에서 반복문 돌림
        this.totalItems = totalItems; // 전체 데이터 개수
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 삭제 함수
    deleteGallery() {},
    // TODO: 공통함수(페이징)
    // TODO: select 박스 변경시 실행될 함수
    // TODO: select 태그 연결
    pageSizeChange() {
      this.page = 1; // 2) 현재 페이지번호 초기화(1)
      this.retrieveGallery(); // 3) 재조회 요청
    },
  },
  // TODO: 화면이 뜰 때 자동으로 실행되는 함수
  mounted() {
    // TODO: 화면이 뜰 때 전체 조회하기
    this.retrieveGallery();
  },
};
</script>

<style></style>
