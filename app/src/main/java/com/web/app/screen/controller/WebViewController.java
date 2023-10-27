package com.web.app.screen.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.screen.service.WebViewService;

@Controller
@RequestMapping("/webview")
public class WebViewController {
	
	@Autowired
	private WebViewService webviewservice;
	
	@GetMapping("webviewInfo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String webviewList(Model model) {
        return "webview/webviewInfo";
    }
	
	@GetMapping("/selectViewList")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String selectViewList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectViewList = webviewservice.selectViewList(paramMap);
		model.addAttribute("viewList", selectViewList);
		return "webview/webviewList";
	}
	
	@GetMapping("/selectViewAuthList")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String selectViewAuthList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectViewAuthList = webviewservice.selectViewAuthList(paramMap);
		model.addAttribute("viewAuthList", selectViewAuthList);
		return "webview/webviewAuthList";
	}
	
	/*
	@PostMapping("/insertBoardList")
	@ResponseBody
	public int insertBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int insertBoardList = boardservice.insertBoardList(paramMap);
		return insertBoardList;
	}
	
	@PutMapping("/updateBoardList")
	@ResponseBody
	public int updateBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int updateBoardList = boardservice.updateBoardList(paramMap);
		return updateBoardList;
	}
	
	@DeleteMapping("/deleteBoardList")
	@ResponseBody
	public int deleteBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		int deleteBoardList = boardservice.deleteBoardList(paramMap);
		return deleteBoardList;
	}
	*/
}
