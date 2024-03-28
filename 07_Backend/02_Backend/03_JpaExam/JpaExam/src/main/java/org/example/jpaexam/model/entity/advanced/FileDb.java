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
 * packageName : org.example.jpaexam.service.advanced
 * fileName : FileDB
 * author : hayj6
 * date : 2024-03-22(022)
 * description : 파일 업로드 엔티티 클래스
 * 요약 :
 *      TODO:
 *      1) 시퀀스 사용 안함 -> 자바의 uuid 사용(기본키)
 *          => uuid : 자바에서 문자열이 생성됨(세계에서 유일하게 생성됨. 그래서 중복이 안돼서 기본키로 사용가능. 시퀀스도 중복안돼서 기본키로 사용)
 *      2) soft delete vs hard delete
 *         => hard delete : delete 문을 이용해서 실제 데이터 삭제
 *                      ex) delete from tb_dept where dno = 10;
 *         => soft delete : update 문을 이용해서 화면에만 표시하지 않는 것, DB에는 데이터가 있음(삭제 안됨)
 *                      ex) update tb_dept set delete_yn = 'Y'
 *                          where dno = 10;
 *                          - 조회 : select * from tb_dept
 *                                  where dno = 10
 *                                  and delete_yn = 'N';        // 이걸 붙여야 함 -> 그래서 모든 select 문에 이게 붙게 됨
 *           - JPA soft delete 어노테이션
 *              1) @Where(clause="조회시_조건절에 추가될_sql문")
 *                  =>  모든 조회(select) sql 의 끝에 추가 조건 붙이기 어노테이션
 *                  ex) 모든 조회 붙이기 : @Where(clause = "delete_yn = 'N'")
 *              2) @SQLDelete(sql="대체 sql문")
 *                  => delete 방식으로 요청되면 대체해서 다른 sql문이 실행되게 해주는 어노테이션
 *                  => 대체 sql문 : update 문 추가
 *                  ex) delete 요청 : 대체 sql 문 : update 문 실행
 *                      @SQLDelete(sql="UPDATE TB_FILE_DB
 *                                      SET DELETE_YN = 'Y',
 *                                      DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')
 *                                      WHERE UUID = ?")
 *              3) @Lob : 속성(필드) 위에 붙이는 어노테이션
 *                  => 컬럼에 이미지를 저장하는 DB 자료형 : BLOB (DB자료형)
 *                  => BLOB 자료형에 대응해서 자바에서 제공하는 어노테이션
 *                  ex) @Lob
 *                      byte[] 속성명;     // 자료형을 보통 byte로 씀, BLOB 자료형 : 이미지
 *                  VS (참고) DB 자료형 : 대량의 문자열을 저장하는 자료형 : CLOB(조회 속도가 느려짐)
 *                    일반 문자열 자료형 : vachar2(최대 4000 byte 까지 저장 가능)
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22(022)         hayj6          최초 생성
 */
// JPA 어노테이션
@Entity
@Table(name = "TB_FILE_DB")
@DynamicInsert
@DynamicUpdate
// 롬북 어노테이션
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// soft delete jpa 어노테이션
@Where(clause="DELETE_YN = 'N'")
@SQLDelete(sql="UPDATE TB_FILE_DB " +
               "SET DELETE_YN = 'Y'" +
               ", DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') " +
               "WHERE UUID = ?")
public class FileDb extends BaseTimeEntity2 {

//    TB_FILE_DB : 컬럼과 일치하게 속성 작성
//    uuid	varchar2(1000 byte)
//    file_title	varchar2(1000 byte)
//    file_content	varchar2(1000 byte)
//    file_name	varchar2(1000 byte)
//    file_data	blob
//    file_url	varchar2(1000 byte)
    @Id                         // uuid 속성에만 특수한 기능이 붙는다는 뜻. 나머지는 영향이 없음
    private String uuid;        // 기본키 : 자바 생성

    private String fileTitle;   // 제목
    private String fileContent; // 내용
    private String fileName;    // 업로드 파일명

    @Lob
    private byte[] fileData;    // 업로드 이미지
    private String fileUrl;     // 이미지 다운로드 url
}