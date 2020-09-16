function parent3FormSub(obj){
	var o = obj.parentNode.parentNode;
	o.submit();
}

function sendEmail(){
	var pName = $("#pName").val();
	var memIdBuyer = $("#memIdBuyer").val();
	 
	console.log("pName: " + pName);
	console.log("memIdBuyer: " + memIdBuyer);
	
	$.ajax({
		type : "POST",
		url : "../../purchase_order/purchaseOrder.do",
		data : {
			"action" : "sendEmail",
			"pName" : pName,
			"memIdBuyer" : memIdBuyer
		},
		// dataType : "json",
		success : function(data) {
			alert("信件已寄出");
		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});
}

$(document).ready(trButtonsBelowUpdateShow);

function trButtonsBelowUpdateShow(){
	
	var poStatus = document.getElementsByClassName("poStatus");
	console.log("poStatus: " + poStatus);
	var poDelivery = document.getElementsByClassName("poDelivery");
	console.log("poDelivery: " + poDelivery);
	
	var trButtonsBelowUpdate = document.getElementsByClassName("trButtonsBelowUpdate");
	
	for(x in trButtonsBelowUpdate){
		
		if(poStatus[x].value == 712){
			console.log("poStatus[x].value: " + poStatus[x].value);
			trButtonsBelowUpdate[x].style.display='';
		}
	}
	
	var cancelOrderSendBtn = document.getElementsByClassName("cancelOrderSendBtn");
	
	for(x in cancelOrderSendBtn){
		if(poStatus[x].value == 10 || (poStatus[x].value == 30 && poDelivery[x].value == 20)){
			console.log("poStatus[x].value: " + poStatus[x].value);
			cancelOrderSendBtn[x].style.display='';
		}
	}
	
	var confirmCancelBtn = document.getElementsByClassName("confirmCancelBtn");
	
	for(x in confirmCancelBtn){
		if(poStatus[x].value == 732){
			console.log("poStatus[x].value: " + poStatus[x].value);
			confirmCancelBtn[x].style.display='';
		}
	}
	var waitForBuyerText = document.getElementsByClassName("waitForBuyerText");
	
	for(x in waitForBuyerText){
		if(poStatus[x].value == 731){
			console.log("poStatus[x].value: " + poStatus[x].value);
			waitForBuyerText[x].style.display='';
		}
	}
	var deliverySendedBtn = document.getElementsByClassName("deliverySendedBtn");
	
	for(x in deliverySendedBtn){
		if(poStatus[x].value == 30){
			console.log("poStatus[x].value: " + poStatus[x].value);
			deliverySendedBtn[x].style.display='';
		}
	}
	
}