package com.web.app.webview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.file.service.FileMapper;

@Service
public class WebViewServiceImpl implements WebViewService{

	@Autowired
	private WebViewMapper webviewmapper; 
	
	@Override
	public List<Map<String, Object>> selectMenuList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.selectMenuList(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> selectViewList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.selectViewList(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> selectAuthList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.selectAuthList(paramMap);
	}
	
	@Override
	public int insertMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.insertMenuInfo(saveMap);
	}

	@Override
	public int updateMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.updateMenuInfo(saveMap);
	}

	@Override
	public int deleteMenuInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.deleteMenuInfo(saveMap);
	}
	
	@Override
	public int insertViewInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.insertViewInfo(saveMap);
	}

	@Override
	public int updateViewInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.updateViewInfo(saveMap);
	}

	@Override
	public int deleteViewInfo(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.deleteViewInfo(saveMap);
	}
}
