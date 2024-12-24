package com.web.app.user;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.util.DateUtil;
import com.web.app.util.DefaultDataUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
    @GetMapping("/signup")
    public String signup(Model model) {  	
    	DefaultDataUtil defaultdatautil = new DefaultDataUtil();
    	DateUtil dateutil = new DateUtil();
    	
    	model.addAttribute("emailAddrs", defaultdatautil.getEmailDomain());
    	model.addAttribute("serials", defaultdatautil.getHpSerialNum());
    	model.addAttribute("years", dateutil.getYearList());
    	model.addAttribute("months", dateutil.getMonthList());
    	
    	return "signup_form";
    }

    @PostMapping("/signup")
    @ResponseBody
    public UserInfo signup(@RequestBody UserForm userform) {
    	UserInfo user = userService.create(userform);
    	return user;
    }
    
    @PostMapping("/chkDupId")
    @ResponseBody
	public Map<String, Object> chkDupId(@RequestBody Map<String, Object> ParamMap) throws Exception {
		return userService.chkDupId(ParamMap);
	}
    
    @GetMapping("/logoutProcess")
    public String logout(HttpServletRequest request, Principal principal) {
    	return "logout_form";
    }
    
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
    	List<Map<String, Object>> selectUserList = userService.selectUserList(paramMap);
    	model.addAttribute("userList", selectUserList);
    	return "admin/user/userInfo";
    }
    
    @GetMapping("/selectUserList")
    @ResponseBody
    public List<Map<String, Object>> selectUserList(@RequestParam Map<String, Object> paramMap) throws Exception {
    	List<Map<String, Object>> selectUserList = userService.selectUserList(paramMap);
    	return selectUserList;
    }
    
    @PostMapping("/selectUserInfo")
	public String selectUserInfo(@RequestBody String userId, Model model) throws Exception {
    	userId = userId.replace("=", "");
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("userId", userId);
    	UserForm selectUserInfo = userService.selectUserInfo(paramMap);
    	
    	DefaultDataUtil defaultdatautil = new DefaultDataUtil(); 
    	DateUtil dateutil = new DateUtil();
    	model.addAttribute("emailAddrs", defaultdatautil.getEmailDomain());
    	model.addAttribute("serials", defaultdatautil.getHpSerialNum());
    	model.addAttribute("years", dateutil.getYearList());
    	model.addAttribute("months", dateutil.getMonthList());
    	model.addAttribute("days", dateutil.getDayList(selectUserInfo.getYear(), selectUserInfo.getMonth()));
    	model.addAttribute("userform", selectUserInfo);
    	
		return "admin/user/userInfo_form";
	}
    
    @PutMapping("/updateUserInfo")
    @ResponseBody
	public Map<String, Object> updateUserInfo(@RequestBody UserForm userform) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	userService.update(userform);
    	resultMap.put("result", "1");
		return resultMap;
	}
    
	@DeleteMapping("/deleteUserInfo")
	@ResponseBody
	public int deleteBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		String userId = String.valueOf(paramMap.get("userId"));
		
		/* 유저정보 및 관련 image도 같이 delete하기 위한 parameter */
		deleteMap.put("userId", userId);
		deleteMap.put("refKey", userId);
		deleteMap.put("refWord", "USER");
		int rescnt = userService.delete(deleteMap);
		return rescnt;
	}
	
	@GetMapping("/getConnUserLogData")
	@ResponseBody
	public List<Map<String, Object>> getConnUserLogData(@RequestParam Map<String,Object> paramMap){
		return userService.getConnUserLogData(paramMap);
	}
	
}
