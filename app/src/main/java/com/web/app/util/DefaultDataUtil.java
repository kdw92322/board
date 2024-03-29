package com.web.app.util;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataUtil {
	public List<String> getEmailDomain(){
		List<String> getEmailDomain = new ArrayList<String>();
		getEmailDomain.add("@cyworld.com");
		getEmailDomain.add("@daum.net");
		getEmailDomain.add("@gmail.com");
		getEmailDomain.add("@hanmail.net");
		getEmailDomain.add("@kakao.com");
		getEmailDomain.add("@msn.com");
		getEmailDomain.add("@nate.com");
		getEmailDomain.add("@naver.com");
		getEmailDomain.add("@outlook.com");
		getEmailDomain.add("@paran.com");
		getEmailDomain.add("@tistory.com");
		getEmailDomain.add("@yahoo.com");
		return getEmailDomain;
	};
	
	public List<String> getHpSerialNum(){
		List<String> getHpSerialNum = new ArrayList<String>();
		getHpSerialNum.add("010");
		getHpSerialNum.add("011");
		getHpSerialNum.add("016");
		getHpSerialNum.add("017");
		getHpSerialNum.add("018");
		getHpSerialNum.add("019");
		return getHpSerialNum;
	};
	
	public String setHPformat(String phoneNum) {
		String format_number = phoneNum.replaceFirst("(\\d{3})(\\d{4})(\\d+)", "$1-$2-$3");
		return format_number;
	}
	
}
