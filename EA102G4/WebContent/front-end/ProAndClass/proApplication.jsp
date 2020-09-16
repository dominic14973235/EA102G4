<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.license.model.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="classTypeSvc" scope="page"
	class="com.classType.model.ClassTypeService" />
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
%>


<head>

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

<title>專業資格申請</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table td, table th {
	border: 1px solid #ddd;
	padding: 8px;
}

@font-face {
	font-family: 'Material Icons';
	font-style: normal;
	font-weight: 400;
	src: url(https://example.com/MaterialIcons-Regular.eot);
	/* For IE6-8 */
	src: local('Material Icons'), local('MaterialIcons-Regular'),
		url(https://example.com/MaterialIcons-Regular.woff2) format('woff2'),
		url(https://example.com/MaterialIcons-Regular.woff) format('woff'),
		url(https://example.com/MaterialIcons-Regular.ttf) format('truetype');
}

.material-icons {
	font-family: 'Material Icons';
	font-weight: normal;
	font-style: normal;
	font-size: 24px; /* Preferred icon size */
	display: inline-block;
	line-height: 1;
	text-transform: none;
	letter-spacing: normal;
	word-wrap: normal;
	white-space: nowrap;
	direction: ltr;
	/* Support for all WebKit browsers. */
	-webkit-font-smoothing: antialiased;
	/* Support for Safari and Chrome. */
	text-rendering: optimizeLegibility;
}

.intro {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 2;
}

.nice-select {
	color: black;
}

.list::-webkit-scrollbar {
	width: 8px;
	background-color: transparent;
}

.list::-webkit-scrollbar-thumb {
	border-radius: 10px;
	background-color: #ececec;
}

.list::-webkit-scrollbar-track {
	border-radius: 10px;
	background-color: transparent;
}

.nice-select .list {
	height: 40px;
	overflow: auto;
	text-align: center;
}

.nice-select {
	text-align: center;
}

.list-group-item.active {
	color: black;
	background-color: white;
	border-color: #e8e8e8;
}

#hi_mem {
	font-family: Microsoft JhengHei;
}

@media screen and (max-width: 1400px) {
	#hi_mem {
		display: none;
	}
}

#logout {
	border-style: none;
	background: none;
	transition: all .3s;
	color: rgba(255, 255, 255, 0.5);
}

#logout:hover {
	text-decoration: none;
	outline: none;
	color: inherit;
}

#smallImg{
	background-repeat: no-repeat;
	background-position: center;
	height: 366.125px;
	background-size: cover;
}

#bigImg{
	background-repeat: no-repeat;
	background-position: center;
	height: 435.434px;
	background-size: cover;
}

.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(assets/img/1244995.jpg);
    background-position: 97px -340px;
    background-size: 90%;
}

span.groStart {
    position: relative;
    top: -5px;
}

span.typeName {
    position: relative;
    top: -5px;
    font-family:microsoft JhengHei;
}

sub {
    font-family: microsoft JhengHei;
    color: #BCBCBC;
    font-size: 15px;
}

.jq-toast-single { 
    font-size: 20px; 
    font-family: microsoft JhengHei !important; 
    line-height: 25px; 
    width:400px;
} 

h2.jq-toast-heading {
	font-size: 30px; 
}

.img-part{
	width:134px;
	height:122px;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}

.share-img-item .content-part {
    padding-top: 0px;
}

.col-lg-4 {
    z-index: 3;
}

