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
    var obj = null;
    try {
        if ( this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
            var arr = this.serializeArray();
            if ( arr ) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });            
            }//if ( arr ) {
        }
    }
    catch(e) {alert(e.message);}
    finally  {}
     
    return obj;
};