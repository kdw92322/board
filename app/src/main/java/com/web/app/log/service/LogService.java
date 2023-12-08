package com.web.app.log.service;

import java.util.List;
import java.util.Map;

public interface LogService {
	public int saveUserLog(Map<String,Object> saveMap);
	public int saveExecQueryLog(Map<String,Object> saveMap);
	public List<Map<String, Object>> selectMstServiceLogList(Map<String, Object> paramMap);
	public Map<String, Object> selectDtlServiceLogInfo(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectMstConnectLogList(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectDtlConnectLogList(Map<String, Object> paramMap);
}
