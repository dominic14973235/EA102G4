<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.memphoto.model.*"%>
<%@ page import="java.util.*"%>

<%	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	
	String mem_id =  (String)request.getAttribute("mem_id");
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(mem_id);
	
	if (memVO == null) {
		memVO = memVOLogin;
	}
	
	if (memVOLogin == null) {			//if (memVOLogin == null && empVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	}else{
		MemPhotoService memPhotoSvc = new MemPhotoService();
		List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto(memVO.getMem_id());
		pageContext.setAttribute("list",list);
	
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
	font-family:Microsoft JhengHei;
}
.resume-div {
	height:270px;
}

.line {
	word-wrap:break-word;
	overflow:auto;
	border:1px solid lightgray;
	width:100%;
	height:80%
}

.memdetail-title {
	color:#161616;
	font-size:24px;
	letter-spacing:0.6px;
	font-weight:600;
	margin-bottom:30px;
	margin-top:10px;
	margin-bottom:10px;
}

.full-photo {
	background-image:url(<%=request.getContextPath()%>/front-end/mem/headphoto.do?mem_id=<%=memVO.getMem_id()%>);
	background-size:100% 100%;
}
#plus-button{
	background-color:lightgray;
	opacity:.5;
	-webkit-border-radius:50%;
	-moz-border-radius:50%;
	border-radius:50%;
	position:absolute;
	left:78%;
	top:85%;
}
#plus-button:hover{
	opacity:1;
}
#plus-button:active{
	opacity:.5;
}
.button-div-item{
	display:inline-block;
	margin-top:30px;
}
.update-button{
	margin-top:30px;
}


#update-button,#class-button,#sel-button,#play-button{
	background-color: #88c13e;
    border-color: #88c13e;
}
.feature-button{
	margin-left:10px;
}

.grid-layout {
    grid-template-columns: repeat(auto-fill, minmax(90px, 1fr));
    grid-auto-rows: minmax(180px, 1fr);
}

.biography-content {
    margin-top: 50px;
}

.tab-content{
	height:910px;
    overflow: auto;
   
}
.empty-div{
	width:100%;
	height:850px;
	
}
.empty-span{
	font-size:100px;
	color:#ccc;
}

@media screen and (max-width:1400px) {
	#hi_mem {
		display:none;
	}
	div.resume-div {
		height:250px;
	}
}

@media screen and (max-width: 1199px) {
	div.resume-div{
		height:200px;
	}
	#plus-button{
		width:2em;
		height:2em;
	}
}

@media screen and (max-width: 991px) {
	div.resume-div{
		height:180px;
	}
	#plus-button{
		width:2em;
		height:2em;
	}
}

