<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.license.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.classAuth.model.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	ProVO proVO = (ProVO) request.getAttribute("proVO");
	pageContext.setAttribute("proVO", proVO);	
	
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(proVO.getMem_ID());
	pageContext.setAttribute("memVO", memVO);
	
	//get Class Type name
	ClassTypeService ctSvc = new ClassTypeService();
	List <ClassTypeVO> ctList = ctSvc.getAll();
	pageContext.setAttribute("ctList",ctList);
	
	LicenseService licenseSvc = new LicenseService();
	List <LicenseVO> licList = licenseSvc.findByPro(proVO.getPro_ID());
	pageContext.setAttribute("licList",licList);
	
	//get pro auth;
	ClassAuthService classAuthSvc = new ClassAuthService();
	List<ClassAuthVO> caList = classAuthSvc.getAllFromOnePro(proVO.getPro_ID());
	List <String> list = new ArrayList<String> ();
	for(ClassAuthVO caVO : caList){
		list.add(caVO.getC_type_no());
	}	
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


<!-- Calendar -->
<link rel="stylesheet" type="text/css"
	href="assets/css/evo-calendar.min.css">

<link rel="stylesheet" type="text/css"
	href="assets/css/evo-calendar.royal-navy.min.css">

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

 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">

<style>
.breadcrumb-style-1 {
	background-color: #F9F9FA;
	min-height: 250px;
	background-size: cover;
	background-position: center;
	position: relative;
	overflow: hidden;
}

.footer-top.padding-top-60.padding-bottom-0 {
    margin-top: 100px;
}

.border-custom .align-items-center {
    place-self: flex-start;
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
	<div class="breadcrumb-style-1 breadcrumb-nobg">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Trainner Details</h1>
			<ul class="page-list margin-bottom-0">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Trainner Details</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!--  trainer start -->
	<section class="trainer-area bg-none margin-top-100">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="row border-custom">
						<div class="col-md-4">
							<div class="thumb">
								<img src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=memPhoto&pic_no=${memVO.getMem_id()}" alt="trainer">
							</div>
						</div>
						<div class="col-md-4 d-flex align-items-center">
							<div class="content">
								<h3><%=memVO.getMem_name() %></h3>
								<p class="pb-3">證照資格:<br>
											<c:forEach var="ctVO" items="${ctList}">
											<c:if test="${fn:contains(list,ctVO.c_type_no)}">
											<br>${ctVO.t_name }
											</c:if>
											</c:forEach></p>
											
								<p class="pb-3">證照資格:<br> <c:forEach var="lcVO" items="${licList}">
		                                             		${lcVO.lic_name}<br>
		                                             	</c:forEach></p>
								<ul class="social">
									<li class="icon-list"><a href="#" class="icon-text"> <i
											class="fa fa-twitter"></i>
									</a></li>
									<li class="icon-list"><a href="#" class="icon-text"> <i
											class="fa fa-facebook-f"></i>
									</a></li>
									<li class="icon-list"><a href="#" class="icon-text"> <i
											class="fa fa-instagram"></i>
									</a></li>
									<li class="icon-list"><a href="#" class="icon-text"> <i
											class="fa fa-youtube"></i>
									</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md-4 d-flex align-items-center">
							<div class="content">
								<h3>Experience</h3>
								<p> <%=proVO.getExpr() %></p>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  trainer end -->

	<!-- Calendar-area start -->
	<div class="tariner-gallery-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<x` class="section-title padding-bottom-65 text-center">
						<h1 class="title">Calendar</h1>
						<p class="subtitle">I am available at....</p>
						
						<!-- calendar -->
<%-- 						<%@include file ="/front-end/FullCalendar/MyCalendar.jsp" %> --%>
						<jsp:include page="/front-end/FullCalendar/MyCalendar.jsp" flush="true" />
						
						<!-- calendar -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Calendar-area end -->


	 <%@ include file="../footerFile/pageFooter.file"%>

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

</html>