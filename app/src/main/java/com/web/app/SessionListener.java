package com.web.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.SqlSession;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.app.user.UserForm;
import com.web.app.user.UserMapper;
import com.web.app.user.UserService;

@WebListener
public class SessionListener implements HttpSessionListener{

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		HttpSession session = httpsessionevent.getSession();
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		UserDetails userdetails = (UserDetails)principal;
		InetAddress inetaddress;
		
		try {
			inetaddress = InetAddress.getLocalHost();
			
			String loginId  = userdetails.getUsername();
			String ip       = inetaddress.getHostAddress();
	        String hostName = inetaddress.getHostName();
	        String logType  = "LOGIN";
	        
	        LOGGER.info("**************************** session Created ****************************");
	        LOGGER.info("**************************** 로그인 ****************************");
	        LOGGER.info("loginId  : " + loginId );
	        LOGGER.info("ip       : " + ip      );
	        LOGGER.info("hostName : " + hostName);
	        LOGGER.info("logType  : " + logType );
	        LOGGER.info("**************************************************************************");
	        
	        Map<String, Object> connUserInfo = new HashMap<String, Object>();
	        connUserInfo.put("loginId" , loginId);
	        connUserInfo.put("ip"      , ip);
	        connUserInfo.put("hostName", hostName);
	        connUserInfo.put("logType" , logType);
	        session.setAttribute("connUserInfo", connUserInfo);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		InetAddress inetaddress;
        String logType  = "LOGOUT";
        
		if(principal != null) {
			UserDetails userdetails = (UserDetails)principal;
			try {
				inetaddress = InetAddress.getLocalHost();
				String ip       = inetaddress.getHostAddress();
		        String hostName = inetaddress.getHostName();
		        
				//접속로그 저장
				String loginId  = userdetails.getUsername();
		        LOGGER.info("**************************** Session Destroyed ****************************");
		        LOGGER.info("**************************** 로그아웃 ****************************");
		        LOGGER.info("loginId  : " + loginId);
		        LOGGER.info("ip       : " + ip);
		        LOGGER.info("hostName : " + hostName);
		        LOGGER.info("logType  : " + logType);
		        LOGGER.info("**************************************************************************");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				inetaddress = InetAddress.getLocalHost();
				String ip       = inetaddress.getHostAddress();
		        String hostName = inetaddress.getHostName();
		        
		        Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String strNow = sdf.format(now);
				LOGGER.info("**************************** Session Destroyed ****************************");
		        LOGGER.info("**************************** 자동세션만료 ****************************");
		        LOGGER.info("세션 만료시간  : " + strNow);
		        LOGGER.info("ip       : " + ip);
		        LOGGER.info("hostName : " + hostName);
		        LOGGER.info("logType  : " + logType);
		        LOGGER.info("**************************************************************************");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
