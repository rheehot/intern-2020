/**
 * @author Dongwook Kim <dongwook.kim1211@worksmobile.com>
 * @file BoardController.java
 */
package com.board.project.blockboard.controller;


import com.board.project.blockboard.common.validation.AuthorityValidation;
import com.board.project.blockboard.dto.BoardDTO;
import com.board.project.blockboard.dto.UserDTO;
import com.board.project.blockboard.service.BoardService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

  @Autowired
  private BoardService boardService;

  /**
   * 게시판 목록 가져오기
   *
   * @return 게시판 목록
   */
  @GetMapping(value = "")
  @ResponseBody
  public List<BoardDTO> getBoardList(HttpServletRequest request) {
    UserDTO userData = new UserDTO(request);

    //게시판 목록
    List<BoardDTO> boardList = boardService
        .getBoardListByCompanyId(userData.getCompanyId()); // select로 받아오기
    return boardList;
  }

  /**
   * 게시판 추가
   *
   * @param newBoardName 새로입력받은 보드이름
   */
  @PostMapping(value = "")
  @ResponseBody
  public void insertNewBoard(@RequestParam("boardName") String newBoardName,
      HttpServletRequest request) {
    UserDTO userData = new UserDTO(request);
    if (AuthorityValidation.isAdmin(userData)) {
      boardService.insertNewBoard(newBoardName, userData.getCompanyId());
    }

  }

  /**
   * 게시판 이름 변경 변경된 리스트를 받아와서 수정한다.
   *
   * @param newTitleList 이름이 변경된 리스트 실제로 기존값과 값이 달라진 경우만 리스트로 받아온다.
   */
  @PutMapping(value = "")
  @ResponseBody
  public void changeNewBoardName(@RequestBody List<BoardDTO> newTitleList,
      HttpServletRequest request) {
    UserDTO userData = new UserDTO(request);
    if (AuthorityValidation.isAdmin(userData)) {
      boardService.updateChangedName(newTitleList);
    }
  }

  /**
   * 게시판 삭제
   *
   * @param deleteBoards 삭제리스트 (체크된 리스트)
   */
  @DeleteMapping(value = "")
  @ResponseBody
  public void deleteBoardByBoardId(@RequestBody List<BoardDTO> deleteBoards,
      HttpServletRequest request) {
    UserDTO userData = new UserDTO(request);
    if (AuthorityValidation.isAdmin(userData)) {
      boardService.deleteBoardsByDeleteBoardList(deleteBoards); //기존데이터
    }
  }

}