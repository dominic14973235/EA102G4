<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
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

	.errorMsgs{
	font-family: Microsoft JhengHei;
	color:red;
	}

	#hi_mem{
	font-family:Microsoft JhengHei;
	}
	@media screen and (max-width: 1400px) {
	  #hi_mem {
			display:none;
		}
	}
	.sign-content.signup-content.text-center.h-100 {
    BACKGROUND-COLOR: RGB(227,227,227);
}
img.w-100 {
    height: 200%;
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
	<!--  search Popup -->

	<%@ include file="../memInclude/navbar.file"%>

	<!-- signin area start -->
	<div class="sign-area">
		<div class="container-fluid no-gutter">
			<div class="row no-gutter">
				<div class="col-xl-6 col-md-6">
					<div class="sign-content signup-content text-center h-100">
						<div class="thumb">
							<img src="assets/img/signin-logo_new.png" alt="">
						</div>
						<h3>Login Your Account!</h3>
						<c:if test="${not empty errorMsgs}">
							<font class = "errorMsgs">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li class = "errorMsgs">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<p>
							Don't have an account? <a href="signup.jsp">Register</a>
						</p>
						
						<form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/mem/mem.do" name="form1">
							<div class="form-group text-left">
                                <label for="mem_email">Email Address </label>
                                <input type="email" class="form-control" id="mem_email" name = "mem_email" placeholder="santa.bevilard@gmail.com" required>
                            </div>
							<div class="form-group text-left">
								<label for="mem_psw">Password <span
									class="color-red password-alert check">(首次登入請至電子信箱收取密碼)</span> <i class="fa fa-smile-o color-red check"
									aria-hidden="true"></i></label> <input type="password"
									class="form-control" id="mem_psw" name="mem_psw" placeholder="******" required>
							</div>
							<input type="hidden" name="action" value="login"> 
							<input type="submit" class="btn btn-success btn-lg btn-block"
								value="Login">
						</form>
					</div>
				</div>
				<div class="col-xl-4 col-md-6">
					<div class="sign-slider">
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/01.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/02.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/03.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/04.jpg" alt="">
						</div>
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/05.jpg" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- signin area end -->

	
	<%@ include file="../footerFile/pageFooter.file"%>

	<!-- back to top area start -->
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
</body>

<script>
		var check1 = document.querySelector("span.check");
		var check2 = document.querySelector("i.check");
		var mem_psw = document.getElementById("mem_psw");
		mem_psw.addEventListener('input', function(){
			var mypsw = mem_psw.value.trim();
		
			if (mypsw == "" || mypsw.lenght == 0) {
				check1.style.display="inline";
			}else{
				check1.style.display="none";
			}
			if(/^[(a-zA-Z0-9)]{2,10}$/.test(mypsw)){
				check2.setAttribute("class", "fa fa-smile-o try");
				check2.setAttribute("color", "lightgreen");
            }else{
            	check2.setAttribute("class", "fa fa-smile-o color-red try");
            }
		});
		
		
		

</script>
</html>