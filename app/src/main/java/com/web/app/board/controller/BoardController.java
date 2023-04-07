package com.web.app.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<Map<String, Object>> selectboardList = boardservice.selectboardList(paramMap);
		return selectboardList;
	}
	
	@GetMapping("/boardPopup")
	public String boardModal(@RequestParam Map<String, Object> paramMap, Model model) {
		model.addAttribute("idx", paramMap.get("idx"));
		return "board/boardPopup";
	}
	
	@PostMapping("/insertBoardList")
	@ResponseBody
	public int insertBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int insertBoardList = boardservice.insertBoardList(paramMap);
		return insertBoardList;
	}
	
	@PostMapping("/updateBoardList")
	@ResponseBody
	public int updateBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int updateBoardList = boardservice.updateBoardList(paramMap);
		return updateBoardList;
	}
	@PostMapping("/deleteBoardList")
	@ResponseBody
	public int deleteBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int deleteBoardList = boardservice.deleteBoardList(paramMap);
		return deleteBoardList;
	}
}
