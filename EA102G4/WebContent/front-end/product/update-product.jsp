<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_photo.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.mem.model.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	System.out.println("修改之商品編號 : " + productVO.getProductNo());
	System.out.println("付款方式 : " + productVO.getPoPayment());
	System.out.println("配送方式 : " + productVO.getPoDelivery());
	
	PhotoService photoService = new PhotoService();
	List<PhotoVO> list = photoService.getOneProductAllPhoto(productVO.getProductNo());
	
	pageContext.setAttribute("productVO", productVO);
	pageContext.setAttribute("list", list);
	
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>修改商品資料</title>
<!-- favicon -->
<link rel=icon
	href=${pageContext.request.contextPath}/front-end/product/assets/img/favicon.png
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
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/nice-select.css">
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
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/wedy.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
<script
	src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery-2.2.4.min.js"></script>
<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/front-end/product/assets/js/wedyAddProduct.js"></script> --%>
	
	<style type="text/css">
		#pphoto {
			display:block;
		}
	</style>
</head>
<body>

	<%@ include file="pages/header.file"%>
	<!-- product-details-tab start -->
	<div class="product-details-tab padding-top-100 padding-bottom-135">
		<!--------- bottom想改成200不知道為什麼動style動不了 --------->

		<FORM METHOD="post"
			ACTION="${pageContext.request.contextPath}/product/product.do"
			name="form1" enctype="multipart/form-data" id="form1">
			<div class="container">
				<div class="row">
					<!-- 圖片區域下 -->
					<div class="col-lg-12">


						<div class="content-part">

							<!---------------------------------填寫購物資料------------------------------------>
							<h3 style="font-family: Microsoft JhengHei; font-size: 30px;">修改商品資料</h3>
							<div class="form-group">
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<!-- 									<font style="color: red">請修正以下錯誤:</font> -->
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
							<!-- 					<div id="image-outside"> -->
							<label for="myFile">商品圖片:</label>
							<div id="preview" class="row">
								<c:forEach var="photoVO" items="${list}">
										<img src="data:image/jpg;base64,${photoVO.base64Image}" style="max-width:25%;max-height:25%;">
								</c:forEach>
							</div>
							<!-- 						</div> -->
							<div class="input-group mb-1" style="padding: 30px;">
<!-- 								<div class="custom-file"> -->
<!-- 									<input type="file" class="custom-file-input" id="myFile" -->
<!-- 										multiple="multiple" name="p_photo"> <label -->
<!-- 										class="custom-file-label" for="inputGroupFile02" -->
<!-- 										style="font-size: 14px">選擇圖片</label> <input type="hidden" -->
<!-- 										id="fileName"> -->
<!-- 								</div> -->
							</div>
							<div class="form-group">
								<label for="usr">商品名稱:</label> <input type="text"
									class="form-control" id="usr" name="pName" value="${productVO.pName}">
							</div>
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="st">數量:</label> <input type="text"
										class="form-control" id="st" name="pStock" value="<fmt:formatNumber value="${productVO.pStock}" pattern="0.#" />">
								</div>
							</span> <span class="specifications mt-2"> <jsp:useBean
									id="categorySvc" scope="page"
									class="com.product_category.model.CategoryService" />
								<div class="form-group">
									<label for="sel1">商品種類:</label> <select class="form-control"
										id="sel1" name="categoryNo">
										<c:forEach var="product_categoryVO" items="${categorySvc.all}">
											<option value="${product_categoryVO.categoryNo}"
												${(productVO.categoryNo==product_categoryVO.categoryNo)? 'selected':'' }>
												${product_categoryVO.categoryName}
										</c:forEach>
									</select>
								</div>

							<input type="hidden" id="poPayment" value="<%=productVO.getPoPayment()%>">
							<input type="hidden" id="poDelivery" value="<%=productVO.getPoDelivery()%>">
							<input type="hidden" id="productNo" value="<%=productVO.getProductNo()%>">
							
												
							</span> <span class="specifications mt-2">
								<div class="form-group">
									<label for="pr">價格:</label> <input type="text"
										class="form-control" id="pr" name="pPrice" value="<fmt:formatNumber value="${productVO.pPrice}" pattern="0.#" />">
								</div>
							</span>
							<div class="form-group">
								<div class="row">
									<span class="specifications mt-2 col-6"> <label>付款方式:</label>
										<div class="form-check" id="pay">
											<input class="form-check-input position-static"
												type="checkbox" id="blankCheckbox1" aria-label="..."
												name="poPayment" value="10"> <label
												class="form-check-label" for="blankCheckbox1"
												style="font-size: 16px;">${poPaymentListener["10"]}</label>

										</div>
										<div class="form-check">
											<input class="form-check-input position-static"
												type="checkbox" id="blankCheckbox2" aria-label="..."
												name="poPayment" value="20"> <label
												class="form-check-label" for="blankCheckbox2"
												style="font-size: 16px;">${poPaymentListener["20"]}</label>

										</div>
										<div class="form-check">
											<input class="form-check-input position-static"
												type="checkbox" id="blankCheckbox3" aria-label="..."
												name="poPayment" value="30"> <label
												class="form-check-label" for="blankCheckbox3"
												style="font-size: 16px;">${poPaymentListener["30"]}</label>
										</div>

									</span> <span class="specifications mt-2 col-6"> <label>運送方式:</label>
										<div class="form-check">
											<input class="form-check-input position-static"
												type="checkbox" id="blankCheckbox4" aria-label="..."
												name="poDelivery" value="10"> <label
												class="form-check-label" for="blankCheckbox4"
												style="font-size: 16px;">${poDeliveryListener["10"]}</label>

										</div>
										<div class="form-check">
											<input class="form-check-input position-static"
												type="checkbox" id="blankCheckbox5" aria-label="..."
												name="poDelivery" value="20"> <label
												class="form-check-label" for="blankCheckbox5"
												style="font-size: 16px;">${poDeliveryListener["20"]}</label>

										</div>
									</span>
								</div>
							</div>

							<div class="form-group">
								<label for="cate">商品詳情:</label>
								<textarea id="cate" class="form-control" rows="7" name="pDetail">${productVO.pDetail}</textarea>
							</div>
							<div class="btn-wrapper"
								style="text-align: right; padding-top: 5px;">
								<a href="#" style="font-family: Microsoft JhengHei;"
									class="btn btn-element btn-medium-size btn-main-color btn-rounded"
									id="productSubmit">確認修改</a>
							</div>
							<input type="hidden" name="action" value="update"> <input
								type="hidden" name="action" value="getOneForInsertPhoto">
							<input type="hidden" name="productNo"
								value="${productVO.productNo}">
						</div>
						<!---------------------------------填寫購物資料------------------------------------>
					</div>

				</div>
				<!-- row -->
			</div>
			<!-- container -->
		</FORM>

	</div>
	<!-- product-details-tab end -->


	<!-- home shopping start -->

	<!-- home shopping end -->

	<!-- brand-area start -->

	<!-- brand-area end -->

<%@ include file="../footerFile/pageFooter.file"%>

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->


	<!-- jquery -->

	<!-- popper -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/nice-select.js"></script>
	<!-- counter up -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/slick-animation.js"></script>
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

	<!-- rProgressbar -->

	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/wedyUpdateProduct.js"></script>
</body>
</html>