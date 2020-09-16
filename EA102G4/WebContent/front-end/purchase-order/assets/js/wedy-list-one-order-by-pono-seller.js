$(document).ready(showAddress);

var orderStatusForm = null;

function sendComment(){
	var pRating = null;
	if($("#star1").attr('class') == "fa fa-star"){
		pRating = 1;
	}
	if($("#star2").attr('class') == "fa fa-star"){
		pRating = 2;
	}
	if($("#star3").attr('class') == "fa fa-star"){
		pRating = 3;
	}
	if($("#star4").attr('class') == "fa fa-star"){
		pRating = 4;
	}
	if($("#star5").attr('class') == "fa fa-star"){
		pRating = 5;
	}
	
	var pRatingEach = $(".pRatingEach").val();
	var productNo = $(".productNo").val();
	var memIdBuyer = $(".memIdBuyer").val();
	var memIdSeller = $(".memIdSeller").val();
	var poNo = $(".poNo").val();
	var productComment = document.getElementById('productComment').value;
	var changePoStatus = $(".changePoStatus").val();
	changePoStatus = parseInt(changePoStatus,10) + 1;
	console.log(productComment);
	console.log(pRating);
	
	
	if(pRating == null){
		alert("麻煩點擊星星給予評分呦");
		return;
	}else if(productComment == null){
		alert("麻煩留下您寶貴的評論呦");
		return;
	}
	$.ajax({
		type : "POST",
		url : "../product_comment/productComment.do",
		data : {
			"action" : "insertProductComment",
			"productNo" : productNo,
			"memIdBuyer" : memIdBuyer,
			"memIdSeller" : memIdSeller,
			"productComment" : productComment,
			"pRatingEach" : pRatingEach
		},
		success : function(data) {
			alert('已送出評論!');
			$(".ratingBtn").css('display', 'none');
			$(".commentText").css('display', 'none');
			$(".commentBtn").css('display', 'none');
			console.log('insertProductComment-success');
		},
		error : function() {
			alert('AJAX出問題囉');
		}
	});
	$.ajax({
		type : "POST",
		url : "../purchase_order/purchaseOrder.do",
		data : {
			"action" : "getOneForUpdateStatusSeller",
			"poNo" : poNo,
			"changePoStatus" : changePoStatus,
			"listAllOrListOne" : "one"
		},
		success : function(data) {
			$("#poStatus").html("");
			$("#poStatus").append("交易完成");
			console.log('getOneForUpdateStatusBuyer-success');
		},
		error : function() {
			alert('AJAX出問題囉');
		}
	});
//	$.ajax({
//		type : "POST",
//		url : "../product/product.do",
//		data : {
//			"action" : "updateRating",
//			"productNo" : productNo,
//			"pRating" : pRating
//		},
//		success : function(data) {
//			console.log("pRating:　" + pRating)
//			console.log('updateRating-success');
//		},
//		error : function() {
//			alert('AJAX出問題囉');
//		}
//	});
}

function sendEmail(){
	var pName = $("#pName").val();
	var memIdBuyer = $("#memIdBuyer").val();
	 
	console.log("pName: " + pName);
	
	$.ajax({
		type : "POST",
		url : "../purchase_order/purchaseOrder.do",
		data : {
			"action" : "sendEmail",
			"pName" : pName,
			"memIdBuyer" : memIdBuyer
		},
		// dataType : "json",
		success : function(data) {
			console.log("信件已寄出");
		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});
}

function setOrderStatusForm(obj){
	orderStatusForm = obj.parentNode;
	console.log(orderStatusForm);
}

function submitOrderStatusForm(){
	orderStatusForm.submit();
}

function showAddress(){ //如果配送方式為宅配到府，顯示地址
	var poDelivery = $(".poDelivery").val();
	console.log(poDelivery);
	if(poDelivery == 10){
		$(".trForDeliveryAddress").css('display','');
		console.log('poDelivery == 10');
	}
}

$(document).ready(showPayIt);
function showPayIt(){
	var poPayment = $(".poPayment").val();
	var poStatus = $(".poStatus").val();
	if(poPayment != 30 && poStatus == 10){
		$(".trForPayIt").css('display','');
	}
}

$(document).ready(function() {
	var postAccount =  //產生隨機700+16碼的虛擬郵局帳號
		'700-' + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) + 
		getRandom(10) ;
		var text = document.createElement('input');
		text.type='text';
	
	if($(".poPayment").val() == 20){
		$('.confirmModal').click(function(e) {
			e.preventDefault();
			$.confirmModal('匯款帳號:' + postAccount,{
				messageHeader: "以下為系統自動產生的一次性帳號，請盡速前往付款"
			}, function(el) {
				console.log("Ok was clicked!");
				JSalert();
				setTimeout(submitOrderStatusForm,5000);
				
				
				// do delete operation
			});
		});
	}else if($(".poPayment").val() == 10){
		$('.confirmModal').click(function(e) {
			e.preventDefault();
			$.confirmModal('信用卡號:',{
				   messageHeader: "填寫信用卡資料"
			} ,  function(el) {
				console.log("Ok was clicked!");
				
				JSalert();
				setTimeout(submitOrderStatusForm,5000);
				
			});

		});
	}

});

function getRandom(x){
    return Math.floor(Math.random()*x);
};

function parent3FormSub(obj) {
	var o = obj.parentNode.parentNode;
	o.submit();
}

function JSalert(){
	swal("付款成功", "系統將通知賣家出貨", "success");
}

$(document).ready(trButtonsBelowShow);
function trButtonsBelowShow(){
	var poStatus = $(".poStatus").val();
	var poDelivery = $(".poDelivery").val();
	if(poStatus == 10 || (poStatus == 30 && poDelivery == 20)){
		$(".trForButtonsBelow").css('display','');
	}
	if(poStatus == 30){
		$(".deliverySendedBtn").css('display','');
	}
}

$(document).ready(trButtonsBelowUpdateShow);
function trButtonsBelowUpdateShow(){
	var poStatus = $(".poStatus").val()
	console.log("poStatus: " + poStatus);
	if(poStatus == 71){
		$(".trButtonsBelowUpdate").css('display','');
	}
	if(poStatus == 732){
		$(".confirmCancelOrderBtn").css('display','');
	}
	if(poStatus == 731){
		$(".waitForBuyerText").css('display','');
	}
	if (poStatus == 80 || poStatus == 82) {
		$(".ratingBtn").css('display', '');
		$(".commentText").css('display', '');
		$(".commentBtn").css('display', '');
		
	}
	
}
$(document).ready(ratingStar);
function ratingStar() {
	var star1 = document.getElementById("star1");
	var star2 = document.getElementById("star2");
	var star3 = document.getElementById("star3");
	var star4 = document.getElementById("star4");
	var star5 = document.getElementById("star5");

	var one = "fa fa-star";
	var zero = "fa fa-star-o";
	
	star1.onmousedown = function(){
		star1.className = zero;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
		
		star1.className = one;
		
		$(".pRatingEach").val(1);
	}
	star2.onmousedown = function(){
		star1.className = zero;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
		
		star1.className = one;
		star2.className = one;
		
		$(".pRatingEach").val(2);
	}
	star3.onmousedown = function(){
		star1.className = zero;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
		
		star1.className = one;
		star2.className = one;
		star3.className = one;
		
		$(".pRatingEach").val(3);
	}
	star4.onmousedown = function(){
		star1.className = zero;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
		
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = one;
		
		$(".pRatingEach").val(4);
	}
	star5.onmousedown = function(){
		star1.className = zero;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
		
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = one;
		star5.className = one;
		
		$(".pRatingEach").val(5);
	}
}