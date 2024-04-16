// TODO: vue의 공유 저장소 : 공유속성, 공유함수 등을 정의함
// TODO: => 모든 컴포넌트가 사용가능 

import { createStore } from 'vuex'

// TODO: 로그인 정보 + 로그인 상태를 정의
// 1) 로컬 스토리지에서 user 객체 가져오기
const user = JSON.parse(localStorage.getItem('user'));

export default createStore({
  // TODO: state : 공유 속성을 정의하는 부분
  state: {                          // 공유 속성 : state
    loggedIn: user ? true : false,  // 로그인 여부
    user: user ? user : null,       // 로그인 정보(웹 토큰 속성 있음)
  },
  // TODO: 공유 속성의 값을 조회하는 함수 정의(생략가능)
  getters: {
  },
  // TODO: 공유 속성의 값을 저장(수정)하는 함수 정의(== setter, 생략가능)
  mutations: {
    // TODO: 로그인 성공 함수
    // => loggedIn(로그인여부 속성) = true 값으로, user(유저 객체) = user 객체 저장
    loginSuccess(state, user){
      state.loggedIn = true;    // 로그인 성공이라 true
      state.user = user;        // 성공 시 user 객체 저장
    },
    // TODO: 로그인 실패 함수
    loginFailure(state){
      state.loggedIn = false;  // 로그인 실패니까 false
      state.user = null;       // 실패하면 user 객체가 없으니까 null
    },
    // TODO: 로그아웃 함수
    logout(state){             // state : 공유 속성
      state.loggedIn = true;   // 로그인 성공했으니까 로그아웃도 된것, true
      state.user = null;       // 로그아웃하니까 user 객체 없음
    },
    // TODO: 회원가입 성공 함수 : 로그인이 된것은 아니다.
    registerSuccess(state){
      state.loggedIn = false;   // 로그인이 안된거니까 false
    },
    // TODO: 회원가입 실패 함수
    registerFailure(state){
      state.loggedIn = false;
    }
  },
  // TODO: 비동기함수들을 정의하는 곳(생략가능)
  actions: {},
  // TODO: 공유저장소를 여러개 사용할 경우 모듈로 정의해서 분리 가능(생략가능)
  modules: {}
})