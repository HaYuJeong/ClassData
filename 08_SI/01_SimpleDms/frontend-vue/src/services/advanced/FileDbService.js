import http from "@/utils/http-common";

class FileDbService {
  // 속성, 생성자, 함수(함수만 만들기)
  // TODO: 전체 조회(select) -> get 방식 -> @GetMapping
  getAll(fileTitle, page, size) {
    return http.get(
      `/advanced/fileDb?fileTitle=${fileTitle}&page=${page}&size=${size}`
    );
  }
  // TODO: 추가 함수(insert) : post 방식 -> @PostMapping
  // TODO: 1) 문서 형태 : multipart/form-data 형태로 보내줘야 함
  // TODO: 2) 파일 업로드 : 오로지 form 태그만 가능한데 뷰에서는 form 태그를 쓰지 않음
  // TODO:              == FormData 객체(자바스크립트 객체)를 써야 백엔드에 전송이 가능하다.
  create(fileDb, image) {
    // 제목, 내용, 설명이 들어간 fileDb 객체랑 이미지를 매개변수로 받는다.
    let formData = new FormData(); // form 객체
    // form 객체에 추가 : append("키", "값");       // Map 형태, Model 형태와 같은 (키, 값) 형태
    formData.append("fileTitle", fileDb.fileTitle);
    formData.append("fileContent", fileDb.fileContent);
    formData.append("image", image);

    return http.post("advanced/fileDb/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data", // 이 형식으로 보낸다는 의미
      },
    });
  }
  // TODO: 상세조회 함수
  get(uuid) {
    return http.get(`/advanced/fileDb/get/${uuid}`);
  }
  // TODO: 수정 함수
  // TODO: 수정(update) -> put 방식 -> @PutMapping
  // TODO: 1) 문서형태   : multipart/form-data
  // TODO: 2) 파일업로드 : 오로지 form 태그 == FormData 객체만 전송 가능
  update(fileDb, image) {
    // 1) FormData 객체 생성
    let formData = new FormData();

    // 2) form 객체변수에 값 추가 : append("키", 값);
    formData.append("fileTitle", fileDb.fileTitle);
    formData.append("fileContent", fileDb.fileContent);
    formData.append("image", image);

    // 3) 벡엔드 통신 : put 방식 -> put()
    return http.put(`/advanced/fileDb/${fileDb.uuid}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
//   TODO: 공통 삭제 함수
  delete(uuid){
    return http.delete(`/advanced/fileDb/deletion/${uuid}`);
  }
}

export default new FileDbService(); // 객체 생성해서 export