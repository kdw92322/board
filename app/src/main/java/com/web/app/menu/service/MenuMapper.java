package com.web.app.menu.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
	List<Map<String,Object>> selectMenuList(Map<String,Object> paramMap);
	int getMenuMaxlevel();
	List<Map<String,Object>> selectSideMenuList();
	List<Map<String,Object>> levelByMenuList(String level);
	
	int insertMenuInfo(Map<String,Object> saveMap);
	int updateMenuInfo(Map<String,Object> saveMap);
	int deleteMenuInfo(Map<String,Object> saveMap);
	
	int insertSubMenuInfo(Map<String,Object> saveMap);
	int updateSubMenuInfo(Map<String,Object> saveMap);
	int deleteSubMenuInfo(Map<String,Object> saveMap);
	
}
