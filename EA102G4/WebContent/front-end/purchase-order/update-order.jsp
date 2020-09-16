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
	System.out.println(productVO.getPoPayment());
%>

<jsp:useBean id="photoService" scope="page" class="com.product_photo.model.PhotoService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>修改訂單</title>
<!-- favicon -->
<link rel=icon href=${pageContext.request.contextPath}/front-end/purchase-order/assets/img/favicon.png sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/slick.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/responsive.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/wedy-quantity-box.css">
<!-- favicon -->
<link rel=icon href=assets/img/favicon.png sizes="20x20" type="image/png">
<!-- animate -->
<!-- <link rel="stylesheet" href="assets/css/animate.css"> -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/wedy.css">
<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedyAddProduct.js"></script>
<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedy-quantity-box.js"></script>
<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedy-update-order.js"></script>




</head>
<body>

	<%@ include file="../product/pages/header.file"%>
	<!-- product-details-tab start -->
	<div class="product-details-tab padding-top-100 padding-bottom-135">
		<!--------- bottom想改成200不知道為什麼動style動不了 --------->

		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/purchase_order/purchaseOrder.do" name="form1" enctype="multipart/form-data" id="form1">
			<div class="container">
				<div class="row">
					<!-- 圖片區域下 -->
					<div class="col-lg-12">


						<div class="content-part">

							<!---------------------------------填寫購物資料------------------------------------>
							<h3 style="font-family: Microsoft JhengHei; font-size: 30px; font-weight: 500;">修改訂單</h3>
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
							<div class="col-lg-12" style="">
								<div class="single-cart-list">
									<table class="table table-borderless">
										<tbody>
											<tr style="text-align: center;">
												<td style="width: 20%;"></td>
												<td style="width: 30%;">商品名稱</td>
												<td style="width: 10%;">單價</td>
												<td style="width: 30%;">購買數量</td>
												<td style="">總計</td>
											</tr>
											<tr style="text-align: center;">
												<td>
													<img src="data:image/jpg;base64,${photoService.get(productVO.getProductNo()).base64Image}" style="max-width: 150px; max-height: 150px;">
												</td>
												<td>
													<p class="font-semibold">${productVO.pName}</p>
												</td>
												<td>
													<p id="pPrice">
														<fmt:formatNumber value="${productVO.pPrice}" pattern="0.#" />
														元
													</p>
												</td>

												<td>
													<div class="qty" style="margin-bottom: 10px;">
														<span class="minus bg-dark">-</span>
														<input type="number" class="count" name="qty" value="1" id="count">
														<input type="hidden" id="afterErrorQuantity" value="${purchaseOrderVO.quantity}">
														<span class="plus bg-dark">+</span>
													</div>
												</td>
												<td>
													<p id="total"></p>
												</td>
												<!-- Total -->
											</tr>

										</tbody>
									</table>
								</div>
							</div>
							<input type="hidden" id="poPaymentChoose" value="${productVO.poPayment}">
							<input type="hidden" id="afterErrorPayment" value="${purchaseOrderVO.poPayment}">
							<input type="hidden" id="poDeliveryChoose" value="${productVO.poDelivery}">
							<input type="hidden" id="afterErrorDelivery" value="${purchaseOrderVO.poDelivery}">
							<div class="form-group">
								<label for="buyerName">訂購人姓名:</label>
								<input type="text" name="buyerName" value="${purchaseOrderVO.buyerName}" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="buyerName">
							</div>
							<div class="form-group">
								<label for="buyerPhone">訂購人電話:</label>
								<input type="text" name="buyerPhone" value="${purchaseOrderVO.buyerPhone}" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="buyerPhone">
							</div>
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="pay">付款方式:</label>
									<select class="form-control" id="payment" name="poPayment">
										<option id="1" selected>請選擇付款方式</option>
										<option id="10" value='10' style="display: none;">信用卡</option>
										<option id="20" value='20' style="display: none;">轉帳付款</option>
										<option id="30" value='30' style="display: none;">取貨付款</option>
									</select>
								</div>
							</span>
							<span class="specifications mt-2">
								<span class="specifications mt-2">
									<div class="form-group">
										<label for="del">配送方式:</label>
										<select class="form-control" id="delivery" name="poDelivery">
											<option id="2" selected>請選擇寄送方式</option>
											<option id="40" value='10'>宅配到府</option>
											<option id="50" value='20'>取貨付款</option>
										</select>
									</div>
								</span>
								<!-- 填寫地址-start -->
								<div id="addressTr" style="display: none;">
									<span class="specifications mt-2">
										<div class="form-group" >
											<div class="input-group mb-3">
												<select class="form-control addressPlusAddress1" id="縣市1"></select>
												<select class="form-control addressPlusAddress2" id="鄉鎮市區1"></select>
											</div>
										</div>
									</span>
									<span class="specifications mt-2">
										<div class="form-group">
											<div class="input-group mb-3">
												<label for="pay">地址:</label>
												<input type="text"   value="" class="form-control addressPlusAddress3" aria-label="Default" aria-describedby="inputGroup-sizing-default">
												<input type="hidden" name="deliveryAddress" class="theFullAddress" value="">
												<input type="hidden" class="afterErrorAddress" value="${purchaseOrderVO.deliveryAddress}">
											</div>
										</div>
									</span>
								</div>
								<!-- 填寫地址-end -->
								<!-- 選擇門市-start -->
								<!-- 							<span class="specifications mt-2"> -->
								<!-- 								<div class="form-group" id="addressTr" style="display:none;"> -->
								<!-- 									<div class="input-group mb-3"> -->
								<!-- 										<label for="pay">配送地址:</label> -->
								<!-- 										<input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"> -->
								<!-- 									</div> -->
								<!-- 								</div> -->
								<!-- 							</span> -->
								<!-- 選擇門市-end -->
								<span class="specifications mt-2">
									<jsp:useBean id="categorySvc" scope="page" class="com.product_category.model.CategoryService" />
								</span>
								<div class="form-group">
									<label for="cate">給賣家的話:</label>
									<textarea id="forSeller" class="form-control" rows="7" name="purchaseDetail">${purchaseOrderVO.purchaseDetail}</textarea>
								</div>
								<span class="specifications mt-2">
									<div class="form-group" style="text-align: right; margin-bottom: 0px;">
										<label id="tb" style="margin-bottom: 0px;"></label>
									</div>
								</span>
								<div class="btn-wrapper" style="text-align: right; padding-top: 5px;">
									<a href="#" style="font-family: Microsoft JhengHei" class="btn btn-element btn-medium-size btn-main-color btn-rounded" id="productSubmit" style="margin-top: 0;" onclick=sub(this)>確認資料無誤，送出訂單</a>
								</div>

								<input type="hidden" name="action" value="update">
								<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
								<input type="hidden" name="productNo" value="${productVO.productNo}">
								<input type="hidden" name="pName" value="${productVO.pName}">
								<input type="hidden" name="pStock" value="${productVO.pStock}" id="ps">
								<input type="hidden" name="memId" value="MEM0000002">
								<!--買家會員編號 -->
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
		<span class="back-top">
			<i class="fa fa-angle-up"></i>
		</span>
	</div>
	<!-- back to top area end -->


	<!-- jquery -->

	<!-- popper -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/nice-select.js"></script>
	<!-- counter up -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/script.js"></script>

</body>
</html>