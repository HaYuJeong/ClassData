import http from "@/utils/http-common"; // spring 통신 정의 파일

class EmpService {
  getAll(ename, page, size) {
    return http.get(`basic/emp?ename=${ename}&page=${page}&size=${size}`);
  }
  // TODO: 추가(insert) 함수 -> post 방식 -> @PostMapping
  create(data) {
    // TODO: 사용법 : http.post("컨트롤러함수url", 생성할객체)
    return http.post("/basic/emp", data);
  }
  get(eno){
    return http.get(`/basic/emp/${eno}`);
  }
  update(eno,data){
    return http.put(`/basic/emp/${eno}`, data);
  }
  delete(eno){
    return http.delete(`/basic/emp/deletion/${eno}`);
  }
}

export default new EmpService();
