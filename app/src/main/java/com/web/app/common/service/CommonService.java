package com.web.app.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {
	List<Map<String, Object>> topMenuList() throws Exception;
	List<Map<String, Object>> topViewList(Map<String, Object> param) throws Exception;
	List<Map<String, Object>> selectCodeMstList(Map<String, Object> param) throws Exception;
	int insertCodeMst(Map<String, Object> saveMap) throws Exception;
	int updateCodeMst(Map<String, Object> saveMap) throws Exception;
	int deleteCodeMst(Map<String, Object> saveMap) throws Exception;
	
	List<Map<String, Object>> selectCodeDtlList(Map<String, Object> param) throws Exception;
}
