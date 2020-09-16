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
	String g_order_no = request.getParameter("g_order_no");
	pageContext.setAttribute("g_order_no", g_order_no);
	MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
	MemService memSvc = new MemService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<%
// 	測試環境   不手動登入用
// 	memVO=memSvc.getOneMem("MEM0000002"); 
// 	session.setAttribute("memVOLogin", memVO);
%>
<% 
	ProService proSvc = new ProService();
	ProVO proVO = proSvc.getProByMem(memVO.getMem_id());
	System.out.println(proVO.getPro_ID());
	List<GroupClassVO> list =  gcSvc.getGroupClassesByProId(proVO.getPro_ID());
	pageContext.setAttribute("list", list);
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

</head>
<body onload="connectByGC();" onunload="disconnectByGC();">

<a href="#"><img src="assets/img/cart.png"></a>
<ul class="sub-menu">
<li><a href="/EA102G4/front-end/groupclass/groupClassSelectPage.jsp">GroupClass</a></li>
</ul>
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

	<%@ include file="../artInclude/navbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 cart-breadcrumb-overlay"
		style="background-image: url(assets/img/bg/shop.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Shop Cart List</h1>
			<ul class="page-list margin-bottom-0 margin-top-10">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Product List</a></li>
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
						<table class="table table-borderless">
							<tbody id="cartBody">
<%-- 							<c:forEach var="goVOTop" items="${goSvc.all}> --%>
								<tr>
								<td>團課編號</td>
								<td>團課名稱</td>
								<td>購買會員</td>
								<td>訂單成立日期</td>
								<td>一堂金額</td>
								</tr>
							<c:forEach var="gcVO" items="${list}">
								<c:forEach var="godVO" items="${godSvc.getAllByG_class_no(gcVO.g_class_no,1)}">
										<tr  class="order">
											<td><p>${gcVO.g_class_no}</p></td>
											<td><p>${gcVO.g.name}</p></td>
											<td><p>${goSvc.getOneGroupOrder(godVO.g_order_no).mem_id}</p></td>
											<td><p class="date">${goSvc.getOneGroupOrder(godVO.g_order_no).g_order_time}</p></td>
											<td><p class="money">${godVO.p_coin}
											</p></td>
										</tr>
										</c:forEach>
										<c:forEach var="godVO" items="${godSvc.getAllByG_class_no(gcVO.g_class_no,0)}">
										<tr  class="order">
											<td><p>${gcVO.g_class_no}</p></td>
											<td><p>${goSvc.getOneGroupOrder(godVO.g_order_no).mem_id}</p></td>
											<td><p class="date">${goSvc.getOneGroupOrder(godVO.g_order_no).g_order_time}</p></td>
											<td><p class="money">${godVO.p_coin}
											</p></td>
										</tr>
										</c:forEach>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-lg-3">
				
				</div>
				<div class="col-lg-9">
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
  
  <%@ include file="/front-end/groupclass/socket.file"%>
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
	$(".date").each(function(){
		var format = $(this).text().split('.');
		$(this).text(format[0]);
		});
	$('.hr').each(function(){
				var hr = $(this).text();
				$(this).text(hr.indexOf(1)+'時');
				
			});
	var total=0;
	$('.money').each(function(){
		var money=$(this).text();
		total+=parseInt(money);
	});
	$('.float-right').text(total);
	</script>
</html>