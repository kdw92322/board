package com.web.app.calendar.controller;

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
import com.web.app.calendar.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarService calendarservice;
	
	//1.SELECT
	@GetMapping("/selectCalendarList")
	@ResponseBody
	public List<Map<String, Object>> selectCalendarList(@RequestParam Map<String, Object> paramMap) throws Exception {
		return calendarservice.selectCalendarList(paramMap);
	}
	
	//2.INSERT
	@PostMapping("/insertCalendarInfo")
	@ResponseBody
	public int insertCalendarInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = calendarservice.insertCalendarInfo(saveMap);
		return result;
	}
	
	//3.UPDATE
	@PutMapping("/updateCalendarInfo")
	@ResponseBody
	public int updateCalendarInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = calendarservice.updateCalendarInfo(saveMap);
		return result;
	}
	
	//4.DELETE
	@DeleteMapping("/deleteCalendarInfo")
	@ResponseBody
	public int deleteCalendarInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = calendarservice.deleteCalendarInfo(saveMap);
		return result;
	}
	
}
