<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.godetail.model.*"%>
<%@ page import="com.grouporder.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pro.model.*"%>
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	GroupClassService gcSvc = new GroupClassService();
	pageContext.setAttribute("gcSvc", gcSvc);
	GroupOrderService goSvc = new GroupOrderService();
	pageContext.setAttribute("goSvc", goSvc);
	GroupOrderDetailService godSvc = new GroupOrderDetailService();
	pageContext.setAttribute("godSvc", godSvc);
	MemService memSvc = new MemService();
	pageContext.setAttribute("memSvc", memSvc);
	ProService proSvc = new ProService();
	pageContext.setAttribute("proSvc", proSvc);
	MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
%>
<%
// 	測試環境   不手動登入用
// 	memVO=memSvc.getOneMem("MEM0000001"); 
// 	session.setAttribute("memVOLogin", memVO);
%>
<!DOCTYPE html>
<html lang="en">
<head>
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
.cart-total-list .cart-price-total {
    border-top: 0px solid #161616;
}
</style>

</head>
<body onload="connectByGC();" onunload="disconnectByGC();">

<!-- 	preloader area start -->
	<div class="preloader" id="preloader">
		<div class="preloader-inner">
			<div class="spinner">
				<div class="dot1"></div>
				<div class="dot2"></div>
			</div>
		</div>
	</div>
<!-- 	preloader area end -->

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
	<div class="breadcrumb-style-1 cart-breadcrumb-overlay"
		style="background-image: url(img/gcselect.jpg);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">您的團課訂單</h1>
			<ul class="page-list margin-bottom-0 margin-top-10">
				<li><a href="index.html">Home</a></li>
<!-- 				<li><a href="#">Product List</a></li> -->
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- cart content start -->
	<section class="cart-content-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-title">
						<h2>訂單總覽</h2>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="single-cart-list">
						<table class="table table-borderless" style="width:100%;">
							<tbody id="cartBody">
<%-- 							<c:forEach var="goVOTop" items="${goSvc.all}> --%>
								<tr>
								<td style="width:15%;">訂單編號</td>
								<td style="width:10%;"></td>
								<td width="25%">訂單成立日期</td>
								<td colspan="3" width="53%">消費金額</td>
								</tr>
								<c:forEach var="goVO" items="${goSvc.getAllByMem_id(memVOLogin.mem_id)}">
									<tr  class="order" >
										<td ><a href="<%=request.getContextPath()%>/front-end/groupclass/listOneOrder.jsp?g_order_no=${goVO.g_order_no}">${goVO.g_order_no}</a></td>
											<td></td>
										<td><p class="date">${goVO.g_order_time}</p></td>
										<td><p class="money">
										<c:set var="total" value="${0}"/>
										<c:forEach var="godVO" items="${godSvc.getDetailsByOrder(goVO.g_order_no,0)}"><c:set var="total" value="${total+godVO.p_coin}"/>
										</c:forEach>
										<c:forEach var="godVO" items="${godSvc.getDetailsByOrder(goVO.g_order_no,1)}"><c:set var="total" value="${total+godVO.p_coin}"/>
										</c:forEach>
										<c:forEach var="godVO" items="${godSvc.getDetailsByOrder(goVO.g_order_no,2)}"><c:set var="total" value="${total+godVO.p_coin}"/>
										</c:forEach>
										<c:out value="${total}"/></p></td>
										<td><button class="detailBtn" >明細</button>
										<input type="hidden" class="g_order_no"  value="${goVO.g_order_no}"></td>
										
									</tr>
							<tr class="${goVO.g_order_no} buyCart " >
<!-- 								<td class="font-semibold">團課圖片</td> -->
								<td class="font-semibold">團課名稱</td>
								<td class="font-semibold"  width="10%">教練</td>
								<td class="font-semibold">上課日期</td>
								<td class="font-semibold" width="20%">上課時間</td>
								<td class="font-semibold" width="17%">消費金額</td>
								<td class="font-semibold">到課狀態</td>
							</tr>
									<c:forEach var="godVO" items="${godSvc.getDetailsByOrder(goVO.g_order_no,0)}"><c:set var="total" value="${total+godVO.p_coin}"/>
										<tr class="orderDetail buyCart ${goVO.g_order_no}" style="background-color: #cfe6b0;">
