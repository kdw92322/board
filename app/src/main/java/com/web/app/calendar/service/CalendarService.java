package com.web.app.calendar.service;

import java.util.List;
import java.util.Map;

public interface CalendarService {
	public List<Map<String, Object>> selectCalendarList(Map<String,Object> paramMap) throws Exception;
	public Map<String, Object> selectDayInfo(Map<String,Object> paramMap) throws Exception;
	public int insertCalendarInfo(Map<String,Object> paramMap) throws Exception;
	public int updateCalendarInfo(Map<String,Object> paramMap) throws Exception;
	public int deleteCalendarInfo(Map<String,Object> paramMap) throws Exception;
}
