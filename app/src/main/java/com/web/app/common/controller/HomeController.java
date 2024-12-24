package com.web.app.common.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.web.app.common.service.CommonService;
import com.web.app.user.UserInfo;
import com.web.app.user.UserRepository;
import com.web.app.util.DateUtil;
import com.web.app.util.DefaultDataUtil;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommonService commonservice;
	
	@GetMapping("/")
	public String home(Principal principal, Model model) throws Exception {
		String path = "";
		
		if(principal == null) {
			path = "login_form";
		}else {
	    	path = "/fragments/frame";
		}
		
		return path;
	}
	
	@GetMapping("/jwtLogin")
	public String jwtLoginPage() {
		return "/test/jwtLogin";
	}
	
	@GetMapping("/formlogin")
	public String formlogin() {
		return "login_form";
	}
	
	@GetMapping("/index")
	public String index(Principal principal, Model model) throws Exception {
		DateUtil dateutil = new DateUtil();
		DefaultDataUtil defaultUtil = new DefaultDataUtil();
		
		String username = principal.getName();
		model.addAttribute("loginID", username);
		
		Optional<UserInfo> _userInfo = userRepository.findById(username);
    	UserInfo userInfo = _userInfo.get();
    	
    	String authName = commonservice.getCodeName("role", userInfo.getAuthorities());
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	Date birth = format.parse(userInfo.getBirth());
    	
    	model.addAttribute("Email", userInfo.getEmail());
    	model.addAttribute("Birth", dateutil.DateToString(birth, "yyyy-MM-dd"));
    	model.addAttribute("Phone", defaultUtil.setHPformat(userInfo.getPhone()));
    	model.addAttribute("JoinDate", dateutil.DateToString(userInfo.getJoin_date(), "yyyy-MM-dd"));
    	model.addAttribute("Auth", authName);
		return "index";
	}
}