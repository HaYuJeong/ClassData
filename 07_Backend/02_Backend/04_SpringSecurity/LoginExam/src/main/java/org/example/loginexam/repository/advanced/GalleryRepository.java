package org.example.loginexam.repository.advanced;

import org.example.loginexam.model.entity.advanced.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.advanced
 * fileName : GalleryRepository
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
@Repository
public interface GalleryRepository extends JpaRepository<Gallery, String> {
//    like 제목(galleryTitle) 검색
    @Query(value = "SELECT g.* FROM TB_GALLERY g\n" +
            "WHERE g.GALLERY_TITLE LIKE '%' || :galleryTitle ||'%'\n" +
            "AND   g.DELETE_YN = 'N'"
            , countQuery = "SELECT count(*) FROM TB_GALLERY g\n" +
            "WHERE g.GALLERY_TITLE LIKE '%' || :galleryTitle ||'%'\n" +
            "AND   g.DELETE_YN = 'N'"
            , nativeQuery = true)
    Page<Gallery> findAllByGalleryTitleContaining(
            @Param("galleryTitle") String galleryTitle,
            Pageable pageable
    );
}
