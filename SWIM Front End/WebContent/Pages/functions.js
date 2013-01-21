// during the registration, enables or disables the button for going on

function goingOn(){
		if ($("#prosegui").attr("disabled") == "disabled")
			$("#prosegui").removeAttr("disabled");
		else 
			$("#prosegui").attr("disabled","disabled");
}

function setCheckToNone (){
		$("#prova").removeAttr("checked");
}