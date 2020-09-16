<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.sports.model.*"%>
<%

MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");

String record_no = request.getParameter("record_no");
SportRecordService sportRecordSvc = new SportRecordService();
SportRecordVO sportRecordVO = sportRecordSvc.getOneSportRecordByRecordNo(record_no);

if (sportRecordVO == null) {
	sportRecordVO = (SportRecordVO) request.getAttribute("sportRecordVO"); //SportRecordServlet.java (Concroller) 存入req的sportRecordVO物件 (包括幫忙取出的sportRecordVO, 也包括輸入資料錯誤時的sportRecordVO物件)
}
pageContext.setAttribute("sportRecordVO", sportRecordVO);

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

<style>

section {
	margin-top: 20px;
}
.sign-content.text-center.h-100 {
    margin: auto auto;
}
.sign-area .sign-content .form-group {
    margin-bottom: 10px;
    
    }
.form-row{
	display:inline;
}

.sign-area .sign-content {
    padding: 100px 80px 100px 80px;
}
.form-group{
	height:50px;
}
#select-div{
	margin-bottom: 10px;
}

</style>

</head>
<body>

	<!--  trainer start -->
	<section class="sign-area">
		<div class="container-fluid no-gutter">
			<div class="row no-gutter">
					<div class="sign-content text-center h-100">
						<div class="thumb">
							<img src="assets/img/signin-logo.png" alt="">
							<div class="container">
								<div class="row">
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
									
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/sportrecord/sportrecord.do" name="form1">
										<div class="form-row">

											<!-- 		紀錄編號 -->
											<input type="hidden" name="record_no" size="30"
													value="${sportRecordVO.record_no }" />
										

											<!-- 		會員編號 -->
											<input type="hidden" name="mem_id" size="30"
													value="<%=memVOLogin.getMem_id()%>" />
										

											<!-- 		紀錄日期 -->
											<div class="form-group">
											<input type="text" class="form-control" name="record_date" id="record_date2" required>
											</div>

											<jsp:useBean id="sportsSvc" scope="page" class="com.sports.model.SportsService" />
											<div class="text-left" id="select-div">
												<select id="sport_no2" name="sport_no">
													<c:forEach var="sportsVO" items="${sportsSvc.all}">
														<c:choose>
															<c:when test="${sportRecordVO != null}">
																<c:choose>
																	<c:when
																		test="${sportsVO.sport_no == sportRecordVO.sport_no}">
																		<option value="${sportsVO.sport_no}" selected>${sportsVO.sport_name}
																	</c:when>
																	<c:otherwise>
																		<option value="${sportsVO.sport_no}">${sportsVO.sport_name}
																	</c:otherwise>
																</c:choose>
															</c:when>
															<c:otherwise>
																<option value="${sportsVO.sport_no}">${sportsVO.sport_name}
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>

											</div>
											
											
											<div class="form-group text-left" id="unit12">
												<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="公斤" required/>
											</div>
											<div class="form-group text-left" id="unit22">
												<input type="text" class="form-control" name="record2" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord2() %>" placeholder="次" required/>
											</div>
											<div class="form-group">
												<input type="hidden" name="action" value="update">
												<input type="hidden" name="whichPage" value = "<%=request.getParameter("whichPage") %>">
												<input type="hidden" name="record_no" value="${sportRecordVO.record_no }"> 
												<input type="submit" value="送出修改">
											</div>
											</div>
									
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  trainer end -->

	

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
java.sql.Date record_date2 = null;
try {
	record_date2 = sportRecordVO.getRecord_date();
} catch (Exception e) {
	record_date2 = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px;*/
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#record_date2').datetimepicker({
	 	       theme: '',              //theme: 'dark',
	 	       timepicker:false,       //timepicker:true,
	 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	 		   value: "<%=record_date2%>"
	 	});
</script>

<script>
var sport_no2 = document.getElementById('sport_no2');
var unit12 = document.getElementById('unit12');
var unit22 = document.getElementById('unit22');

	sport_no2.addEventListener('change',function() {
		if (sport_no2.value == "SPORT00001") {
			unit12.innerHTML = '<input type="text" class="form-control" name="record1" value="" placeholder="公斤" required/>';
			unit22.innerHTML = '<input type="text" class="form-control" name="record2" value="" placeholder="次" required/>';
		} else if (sport_no2.value == "SPORT00002"|| sport_no2.value == "SPORT00003") {
			unit12.innerHTML = '<input type="text" class="form-control" name="record1" value="" placeholder="公尺" required/>';
			unit22.innerHTML = '<input type="text" class="form-control" name="record2" value="" placeholder="分鐘" required/>';
		} else {
			unit12.innerHTML = '<input type="text" class="form-control" name="record1" value="" placeholder="分鐘" required/>';
			unit22.innerHTML = '<input type="hidden" class="form-control" name="record2" value="0"/>';
		}
	});

	window.onload = function() {
		if(sport_no2.value == "SPORT00001"){
			unit12.innerHTML='<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="公斤" required/>';
			unit22.innerHTML='<input type="text" class="form-control" name="record2" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord2() %>" placeholder="次" required/>';
	}else if(sport_no2.value == "SPORT00002" || sport_no2.value == "SPORT00003"){
			unit12.innerHTML='<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="公尺" required/>';
			unit22.innerHTML='<input type="text" class="form-control" name="record2" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord2() %>" placeholder="分鐘" required/>';
			
	}else{
			unit12.innerHTML='<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="分鐘" required/>';
			unit22.innerHTML='<input type="hidden" class="form-control" name="record2" value="0"/>';
	}
	
	};
</script>

</html>

