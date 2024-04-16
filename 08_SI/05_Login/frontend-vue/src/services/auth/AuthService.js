// AuthService.js
// 로그인/로그아웃/회원가입을 위한 공통 axios 함수
import http from '@/utils/http-common';

class AuthService{
// 로그인 공통 서비스 함수 정의
// jsp 는 웹 보안위해 post 방식으로 사용함(get 방식은 웹 브라우저 주소창에 다 보임), vue/react 도 post 방식
    login(user){
        // 임시 객체 data
        let data = {
            email : user.email,         // 로그인 id
            password : user.password    // 로그인 password : 암호
        }
        return http.post("/auth/login", data);  // 이 주소로 data를 리턴하기
    }
    // TODO: 로그아웃 공통 서비스 함수 정의
    logout(){
        // 로컬스토리지의 값을 삭제
        // 사용법 : localStorage.removeItem("키이름")
        localStorage.removeItem("user");
    }
}

export default new AuthService();