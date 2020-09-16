<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
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
     <link rel=icon href=assets/img/favicon.png sizes="20x20" type="image/png">
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
    <!-- hamburgers -->
    <link rel="stylesheet" href="assets/css/hamburgers.min.css">
    <!-- twentytwenty -->
    <link rel="stylesheet" href="assets/css/twentytwenty.css">
    <!-- Date Picker -->
    <link href="assets/css/datepicker.min.css" rel="stylesheet" type="text/css">
    <!-- Time Picker -->
    <link rel="stylesheet" href="assets/css/wickedpicker.min.css">
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
            <button type="submit" class="submit-btn"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <!-- //. search Popup -->

    <div class="info-popup">
        <div class="info-popup-content">
            <button type="button" class="info-popup-content_close">X</button>
            <div class="row no-gutters">
                <div class="col-xl-7 col-12 col-sm-6">
                    <div class="info-popup-content__img info-popup-content__img--one"></div>
                </div>
                <div class="col-xl-5 col-12 col-sm-6">
                    <div class="info-popup-content__text">
                        <div class="info-popup-content__text-header">
                            <h3 class="info-popup-content__title">Opening Hours</h3>
                        </div>
                        <div class="info-popup-content__text-body">
                            <span class="info-popup-content__text-is">moday - sunday</span>
                            <span class="info-popup-content__text-is">8.00 am - 9.00 pm</span>
                        </div>
                        <div class="info-popup-content__text-footer">
                            <span class="info-popup-content__text-is">+880 046 292 02</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!--info-popup-->
    
    <div class="location-popup">
        <div class="location-popup-content">
            <button type="button" class="message-popup-content_close">X</button>
            <div class="row no-gutters">
                <div class="col-lg-7 col-12 col-sm-6">
                    <div class="mapouter1">
                        <div class="gmap_canvas1">
                            <iframe width="100%" height="100%" id="gmap_canvas1" src="https://maps.google.com/maps?q=university%20of%20san%20francisco&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                            <a href="https://www.embedgooglemap.net/blog/elementor-pro-discount-code-review/">elementor review</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-12 col-sm-6">
                    <div class="location-popup-content__text">
                        <div class="location-popup-content__text-header">
                            <h3 class="location-popup-content__title">Address</h3>
                        </div>
                        <div class="location-popup-content__text-body">
                            <span class="location-popup-content__text-is">
                                2, Ave Manchester, Lorem <br />
                                ipsum St, Rio Danubin
                            </span>
                        </div>
                        <div class="btn-wrapper desktop-center">
                            <a href="#" class="btn btn-element btn-normal-size btn-main-color btn-rounded">Open in maps</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!--location-popup-->
    
    <div class="message-popup">
        <div class="message-popup-content">
            <button type="button" class="message-popup-content_close">X</button>
            <div class="row no-gutters">
                <div class="col-lg-7 col-12 col-sm-6">
                    <div class="message-popup-content__text text-left pl-5">
                        <div class="message-popup-content__text-header">
                            <h3 class="message-popup-content__title mb-3">
                                You have a question for us?
                            </h3>
                        </div>
                        <div class="message-popup-content__text-body">
                            <form action="#">
                                <div class="row">
                                    <div class="col-12 margin-top-20">
                                        <input type="text" placeholder="Email address" class="contact-form__input"/>
                                    </div>
                                    <div class="col-12 margin-top-20">
                                        <textarea name="Message" placeholder="Message" class="contact-form__textarea" cols="30" rows="2" ></textarea>
                                    </div>
                                    <div class="col-12">
                                        <div class="btn-wrapper desktop-center margin-top-40">
                                            <a href="#" class="btn btn-element btn-normal-size btn-main-color btn-rounded">Send Us</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-12 col-sm-6">
                    <div class="message-popup-content__img message-popup-content__img--three"></div>
                </div>
            </div>
        </div>
    </div><!--message-popup-->

    <!-- navbar start -->
    <div class="zarxio-navbar">
        <nav class="navbar navbar-area navbar-expand-lg nav-style-01">
            <div class="container nav-container">
                <div class="responsive-mobile-menu">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#billatrail_main_menu" 
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                        <span class="bar1"></span>
                        <span class="bar2"></span>
                        <span class="bar3"></span>
                    </button>
                </div>
                
                <div class="logo">
					<a href="<%=request.getContextPath() %>/front-end/index.jsp"> <img src="<%=request.getContextPath()%>/front-end/gpt/assets/img/logo_new.png" alt="logo"></a>
				</div>
               
                <div class="collapse navbar-collapse" id="billatrail_main_menu">
                	
                    <ul class="navbar-nav menu-open">
                    
                        <li class="menu-item-has-children current-menu-item">
                            <a href="#">Home</a>
                            <ul class="sub-menu">
                                <li><a href="index.html">Home 1</a></li>
                                <li><a href="index-2.html">Home 2</a></li>
                                <li><a href="index-3.html">Home 3</a></li>
                            </ul>
                        </li>

                        <li class="hidden-md"><a href="about.html">About</a></li>
                        <li class="menu-item-has-children">
                            <a href="#">Classes</a>
                            <ul class="sub-menu">
                                <li><a href="classes.html">Class</a></li>
                                <li><a href="class-details.html">Class Details</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Trainer</a>
                            <ul class="sub-menu">
                                <li><a href="trainer.html">Trainer</a></li>
                                <li><a href="trainer-details.html">Trainer Details</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Shop</a>
                            <ul class="sub-menu">
                                <li><a href="shop.html">Shop</a></li>
                                <li><a href="product-details.html">Product Details</a></li>
                                <li><a href="shopping-cart.html">Shopping Cart</a></li>
                                <li><a href="payment.html">Payment</a></li>
                                <li><a href="checkout.html">Checkout</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Blog</a>
                            <ul class="sub-menu">
                                <li><a href="blog.html">Blog</a></li>
                                <li><a href="blog-details.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
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
                        <li class="search" id="search">
                            <a href="#"><i class="fa fa-search"></i></a>
                        </li>
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

    <!-- banner start -->
    <div class="banner-area banner-style-one" style="background-image:url(assets/img/bg/1.png);">
        
        <div class="border1"></div>
        <div class="border2"></div>
        <div class="border3"></div>
        <div class="border4"></div>

        <div class="banner-slider banner-slide1">
            <div class="banner-bg-style-one">
                <div class="container">
                    <div class="row h-100vh">
                        <div class="col-lg-6">
                            <div class="banner-inner-style-one">
                                <p data-animation-in="fadeInLeft">FUEL YOUR</p>
                                <h1 class="title1" data-animation-in="fadeInDown"> <span class="color-main">BODY</span> FITNESS</h1>
                                <div class="btn-wrapper" data-animation-in="fadeInUp">
                                    <a href="#" class="btn btn-slider">Be one of us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banner-bg-style-one" >
                <div class="container">
                    <div class="row h-100vh">
                        <div class="col-lg-6">
                            <div class="banner-inner-style-one">
                                <p data-animation-in="fadeInLeft">Improve</p>
                                <h1 class="title1" data-animation-in="fadeInDown"> <span class="color-main">Your</span> Health</h1>
                                <div class="btn-wrapper" data-animation-in="fadeInUp">
                                    <a href="#" class="btn btn-slider">Be one of us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banner-bg-style-one">
                <div class="container">
                    <div class="row h-100vh">
                        <div class="col-lg-6">
                            <div class="banner-inner-style-one">
                                <p data-animation-in="fadeInLeft">Let's Change</p>
                                <h1 class="title1" data-animation-in="fadeInDown"> <span class="color-main">Life</span> Style</h1>
                                <div class="btn-wrapper" data-animation-in="fadeInUp">
                                    <a href="#" class="btn btn-slider">Be one of us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banner-bg-style-one">
                <div class="container">
                    <div class="row h-100vh">
                        <div class="col-lg-6">
                            <div class="banner-inner-style-one">
                                <p data-animation-in="fadeInLeft">Start YOUR</p>
                                <h1 class="title1" data-animation-in="fadeInDown"> <span class="color-main">Gym</span> Classes</h1>
                                <div class="btn-wrapper" data-animation-in="fadeInUp">
                                    <a href="#" class="btn btn-slider">Be one of us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- social icon -->
        <ul class="social-icon">
            <li class="icon-list">
                <a href="https://twitter.com/codingeeknet%20" target="_blank" class="icon-text">
                    <i class="fa fa-twitter"></i>
                </a>
            </li>
            <li class="icon-list">
                <a href="https://www.facebook.com/codingeek.net/" target="_blank" class="icon-text">
                    <i class="fa fa-facebook-f"></i>
                </a>
            </li>
            <li class="icon-list">
                <a href="https://www.instagram.com/codingeeknet/%20" target="_blank" class="icon-text">
                    <i class="fa fa-instagram"></i>
                </a>
            </li>
            <li class="icon-list">
                <a href="https://www.youtube.com/channel/UCz1tS-oRzKeElBOd6pIjgLQ" target="_blank" class="icon-text">
                    <i class="fa fa-youtube"></i>
                </a>
            </li>
        </ul>

        <!-- video button -->
        <div class="cart-btn">
            <a href="#"><i class="flaticon-cart"></i><span class="badge">3</span></a>
        </div>
        
        <!-- scroll down -->
        <div class="scroll">
            <a href="#home-about">
                <div class="scroll-btn">
                    <span></span>
                    <ul>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
            </a>
        </div>

        <div class="banner-small-slider">
            <div class="small-slider">
                <div class="slider-image1"><img src="assets/img/home/sm-slider/1.jpg" alt=""></div>
                <div class="slider-image1"><img src="assets/img/home/sm-slider/2.jpg" alt=""></div>
                <div class="slider-image1"><img src="assets/img/home/sm-slider/3.jpg" alt=""></div>
                <div class="slider-image1"><img src="assets/img/home/sm-slider/4.jpg" alt=""></div>
            </div>
            <div class="controler-wrapper d-none d-lg-block">
                <div class="progressbar">
                    <span class="home-slider-progress"></span>
                    <span class="home-slider-progress-active"></span> 
                </div>
                <span class="active-controler">01</span><span>/</span><span class="total-controler">04</span>
            </div>
        </div>
    </div>
    <!-- banner end -->

    <!-- home-about start -->
    <div class="home-about" id="home-about">
        <div class="floating-icon" id="service_info_item">
            <div class="floating-icon__is floating-icon-info">
                <i class="flaticon-phone-call"></i>
            </div>
            <!--floating-icon-is-->
            <div class="floating-icon__is floating-icon-location">
                <i class="flaticon-placeholder"></i>
            </div>
            <!--floating-icon-is-->
            <div class="floating-icon__is floating-icon-message">
                <i class="flaticon-email"></i>
            </div>
            <!--floating-icon-is-->
        </div>
        <!--floating-icon-->

        <div class="container padding-top-100 padding-bottom-94">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-6 col-xl-5">
                            <div class="left-content-area" id="twentytwenty-container">
                                <img src="assets/img/home/before.jpg" alt="editing company image">
                                <img src="assets/img/home/after.jpg" alt="editing company image">
                            </div>
                        </div>
                        <div class="col-lg-6 col-xl-7">
                            <div class="about-content">
                                <span>Who we are</span>
                                <h3 class="padding-top-3">About Us</h3>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis libero. Nulla interdum erat nec tincidunt laoreet. In ac consequat risus. Donec a lectus mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In maximus volutpat vehicula. Morbi ut dui iss. Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis libero. Nulla interdum erat nec tincidunt laoreet. In ac consequat risus. Donec a lectus mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In maximus volutpat vehicula. Morbi ut dui iss.</p>
                                <p class="hidden-md">Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In maximus volutpat vehicula. Morbi ut dui iss. Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis libero. Nulla interdum erat nec tincidunt laoreet. In ac consequat risus. Donec a lectus mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In maximus volutpat vehicula. </p>
                                <div class="btn-wrapper margin-top-40">
                                    <a href="#" class="btn btn-element btn-normal-size btn-main-color btn-rounded mr-5">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- home-about end -->

    <!-- home service start -->
    <div class="our-service-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title text-center padding-bottom-35">
                        <p class="subtitle">Service we provide</p>
                        <h1 class="title">Our Services</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="service-area service-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon">
                                        <a href="#"><i class="flaticon-weight" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content">
                                        <h4 class="title">Strength equipment</h4>
                                        <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon">
                                        <a href="#"><i class="flaticon-gym-1" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content">
                                        <h4 class="title">Gym equipment</h4>
                                        <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon">
                                        <a href="#"><i class="flaticon-woman" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content">
                                        <h4 class="title">Health equipment</h4>
                                        <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon">
                                        <a href="#"><i class="flaticon-weightlifting" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content">
                                        <h4 class="title">Weight equipment</h4>
                                        <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon">
                                        <a href="#"><i class="flaticon-gym" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content">
                                        <h4 class="title">Energy equipment</h4>
                                        <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon">
                                        <a href="#"><i class="flaticon-weight-lifting" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content">
                                        <h4 class="title">Fitness equipment</h4>
                                        <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- home service end -->

    <!-- home explore start -->
    <div class="explore-area explore-bg margin-top-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="explore-title">
                        <div class="title">
                            <h2>Explore life fitness</h2>
                        </div>
                        <div class="btn-wrapper">
                            <a href="#" class="btn btn-element btn-lg btn-main">Join with us</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- home explore end -->

    <!-- popular classes start -->
    <div class="popular-classes">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title title-padding-bottom text-center">
                        <p class="subtitle">What do you want to learn today?</p>
                        <h1 class="title">Popular Class</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="popular-slider">
                        <div class="single-popular-item">
                            <div class="popular-item-style-01">
                                <div class="thumb">
                                    <img src="assets/img/home/popular/1.png" alt="slider1">
                                </div>
                                <div class="content">
                                    <p>Muscles Build</p>
                                </div>
                                <div class="hover-element">
                                    <div class="hover-top">
                                        <span>Body Shaper</span><br>
                                        <a href="#" class="btn">H. Hines</a>
                                    </div>
                                    <ul>
                                        <li><a href="#">Sat <br><span>10:00am</span></a></li>
                                        <li><a href="#">Sun <br><span>12:00am</span></a></li>
                                        <li><a href="#">Mon <br><span>07:00am</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="single-popular-item">
                            <div class="popular-item-style-01">
                                <div class="thumb">
                                    <img src="assets/img/home/popular/2.png" alt="slider1">
                                </div>
                                <div class="content">
                                    <p>Body Build</p>
                                </div>
                                <div class="hover-element">
                                    <div class="hover-top">
                                        <span>Body Shaper</span><br>
                                        <a href="#" class="btn">Sharifur</a>
                                    </div>
                                    <ul>
                                        <li><a href="#">Sat <br><span>08:00am</span></a></li>
                                        <li><a href="#">Sun <br><span>09:00am</span></a></li>
                                        <li><a href="#">Wed <br><span>10:00am</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="single-popular-item">
                            <div class="popular-item-style-01">
                                <div class="thumb">
                                    <img src="assets/img/home/popular/3.png" alt="slider1">
                                </div>
                                <div class="content">
                                    <p>Fitness Build</p>
                                </div>
                                <div class="hover-element">
                                    <div class="hover-top">
                                        <span>Body Shaper</span><br>
                                        <a href="#" class="btn">Naeem</a>
                                    </div>
                                    <ul>
                                        <li><a href="#">Sat <br><span>05:00am</span></a></li>
                                        <li><a href="#">Mon <br><span>06:00am</span></a></li>
                                        <li><a href="#">Tues <br><span>08:00am</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="single-popular-item">
                            <div class="popular-item-style-01">
                                <div class="thumb">
                                    <img src="assets/img/home/popular/4.png" alt="slider1">
                                </div>
                                <div class="content">
                                    <p>Mind Refress</p>
                                </div>
                                <div class="hover-element">
                                    <div class="hover-top">
                                        <span>Body Shaper</span><br>
                                        <a href="#" class="btn">Janice Mcreaken</a>
                                    </div>
                                    <ul>
                                        <li><a href="#">Sat <br><span>10:00am</span></a></li>
                                        <li><a href="#">Thurs <br><span>10:00am</span></a></li>
                                        <li><a href="#">Fri <br><span>10:00am</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="single-popular-item">
                            <div class="popular-item-style-01">
                                <div class="thumb">
                                    <img src="assets/img/home/popular/1.png" alt="slider1">
                                </div>
                                <div class="content">
                                    <p>Muscles Build</p>
                                </div>
                                <div class="hover-element">
                                    <div class="hover-top">
                                        <span>Body Shaper</span><br>
                                        <a href="#" class="btn">John Smith</a>
                                    </div>
                                    <ul>
                                        <li><a href="#">Sat <br><span>12:00am</span></a></li>
                                        <li><a href="#">Wed <br><span>10:00am</span></a></li>
                                        <li><a href="#">Fri <br><span>09:00am</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- popular classes end -->

    <!-- class schedule start -->
    <div class="class-schedule padding-top-93">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title text-center padding-bottom-47">
                        <p class="subtitle">Zarxio Weekly Classes</p>
                        <h1 class="title">Class Schedule</h1>
                    </div>
                </div>
            </div>
            <form>
                <div class="row schedule-bottom schedule-bg-1">
                    <div class="col-lg-12 col-xl-8">
                        <div class="d-flex justify-content-lg-around flex-column flex-lg-row align-items-center text-center">
                            <div class="form-field margin-top-10 margin-bottom-10">
                                <!--Date Picker-->
                                <input type="text" id="datepicker-modal" placeholder="10/19/2019" class="datepicker-here datepick" data-language='en' data-date-format="dd/mm/yyyy">
                            </div>
                            <div class="form-field margin-top-10 margin-bottom-10">
                                <input type="text" name="timepicker" class="timepicker resto_timepicker" />
                            </div>
                            <div class="btn-wrapper margin-top-10 margin-bottom-10">
                                <a href="#" class="btn btn-element btn-normal-size btn-main-color btn-rounded">Find Class → </a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="d-flex flex-row flex-wrap justify-content-center schedule-area">
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 01</p>
                            <p>12:00pm - 01:00pm</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Charif Barrani</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 02</p>
                            <p>01:00pm - 02:00pm</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Sharifur</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 03</p>
                            <p>02:00pm - 03:00pm</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Naeem</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 04</p>
                            <p>03:00pm - 04:00pm</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Asadeee</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 05</p>
                            <p>04:00pm - 05:00pm</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Rasel</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 06</p>
                            <p>05:00am - 06:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Nasir</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 07</p>
                            <p>07:00am - 8:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Salim</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 08</p>
                            <p>09:00am - 10:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Mosabber</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 09</p>
                            <p>11:00am - 12:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Robiul</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 10</p>
                            <p>12:00am - 02:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Refat</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 01</p>
                            <p>02:00am - 3:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Alomgir</a>
                            </div>
                        </div>
                    </div>
                    <div class="schedule-item-style-01">
                        <div class="content">
                            <p>Gm Session 02</p>
                            <p>12:00am - 02:00am</p>
                            <div class="btn-wrapper">
                                <a href="#" class="btn">Subrata</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- class schedule end -->

    <!-- partner-area start -->
    <div class="partner-area">
        <div class="container">
            <div class="row">
                <div class="order-2 order-sm-1 col-lg-6 col-xl-7 d-flex align-items-end">
                    <div class="thumb">
                        <img src="assets/img/home/partner.png" alt="partner">
                    </div>
                </div>
                <div class="order-1 order-sm-2 col-lg-6 col-xl-5 d-flex align-items-center">
                    <div class="partner-content">
                        <p>Team challenge</p>
                        <h1>Partner up - <br>Double Power</h1>
                        <div class="btn-wrapper">
                            <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Choose Your Partner</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- partner-area end -->

    <!-- home trainer start -->
    <div class="trainer-title">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title padding-top-93 padding-bottom-47 text-center">
                        <p class="subtitle">Your Best Partner</p>
                        <h1 class="title">Choose your trainer</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="trainer-area margin-bottom-25">
        <div class="container">
            <div class="row class-slider">
                <div class="col-md-6">
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/1.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Dorothy D. Nabors</h3>
                                <h6>Fitness Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/2.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Surunjit kumar</h3>
                                <h6>Health Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/3.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Mushfiqur Rahman</h3>
                                <h6>Body Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/4.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Janice Mcreaken</h3>
                                <h6>Message Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/5.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Dorothy D. Nabors</h3>
                                <h6>Yoga Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/6.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Dorothy D. Nabors</h3>
                                <h6>Gym Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/7.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Dorothy D. Nabors</h3>
                                <h6>Weight Trainer</h6>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row border-custom">
                        <div class="col-md-5 d-flex align-items-end">
                            <div class="thumb">
                                <img src="assets/img/home/trainer/8.png" alt="trainer">
                            </div>
                        </div>
                        <div class="col-md-7 d-flex align-items-center">
                            <div class="content">
                                <h3>Dorothy D. Nabors</h3>
                                <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                                <div class="btn-wrapper">
                                    <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
                                </div>
                                <ul class="social">
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-facebook-f"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                    <li class="icon-list">
                                        <a href="#" class="icon-text">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- home trainer end -->

    <!-- pricing start -->
    <div class="pricing-area padding-top-90">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title padding-bottom-65 text-center">
                        <p class="subtitle">Become A Part of us</p>
                        <h1 class="title">Our Membership</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-12">
                    <div class="single-pricing-style-01 pricing-item-bg1">
                        <h2 class="off-style-1">10% Off</h2>
                        <div class="pricing-icon text-center">
                            <a href="#"><i class="flaticon-weight" aria-hidden="true"></i></a>
                        </div>
                        <div class="plan-title">
                            <h3>Biginner plan</h3>
                        </div>
                        <ul>
                            <li><span>$50</span></li>
                            <li>24 Hours Support</li>
                            <li> All Over the World</li>
                            <li>Customer Managment</li>
                            <li>Business & Financ Analysing</li>
                            <li>Unlimited Tires</li>
                        </ul>
                        <div class="btn-wrapper desktop-center">
                            <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Select Plan</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-12">
                    <div class="single-pricing-style-01 active pricing-item-bg2">
                        <h2 class="off-style-1">20% Off</h2>
                        <div class="pricing-icon text-center">
                            <a href="#"><i class="flaticon-weight-lifting" aria-hidden="true"></i></a>
                        </div>
                        <div class="plan-title">
                            <h3>Intermediate plan</h3>
                        </div>
                        <ul>
                            <li><span>$70</span></li>
                            <li>24 Hours Support</li>
                            <li> All Over the World</li>
                            <li>Customer Managment</li>
                            <li>Business & Financ Analysing</li>
                            <li>Unlimited Tires</li>
                        </ul>
                        <div class="btn-wrapper desktop-center">
                            <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Select Plan</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-12">
                    <div class="single-pricing-style-01 pricing-item-bg3">
                        <h2 class="off-style-1">20% Off</h2>
                        <div class="pricing-icon text-center">
                            <a href="#"><i class="flaticon-woman" aria-hidden="true"></i></a>
                        </div>
                        <div class="plan-title">
                            <h3>Ultimate plan</h3>
                        </div>
                        <ul>
                            <li><span>$90</span></li>
                            <li>24 Hours Support</li>
                            <li> All Over the World</li>
                            <li>Customer Managment</li>
                            <li>Business & Financ Analysing</li>
                            <li>Unlimited Tires</li>
                        </ul>
                        <div class="btn-wrapper desktop-center">
                            <a href="#" class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Select Plan</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- pricing end -->

    <!-- home shopping start -->
    <div class="shopping-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title padding-top-93 padding-bottom-60 text-center">
                        <p class="subtitle">Product For People</p>
                        <h1 class="title">Let's Shopping</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 shopping-slider shopping-slider-item text-center">
                    <div class="shop-single-item">
                        <div class="top-content">
                            <div class="thumb">
                                <img src="assets/img/home/shopping/1.png" alt="shopping">
                            </div>
                            <div class="hover-content">
                                <div class="btn-wrapper desktop-center">
                                    <a href="#" class="btn btn-element btn-normal-size btn-rounded">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="bottom-content">
                            <p class="subtitle">Fitness, Accessories</p>
                            <h3><a href="product-details.html">Free Dumbbell Ball</a></h3>
                            <p class="price">
                                <ins>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>34.00
                                    </span>
                                </ins>
                                <del>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>50.00
                                    </span>
                                </del> 
                            </p>
                        </div>
                    </div>
                    <div class="shop-single-item">
                        <div class="top-content">
                            <div class="thumb">
                                <img src="assets/img/home/shopping/2.png" alt="shopping">
                            </div>
                            <div class="hover-content">
                                <div class="btn-wrapper desktop-center">
                                    <a href="#" class="btn btn-element btn-normal-size btn-rounded">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="bottom-content">
                            <p class="subtitle">Gym, Accessories</p>
                            <h3><a href="product-details.html">Incline Bench Press</a></h3>
                            <p class="price">
                                <ins>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>34.00
                                    </span>
                                </ins>
                                <del>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>50.00
                                    </span>
                                </del> 
                            </p>
                        </div>
                    </div>
                    <div class="shop-single-item">
                        <div class="top-content">
                            <div class="thumb">
                                <img src="assets/img/home/shopping/3.png" alt="shopping">
                            </div>
                            <div class="hover-content">
                                <div class="btn-wrapper desktop-center">
                                    <a href="#" class="btn btn-element btn-normal-size btn-rounded">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="bottom-content">
                            <p class="subtitle">Health, Accessories</p>
                            <h3><a href="product-details.html">Pec Deck Machine</a></h3>
                            <p class="price">
                                <ins>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>34.00
                                    </span>
                                </ins>
                                <del>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>50.00
                                    </span>
                                </del> 
                            </p>
                        </div>
                    </div>
                    <div class="shop-single-item">
                        <div class="top-content">
                            <div class="thumb">
                                <img src="assets/img/home/shopping/4.png" alt="shopping">
                            </div>
                            <div class="hover-content">
                                <div class="btn-wrapper desktop-center">
                                    <a href="#" class="btn btn-element btn-normal-size btn-rounded">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="bottom-content">
                            <p class="subtitle">Weight, Accessories</p>
                            <h3><a href="product-details.html">Pull-Down Machine</a></h3>
                            <p class="price">
                                <ins>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>34.00
                                    </span>
                                </ins>
                                <del>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>50.00
                                    </span>
                                </del> 
                            </p>
                        </div>
                    </div>
                    <div class="shop-single-item">
                        <div class="top-content">
                            <div class="thumb">
                                <img src="assets/img/home/shopping/5.png" alt="shopping">
                            </div>
                            <div class="hover-content">
                                <div class="btn-wrapper desktop-center">
                                    <a href="#" class="btn btn-element btn-normal-size btn-rounded">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="bottom-content">
                            <p class="subtitle">Yoga, Accessories</p>
                            <h3><a href="product-details.html">Inclined Bench Press</a></h3>
                            <p class="price">
                                <ins>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>34.00
                                    </span>
                                </ins>
                                <del>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>50.00
                                    </span>
                                </del> 
                            </p>
                        </div>
                    </div>
                    <div class="shop-single-item">
                        <div class="top-content">
                            <div class="thumb">
                                <img src="assets/img/home/shopping/12.png" alt="shopping">
                            </div>
                            <div class="hover-content">
                                <div class="btn-wrapper desktop-center">
                                    <a href="#" class="btn btn-element btn-normal-size btn-rounded">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="bottom-content">
                            <p class="subtitle">Health, Accessories</p>
                            <h3><a href="product-details.html">Hammer Strength</a></h3>
                            <p class="price">
                                <ins>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>34.00
                                    </span>
                                </ins>
                                <del>
                                    <span class="woocommerce-Price-amount amount">
                                        <span class="woocommerce-Price-currencySymbol">$</span>50.00
                                    </span>
                                </del> 
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- home shopping end -->

    <!-- shaper-area start -->
    <div class="shaper-area padding-top-85">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="shaper-left h-100">
                        <h1>Body<br> Shapper</h1>
                        <div class="btn-wrapper">
                            <a href="#">Buy Now</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="shaper-right h-100">
                        <h1>Cycling</h1>
                        <div class="btn-wrapper desktop-right">
                            <a href="#">Buy Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- shaper-area end -->

    <!-- news-area start -->
    <div class="news-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title title-padding text-center">
                        <p class="subtitle">Zarxio News For You</p>
                        <h1 class="title">Latest News</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-sm-12 col-11 news-slider text-center">
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/1.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Rasel</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 9 comments</a></h6>
                            </div>
                            <h3>Cable Pulley gym</h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">20 Dec</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/2.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Nasir</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 7 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">Just like a stability</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">25 Dec</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/3.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Fuad</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 4 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">Nulla tempus, augue</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">24 Jun</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/4.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Robin</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 8 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">Train different parts</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">14 Jan</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/5.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Asad</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 9 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">World wide occasion</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">10 Jan</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/6.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Naeem</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 7 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">Easy solution here</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">09 Aug</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/7.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By Admin</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 3 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">Doing something new</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">03 May</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                    <div class="news-item-style-01">
                        <div class="thumb">
                            <img src="assets/img/home/news/8.png" alt="news">
                        </div>
                        <div class="content">
                            <div class="blog-meta">
                                <h6 class="d-flex justify-content-between"><a href="#"><i class="fa fa-user-o" aria-hidden="true"></i> By John</a><a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 6 comments</a></h6>
                            </div>
                            <h3><a href="blog-details.html">Gym fair 2019</a></h3>
                            <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                        </div>
                        <div class="hover-content">
                            <span class="border-bottom">12 Jul</span><br>
                            <span>2019</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- news-area end -->

    <!-- client start -->
    <div class="client-area margin-top-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title title-padding-bottom text-center">
                        <p class="subtitle">Zarxio Testimonial</p>
                        <h1 class="title">Our Clients Say's</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="owl-carousel owl-theme">
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/1.png" alt="client">
                </div>
                <div class="content">
                    <h1>Arpita paul</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/2.png" alt="client">
                </div>
                <div class="content">
                    <h1>Susmita sen</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/3.png" alt="client">
                </div>
                <div class="content">
                    <h1>Dipa talukdar</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/4.png" alt="client">
                </div>
                <div class="content">
                    <h1>Jimmy H. Hines</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/7.png" alt="client">
                </div>
                <div class="content">
                    <h1>Sharifur Rahman</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/8.png" alt="client">
                </div>
                <div class="content">
                    <h1>Azharul Naeem</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
            <div class="item quote-style-1">
                <div class="thumb">
                    <img src="assets/img/home/client/9.png" alt="client">
                </div>
                <div class="content">
                    <h1>Asaduzzaman</h1>
                    <p>"Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum erat nec tincidunt"</p>
                </div>
            </div>
        </div>
    </div>
    <!-- client end -->

    <!-- brand-area start -->
    <div class="brand-area margin-top-80">
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
    <footer class="footer-area footer-style-1 black-bg padding-top-160">
        <div class="footer-top padding-top-30 padding-bottom-0">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-xl-4 col-md-6">
                        <div class="footer-widget widget">
                            <a href="index.html" class="footer-logo"> 
                                <img src="assets/img/footer-logo.png" alt="footer logo">
                            </a>
                            <ul class="contact_info_list">
                                <li class="single-info-item">
                                    <div class="icon">
                                        <i class="flaticon-phone-call"></i>
                                    </div>
                                    <div class="details">
                                        1900-45-20
                                    </div>
                                </li>
                                <li class="single-info-item">
                                    <div class="icon">
                                        <i class="flaticon-placeholder"></i>
                                    </div>
                                    <div class="details">
                                        152/02 William Streat, NYE
                                    </div>
                                </li>
                                <li class="single-info-item">
                                    <div class="icon">
                                        <i class="flaticon-email"></i>
                                    </div>
                                    <div class="details">
                                        Zarxiofitness@info.com
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xl-3 col-md-6">
                        <div class="footer-widget widget">
                            <h4 class="widget-title">Recent Post</h4>
                            <ul class="recent_post_item">
                                <li class="single-recent-post-item">
                                    <div class="thumb">
                                        <img src="assets/img/post/1.png" alt="">
                                    </div>
                                    <div class="content">
                                        <p class="title">New gym center</p>
                                        <p class="desc">There are many variations of passages</p>
                                    </div>
                                </li>
                                <li class="single-recent-post-item">
                                    <div class="thumb">
                                        <img src="assets/img/post/2.png" alt="">
                                    </div>
                                    <div class="content">
                                        <p class="title">Improve fitness</p>
                                        <p class="desc">There are many variations of passages</p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xl-4 col-md-12 offset-xl-1">
                        <div class="footer-widget widget">
                            <h4 class="widget-title">Stay in Touch</h4>
                            <p class="description">Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p>
                            <div class="subscribe-form">
                                <div class="input-group margin-top-30">
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
                    <div class="col-lg-12">
                        <div class="copyright-area-inner copyright-sm margin-top-30">
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
    <!-- owl carousel -->
    <script src="assets/js/owl.carousel.min.js"></script>
    <!-- Slick -->
    <script src="assets/js/slick.min.js"></script>
    <!-- Slick Animation -->
    <script src="assets/js/slick-animation.js"></script>
    <!-- jquery.twentytwenty -->
    <script src="assets/js/jquery.twentytwenty.js"></script>
    <script src="assets/js/jquery.event.move.js"></script>
    <!-- waypoint -->
    <script src="assets/js/waypoints.min.js"></script>
     <!-- Date Picker -->
     <script src="assets/js/datepicker.min.js"></script>
     <script src="assets/js/datepicker.en.js"></script>
     <!-- Time Picker -->
     <script src="assets/js/wickedpicker.min.js"></script>
    <!-- counterup -->
    <script src="assets/js/jquery.counterup.min.js"></script>
    <!-- imageloaded -->
    <script src="assets/js/imagesloaded.pkgd.min.js"></script>
    <!-- isotope -->
    <script src="assets/js/isotope.pkgd.min.js"></script>
     <!-- rProgressbar -->
    <script src="assets/js/jQuery.rProgressbar.min.js"></script>
    <script src="assets/js/timepicker.js"></script>
    <!-- main js -->
    <script src="assets/js/main.js"></script>
    <script src="assets/js/script.js"></script>
</body>


</html>