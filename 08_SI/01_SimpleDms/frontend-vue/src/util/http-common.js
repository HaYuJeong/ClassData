// axios 기본 설정
import axios from "axios";

export default axios.create({
    // spring ip 주소
    baseURL: "http://localhost:8000/api",
    headers:{
        // 문서 종류
        "Content-Type": "application/json"
    }
})