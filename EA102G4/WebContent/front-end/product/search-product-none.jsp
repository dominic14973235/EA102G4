<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.product_photo.model.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	String searchStr = (String)request.getAttribute("searchStr");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>InGym商城</title>
<style>
* {
	font-family: Microsoft JhengHei;
}
</style>


<!-- favicon -->
<link rel=icon
	href="${pageContext.request.contextPath}/front-end/product/assets/img/favicon.png"
	sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/slick-theme.css" />
<!-- owl carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/hamburgers.min.css">
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/nice-select.css">
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/responsive.css">
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">

</head>
<body>
	<%@ include file="pages/header.file"%>
	<%@ include file="pages/header-home.file"%>

	<!-- home shopping start -->
	<section class="shopping-area margin-top-90">
		<div class="container">
			<!-- search banner -->
			<div class="search-banner margin-bottom-50">
				<div class="row">
					<div class="col-lg-10">
						<div
							class="row d-flex justify-content-lg-between flex-column flex-md-row align-items-center text-center">
							<div class="form-field margin-top-10 margin-bottom-10 ">
								<div class="input-group">
									<form class="searchForm"
										action="${pageContext.request.contextPath}/product/product.do">
										<input type="text" class="form-control" placeholder="搜尋商品"
											name="searchStr"> <input type="hidden" name="action"
											value="searchProduct">
									</form>
									<div class="input-group-append">
										<button class="btn btn-secondary" type="button"
											onclick=searchSubmit(this)>
											<i class="fa fa-search" style="color: #88C13E"></i>
										</button>
									</div>

								</div>
							</div>
							<!-- 商品分類 -->


							<!-- 							<div class="search-menu" id="search-menu"> -->
							<!-- 								<ul> -->
							<!-- 									<li><a class="active" -->
							<%-- 										href="${pageContext.request.contextPath}/front-end/product/list-all-product.jsp">All</a></li> --%>
							<%-- 									<% --%>
							<!-- // 										Product_categoryVO cvo = null; -->

							<!-- // 									while (list_category_it.hasNext()) { -->
							<!-- // 										cvo = list_category_it.next(); -->
							<%-- 									%> --%>
							<!-- 									<li> -->
							<!-- 										<form -->
							<%-- 											action="${pageContext.request.contextPath}/product_category/category.do" --%>
							<!-- 											enctype="multipart/form-data"> -->
							<!-- 											<input type="hidden" name="action" -->
							<!-- 												value="getProductByCategory" /> <a href="#" -->
							<%-- 												onclick=subCategory(this)> <%=cvo.getCategoryName()%> <input --%>
							<!-- 												type="hidden" name="categoryNo" -->
							<%-- 												value="<%=cvo.getCategoryNo()%>" /> --%>
							<!-- 											</a> -->
							<!-- 										</form> -->
							<!-- 									</li> -->
							<%-- 									<% --%>
							// }
							<%-- 									%> --%>
							<!-- 								</ul> -->
							<!-- 							</div> -->



							<!-- /商品分類 -->
							<div class="col-7">
								<h3 style="font-family: Microsoft JhengHei; color: red;">
									關鍵字"<%=searchStr %>"查詢結果如下</h3>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- search banner end -->
			<!--------------------- ------------ -- 單個商品-------------- --------->
			<!--------------------- ------------ -- 單個商品-------------- --------->
			<div class="col-md-12"></div>
		</div>
	</section>
	<!-- home shopping end -->

	<!-- brand-area start -->
	<div class="brand-area margin-top-100">
		<div class="container">
			<div class="row">
<!-- 				<div class="col-lg-12"> -->
<!-- 					<div class="brand-slider"> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand1.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand2.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand3.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand4.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand5.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand1.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand2.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand3.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand4.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 						<div class="brant-item"> -->
<!-- 							<img -->
<%-- 								src="${pageContext.request.contextPath}/front-end/product/assets/img/brand/brand5.png" --%>
<!-- 								alt="brand"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>
	<!-- brand-area end -->

	<%@ include file="../footerFile/pageFooter.file"%>

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->

	<!-- jquery -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.magnific-popup.js"></script>
	<!-- Slick -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/slick-animation.js"></script>
	<!-- wow -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/nice-select.js"></script>
	<!-- owl carousel -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/owl.carousel.min.js"></script>
	<!-- waypoint -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/script.js"></script>

	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/wedy-search-product.js"></script>

</body>
</html>