/**
 * default data Dataset
 */

/**
 * function : getYearList
 * parameter : X 
 * 설명 : 년도 Array 가져오기
 * returnType :  Array
 */
var getYearList = function(){
	var arr = new Array();
	const today = new Date();
	const currentYear = today.getFullYear();
	var yearCnt = 100;

	for(var i=0; i<yearCnt; i++){
		var year = currentYear - i;
		year = String(year);
		arr.push(year);
	};
	
	return arr;
};

/**
 * function : getMonthList
 * parameter : X 
 * 설명 : 월 Array 가져오기
 * returnType :  Array
 */
var getMonthList = function(){
	var arr = new Array();
	for(var i=1; i<=12; i++){
		var month = String(i);
		if(month < 10){ month = "0" + month; }
		arr.push(month);
	};
	
	return arr;
}

/**
 * function : getYearMonthByDay
 * parameter : Year, Month
 * 설명 : 년도,월별 일자 Array 가져오기
 * returnType :  Array
 */
var getYearMonthByDay = function(Year, Month){
	var arr = new Array();
	var dayCnt = 31; //default
	if((Year != null && Year != "" && Year != undefined) && (Month != null && Month != "" && Month != undefined)){
		dayCnt = new Date(Year, Month, 0).getDate();
	}

	for(var i=1; i<(dayCnt+1); i++){
		var day = String(i);
		if(day < 10){ day = "0" + day; }
		arr.push(day);
	}

	return arr;
}


/**
 * function : getYearMonthByDay
 * parameter : Year, Month
 * 설명 : 년도,월별 일자 Array 가져오기
 * returnType :  Array
 */
var getYearMonthByDay = function(Year, Month){
	var arr = new Array();
	var dayCnt = 31; //default
	if((Year != null && Year != "" && Year != undefined) && (Month != null && Month != "" && Month != undefined)){
		dayCnt = new Date(Year, Month, 0).getDate();
	}

	for(var i=1; i<(dayCnt+1); i++){
		var day = String(i);
		if(day < 10){ day = "0" + day; }
		arr.push(day);
	}

	return arr;
}

/**
 * function : getEmailDomain
 * parameter : 
 * 설명 : 이메일 도메인 가져오기
 * returnType : Array
 */
var getEmailDomain = function(){
	var arr = ["@cyworld.com","@daum.net","@gmail.com","@hanmail.net","@kakao.com","@msn.com","@nate.com","@naver.com","@outlook.com","@paran.com","@tistory.com","@yahoo.com"];
	return arr;
}

/**
 * function : getHpSerialNum
 * parameter : 
 * 설명 : 휴대폰 serial(앞자리) 가져오기
 * returnType : Array
 */
var getHpSerialNum = function(){
	var arr = ["010","011","016","017","018","019"];
	return arr;
}

/**
 * function : bindSelectOption
 * parameter : selectID, optionList
 * 설명 : select태그에 option목록 동적추가
 * returnType : X
 */
var bindSelectOption = function(selectID, optionList, value){
	$("#"+selectID).empty();
	
	var addHtml = '<option value="">' + "선택" + '</option>';
	for(var i=0; i<optionList.length; i++){
		var optionvalue = optionList[i];
		if(optionvalue != value){
			addHtml += '<option value=' + optionvalue + '>' + optionvalue + '</option>';
		} else{
			addHtml += '<option value=' + optionvalue + ' selected>' + optionvalue + '</option>';
		}
	}
	
	$("#"+selectID).append(addHtml);
}
