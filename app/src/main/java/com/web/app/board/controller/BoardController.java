package com.web.app.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public List<Map<String, Object>> selectboardList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		System.out.println("paramMap : " + paramMap);
		List<Map<String, Object>> selectboardList = boardservice.selectboardList(paramMap);
		return selectboardList;
	}
}
