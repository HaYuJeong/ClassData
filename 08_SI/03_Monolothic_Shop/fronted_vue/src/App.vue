<template>
  <div>
    <!-- 머리말 -->
    <HeaderCom />

    <!-- 본문 -->
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderCom from "./components/common/HeaderCom.vue";

export default {
  components: {
    HeaderCom
  },
};
</script>

<style lang="scss">
@import "@/assets/css/app.css";
</style>