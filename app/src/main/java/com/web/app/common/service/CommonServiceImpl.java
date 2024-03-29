package com.web.app.common.service;

import java.util.HashMap;
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
	
	@Override
	public int insertCodeDtl(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.insertCodeDtl(saveMap);
	}

	@Override
	public int updateCodeDtl(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.updateCodeDtl(saveMap);
	}

	@Override
	public int deleteCodeDtl(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.deleteCodeDtl(saveMap);
	}

	@Override
	public String getCodeName(String mstCd, String dtlCd) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mstCd", mstCd);
		paramMap.put("dtlCd", dtlCd);
		
		
		List<Map<String, Object>> codeInfoList = commonmapper.selectCodeDtlList(paramMap);
		Map<String, Object> codeInfo = codeInfoList.get(0);
		String dtlNm = String.valueOf(codeInfo.get("dtlNm"));
		return dtlNm;
	}

	@Override
	public List<Map<String, Object>> indexFileViewerList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return commonmapper.indexFileViewerList(paramMap);
	}
	
}
