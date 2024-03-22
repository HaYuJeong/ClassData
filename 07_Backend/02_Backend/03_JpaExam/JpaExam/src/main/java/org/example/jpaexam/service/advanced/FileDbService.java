package org.example.jpaexam.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.advanced.FileDb;
import org.example.jpaexam.repository.advanced.FileDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
