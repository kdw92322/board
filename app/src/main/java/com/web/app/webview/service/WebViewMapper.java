package com.web.app.webview.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebViewMapper {
	List<Map<String,Object>> selectMenuList(Map<String,Object> paramMap);	
	List<Map<String,Object>> selectViewList(Map<String,Object> paramMap);
	List<Map<String,Object>> selectAuthList(Map<String,Object> paramMap);
	
	int insertMenuInfo(Map<String,Object> saveMap);
	int updateMenuInfo(Map<String,Object> saveMap);
	int deleteMenuInfo(Map<String,Object> saveMap);
	
	int insertViewInfo(Map<String,Object> saveMap);
	int updateViewInfo(Map<String,Object> saveMap);
	int deleteViewInfo(Map<String,Object> saveMap);
	
}
