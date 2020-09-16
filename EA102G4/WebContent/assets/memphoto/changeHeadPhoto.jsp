<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>

<%
	int authNumber = 0;
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	if (memVO == null) {
		memVO = memVOLogin;
		authNumber = 1;
	}
	if (memVOLogin == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	} else {
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
	#hi_mem{
	font-family:Microsoft JhengHei;
	}
	@media screen and (max-width: 1400px) {
	  #hi_mem {
			display:none;
		}
	}
	#insert-div{
		margin:auto auto;
	}
	
	.sign-area .sign-content .form-group {
    	margin-bottom: 0;
	}
	
	#input-submit{
		margin-top:20px;
	}
	#div-submit{
		margin-top:10%
	}
	#sex-div{
		display:none;
	}
	.sign-area .sign-content {
    background: #F5F5F5;
    padding: 50px 50px 50px 50px;
    color: #999999;
    width: 100%;
    font-size: 15px;
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
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/changeMyPassword.jsp">ChangePassword</a></li>
                            </ul>
                        </li>

						<li><a
							href="<%=request.getContextPath()%>/front-end/mem/mem.do?action=logout">Logout</a>
						</li>
					</ul>
				</div>
				<%
					if (memVOLogin != null) {
				%>
				<a id="hi_mem" class="current-menu-item"
					href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%></a>
				<%
					}
				%>
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
	<!-- signup area start -->
	<section class="sign-area">
        <div class="container-fluid no-gutter">
            <div class="row no-gutter">
                <div class="col-xl-3 col-lg-3" id="insert-div">
                    <div class="sign-content text-center h-100">
                        <div class="thumb">
                            <img src="assets/img/signin-logo.png" alt="">
                        </div>
                        <%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
                        <form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/memphoto/memphoto.do" name="form1" enctype="multipart/form-data">
                            <div class="form-row">
                            	<div class="form-group">
                                	 <input type="hidden" name="mem_id" id ="mem_id" value="<%=memVOLogin.getMem_id()%>" />
                            	</div>
								<div class="form-group text-left">
									<label for="photo">Photo</label>
                               		<input type="file" name="photo" id ="photo" accept="image/gif, image/jpeg, image/png" />
                            	</div>
                            	<div class="form-group">
                                 	<input type="hidden" name="mem_id" id ="mem_id" value="<%=memVOLogin.getMem_id()%>" />
                           		</div>
						
								<div>
									<img id="img_content">
								</div>
                            </div>
                            <div class="form-group" id ="div-submit">
                            <input type="hidden" name="action" value="insert"> 
                            <input type="submit" id ="input-submit" class="btn btn-dark" value="Submit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- signup area end -->

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


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
	var photo = document.getElementById('photo');
	var img = document.getElementById('img_content')

	//上傳圖片
	photo.addEventListener('change', function() {
		readURL(this);
	});

	//顯示圖片
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				img.setAttribute('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

</html>
<%
	}
%>