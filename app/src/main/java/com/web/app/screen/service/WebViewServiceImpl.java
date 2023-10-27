package com.web.app.screen.service;

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
	public List<Map<String, Object>> selectViewList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.selectViewList(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectViewAuthList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return webviewmapper.selectViewAuthList(paramMap);
	}

}
