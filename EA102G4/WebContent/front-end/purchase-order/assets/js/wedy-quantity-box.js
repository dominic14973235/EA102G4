$(document).ready(function() {
	

	
//	$('.count').prop('disabled', true);
	$(document).on('click', '.plus', function() {
		$('.count').val(parseInt($('.count').val()) + 1);
		
		if($('.count').val() >= parseInt($("#ps").val(),10)){
			$('.count').val(parseInt($('#ps').val(),10));
		}
		
		$('#total').empty();
		$('#total').append(parseInt($('#count').val(),10) * parseInt(pPrice, 10));
		$('#total').append('元');
		
		console.log($('#count').val());
		
		var p = document.createElement("p");
		p.value = "總金額:" + parseInt($('#count').val(),10) * parseInt(pPrice, 10) + "元";
		console.log(p.value)
		$('#tb').empty();
		$('#tb').append(p.value);
		
	});
	$(document).on('click', '.minus', function() {
		$('.count').val(parseInt($('.count').val()) - 1);
		if ($('.count').val() <= 0) {
			$('.count').val(1);
		}
		$('#total').empty();
		$('#total').append(parseInt($('#count').val(),10) * parseInt(pPrice, 10));
		$('#total').append('元');
		console.log($('#count').val());
		
		var p = document.createElement("p");
		p.value = "總金額:" + parseInt($('#count').val(),10) * parseInt(pPrice, 10) + "元";
		console.log(p.value)
		$('#tb').empty();
		$('#tb').append(p.value);
		
		
	});
	$('.count').on('change',function(){
		if($('.count').val() < 1){
			$('.count').val(1);
			alert('購買數量請輸入0以上的數字')
		}
		
		if($('.count').val() >= parseInt($("#ps").val(),10)){
			$('.count').val(parseInt($('#ps').val(),10));
		}
		
		$('#total').empty();
		$('#total').append(parseInt($('#count').val(),10) * parseInt(pPrice, 10));
		$('#total').append('元');
		
		var p = document.createElement("p");
		p.value = "總金額:" + parseInt($('#count').val(),10) * parseInt(pPrice, 10) + "元";
		console.log(p.value)
		$('#tb').empty();
		$('#tb').append(p.value);
		
		

	})
	
	
	$(".plus").on('click',function(){
		if($('.count').val() >= parseInt($("#ps").val(),10)){
			$('.count').val(parseInt($('#ps').val(),10));
		}
	});
	
	console.log($('#pPrice').text());
	var pPrice = $('#pPrice').text();
	console.log(parseInt(pPrice, 10));
	
	console.log(parseInt($('#count').val(),10) * parseInt(pPrice, 10));
	$('#total').append(parseInt($('#count').val(),10) * parseInt(pPrice, 10));
	$('#total').append('元');
	
	var p = document.createElement("p");
	p.value = "總金額:" + parseInt($('#count').val(),10) * parseInt(pPrice, 10) + "元";
	console.log(p.value)
	$('#tb').append(p.value);
});