// funzione per aprire le varie finestre in popup
function open_win(url, name){
	window.open(url,name,'width=400,height=450,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=500,top=150');
	}
	
function open_winForAnswer(url, name){
	window.open(url,name,'width=400,height=100,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=500,top=150');
	}

function open_winProfile(url, name){
	window.open(url,name,'width=400,height=550,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=500,top=150');
	}

function open_winPassword(url, name){
	window.open(url,name,'width=400,height=300,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=500,top=150');
	}

function open_winFeedback(url, name){
	window.open(url,name,'width=300,height=150,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=500,top=150');
	}
	
function open_winReply(url, name){
	window.open(url,name,'width=400,height=360,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=500,top=150');
	}
	
function closeAndAlert(){
	window.alert("Il tuo messaggio è stato inviato correttamente");
	window.close();
	}
	
function closeProfile(){
	window.alert("Profilo aggiornato correttamente");
	window.close();
	}

function closePassword(){
	window.alert("Password aggiornata correttamente");
	window.close();
	}

function closeAndFinish(){
	window.parent.open('../Pages/ReviewConfirmation.html');
	window.close();
}

function messageFriendshipAccepted(){
	window.alert("La richiesta di amicizia è stata confermata");
	window.close();
	}

function messageFriendshipDenied(){
	window.alert("La richiesta di amicizia è stata rifiutata");
	window.close();
	}

