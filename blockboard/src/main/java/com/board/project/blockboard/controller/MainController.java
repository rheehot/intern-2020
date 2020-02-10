/**
 * @author Woohyeok Jun <woohyeok.jun@worksmobile.com>
 * @file MainController.java
 */
package com.board.project.blockboard.controller;

import com.board.project.blockboard.dto.BoardDTO;
import com.board.project.blockboard.dto.UserDTO;
import com.board.project.blockboard.service.BoardService;
import com.board.project.blockboard.service.CompanyService;
import com.board.project.blockboard.service.FunctionService;
import com.board.project.blockboard.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @Autowired
  private BoardService boardService;
  @Autowired
  private UserService userService;
  @Autowired
  private FunctionService functionService;
  @Autowired
  private CompanyService companyService;

  @GetMapping("")
  public String home() {
    return "redirect:/login";
  }

  /**
   * 메인 화면
   *
   * @author Dongwook Kim <dongwook.kim1211@worksmobile.com>
   */
  @GetMapping("/main")
  public String getMainContent(HttpServletRequest request,
      Model model) {  // 일일이 예외처리 안해서 Exception으로 수정 (동욱)

    UserDTO userData = new UserDTO(request);

    List<BoardDTO> boardList = boardService.getBoardListByCompanyID(userData.getCompanyID());

    model.addAttribute("boardList", boardList); //게시판 목록
    model.addAttribute("companyName",
        companyService.getCompanyNameByUserID(userData.getUserID()));//회사이름
    model.addAttribute("userType", userService.getUserTypeByUserID(userData.getUserID()));
    model.addAttribute("companyID", userData.getCompanyID());
    model.addAttribute("userID", userData.getUserID());
    model.addAttribute("userName", userService.getUserNameByUserID(userData.getUserID()));
    model.addAttribute("functionInfoList",
        functionService.getfunctionInfoListByCompanyID(userData.getCompanyID()));

    return "boards";
  }
}
