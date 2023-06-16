package com.web.app.calendar.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService{

	@Autowired
	private CalendarMapper calendarmapper; 
	
	@Override
	public List<Map<String, Object>> selectCalendarList(Map<String, Object> paramMap){
		// TODO Auto-generated method stub
		return calendarmapper.selectCalendarList(paramMap);
	}
	
	@Override
	public Map<String, Object> selectDayInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return calendarmapper.selectDayInfo(paramMap);
	}
	
	@Override
	public int insertCalendarInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return calendarmapper.insertCalendarInfo(saveMap);
	}

	@Override
	public int updateCalendarInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return calendarmapper.updateCalendarInfo(saveMap);
	}

	@Override
	public int deleteCalendarInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return calendarmapper.deleteCalendarInfo(saveMap);
	}
}
