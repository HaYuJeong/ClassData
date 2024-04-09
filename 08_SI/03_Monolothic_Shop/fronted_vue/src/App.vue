<template>
  <div>
    <!-- 머리말 -->
    <HeaderCom /> 

    <!-- 본문 -->
    <div class="container">
      <!-- router url + 페이지 등록 : 그 곳을 찾아 보여주는 태그 -->
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderCom from "./components/common/HeaderCom.vue";
export default {
  components: {
    HeaderCom
  }
}
</script>

<style lang="scss">
@import "@/assets/css/app.css";
</style>