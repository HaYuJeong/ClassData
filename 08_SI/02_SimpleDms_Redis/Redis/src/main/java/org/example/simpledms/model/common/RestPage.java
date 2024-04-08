package com.example.simpledms.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * packageName : com.example.backedu.entity
 * fileName : RestPage
 * author : kangtaegyung
 * date : 10/21/23
 * description : Redis Page Json 변환시 오류 해결을 위한 래퍼클래스 (pageable 속성을 레디스가 변환할때 에러가 발생함)
 *              - Page 객체에 포함되어 있는 pageable 를 json 변환시 해당 속성 무시
 *              - content, number, size, totalElements 만 리턴할 수 있도록 재정의함
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/21/23         kangtaegyung          최초 생성
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable"})
public class RestPage<T> extends PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPage(@JsonProperty("content") List<T> content,
                    @JsonProperty("number") int page,
                    @JsonProperty("size") int size,
                    @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(page, size), total);
    }

//    TODO: 생성자 함수
    public RestPage(Page<T> page) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
    }

}
