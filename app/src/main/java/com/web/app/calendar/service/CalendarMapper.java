package com.web.app.calendar.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {
	List<Map<String,Object>> selectCalendarList(Map<String,Object> paramMap);
	
	Map<String,Object> selectDayInfo(Map<String,Object> paramMap);
	
	int insertCalendarInfo(Map<String, Object> paramMap); 

	int updateCalendarInfo(Map<String, Object> paramMap);
	
	int deleteCalendarInfo(Map<String, Object> paramMap);
}
