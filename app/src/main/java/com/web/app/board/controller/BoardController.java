package com.web.app.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	/* 게시판 */
	@GetMapping("/boardForm")
	public String boardForm() {
		return "board/boardForm";
	}
	
	/* 게시물 목록 조회 */
	@GetMapping("/boardList")
	public String boardList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> boardList = boardservice.selectboardList(paramMap);
		model.addAttribute("boardList", boardList);
		return "board/boardList";
	}
	
	/* 1. 조회 */
	@GetMapping("/selectBoardList")
	@ResponseBody
	public List<Map<String, Object>> selectboardList(Map<String, Object> paramMap, Model model) throws Exception {
		System.out.println("paramMap : " + paramMap);
		List<Map<String, Object>> selectboardList = boardservice.selectboardList(paramMap);
		return selectboardList;
	}
	
	/* 2. 저장 */
	@PostMapping("/insertBoardList")
	@ResponseBody
	public Map<String, Object> insertBoardList(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = boardservice.insertBoardList(saveMap);
		saveMap.put("rtnCnt", result);
		return saveMap;
	}
	
	/* 3. 수정 */
	@PutMapping("/updateBoardList")
	@ResponseBody
	public Map<String, Object> updateBoardList(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = boardservice.updateBoardList(saveMap);
		saveMap.put("rtnCnt", result);
		return saveMap;
	}
	
	/* 4. 삭제 */
	@DeleteMapping("/deleteBoardList")
	@ResponseBody
	public int deleteBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int result = boardservice.deleteBoardList(paramMap);
		return result;
	}
}
