<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.coin.model.*"%>
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
	CoinVO coinVO = (CoinVO)request.getAttribute("coinVO");
%>
<%
// <!-- 	測試環境   不手動登入用 -->
	MemService memSvc = new MemService();
// 	memVO=memSvc.getOneMem("MEM0000001");
// 	session.setAttribute("memVOLogin", memVO);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Zarxio Fitness & Gym HTML Template</title>
<!-- favicon -->
<!-- favicon -->
<link rel=icon
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/img/favicon.png"
	sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/wedy.css">
	
	 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
	
	<style>
.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(assets/img/1244995.jpg);
    background-position: 97px -340px;
    background-size: 90%;
}

</style>
</head>
<body>

	<!-- preloader area start -->
	<div class="preloader" id="preloader">
		<div class="preloader-inner">
			<div class="spinner">
				<div class="dot1"></div>
				<div class="dot2"></div>
			</div>
		</div>
	</div>
	<!-- preloader area end -->

	<!-- search Popup -->
	<div class="body-overlay" id="body-overlay"></div>
	<div class="search-popup" id="search-popup">
		<form action="index.html" class="search-form">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search.....">
			</div>
			<button type="submit" class="submit-btn">
				<i class="fa fa-search"></i>
			</button>
		</form>
	</div>
	<!-- //. search Popup -->

	<%@ include file="../artInclude/navbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Shop Cart List</h1>
			<ul class="page-list margin-bottom-0 margin-top-10">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Product List</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->


	<!-- cart content start -->
	<section class="cart-content-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-title">
						<h2>點數訂單頁面</h2>
					</div>
				</div>

			</div>
           
			<div class="row">
				<div class="col-lg-3">
						<p style="color: black;" >點數訂單編號</p>
				</div>
				<div class="col-lg-3">
						
    					<p style="color: black;" >儲值金額</p>
				</div>
				<div class="col-lg-3">
						<p style="color: black;" >訂單成立日期</p>
				</div>
				<div class="col-lg-3">
					<div class="btn-wrapper">
						<p style="color: black;" >訂單狀態</p>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-3">
						<p style="color: black;" >${coinVO.coin_id}</p>
				</div>
				<div class="col-lg-3">
						
    					<p style="color: black;" >${coinVO.amount}</p>
				</div>
				<div class="col-lg-3">
						<p style="color: black;" >${coinVO.coin_date}</p>
				</div>
				<div class="col-lg-3">
					<div class="btn-wrapper">
						<p style="color: black;" >${coinVO.co_status eq 0? "未付款" : "付款成功"}</p>
					</div>
				</div>
			</div>
			<br>
			<br>
			<div class="row">
			<div class="col-lg-6">
				</div>
				<div class="col-lg-3">
				<a href="${pageContext.request.contextPath}/front-end/coin/listMemCoinOrder.jsp">查看您的所有訂單</a>
				</div>
			</div>
		</div>
	</section>
	
	<!-- cart content end -->

	  <%@ include file="../footerFile/pageFooter.file"%>

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->
	<!-- jquery -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/nice-select.js"></script>
	<!-- counter up -->
	
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/script.js"></script>

	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/wedy.js"></script>
<!-- 	<script> -->
// 	$("#buyButton").click(function() {
// 		$.ajax({
// 			type:"POST",
// 			url:"coin.do",
// 			dataType:"text",
// 			data: {"action":"insert"},
// 			error:function(){
// 				alert("error");
// 			},
// 			success:function(data){
// 				var payview=data
				
// 			}
// 		});
// 	});
<!-- 	</script> -->

</body>
</html>