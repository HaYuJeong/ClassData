package org.example.loginquiz.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginquiz.model.entity.advanced.FileDb;
import org.example.loginquiz.repository.advanced.FileDbRepository;
import org.example.loginquiz.model.entity.advanced.FileDb;
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
 * fileName : FileDbService
 * author : hayj6
 * date : 2024-03-22(022)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22(022)         hayj6          최초 생성
 */
@Slf4j
@Service
public class FileDbService {
    @Autowired
    FileDbRepository fileDbRepository;  // JPA DB 객체

    //    like 검색 + 전체 조회 + 페이징 처리
    public Page<FileDb> findAllByFileTitleContaining(
            String fileTitle,
            Pageable pageable
    ) {
//        JPA like 검색 함수 실행
        Page<FileDb> page = fileDbRepository.findAllByFileTitleContaining(fileTitle, pageable);
        return page;
    }

    //    TODO: 저장/수정 : save
//     1) insert : 기본키(uuid)가 없으면
//      1-1) uuid 생성 : 자바생성함수 이용
//      1-2) 파일 다운로드 url 생성
//          (1) 현재 기본주소 : http://localhost:8000
//          (2) 추가 주소 붙임 : /advanced/fileDb
//          (3) 파일명(uuid) 붙임 : 다운로드 파일명(유일해야함) : uuid (xxxx...)
//              => http://localhost:8000//advanced/fileDb/xxxx
//      1-3) FileDb 객체에 넣어서 저장(save)
    public FileDb save(String uuid
            , String fileTitle
            , String fileContent
            , MultipartFile file) {         // TODO: file 업로드 클래스(Multipart 형태로 업로드 됨)
        FileDb fileDb2 = null;                     // TODO: 파일 업로드는 항상 예외처리

        try {
            if (uuid == null) {
//              TODO: insert : 기본키(uuid)가 없으면
//                1-1) uuid 생성 : 자바생성함수 이용 (uuid : 유일하게 랜덤한 고유값 생성)
//                     uuid 생성 예시 : xxxx-xxxx-xxxx-xxxx => xxxxxxxxxxxxxxxx로 변경
                String tmpUuid = UUID.randomUUID().toString().replace("-", "");
//               TODO: 1-2) 파일 다운로드 url 생성 : 자바함수 (ServletUriComponentBuilder)
                String fileDownload = ServletUriComponentsBuilder
                        .fromCurrentContextPath()      // spring 서버기본주소 : http://localhost:8000
                        .path("/advanced/fileDb/")     // 추가 경로 넣기      : /advanced/fileDb/
                        .path(tmpUuid)                 // uuid 넣기          : xxxxxx
                        .toUriString();                // 합치기              : http://localhost:8000/advanced/fileDb/xxxxxx

//                TODO: 1-3) FileDb 객체에 넣어서(생성자, setter) 저장(save)
                FileDb fileDb = new FileDb(tmpUuid
                        , fileTitle
                        , fileContent
                        , file.getOriginalFilename()     // 파일 업로드 당시 파일명
                        , file.getBytes()                // 파일 사이즈
                        , fileDownload                   // 새로만든 다운로드 url
                );
                fileDb2 = fileDbRepository.save(fileDb);    // DB 저장
            } else {
                //  TODO: update : 기본키(uuid)가 있으면
//                      1-1) uuid 생성(생략) : 기본키가 있기 때문에 생성하지 않아도 됨
//                      1-2) 파일 다운로드 url 생성 :
                String fileDownload = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/advanced/fileDb/")
                        .path(uuid)
                        .toUriString();     // 합치기 : http://localhost:8000/advanced/fileDb/xxxxxx
//                TODO: 1-3) FileDb 객체 생성(생성자, setter)
                FileDb fileDb = new FileDb(
                        uuid,                           // 기존 uuid
                        fileTitle,                      // 제목
                        fileContent,                    // 내용
                        file.getOriginalFilename(),     // 업로드 할때의 파일명
                        file.getBytes(),                // 실제 업로드 이미지
                        fileDownload                    // 파일 다운로드 url
                );
                fileDb2 = fileDbRepository.save(fileDb);    // DB 저장
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return fileDb2;
    }       // TODO: file 업로드 클래스

    //    상세조회
    public Optional<FileDb> findById(String uuid) {
        return fileDbRepository.findById(uuid);
    }

    //    삭제 : 기본키(uuid)
    public boolean removeById(String uuid) {
        if(fileDbRepository.existsById(uuid) == true) {
            fileDbRepository.deleteById(uuid);
            return true;
        } else {
            return false;
        }
    }

}