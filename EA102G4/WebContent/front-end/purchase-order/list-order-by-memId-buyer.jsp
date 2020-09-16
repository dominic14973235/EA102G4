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
		PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
		List<PurchaseOrderVO> list = purchaseOrderService.getOrderByMemId(memId); 
		pageContext.setAttribute("list", list);
%>
<jsp:useBean id="photoService" scope="page"
	class="com.product_photo.model.PhotoService" />
<jsp:useBean id="productService" scope="page" class="com.product.model.ProductService" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>買家商品管理</title>
<!-- favicon -->
<link rel=icon href=${pageContext.request.contextPath}/front-end/purchase-order/assets/img/favicon.png sizes="20x20"
	type="image/png">
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
</head>
<body>
	<%@ include file="../product/pages/header.file"%>
	<!-- cart content start -->
	<section class="cart-content-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-title">
						<h2 style="font-family:Microsoft JhengHei;font-size: 30px;font-weight: 500;">買家訂單管理</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="single-cart-list">
						<table class="table table-borderless">
							<tbody>
								<tr>
									<td style="width: 10%;"><p>訂單編號</p></td>
									<td style="width: 35%;"><p class="font-semibold">商品名稱</p></td>
									<td style="width: 10%;"><p>訂單總金額</p></td>
									<td style="width: 10%;"><p>購買數量</p></td>
									<td style="width: 10%;"><p>訂單狀態</p></td>
									<td style="width: 10%;"></td>
									<td style="width: 10%;"></td>
								</tr>

								<!------------------------ 一個商品-start ------------------------>
								<%@ include file="../purchase-order/pages/page1.file"%>
								<c:forEach var="purchaseOrderVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<td>
<%-- 											<img src="data:image/jpg;base64,${photoService.get(purchaseOrderVO.getProductNo()).base64Image}" style="max-width: 122px; max-height: 122px" /> --%>
										<p>${purchaseOrderVO.poNo}</p>
										</td>
											<td style="">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
											<p class="font-semibold">
											
												<a href="#" style="color:inherit;" onclick=parent3FormSub(this)>${productService.getOneProduct(purchaseOrderVO.getProductNo()).pName}
												</a>
											</p>
											<input type="hidden" name="action" value="getOneForDisplay">
											<input type="hidden" name="productNo" value="${purchaseOrderVO.productNo}">
										</FORM>
											</td>
										<td><p><fmt:formatNumber value="${productService.getOneProduct(purchaseOrderVO.getProductNo()).pPrice * purchaseOrderVO.quantity}" pattern="0.#" />元</p></td>
										<td><p><fmt:formatNumber value="${purchaseOrderVO.quantity }" pattern="0.#" /></p></td>
										<td><p>${OrderStatusListener[purchaseOrderVO.poStatus]}</p></td>
										
										
										<td>
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
												<input type="submit" class="btn btn-success" value="查看訂單詳情" name="updateProductInputBtn">
												<input type="hidden" name="action" value="get-one-order-for-display-buyer">
												<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											</FORM>
										</td>
										<td class="tdForUpdateOrderBtn" style="display:none;">
											<input type="hidden" class="poStatus" value="${purchaseOrderVO.poStatus}">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
												<input type="submit" class="btn btn-danger" value="提出修改訂單" name="offProductInputBtn">
												<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
												<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
												<input type="hidden" name="changePoStatus" value="712">
												<input type="hidden" name="listAllOrListOne" value="all">
											</FORM>
										</td>
										<td class="tdForUpdateBtn" style="display:none;">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
												<input type="submit" class="btn btn-warning" value="修改訂單" name="offProductInputBtn">
												<input type="hidden" name="action" value="getOneForUpdate">
												<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
											</FORM>
										</td>
										<td class="waitForSellerText" style="color:green;display:none;">
											-等候賣家回應-
										</td>
										<td class="finishTheOrderBtn" style="display:none;">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
												<input type="submit" class="btn btn-warning" value="完成訂單" name="offProductInputBtn">
												<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
												<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
												<input type="hidden" name="changePoStatus" value="80">
												<input type="hidden" name="listAllOrListOne" value="all">
											</FORM>
										</td>
										<td class="ConfirmCancelTheOrderBtn" style="display:none;">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/purchase_order/purchaseOrder.do">
												<input type="submit" class="btn btn-warning" value="確認取消交易" name="offProductInputBtn">
												<input type="hidden" name="action" value="getOneForUpdateStatusBuyer">
												<input type="hidden" name="poNo" value="${purchaseOrderVO.poNo}">
												<input type="hidden" name="changePoStatus" value="70">
												<input type="hidden" name="listAllOrListOne" value="all">
											</FORM>
										</td>
									</tr>
								</c:forEach>
								<!------------------------ 一個商品-end ------------------------>
							</tbody>
						</table>
								<%@ include file="../purchase-order/pages/page2.file"%>
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
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedy-list-order-by-memId.js"></script>
	<script src="${pageContext.request.contextPath}/front-end/purchase-order/assets/js/wedy-list-order-by-memId-buyer.js"></script>
</body>
</html>
<%}%>