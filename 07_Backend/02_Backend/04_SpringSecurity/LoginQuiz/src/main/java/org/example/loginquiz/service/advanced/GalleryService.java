package org.example.loginquiz.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginquiz.model.entity.advanced.Gallery;
import org.example.loginquiz.repository.advanced.GalleryRepository;
import org.example.loginquiz.model.entity.advanced.Gallery;
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
 * author : hayj6
 * date : 2024-03-25(025)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-25(025)         hayj6          최초 생성
 */
@Slf4j
@Service
public class GalleryService {
    @Autowired
    GalleryRepository galleryRepository;

    //    전체 like 검색 함수
    public Page<Gallery> findAllByGalleryTitleContaining(String galleryTitle, Pageable pageable) {
        Page<Gallery> page = galleryRepository.findAllByGalleryTitleContaining(galleryTitle, pageable);
        return page;
    }

    //    TODO : 연습 : GalleryService     : save 함수 정의
//                 GalleryControlelr
//                  저장페이지 열기      : addGallery()
//                  저장버튼 클릭시 저장 : createGallery()
//                  파일 다운로드 함수   : findDownloadGallery()
//                  jsp               : add_gallery.jsp
    public Gallery save(String uuid, String galleryTitle, MultipartFile file) {
        Gallery gallery2 = null;

        try {
            if (uuid == null) {
                String tmpUuid = UUID.randomUUID().toString().replace("-", "");

                String fileDownload = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/advanced/gallery/")
                        .path(tmpUuid)
                        .toUriString();

                Gallery gallery = new Gallery(tmpUuid
                        , galleryTitle
                        , file.getOriginalFilename()
                        , file.getBytes()
                        , fileDownload);

                gallery2 = galleryRepository.save(gallery); // DB 저장

            } else {
                String fileDownload = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/advanced/gallery/")
                        .path(uuid)
                        .toUriString();

                Gallery gallery = new Gallery(
                        uuid,
                        galleryTitle,
                        file.getOriginalFilename(),
                        file.getBytes(),
                        fileDownload);

                gallery2 = galleryRepository.save(gallery);
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

    //    TODO: 연습 : Gallery 도 FileDB 참고해서 삭제 기능을 완성하세요
    //    삭제 : 기본키(uuid)
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