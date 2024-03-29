package com.web.app.exception.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlingController implements ErrorController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 에러 페이지 정의
	private final String ERROR_404_PAGE_PATH = "/error/404";
	private final String ERROR_500_PAGE_PATH = "/error/500";
	private final String ERROR_ETC_PAGE_PATH = "/error/etcError";
	
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {
		// return 할 page
		String rtnPage = "";
		
		String path = request.getServletPath();
		System.out.println("path : " + path);
		
		// 에러 코드를 획득한다.
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			System.out.println("status : " + status.toString());
		
			// 에러 코드에 대한 상태 정보
			HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

			// HttpStatus와 비교해 페이지 분기를 나누기 위한 변수
			int statusCode = Integer.valueOf(status.toString());
			// 로그로 상태값을 기록 및 출력
			logger.info("httpStatus : " + statusCode);
			
			// 404 error
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				logger.info("404 에러");
				// 에러 페이지에 표시할 정보
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", httpStatus.getReasonPhrase());
				model.addAttribute("timestamp", new Date());
				rtnPage = ERROR_404_PAGE_PATH;
			}else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { // 500 error
				// 서버에 대한 에러이기 때문에 사용자에게 정보를 제공하지 않는다.
				rtnPage = ERROR_500_PAGE_PATH;
			}else {
				// 권한에 대한 에러이기 때문에 사용자에게 정보를 제공하지 않는다.
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", "접근 권한이 없습니다.");
				model.addAttribute("timestamp", new Date());
				rtnPage = ERROR_ETC_PAGE_PATH;
			}
		}
		return rtnPage;
	}
}
