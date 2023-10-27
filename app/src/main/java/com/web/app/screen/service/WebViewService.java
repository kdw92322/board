package com.web.app.screen.service;

import java.util.List;
import java.util.Map;

public interface WebViewService {
	public List<Map<String,Object>> selectViewList(Map<String,Object> paramMap) throws Exception;
	public List<Map<String,Object>> selectViewAuthList(Map<String,Object> paramMap) throws Exception;
}