@media screen and (max-width: 767px) {
	div.resume-div {
		height:150px;
	}
	div.photo-div{
		height:500px;
	}
	#plus-button{
		width:4em;
		height:4em;
	}
	#update-button{
		width:80%;
		margin-top:30px;
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
    					<li>
						<a href ="<%=request.getContextPath()%>/front-end/mem/mem.do?action=logout">Logout</a>
						</li>
					</ul>
				</div>
				<%if (memVOLogin != null) {%>
					<a id="hi_mem" class="current-menu-item" href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%></a>
 				<%}%>
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
			<h1 class="page-title">Personal Information</h1>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!--  trainer start -->
	<section class="trainer-area bg-none margin-top-100">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="row border-custom">
						<div class="col-md-5 full-photo photo-div">
						<%if(memVO == memVOLogin){ %>
							<svg id ="plus-button" width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  								<path fill-rule="evenodd" d="M8 3.5a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5H4a.5.5 0 0 1 0-1h3.5V4a.5.5 0 0 1 .5-.5z" />
								<path fill-rule="evenodd" d="M7.5 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1H8.5V12a.5.5 0 0 1-1 0V8z" />
								<path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
							</svg>
						<%} %>
							<!--                            照片在css,當div的背景 -->
						</div>
						<div class="col-md-7 d-flex align-items-center">
							<div class="content ">
								<h3><%=memVO.getMem_name()%></h3>
								<p class="pb-3">
									性別 :
									<%=memVO.getSex()%></p>
								<p class="pb-3">
									生日 :
									<%=memVO.getMem_bir()%></p>
								<p class="pb-3">
									Email :
									<%=memVO.getMem_email()%></p>
								<p class="pb-3">
									Phone :
									<%=memVO.getMem_phone()%></p>
								<p class="pb-3">
									註冊日期 :
									<fmt:formatDate value="<%=memVO.getM_reg_date()%>"
										pattern="yyyy-MM-dd" />
								</p>
								<ul class="social">
									<li class="icon-list">
										<a href="#" class="icon-text"> 
											<!-- 連絡我小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
  												<path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
											Contact
										</a>
									</li>
									<li class="icon-list">
										<a href="#" class="icon-text"> 
											<!-- 課程小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-book" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd" d="M3.214 1.072C4.813.752 6.916.71 8.354 2.146A.5.5 0 0 1 8.5 2.5v11a.5.5 0 0 1-.854.354c-.843-.844-2.115-1.059-3.47-.92-1.344.14-2.66.617-3.452 1.013A.5.5 0 0 1 0 13.5v-11a.5.5 0 0 1 .276-.447L.5 2.5l-.224-.447.002-.001.004-.002.013-.006a5.017 5.017 0 0 1 .22-.103 12.958 12.958 0 0 1 2.7-.869zM1 2.82v9.908c.846-.343 1.944-.672 3.074-.788 1.143-.118 2.387-.023 3.426.56V2.718c-1.063-.929-2.631-.956-4.09-.664A11.958 11.958 0 0 0 1 2.82z"/>
  												<path fill-rule="evenodd" d="M12.786 1.072C11.188.752 9.084.71 7.646 2.146A.5.5 0 0 0 7.5 2.5v11a.5.5 0 0 0 .854.354c.843-.844 2.115-1.059 3.47-.92 1.344.14 2.66.617 3.452 1.013A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.276-.447L15.5 2.5l.224-.447-.002-.001-.004-.002-.013-.006-.047-.023a12.582 12.582 0 0 0-.799-.34 12.96 12.96 0 0 0-2.073-.609zM15 2.82v9.908c-.846-.343-1.944-.672-3.074-.788-1.143-.118-2.387-.023-3.426.56V2.718c1.063-.929 2.631-.956 4.09-.664A11.956 11.956 0 0 1 15 2.82z"/>
											</svg>
											Class
										</a>
									</li>
									<li class="icon-list">
										<a href="#" class="icon-text"> 
											<!-- 商城小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-shop" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd" d="M0 15.5a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zM3.12 1.175A.5.5 0 0 1 3.5 1h9a.5.5 0 0 1 .38.175l2.759 3.219A1.5 1.5 0 0 1 16 5.37v.13h-1v-.13a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.13H0v-.13a1.5 1.5 0 0 1 .361-.976l2.76-3.22z"/>
  												<path d="M2.375 6.875c.76 0 1.375-.616 1.375-1.375h1a1.375 1.375 0 0 0 2.75 0h1a1.375 1.375 0 0 0 2.75 0h1a1.375 1.375 0 1 0 2.75 0h1a2.375 2.375 0 0 1-4.25 1.458 2.371 2.371 0 0 1-1.875.917A2.37 2.37 0 0 1 8 6.958a2.37 2.37 0 0 1-1.875.917 2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.5h1c0 .76.616 1.375 1.375 1.375z"/>
  												<path d="M4.75 5.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm3.75 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm3.75 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
  												<path fill-rule="evenodd" d="M2 7.846V7H1v.437c.291.207.632.35 1 .409zm-1 .737c.311.14.647.232 1 .271V15H1V8.583zm13 .271a3.354 3.354 0 0 0 1-.27V15h-1V8.854zm1-1.417c-.291.207-.632.35-1 .409V7h1v.437zM3 9.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 .5.5V15H7v-5H4v5H3V9.5zm6 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4zm1 .5v3h2v-3h-2z"/>
											</svg>
											Mall
										</a>
									</li>
									<li class="icon-list">
										<a href="#" class="icon-text"> 
											<!-- 揪團小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-door-open" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
 												<path fill-rule="evenodd" d="M1 15.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13a.5.5 0 0 1-.5-.5zM11.5 2H11V1h.5A1.5 1.5 0 0 1 13 2.5V15h-1V2.5a.5.5 0 0 0-.5-.5z"/>
  												<path fill-rule="evenodd" d="M10.828.122A.5.5 0 0 1 11 .5V15h-1V1.077l-6 .857V15H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117z"/>
  												<path d="M8 9c0 .552.224 1 .5 1s.5-.448.5-1-.224-1-.5-1-.5.448-.5 1z"/>
											</svg>
											Activity
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 resume-div">
					<h3 class="memdetail-title">自我介紹:</h3>
					<div class="color-black line"><%=memVO.getMem_resume()%></div>
				</div>
				<%if(memVO == memVOLogin){ %>
				<button type="button" class="btn btn-dark col-md-6 update-button" id="update-button">修改個人資料</button>
				<%} %>
					
			</div>
			
		</div>
		
	</section>
	<!--  memDetail end -->
	
