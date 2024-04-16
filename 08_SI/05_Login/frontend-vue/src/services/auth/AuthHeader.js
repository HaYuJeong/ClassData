// AuthService.js
// 웹 토큰을 헤더에 넣어 spring 으로 보내기 위한 헤더 정의
export default function AuthHeader() {
  // 쿠키 대신 로컬 스토리지(html 5버전)
  // 로컬스토리지는 쿠키 확장판, 웹 토큰을 저장할 공간
  // 사용법 : localStrage.getItem('키이름') : 키이름은 웹 토큰을 가진 정보
  // => 키를 문자열로 가져오기
  // JSON.parse(문자열객체) => 실제객체로 바꾸어주는 함수
  // 사용법 : JSON.parse(문자열객체)
  let user = JSON.parse(localStorage.getItem("user"));

//   로컬스토리지에서 웹 토큰을 가지고 있는 유저가 있으면 
//   예) user => 로그인 ID, password(암호화), 이름, 웹토큰
//   accessToken : 웹토큰
  if (user && user.accessToken) {
    // Bearer (웹토큰 전송시 약속)
    // spring 에서 웹토큰 인증함
    return { Authorization: "Bearer " + user.accessToken };
  } else {
    return {};  // 아무것도 안보냄
  }
}