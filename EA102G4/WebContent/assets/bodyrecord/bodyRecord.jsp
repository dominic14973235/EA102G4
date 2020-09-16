<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.bodyrecord.model.*"%>



<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	String changedBodyNo = (String)request.getAttribute("body_no");
	pageContext.setAttribute("changedBodyNo", changedBodyNo);
	
	if (memVOLogin == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	} else {
		BodyRecordService bodyRecordSvc = new BodyRecordService();
		List<BodyRecordVO> list = bodyRecordSvc.getOneBodyRecordByMemId(memVOLogin.getMem_id());
		pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Zarxio Fitness & Gym HTML Template</title>
<!-- favicon -->
<link rel=icon href=assets/img/favicon.png sizes="20x20"
	type="image/png">
<!-- animate -->
<link rel="stylesheet" href="assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet" href="assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet" href="assets/css/slick.css" />
<link rel="stylesheet" href="assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet" href="assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet" href="assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet" href="assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet" href="assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet" href="assets/css/responsive.css">

<style>
#hi_mem {
	font-family: Microsoft JhengHei;
}

@media screen and (max-width:1400px) {
	#hi_mem {
		display: none;
	}
}

#PageSelecter {
	padding: 5px 10px;
	margin-left: 50px;
	font-family: Microsoft JhengHei;
}

#plus-button-div{
width:80%;
margin:20px auto 0 auto;
}

p.bodyinfo {
	margin: 0 0;
}
img.bodyphoto{
	width:100%;
	height:100%;
}

.empty-div{
	width:100%;
	height:250px;
	text-align:center;
	
}
.empty-span{
	font-size:100px;
	color:#ccc;
	
}

