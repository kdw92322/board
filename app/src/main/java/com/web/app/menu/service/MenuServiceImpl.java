package com.web.app.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menumapper; 
	
	@Override
	public List<Map<String, Object>> selectMenuList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.selectMenuList(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> selectSideMenuList(Map<String, Object> paramMap) throws Exception {
		
		int maxLevel = menumapper.getMenuMaxlevel();
		
		List<Map<String,Object>> menuList = menumapper.selectSideMenuList();
		
		/* Data Format
		 * ex)
		 *  element = {
		 *     menucode : a,
		 *     menuname : "abcd",
		 *     _attributes : {expanded : true}
		 * 	   _children : ArrayList( = element)
		 *  }
		 */
		
		//최상위 메뉴 List
		List<Map<String,Object>> sideMenuTopList = menuList.stream()
				.filter(e -> String.valueOf(e.get("menulevel")).equals("1"))
				.collect(Collectors.toList());
		
		//현재 level에 해당하는 하위메뉴 List
		List<Map<String,Object>> sideMenuBottomList = menuList.stream()
				.filter(e -> !String.valueOf(e.get("menulevel")).equals("1"))
				.collect(Collectors.toList());
		
		//상위메뉴에 해당하는 하위메뉴 List Setting
		for(Map<String,Object> sideTopMenu : sideMenuTopList) {
			String topMenuCode = String.valueOf(sideTopMenu.get("menucode"));
			
			List<Map<String,Object>> childList = sideMenuBottomList.stream()
				.filter(e -> String.valueOf(e.get("parentcode")).equals(topMenuCode))
				.collect(Collectors.toList());

			if(childList.size() != 0) {
				Map<String, Object> attrObj = new HashMap<>();
				attrObj.put("expanded", true);
				
				sideTopMenu.put("_children", childList);
				sideTopMenu.put("_attributes", attrObj);
			}
		}
		return sideMenuTopList;
	}

	
	@Override
	public int insertMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.insertMenuInfo(saveMap);
	}

	@Override
	public int updateMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.updateMenuInfo(saveMap);
	}

	@Override
	public int deleteMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.deleteMenuInfo(saveMap);
	}
	
	@Override
	public int insertSubMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.insertSubMenuInfo(saveMap);
	}

	@Override
	public int updateSubMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.updateSubMenuInfo(saveMap);
	}

	@Override
	public int deleteSubMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.deleteSubMenuInfo(saveMap);
	}

}
