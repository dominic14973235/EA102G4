<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
%>
<%
// 	測試環境   不手動登入用
	MemService memSvc = new MemService();
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
	body{
	font-family:Microsoft JhengHei;
	}
	
	
.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(images/gc.png);
    background-position: 97px -340px;
    background-size: 90%;
}
	
	</style>
</head>
<body >

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

<%@ include file="../memInclude/navbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay">
		<div class="breadcrumb-inner">
			<h1 class="page-title">點數儲值</h1>
			<ul class="page-list margin-bottom-0 margin-top-10">
				<li><a href="index.html">Home</a></li>
				<li>
<!-- 				<a href="#">Product List</a> -->
				</li>
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
						<h2>儲值頁面</h2>
					</div>
				</div>

			</div>
<FORM METHOD="post" ACTION="coin.do" id="coin">
<input type="hidden" name="action" value="insert">            
			<div class="row">
				<div class="col-lg-5">
						<p style="color: black;float: right;" >儲值金額 :</p>
				</div>
				<div class="col-lg-5">
						<input type="number" id="amount" name="amount" min="1" max="30000" value="${coinVO!=null?'coinVO.amount':'100'}" style="
    width: 58%;
    height: 100%;
    float: left; "/>
    <p><c:if test="${not empty errorMsgs}">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
	</p>
				</div>
			</div>
              <br>
              <br>                                 
			<div class="row">
				<div class="col-lg-5">
				
				</div>
				<div class="col-lg-3">
					<div class="btn-wrapper">
						<button id="buyButton" 
							class="btn btn-block btn-element btn-lg-size btn-main-color btn-rounded" width="25px">儲值</button>
					</div>
				</div>
			</div>
		</div>
		</FORM>
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
	<script src="${pageContext.request.contextPath}/front-end/groupclass/assets/js/wedy.js"></script>
<!-- 	<script> -->




<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<script>
$("#buyButton").click(function(event){
	var amount = $('$amount').val();
	
	  event.preventDefault();
	  var flag = ${memVOLogin==null};
	  console.log(flag);
	  if(flag){
		  Swal.fire({
			  title: '請先登入',
			  text: "",
			  icon: 'warning',
			  showCancelButton: false,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '現在登入'
			}).then((result) => {
				if (result.isConfirmed) {
					 Swal.fire(
						      'Deleted!',
						      'Your file has been deleted.',
						      'success'
						    )
			  	var url = "<%=request.getContextPath()%>/front-end/mem/signin.jsp";
					console.log(url);
					var orderView = $(location).attr('href', url);
					var timeoutID = window.setTimeout( ( () => orderView ), 5000);
				}
				})
	  }else{
	Swal.fire({
		  title: '確定儲值?',
		  text: "儲值後將不退還點數!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '好!'
		}).then((result) => {
		  if (result.value) {
// 		    Swal.fire(
// 		      '訂單已生成!',
// 		      '2秒後將跳轉至綠界付款頁面',
// 		      'success'
// 		    )
		    
		    $("#coin").submit();
		  }
		})
	}
});
	</script>

</body>
</html>