package com.board.project.blockboard.controller;

import com.board.project.blockboard.model.Board;
import com.board.project.blockboard.model.User;
import com.board.project.blockboard.service.BoardService;
import com.board.project.blockboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    @Autowired
    BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/b")
    public String printBoardInfo(Model model) {

        List<Board> list = boardService.allBoard();

        model.addAttribute("board_id",list.get(0).getBoard_id());
        model.addAttribute("com_id",list.get(0).getCom_id());
        model.addAttribute("board_name",list.get(0).getBoard_name());
        System.out.println(model);
        return "board";
    }

}