#myForm table tr,
#myForm table td{
	color: black;
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

	
<!-- navbar start -->
	<div class="zarxio-navbar sticky-top">
		<nav class="navbar navbar-area navbar-expand-lg black-bg nav-style-02">
			<div class="container nav-container">
				<div class="responsive-mobile-menu">
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#billatrail_main_menu" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span> <span class="bar1"></span>
						<span class="bar2"></span> <span class="bar3"></span>
					</button>
				</div>
				<div class="logo">
					<a href="<%=request.getContextPath()%>/front-end/index.jsp"> <img src="<%=request.getContextPath()%>/front-end/gpt/assets/img/logo_new.png" alt="logo" style="width: 250px;"></a>
				</div>
				<div class="collapse navbar-collapse" id="billatrail_main_menu">
					<ul class="navbar-nav menu-open">
						<li class="menu-item-has-children current-menu-item">
                            <a href="#">Coach&Class</a>
					       <ul class="sub-menu">
							     <li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/classes.jsp">PrivateClass</a></li>
							       	<li><a href="${request.getContextPath() }/EA102G4/front-end/groupclass/groupClassSelectPage.jsp">GroupClass</a></li>
							       <li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/pro.jsp">Trainer</a></li>
							   <c:if test="${memVOLogin != null }">    
							       <li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/proApplication.jsp">TrainerApplication</a></li>
							       <li><a href="${request.getContextPath() }/EA102G4/front-end/groupclass/listMemOrder.jsp">Check My Order</a></li>
							       <c:if test="${proVOLogin != null }">
							       		<li><a href="${request.getContextPath() }/EA102G4/front-end/ProAndClass/myClass.jsp">TrainerBack-end</a></li>
							       </c:if>
						       </c:if>
			        		</ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">InGymStore</a>
                            <ul class="sub-menu">
                                <li><a href="${pageContext.request.contextPath}/front-end/product/list-all-product.jsp">Shop</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/product/add-product.jsp">Sell Something</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/product/list-product-by-memId.jsp">Your Product</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/purchase-order/list-order-by-memId-seller.jsp">Seller Order</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/purchase-order/list-order-by-memId-buyer.jsp">Buyer Order</a></li>
                                <li><a href="${pageContext.request.contextPath}/front-end/product-tracking/list-all-tracking.jsp">Tracking Product</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children"><a href="#">Activity</a>
							<ul class="sub-menu">
								<li><a href="<%=request.getContextPath()%>/front-end/gpt/listAllGroup.jsp">Activity</a></li>
								<li style='display:${memVOLogin == null ?"none":""};'><a href="<%=request.getContextPath()%>/front-end/gpt/group.do?action=viewMyGroup&mem_id=${memVOLogin.mem_id }">My
										Activity</a></li>
								<li style='display:${memVOLogin == null ?"none":""};'><a href="<%=request.getContextPath()%>/front-end/gpt/groupAdd.jsp">Create Activity</a></li>
							</ul>
						</li>
                        <li class="menu-item-has-children ">
                            <a href="#">Article</a>
                            <ul class="sub-menu">
                                <li><a href="/EA102G4/front-end/article/listAllArticle.jsp">Article</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Member</a>
                            <ul class="sub-menu">
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Personal Information</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/bodyrecord/bodyRecord.jsp">MyBodyData</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp">MySportData</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/foodrecord/foodRecord.jsp">MyFoodRecord</a></li>
                                <%if (memVOLogin != null) {%>
                                <li><a href="<%=request.getContextPath()%>/front-end/mem/changeMyPassword.jsp">ChangeMyPassword</a></li>
                                 <li><a href="<%=request.getContextPath()%>/front-end/coin/addCoinOrder.jsp">Point Charge</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/coin/listMemCoinOrder.jsp">Credit History</a></li>
                                <%}%>
                            </ul>
                        </li>
						<%
							if (memVOLogin != null) {
						%>
						<li>
						<a href ="<%=request.getContextPath()%>/front-end/mem/mem.do?action=logout">Logout</a>
						</li>
						<%
							} else {
						%>
						<li><a href="<%=request.getContextPath()%>/front-end/mem/signin.jsp">Login</a></li>
						<%
							}
						%>
					</ul>
				</div>
				<%
					if (memVOLogin != null) {
				%>
				<a style="color:white;" id="hi_mem" href="<%=request.getContextPath()%>/front-end/mem/memDetail.jsp">Welcome!<%=memVOLogin.getMem_name()%></a>
				<%
					}
				%>
				</ul>
				<div class="nav-right-part">
					<ul>
						<li style="color:white;" class="search" id="search"><a href="#"><i class="fa fa-search"></i></a></li>
						<li>
							<div class="hamberger-area d-none d-lg-block">
								<div class="menu-toggle hamburger hamburger--squeeze is-active">
									<div class="hamburger-box">
										<div class="hamburger-inner"></div>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<!-- navbar end -->

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay">
<!-- 			style="background-image: url(assets/img/bg/classes.png); -->
		<div class="breadcrumb-inner">
			<h1 class="page-title">MyClasses 教練後台</h1>
			<ul class="page-list margin-bottom-2">
				<li><a href="index.html">Home</a></li>
				<li><a href="#">Classes</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->


	<p align="center">資格申請</p>
	<form id="myForm" name="myForm" action="pro.do" method="POST" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td>姓名</td>
				<td><input type="hidden" name="mem_id" value="${ memVOLogin.mem_id}">${ memVOLogin.mem_name}
				</td>
			</tr>

			<tr>
				<td>課程類別</td>
				<td><c:forEach var="ClassTypeVO" items="${classTypeSvc.all}">
						<label> <input type="checkbox" name="c_type_no"
							value="${ClassTypeVO.c_type_no}">${ClassTypeVO.t_name}
						</label>

					</c:forEach></td>
			</tr>

			<tr>
				<td>證照名稱</td>
				<td><input type="text" name="lic_name" class="lic_name" value=""> 證照編碼 
				<input type="text" class="no_reg" name="no_reg"> <input type="file" name="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
			
			<tr>
				<td>證照名稱</td>
				<td><input type="text" name="lic_name" class="lic_name" value=""> 證照編碼 
				<input type="text" class="no_reg" name="no_reg"> <input type="file" name="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
			
			<tr>
				<td>證照名稱</td>
				<td><input type="text" name="lic_name" class="lic_name" value=""> 證照編碼 
				<input type="text" id="no_reg" class="no_reg" name="no_reg"> <input type="file" name="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
			

			<tr>
				<td>經歷</td>
				<td><textarea cols="100" rows="10" name="expr" id="expr" placeholder="請輸入經歷...">
					</textarea>
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="action" value="insert"> 
					<input type="submit" value="送出">
					<button type="button" id="magic">神奇小按鈕</button>
				</td>
			</tr>

		</table>

	</form>
	  <%@ include file="../footerFile/pageFooter.file"%>
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
	
	$('#magic').click(function(){
		event.preventDefault();
		$('.lic_name').eq(0).val("AASFP私人教練指導").text("AASFP私人教練指導");
		$('.lic_name').eq(1).val("YMCA體適能").text("YMCA體適能");
		$('.lic_name').eq(2).val("MET轉式").text("MET轉式");
		
		$('.no_reg').eq(0).val("AASFP1234").text("AASFP1234");
		$('.no_reg').eq(1).val("YMCA05567").text("YMCA05567");
		$('.no_reg').eq(2).val("MET0987").text("MET0987");
		$('#expr').val("我是個會JAVA的男人 台灣人稱 最軟Q的筋骨").text("我是個會JAVA的男人 台灣人稱 最軟Q的筋骨");
	})
	</script>
	
	
</body>
</html>