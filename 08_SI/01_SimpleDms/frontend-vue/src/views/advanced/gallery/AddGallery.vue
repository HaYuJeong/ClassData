// AddFileDb.vue // vueInit
<template>
  <div>
    <div class="col-6 mx-auto">
      <!-- {/* 이미지명(fileTitle) 입력 박스 시작 */} -->
      <div class="row g-3 align-items-center mb-3">
        <div class="col-3">
          <label htmlFor="galleryTitle" class="form-label"> 이미지명 </label>
        </div>

        <div class="col-9">
          <input
            type="text"
            class="form-control"
            id="galleryTitle"
            name="galleryTitle"
            v-model="gallery.galleryTitle"
          />
        </div>
      </div>
      <!-- {/* 이미지명 입력 박스 끝 */} -->

      <div class="input-group mb-3">
        <input type="file" ref="gallery" @change="selectImage" />

        <button
          class="btn btn-outline-secondary"
          type="button"
          id="inputGroupFileAddon04"
          @click="create"
        >
          Upload
        </button>
      </div>
      <!-- {/* upload 선택상자/버튼 end */} -->

      <!-- {/* upload 성공/실패 메세지 출력 시작 */} -->
      <div v-if="message" class="alert alert-success" role="alert">
        {{ message }}
      </div>
      <!-- {/* upload 성공/실패 메세지 출력 끝 */} -->
    </div>
  </div>
</template>
<script>
import GalleryService from "@/services/advanced/GalleryService";

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      currentImage: undefined,
      message: "",
      gallery: {
        uuid: null,
        galleryTitle: "",
        galleyUrl: "",
      },
    };
  },
  methods: {
    // TODO: 파일 선택상자에서 이미지 선택하면 변수에 저장하는 함수
    selectImage() {
      // 1) 파일 선택 상자에서 첫번째로 선택한 이미지를 변수에 저장
      // ref="file" 접근 -> 내부 속성 : files[번호]
      // TODO: 사용법 : this.$refs.변수명 => input type="file" ref"변수명" 태그에 접근
      this.currentImage = this.$refs.gallery.files[0];
      // 성공메세지 변수 초기화
      this.message = "";
    },
    // TODO: insert(추가:파일업로드) 함수
    async create() {
      try {
        // TODO: insert 요청 : 공통 추가 함수 : create()
        // TODO: 비동기 코딩 : async ~ await
        let response = await GalleryService.create(
          this.gallery,
          this.currentImage
        );
        // 로깅
        console.log(response);
        // 성공 메세지 출력
        // this.message = response.data;
        alert("업로드 완료");
        this.$router.push("/gallery");
      } catch (e) {
        this.currentImage = undefined; // 원인 모를 에러가 나면 현재 선택된 이미지 변수를 초기화 시키기,
        this.message = ""; // 에러메세지도 띄우기
        console.log(e);
      }
    },
  },
};
</script>
<style></style>
