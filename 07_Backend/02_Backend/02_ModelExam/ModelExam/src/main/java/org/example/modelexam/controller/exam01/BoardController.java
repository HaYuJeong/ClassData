package org.example.modelexam.controller.exam01;

import lombok.extern.slf4j.Slf4j;
import org.example.modelexam.model.Board;
import org.example.modelexam.model.Dept;
import org.example.modelexam.service.exam01.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exam01
 * fileName : BoardController
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
@Slf4j                                        // 로깅하기 위함
@Controller                                   // jsp로 연결, 함수에 url을 붙일 수 있음
@RequestMapping("/exam01")                 // 공통 url 작성
public class BoardController {                // BoardController 클래스 만듬
    @Autowired
    BoardService boardService;                 // 객체 정의
//  BoardService boardService = new BoardService();로 해야하는걸 간략하게 만들어줌

//    전체 조회
    @GetMapping("/board")                    // 함수에 url을 부여하는 어노테이션
    public String getBoardAll(Model model){     // getBoardAll 함수 정의 : 전체 조회

        List<Board> list = boardService.findAll();  // 전체조회하는 서비스 함수 실행. 객체 배열로 결과가 나옴

        model.addAttribute("list", list);   // 배열을 model에 담아서 jsp로 전송

        return "/exam01/board/board_all.jsp";           // jsp 주소를 return
    }

//    상세 조회(기본키_게시판 번호_id 로 조회)
    @GetMapping("/board/{id}")              // 웹 url에 값이 들어오면 조회 : 파라메터 방식
    public String getBoardId(@PathVariable int id,  // 파라메터 방식은 @PathVariable 사용
                             Model model){
        Board board = boardService.findById(id);    // 상세조회하는 함수 실행
                                                    // boardService의 findById 함수에서 id로 조회한 값을 board에 저장

        model.addAttribute("board", board); // model에 객체 한개를 담아서 jsp로 전달

        return "/exam01/board/board_id.jsp";        // return 값은 jsp 주소
    }

//    부서 추가 페이지 열기 함수 #1
    @GetMapping("/board/addition")
    public String addBoard(){
        return "exam01/board/add_board.jsp";
    }

//    저장 버튼 클릭 시 실행될 함수 createBoard() #2
    @PostMapping("/board/add")
    public RedirectView createBoard(
            @ModelAttribute Board board){
        boardService.save(board);   // DB 저장 함수 실행

        return new RedirectView("/exam01/board");   // 전체 페이지로 강제 이동
    }

//    id 클릭시 수정 페이지 열기
@GetMapping("/board/edition/{id}")
    public String editBoard(@PathVariable int id,
                                    Model model){
        Board board = boardService.findById(id);

        model.addAttribute("board", board);

        return "exam01/board/update_board.jsp";
    }

//    수정 버튼 누르면 나오는 페이지
    @PutMapping("/board/edit/{id}")
    public RedirectView updateBoard(@PathVariable int id,
                                   @ModelAttribute Board board){   // 객체를 받아서 jsp로 전달
        // Model model은 값을 받아서 jsp로 전달
//          TODO: 수정 함수(서비스) 호출
//              => save : if(insert) else(update)가 있음 : 부서번호 null이면 insert, null 아니면 update
         boardService.save(board);
//          TODO: 전체 조회 페이지로 강제 이동 : /exam01/dept
        return new RedirectView("/exam01/board");
    }

    @DeleteMapping("/board/delete/{id}")    // 조건에 해당하는것만 삭제하는 조건달기
    public RedirectView deleteDept(@PathVariable int id){
//      TODO: DB 삭제 함수 실행
        boardService.removeById(id);
//        TODO: 삭제 후 전체조회 페이지로 강제 이동
        return new RedirectView("/exam01/board");
    }
}