package org.example.modelexam.dao;

import org.example.modelexam.model.Faq;
import org.example.modelexam.testdata.SampleFaq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.example.modelexam.dao
 * fileName : DeptDao
 * author : kangtaegyung
 * date : 2022/10/15
 * description : 샘를 데이터를 직접 조회/추가/삭제/수정하는 기본적인 함수들의 모임(DB 직접 접근 클래스)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/15         kangtaegyung          최초 생성
 */
@Repository
public class FaqDao {

    @Autowired
    SampleFaq sampleFaq; // 스프링부트 컨테이너에서 샘플 데이터 객체 하나 가져오기

    //    모든 샘를 데이터 조회 함수
    public List<Faq> selectAll() {
        List<Faq> list = sampleFaq.getList();

        return list;
    }

    //    아이디로 조회하는 함수
    public Faq selectById(int id) {
        List<Faq> list = sampleFaq.getList();

        Faq resFaq = null;

//        id에 해당하는 값 찾기
        for (Faq faq : list) {
            if(faq.getFno() == id) {
                resFaq = faq;
            }
        }

        return resFaq;
    }

//    데이터를 저장하는 함수
    public List<Faq> insert(Faq faq) {

        List<Faq> list = sampleFaq.getList();

        int count = selectAll().size(); // 전체 건수
        int newId = count + 1;
        faq.setFno(newId); // 새로운 번호 저장

        list.add(faq);

        sampleFaq.setList(list);

        return sampleFaq.getList();
    }

//    데이터를 수정하는 함수
    public List<Faq> update(Faq faq) {

        List<Faq> list = sampleFaq.getList();

        if(faq.getFno() != null) {
            //            수정
            for (Faq element : list) {
                if ((int)element.getFno() == (int)faq.getFno()) {
                    element.setFno(faq.getFno());
                    element.setSubject(faq.getSubject());
                    element.setText(faq.getText());
                    element.setUpdateTime(faq.getUpdateTime());
                }
            }
        }

        return list;
    }

//  데이터를 삭제하는 함수
    public int deleteById(int id) {
        List<Faq> list = sampleFaq.getList();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFno() == id) {
                list.remove(i);
                return 1;
            }
        }

        return 0;
    }
}











