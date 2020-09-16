<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	if(memVO == null){
		memVO = memVOLogin;
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
    }
    .breadcrumb-style-1.breadcrumb-nobg{
    	min-height:200px;
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
						<li class="menu-item-has-children  current-menu-item">
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
						<%if(memVOLogin != null){ %>
    					<li>
						<a href ="<%=request.getContextPath()%>/front-end/mem/mem.do?action=logout">Logout</a>
						</li>
    					<%}else{ %>
                        <li><a href="<%=request.getContextPath()%>/front-end/mem/signin.jsp">Login</a></li>
                        <%} %>
					</ul>
				</div>
				<%if(memVOLogin != null){ %>
    				<a id = "hi_mem" href ="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%></a>
    			<%} %>
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
	<div class="breadcrumb-style-1 breadcrumb-nobg">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Change MyPassword</h1>
		</div>
	</div>
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
                        <form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/mem/mem.do" name="form1">
                            <div class="form-row">
                            
<!--                            會員編號 -->
                            	<div class="form-group">
                                    <input type="hidden" name="mem_id" id ="mem_id" value="<%=memVOLogin.getMem_id()%>" />
                                </div>
                                
<!--                            會員姓名 -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_name" id="mem_name" value="<%=memVOLogin.getMem_name()%>"><br>
                                </div>
                                
<!--                            會員密碼 -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_psw" id="mem_psw" value="<%=memVOLogin.getMem_psw()%>" />
                                </div>
                                
<!--                            確認原會員密碼  -->
                                <div class="form-group col-md-10 text-left">
                                <label for="mem_psw_check">Password</label>
                                    <input type="password" name="mem_psw_check" id="mem_psw_check" class="form-control" />
                                </div>
                                
<!--                            新會員密碼  -->
                                <div class="form-group col-md-10 text-left">
                                <label for="mem_psw_new">NewPassword</label>
                                    <input type="password" name="mem_psw_new" id="mem_psw_new" class="form-control" />
                                </div>
                                
<!--                            確認新會員密碼  -->
                                <div class="form-group col-md-10 text-left">
                                <label for="mem_psw_newcheck">CheckNewPassword</label>
                                    <input type="password" name="mem_psw_newcheck" id="mem_psw_newcheck" class="form-control" />
                                </div>
                                
<!--                            會員生日 -->
                                <div class="form-group">
								<input type="hidden" class="form-control" name="mem_bir" id="mem_bir" value="<%=memVOLogin.getMem_bir()%>"><br>
								</div>
								
<!--                            會員性別 -->
                                <div id="sex-div">
                                    <%if(memVO.getSex().equals("男")){ %>
                                    <input type="radio" id="male" class="sex-margin" name="sex" value="男" checked> <label for="male" class="sex-margin">男</label>
                                    <input type="radio" id="female" class="sex-margin" name="sex" value="女"> <label class="sex-margin" for="female">女</label><br>
									<%}else{ %>
									<input type="radio" id="male" class="sex-margin" name="sex" value="男"><label class="sex-margin" for="male">男</label>
									<input type="radio" id="female" class="sex-margin" name="sex" value="女" checked><label class="sex-margin" for="female">女</label><br>
									<%} %>
                                </div>
                                
<!--                            會員地址 -->
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="mem_addr" name="mem_addr" value="<%=memVO.getMem_addr()%>">
                                </div>
                                
<!--                            會員信箱(帳號) -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_email" size="30" value="<%=memVOLogin.getMem_email()%>" />
                                </div>
                                
<!--                            會員電話 -->
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="mem_phone" name="mem_phone" value="<%=memVO.getMem_phone()%>">
                                </div>
                                
<!--                            會員請假次數 -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_absent" size="30" value="<%=memVOLogin.getMem_absent()%>" />
                                </div>
                                
<!--                            會員點數 -->
                                <div class="form-group">
                                    <input type="hidden" name="coin" size="30" value="<%=memVOLogin.getCoin()%>" />
                                </div>
                                
<!--                            會員自我介紹 -->
                                <div class="form-group">
                                    <input type = "hidden" class="form-control" id="mem_resume" name="mem_resume" value="<%=memVOLogin.getMem_resume()%>">
                                </div>
                                
<!--                            註冊時間 -->
                                <div class="form-group">
                                    <input type="hidden" name="m_reg_date" size="30" value="<%=memVOLogin.getM_reg_date()%>" />
                                </div>
                                
<!--                            賣家權限 -->
                                <div class="form-group">
                                    <input type="hidden" name="sel_auth" size="30" value="<%=memVOLogin.getSel_auth()%>" />
                                </div>
                                
<!--                            發文權限 -->
                                <div class="form-group">
                                    <input type="hidden" name="art_auth" size="30" value="<%=memVOLogin.getArt_auth()%>" />
                                </div>
                                
<!--                            留言權限 -->
                                <div class="form-group">
                                    <input type="hidden" name="com_auth" size="30" value="<%=memVOLogin.getCom_auth()%>" />
                                </div>
                            </div>
                            <div class="form-group" id ="div-submit">
                            <input type="hidden" name="action" value="update_psw"> 
                            <input type="submit" id ="input-submit" class="btn btn-dark" value="Submit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- signup area end -->

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
                                    <input type="text" class="form-control" placeholder="Enter your email address">
                                    <span class="input-group-btn">
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
                                <a href="index.html" class="footer-logo"> 
                                    <img src="assets/img/footer-logo.png" alt="footer logo">
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
                            © Zarxio 2019 All rights reserved. Powered with <i class="fa fa-heart"></i>  by <a href="https://codingeek.net/" target="_blank">Codingeek</a>
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

</html>
<%}%>