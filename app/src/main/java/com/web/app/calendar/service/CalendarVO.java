package com.web.app.calendar.service;

import lombok.Data;

@Data
public class CalendarVO {
	private String id;
	private String groupId;
	private String title;
	private String writer;
	private String content;
	
	//DashBoard Calendar Data Mapping
	private String start;
	private String end;
	
	//Calendar DayInfo Data Mapping
	private String startDt;
	private String startTi;
	private String endDt;
	private String endTi;
	
	
	private Boolean allDay;
	private String textColor;
	private String backgroundColor;
	private String borderColor;
}