.changecolor{
		
		animation: mymove 2s alternate;
		
	
	}
	@keyframes mymove{

		0%{
     		background-color:#fff;
		}
		50%{
      		background-color:#cccfff;
		}
		100%{
      		background-color:#fff;
		}
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

	<!-- navbar start -->
	<div class="zarxio-navbar">
		<nav class="navbar navbar-area navbar-expand-lg black-bg nav-style-02">
			<div class="container nav-container">
				<div class="responsive-mobile-menu">
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#billatrail_main_menu" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span> <span class="bar1"></span>
						<span class="bar2"></span> <span class="bar3"></span>
					</button>
				</div>
				 <div class="logo">
					<a href="<%=request.getContextPath() %>/front-end/index.jsp"> <img src="assets/img/logo.png" alt="logo"></a>
				</div>
				<div class="collapse navbar-collapse" id="billatrail_main_menu">
					<ul class="navbar-nav menu-open">
						<li class="menu-item-has-children"><a href="#">Home</a>
							<ul class="sub-menu">
								<li><a href="index.html">Home 1</a></li>
								<li><a href="index-2.html">Home 2</a></li>
								<li><a href="index-3.html">Home 3</a></li>
							</ul></li>

						<li class="hidden-md"><a href="about.html">About</a></li>
						<li class="menu-item-has-children"><a href="#">Classes</a>
							<ul class="sub-menu">
								<li><a href="classes.html">Class</a></li>
								<li><a href="class-details.html">Class Details</a></li>
							</ul></li>
						<li class="menu-item-has-children"><a
							href="#">Trainer</a>
							<ul class="sub-menu">
								<li><a href="trainer.html">Trainer</a></li>
								<li><a href="trainer-details.html">Trainer Details</a></li>
							</ul></li>
						<li class="menu-item-has-children"><a href="#">Shop</a>
							<ul class="sub-menu">
								<li><a href="shop.html">Shop</a></li>
								<li><a href="product-details.html">Product Details</a></li>
								<li><a href="shopping-cart.html">Shopping Cart</a></li>
								<li><a href="payment.html">Payment</a></li>
								<li><a href="checkout.html">Checkout</a></li>
							</ul></li>
						<li class="menu-item-has-children"><a href="#">Blog</a>
							<ul class="sub-menu">
								<li><a href="blog.html">Blog</a></li>
								<li><a href="blog-details.html">Blog Details</a></li>
							</ul></li>
						<li class="menu-item-has-children current-menu-item">
                            <a href="#">Member</a>
                            <ul class="sub-menu">
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Personal Information</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/bodyrecord/bodyRecord.jsp">MyBodyData</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp">MySportData</a></li>
                                <li><a href="#">FoodRecord</a></li>
                                <%if (memVOLogin != null) {%>
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/changeMyPassword.jsp">ChangeMyPassword</a></li>
                                <%}%>
                            </ul>
                        </li>
						<li><a
							href="<%=request.getContextPath()%>/front-end/mem/mem.do?action=logout">Logout</a>
						</li>
					</ul>
				</div>
				<a id="hi_mem"
					href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%></a>
				<div class="nav-right-part">
					<ul>
						<li class="search" id="search"><a href="#"><i
								class="fa fa-search"></i></a></li>
						<li>
							<div class="hamberger-area d-none d-lg-block">
								<div class="menu-toggle hamburger hamburger--squeeze is-active">
									<div class="hamburger-box">
										<div class="hamburger-inner"></div>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<!-- navbar end -->

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/service.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">My BodyData</h1>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<div id="plus-button-div">
		<button type="button" class="btn btn-secondary btn-lg btn-block" id="plus-button">新增</button>
	</div>

	
	<!--  trainer start -->
	<section class="trainer-area bg-none margin-bottom-100 margin-top-100">
		<%@ include file="page1.file"%>
		<div class="container">
			<div class="row class-slider-style-2">
				<c:choose>
					<c:when test="${list.size() == 0 }">
						<div class="empty-div">
							<span class="empty-span">尚無資料</span>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="bodyRecordVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<c:choose>
						<c:when test="${bodyRecordVO.body_no == changedBodyNo }">
							<div class="col-md-6 changecolor">
						</c:when>
						<c:otherwise>
							<div class="col-md-6">
						</c:otherwise>
					</c:choose>
						<div class="row border-custom">
							<div class="col-md-5">
								
									<img src="<%=request.getContextPath()%>/front-end/bodyrecord/bodyphoto.do?body_no=${bodyRecordVO.body_no}" class="bodyphoto">
								
							</div>
							<div class="col-md-7 d-flex align-items-center">
								<div class="content">

									<p class="pd-3">日期 : ${bodyRecordVO.body_date}</p>
									<p class="pd-3">身高 : ${bodyRecordVO.mem_height}</p>
									<p class="pd-3">體重 : ${bodyRecordVO.mem_weight}</p>
									<p class="pd-3">BMI : ${bodyRecordVO.mem_bmi}</p>
									<p class="pd-3">BMR : ${bodyRecordVO.mem_bmr}</p>
									<p class="pd-3">
										運動頻率 :
										<c:choose>
											<c:when test="${bodyRecordVO.frequency == '0'}">
       										久坐(沒有運動習慣)
    									</c:when>
											<c:when test="${bodyRecordVO.frequency == '1'}">
       										輕度(運動1-2天/週)
										</c:when>
											<c:when test="${bodyRecordVO.frequency == '2'}">
        									中度(運動3-5天/週)
										</c:when>
											<c:when test="${bodyRecordVO.frequency == '3'}">
        									高度(運動6-7天/週)
										</c:when>
											<c:otherwise>
        									極高度(運動員等級，每天運動2次)
										</c:otherwise>
										</c:choose>
									</p>
									<p class="pd-3">TDEE : ${bodyRecordVO.mem_tdee}</p>
									<div class="btn-wrapper">
										<a href='<%=request.getContextPath() %>/front-end/bodyrecord/updateBodyRecord.jsp?body_no=${bodyRecordVO.body_no}&whichPage=<%=whichPage%>'
											class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">修改</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
					</c:otherwise>
				</c:choose>
				
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<div
						class="pagination-area d-flex justify-content-center margin-top-50">
						<ul>
							<li id="before"><span class="next page-bumber"><i
									class="fa fa-angle-left"></i> Prev</span></li>

							<li><span class="page-bumber current"><%=whichPage%></span></li>

							<li id="after"><span class="next page-bumber">Next <i
									class="fa fa-angle-right"></i></span></li>
						</ul>
						<%
							if (pageNumber > 1) {
						%>
						<div class="dropdown">
						<a class="btn btn-secondary dropdown-toggle" type="button" id="PageSelecter" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   						 第<%=whichPage%>頁
  						</a>
  						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<%
									for (int i = 1; i <= pageNumber; i++) {
								%>
								 <a class="dropdown-item" href="<%=request.getRequestURI()%>?whichPage=<%=i%>">第<%=i%>頁</a>
								<%
									}
								%>
						</div>
					</div>
					<%} %>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!--  trainer end -->

	<!-- brand-area start -->
	<div class="brand-area margin-top-100">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="brand-slider">
						<div class="brant-item">
							<img src="assets/img/brand/brand1.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand2.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand3.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand4.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand5.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand1.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand2.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand3.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand4.png" alt="brand">
						</div>
						<div class="brant-item">
							<img src="assets/img/brand/brand5.png" alt="brand">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- brand-area end -->

	<!-- footer area start -->
	<footer class="footer-area footer-style-2 footer-bg padding-top-100">
		<div class="footer-top padding-top-60 padding-bottom-0">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="footer-widget widget">
							<div class="subscribe-form subscribe-form-style2">
								<div class="input-group margin-top-15 margin-bottom-100">
									<input type="text" class="form-control"
										placeholder="Enter your email address"> <span
										class="input-group-btn">
										<button class="btn btn-default submit-btn" type="button">Subscribe</button>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-5 col-12">
						<div class="footer-widget widget widget_nav_menu">
							<ul>
								<li><a href="#">Home</a></li>
								<li><a href="#">About Us</a></li>
								<li><a href="#">Service</a></li>
								<li><a href="#">Classes</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-2 col-12">
						<div class="footer-widget widget">
							<div class="about_us_widget">
								<a href="index.html" class="footer-logo"> <img
									src="assets/img/footer-logo.png" alt="footer logo">
								</a>
							</div>
						</div>
					</div>
					<div class="col-lg-5 col-12">
						<div class="footer-widget widget widget_nav_menu">
							<ul>
								<li><a href="#">Trainer</a></li>
								<li><a href="#">Shop</a></li>
								<li><a href="#">Blog</a></li>
								<li><a href="#">Contact Us</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="copyright-area-inner">
							© Zarxio 2019 All rights reserved. Powered with <i
								class="fa fa-heart"></i> by <a href="https://codingeek.net/"
								target="_blank">Codingeek</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- footer area end -->

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->

	<!-- jquery -->
	<script src="assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script src="assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script src="assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script src="assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script src="assets/js/nice-select.js"></script>
	<!-- owl carousel -->
	<script src="assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script src="assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script src="assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script src="assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script src="assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script src="assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/script.js"></script>
</body>
<script>

var before = document.getElementById("before");
var after = document.getElementById("after");
var insert = document.getElementById("plus-button");

before.addEventListener("click", function(){
	<%if (whichPage > 1) {%>
	window.location.href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>";
	<%}%>
});
after.addEventListener("click", function(){
	<%if (whichPage < pageNumber) {%>
	window.location.href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>";
	<%}%>
	});
	
insert.addEventListener("click", function(){
	window.location.href="<%=request.getContextPath()%>/front-end/bodyrecord/insertBodyRecord.jsp";
});
</script>
</html>

<%
	}
%>