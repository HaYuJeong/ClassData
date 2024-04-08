package org.example.simpledms.service.basic;

import com.example.simpledms.model.common.RestPage;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.repository.basic.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.basic
 * fileName : DeptService
 * author : GGG
 * date : 2024-04-02
 * description : Redis 스프링 사용
 *      @EnableCaching : 서비스 클래스 위에 붙이고, 레디스 DB 의 캐싱기능 활성화하는 어노테이션
 *      페이징 전체 결과 리턴시 Pageable 자바 객체 -> json -> 레디스 Db 형태 변환시
 *      오류가 발생 (버그)
 *      => 해결책 : 변형된 페이징 클래스 추가로 정의해서 사용하기
 *              ( 부서객체배열 , 현재페이지번호, 1페이지당개수, 전체데이터 건수 )
 *
 *      @Cacheable("키")
 *      => 레디스 DB 에서 조회시 사용하는 어노테이션 , 주로 조회함수 위에 붙임
 *
 *      @CacheEvict(value = "키", key = "#기본키매개변수")
 *      => 레디스 DB 에서 기본키로 삭제하는 어노테이션

 *

 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02         GGG          최초 생성
 */
@Slf4j
@Service
@EnableCaching
public class DeptService {

    //    DB CRUD 클래스 받기 : JPA 제공 함수 사용 가능
    @Autowired
    DeptRepository deptRepository; // DI

    /**
     * 전체 조회 : 페이징 없음
     * @return 부서 배열
     */

    public List<Dept> findAll() {
//        JPA 전체조회 함수 실행 : select 문 자동 작성
        List<Dept> list = deptRepository.findAll();
        return list;
    }

    /**
     * like 검색 함수 : 페이징 처리
     * @param dname
     * @param pageable
     * @return
     */
    @Cacheable("depts")
    public Page<Dept> findAllByDnameContaining(String dname,
                                               Pageable pageable) {
//      DB like 검색 함수 실행 : 페이징 처리
        Page<Dept> page
                = deptRepository
                .findAllByDnameContaining(dname, pageable);
//      return page;
        return new RestPage<>(page);    // 버그 해결위한 생성자 함수 실행
    }

    /**
     * 상세조회 : return : 객체 1개 (null 가능성 있음)
     *  복습 : null 방지 래퍼클래스 : Optional<객체>
     * @param dno(부서번호)
     * @return 옵셔널 부서 객체
     */
    @Cacheable("dept")  // 상세조회 함수에 대해 캐싱기능 적용, 조회 결과가 Redis DB 에 저장됨
    public Optional<Dept> findById(int dno) {
//        JPA 상세조회 함수 실행
        Optional<Dept> optionalDept
                = deptRepository.findById(dno);
        return optionalDept;
    }

    public Dept insert(Dept dept) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Dept dept2 = deptRepository.save(dept);

        return dept2;
    }

    //    저장/수정 : 1) 기본키가(부서번호) 없으면 저장(insert)
//               2) 기본키가(부서번호) 있으면 수정(update)
    @CacheEvict(value="dept", key="#dept.dno")
//           => JPA 내부적으로 if문 있음 : 알아서 실행됨
    public Dept update(Dept dept) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Dept dept2 = deptRepository.save(dept);

        return dept2;
    }

    //    삭제 함수
    @CacheEvict(value="dept", key="#dno")
    public boolean removeById(int dno) {
//        JPA 삭제함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true 리턴
//           없으면 false 리턴

//        사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있으지 확인. 있으면 true, 없으면 false
        if(deptRepository.existsById(dno) == true) {
            deptRepository.deleteById(dno);
            return true;
        } else {
            return false;
        }
    }
}

