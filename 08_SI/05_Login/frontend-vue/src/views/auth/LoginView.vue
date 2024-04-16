// LoginView.vue // vueInit
<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-xl-10 col-lg-12 col-md-9">
        <div class="card mt-5">
          <div class="card-body p-0">
            <!-- {/* Nested Row within Card Body */} -->
            <div class="row">
              <div class="col-lg-6 bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 mb-4">Welcome Back!</h1>
                  </div>
                  <!-- 사용법 : @submit.prevent-"함수" -->
                  <!-- prevent : submit 의 기본 속성 막기(다른 곳으로 이동하려는 특징) -->
                  <form class="user" @submit.prevent="handleLogin">
                    <div class="form-group">
                      <input
                        type="email"
                        class="form-control form-control-user mb-3"
                        placeholder="이메일을 입력하세요"
                        name="email"
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-group">
                      <input
                        type="password"
                        class="form-control form-control-user mb-3"
                        id="exampleInputPassword"
                        placeholder="비밀번호를 입력하세요"
                        name="password"
                        v-model="user.password"
                      />
                    </div>

                    <button class="btn btn-primary btn-user w-100 mb-3">
                      Login
                    </button>
                    <hr />
                    <a href="/" class="btn btn-google btn-user w-100 mb-2">
                      <i class="fab fa-google fa-fw"></i>&nbsp;Login with Google
                    </a>
                    <a href="/" class="btn btn-naver btn-user w-100 mb-2">
                      <i class="fa-solid fa-n"></i>&nbsp;Login with Naver
                    </a>
                    <a href="/" class="btn btn-kakao btn-user w-100 mb-3">
                      <i class="fa-solid fa-k"></i>&nbsp;Login with Kakao
                    </a>
                  </form>
                  <hr />
                  <div class="text-center">
                    <a class="small" href="/forgot-password">
                      Forgot Password?
                    </a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="/register"> Create an Account! </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AuthService from '../../services/auth/AuthService';

export default {
  // TODO: 데이터 바인딩 속성 정의
  data() {
    return {
      user: {
        email: "", // 로그인 ID
        password: "", // 암호
      },
    };
  },
  methods: {
    async handleLogin() {
        // TODO: 공통 로그인 서비스 함수
        // TODO: 비동기 코딩 : async ~ await 
        // TODO: 로그인 성공 : 로그인 성공 공유함수 호출하기 
        // TODO: 로그인 실패 : 로그인 실패 공유함수 호출하기
        try{
            let response = await AuthService.login(this.user);
            // 로깅
            console.log(response.data);     
            // TODO: 백엔드 spring에서 보내준 user객체에 웹 토큰도 있음. 그걸 로컬스토리지에 저장
            // TODO: 사용법 : localStorage.setItem(키, 값); -> 단, 값은 문자열만 저장됨 
            // 객체 -> 문자열 : JSON.stringify(객체) : 문자열로 바뀐 객체가 리턴됨
            localStorage.setItem("user", JSON.stringify(response.data))   // response.data는 객체, 로컬 스토리지에는 문자열로 저장해야함
            // TODO: 로그인 성공 : 로그인 성공 공유함수(loginSuccess) 호출하기
            this.$store.commit("loginSuccess");
            // TODO: 로그인 후 home 으로 강제 이동시키기
            this.$router.push("/");
        } catch(e) {
            // TODO: 로그인 실패 : 로그인 실패 공유함수(loginFailure) 호출하기(공유속성 아닌 공유함수 호출)
            // 공유저장소의 mutations 함수는 commit 을 이용하여 불러낸다.
            // TODO: 사용법 : this.$store.commit("mutations 함수명");
            this.$store.commit("loginFailure");
            console.log(e);     // 실패 시 웹브라우저 콘솔탭에 에러
        }
    },
  },
  // mounted() : 화면이 뜰 때 자동 실행(생명주기 함수), html 태그까지 모두 뜰 때 자동 실행
  // created() : 뷰가 생성될 때 자동 실행, html 태그 아닌 뷰만 생성되면 자동 실행
  // created() 실행되고 mounted() 가 실행됨. mounted가 더 느림
  // destroyed() : 뷰가 삭제될 때 실행(거의 사용안함)
  created() {
    // 인증 : jsp(쿠키/세션) => vue/spring(로컬스토리지/웹토큰)
    // 프로젝트 규모가 커지면 : 로그인 정보 + 상태값 관리를
    // vue의 공유저장소(vuex(뷰엑스))에 넣음
    // component끼리 직접 접근하지 못함. binding 속성끼리 접근 불가 
    // -> 공유저장소에 변수를 넣어놓고 component들이 필요할때마다 사용할수있도록 함(props는 어려움)
    // 로그인 할 때 공유저장소를 주로 사용 
    // 상태값 관리 : 로그인 되어있으면 메뉴 보이게, 안되어있으면 메뉴 안보이게 함
    // TODO: 공유저장소의 공유속성 중 로그인이 true -> 로그인이 되어있는 상태
    // TODO: => 강제로 Home 으로 이동시킴
    // TODO: 사용법 : this.$store.state.공유속성명 : 공유저장소의 공유속성 접근법
    if(this.$store.state.loggedIn == true){     // 로그인 성공했을 때니까
        this.$router.push("/");                 // home으로 강제 이동 
    }
  },
};
</script>

<style>
@import "@/assets/css/login.css";
</style>
