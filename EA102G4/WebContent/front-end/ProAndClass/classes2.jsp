<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.individualClass.model.*"%>
<%@ page import="com.mem.model.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	IndividualClassService IndividualClassSvc = new IndividualClassService();
	List<IndividualClassVO>list = (List<IndividualClassVO>) request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="tool" scope="page" class="com.tool.ShowPhotos" />

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

 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
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
	<div class="breadcrumb-style-1 classes-breadcrumb-overlay"
		style="background-image: url(assets/img/bg/classes.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Classes</h1>
			<ul class="page-list margin-bottom-2">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Classes</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- home shopping start -->
	<div class="all-classes-area">
		<div class="container all-class-slider">
			<div class="row classes-items text-center">
				<c:forEach var="iClassVO" items="${list}">
					<div class="col-lg-4">
						<div class="single-class-item">
							<div class="top-content">
								<div class="content-img">
									<img src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=iClassPhoto&pic_no=${iClassVO.i_class_no}" width="240" height="250" alt="shopping">
								</div>
								<div class="hover-content">
									<div class="btn-wrapper desktop-center">
										<a href="IndividualClassServlet.do?i_class_no=${iClassVO.i_class_no}&action=getOne_For_Display"
											class="btn btn-element btn-normal-size btn-rounded">Details</a>
									</div>
								</div>
							</div>
							<div class="bottom-content">
								<h3>
									<a href="IndividualClassServlet.do?i_class_no=${iClassVO.i_class_no}&action=getOne_For_Display">${iClassVO.c_name}</a>
								</h3>
								<p>${iClassVO.c_detail }</p>
								<h4>Course Price : ${iClassVO.p_coin}</h4>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>
	</div>
	</div>
	<!-- home shopping end -->

	<div class="pagination-content">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="pagination-area d-flex justify-content-center">
						<ul>
							<li><span class="next page-bumber"><i
									class="fa fa-angle-left"></i> Prev</span></li>
							<li><span class="page-bumber">1</span></li>
							<li><span class="page-bumber current">2</span></li>
							<li><span class="page-bumber">3</span></li>
							<li><span class="next page-bumber">Next <i
									class="fa fa-angle-right"></i></span></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

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