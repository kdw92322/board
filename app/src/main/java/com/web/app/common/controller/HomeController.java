package com.web.app.common.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.user.UserService;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request, Principal principal ) {
		ModelAndView mv = new ModelAndView();
		
		if(principal == null) {
			mv.setViewName("login_form");
		}else {
			mv.setViewName("/index");
		}
		
		return mv;
	}
	
	@GetMapping("test")
	public String test() {
		return "test/test";
	}
	
	@GetMapping("media")
	public String testDiagram1() {
		return "test/media";
	}
}
