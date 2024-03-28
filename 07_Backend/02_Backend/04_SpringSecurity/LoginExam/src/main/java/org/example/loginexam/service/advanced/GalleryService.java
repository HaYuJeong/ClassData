package org.example.loginexam.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.model.entity.advanced.Gallery;
import org.example.loginexam.repository.advanced.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

/**
 * packageName : org.example.jpaexam.service.advanced
 * fileName : GalleryService
 * author : GGG
 * date : 2024-03-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-25         GGG          최초 생성
 */
@Slf4j
@Service
public class GalleryService {

    @Autowired
    GalleryRepository galleryRepository; // DI

    //    전체 like 검색 함수
    public Page<Gallery> findAllByGalleryTitleContaining(
            String galleryTitle,
            Pageable pageable
    ) {
        Page<Gallery> page
                = galleryRepository
                .findAllByGalleryTitleContaining(galleryTitle, pageable);
        return page;
    }

    //    TODO : 연습 : GalleryService     : save 함수 정의
//                 GalleryControlelr
//                  저장페이지 열기      : addGallery()
//                  저장버튼 클릭시 저장 : createGallery()
//                  파일 다운로드 함수   : findDownloadGallery()
//                  jsp               : add_gallery.jsp
    public Gallery save(String uuid
            , String galleryTitle    // 제목
            , MultipartFile file     // TODO: file 업로드 클래스
    ) {
        Gallery gallery2 = null;

        try {
            if (uuid == null) {
//            TODO: 1-1) uuid 생성 : 자바생성함수 이용
                String tmpUuid = UUID.randomUUID()
                        .toString()
                        .replace("-", "");

//          TODO: 1-2) 파일 다운로드 url 생성 : 자바함수 (ServletUriComponentsBuilder)
                String fileDownload = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/advanced/gallery/")
                        .path(tmpUuid)
                        .toUriString(); // 합치기 : http://localhost:8000/advanced/gallery/xxxxxx

//          TODO: 1-3) gallery 객체에 넣어서(생성자, setter) 저장(save)
                Gallery gallery = new Gallery(tmpUuid // uuid
                        , galleryTitle                // 제목
                        , file.getOriginalFilename()  // 파일업로드 당시 파일명 (예: course-3.jpg 등)
                        , file.getBytes()             // 파일 사이즈(이미지 파일)
                        , fileDownload                // 새로만든 파일다운로드 url
                );
                gallery2 = galleryRepository.save(gallery); // DB 저장
            } else {
//            TODO: update : 기본키(uuid) 있으면
                //            TODO: update : 기본키(uuid) 있으면
//                1-1) uuid 생성(생략) : why? 있음
//                1-2) 파일 다운로드 url 생성 :
                String fileDownload = ServletUriComponentsBuilder
                        .fromCurrentContextPath()  // spring 기본주소 : http://localhost:8000
                        .path("/advanced/gallery/") // 추가 경로 넣기 : /advanced/fileDb/
                        .path(uuid)                // uuid 넣기     : xxxxxx
                        .toUriString(); // 합치기 : http://localhost:8000/advanced/fileDb/xxxxxx
//             TODO: 1-3) Gallery 객체 생성(생성자, setter) + save()
                Gallery gallery = new Gallery(
                        uuid,                       // 기존 uuid
                        galleryTitle,               // 제목
                        file.getOriginalFilename(), // 업로드 할때 파일명
                        file.getBytes(),            // 업로드 이미지
                        fileDownload                // 파일 다운로드 url
                );
                gallery2 = galleryRepository.save(gallery);  // DB 수정

            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return gallery2;
    }

    //    상세조회
    public Optional<Gallery> findById(String uuid) {
        return galleryRepository.findById(uuid);
    }


    //    연습 : Gallery 도 FileDB 참고해서 삭제 기능을 완성하세요
//    삭제 함수
    public boolean removeById(String uuid) {
        if (galleryRepository.existsById(uuid) == true) {
            galleryRepository.deleteById(uuid);
            return true;
        } else {
            return false;
        }
    }

//    연습 : Gallery 도 FileDB 참고해서 수정 기능을 완성하세요

}
