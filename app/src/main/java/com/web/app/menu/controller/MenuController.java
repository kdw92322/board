package com.web.app.menu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.web.app.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuservice;
	
	@GetMapping("/menuForm")
    public String menuForm(Model model) {
        return "/admin/menu/menuForm";
    }
	
	@GetMapping("/menuList")
	public String menuList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectMenuList = menuservice.selectMenuList(paramMap);
		model.addAttribute("menuList", selectMenuList);
		return "/admin/menu/menuList";
	}
	
	@GetMapping("/getTopMenuList")
	@ResponseBody
	public List<Map<String, Object>> getTopMenuList(@RequestParam Map<String, Object> paramMap) throws Exception {
		return menuservice.selectMenuList(paramMap);
	}
	
	@GetMapping("/getSideMenuList")
	@ResponseBody
	public List<Map<String,Object>>  getSideMenuList(@RequestParam Map<String, Object> paramMap) throws Exception {
		return menuservice.selectSideMenuList(paramMap);
	}
	
	@PostMapping("/insertMenuInfo")
	@ResponseBody
	public int insertMenuInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int insertMenuInfo = menuservice.insertMenuInfo(saveMap);
		return insertMenuInfo;
	}
	
	@PutMapping("/updateMenuInfo")
	@ResponseBody
	public int updateMenuInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int updateMenuInfo = menuservice.updateMenuInfo(saveMap);
		return updateMenuInfo;
	}
	
	@DeleteMapping("/deleteMenuInfo")
    @ResponseBody
	public int deleteMenuInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = menuservice.deleteMenuInfo(saveMap);
		return result;
	}
	
	@PostMapping("/insertSubMenuInfo")
	@ResponseBody
	public int insertViewInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int insertInfo = menuservice.insertSubMenuInfo(saveMap);
		return insertInfo;
	}
	
	@PutMapping("/updateSubMenuInfo")
	@ResponseBody
	public int updateViewInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int updateWebviewInfo = menuservice.updateSubMenuInfo(saveMap);
		return updateWebviewInfo;
	}
	
	@DeleteMapping("/deleteSubMenuInfo")
	@ResponseBody
	public int deleteViewInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		return menuservice.deleteSubMenuInfo(saveMap);
	}
	
	@GetMapping("/subMenuList")
	public String subMenuList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectSubMenuList = menuservice.selectMenuList(paramMap);
		model.addAttribute("parentcode", paramMap.get("parentcode"));
		model.addAttribute("parentname", paramMap.get("parentname"));
		model.addAttribute("level", paramMap.get("level"));
		model.addAttribute("subMenuList", selectSubMenuList);
		return "/admin/menu/subMenuList";
	}
}
