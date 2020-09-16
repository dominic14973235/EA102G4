<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.coin.model.*"%>
<%@ page import="com.coin.model.*"%>
<%@ page import="java.text.*"%>

<%
// 	測試環境   不手動登入用
	MemService memSvc = new MemService();
// 	MemVO memVO=memSvc.getOneMem("MEM0000001"); 
// 	session.setAttribute("memVOLogin", memVO);
	
%>
<%
	
	CoinService coinSvc = new CoinService();
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
	List list = coinSvc.getMemCoinOrder(memVOLogin.getMem_id());
// 	List list = coinSvc.getMemCoinOrder("MEM0000001");
 	Collections.reverse(list);
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
<!-- favicon -->
<link rel=icon
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/img/favicon.png"
	sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/groupclass/assets/css/wedy.css">
	
 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
<style>
.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(images/gc.png);
    background-position: 97px -340px;
    background-size: 90%;
}

</style>
	
</head>
<body>

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

<%@ include file="../artInclude/navbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay">
		<div class="breadcrumb-inner">
			<h1 class="page-title">點數訂單歷史紀錄</h1>
			<ul class="page-list margin-bottom-0 margin-top-10">
				<li><a href="index.html">Home</a></li>
				<li></li>
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
						<h2>點數訂單歷史紀錄</h2>
					</div>
				</div>

			</div>
			
			<%@ include file="page1.file"%>
			<div class="row">
				<div class="col-lg-3" style="color:black">
				訂單編號
				</div>
				<div class="col-lg-2" style="color:black">
				消費金額
				</div>
				<div class="col-lg-4" style="color:black">
				訂單成立時間
				</div>
				<div class="col-lg-3" style="color:black">
				訂單狀態
				</div>
			</div>
			<c:forEach var="coinVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<br>
			<div class="row">
				<div class="col-lg-3" style="color:black">
				${coinVO.coin_id}
				</div>
				<div class="col-lg-2" style="color:black" >
				${coinVO.deposit_coin}
				</div>
				<div class="col-lg-4 date" style="color:black">
				${coinVO.coin_date}
				</div>
				<div class="col-lg-3" style="color:black">
				${coinVO.co_status eq 0? "未付款" : "付款成功"}
				</div>
			</div>
		
			</c:forEach>
			
			<div class="pagination-content">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div
						class="pagination-area d-flex justify-content-center margin-top-50">
						<%if(list.size()>0){%><ul>
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
						<%}%>
					</div>
				</div>
			</div>
		</div>
	</div>
			<div class="row">
				<div class="col-lg-5">
				
				</div>
				
			</div>
		</div>
	</section>
	<!-- cart content end -->
	
  <%@ include file="../footerFile/pageFooter.file"%>
 
	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->
	<!-- jquery -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/nice-select.js"></script>
	<!-- counter up -->
	
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/script.js"></script>

	<!-- rProgressbar -->
	 <%@ include file="/front-end/groupclass/cart.file"%>
	<script
		src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/wedy.js"></script>
		<script>
		$(".date").each(function(){
		var format = $(this).text().split('.');
		$(this).text(format[0]);
		});
		</script>


</body>
</html>