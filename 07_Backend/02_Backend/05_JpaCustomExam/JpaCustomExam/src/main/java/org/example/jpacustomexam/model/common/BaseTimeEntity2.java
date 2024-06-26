package org.example.jpacustomexam.model.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName : org.example.jpaexam.model.common
 * fileName : BaseTimeEntity
 * author : hayj6
 * date : 2024-03-19(019)
 * description : 공통 클래스 : soft delete(소프트 삭제용)
 * 요약 :
 *      @MappedSuperclass
 *      @EntityListeners(AuditingEntityListener.class)
 *      : JPA 가 sql 문을 생성할 때 아래 공통 컬럼(속성)을 추가해서 생성시켜주는 어노테이션
 *      - 공통 컬럼(속성) : insertTime, updateTime
 *       ex) insert into tb_dept(dno, dname, loc)
 *           values(1, 'sales', '부산');
 *           -> 여기에 공통 컬럼 붙이면
 *           insert into tb_dept(dno, dname, loc, insert_time, update_time)
 *           values(1, 'sales', '부산', '2024/03/19...', '2024/03/19...')
 *           : 추가 처리 - JPA 에서 사용할 떄 추상클래스로 사용함
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity2 {

    private String insertTime;

    private String updateTime;

//    TODO: 삭제 여부 필드 : Y(삭제됨) 아니면 N(삭제안됨) 이 나옴 : soft delete 용 필드 == update 문 실행
//          select * from tb_dept where delete_yn = 'N';  => Y하면 화면에 안보이니 삭제가 된것처럼 보임
    private String deleteYn;

//    TODO: 삭제 시 시간 필드(soft delete 용)
    private String deleteTime;

    @PrePersist
    void OnPrePersist(){
//        insert 하기전에 현재날짜를 넣기 : 날짜포맷(yyyy-MM-dd HH:mm:ss)
        this.insertTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        this.deleteYn = "N";    // 삭제할때만 Y로 바꾸기
    }

    @PreUpdate
    void OnPreUpdate(){
//        update 하기전에 현재날짜를 넣기
        this.updateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        insertTime과 같이 변경 (생성일시 == 수정일시 동일하게 처리)
        this.insertTime = this.updateTime;

        this.deleteYn = "N";
    }
}