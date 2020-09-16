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
<%@ page import="com.memphoto.model.*"%>

<%

	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	String mem_id =  (String)request.getAttribute("mem_id");
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(mem_id);
	
	if (memVO == null) {
		memVO = memVOLogin;
	}
	
	if (memVOLogin == null) {			//if (memVOLogin == null && empVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	}else{
		String memId = memVO.getMem_id();
		ProductService productService = new ProductService();
		List<ProductVO> list = productService.findByMemId(memId);
		pageContext.setAttribute("list", list);
%>
<jsp:useBean id="photoService" scope="page"
	class="com.product_photo.model.PhotoService" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>追蹤中商品</title>
<!-- favicon -->
<link rel=icon href=${pageContext.request.contextPath}/front-end/product/assets/img/favicon.png sizes="20x20"
	type="image/png">
<!-- animate -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/slick.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/responsive.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/wedy-comfirmbox/css/style.css"> --%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">


</head>
<body>
	<input type="hidden" class="memId" value="<%=memId%>">
	<%@ include file="../product/pages/header.file"%>
	<!-- cart content start -->
	<section class="cart-content-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-title">
						<h2 style="font-family:Microsoft JhengHei;font-size: 30px;font-weight: 500;">追蹤中商品</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="single-cart-list">
						<table class="table table-borderless">
							<tbody class="productTbody">
								<tr>
									<td></td>
									<td><p class="font-semibold">商品名稱</p></td>
									<td><p>價格</p></td>
									<td></td>
									<td></td>
								</tr>

								<!------------------------ 一個商品-start ------------------------>
<%-- 								<%@ include file="../product/pages/page1.file"%> --%>
<%-- 								<c:forEach var="productVO" items="${list}" --%>
<%-- 									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<!-- 									<tr> -->
<!-- 										<td style="width:146px;"> -->
<%-- <%-- 											<img src="data:image/jpg;base64,${photoService.getFirstPhoto(productVO.getProductNo()).base64Image}" style="max-width: 122px; max-height: 122px" /> --%> --%>
<!-- 										</td> -->
<!-- 										<td class="pName"></td> -->
<!-- 										<td></td> -->
<!-- 										<td></td> -->
<!-- 										<td></td> -->
<!-- 									</tr> -->
<%-- 								</c:forEach> --%>
								<!------------------------ 一個商品-end ------------------------>
							</tbody>
						</table>
<%-- 								<%@ include file="../product/pages/page2.file"%> --%>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- cart content end -->

	<!-- brand-area start -->
	<div class="brand-area margin-top-100">
		<div class="container">
			<div class="row"></div>
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
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/nice-select.js"></script>
	<!-- counter up -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/script.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/wedy-list-product-by-memId.js"></script> --%>
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/wedy-list-all-tracking.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/product/wedy-comfirmbox/js/jquery.confirmModal.min.js"></script>
</body>
</html>
<%}%>