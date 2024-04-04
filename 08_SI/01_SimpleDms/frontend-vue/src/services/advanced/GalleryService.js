import http from "@/utils/http-common";

class GalleryService{
    // 속성, 생성자, 함수(함수만 만들기)
    // TODO: 전체 조회(select) -> get 방식 -> @GetMapping
    getAll(galleryTitle, page, size){
        return http.get(`/advanced/gallery?galleryTitle=${galleryTitle}&page=${page}&size=${size}`);
    } 
}

export default new GalleryService();  // 객체 생성해서 export