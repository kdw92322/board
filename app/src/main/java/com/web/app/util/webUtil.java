package com.web.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.ibatis.io.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;

public class webUtil {
	
	/** URL Connection SSL ignore **/
	public static TrustManager[] createTrustManagers() {
		TrustManager[] trustAllCerts = new TrustManager[]{ new X509TrustManager() {
	        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) { }
	        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) { }
	        public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new java.security.cert.X509Certificate[]{}; }
	    }};	    
	    return trustAllCerts;
	}
	
	/**
	 * @author : dawi Kim
	 * @param 
	 * @Date : 2023. 08. 11
	 * @Method Name : getAuthKey
	 * @Desc : ID, PW정보로 BASE64 인코딩으로 API 인증키 생성
	 */
	private String getAuthKey() {
		String authKey = null;
		
		//URL 인증키 생성
		try {
			//Properties 파일에서 ID, PW 값을 가져와 BASE64
			Properties prop = new Properties();
			Reader reader = Resources.getResourceAsReader("smartsuite/properties/global.properties"); 
			prop.load(reader);
			
			String id = prop.getProperty("sr_data_integration.id");
			String pw = prop.getProperty("sr_data_integration.pw");
			String auth = id + ":" + pw; 
			/*
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes()); 
			authKey = "Basic " + new String(encodedAuth); 
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return authKey;
	}
	
	/**
	 * @author : dawi Kim
	 * @param 
	 * @throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException, IOException 
	 * @Date : 2023. 08. 11
	 * @Method Name : saveRitmAndSctask
	 * @Desc : RitmAndSctask정보를 가져오는 URL을 호출하여 테이블에 저장
	 */
	@SuppressWarnings("unchecked")
	private void saveRitmAndSctask() throws NoSuchAlgorithmException, KeyManagementException, IOException {
		String ritmandsctaskurl = "https://doosanuat.service-now.com/api/doorp/enerbility_tickets/sr";
		ObjectMapper objectMapper = new ObjectMapper();
		BufferedReader br = null;
		
		//SSL Ignore
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, createTrustManagers(), new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		//Connect
		/*
		URL url = new URL("https", "doosanuat.service-now.com", 443, ritmandsctaskurl, new sun.net.www.protocol.https.Handler());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET"); //get방식
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Authorization", getAuthKey());
		conn.setRequestProperty("Cache-Control", "no-cache");
		conn.setRequestProperty("CONTENT-TYPE", "application/json");
		*/
		
		//result
		/*
		int responseCode = conn.getResponseCode();
		if(responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String result = "";
			String line;
			while((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			
			Map<String, Object> resultInfo = objectMapper.readValue(result, Map.class);
			List<Map<String, Object>> ritmandsctaskList = (List<Map<String, Object>>) resultInfo.get("result");
			
			//데이터 공백 NULL로 처리
			for(int i=0; i<ritmandsctaskList.size(); i++) {
				Map<String, Object> rowMap = ritmandsctaskList.get(i);
				Iterator<String> keys = rowMap.keySet().iterator();
				while(keys.hasNext()) {
					String key = keys.next();
					String value = String.valueOf(rowMap.get(key));
					value = value.trim();
					if(value.equals("") || value.equals("null")) { value = null; };
					ritmandsctaskList.get(i).put(key, value);
				}
			}
			
			if(ritmandsctaskList.size() > 0) {

				//sqlSession.delete("erplinkint.deleteRitmAndSctask", null);
				//sqlSession.insert("erplinkint.saveRitmAndSctask", ritmandsctaskList);
			}
		}*/
		
	}
}
