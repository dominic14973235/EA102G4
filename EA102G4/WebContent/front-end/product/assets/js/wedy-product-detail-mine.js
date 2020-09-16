$(document).ready(productTrackingAjax);
$(document).ready(ratingStars);

function ratingStars() { // 評分星星
	var pRating = $(".pRating ").val();
	var star1 = document.getElementById("star1");
	var star2 = document.getElementById("star2");
	var star3 = document.getElementById("star3");
	var star4 = document.getElementById("star4");
	var star5 = document.getElementById("star5");

	var one = "fa fa-star";
	var half = "fa fa-star-half-o";
	var zero = "fa fa-star-o";
	console.log(pRating);
	if (isNaN(pRating)) {
		var ra = document.getElementById("ra");
		ra.innerHTML = "";
		var p = document.createElement('p');
		p.style.fontSize = "16px";
		p.style.color = "#808080";
		p.innerHTML = "暫無評價";
		ra.append(p);
	}

	if (pRating > 0 && pRating < 0.5) {
		star1.className = zero;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 0.5 && pRating < 1) {
		star1.className = half;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 1 && pRating < 1.5) {
		star1.className = one;
		star2.className = zero;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 1.5 && pRating < 2) {
		star1.className = one;
		star2.className = half;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 2 && pRating < 2.5) {
		star1.className = one;
		star2.className = one;
		star3.className = zero;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 2.5 && pRating < 3) {
		star1.className = one;
		star2.className = one;
		star3.className = half;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 3 && pRating < 3.5) {
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = zero;
		star5.className = zero;
	}
	if (pRating >= 3.5 && pRating < 4) {
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = half;
		star5.className = zero;
	}
	if (pRating >= 4 && pRating < 4.5) {
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = one;
		star5.className = zero;
	}
	if (pRating >= 4.5 && pRating < 5) {
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = one;
		star5.className = half;
	}
	if (pRating >= 5) {
		star1.className = one;
		star2.className = one;
		star3.className = one;
		star4.className = one;
		star5.className = one;
	}
}

function productTrackingAjax() {
	var memId = $(".memId").val()
	console.log("var memId = $(.memId).val(): " + memId);
	if (memId == "") {
		$(".insertProductTrackingBtn").on('click', function() {
			window.location.href = "../../EA102G4/front-end/mem/signin.jsp";
		});
	}
	console.log(memId);
	var productNo = $(".productNo").val();
	console.log(productNo);
	$.ajax({
		type : "POST",
		url : "../product_tracking/ProductTracking.do",
		data : {
			"action" : "initProductTracking",
		},
		dataType : "json",
		success : function(data) {
			for (x in data) {
				// console.log(data[x]["memId"] + ":" + data[x]["productNo"]);
				if (memId == data[x]["memId"]
						&& productNo == data[x]["productNo"]) {
					console.log();
					console.log();
					$(".insertProductTrackingBtn").css('display', 'none');
					$(".deleteProductTrackingBtn").css('display', '');
					console.log('已追蹤');
				}

			}
			// console.log(JSON.stringify(data));
		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});

	$(".insertProductTrackingBtn").click(function() {
		$(".insertProductTrackingBtn").css('display', 'none');
		$(".deleteProductTrackingBtn").css('display', '');
		$.ajax({
			type : "POST",
			url : "../product_tracking/ProductTracking.do",
			data : {
				"action" : "insertProductTracking",
				"memId" : $(".memId").val(),
				"productNo" : $(".productNo").val()
			},
			// dataType : "json",
			success : function(data) {
				// alert("已加入追蹤清單");
			},
			error : function() {
				alert("AJAX-class發生錯誤囉!")
			}
		});
	});
	$(".deleteProductTrackingBtn").click(function() {
		$(".deleteProductTrackingBtn").css('display', 'none');
		$(".insertProductTrackingBtn").css('display', '');
		$.ajax({
			type : "POST",
			url : "../product_tracking/ProductTracking.do",
			data : {
				"action" : "deleteProductTracking",
				"memId" : $(".memId").val(),
				"productNo" : $(".productNo").val()
			},
			// dataType : "json",
			success : function(data) {
				// alert("已加入追蹤清單");
			},
			error : function() {
				alert("AJAX-class發生錯誤囉!")
			}
		});
	});

}
