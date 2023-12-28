package com.web.app.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonMapper commonmapper;
	
	@Override
	public List<Map<String, Object>> topMenuList() throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.topMenuList();
	}

	@Override
	public List<Map<String, Object>> topViewList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.topViewList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectCodeMstList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.selectCodeMstList(param);
	}
	
	@Override
	public int insertCodeMst(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.insertCodeMst(saveMap);
	}

	@Override
	public int updateCodeMst(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.updateCodeMst(saveMap);
	}

	@Override
	public int deleteCodeMst(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.deleteCodeMst(saveMap);
	}

	@Override
	public List<Map<String, Object>> selectCodeDtlList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.selectCodeDtlList(param);
	}

}
