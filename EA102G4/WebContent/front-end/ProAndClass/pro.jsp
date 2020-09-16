<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.classAuth.model.*"%>

<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	ProService proSvc = new ProService();
	List<ProVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
	
	ClassTypeService ctSvc = new ClassTypeService();
	List <ClassTypeVO> clist = ctSvc.getAll();
	pageContext.setAttribute("clist", clist);
			
%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="classTypeSvc" scope="page" class="com.classType.model.ClassTypeService"/>
<jsp:useBean id="classAuthSvc" scope = "page" class="com.classAuth.model.ClassAuthService"/>

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


 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">

<style>
.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(assets/img/1244995.jpg);
    background-position: 97px -340px;
    background-size: 90%;
}

div#skill {
    overflow-y: scroll;
    max-height: 77px;
}

#skill::-webkit-scrollbar {
	width: 8px;
	background-color: transparent;
}

#skill::-webkit-scrollbar-thumb {
	border-radius: 10px;
	background-color: #ececec;
}

#skill::-webkit-scrollbar-track {
	border-radius: 10px;
	background-color: transparent;
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

	<%@ include file="../proInclude/navbar.file"%>
	
	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay" >
		<div class="breadcrumb-inner">
			<h1 class="page-title">私人教練</h1>
			<ul class="page-list margin-bottom-0">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Trainer</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- search banner -->
	<div class="search-banner grey-bg padding-top-100 padding-bottom-100">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div
						class="row d-flex justify-content-xl-around flex-column flex-lg-row align-items-center text-center">
						<div
							class="filter-icon margin-top-10 margin-bottom-10 col-xl-2 col-md-1 col-sm-12 col-12">
							<i class="flaticon-adjust"></i>
						</div>
						<div
							class="form-field margin-top-10 margin-bottom-10 col-md-8 col-xl-8 col-sm-12 col-12">
							<form method="post" action="">
							<select class="select-form country_select">
								<option value="search" selected>Search by categories</option>
								<c:forEach var="classTypeVO" items="${classTypeSvc.all }">
								<option value="${classTypeVO.c_type_no }">${classTypeVO.t_name}</option>
								</c:forEach>
							</select>
						</div>
						<div
							class="btn-wrapper margin-top-10 margin-bottom-10 col-md-3 col-xl-2 col-sm-12 col-12">
							<a href="#"
								class="btn btn-element btn-normal-size btn-main-color btn-rounded">Find
								Trainer</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- search banner end -->

	<!--  trainer start -->
	<section class="trainer-area bg-none margin-bottom-100 margin-top-100">
		<div class="container">
			<div class="row class-slider-style-2">
				<c:forEach var="proVO" items="${list}">
					<div class="col-md-6">
						<div class="row border-custom">
							<div class="col-md-5">
								<div class="thumb">
										<img src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=memPhoto&pic_no=${ proVO.mem_ID}" alt="trainer">
								</div>
							</div>
							<div class="col-md-7 d-flex align-items-center">
								<div class="content">
									<h3>
										<c:forEach var="memVO" items="${memSvc.all}">
											<c:if test="${proVO.mem_ID==memVO.mem_id}">${memVO.mem_name}
                    						</c:if>
										</c:forEach>
									</h3>
									<h6>
									<c:choose>
										<c:when test="${proVO.p_rating!=0}">
											<h5>評價:${proVO.t_rating/proVO.p_rating}</h5>
										</c:when>
										<c:otherwise>
											<h5>未評價</h5>
										</c:otherwise>
									</c:choose>
									</h6>
									
									<h5>課程權限 :</h5>
										<div id="skill">	
											<c:forEach var="caVO" items="${classAuthSvc.getAllFromOnePro(proVO.pro_ID)}">
<%-- 												<c:forEach var="ctVO" items="${clist}"> --%>
													<h5>${classTypeSvc.getOneClassType(caVO.c_type_no).t_name}</h5>
<%-- 												</c:forEach> --%>
											</c:forEach>
										</div>
									<div class="btn-wrapper">
										<a href="<%=request.getContextPath() %>/front-end/ProAndClass/pro.do?pro_ID=${proVO.pro_ID}&action=getOne_For_Display"
											class="btn btn-element btn-normal-size btn-transparent-color btn-rounded">Details</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!--  trainer end -->

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