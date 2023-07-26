package com.web.app.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DefaultDataUtil {
	/**
	 * function : getYearList
	 * parameter : X 
	 * 설명 : 년도 Array 가져오기
	 * returnType :  Array
	 */
	public List<String> getYearList(){
		List<String> getYearList = new ArrayList<String>();
		
		Date toDay = new Date();
		int currentYear = toDay.getYear() + 1900;
		
		final int yearCnt = 100;
		for(int i=0; i<yearCnt; i++){
			int year = currentYear - i;
			String stryear = String.valueOf(year);
			getYearList.add(stryear);
		};

		return getYearList;
	};
	
	public List<String> getMonthList(){
		List<String> getMonthList = new ArrayList<String>();
		final int maxMonth = 12;
		for(int i=1; i <= maxMonth; i++){
			int month = i;
			String strmonth = "";
			if(month < 10) {
				strmonth = "0"+ String.valueOf(month);
			}else {
				strmonth = String.valueOf(month);
			}
			getMonthList.add(strmonth);
		}
        return getMonthList;
	};
	
	public List<String> getDayList(String year, String month){
		List<String> getDayList = new ArrayList<String>();
		LocalDate currentDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		int dayCnt = currentDate.lengthOfMonth();
		//System.out.println("dayCnt : " + dayCnt);

		for(int i=1; i <= dayCnt; i++){
			int day = i;
			String strday = "";
			if(day < 10) {
				strday = "0"+ String.valueOf(day);
			}else {
				strday = String.valueOf(day);
			}
			getDayList.add(strday);
		}
        return getDayList;
	};
	
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
}
