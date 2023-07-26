package com.web.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request, Principal principal ) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login_form");

		if(principal == null) {
			mv.setViewName("login_form");
		}else {
			mv.setViewName("/index");
		}
		
		return mv;
	}
	
	@GetMapping("/test")
	public String test() {
		return "test/test";
	}
	
	@GetMapping("test/diagram1")
	public String testDiagram1() {
		return "test/diagram1";
	}
	
	@GetMapping("test/diagram2")
	public String testDiagram2() {
		return "test/diagram2";
	}
}
