$(document).ready(ratingStars);
$(document).ready(starBar);


function starBar(){
	let p5 = $(".pRating5StarAvg").val();
	let p5p = parseInt(p5,10);
	let p4 = $(".pRating4StarAvg").val();
	let p4p = parseInt(p4,10);
	let p3 = $(".pRating3StarAvg").val();
	let p3p = parseInt(p3,10);
	let p2 = $(".pRating2StarAvg").val();
	let p2p = parseInt(p2,10);
	let p1 = $(".pRating1StarAvg").val();
	let p1p = parseInt(p1,10);
	
	if(isNaN(p5p)) p5p = 0;
	if(isNaN(p4p)) p4p = 0;
	if(isNaN(p3p)) p3p = 0;
	if(isNaN(p2p)) p2p = 0;
	if(isNaN(p1p)) p1p = 0;
	$(".wedy-starBar1").css('width',p1p + '%');
	$(".wedy-starBar2").css('width',p2p + '%');
	$(".wedy-starBar3").css('width',p3p + '%');
	$(".wedy-starBar4").css('width',p4p + '%');
	$(".wedy-starBar5").css('width',p5p + '%');
	
	$(".wedy-starBarText5").text("");
	$(".wedy-starBarText5").append(p5p + "%");
	
	$(".wedy-starBarText4").text("");
	$(".wedy-starBarText4").append(p4p + "%");
	
	$(".wedy-starBarText3").text("");
	$(".wedy-starBarText3").append(p3p + "%");
	
	$(".wedy-starBarText2").text("");
	$(".wedy-starBarText2").append(p2p + "%");
	
	$(".wedy-starBarText1").text("");
	$(".wedy-starBarText1").append(p1p + "%");
	
	
}

function ratingStars() { // 評分星星
	var pRating = $(".pRating ").val();
	var star1 = document.getElementById("wedy-star1");
	var star2 = document.getElementById("wedy-star2");
	var star3 = document.getElementById("wedy-star3");
	var star4 = document.getElementById("wedy-star4");
	var star5 = document.getElementById("wedy-star5");

	var one = "fa fa-star";
	var half = "fa fa-star-half-o";
	var zero = "fa fa-star-o";
	console.log("pRating:　" + pRating);

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