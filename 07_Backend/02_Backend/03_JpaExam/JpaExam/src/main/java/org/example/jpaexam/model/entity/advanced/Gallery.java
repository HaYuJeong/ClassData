package org.example.jpaexam.model.entity.advanced;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import org.example.jpaexam.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.jpaexam.model.entity.advanced
 * fileName : Galley
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
@Entity
@Table(name = "TB_GALLERY")
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Where(clause="DELETE_YN = 'N'")
@SQLDelete(sql="UPDATE TB_GALLERY SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE UUID = ?")
public class Gallery extends BaseTimeEntity2 {
    //    TODO: 연습 : Gallery 엔티티,
//         GalleryRepository : findAllByGalleryTitleContaining
//         GalleryService    : findAllByGalleryTitleContaining
//         GalleryController : findAllByGalleryTitleContaining
//         jsp               : gallery_all.jsp
//         샘플 참고 : FileDb
    @Id
    private String uuid;            // 기본키
    private String galleryTitle;    // 제목
    private String galleryFileName; // 파일명

    @Lob
    private byte[] galleryData;     // 이미지(BLOB)
    private String galleryFileUrl;  // 파일 다운로드 url
}