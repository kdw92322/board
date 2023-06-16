package com.web.app.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@AuthenticationPrincipal UserInfo userinfo, @Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",  "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        
        try {
        	Date date = new Date();
        	userService.create(
        			userCreateForm.getUserId(),	
        			userCreateForm.getUsername(),
        			userCreateForm.getEmail(),
        			userCreateForm.getPassword1(),
        			userCreateForm.getBirth(),
        			userCreateForm.getPhone(),
        			date
        	);
        }catch (DataIntegrityViolationException e) {
			// TODO: handle exception
        	e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
		}

        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
    
    @GetMapping("/userInfo")
    public String userInfo() {
        return "user/userInfo";
    }
    
    @GetMapping("/selectUserList")
	@ResponseBody
	public List<Map<String, Object>> selectUserList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectUserList = userService.selectUserList(paramMap);
		return selectUserList;
	}
    
}
