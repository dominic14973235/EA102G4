<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.grouphour.model.*"%>
<%@ page import="java.util.stream.*" %>

<%//${gcVO.c_status==1 and gcSvc.getAllGhSize(gcVO.getG_class_no()) ge 1 }
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");	
	GroupClassService gcSvc = new GroupClassService();

	pageContext.setAttribute("gcSvc", gcSvc);
	List<GroupClassVO> list = gcSvc.getAll().stream()
			.filter(gcVO -> gcVO.getC_status()==1&&gcSvc.getAllGhSize(gcVO.getG_class_no())>=1)
			.collect(Collectors.toList());
	pageContext.setAttribute("list", list);
	ProService proSvc =new ProService();
	pageContext.setAttribute("proSvc", proSvc);
	MemService memSvc = new MemService();
	pageContext.setAttribute("memSvc", memSvc);
	ClassTypeService ctSvc = new ClassTypeService();
	pageContext.setAttribute("ctSvc", ctSvc);
	GroupHourService ghSvc = new GroupHourService();
	pageContext.setAttribute("ghSvc", ghSvc);
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("Expires", 0);
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


 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">

<style>
.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(img/gcselect.jpg);
    background-position: 97px -340px;
    background-size: 90%;
}

</style>

</head>
<body onload="connectByGC();" onunload="disconnectByGC();">



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
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay">
		<div class="breadcrumb-inner">
			<h1 class="page-title">團課</h1>
			<ul class="page-list margin-bottom-2">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Classes</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- home shopping start -->

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	
	<div class="all-classes-area">
		<div class="container all-class-slider">
			<div class="row classes-items text-center">
				<br>
	<%@ include file="page1.file"%>
				<c:forEach var="gcVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 					<c:choose><c:when test="${gcVO.c_status==1 and gcSvc.getAllGhSize(gcVO.getG_class_no()) ge 1 }"> --%>
					<div class="col-lg-4">
						<div class="single-class-item">
							<div class="top-content">
								<div class="content-img">
								
									<img
										src="../groupclass/groupClass.do?g_class_no=${gcVO.g_class_no}"
										width="200" height="200" alt="shopping">
									
								</div>
								<div class="hover-content">
									<div class="btn-wrapper desktop-center">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/groupclass/groupClass.do" 
			 					 style="margin-bottom: 0px;" enctype="multipart/form-data">
			    				 <input type="hidden" name="g_class_no"  value="${gcVO.g_class_no}">
			    				 <input type="hidden" name="action"	value="getOne_For_Display">
										<input type="submit"
											class="btn btn-element btn-normal-size btn-rounded" value="Details">
												</FORM>
									</div>
								</div>
							</div>
							<div class="bottom-content">
								<h3 style="margin-top:5px">
									團課名稱 :   ${gcVO.g_name}
								</h3>
								<p style="padding-top:5px;padding-bottom:5px;margin-bottom: 5px;">教練 : ${memSvc.getOneMem(proSvc.getOnePro(gcVO.pro_id).mem_ID).mem_name}  </p>
								<p style="padding-top:5px;padding-bottom:5px;margin-bottom: 5px;">課程類別: ${ctSvc.getOneClassType(gcVO.c_type_no).t_name}  </p>
								<p style="padding-top:5px;padding-bottom:5px;margin-bottom: 5px;">價錢: $${gcVO.p_coin} </p>
								<h4>上課地點: ${gcVO.loc}</h4>
							</div>
						</div>
					</div>
<%-- 					</c:when> --%>
<%-- 					</c:choose> --%>
				</c:forEach>
			</div>
		</div>
	</div>


	<!-- home shopping end -->

	<div class="pagination-content">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div
						class="pagination-area d-flex justify-content-center margin-top-50">
						<ul>
							<li style="white-space: nowrap;"
							<%=(pageIndex >= rowsPerPage) ? "" : "hidden"%>><A
								href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"><span
									class="next page-bumber"><i class="fa fa-angle-left"></i>
										Prev</span></A></li>
							<%for (int i = 1; i <= pageNumber; i++) {%>
							<li><A href="<%=request.getRequestURI()%>?whichPage=<%=i%>">
									<span
									class='page-bumber <%=(whichPage == i) ? "current" : ""%>'><%=i%></span>
							</A></li>
							<%}%>   
							<li style="white-space: nowrap;"<%=(pageIndex<pageIndexArray[pageNumber-1]) ? "" : "hidden"%>><span
								class="next page-bumber"><A
									href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">Next<i
										class="fa fa-angle-right"></i>
								</A></span></li>
                            
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

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
							<a href="#" class="btn btn-element btn-lg btn-main">Join with
								us</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- home explore end -->

	<!-- client start -->
	<div class="client-area padding-top-95">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title padding-bottom-63 text-center">
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
					<h1>Charif Barrani</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
			<div class="item quote-style-1">
				<div class="thumb">
					<img src="assets/img/home/client/3.png" alt="client">
				</div>
				<div class="content">
					<h1>Sharifur Robin</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
			<div class="item quote-style-1">
				<div class="thumb">
					<img src="assets/img/home/client/4.png" alt="client">
				</div>
				<div class="content">
					<h1>MD Asaduzzaman</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
			<div class="item quote-style-1">
				<div class="thumb">
					<img src="assets/img/home/client/2.png" alt="client">
				</div>
				<div class="content">
					<h1>Robiul Islam</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
			<div class="item quote-style-1">
				<div class="thumb">
					<img src="assets/img/home/client/4.png" alt="client">
				</div>
				<div class="content">
					<h1>Azharul Islam</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
			<div class="item quote-style-1">
				<div class="thumb">
					<img src="assets/img/home/client/1.png" alt="client">
				</div>
				<div class="content">
					<h1>Salim Sarker</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
			<div class="item quote-style-1">
				<div class="thumb">
					<img src="assets/img/home/client/3.png" alt="client">
				</div>
				<div class="content">
					<h1>Subrata Das</h1>
					<p>"Praesent a diam at velit finibus vehicula sit amet eu dui.
						Vestibulum rutrum dignissim arcu, vitae libero. Nulla interdum
						erat nec tincidunt"</p>
				</div>
			</div>
		</div>
	</div>
	<!-- client end -->

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
	<!-- main js -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/script.js"></script>
</body>
  <%@ include file="/front-end/groupclass/socket.file"%>
  <%@ include file="/front-end/groupclass/cart.file"%>
<!--   <script> -->
//   $('#goToBuy').click(function(e){
// 	  e.preventDefault(e);
<%-- 	  var url = "<%=request.getContextPath()%>/front-end/groupclass/insertForGroupOrderList.jsp"; --%>
// 		console.log(url);
// 		var orderView = $(location).attr('href', url);
// 		var timeoutID = window.setTimeout( ( () => orderView ), 5000);
//   })
<!--   </script> -->
</html>