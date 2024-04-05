import http from "@/utils/http-common";

class GalleryService{
    // 속성, 생성자, 함수(함수만 만들기)
    // TODO: 전체 조회(select) -> get 방식 -> @GetMapping
    getAll(galleryTitle, page, size){
        return http.get(`/advanced/gallery?galleryTitle=${galleryTitle}&page=${page}&size=${size}`);
    }     
    create(gallery, image){      // 제목, 내용, 설명이 들어간 fileDb 객체랑 이미지를 매개변수로 받는다.
        let formData = new FormData();  // form 객체
        // form 객체에 추가 : append("키", "값");       // Map 형태, Model 형태와 같은 (키, 값) 형태
        formData.append("galleryTitle", gallery.galleryTitle);
        formData.append("image", image);

        return http.post("advanced/gallery/upload"
                        , formData
                        , { headers: {
                                "Content-Type": "multipart/form-data",      // 이 형식으로 보낸다는 의미
                              },
                        });
    }
    get(uuid){
        return http.get(`/advanced/gallery/get/${uuid}`);
    }
    update(gallery, image){
        let formData = new FormData();
        formData.append("galleryTitle", gallery.galleryTitle);
        formData.append("image", image);

        return http.put(`/advanced/gallery/${gallery.uuid}`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });
        }
        delete(uuid){
            return http.delete(`/advanced/gallery/deletion/${uuid}`);
        }
      }
export default new GalleryService();  // 객체 생성해서 export