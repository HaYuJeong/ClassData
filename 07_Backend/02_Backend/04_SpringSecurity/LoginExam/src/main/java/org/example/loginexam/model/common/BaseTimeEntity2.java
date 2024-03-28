package org.example.loginexam.model.common;

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
 * fileName : BaseTimeEntity2
 * author : GGG
 * date : 2024-03-19
 * description : TODO: 공통 클래스 : soft delete(소프트 삭제용)
 * 요약 :
 *      @MappedSuperclass
 *      @EntityListeners(AuditingEntityListener.class)
 *       : JPA 가 sql 문을 생성할때 아래 공통컬럼(속성)을 추가해서 생성시켜주는 어노테이션
 *         - 공통컬럼(속성) : insertTime, updateTime
 *          예) insert into tb_dept(dno, dname, loc) values (1,'sales','부산');
 *            => 붙이면 :
 *              insert into tb_dept(dno, dname, loc, insert_time, update_time)
 *               values (1,'sales','부산', '2024/03/19...', '2024/03/19...');
 *        : 추가 처리 - JPA 에서 사용할때 추상클래스로 사용함
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity2 {

    private String insertTime;

    private String updateTime;
//    TODO: 삭제여부 필드 : Y, N (soft delete 용 == update 문 실행)
//     select * from tb_dept where delete_yn = 'N';
    private String deleteYn;
//    TODO: 삭제시 시간 필드 (soft delete 용)
    private String deleteTime;

    @PrePersist
    void OnPrePersist() {
//        insert 하기전에 현재날짜를 넣기 : 날짜포맷(yyyy-MM-dd HH:mm:ss)
        this.insertTime = LocalDateTime.now()
                            .format(DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.deleteYn = "N";
    }

    @PreUpdate
    void OnPreUpdate() {
//        update 하기전에 현재날짜를 넣기
        this.updateTime = LocalDateTime.now()
                            .format(DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.insertTime = this.updateTime;
        this.deleteYn = "N";
    }
}
