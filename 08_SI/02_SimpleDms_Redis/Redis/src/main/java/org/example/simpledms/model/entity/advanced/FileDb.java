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
 * fileName : FileDb
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
@Table(name = "TB_FILE_DB")
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
@SQLDelete(sql ="UPDATE TB_FILE_DB SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE UUID = ?")
public class FileDb extends BaseTimeEntity2 {

    //    TB_FILE_DB : 컬럼과 일치하게 속성 작성
    @Id
    private String uuid;        // 기본키 : 자바생성

    private String fileTitle;   // 제목

    private String fileContent; // 내용

    private String fileName;    // 업로드 파일명

    @Lob
    private byte[] fileData;    // 업로드 이미지

    private String fileUrl;     // 파일 다운로드 url

}

