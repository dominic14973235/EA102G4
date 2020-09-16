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
.sign-content.text-center.h-100 {
    background: rgb(227 227 227);
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
							<img src="assets/img/signin-logo_new.png" alt="">
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

</html>

