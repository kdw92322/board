package com.web.app.security;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.web.app.user.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) 
public class SecurityConfig {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
        	.requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/js/**")).permitAll()
        	.antMatchers("/user/userInfo").authenticated()
	        .and()
	            .formLogin()
	            .loginPage("/user/login")
	            .defaultSuccessUrl("/")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .loginProcessingUrl("/user/loginProcess")
	            .successHandler(successHandler())
	            .failureHandler( // 로그인 실패 후 핸들러
	                 new AuthenticationFailureHandler() { // 익명 객체 사용
	                     @Override
	                     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	                         System.out.println("exception : " + exception.getMessage());
	                         response.sendRedirect("/user/login?error");
	                     }
	            })
	            .permitAll()
	        .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler((request, response, authentication) -> { 
                    HttpSession session = request.getSession();
                    
                    Map<String, Object> connUserInfo = (Map<String, Object>) session.getAttribute("connUserInfo");
                    connUserInfo.remove("seq");
                    connUserInfo.replace("logType", "LOGOUT");
                    sqlSession.insert("com.web.app.user.UserMapper.saveUserLog", connUserInfo);
                    
                     // 사실 굳이 세션 무효화하지 않아도 됨.(LogoutFilter가 내부적으로 처리)
                    if (session != null) {
                        session.invalidate();
                    }
                    
                })
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logoutProcess")
                .invalidateHttpSession(true)
            .and()
	        	.csrf().disable() // jwt token을 사용하는 방식이기 때문에 csrf를 disable합니다.
	        	.anonymous().disable()
	        ;
        
        return http.build();
    }
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CustomSuccessHandler successHandler() {
	    return new CustomSuccessHandler();
	}
}
