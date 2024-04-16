// axios 기본 설정
import axios from "axios";                  // axios 기본 설정을 하고 나서

export default axios.create({               // axios의 create 함수를 내보냄
    // spring ip 주소
    baseURL: "http://localhost:8000/api",   // spring-ip (백엔드) 주소로 공유(보냄) (백엔드와 통신하기위해 백엔드로 주소를 보내야 함)
    headers:{
        // 문서 종류
        "Content-Type": "application/json"  // json 파일 형태로 보낸다
    }
})