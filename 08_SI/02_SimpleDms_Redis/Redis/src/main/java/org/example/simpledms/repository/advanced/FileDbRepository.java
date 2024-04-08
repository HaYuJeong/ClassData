package org.example.simpledms.repository.advanced;

import org.example.simpledms.model.entity.advanced.FileDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.advanced
 * fileName : FileDbRepository
 * author : GGG
 * date : 2024-04-04
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04         GGG          최초 생성
 */
@Repository
public interface FileDbRepository extends JpaRepository<FileDb, String> {
    //    제목(fileTitle) like 검색 : @Query()
//    TODO: soft delete : 삭제하지 않고 화면에만 안보이게 하는것
//         DELETE_YN = 'N' (조회시) => @Where() : JPA 사용(JPQL 만 적용)
//                                 => nativeQuery = true(오라클 sql 은 직접 작성)
    @Query(value = "SELECT * FROM TB_FILE_DB\n" +
            "WHERE FILE_TITLE LIKE '%' || :fileTitle || '%'" +
            "AND DELETE_YN = 'N'"
            , countQuery = "SELECT * FROM TB_FILE_DB\n" +
            "WHERE FILE_TITLE LIKE '%' || :fileTitle || '%'" +
            "AND DELETE_YN = 'N'"
            , nativeQuery = true)
    Page<FileDb> findAllByFileTitleContaining(
            @Param("fileTitle") String fileTitle,
            Pageable pageable
    );

    /**
     * packageName : org.example.simpledms.repository
     * fileName : GalleryRepository
     * author : GGG
     * date : 2024-04-04
     * description :
     * 요약 :
     * <p>
     * ===========================================================
     * DATE            AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2024-04-04         GGG          최초 생성
     */
    interface GalleryRepository {
    }
}

