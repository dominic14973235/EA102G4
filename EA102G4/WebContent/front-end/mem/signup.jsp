<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>


<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
	
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
	#hi_mem{
		font-family:Microsoft JhengHei;
	}
	@media screen and (max-width: 1400px) {
	  #hi_mem {
			display:none;
		}
	}
	
	@media only screen and (max-width: 1650px) and (min-width: 1519px){
		.sign-area .signup-content, .sign-area .sign-content {
    		padding: 0px 100px 0px 80px;		
    	}
	}
	input#f_date1 {
    	display: block;
    	width: 50%;
    	padding: .375rem .75rem;
    	font-size: 1rem;
    	line-height: 1.5;
    	color: #495057;
    	background-color: #fff;
    	background-clip: padding-box;
    	border: 1px solid #ced4da;
		border-radius: .25rem;
    	transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
	}
	
	select.country {
    	display: inline;
    	width:100%;
    	padding: .375rem .75rem;
    	font-size: 1rem;
    	line-height: 1.5;
    	color: #495057;
    	background-color: #fff;
    	background-clip: padding-box;
    	border: 1px solid #ced4da;
    	border-radius: .25rem;
    	transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    	margin-bottom:5px;
	}
	select.district {
    	display: inline;
    	width:100%;
    	padding: .375rem .75rem;
    	font-size: 1rem;
    	line-height: 1.5;
    	color: #495057;
    	background-color: #fff;
    	background-clip: padding-box;
    	border: 1px solid #ced4da;
    	border-radius: .25rem;
    	transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    	margin-bottom:5px;
	}
	input.zipcode {
    	display: inline;
    	width:100%;
    	padding: .375rem .75rem;
    	font-size: 1rem;
    	line-height: 1.5;
    	color: #495057;
    	background-color: #fff;
    	background-clip: padding-box;
    	border: 1px solid #ced4da;
    	border-radius: .25rem;
    	transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    	margin-bottom:5px;
	}
	
	ul {
    	list-style-type: none;
    	padding-inline-start: 0px;
	}
	.sign-content.text-center.h-100 {
    	padding-top: 50px;
    	padding-bottom: 20px;
    	BACKGROUND-COLOR: RGB(227,227,227);
	}
	#magical-button{
		margin-top:20px;
		margin-bottom:40px;
		margin-left:10px;
	}
	#join{
		
		margin-top:20px;
		margin-left:15px;
		
	}
	.sign-area .sign-content .form-group {
    margin-bottom: 15px;
}
img.w-100 {
    height: 110%;
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

	<!-- signup area start -->
	<section class="sign-area">
		<div class="container-fluid no-gutter">
			<div class="row no-gutter">
				<div class="col-xl-4 col-lg-6 offset-xl-2">
					<div class="sign-slider">
						<div class="thumb">
							<img class="w-100" src="assets/img/signin/05.jpg" alt="">
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
							<img class="w-100" src="assets/img/signin/01.jpg" alt="">
						</div>
					</div>
				</div>
				<div class="col-xl-6 col-lg-6 right-div">
					<div class="sign-content text-center h-100">
						<div class="thumb">
							<img src="assets/img/signin-logo_new.png" alt="">
						</div>

						<h3>Join our community!</h3>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<p>
							Already have an account? <a href="signin.jsp">Login</a>
						</p>
						<form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/mem/mem.do" name="form1">
							<div class="form-row">
								<div class="form-group col-md-12 text-left">
									<label for="mem_name">Name</label> <input type="text"
										class="form-control" id="mem_name" name = "mem_name" value="<%= (memVO==null)? "" : memVO.getMem_name()%>" placeholder="姓名" required>
								</div>
							</div>
							<div class="form-group text-left">
								<label for="mem_email">Email Address</label> <input
									type="email" class="form-control" id="mem_email" name="mem_email"
									value="<%= (memVO==null)? "" : memVO.getMem_email()%>" placeholder="santa.bevilard@gmail.com" required>
							</div>
							<div class="form-group text-left">
								<label>Birthday</label> <input class = "form-control col-md-3" name="mem_bir"
									id="mem_bir" type="text" required>
							</div>
							<div class="form-row">
								<div class="col-md-6 text-left" id = "gender_div">
									<%if(memVO == null || memVO.getSex().equals("男")){ %>
									<input type="radio" id="male" name="sex" value="男" checked>
									<label for="male">男</label>
									<input type="radio" id="female" name="sex" value="女">
									<label for="female">女</label><br>
									<%}else{ %>
									<input type="radio" id="male" name="sex" value="男"> 
									<label for="male">男</label>
									<input type="radio" id="female" name="sex" value="女" checked>
									<label for="female">女</label><br>
									<%} %>
								</div>
							</div>
							<div class="form-group text-left">
								<label for="mem_addr">Address</label> 
								<ul class="row memberForm">
      						<!-- Country -->
      								<li class="col-md-4">
          								<select class="country" name="country" required></select>
      								</li>
      						<!-- Country END -->

      						<!-- district -->
      								<li class="col-md-4">
          								<select class="district" name="district" required></select>
      								</li>
      						<!-- district END -->

      						<!-- zipcode -->
      								<li class="col-md-4">
          									<input type="text" class="zipcode" name="zipcode" value="" placeholder="郵遞區號" readonly />
      								</li>
      						<!-- zipcode END -->

      						<!-- Address -->
      								<li class="col-md-12">
         									<input type="text" class="form-control detail" id="address" name="address" value="" required>
      								</li>
      						<!-- Address END -->
    						</ul>
								
							</div>
							<div class="form-group text-left">
								<label for="mem_phone">Phone</label> <input type="text" class="form-control"
									id="mem_phone" name = "mem_phone" value="<%= (memVO==null)? "" : memVO.getMem_phone()%>" required>
							</div>
							<div class="row">
							<input type="hidden" name="action" value="insert">
							<input type="submit" id = "join" class="btn btn-success col-md-8  btn-block form-control" value="Join our community">
							<button id = "magical-button" class="col-md-3 form-control">Magical Button</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- signup area end -->
	
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

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date mem_bir = null;
  try {
	    mem_bir = memVO.getMem_bir();
   } catch (Exception e) {
	    mem_bir = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script src="assets/js/dk-tw-citySelector.js"></script>
<script src="assets/js/init-address.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  
  #gender_div{
  	color:black;
  	font-family: Microsoft JhengHei;
  }
</style>

<script>
	
	
	// 日期
	$.datetimepicker.setLocale('zh');
    $('#mem_bir').datetimepicker({
		theme: '',              //theme: 'dark',
		timepicker:false,       //timepicker:true,
		step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		format:'Y-m-d',         //format:'Y-m-d H:i:s',
		value: '<%=mem_bir%>' 
        });
	
	//神奇小按鈕
	$('#magical-button').click(function(e){
		e.preventDefault();
		$('#mem_name').val("大衛海鮮");
		$('#mem_email').val("ouyangyi0418@gmail.com");
		$('#mem_bir').val("1987-08-07");
		$('#mem_phone').val("0912345678");
		let memberForm = $('.memberForm');
		let address = '313新竹縣市尖石鄉新華街四段537巷47號86樓';
		initAddress(memberForm, address);
	});
	window.onload = function(){

		let memberForm = $('.memberForm');
		initAddress(memberForm, "<%=(memVO == null)?"":memVO.getMem_addr()%>");
	};
	
</script>
</html>