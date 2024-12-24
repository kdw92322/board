package com.web.app.menu.service;

import java.util.List;
import java.util.Map;

public interface MenuService {
	public List<Map<String,Object>> selectMenuList(Map<String,Object> paramMap) throws Exception;
	public List<Map<String,Object>> selectSideMenuList(Map<String,Object> paramMap) throws Exception;
	
	public int insertMenuInfo(Map<String,Object> saveMap) throws Exception;
	public int updateMenuInfo(Map<String,Object> saveMap) throws Exception;
	public int deleteMenuInfo(Map<String,Object> saveMap) throws Exception;
	
	public int insertSubMenuInfo(Map<String,Object> saveMap) throws Exception;
	public int updateSubMenuInfo(Map<String,Object> saveMap) throws Exception;
	public int deleteSubMenuInfo(Map<String,Object> saveMap) throws Exception;
}
