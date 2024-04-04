package org.example.simpledms.repository.advanced;

import org.example.simpledms.model.entity.advanced.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.advanced
 * fileName : GalleryRepository
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
@Repository
public interface GalleryRepository extends JpaRepository<Gallery, String> {
    @Query(value = "SELECT * FROM TB_GALLERY\n" +
            "WHERE GALLERY_TITLE LIKE '%' || :galleryTitle || '%'\n" +
            "AND DELETE_YN = 'N'"
            , countQuery = "SELECT * FROM TB_GALLERY\n" +
            "WHERE GALLERY_TITLE LIKE '%' || :galleryTitle || '%'\n" +
            "AND DELETE_YN = 'N'"
            , nativeQuery = true)
    Page<Gallery> findAllByGalleryTitleContaining(
            @Param("galleryTitle") String galleryTitle,
            Pageable pageable
    );
}
