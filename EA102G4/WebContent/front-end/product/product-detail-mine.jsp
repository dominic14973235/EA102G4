<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_photo.model.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.memphoto.model.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
String mem_id = (String) request.getAttribute("mem_id");
MemService memSvc = new MemService();
MemVO memVO = memSvc.getOneMem(mem_id);
String memId = null;
if (memVO == null) {
	memVO = memVOLogin;
	if (memVO != null) {
		memId = memVO.getMem_id();
	}
}

pageContext.setAttribute("memId", memId);

ProductVO productVO = (ProductVO) request.getAttribute("productVO");
System.out.println("前端的getProductNo : " + productVO.getProductNo());
PhotoService photoService = new PhotoService();

List<PhotoVO> list = photoService.getOneProductAllPhoto(productVO.getProductNo());
Iterator it = list.iterator();
List<PhotoVO> list2 = photoService.getOneProductAllPhoto(productVO.getProductNo());
Iterator it2 = list2.iterator();
%>
<jsp:useBean id="categoryService" scope="page" class="com.product_category.model.CategoryService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<style type="text/css">
* {
	font-family: Microsoft JhengHei;
}
</style>

<title>${productVO.pName}</title>
<!-- favicon -->
<link rel=icon href="${pageContext.request.contextPath}/front-end/product/assets/img/favicon.png" sizes="20x20" type="image/png">
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/product/assets/css/wedy.css">
</head>
<body>
	<%@ include file="pages/header.file"%>

	<!-- product-details-tab start -->
	<div class="product-details-tab padding-top-100 padding-bottom-60">
		<!--------- bottom想改成200不知道為什麼動style動不了 --------->
		<div class="container">
			<div class="row">
				<div class="col-lg-5">
					<!-- 圖片區域上 -->
					<div class="slider-tabfor">
						<%
							while (it.hasNext()) {
						%>
						<%
							PhotoVO photoVO = (PhotoVO) it.next();
						%>
						<%--                      <c:forEach var="photoVO" items="${list}"> --%>
						<div class="single-item">
							<img src="data:image/jpg;base64,<%=photoVO.getBase64Image()%>" style="width: 566px; max-height: 566px;" />
						</div>
						<%
							}
						%>
					</div>
					<div class="slider-tabnav">
						<%
							while (it2.hasNext()) {
						%>
						<%
							PhotoVO photoVO = (PhotoVO) it2.next();
						%>
						<div class="single-item">
							<div class="img">
								<img src="data:image/jpg;base64,<%=photoVO.getBase64Image()%>" />
								<%--                                 <img src="${pageContext.request.contextPath}/front-end/product/assets/img/product-details/1.png" alt=""> --%>
							</div>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<!-- 圖片區域下 -->
				<div class="col-lg-7">
					<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/purchase_order/purchaseOrder.do" name="form1" enctype="multipart/form-data" id="form1">
						<div class="content-part">
							<!---------------------------------填寫購物資料------------------------------------>

							<!--                         <h2>Tradmail<br><span>For Men, Made in China, 2017</span></h2> -->
							<div class="form-group" style="margin-bottom: 30px;">
								<h2 style="font-family: Microsoft JhengHei;"><%=productVO.getpName()%></h2>
								<label style="font-size: 14px; color: gray;">${categoryService.getOneCategory(productVO.categoryNo).categoryName}</label>
								<br>
								<!-- 商品種類 -->
								<span class="rating ml-0" id="ra">
									<i class="fa fa-star" id="star1"></i>
									<i class="fa fa-star" id="star2"></i>
									<i class="fa fa-star" id="star3"></i>
									<i class="fa fa-star" id="star4"></i>
									<i class="fa fa-star-half-o" id="star5"></i>
									<input type="hidden" class="pRating" value="${productVO.pRating / productVO.numberOfRating}">
								</span>
							</div>
							<div class="form-group">
								<label>庫存量:</label>
								<label>
									<fmt:formatNumber value="<%=productVO.getpStock()%>" pattern="0.#" />
								</label>
							</div>
							<span class="specifications mt-2"> </span>
							<span class="specifications mt-2">
								<div class="form-group">
									<label>價格:</label>
									<label>
										<fmt:formatNumber value="<%=productVO.getpPrice()%>" pattern="0.#" />
										元
									</label>
								</div>
							</span>

							<span class="specifications mt-2">
								<div class="form-group">
									<label>上架日期:</label>
									<label>
										<fmt:formatDate value="${productVO.pUploadTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</label>
								</div>
							</span>

							<div class="form-group">
								<label>商品詳情:</label>
								<br>
								<label style="font-size: 18px;"><%=productVO.getpDetail()%></label>
							</div>

							<div class="btn-wrapper" style="text-align: right; padding-top: 5px;">
								<a href="javascript:void(0)" class="btn btn-link insertProductTrackingBtn" style="font-family: Microsoft JhengHei; padding-top: 25px; padding-right: 25px;">加入追蹤</a>
								<a href="javascript:void(0)" class="btn btn-link deleteProductTrackingBtn" style="font-family: Microsoft JhengHei; padding-top: 25px; padding-right: 25px; display: none;">取消追蹤</a>
								<a href="#" class="btn btn-element btn-medium-size btn-main-color btn-rounded" id="productSubmit" style="font-family: Microsoft JhengHei;" onclick=buyIt(this)>直接購買</a>
							</div>
							<input type="hidden" name="action" value="getOneForOrder">
							<input type="hidden" class="memId" value="${memId}">
							<input type="hidden" name="productNo" class="productNo" value="${productVO.productNo}">
						</div>
						<!---------------------------------填寫購物資料------------------------------------>
					</FORM>
				</div>
			</div>
		</div>
	</div>
	<!-- product-details-tab end -->

	<!-- home shopping start -->
	<div class="shopping-area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h1 class="related-product-title" id="related-product-title">相關商品</h1>
				</div>
			</div>
			<div class="row shopping-slider-item shopping-slider3 text-center">
				<!-- 相關商品開始  -->



				<%
					Set set = categoryService.getProductsByCategory(productVO.getCategoryNo());
				Iterator it3 = set.iterator();
				ProductVO p = null;
				int i = 0;
				while (i <= 4 && it3.hasNext()) {
					p = (ProductVO) it3.next();
					System.out.println("測試01 :　" + productVO.getProductNo());
					System.out.println("測試01 :　" + p.getProductNo());
					if (p.getProductNo().equals(productVO.getProductNo())) {
						if (it3.hasNext()) {

					p = (ProductVO) it3.next();
						} else {
					break;
						}
					}
				%>

				<div class="col-md-3 justify-content-center">
					<!-- 單個相關商品開始  -->
					<div class="shop-single-item margin-bottom-50">
						<div class="top-content">
							<div class="thumb">
								<img src="data:image/jpg;base64,<%=photoService.get(p.getProductNo()).getBase64Image()%>" style="max-width: 198px; max-height: 198px" />
							</div>
							<div class="hover-content">
								<div class="btn-wrapper desktop-center">
									<FORM METHOD="post" action="${pageContext.request.contextPath}/purchase_order/purchaseOrder.do" id="go">
										<a href="#" class="btn btn-element btn-normal-size btn-rounded" onclick=buyIt(this)>直接購買</a>
										<input type="hidden" name="action" value="getOneForOrder">
										<input type="hidden" name="productNo" value="<%=p.getProductNo()%>">
										<input type="hidden" name="quantity" value="<%=p.getProductNo()%>">
									</FORM>
								</div>
							</div>
						</div>
						<div class="bottom-content">
							<p class="subtitle">${categoryService.getOneCategory(productVO.getCategoryNo()).categoryName}</p>

							<FORM METHOD="post" action="../product/product.do" enctype="multipart/form-data" id="fff">
								<h5>
									<a href="#" onclick=sub(this)><%=p.getpName()%>
										<input type="hidden" name="productNoRelated" value="<%=p.getProductNo()%>">
										<input type="hidden" name="action" value="getOneForDisplayRelated">
									</a>
								</h5>
								<!-- 商品名稱 -->
							</FORM>

							<p class="price">
								<ins>
									<span class="woocommerce-Price-amount amount">
										<span class="woocommerce-Price-currencySymbol">
											<fmt:formatNumber value="<%=p.getpPrice()%>" pattern="0.#" />
										</span>
										元
										<!-- 商品價格 -->
									</span>
								</ins>
							</p>
						</div>
					</div>
				</div>
				<!-- 單個相關商品結束 -->

				<%
					i++;
				}
				//                 圖片不足時補空格 不會讓版面跑版
				if (i == 3) {
				%>
				<div class="col-md-3 justify-content-center"></div>
				<%
					} else if (i == 2) {
				%>
				<div class="col-md-3 justify-content-center"></div>
				<div class="col-md-3 justify-content-center"></div>
				<%
					} else if (i == 1) {
				%>
				<div class="col-md-3 justify-content-center"></div>
				<div class="col-md-3 justify-content-center"></div>
				<div class="col-md-3 justify-content-center"></div>
				<%
					}
				%>

			</div>
			<!-- 相關商品結束  -->
		</div>
	</div>
	<!-- home shopping end -->

	<!-- brand-area start -->
	<div class="brand-area margin-top-85"></div>
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
								<li>
									<a href="#">Home</a>
								</li>
								<li>
									<a href="#">About Us</a>
								</li>
								<li>
									<a href="#">Service</a>
								</li>
								<li>
									<a href="#">Classes</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-2 col-12">
						<div class="footer-widget widget">
							<div class="about_us_widget">
								<a href="index.html" class="footer-logo">
									<img src="${pageContext.request.contextPath}/front-end/product/assets/img/footer-logo.png" alt="footer logo">
								</a>
							</div>
						</div>
					</div>
					<div class="col-lg-5 col-12">
						<div class="footer-widget widget widget_nav_menu">
							<ul>
								<li>
									<a href="#">Trainer</a>
								</li>
								<li>
									<a href="#">Shop</a>
								</li>
								<li>
									<a href="#">Blog</a>
								</li>
								<li>
									<a href="#">Contact Us</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="copyright-area-inner">
							© Zarxio 2019 All rights reserved. Powered with
							<i class="fa fa-heart"></i>
							by
							<a href="https://codingeek.net/" target="_blank">Codingeek</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- footer area end -->

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top">
			<i class="fa fa-angle-up"></i>
		</span>
	</div>
	<!-- back to top area end -->

	<script>
	var sub = document.getElementById("productSubmit");
	sub.addEventListener('click',function(e){
		document.getElementById("form1").submit();
		console.log("succes");
	});
	
	</script>

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

	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/wedyListAllProduct.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/product/assets/js/wedy-product-detail-mine.js"></script>
	<!-- rProgressbar -->
	<!--     <script src="assets/js/wedy.js"></script> -->

</body>
</html>
<%-- <%}%> --%>