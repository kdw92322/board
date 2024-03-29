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
import org.springframework.web.bind.annotation.RestController;

import com.web.app.calendar.service.CalendarService;
import com.web.app.calendar.service.CalendarVO;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarService calendarservice;
	
	//1.SELECT
	@GetMapping("/selectCalendarList")
	public List<CalendarVO> selectCalendarList(@RequestParam Map<String, Object> paramMap) throws Exception {
		List<CalendarVO> list = calendarservice.selectCalendarList(paramMap);
		return list;
	}
	
	//2.INSERT
	@PostMapping("/insertCalendarInfo")
	public int insertCalendarInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = calendarservice.insertCalendarInfo(saveMap);
		return result;
	}
	
	//3.UPDATE
	@PutMapping("/updateCalendarInfo")
	public int updateCalendarInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = calendarservice.updateCalendarInfo(saveMap);
		return result;
	}
	
	//4.DELETE
	@DeleteMapping("/deleteCalendarInfo")
	public int deleteCalendarInfo(@RequestBody Map<String, Object> saveMap) throws Exception {
		int result = calendarservice.deleteCalendarInfo(saveMap);
		return result;
	}
	
}
