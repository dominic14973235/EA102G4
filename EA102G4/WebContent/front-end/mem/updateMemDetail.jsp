<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	if(memVO == null){
		memVO = memVOLogin;
	}
	if (memVOLogin == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	} else {
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
	#insert-div{
		margin:auto auto;
	}
	#mem_bir-div{
		margin:0 0 0 0;
		display:inline;
	}
	#sex-div{
		display:inline;
		color: #999999;
    	width: 100%;
    	font-size: 15px;
	}
	.sex-margin{
		margin-right:10px;
	}
	
	.sign-area .sign-content .form-group {
    	margin-bottom: 0;
	}
	
	#input-submit,#input-cancel{
		margin-top:20px;
	}
	#div-submit{
		margin-top:10%
	}
	
	#mem_resume{
		height:100%;
	}
	.sign-area .sign-content {
    background: rgb(227 227 227);
    padding: 50px 50px 50px 50px;
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
	
	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/service.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Update Personal Information</h1>
		</div>
	</div>
	<!-- breadcrumb area end -->
	
	<!-- signup area start -->
	<section class="sign-area">
        <div class="container-fluid no-gutter">
            <div class="row no-gutter">
                <div class="col-xl-6 col-lg-6" id="insert-div">
                    <div class="sign-content text-center h-100">
                        <div class="thumb">
                            <img src="assets/img/signin-logo_new.png" alt="">
                        </div>
                        <%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
                        <h3 class="text-left"><%=memVOLogin.getMem_name() %></h3>
                        <form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/mem/mem.do" name="form1">
                            <div class="form-row">
                            
<!--                            會員編號 -->
                            	<div class="form-group">
                                    <input type="hidden" name="mem_id" size="30" value="<%=memVOLogin.getMem_id()%>" />
                                </div>
                                
<!--                            會員姓名 -->
                                <div class="form-group col-md-7 text-left">
                                    <label for="body_date">Name</label>
                                    <input type="text" class="form-control" name="mem_name" id="mem_name" value="<%=memVO.getMem_name()%>" required><br>
                                </div>
                                
<!--                            會員密碼 -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_psw" size="30" value="<%=memVOLogin.getMem_psw()%>" />
                                </div>
                                
<!--                            會員生日 -->
                                <div class="form-group col-md-7 text-left">
								<label>Birthday</label> <input type="text" class="form-control" name="mem_bir" id="mem_bir" required><br>
								</div>
								
<!--                            會員性別 -->
                                <div class="col-md-7 text-left" id="sex-div">
                                    <label class="sex-margin">Gender</label>
                                    <%if(memVO.getSex().equals("男")){ %>
                                    <input type="radio" id="male" class="sex-margin" name="sex" value="男" checked> <label for="male" class="sex-margin">男</label>
                                    <input type="radio" id="female" class="sex-margin" name="sex" value="女"> <label class="sex-margin" for="female">女</label><br>
									<%}else{ %>
									<input type="radio" id="male" class="sex-margin" name="sex" value="男"><label class="sex-margin" for="male">男</label>
									<input type="radio" id="female" class="sex-margin" name="sex" value="女" checked><label class="sex-margin" for="female">女</label><br>
									<%} %>
                                </div>
                                
<!--                            會員地址 -->
                                <div class="form-group text-left">
								<label for="mem_addr">Address</label> 
								<ul class="row memberForm">
      						<!-- Country -->
      								<li class="col-md-3">
          								<select class="country" name="country" required></select>
      								</li>
      						<!-- Country END -->

      						<!-- district -->
      								<li class="col-md-3">
          								<select class="district" name="district" required></select>
      								</li>
      						<!-- district END -->

      						<!-- zipcode -->
      								<li class="col-md-3">
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
                                
<!--                            會員信箱(帳號) -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_email" size="30" value="<%=memVOLogin.getMem_email()%>" />
                                </div>
                                
<!--                            會員電話 -->
                                <div class="form-group col-md-6 text-left">
                                    <label for="mem_phone">Phone</label>
                                    <input type="text" class="form-control" id="mem_phone" name="mem_phone" value="<%=memVO.getMem_phone()%>" required>
                                </div>
                                
<!--                            會員請假次數 -->
                                <div class="form-group">
                                    <input type="hidden" name="mem_absent" size="30" value="<%=memVOLogin.getMem_absent()%>" />
                                </div>
                                
<!--                            會員點數 -->
                                <div class="form-group">
                                    <input type="hidden" name="coin" size="30" value="<%=memVOLogin.getCoin()%>" />
                                </div>
                                
<!--                            會員自我介紹 -->
                                <div class="form-group col-md-10 text-left">
                                    <label for="mem_resume">Introduction</label>
                                    <textarea style="resize:none" rows="10" cols="12" class="form-control" id="mem_resume" name="mem_resume"><%=memVO.getMem_resume()%></textarea>
                                    
                                </div>
                                
<!--                            註冊時間 -->
                                <div class="form-group">
                                    <input type="hidden" name="m_reg_date" size="30" value="<%=memVOLogin.getM_reg_date()%>" />
                                </div>
                                
<!--                            賣家權限 -->
                                <div class="form-group">
                                    <input type="hidden" name="sel_auth" size="30" value="<%=memVOLogin.getSel_auth()%>" />
                                </div>
                                
<!--                            發文權限 -->
                                <div class="form-group">
                                    <input type="hidden" name="art_auth" size="30" value="<%=memVOLogin.getArt_auth()%>" />
                                </div>
                                
<!--                            留言權限 -->
                                <div class="form-group">
                                    <input type="hidden" name="com_auth" size="30" value="<%=memVOLogin.getCom_auth()%>" />
                                </div>
                            </div>
                            <div class="form-group" id ="div-submit">
                            <input type="hidden" name="action" value="update"> 
                            <input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                            <input type="submit" id ="input-submit" class="btn btn-dark" value="Submit">
                            <input type="button" id ="input-cancel" class="btn btn-dark" value="Cancel">
                            
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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="assets/js/dk-tw-citySelector.js"></script>
<script src="assets/js/init-address.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#mem_bir').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: "<%=mem_bir%>"
		});  
        
        window.onload = function(){

    		let memberForm = $('.memberForm');
    		initAddress(memberForm, "<%=(memVO == null)?"":memVO.getMem_addr()%>");
    	};
</script>
<script>
    	var cancel = document.getElementById("input-cancel");
    	
    	cancel.addEventListener("click", function(){
    		window.location.href='<%=request.getContextPath()%>/front-end/mem/memDetail.jsp';
    	});
    
    </script>
</html>
<%}%>