<template>
  <div>
    <div class="col-6 mx-auto">
      <!-- {/* 이미지명(fileTitle) 입력 박스 시작 */} -->
      <div class="mb-3 col-md-12">
        <label htmlFor="galleryTitle" class="form-label"> 이미지명 </label>
        <input
          type="text"
          class="form-control"
          id="galleryTitle"
          required
          name="galleryTitle"
          v-model="gallery.galleryTitle"
        />
      </div>
      <!-- {/* 이미지명 입력 박스 끝 */} -->

      <!-- fileUrl : 스프링의 url 주소. DB에 이미지가 있을 것 -->
      <div class="mb-3 col-md-12">
        <img :src="gallery.galleryFileUrl" class="card-img-top" alt="강의" />
      </div>

      <!-- {/* upload 선택상자/버튼 start */} -->
      <div class="input-group mb-3">
        <!-- {/* upload 선택상자/버튼 start */} -->
        <label class="btn btn-default p-0 mb-3">
          <!-- TODO: 파일선택상자 : v-model 사용안됨 => ref="변수명" -->
          <input type="file" ref="file" @change="selectImage" />
        </label>

        <button class="btn btn-success mb-3" @click="update">Update</button>
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
  data() {
    return {
      currentImage: undefined, //
      message: "", // 성공메세지 변수
      gallery: {
        // 웹매개변수(url) 전달방식 :
        // TODO: 사용법 : this.$route.params.uuid 의 uuid 와
        // TODO: 참조) route/index.js -> path : fileDb/:uuid 의 uuid 가 같아야 가져올 수 있음
        // TODO: 이 두개의 변수 uuid 가 일치해야 함
        uuid: this.$route.params.uuid,
        galleryTitle: "",
        galleryFileUrl: "",
      },
    };
  },
  methods: {
    // TODO: 상세조회 함수(사원은 dno, 파일은 기본키 uuid로 상세조회)
    async get(uuid) {
      // TODO: 공통 상세조회함수 : get()
      // TODO: 비동기 코딩 : async ~ await, 백엔드와 통신할 때 에러가 많이 발생하므로 try catch 문 사용
      try {
        let response = await GalleryService.get(uuid); // 백엔드에서 결과가 오면 response 에 담겠다
        this.gallery = response.data; // 스프링 결과를 fileDb 객체에 저장함
        console.log(response.data); // response.data 가 어떻게 생겼는지 확인하기
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 파일선택상자에서 이미지를 선택할때 실행될함수
    selectImage() {
      this.currentImage = this.$refs.file.files[0];
      this.message = "";
    },

    // TODO: 수정함수(update)
    async update() {
      try {
        let response = await GalleryService.update(this.gallery, this.currentImage);
        this.message = response.data;
      } catch (error) {
        console.log(error);
      }
    },
  },
  // TODO: 화면이 뜰 때 자동실행 함수
  mounted() {
    this.get(this.$route.params.uuid); // 상세조회 실행. 웹 매개변수에 기본키가 전달됨(uuid가 전달)
    this.message = ""; // 성공메세지 초기화
  },
};
</script>

<style></style>