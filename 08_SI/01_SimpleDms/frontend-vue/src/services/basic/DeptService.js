// basic/DeptService.js
// 목적 : 부서 CRUD 공통 함수들 정의
import http from "@/utils/http-common"; // spring 통신 정의 파일

class DeptService {
    // 속성(x), 생성자(x), 공통함수
    // TODO: 전체 조회
    getAll(dname, page, size) {
        // TODO: 조회(select) : get 방식 -> @GetMapping
        // TODO: 사용법 : http.get("스프링_컨트롤러함수_url")
        // 예) axios_기본주소 + 추가 url 
            // => http://localhost:8000/api + /basic/dept?dname=SALES&page=0&size=3
            // => http://localhost:8000/api/basic/dept?dname=SALES&page=0&size=3
        return http.get(`/basic/dept?dname=${dname}&page=${page}&size=${size}`);
    }   
}

// 내보내기 : 다른 js 에서 사용하기 위해
// 사용법 : 자바 같음 : 객체.함수명()
export default new DeptService();