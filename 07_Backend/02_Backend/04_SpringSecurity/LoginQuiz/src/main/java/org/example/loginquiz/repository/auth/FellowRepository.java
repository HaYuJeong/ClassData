package org.example.loginquiz.repository.auth;

import org.example.loginquiz.model.entity.auth.Fellow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.loginquiz.repository.auth
 * fileName : FellowRepository
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
@Repository
public interface FellowRepository extends JpaRepository<Fellow, String > {

}
