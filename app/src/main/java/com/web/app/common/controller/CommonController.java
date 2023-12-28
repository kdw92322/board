package com.web.app.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.web.app.common.service.CommonService;

@Controller
@RequestMapping("common")
public class CommonController {
	
	@Autowired
	private CommonService commonservice;
	
	/*
	 * 1. 등록된 상단 메뉴&화면 목록 조회
	 */
	@GetMapping("/topMenuList")
	@ResponseBody
    public List<Map<String, Object>> topMenuList() throws Exception {  	
    	return commonservice.topMenuList();
    }
	
	@GetMapping("/topViewList")
	@ResponseBody
    public List<Map<String, Object>> topViewList(@RequestParam Map<String, Object> param) throws Exception {
    	return commonservice.topViewList(param);
    }
	
	/*
	 * 2. 환경설정
	 */
	@GetMapping("/envForm")
    public String environment(@RequestParam Map<String, Object> param) throws Exception {
    	return "/admin/env/envForm";
    }
	
	/*
	 * 3. 코드등록
	 */
	@GetMapping("/codeForm")
    public String code(@RequestParam Map<String, Object> param) throws Exception {
    	return "/admin/code/codeForm";
    }
	
	/*
	 * 3-1. 대분류(CODE) List Select View
	 */
	@GetMapping("/selectCodeMstList")
    public String selectCodeMstList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> selectCodeMstList = commonservice.selectCodeMstList(paramMap);
		model.addAttribute("codeMstList", selectCodeMstList);
    	return "/admin/code/codeMstList";
    }
	
	/*
	 * 3-2. 대분류(CODE) Insert
	 */
	@PostMapping("/insertCodeMst")
	@ResponseBody
    public int insertCodeMst(@RequestBody Map<String, Object> saveMap) throws Exception {
    	return commonservice.insertCodeMst(saveMap);
    }
	
	/*
	 * 3-3. 대분류(CODE) Update
	 */
	@PutMapping("/updateCodeMst")
	@ResponseBody
	public int updateCodeMst(@RequestBody Map<String, Object> saveMap) throws Exception {
    	return commonservice.updateCodeMst(saveMap);
    }
	
	/*
	 * 3-4. 대분류(CODE) Delete
	 */
	@DeleteMapping("/deleteCodeMst")
	@ResponseBody
	public int deleteCodeMst(@RequestBody Map<String, Object> saveMap) throws Exception {
    	return commonservice.updateCodeMst(saveMap);
    }
	
	/*
	 * 3-5. 소분류(CODE) List Select View
	 */
	@GetMapping("/selectCodeDtlList")
    public String selectCodeDtlList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		System.out.println("paramMap : " + paramMap);
		List<Map<String, Object>> selectCodeDtlList = commonservice.selectCodeDtlList(paramMap);
		model.addAttribute("mstCd", paramMap.get("mstCd"));
		model.addAttribute("codeDtlList", selectCodeDtlList);
    	return "/admin/code/codeDtlList";
    }
	
	/*
	 * 3-6. 소분류(CODE) Insert
	 */
	@PostMapping("/insertCodeDtl")
	@ResponseBody
    public int insertCodeDtl(@RequestBody Map<String, Object> saveMap) throws Exception {
    	return commonservice.insertCodeMst(saveMap);
    }
	
	/*
	 * 3-7. 소분류(CODE) Update
	 */
	@PutMapping("/updateCodeDtl")
	@ResponseBody
	public int updateCodeDtl(@RequestBody Map<String, Object> saveMap) throws Exception {
    	return commonservice.updateCodeMst(saveMap);
    }
	
	/*
	 * 3-8. 소분류(CODE) Delete
	 */
	@DeleteMapping("/deleteCodeDtl")
	@ResponseBody
	public int deleteCodeDtl(@RequestBody Map<String, Object> saveMap) throws Exception {
    	return commonservice.updateCodeMst(saveMap);
    }
}
