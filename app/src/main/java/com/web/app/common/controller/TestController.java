package com.web.app.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("test")
public class TestController {
	
	@PostMapping("postApi")
	@ResponseBody
	public Map<String, Object> temp(@RequestBody Map<String, Object> paramMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "success");
		return resultMap;
	}
	
	@GetMapping("/realtest")
	public String realtest(Model model) {
		return "/test/realtest";
	}
	
	@GetMapping("/test")
	public String test() {
		return "/test/test";
	}
	
	@GetMapping("media")
	public String testDiagram1() {
		return "test/media";
	}
	
	@GetMapping("/test1")
	public String test1() {
		return "test/test1";
	}
	
	@GetMapping("/test2")
	public String test2(@RequestParam Map<String, Object> paramMap, Model model) {
		return "test/test2";
	}
}
