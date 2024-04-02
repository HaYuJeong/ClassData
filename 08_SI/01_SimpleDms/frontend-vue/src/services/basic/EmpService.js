import http from "@/utils/http-common"; // spring 통신 정의 파일

class EmpService{
    getAll(ename, page, size){
        return http.get(`basic/emp?ename=${ename}&page=${page}&size=${size}`)
    }
}

export default new EmpService();