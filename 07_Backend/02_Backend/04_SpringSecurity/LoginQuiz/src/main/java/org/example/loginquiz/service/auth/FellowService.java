package org.example.loginquiz.service.auth;

import org.example.loginquiz.model.entity.auth.Fellow;
import org.example.loginquiz.repository.auth.FellowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.loginquiz.service.auth
 * fileName : FellowService
 * author : hayj6
 * date : 2024-03-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27(027)         hayj6          최초 생성
 */
@Service
public class FellowService {

    private final FellowRepository fellowRepository;

    @Autowired
    public FellowService(FellowRepository fellowRepository) {
        this.fellowRepository = fellowRepository;
    }

    public Optional<Fellow> findById(String userId){
        Optional<Fellow> optionalFellow = fellowRepository.findById(userId);
        return optionalFellow;
    }

//    기본키로 조회 함수
    public boolean existById(String userId){
//        JPA 함수 실행 : DB에 기본키가 있으면 true, 없으면 false
        boolean result = fellowRepository.existsById(userId);
        return result;
    }

    public Fellow save(Fellow fellow){
        Fellow fellow2 = fellowRepository.save(fellow);
        return fellow2;
    }
}
