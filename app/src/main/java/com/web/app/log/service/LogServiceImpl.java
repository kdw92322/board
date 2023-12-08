package com.web.app.log.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class LogServiceImpl implements LogService{
	@Autowired
	private LogMapper logmapper;

	/** 1. System Log 처리 Process **/
	/* 1-1. 로그인시 User 접속로그 저장 */
	@Override
	public int saveUserLog(Map<String, Object> saveMap) {
		// TODO Auto-generated method stub
		return logmapper.saveUserLog(saveMap);
	}
	
	/* 1-2. Service 실행마다 SQL 실행이력 저장 */
	@Override
	public int saveExecQueryLog(Map<String, Object> saveMap) {
		// TODO Auto-generated method stub
		return logmapper.saveExecQueryLog(saveMap);
	}
	/*******************************/
	
	/** 2. Service Use Log 처리 Process **/
	/* 2-1. Service Use Master List 조회 */
	@Override
	public List<Map<String, Object>> selectMstServiceLogList(Map<String, Object> paramMap) {
		List<Map<String, Object>> selectMSTLogList = logmapper.selectMstServiceLogList(paramMap);
		return selectMSTLogList;
	}
	
	/* 2-2. Service Use Detail Info 조회 */
	@Override
	public Map<String, Object> selectDtlServiceLogInfo(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Map<String, Object> selectDtlServiceLogInfo = logmapper.selectDtlServiceLogInfo(paramMap);
		if(selectDtlServiceLogInfo.get("path") != null) {
			String path = String.valueOf(selectDtlServiceLogInfo.get("path"));
			String Id = path.substring(path.lastIndexOf(".") + 1, path.length());
			selectDtlServiceLogInfo.put("ID", Id);
		}
		
		if(selectDtlServiceLogInfo.get("execQuery") != null) {
			byte[] temp = (byte[])selectDtlServiceLogInfo.get("execQuery");
			String strRevTxt = new String(temp);
			selectDtlServiceLogInfo.put("txtExecQuery", strRevTxt);
		}
		
		return selectDtlServiceLogInfo;
	}
	
	/** 3. connect Log 처리 Process **/
	/* 3-1. connect Use Master List 조회 */
	@Override
	public List<Map<String, Object>> selectMstConnectLogList(Map<String, Object> paramMap) {
		List<Map<String, Object>> selectMSTLogList = logmapper.selectMstConnectLogList(paramMap);
		return selectMSTLogList;
	}
	
	/* 3-2. connect Use Detail Info 조회 */
	@Override
	public List<Map<String, Object>> selectDtlConnectLogList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> selectDtlConnectLogList = logmapper.selectDtlConnectLogList(paramMap);
		return selectDtlConnectLogList;
	}
}
