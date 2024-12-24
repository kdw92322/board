package com.web.app.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		List<String> methodList = new ArrayList<String>();
		methodList.add("GET");
		methodList.add("POST");
		methodList.add("PUT");
		methodList.add("DELETE");
		
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("Content-Type,x-requested-with,Authorization,Axxess-Control-Allow-Origin");
		config.setAllowedMethods(methodList);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	} 
}
