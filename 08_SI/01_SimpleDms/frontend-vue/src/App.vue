<template>
<!-- 머리말 -->
<HeaderCom />

<!-- 본문 -->
<div class="container">
    <router-view/>
</div>

</template>

<script>
import HeaderCom from "./components/common/HeaderCom.vue";
// import HeaderComVue from "@/components/common/HedaerCom.vue" : 절대주소
export default{
    components : {
        HeaderCom   // 컴포넌트 등록
    }
}
</script>