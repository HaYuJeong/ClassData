package org.example.querydslexam.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName : org.example.querydslexam.config
 * fileName : QuerydslConfig
 * author : hayj6
 * date : 2024-04-16(016)
 * description : Querydsl 설정 파일
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16(016)         hayj6          최초 생성
 */
@Configuration
public class QuerydslConfig {
    @PersistenceContext
    private EntityManager entityManager;   // JPA 관리 매니저

    //    TODO: JPA 관리 매니저를 querydsl 패키지에 넣기 : 연결
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
