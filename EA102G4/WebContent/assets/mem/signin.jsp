<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>


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

	.errorMsgs{
	font-family: Microsoft JhengHei;
	color:red;
	}

	#hi_mem{
	font-family:Microsoft JhengHei;
	}
	@media screen and (max-width: 1400px) {
	  #hi_mem {
			display:none;
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
	<!--  search Popup -->

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
						<li class="menu-item-has-children"><a href="#">Trainer</a>
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
						<li class="menu-item-has-children">
                            <a href="#">Member</a>
                            <ul class="sub-menu">
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">MemberDetail</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/bodyrecord/bodyRecord.jsp">BodyRecord</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp">SportRecord</a></li>
                                <li><a href="#">FoodRecord</a></li>
                            </ul>
                        </li>
						<li class ="current-menu-item"><a href="<%=request.getContextPath()%>/front-end/mem/signin.jsp">Login</a></li>
					</ul>
				</div>
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

	<!-- signin area start -->
	<div class="sign-area">
		<div class="container-fluid no-gutter">
			<div class="row no-gutter">
				<div class="col-xl-6 col-md-6">
					<div class="sign-content signup-content text-center h-100">
						<div class="thumb">
							<img src="assets/img/signin-logo.png" alt="">
						</div>
						<h3>Login Your Account!</h3>
						<c:if test="${not empty errorMsgs}">
							<font class = "errorMsgs">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li class = "errorMsgs">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<p>
							Don't have an account? <a href="signup.jsp">Register</a>
						</p>
						<a class="btn btn-primary btn-lg btn-block"><i
							class="fa fa-facebook-square"></i> Login via facebook</a>
						
						<form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/mem/mem.do" name="form1">
							<div class="form-group text-left">
                                <label for="mem_email">Email Address </label>
                                <input type="email" class="form-control" id="mem_email" name = "mem_email" placeholder="santa.bevilard@gmail.com" required>
                            </div>
							<div class="form-group text-left">
								<label for="mem_psw">Password <span
									class="color-red password-alert check">(首次登入請至電子信箱收取密碼)</span> <i class="fa fa-smile-o color-red check"
									aria-hidden="true"></i></label> <input type="password"
									class="form-control" id="mem_psw" name="mem_psw" placeholder="******" required>
							</div>
							<input type="hidden" name="action" value="login"> 
							<input type="submit" class="btn btn-success btn-lg btn-block"
								value="Login">
						</form>
					</div>
				</div>
				<div class="col-xl-4 col-md-6">
					<div class="sign-slider">
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/01.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/02.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/03.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/04.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/05.jpg" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- signin area end -->

	<!-- brand-area start -->
	<div class="brand-area">
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
	<!-- counter up -->
	<script src="assets/js/counter-up.js"></script>
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
		var check1 = document.querySelector("span.check");
		var check2 = document.querySelector("i.check");
		var mem_psw = document.getElementById("mem_psw");
		mem_psw.addEventListener('input', function(){
			var mypsw = mem_psw.value.trim();
		
			if (mypsw == "" || mypsw.lenght == 0) {
				check1.style.display="inline";
			}else{
				check1.style.display="none";
			}
			if(/^[(a-zA-Z0-9)]{2,10}$/.test(mypsw)){
				check2.setAttribute("class", "fa fa-smile-o try");
				check2.setAttribute("color", "lightgreen");
            }else{
            	check2.setAttribute("class", "fa fa-smile-o color-red try");
            }
		});
		
		
		

</script>
</html>