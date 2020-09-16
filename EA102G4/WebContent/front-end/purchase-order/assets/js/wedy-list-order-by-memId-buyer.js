$(document).ready(showButton);
function showButton(){
	var poStatus = document.getElementsByClassName('poStatus');
	var tdForUpdateOrderBtn = document.getElementsByClassName('tdForUpdateOrderBtn');
	var tdForUpdateBtn = document.getElementsByClassName('tdForUpdateBtn');
	var waitForSellerText = document.getElementsByClassName('waitForSellerText');
	var finishTheOrderBtn = document.getElementsByClassName('finishTheOrderBtn');
	var ConfirmCancelTheOrderBtn = document.getElementsByClassName('ConfirmCancelTheOrderBtn');
	
	
	console.log(poStatus.value);
	
	for(x in poStatus){
		console.log("poStatus[x].value: " + poStatus[x].value);
		if(poStatus[x].value == 10 || poStatus[x].value == 30){
			tdForUpdateOrderBtn[x].style.display = '';
		}
		if(poStatus[x].value == 722){
			tdForUpdateBtn[x].style.display = '';
		}
		if(poStatus[x].value == 712 || poStatus[x].value == 732){
			waitForSellerText[x].style.display = '';
		}
		if(poStatus[x].value == 40){
			finishTheOrderBtn[x].style.display = '';
		}
		if(poStatus[x].value == 731){
			ConfirmCancelTheOrderBtn[x].style.display = '';
		}
		
	}
}