package com.web.app.user;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.util.DefaultDataUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
    @GetMapping("/signup")
    public String signup(UserForm userform, Model model) {  	
    	DefaultDataUtil defaultdatautil = new DefaultDataUtil(); 
    	model.addAttribute("emailAddrs", defaultdatautil.getEmailDomain());
    	model.addAttribute("serials", defaultdatautil.getHpSerialNum());
    	model.addAttribute("years", defaultdatautil.getYearList());
    	model.addAttribute("months", defaultdatautil.getMonthList());
    	
    	return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@AuthenticationPrincipal UserInfo userinfo, @Valid UserForm userform, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userform.getPassword1().equals(userform.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",  "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        
        try {
        	userService.create(userform);
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
    
    @GetMapping("/logoutProcess")
    public String logout(Principal principal) {
        System.out.println("로그아웃 테스트 : ");
    	return "logout_form";
    }
    
    
    @GetMapping("/userInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String userInfo(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
    	List<Map<String, Object>> selectUserList = userService.selectUserList(paramMap);
    	model.addAttribute("userList", selectUserList);
    	return "user/userInfo";
    }
    
    @PostMapping("/selectUserInfo")
	public String selectUserInfo(@RequestBody String userId, Model model) throws Exception {
    	userId = userId.replace("=", "");
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("userId", userId);
    	UserForm selectUserInfo = userService.selectUserInfo(paramMap);
    	
    	DefaultDataUtil defaultdatautil = new DefaultDataUtil(); 
    	
    	model.addAttribute("emailAddrs", defaultdatautil.getEmailDomain());
    	model.addAttribute("serials", defaultdatautil.getHpSerialNum());
    	model.addAttribute("years", defaultdatautil.getYearList());
    	model.addAttribute("months", defaultdatautil.getMonthList());
    	model.addAttribute("days", defaultdatautil.getDayList(selectUserInfo.getYear(), selectUserInfo.getMonth()));
    	model.addAttribute("userform", selectUserInfo);
    	
		return "user/userInfo_form";
	}
    
    @PostMapping("/updateUserInfo")
	public String updateUserInfo(@Valid UserForm userform) throws Exception {
		userService.update(userform);
		return "redirect:/user/userInfo";
	}
    
	@DeleteMapping("/deleteUserInfo/{userId}")
	public int deleteBoardList(@PathVariable("userId") String userId) throws Exception {
		int rescnt = userService.delete(userId);
		return rescnt;
	}
}