<!-- 		分頁 --START				-->
	<div class="product-specification padding-bottom-95">
        <div class="container">
            
                <div class="biography-area">
                    <div class="biography-content">
                        <ul class="nav nav-pills">
                        	<li>
                                <a data-toggle="pill" href="#allmemphoto" class="active"><%=memVO.getMem_name() %>'s PhotoBox</a>
                            </li>
                            <li>
                                <a data-toggle="pill" href="#trainerreview">TrainerReview</a>
                            </li>
                            <li>
                                <a data-toggle="pill" href="#sellreview">SellReview</a>
                            </li>
                            <li>
                                <a data-toggle="pill" href="#activityreview">ActivityReview</a>
                            </li>
                        </ul>
    
                        <div class="tab-content">
                        	<div id="allmemphoto" class="tab-pane fade in active show">
                        		<div class="tab-content-description">
									<div class="col-lg-12 text-center">
										<div class="container">
											
											<c:choose>
												<c:when test="${list.size() != 0 }">
												<div class="grid-layout">
													<c:forEach var ="memphotoVO" items="${list}">	
													<div class="grid-item span-4 grid-item-3">
														<img src="<%=request.getContextPath() %>/front-end/memphoto/allmemphoto.do?photo_no=${memphotoVO.photo_no}" alt="">
													</div>
													</c:forEach>
												</div>
												</c:when>
												<c:otherwise>
												<div class="empty-div">
													<span class="empty-span">尚無圖片</span>
												</div>
												</c:otherwise>
											</c:choose>
											</div>
										
									</div>
								</div>
                        	</div>
                        	<div id="trainerreview" class="tab-pane fade">
                                <div class="tab-content-description">
                                    <h2 class="margin-top-25">
                                        <span class="rating">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-half-o"></i>
                                        </span>
                                    </h2>
                                    <div class="element-progress-bar pagination-progressbar">
                                        <div class="d-flex">
                                            <p>5 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="html3"></div>
                                            </div>
                                            <p>80%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>4 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="css3"></div>
                                            </div>
                                            <p>90%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>3 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="php3"></div>
                                            </div>
                                            <p>75%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>2 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="javascript3"></div>
                                            </div>
                                            <p>10%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>1 Star</p>
                                            <div class="single-progressbar col-md-6 mb-0">
                                                <div class="jquery3"></div>
                                            </div>
                                            <p>0%</p>
                                        </div>
                                    </div>
                                    <div id="comments" class="comments-area comments-area-wrapper">
                                        <h4 class="comments-title">3 Reviews</h4>
                                        <ul class="comment-list">
                                            <li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="assets/img/blog/avatar.png" class="avatar">
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>Sharifur</h5>                       
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                            </div>
                                                            <div class="reply-btn">
                                                                <a class="comment-reply-link" href="#">Reply</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <ul class="children">
                                                    <li class="comment">
                                                        <div class="single-comment justify-content-between d-flex">
                                                            <div class="user justify-content-between d-flex">
                                                                <div class="thumb">
                                                                    <img alt="" src="assets/img/blog/avatar2.jpg" class="avatar"> 
                                                                </div>
                                                                <div class="desc">
                                                                    <div class="d-flex justify-content-between comment_title">
                                                                        <div class="d-flex align-items-center">
                                                                            <h5>Naeem</h5>
                                                                        </div>
                                                                    </div>
                                                                    <div class="comment-content">
                                                                        <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                                    </div>
                                                                    <div class="reply-btn">
                                                                        <a class="comment-reply-link" href="#">Reply</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="assets/img/blog/avatar3.jpg" class="avatar">                
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>Asade</h5>
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                            </div>
                                                            <div class="reply-btn">
                                                                <a class="comment-reply-link" href="#">Reply</a> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div id="sellreview" class="tab-pane fade">
                                <div class="tab-content-description">
                                    <h2 class="margin-top-25">
                                        <span class="rating">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-half-o"></i>
                                        </span>
                                    </h2>
                                    <div class="element-progress-bar pagination-progressbar">
                                        <div class="d-flex">
                                            <p>5 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="html3"></div>
                                            </div>
                                            <p>80%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>4 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="css3"></div>
                                            </div>
                                            <p>90%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>3 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="php3"></div>
                                            </div>
                                            <p>75%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>2 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="javascript3"></div>
                                            </div>
                                            <p>10%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>1 Star</p>
                                            <div class="single-progressbar col-md-6 mb-0">
                                                <div class="jquery3"></div>
                                            </div>
                                            <p>0%</p>
                                        </div>
                                    </div>
                                    <div id="comments" class="comments-area comments-area-wrapper">
                                        <h4 class="comments-title">3 Reviews</h4>
                                        <ul class="comment-list">
                                            <li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="assets/img/blog/avatar.png" class="avatar">
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>Sharifur</h5>                       
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                            </div>
                                                            <div class="reply-btn">
                                                                <a class="comment-reply-link" href="#">Reply</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <ul class="children">
                                                    <li class="comment">
                                                        <div class="single-comment justify-content-between d-flex">
                                                            <div class="user justify-content-between d-flex">
                                                                <div class="thumb">
                                                                    <img alt="" src="assets/img/blog/avatar2.jpg" class="avatar"> 
                                                                </div>
                                                                <div class="desc">
                                                                    <div class="d-flex justify-content-between comment_title">
                                                                        <div class="d-flex align-items-center">
                                                                            <h5>Naeem</h5>
                                                                        </div>
                                                                    </div>
                                                                    <div class="comment-content">
                                                                        <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                                    </div>
                                                                    <div class="reply-btn">
                                                                        <a class="comment-reply-link" href="#">Reply</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="assets/img/blog/avatar3.jpg" class="avatar">                
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>Asade</h5>
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                            </div>
                                                            <div class="reply-btn">
                                                                <a class="comment-reply-link" href="#">Reply</a> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div id="activityreview" class="tab-pane fade">
                                <div class="tab-content-description">
                                    <h2 class="margin-top-25">
                                        <span class="rating">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-half-o"></i>
                                        </span>
                                    </h2>
                                    <div class="element-progress-bar pagination-progressbar">
                                        <div class="d-flex">
                                            <p>5 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="html3"></div>
                                            </div>
                                            <p>80%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>4 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="css3"></div>
                                            </div>
                                            <p>90%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>3 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="php3"></div>
                                            </div>
                                            <p>75%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>2 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="javascript3"></div>
                                            </div>
                                            <p>10%</p>
                                        </div>
                                        <div class="d-flex">
                                            <p>1 Star</p>
                                            <div class="single-progressbar col-md-6 mb-0">
                                                <div class="jquery3"></div>
                                            </div>
                                            <p>0%</p>
                                        </div>
                                    </div>
                                    <div id="comments" class="comments-area comments-area-wrapper">
                                        <h4 class="comments-title">3 Reviews</h4>
                                        <ul class="comment-list">
                                            <li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="assets/img/blog/avatar.png" class="avatar">
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>Sharifur</h5>                       
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                            </div>
                                                            <div class="reply-btn">
                                                                <a class="comment-reply-link" href="#">Reply</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <ul class="children">
                                                    <li class="comment">
                                                        <div class="single-comment justify-content-between d-flex">
                                                            <div class="user justify-content-between d-flex">
                                                                <div class="thumb">
                                                                    <img alt="" src="assets/img/blog/avatar2.jpg" class="avatar"> 
                                                                </div>
                                                                <div class="desc">
                                                                    <div class="d-flex justify-content-between comment_title">
                                                                        <div class="d-flex align-items-center">
                                                                            <h5>Naeem</h5>
                                                                        </div>
                                                                    </div>
                                                                    <div class="comment-content">
                                                                        <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                                    </div>
                                                                    <div class="reply-btn">
                                                                        <a class="comment-reply-link" href="#">Reply</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="assets/img/blog/avatar3.jpg" class="avatar">                
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>Asade</h5>
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p>
                                                            </div>
                                                            <div class="reply-btn">
                                                                <a class="comment-reply-link" href="#">Reply</a> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
<!-- 			分頁--END 			-->


	<!-- brand-area start -->
	<div class="brand-area margin-top-90">
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
<script type="text/javascript">

var plusbutton = document.getElementById("plus-button");
var updatebutton = document.getElementById("update-button");


plusbutton.addEventListener('click', function(){
	window.location.href='<%=request.getContextPath()%>/front-end/memphoto/changeHeadPhoto.jsp';
});
updatebutton.addEventListener('click', function(){
	window.location.href='<%=request.getContextPath()%>/front-end/mem/updateMemDetail.jsp';
});


</script>
</html>
<%} %>
