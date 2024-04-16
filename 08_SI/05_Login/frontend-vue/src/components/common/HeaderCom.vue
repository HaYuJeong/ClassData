<template>
  <div>
    <!-- 부트스트랩 메뉴 넣기 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Simple</a>
        <!-- 단축 메뉴(햄버거 메뉴) -->
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
          <!-- 왼쪽 메뉴 -->
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
              <!-- 기본예제 메뉴 #1 -->
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                기초 예제
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/dept">Dept</a></li>
                <li><a class="dropdown-item" href="/add-dept">Add Dept</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="/emp">Emp(연습)</a>
                </li>
                <li>
                  <a class="dropdown-item" href="add-emp">Add Emp(연습)</a>
                </li>
              </ul>
            </li>
            <!-- 고급예제 메뉴 #2 -->
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                고급 예제
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/fileDb">FileDb</a></li>
                <li>
                  <a class="dropdown-item" href="/add-fileDb">Add FileDb</a>
                </li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="/gallery">Gallery(연습)</a>
                </li>
                <li>
                  <a class="dropdown-item" href="add-gallery"
                    >Add Gallery(연습)</a
                  >
                </li>
              </ul>
            </li>
          </ul>
          <!-- 오른쪽 메뉴 -->
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
              <a href class="nav-link active" @click="handleLogout"> 로그아웃 </a>
            </li>
            <!-- {/* 로그아웃 끝 */} -->
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import AuthService from '@/services/auth/AuthService';
export default {
  // TODO: 함수 정의
  methods: {
    // TODO: 로그아웃 함수
    handleLogout(){
              // TODO: 공통 서비스 로그아웃 함수 실행
      AuthService.logout();
      console.log("로그아웃");
      // TODO: 공유저장소 로그아웃 함수 실행
      this.$store.commit("logout");
      // TODO: 로그아웃하면 로그인 페이지로 강제 이동
      this.$router.push("/login");
      
    }
  },
};
</script>

<style></style>