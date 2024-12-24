package com.web.app.log.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.log.service.LogService;

@Controller
@RequestMapping("log")
public class LogController {
	
	@Autowired
	private LogService logservice;
	
	/* Service Log View form 조회 */
	@GetMapping("/serviceLogView")
    public String serviceLogView() {
        return "log/serviceLogView";
    }
	
	/* Service Log Master View 조회 */
	@GetMapping("/serviceLogMstView")
	public String serviceLogMstView(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> mstLogList = logservice.selectMstServiceLogList(paramMap);
		model.addAttribute("mstLogList", mstLogList);
		
		return "log/serviceLogMstView";
	}
	
	/* Service Log Detail View 조회 */
	@GetMapping("/serviceLogDtlView")
	public String serviceLogDtlView(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		Map<String, Object> dtlLogInfo = logservice.selectDtlServiceLogInfo(paramMap);
		model.addAttribute("dtlLogInfo", dtlLogInfo);
		
		return "log/serviceLogDtlView";
	}
	
	/* connect Log View form 조회 */
	@GetMapping("/connectLogView")
    public String connectLogView() {
        return "log/connectLogView";
    }
	
	/* connect Log Master View 조회 */
	@GetMapping("/connectLogMstView")
	public String connectLogMstView(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> mstLogList = logservice.selectMstConnectLogList(paramMap);
		model.addAttribute("mstLogList", mstLogList);
		
		return "log/connectLogMstView";
	}
	
	/* connect Log Detail View 조회 */
	@GetMapping("/connectLogDtlView")
	public String connectLogDtlView(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> dtlLogList = logservice.selectDtlConnectLogList(paramMap);
		model.addAttribute("dtlLogList", dtlLogList);
		
		return "log/connectLogDtlView";
	}
	
	@GetMapping("/connectLogDtlViewList")
	@ResponseBody
	public List<Map<String, Object>> connectLogDtlViewList(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
		List<Map<String, Object>> dtlLogList = logservice.selectDtlConnectLogList(paramMap);
		return dtlLogList;
	}
}
