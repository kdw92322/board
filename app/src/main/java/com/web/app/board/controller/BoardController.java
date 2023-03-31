package com.web.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@GetMapping("/boardList")
	public String boardList() {
		return "board/boardList";
	}
	
	@GetMapping("/selectBoardList")
	public String selectboardList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectboardList = boardservice.selectboardList(paramMap);
		model.addAttribute("selectboardList", selectboardList);

		return "board/boardList";
	}
}
