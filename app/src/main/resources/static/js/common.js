/*
	Modal 생성
	result : html 호출
*/
var openModalPop = function(modalId, url, param){
    $.ajax({
		type:'post',	
	    dataType:"html", //서버에서 html타입을 반환받는다.
	    url: url,
	    contentType: "application/json; charset=utf-8",
	    data : JSON.stringify(param),
	    success : function(resultHtml) {
	        $(modalId).find('.modal-content').html(resultHtml);
        	$(modalId).modal('show');
	    },
	});    
};

/*
	form 데이터를 Object로 변환
	name속성이 존재하는 tag의 값만 가져옴
*/
jQuery.fn.serializeObject = function() {
    var obj = new Object;
    try {
        if ( this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
            var arr = this.serializeArray();
            if ( arr ) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });            
            }
        }
    }catch(e) {
		alert(e.message);
	}finally  {
		
	}
     
    return obj;
};

var initDateTimePicker = function(id, formatType, optionObj){
	//defaultOption
	var defaultOption = {
		lang   : "kr",           
		format : 'Y-m-d H:i',
		step   : '30',
		weeks  : true,          
		todayButton : true    
	};
	
	if(formatType == "D"){
		defaultOption.format = 'Y-m-d';
		defaultOption.timepicker = false;
	}else if(formatType == "T"){
		defaultOption.format = 'H:i';
		defaultOption.datepicker = false;
	};
	
	if(optionObj != null && optionObj != undefined){
		var keys = Object.keys(optionObj);
		if(keys.length == 0){ optionObj = defaultOption; }	
	}else {
		optionObj = defaultOption;
	}
	
	$("#"+id).datetimepicker(optionObj);	
}

/*
    문자열의 모든 searchStr에 대해 replaceStr로 대체
*/
var replaceAll = function(str, searchStr, replaceStr) {
   return str.split(searchStr).join(replaceStr);
}

/*
    Date to String(format : yyyy-mm-dd hh:mi:ss)
*/
var DateToStringFormat = function(date){
	var rtnStrDate = "";
	if(typeof date == 'object'){
		date.setHours(date.getHours() + 9); //한국 시간대로 setting
		rtnStrDate = date.toISOString().substring(0, 19);
	}
	return rtnStrDate;
}

/*
    method : callService
    parameter : (type, url, async, dataType, data, isReturnValue, timeout)
    desc : Service 호출 function
*/
var callService = function(type, url, async, dataType, data, isReturnValue, timeout){
	//Return Object
	var returnObject = new Object();
	
	/* type : GET, POST, PUT, DELETE */
	if(type == null && url == "" && url == undefined){
		type = type.toUpperCase();
		console.log("type is null, parameter order : 1");
		return;
	}
	
	/* Service URL */
	if(url == null && url == "" && url == undefined){
		console.log("url is null, parameter order : 2");
		return;
	} 
	
	/* 동기/비동기 여부(기본 비동기) */
	if(async == null && async == "" && async == undefined){
		console.log("async is null(default : true), parameter order : 3");
		async = true;
	}
	
	/* 서버에서 return 되는 DataType : text, html, xml, json, jsonp, script */
	if(dataType == null && dataType == "" && dataType == undefined){
		console.log("dataType is null(default : text), parameter order : 4");
		dataType = "text";
	}
	
	/* 서버에 전송할 데이터 */
	if(data == null && data == "" && data == undefined){
		console.log("dataType is null(default : {}), parameter order : 5");
		data = new Object();
	}
	
	/* Call Service 후 Return 여부 */
	if(isReturnValue == null && isReturnValue == "" && isReturnValue == undefined){
		console.log("isReturnValue is null(default : true), parameter order : 6");
		isReturnValue = false;//default
	}
	
	/* 타임아웃 설정 */
	if(timeout == null && timeout == "" && timeout == undefined){
		console.log("dataType is null(default : 0), parameter order : 7");
		timeout = 0;//default
	}
	
	let ContentType = 'application/x-www-form-urlencoded; charset=utf-8';
	if(dataType == "json") {
		ContentType = 'application/json; charset=utf-8';
	}
	
	$.ajax({
		type: type,
		url: url,
		async: async,
		dataType: dataType,
		data: data,
		contentType: ContentType,
		timeout : timeout,
		beforeSend:function(){
			$('.layout_loader').show(); // loading bar show
	    },
	    complete:function(){
			setTimeout(() => {
			    $('.layout_loader').hide(); // loading bar hide
			}, 1000);
	    },
	})
	.done(function(data, textStatus, xhr){
		if(isReturnValue){
			returnObject = {
				"value" : data,
				"Status" : textStatus,
				"xhr" : xhr
			};
		}
	})
	.fail(function(xhr, textStatus, errorThrown){
		if(isReturnValue){
			returnObject = {
				"xhr" : xhr,
				"Status" : textStatus,
				"Thrown" : errorThrown,
			};
		}
	});
	
	return returnObject;
}


