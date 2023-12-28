package com.web.app.webview.service;

import java.util.List;
import java.util.Map;

public interface WebViewService {
	public List<Map<String,Object>> selectMenuList(Map<String,Object> paramMap) throws Exception;
	public List<Map<String,Object>> selectViewList(Map<String,Object> paramMap) throws Exception;
	public List<Map<String,Object>> selectAuthList(Map<String,Object> paramMap) throws Exception;
	
	public int insertMenuInfo(Map<String,Object> saveMap) throws Exception;
	public int updateMenuInfo(Map<String,Object> saveMap) throws Exception;
	public int deleteMenuInfo(Map<String,Object> saveMap) throws Exception;
	
	public int insertViewInfo(Map<String,Object> saveMap) throws Exception;
	public int updateViewInfo(Map<String,Object> saveMap) throws Exception;
	public int deleteViewInfo(Map<String,Object> saveMap) throws Exception;
}
