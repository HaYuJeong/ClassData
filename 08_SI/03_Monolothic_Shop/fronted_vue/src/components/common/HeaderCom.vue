// HeaderCom.vue
// vueInit
<template>
    <div>
  <div>
    <nav class="navbar navbar-expand-lg bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
          <img :src="require('../../assets/img/simple-coding2.png')" alt="" width="24" height="24" />
          &nbsp;&nbsp;Simple Coding
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <!-- {/* me-auto : 2nd ul 태그가 오른쪽으로 이동됨 */} -->
          <!-- {/* 1st ul 시작 */} -->
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <!-- {/* 홈 시작 */} -->
            <li class="nav-item ms-2">
              <a class="nav-link active" aria-current="page" href="/"> Home </a>
            </li>
            <!-- {/* 홈 끝 */} -->
            <!-- {/* 기초 시작 */} -->
            <li class="nav-item dropdown ms-2">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                MSA 예제
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="/simple-product"> Simple Shop </a>
                </li>
                <li>
                  <a class="dropdown-item" href="/add-simple-product"> Add Simple Shop </a>
                </li>
                <li>
                  <hr class="dropdown-divider" />
                </li>
                <li>
                  <a class="dropdown-item" href="/product"> Shop </a>
                </li>
                <li>
                  <a class="dropdown-item" href="/add-product"> Add Shop </a>
                </li>
              </ul>
            </li>
            <!-- {/* 기초 끝 */} -->
          </ul>
          <!-- {/* 1st ul 끝 */} -->

          <!-- {/* 2nd ul 시작 */} -->
          <ul class="navbar-nav">
            <!-- {/* 관리자 시작 */} -->
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                어드민 예제
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="/code-category">
                    Code Category List
                  </a>
                </li>
                <li>
                  <a class="dropdown-item" href="/add-code-category">
                    Add Code Category
                  </a>
                </li>
                <li>
                  <hr class="dropdown-divider" />
                </li>
                <li>
                  <a class="dropdown-item" href="/code"> Code </a>
                </li>
                <li>
                  <a class="dropdown-item" href="/add-code"> Add Code </a>
                </li>
              </ul>
            </li>
            <!-- {/* 관리자 끝 */} -->

            <!-- {/* 로그인 시작 */} -->
            <li class="nav-item">
              <a class="nav-link active" href="/register"> 회원가입 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="/login"> 로그인 </a>
            </li>
            <!-- {/* 로그인 끝 */} -->

            <!-- {/* 로그아웃 시작 */} -->
            <li class="nav-item">
              <a class="nav-link active" href="#"> 로그아웃 </a>
            </li>
            <!-- {/* 로그아웃 끝 */} -->
          </ul>
          <!-- {/* 2nd ul 끝 */} -->
        </div>
      </div>
    </nav>
  </div>
    </div>
</template>
<script>
export default {
    
}
</script>
<style>
    
</style>