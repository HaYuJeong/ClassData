package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.BoardDao;
import org.example.modelexam.model.Board;
import org.example.modelexam.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * packageName : org.example.modelexam.service.exam01
 * fileName : BoardService
 * author : hayj6
 * date : 2024-03-15(015)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-15(015)         hayj6          최초 생성
 */
@Service        // BoardService 클래스를 만듬(DB를 조회/수정/삭제/추가하는 함수를 정의), IOC(제어의 역전)
public class BoardService {
    //  todo: 연습 2)
//    BoardService 클래스를 만들고 findAll() 함수를 정의한다.
//    BoardController 클래스를 만들어서 getBoardAll() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : board_all.jsp) 전송해 보세요.
//    DB 접속하는 클래스 : BoardDao
//    url : /board
    @Autowired          // @Autowired : 미리 생성해둔 객체를 받는 어노테이션 (boardDao라는 이름으로 객체를 가져옴), DI(의존성 주입)
    BoardDao boardDao;  // BoardDao : DB를 조회/수정/삭제/추가하는 함수가 있는 클래스
//  BoardDao boardDao = new BoardDao(); 로 해야하는걸 간략하게 만들어줌

    public List<Board> findAll(){                   // findAll() 함수를 정의
        List<Board> list = boardDao.selectAll();    // boardDao를 전체조회하는 함수를 뜻하며 그 변수가 list
        return list;                                // return 값은 list
    }

    //  todo: 연습 2-2)
//    BoardService 클래스를 만들고 findById() 함수를 정의한다.
//    BoardController 클래스를 만들어서 getBoardId() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : board_id.jsp) 전송해 보세요.
//    url : /board/{id}
//    jsp : exam01/board/board_id.jsp
//    url test : http://localhost:8000/exam01/board/1

//    기본키(게시판 번호 : id)로 상세조회 : 1건나옴
    public Board findById(int id){

        Board board = boardDao.selectById(id);  // 기본키인 id로 DB를 상세조회하는 함수 실행

        return board;                           // 그 id값으로 조회한걸 넣은 변수 board를 return
    }

    //  todo: 연습 2-3)
//    BoardService 클래스를 만들고 save() 함수를 정의한다.
//    BoardController 클래스를 만들어서 addBoard(),
//    createBoard() 함수를 정의
//    addBoard()
//    - url : /board/additions
//    - jsp : exam01/board/add_board.jsp
//    createBoard()
//    - url : /board/add
//    - redirect url : /exam01/board
    public List<Board> save(Board board){       // save 함수 정의

List<Board> list = null;
//  게시판번호가 없으면(null) : insert
//  게시판번호가 있으면       : update
        if(board.getId() == null) {
//        TODO: DB 저장함수 (insert 함수) // 2. insert를 이 안에 넣기
            list = boardDao.insert(board); // 게시판번호 자동생성(sequence)
        } else{
//        TODO: DB 수정함수 (update 함수)
            list = boardDao.update(board);
        }
        return list;
    }

    //  todo: 연습 5-2)
//    BoardService 클래스를 만들고 save() 함수를 정의한다.
//    BoardController 클래스를 만들어서 editBoard(), updateBoard() 함수를 정의
//    editBoard()
//    - url : /board/edition/{id}
//    - jsp : exam01/board/update_board.jsp
//    updateBoard()
//    - url : /board/edit/{id}
//    - redirect url : /exam01/board
//    editBoard() : 수정페이지 열기 함수
//    - url : /board/edition/{id}
//    - jsp : exam01/board/update_board.jsp


//    삭제 함수
    public boolean removeById(int id){
        int count = boardDao.deleteById(id);
        return (count > 0)? true : false;
    }
}