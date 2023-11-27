package com.web.app.board.controller;

import java.security.Principal;
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

import com.web.app.boardreview.service.BoardReviewService;

@Controller
@RequestMapping("/boardReview")
public class BoardReviewController {
	
	@Autowired
	private BoardReviewService boardreviewservice;
	

	/* 게시물 목록 조회 */
	@GetMapping("/selectreviewList")
	public String reviewBoardList(@RequestParam Map<String, Object> paramMap, Model model, Principal principal) throws Exception {
		paramMap.put("loginId", principal.getName());
		List<Map<String, Object>> reviewList = boardreviewservice.selectreviewList(paramMap);
		model.addAttribute("reviewList", reviewList);
		return "board/boardReview";
	}

	/* 1. 저장 */
	@PostMapping("/insertBoardReview")
	@ResponseBody
	public int insertBoardReview(@RequestBody Map<String, Object> saveMap) throws Exception {
		int insertBoardReview = boardreviewservice.insertBoardReview(saveMap);
		return insertBoardReview;
	}
	
	/* 2-1. 수정(리뷰) */
	@PutMapping("/updateBoardReview")
	@ResponseBody
	public int updateBoardReview(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = boardreviewservice.updateBoardReview(saveMap);
		return result;
	}
	
	/* 2-2. 수정(like) */
	@PutMapping("/updateReviewLike")
	@ResponseBody
	public int updateReviewLike(@RequestBody Map<String, Object> saveMap, Principal principal) throws Exception {
		int result = boardreviewservice.updateReviewLike(saveMap);
		return result;
	}
	
	/* 2-3. 수정(hate) */
	@PutMapping("/updateReviewHate")
	@ResponseBody
	public int updateReviewHate(@RequestBody Map<String, Object> saveMap, Principal principal) throws Exception {
		int result = boardreviewservice.updateReviewHate(saveMap);
		return result;
	}
	
	/* 3. 삭제 */
	@DeleteMapping("/deleteBoardReview")
	@ResponseBody
	public void deleteBoardReview(@RequestBody Map<String, Object> saveMap) throws Exception {
		boardreviewservice.deleteBoardReview(saveMap);
	}
	
	/* 3. 삭제 */
	@PostMapping("/insertLikeAndHateLog")
	@ResponseBody
	public int insertLikeAndHateLog(@RequestBody Map<String, Object> saveMap, Principal principal) throws Exception {
		saveMap.put("loginId", principal.getName());
		int result = boardreviewservice.insertLikeAndHateLog(saveMap);
		return result;
	}
}