<!-- 											<td ><img -->
<%-- 											src="<%=request.getContextPath()+"/front-end/groupclass/groupClass.do?g_class_no="%>${godVO.g_class_no}" --%>
<!-- 											alt="" max-width="70%" max-height="70%"></td> -->
											<td><a href="<%=request.getContextPath()%>/front-end/groupclass/groupClass.do?action=getOne_For_Display&g_class_no=${godVO.g_class_no}" color="A9FDFD">${gcSvc.getOneGroupClass(godVO.g_class_no).g_name}</a></td>
																				<td style="width:15%;"> ${memSvc.getOneMem(proSvc.getOnePro(gcSvc.getOneGroupClass(godVO.g_class_no).pro_id).mem_ID).mem_name}</td>
																					<td  style="width:15%;"><p>${godVO.rdate}</p></td>
											<td><p class="hr font-semibold">${godVO.hr}</p></td>
											<td><p class="font-semibold">${godVO.p_coin}</p></td>
											<td><p class="font-semibold gostatus">${godVO.go_status}</p></td>
										</tr>
										</c:forEach>
										<c:forEach var="godVO" items="${godSvc.getDetailsByOrder(goVO.g_order_no,1)}"><c:set var="total" value="${total+godVO.p_coin}"/>
										<tr class="orderDetail buyCart ${goVO.g_order_no}" style="background-color: #cfe6b0;">
<!-- 											<td ><img -->
<%-- 											src="<%=request.getContextPath()+"/front-end/groupclass/groupClass.do?g_class_no="%>${godVO.g_class_no}" --%>
<!-- 											alt="" max-width="70%" max-height="70%"></td> -->
											<td><a href="<%=request.getContextPath()%>/front-end/groupclass/groupClass.do?action=getOne_For_Display&g_class_no=${godVO.g_class_no}" color="A9FDFD">${gcSvc.getOneGroupClass(godVO.g_class_no).g_name}</a></td>
																				<td style="width:15%;"> ${memSvc.getOneMem(proSvc.getOnePro(gcSvc.getOneGroupClass(godVO.g_class_no).pro_id).mem_ID).mem_name}</td>
																					<td  style="width:15%;"><p>${godVO.rdate}</p></td>
											<td><p class="hr font-semibold">${godVO.hr}</p></td>
											<td><p class="font-semibold">${godVO.p_coin}</p></td>
											<td><p class="font-semibold gostatus">${godVO.go_status}</p></td>
										</tr>
										</c:forEach>
										<c:forEach var="godVO" items="${godSvc.getDetailsByOrder(goVO.g_order_no,2)}"><c:set var="total" value="${total+godVO.p_coin}"/>
										<tr class="orderDetail buyCart ${goVO.g_order_no}" style="background-color: #cfe6b0;">
<!-- 											<td ><img -->
<%-- 											src="<%=request.getContextPath()+"/front-end/groupclass/groupClass.do?g_class_no="%>${godVO.g_class_no}" --%>
<!-- 											alt="" max-width="70%" max-height="70%"></td> -->
											<td><a href="<%=request.getContextPath()%>/front-end/groupclass/groupClass.do?action=getOne_For_Display&g_class_no=${godVO.g_class_no}" color="A9FDFD">${gcSvc.getOneGroupClass(godVO.g_class_no).g_name}</a></td>
																				<td style="width:15%;"> ${memSvc.getOneMem(proSvc.getOnePro(gcSvc.getOneGroupClass(godVO.g_class_no).pro_id).mem_ID).mem_name}</td>
																					<td  style="width:15%;"><p>${godVO.rdate}</p></td>
											<td><p class="hr font-semibold">${godVO.hr}</p></td>
											<td><p class="font-semibold">${godVO.p_coin}</p></td>
											<td><p class="font-semibold gostatus">${godVO.go_status}</p></td>
										</tr>
										</c:forEach>
								</c:forEach>
<%-- 							</c:forEach> --%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12" style="border-top: 1px solid #161616;">
					<div class="cart-total-list">
						<div class="cart-price-total">
							<p style="padding-left: 35%;">
							
								 <span class="float-right text-right"> 
								
								</span>
							</p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</section>
	<!-- cart content end -->

	  <%@ include file="../footerFile/pageFooter.file"%>
	  
		<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
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
	<script>
	$(".buyCart ").hide();
	$(".detailBtn").click(function(e){
		e.preventDefault();
		var g_order_no =$(this).siblings().filter(".g_order_no").val();
		$("."+g_order_no).toggle(800);
	})
	$(".date").each(function(){
		var format = $(this).text().split('.');
		$(this).text(format[0]);
		});
	$('.hr').each(function(){
				var hr = $(this).text();
				var time = hr.indexOf(1);
				$(this).text(time+'時');
				
			});
	$('.gostatus').each(function(){
		var gostatus  = $(this).text();
		gostatus=parseInt(gostatus);
		if(gostatus===1)
			$(this).text('已上課');
		else if (gostatus===0)
			$(this).text('未上課');
		else
			$(this).text('已取消');
	});
	var total=0;
	$('.money').each(function(){
		var money=$(this).text();
		total+=parseInt(money);
	});
	$('.float-right').text(total);
	</script>
	  <%@ include file="/front-end/groupclass/socket.file"%>
</html>