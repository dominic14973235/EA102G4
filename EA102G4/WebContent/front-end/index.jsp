<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pro.model.*"%>
<%
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
	ProVO proVOLogin = (ProVO)session.getAttribute("proVO");
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
        #logoID{
            /* width: 100%; */
            filter: brightness(2); 
        }
        .footer-area{
            margin-top: 0;
            padding-top: 0px;
        }
        .service-area {
            padding-bottom: 160px;
        }
        
        .service-area.service-bg:after {
            position: absolute;
		    content: '';
		    top: 21%;
		    left: 35%;
		    width: 30%;
		    height: 100%;
		    background: url(/EA102G4/front-end/assets/img/service.jpg);
		    background-size: contain;
		    background-position: 50% 0;
		    background-repeat: no-repeat;
		    z-index: -1;
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
	<div class="zarxio-navbar sticky-top">
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
					<a href="<%=request.getContextPath() %>/front-end/index.jsp"> <img src="<%=request.getContextPath()%>/front-end/gpt/assets/img/logo_new.png" alt="logo" style="width: 250px; height: 100%; "></a>
				</div>
				<div class="collapse navbar-collapse" id="billatrail_main_menu">
					<ul class="navbar-nav menu-open">
						<li class="menu-item-has-children">
                           <a href="#">Coach&Class</a>
					       <ul class="sub-menu">
							  <li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/classes.jsp">PrivateClass</a></li>
							       	<li><a href="${request.getContextPath() }/EA102G4/front-end/groupclass/groupClassSelectPage.jsp">GroupClass</a></li>
							       <li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/pro.jsp">Trainer</a></li>
							   <c:if test="${memVOLogin != null }">    
							       <li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/proApplication.jsp">TrainerApplication</a></li>
							       <li><a href="${request.getContextPath() }/EA102G4/front-end/groupclass/listMemOrder.jsp">Check My Order</a></li>
							       <c:if test="${proVOLogin != null }">
							       		<li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/myClass.jsp">TrainerBack-end</a></li>
							       </c:if>
						       </c:if>
			        		</ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">InGymStore</a>
                            <ul class="sub-menu">
                                <li><a href="${pageContext.request.contextPath}/front-end/product/list-all-product.jsp">Shop</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/product/add-product.jsp">Sell Something</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/product/list-product-by-memId.jsp">Your Product</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/purchase-order/list-order-by-memId-seller.jsp">Seller Order</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/purchase-order/list-order-by-memId-buyer.jsp">Buyer Order</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/product-tracking/list-all-tracking.jsp">Tracking Product</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children"><a href="#">Activity</a>
							<ul class="sub-menu">
								<li><a href="<%=request.getContextPath()%>/front-end/gpt/listAllGroup.jsp">Activity</a></li>
								<li style='display:${memVOLogin == null ?"none":""};'><a href="<%=request.getContextPath()%>/front-end/gpt/group.do?action=viewMyGroup&mem_id=${memVOLogin.mem_id }">My
										Activity</a></li>
								<li style='display:${memVOLogin == null ?"none":""};'><a href="<%=request.getContextPath()%>/front-end/gpt/groupAdd.jsp">Create Activity</a></li>
							</ul>
						</li>
                        <li class="menu-item-has-children">
                            <a href="#">Article</a>
                            <ul class="sub-menu">
                                <li><a href="/EA102G4/front-end/article/listAllArticle.jsp">Article</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Member</a>
                            <ul class="sub-menu">
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Personal Information</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/bodyrecord/bodyRecord.jsp">MyBodyData</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp">MySportData</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/foodrecord/foodRecord.jsp">MyFoodRecord</a></li>
                                <%if (memVOLogin != null) {%>
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/changeMyPassword.jsp">ChangeMyPassword</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/coin/addCoinOrder.jsp">Point Charge</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/coin/listMemCoinOrder.jsp">Credit History</a></li>
                                <%}%>
                            </ul>
                        </li>
						<%
							if (memVOLogin != null) {
						%>
						<li>
						<a href ="<%=request.getContextPath()%>/front-end/mem/mem.do?action=logout">Logout</a>
						</li>
						<%
							} else {
						%>
						<li><a href="<%=request.getContextPath()%>/front-end/mem/signin.jsp">Login</a></li>
						<%
							}
						%>
					</ul>
				</div>
				<%
					if (proVOLogin != null) {
				%>
				<a style="color:white;" id="hi_mem" href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%> 教練</a>
				<%
					}else if (memVOLogin != null) {
				%>
				<a style="color:white;" id="hi_mem" href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%></a>
				<%
					}
				%>
				</ul>
				<div class="nav-right-part">
					<ul>
						<li style="color:white;" class="search" id="search"><a href="#"><i class="fa fa-search"></i></a></li>
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

    <!-- banner start -->
    <div class="banner-area banner-style-one" style="background-image:url(/EA102G4/front-end/assets/img/jeff-seid-1.jpg);">
        
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
                                <p data-animation-in="fadeInLeft">加入</p>
                                <h1 class="title1" data-animation-in="fadeInDown">TIBAME<span class="color-main"><br>大小吳帶你飛</span></h1>
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
                                <p data-animation-in="fadeInLeft">JAVA口絕一</p>
                                <h1 class="title1" data-animation-in="fadeInDown">JAVA是<span class="color-main">簡單的</span> </h1>
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
                                <p data-animation-in="fadeInLeft">JAVA口絕二</p>
                                <h1 class="title1" data-animation-in="fadeInDown"><span class="color-main">萬般皆物件</span> </h1>
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
                                <p data-animation-in="fadeInLeft">JAVA口絕三</p>
                                <h1 class="title1" data-animation-in="fadeInDown"> 要什麼<span class="color-main">給什麼</span></h1>
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
                <div class="slider-image1"><img src="/EA102G4/front-end/assets/img/bigH.jpg" alt=""></div>
                <div class="slider-image1"><img src="/EA102G4/front-end/assets/img/bb.jpeg" alt=""></div>
                <div class="slider-image1"><img src="/EA102G4/front-end/assets/img/steve seid.jpg" alt=""></div>
                <div class="slider-image1"><img src="/EA102G4/front-end/assets/img/AA.jpg" alt=""></div>
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
       
        <!--floating-icon-->

        <div class="container padding-top-100 padding-bottom-94">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-6 col-xl-5">
                            <div class="left-content-area" id="twentytwenty-container">
                                <img src="/EA102G4/front-end/assets/img/DavidWu.jpg" alt="editing company image">
                                <img src="/EA102G4/front-end/assets/img/peterWu.jpg" alt="editing company image">
                            </div>
                        </div>
                        <div class="col-lg-6 col-xl-7">
                            <div class="about-content">
                                <h3 class="padding-top-3">About Us</h3>
                                <p>我們不妨可以這樣來想: 這樣看來，健身究竟是怎麼樣的存在，始終是個謎題。世界上若沒有健身，對於人類的改變可想而知。話雖如此，我們卻也不能夠這麼篤定。總而言之，健身，發生了會如何，不發生又會如何。了解清楚健身到底是一種怎麼樣的存在，是解決一切問題的關鍵。漢密爾頓說過一句著名的話，在政治上，如同在宗教上一樣，要想用火與劍迫使人們改變信仰，是同樣荒謬的。這讓我對於看待這個問題的方法有了巨大的改變。謹慎地來說，我們必須考慮到所有可能。巴斯德曾經說過，立志工作成功，是人類活動的三大要素。他會這麼說是有理由的。馬克思曾經提到過，科學的全部目的，就是有意識地使用。這句話決定了一切。</p>
                                <p class="hidden-md">要想清楚，健身，到底是一種怎麼樣的存在。既然，伏爾泰說過一句很有意思的話，祖國是我們心心嚮往的地方。這段話令我陷入了沈思。帶著這些問題，我們一起來審視健身。如果仔細思考健身，會發現其中蘊含的深遠意義。當你搞懂後就會明白了。問題的核心究竟是什麼？培根告訴我們，不能說凡是合理的都是美的，但凡。這不禁令我深思。如果此時我們選擇忽略健身，那後果可想而知。不難發現，問題在於該用什麼標準來做決定呢？既然如此，生活中，若健身出現了，我們就不得不考慮它出現了的事實。</p>
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
                                    <div class="sb-icon text-center">
                                        <a href="#"><i class="flaticon-weight" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content text-center">
                                        <h4 class="title">商城</h4>
<!--                                         <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon text-center">
                                        <a href="#"><i class="flaticon-gym-1" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content text-center">
                                        <h4 class="title">揪團</h4>
                                      <!--   <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon text-center">
                                        <a href="#"><i class="flaticon-woman" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content text-center">
                                        <h4 class="title">預約課程</h4>
<!--                                         <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon text-center">
                                        <a href="#"><i class="flaticon-weightlifting" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content text-center">
                                        <h4 class="title">各人紀錄</h4>
<!--                                         <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon text-center">
                                        <a href="#"><i class="flaticon-gym" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content text-center">
                                        <h4 class="title">行事曆</h4>
                                        <!-- <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6">
                                <div class="service-box-style-01">
                                    <div class="sb-icon text-center">
                                        <a href="#"><i class="flaticon-weight-lifting" aria-hidden="true"></i></a>
                                    </div>
                                    <div class="sb-content text-center">
                                        <h4 class="title">文章</h4>
                                        <!-- <p>Praesent a diam at velit finibus vehicula sit amet eu dui. Vestibulum rutrum dignissim arcu, vitae convallis</p> -->
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


    <!-- footer area start -->
    <footer class="footer-area footer-style-1 black-bg padding-top-160">
        <div class="footer-top padding-top-30 padding-bottom-0">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-xl-4 col-md-6">
                        <div class="footer-widget widget">
                            <a href="<%=request.getContextPath() %>/front-end/index.jsp" class="footer-logo"> 
                                <img src="/EA102G4/front-end/assets/img/footer-logo.png" alt="footer logo" style=" width: 300px;">
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
                                        <img src="/EA102G4/front-end/assets/img/peterWu.jpg" alt="">
                                    </div>
                                    <div class="content">
                                        <p class="title">台灣JAVA之神</p>
                                        <p class="desc">吳永志<br>帶你遨遊JAVA程式海不迷路</p>
                                    </div>
                                </li>
                                <li class="single-recent-post-item">
                                    <div class="thumb">
                                        <img src="/EA102G4/front-end/assets/img/DavidWu.jpg" alt="">
                                    </div>
                                    <div class="content">
                                        <p class="title">神都看好的男人</p>
                                        <p class="desc">吳冠宏<br>深入淺出帶你學JAVA</p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xl-4 col-md-12 offset-xl-1">
                        <div class="footer-widget widget">
                            <h4 class="widget-title"> MVC三部曲</h4>
                            <p class="description">接收請求參數<br>永續層存取<br>進行轉交</p>
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
                             Zarxio 2019 All rights reserved. Powered with <i class="fa fa-heart"></i>  by <a href="https://codingeek.net/" target="_blank">Codingeek</a>
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