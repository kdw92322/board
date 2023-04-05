/**
 * sweetAlert Customizing
 * icon : "warning","error","success","info"
 */
var swAlert = function(title, text, icon, button){
	
	var conv_icon = "I";//default
	if(icon.toUpperCase() == "W"){
		conv_icon = "warning";
	}else if(icon.toUpperCase() == "E"){
		conv_icon = "error";
	}else if(icon.toUpperCase() == "S"){
		conv_icon = "success";
	}else if(icon.toUpperCase() == "I"){
		conv_icon = "info";
	}
	
	swal({
		title : title,
		text: text,
		icon: conv_icon,
		button: button,
	});
}
 