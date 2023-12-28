package com.web.app;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.google.gson.Gson;

/* 
 * MybatisInterceptor
 * Mybatis로 SQL 실행 전 처리되는 서비스
 * ***기능***
 * 1. 실행한 SQL 이력을 저장하는 서비스 추가
 * 
 * 
 */

@Intercepts(@Signature(type=Executor.class, method="query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }))
public class MybatisInterceptor implements Interceptor{
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String username = userDetails.getUsername(); 
		//System.err.println("======> username : " + username);
		
		//QueryId
		String queryID = ((MappedStatement)invocation.getArgs()[0]).getId();
		//System.err.println("======> Query ID : " + queryID);
		
		//Query Parameter
		//Object param = invocation.getArgs()[1];
		Map<String, Object> paramMap = (Map<String, Object>) invocation.getArgs()[1];
		//System.err.println("======> ParamMap : " + paramMap);
		
		//방법 3 - keySet() : key
		String paramString = "";
		if(paramMap != null) {
			for(String key : paramMap.keySet()){
	            String value = String.valueOf(paramMap.get(key));
	            String temp = "key : " + key + ", value : " + value + " ";
	            if(!value.equals("")) {
	            	paramString += temp;
	            }
	        }
		}
        //System.err.println("======> Convert parameter String : " + paramString);
        
		//Query String
		BoundSql boundSql = ((MappedStatement)invocation.getArgs()[0]).getBoundSql(paramMap);
		String queryString = boundSql.getSql();
		
		Gson gson = new Gson();
		List<ParameterMapping> parameterMapping = boundSql.getParameterMappings();
		for(ParameterMapping pm : parameterMapping){
		    //pm.getProperty() >> "param.session.userid"와 같이 나옵니다.
			//param.session.userid 을 .으로 split(쪼개면)합니다. tParam.length=2
			String[] tParam = pm.getProperty().split("\\.");
			Object tValue = paramMap; //오브젝트에 param대입
			
			//쪼개진 tParam[]을 순차적으로 돌면서 tValue를 얻고자 합니다.
			for(int i=0, il=tParam.length; i<il; i++){
			    try{
			        //toJson으로 텍스트형으로 바꾸고, 다시 HashMap 형태로 fromJson 해줬습니다.
			        tValue = gson.fromJson(gson.toJson(((Map<String,Object>) tValue).get(tParam[i])), HashMap.class);
			    }catch(Exception e){
			        tValue = (String) ((Map<String,Object>) tValue).get(tParam[i]); //마지막의 String이 어갈 겁니다.
			    }
			}
			
			//구해진 tValue를 query에다 넣어 줍니다.
			queryString = queryString.replaceFirst("\\?", "'"+ tValue +"'");
		}
		//System.err.println("======> Query String : " + queryString);

		Map<String, Object> saveMap = new HashMap<String, Object>();
		saveMap.put("queryID", queryID);
		saveMap.put("param", paramString);
		saveMap.put("exec_query", queryString);
		saveMap.put("username", username);
		
		insertlog(saveMap);
		
		
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		//Interceptor.super.setProperties(properties);
	}
	
	public void insertlog(Map<String, Object> saveMap) {
		//1. data Setting
		String queryID    = String.valueOf(saveMap.get("queryID")).trim();   
		String param      = String.valueOf(saveMap.get("param")).trim().equals("") ? null : String.valueOf(saveMap.get("param")).trim();
		String exec_query = String.valueOf(saveMap.get("exec_query")).trim();
		InputStream IsExecQuery = new ByteArrayInputStream(exec_query.getBytes());
		String username   = String.valueOf(saveMap.get("username")).trim();
		
		try {
			//로그관련 Service는 제외 하고 로그기록
			if(queryID.indexOf("com.web.app.log.service.LogMapper") < 0) {
				
				/* 2. DB Connection, Execute Insert SQL */
				/* 2-1. DB 관련된 Properties 설정 값 가져오기 */
				Properties properties = new Properties();
				String url = "", dbuser = "", password = "";
				try {
					Reader reader;
					reader = Resources.getResourceAsReader("application.properties");
					properties.load(reader);
					
					url = properties.getProperty("spring.datasource.url");
					dbuser = properties.getProperty("spring.datasource.username");
					password = properties.getProperty("spring.datasource.password");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Connection conn = null;
				Statement stmt = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				int seq = 0; String today = ""; 
				
				/* 2-2. DB Connect */
				conn = DriverManager.getConnection(url, dbuser, password);
				stmt = conn.createStatement();
				
				/* 2-3. key 값 조회 및 Setting */
				rs = stmt.executeQuery("select ifnull(max(a.seq),0)+1 as seq, DATE_FORMAT(sysdate(), '%Y-%m-%d %H:%i:%s') as today from tb_query_exec_history_log a");
				while (rs.next()) {
	                seq = rs.getInt("seq");
	                today = rs.getString("today");
	            }
				
				/* 2-4. sql history table에 insert */
				String insertSql = "insert into tb_query_exec_history_log(seq, queryID, param, exec_query, username, exec_date) values(?, ?, ?, ?, ?, ?)"; 
				psmt = conn.prepareStatement(insertSql);
				psmt.setInt(1, seq);
				psmt.setString(2, queryID);
				psmt.setString(3, param);
				psmt.setBlob(4, IsExecQuery);
				psmt.setString(5, username);
				psmt.setString(6, today);
				
				psmt.executeUpdate();
				
				//3. close
				psmt.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
