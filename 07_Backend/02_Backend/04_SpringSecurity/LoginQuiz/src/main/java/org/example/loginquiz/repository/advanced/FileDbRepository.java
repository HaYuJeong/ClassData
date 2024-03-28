package org.example.loginquiz.repository.advanced;

import org.example.loginquiz.model.entity.advanced.FileDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.advanced
 * fileName : FileDbRepository
 * author : hayj6
 * date : 2024-03-22(022)
 * description : JPA CRUD 함수들이 정의된 곳, 직접 sql 도 작성가능
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22(022)         hayj6          최초 생성
 */
@Repository
public interface FileDbRepository extends JpaRepository<FileDb, String> {
//   제목(title) like 검색 : @Query()
//   TODO: soft delete : 삭제하지 않고 화면에만 안보이게 하는 것
//         DELETE_YN = 'N'(조회시) => @Where() : JPA에서 사용(JPQL만 사용. 우리는 오라클 쿼리 사용중
//                                => nativeQuery = true(오라클 sql)
    @Query(value="SELECT * FROM TB_FILE_DB\n" +
            "WHERE FILE_TITLE LIKE '%'|| :fileTitle ||'%'" +
            "AND DELETE_YN = 'N'"
            , countQuery="SELECT * FROM TB_FILE_DB\n" +
            "WHERE FILE_TITLE LIKE '%'|| :fileTitle ||'%'" +
            "AND DELETE_YN = 'N'"
            , nativeQuery=true)
    Page<FileDb> findAllByFileTitleContaining(
            @Param("fileTitle") String fileTitle,
            Pageable pageable
    );
}