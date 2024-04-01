package org.example.jpacustomexam.service.exam02;

import org.example.jpacustomexam.model.entity.exam02.Emp2;
import org.example.jpacustomexam.repository.exam02.Emp2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.jpacustomexam.service.exam02
 * fileName : Emp2Service
 * author : GGG
 * date : 2024-04-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01         GGG          최초 생성
 */
@Service
public class Emp2Service {

    @Autowired
    Emp2Repository emp2Repository; // DI

    //    TODO: 단방향 조인 함수
    public Page<Emp2> selectJoinPage2(Pageable pageable){
        Page<Emp2> page
                = emp2Repository.selectJoinPage2(pageable);
        return page;
    }
}
