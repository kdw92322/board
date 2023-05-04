package com.web.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	/*
	@GetMapping("/")
	public RedirectView home() {
		return new RedirectView("/board/boardList");
	}
	*/
	
	@GetMapping("/")
	public ModelAndView index(Principal principal) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/boardList");
		//System.out.println("principal : " + principal);
		mv.setViewName("login_form");

		if(principal == null) {
			mv.setViewName("login_form");
		}else {
			mv.setViewName("/index");
			//System.out.println("Name : " + principal.getName());
			mv.addObject("id", principal.getName());
		}
		
		return mv;
	}
	
	@GetMapping("/test")
	public String test() {
		return "test/test";
	}
}
