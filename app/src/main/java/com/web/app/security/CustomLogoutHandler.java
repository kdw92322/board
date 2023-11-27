package com.web.app.security;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.web.app.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
public class CustomLogoutHandler implements LogoutHandler{
	
	@Autowired
    private UserService userservice;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		Map<String,Object> saveMap = new HashMap<String, Object>();
        User user = (User) authentication.getPrincipal();
        saveMap.put("loginId", user.getUsername());
        
		try {
			InetAddress inetaddress = InetAddress.getLocalHost();
			String hostAddress = inetaddress.getHostAddress();
            saveMap.put("ip", hostAddress);
            
            String hostName = inetaddress.getHostName();
            saveMap.put("HostName", hostName);
            userservice.saveUserConnectLog(saveMap);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	
}
