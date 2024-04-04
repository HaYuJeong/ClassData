import http from "@/utils/http-common";

class FileDbServie{
    // 속성, 생성자, 함수(함수만 만들기)
    // TODO: 전체 조회(select) -> get 방식 -> @GetMapping
    getAll(fileTitle, page, size){
        return http.get(`/advanced/fileDb?fileTitle=${fileTitle}&page=${page}&size=${size}`);
    } 
    // TODO: 추가 함수(insert) : post 방식 -> @PostMapping
    // TODO: 1) 문서 형태 : multipart/form-data 형태로 보내줘야 함
    // TODO: 2) 파일 업로드 : 오로지 form 태그만 가능한데 뷰에서는 form 태그를 쓰지 않음
    // TODO:              == FormData 객체(자바스크립트 객체)를 써야 백엔드에 전송이 가능하다.
    create(fileDb, image){      // 제목, 내용, 설명이 들어간 fileDb 객체랑 이미지를 매개변수로 받는다.
        let fomrData = new fomrData();  // form 객체
        // form 객체에 추가 : append("키", "값");       // Map 형태, Model 형태와 같은 (키, 값) 형태
        FormData.append("fileTitle", fileDb.fileTitle);
        FormData.append("fileTitle", fileDb.fileContent);
        FormData.append("fileTitle", image);

        return http.post("advanced/fileDb/upload"
                        , FormData
                        , { headers: {
                                "Content-Type": "multipart/form-data",      // 이 형식으로 보낸다는 의미
                              },
                        });
    }
}

export default new FileDbServie();  // 객체 생성해서 export