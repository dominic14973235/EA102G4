<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.product_photo.model.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	try{
		String poNo = request.getParameter("poNo");
		String showSuccess = request.getParameter("showSuccess");
		if(showSuccess.equals("true")){
			pageContext.setAttribute("showSuccess", showSuccess);
		}
		PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
		ProductService productService = new ProductService();
		PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
		ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
		pageContext.setAttribute("purchaseOrderVO", purchaseOrderVO);
		pageContext.setAttribute("productVO", productVO);
	}catch(Exception e){
		System.out.println("list-one-order-by-pono-buyer.jsp");
	}

%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>買家訂單詳情</title>
<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/jquery-2.2.4.min.js"></script>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/purchase-order/assets/css/responsive.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedy-list-one-order-by-pono-buyer.js"></script> --%>
</head>
<body>
	<%@ include file="../product/pages/header.file"%>
	<!-- cart content start -->
	<section class="cart-content-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-8">
					<div class="cart-title">
						<h2 style="font-family: Microsoft JhengHei; font-size: 30px; font-weight: 500;">買家訂單詳情</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-8">
					<div class="single-cart-list">
						<table class="table table-borderless">
							<tbody>
								<tr>
									<td>
										<p>訂單編號</p>
									</td>
									<!-- 訂單編號  -->
									<td>
										<p>${purchaseOrderVO.poNo}</p>
									</td>
								</tr>
								<tr>
									<td>
										<p class="font-semibold">商品名稱</p>
									</td>
									<!-- 商品名稱  -->
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
											<p class="font-semibold">

												<a href="#" style="color: inherit;" onclick=parent3FormSub(this)>
													<%--${productService.getOneProduct(purchaseOrderVO.getProductNo()).pName} --%>
													${productVO.pName}
												</a>
											</p>
											<input type="hidden" name="action" value="getOneForDisplay">
											<input type="hidden" class="productNo" name="productNo" value="${purchaseOrderVO.productNo}">
										</FORM>
										<!-- /商品名稱  -->
									</td>
								</tr>
								<tr>
									<td>
										<p>賣家帳號</p>
									</td>
									<td>
										<p>${productVO.memId}</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>訂購人姓名</p>
									</td>
									<td>
										<p>${purchaseOrderVO.buyerName }</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>訂購人電話</p>
									</td>
									<td>
										<p>${purchaseOrderVO.buyerPhone }</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>訂單總金額</p>
									</td>
									<!-- 訂單總金額  -->
									<td>
										<p>
											<fmt:formatNumber value="${productVO.pPrice * purchaseOrderVO.quantity}" pattern="0.#" />
											元
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>購買數量</p>
									</td>
									<!-- 購買數量  -->
									<td>
										<p>
											<fmt:formatNumber value="${purchaseOrderVO.quantity }" pattern="0.#" />
										</p>
									</td>


								</tr>
								<tr>
									<td style="width: 20%;">
										<p>訂單狀態</p>
									</td>
									<!-- 訂單狀態  -->
									<td>
										<p id="poStatus">${OrderStatusListener[purchaseOrderVO.poStatus]}</p>
										<input type="hidden" class="poStatus" value="${purchaseOrderVO.poStatus}">
									</td>

								</tr>
								<tr>
									<td>
										<p>訂購日期</p>
									</td>
									<td>
										<p>
											<fmt:formatDate value="${purchaseOrderVO.poTime}" pattern="yyyy-MM-dd" />
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>付款方式</p>
									</td>
									<td>
										<p>${poPaymentListener[purchaseOrderVO.poPayment]}</p>
									</td>
								</tr>
								<tr class="trForPayIt" style="display: none;">
									<td></td>
									<td style="padding-top: 0;">
										<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
											<input type="button" class="btn btn-info confirmModal" value="立即付款" name="offProductInputBtn" onclick=setOrderStatusForm(this);>
											<!-- 綠界須提交之... -->
											<input type="hidden" name="action" value="green-pay">
											<input type="hidden" name="amountStr" value="${productVO.pPrice * purchaseOrderVO.quantity}">
											<input type="hidden" name="pName" value="${productVO.pName}">
											<input type="hidden" class="poNo" name="poNo" value="${purchaseOrderVO.poNo}">

											<input type="hidden" class="poPayment" value="${purchaseOrderVO.poPayment}">
											<input type="hidden" class="showSuccess" value="${showSuccess}">

											<!-- 更改狀態 -->
											<!-- 											<input type="hidden" name="action" value="getOneForUpdateStatusBuyer"> -->
											<%-- 											<input type="hidden" class="poNo" name="poNo" value="${purchaseOrderVO.poNo}"> --%>
											<!-- 											<input type="hidden" name="changePoStatus" value="30"> -->
											<!-- 											<input type="hidden" name="listAllOrListOne" value="one"> -->

										</FORM>
									</td>
								</tr>
								<tr>
									<td>
										<p>配送方式</p>
									</td>
									<td>
										<p>
											${poDeliveryListener[purchaseOrderVO.poDelivery]}
											<input type="hidden" class="poDelivery" value="${purchaseOrderVO.poDelivery}">
										</p>
									</td>
								</tr>
								<tr class="trForDeliveryAddress" style="display: none;">
									<td>
										<p>地址</p>
									</td>
									<td>
										<p>${purchaseOrderVO.deliveryAddress}</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>給賣家的話</p>
									</td>
									<td>
										<p>${purchaseOrderVO.purchaseDetail}</p>
									</td>
								</tr>
								<tr class="trForButtonsBelow" style="display: none;">
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
											<input type="submit" class="btn btn-success" value="提出修改訂單" name="offProductInputBtn">
											<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
											<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											<input type="hidden" name="changePoStatus" value="712">
											<input type="hidden" name="listAllOrListOne" value="one">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
											<input type="submit" class="btn btn-danger" value="提出取消交易" name="offProductInputBtn">
											<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
											<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											<input type="hidden" name="changePoStatus" value="732">
											<input type="hidden" name="listAllOrListOne" value="one">
										</FORM>
									</td>

								</tr>

								<tr class="trButtonsBelowUpdate" style="display: none;">
									<td></td>
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
											<input type="submit" class="btn btn-warning" value="修改訂單" name="offProductInputBtn">
											<input type="hidden" name="action" value="getOneForUpdate">
											<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											<input type="hidden" name="" value="">
										</FORM>
									</td>

								</tr>
								<tr class="waitForSellerText" style="display: none;">
									<td></td>
									<td style="color: green;">--請等候賣家回應--</td>
								</tr>
								<tr class="finishTheOrderBtn" style="display: none;">
									<td></td>
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
											<input type="submit" class="btn btn-warning" value="完成訂單" name="offProductInputBtn">
											<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
											<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											<input type="hidden" name="changePoStatus" value="80">
											<input type="hidden" name="listAllOrListOne" value="one">
										</FORM>
									</td>
								</tr>
								<tr class="confirmCancelOrderBtn" style="display: none;">
									<td></td>
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
											<input type="submit" class="btn btn-warning" value="確認取消交易" name="offProductInputBtn">
											<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
											<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											<input type="hidden" name="changePoStatus" value="70">
											<input type="hidden" name="listAllOrListOne" value="one">
										</FORM>
									</td>
								</tr>
								<tr class="ratingBtn" style="display: none;">
									<td>
										<p style="color: red;">點擊星星給評</p>
									</td>
									<td>
										<p>
											<span class="rating ml-0">
												<i class="fa fa-star-o" id="star1"></i>
												<i class="fa fa-star-o" id="star2"></i>
												<i class="fa fa-star-o" id="star3"></i>
												<i class="fa fa-star-o" id="star4"></i>
												<i class="fa fa-star-o" id="star5"></i>
											</span>
										</p>
									</td>
								</tr>
								<tr class="commentText" style="display: none;">
									<td>
										<p style="color: red;">給予賣家評論</p>
									</td>
									<td>
										<textarea class="form-control" rows="7" id="productComment"></textarea>
										<input type="hidden" name="action" value="insertProductComment">
										<input type="hidden" class="productNo" value="${productVO.productNo}">
										<input type="hidden" class="memIdSeller" value="${purchaseOrderVO.memId}">
										<input type="hidden" class="memIdBuyer" value="${productVO.memId}">
										<input type="hidden" class="changePoStatus" value="${purchaseOrderVO.poStatus}">
										<input type="hidden" class="pRatingEach" name="pRatingEach" value="">
										
										<input type="hidden" name="listAllOrListOne" value="one">
									</td>
								</tr>
								<tr class="commentBtn" style="display: none;">
									<td></td>
									<td>
										<input type="submit" class="btn btn-success" value="送出評論" onclick=sendComment();>
									</td>
								</tr>
								<!------------------------ 一個商品-end ------------------------>
							</tbody>
						</table>
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
		<span class="back-top">
			<i class="fa fa-angle-up"></i>
		</span>
	</div>
	<!-- back to top area end -->

	<!-- jquery -->
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/jquery-2.2.4.min.js"></script>
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
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedy-list-one-order-by-pono-buyer.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/wedy-comfirmbox/js/jquery.confirmModal.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/sweetalert@2.1.2/js/sweetalert.min.js"></script>
</body>
</html>