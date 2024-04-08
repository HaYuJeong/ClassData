package org.example.simpledms.model.entity.advanced;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import org.example.simpledms.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.simpledms.model.entity.advanced
 * fileName : Gallery
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
@Entity
@Table(name = "TB_GALLERY")
@DynamicInsert
@DynamicUpdate
// 롬북 어노테이션
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// soft delete jpa 어노테이션
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql ="UPDATE TB_GALLERY SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE UUID = ?")
public class Gallery extends BaseTimeEntity2 {
    //    TODO: 연습 : Gallery 엔티티,
//      GalleryRepository : findAllByGalleryTitleContaining
//      GalleryService    : findAllByGalleryTitleContaining
//      GalleryController : findAllByGalleryTitleContaining
//      jsp               : gallery_all.jsp
//      샘플 참고 : FileDb
    @Id
    private String uuid;            // 기본키

    private String galleryTitle;    // 제목

    private String galleryFileName; // 파일명(업로드 할때)

    @Lob
    private byte[] galleryData;     // 이미지(blob)

    private String galleryFileUrl;  // 파일 다운로드 url

}

