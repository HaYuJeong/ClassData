package org.example.querydslexam.repository;

import org.example.querydslexam.model.entity.basic.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.querydslexam.repository
 * fileName : DeptRepository
 * author : hayj6
 * date : 2024-04-16(016)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16(016)         hayj6          최초 생성
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
}
