package com.web.app.webview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.app.webview.service.WebViewService;

@Controller
@RequestMapping("/webview")
public class WebViewController {
	
	@Autowired
	private WebViewService webviewservice;
	
	@GetMapping("webviewForm")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String webviewList(Model model) {
        return "/admin/webview/webviewForm";
    }
	
	@GetMapping("/menuList")
	public String menuList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectMenuList = webviewservice.selectMenuList(paramMap);
		model.addAttribute("menuList", selectMenuList);
		return "/admin/webview/menuList";
	}
	
	@GetMapping("/viewList")
	public String viewList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectViewList = webviewservice.selectViewList(paramMap);
		model.addAttribute("menucode", paramMap.get("menucode"));
		model.addAttribute("menutype", paramMap.get("menutype"));
		model.addAttribute("viewList", selectViewList);
		return "/admin/webview/viewList";
	}
	
	@GetMapping("/authList")
	public String authList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectAuthList = webviewservice.selectAuthList(paramMap);
		model.addAttribute("authList", selectAuthList);
		return "/admin/webview/authList";
	}
	
	@PostMapping("/insertMenuInfo")
	@ResponseBody
	public int insertMenuInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int insertWebviewMenuInfo = webviewservice.insertMenuInfo(saveMap);
		return insertWebviewMenuInfo;
	}
	
	@PutMapping("/updateMenuInfo")
	@ResponseBody
	public int updateMenuInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int updateWebviewMenuInfo = webviewservice.updateMenuInfo(saveMap);
		return updateWebviewMenuInfo;
	}
	
	@DeleteMapping("/deleteMenuInfo")
    @ResponseBody
	public int deleteMenuInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = webviewservice.deleteMenuInfo(saveMap);
		return result;
	}
	
	@PostMapping("/insertViewInfo")
	@ResponseBody
	public int insertViewInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int insertWebviewInfo = webviewservice.insertViewInfo(saveMap);
		return insertWebviewInfo;
	}
	
	@PutMapping("/updateViewInfo")
	@ResponseBody
	public int updateViewInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int updateWebviewInfo = webviewservice.updateViewInfo(saveMap);
		return updateWebviewInfo;
	}
	
	@DeleteMapping("/deleteViewInfo")
	@ResponseBody
	public int deleteViewInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		return webviewservice.deleteViewInfo(saveMap);
	}
	
}
