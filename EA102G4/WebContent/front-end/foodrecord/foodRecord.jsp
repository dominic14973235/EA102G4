<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.foodrecord.model.*"%>
<%@ page import="com.foods.model.*"%>

<%
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");

	if (memVOLogin == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	} else {

	FoodRecordService foodrecordSvc = new FoodRecordService();
	List<FoodRecordVO> list = foodrecordSvc.getAllByMem(memVOLogin.getMem_id());
	TreeSet<Date> set = new TreeSet<Date>();
	for(FoodRecordVO i : list ) {
		set.add(i.getFoodr_date());
	}
	TreeMap map = new TreeMap();
	for(Date d : set) {
		map.put(d,foodrecordSvc.getAllByDate(d, memVOLogin.getMem_id()));
	}
	
	TreeMap map2 = new TreeMap();
	FoodService foodSvc = new FoodService();
	for(Date d : set){
		map2.put(d,foodSvc.getAllNu(foodrecordSvc.getAllByDate(d, memVOLogin.getMem_id())));
	}
	
    pageContext.setAttribute("set",map);
    pageContext.setAttribute("set2",map2);
%>
<jsp:useBean id="FoodSvc" scope="page" class="com.foods.model.FoodService" />

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
#hi_mem {
	font-family: Microsoft JhengHei;
}

@media screen and (max-width:1400px) {
	#hi_mem {
		display: none;
	}
}

#PageSelecter {
	padding: 5px 10px;
	margin-left: 50px;
	font-family: Microsoft JhengHei;
}

#plus-button-div{
width:80%;
margin:20px auto 0 auto;
}

p.bodyinfo {
	margin: 0 0;
}
img.bodyphoto{
	width:100%;
	height:100%;
}
</style>


<style> 
    table { 
 		background-color: #00FFFF; 
 		margin-bottom: 5px; 
 		color:black; 
 		border-radius: 10px; 
    } 
    th, td { 
    	padding: 5px; 
     	text-align: center;
     	border: 1px solid #FFF; 
    }
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

<%@ include file="../memInclude/navbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/service.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">飲食紀錄</h1>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<div id="plus-button-div">
		<button type="button" class="btn btn-secondary btn-lg btn-block" id="plus-button">新增</button>
	</div>

	<!--  trainer start -->
	<section class="margin-bottom-100">
<!-- 	<section class="trainer-area bg-none margin-bottom-100 margin-top-100"> -->
		<%@ include file="page1_2.file"%>
		<div class="container">
<!-- 			<div class="row class-slider-style-2 col-md-12"> -->
			<div class="row class-slider-style-2 col-md-12" style="margin:auto;">
				<c:forEach var="record" items="${set}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<h3>${record.key}</h3>
				<table data-toggle="table" class="col-md-12">
				<thead>
				<tr>
					<th>用餐時間</th>	
					<th>食物名稱</th>
					<th>澱粉(g)</th>
					<th>蛋白質(g)</th>
					<th>脂肪(g)</th>
					<th>熱量(kcal)</th>
					<th>份量</th>
					<th>修改</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="foodrecordVO" items="${record.value}">
					<tr>
						<td>${foodrecordVO.foodr_time}</td>					
						<c:forEach var="foodVO" items="${FoodSvc.all}">
		                    <c:if test="${foodrecordVO.food_no==foodVO.food_no}">
		                    <td>${foodVO.food_name}</td>
		                    <td>${foodVO.starch}</td>				
		                    <td>${foodVO.protein}</td>				
		                    <td>${foodVO.fat}</td>				
		                    <td>${foodVO.kcal}</td>				
		                    </c:if>
		                </c:forEach>
						<td>${foodrecordVO.foodr_weight}</td>			
						<td>
					  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/foodrecord/foodrecord.do" style="margin-bottom: 0px;">
					    <input type="submit" value="修改">
					    <input type="hidden" name="foodr_no" value="${foodrecordVO.foodr_no}">
					    <input type="hidden" name="action" value="getOne_For_Update"></FORM>
						</td>			
					</tr>		
					</c:forEach>				
					<c:forEach var="nutrition" items="${set2}">	
					<c:if test="${nutrition.key==record.key}">							
					<tr>
						<td colspan=2><b>總計</b></td>
						<td>${nutrition.value[0]}</td>
						<td>${nutrition.value[1]}</td>
						<td>${nutrition.value[2]}</td>
						<td>${nutrition.value[3]}</td>
					</tr>
					</c:if>
					</c:forEach>
					</tbody>																											
				</table>				
				</c:forEach>

			</div>
			<div class="row">
				<div class="col-md-12">
					<div
						class="pagination-area d-flex justify-content-center margin-top-50">
						<ul>
							<li id="before"><span class="next page-bumber"><i
									class="fa fa-angle-left"></i> Prev</span></li>

							<li><span class="page-bumber current"><%=whichPage%></span></li>

							<li id="after"><span class="next page-bumber">Next <i
									class="fa fa-angle-right"></i></span></li>
						</ul>
						<%
							if (pageNumber > 1) {
						%>
						<div class="dropdown">
						<a class="btn btn-secondary dropdown-toggle" type="button" id="PageSelecter" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   						 第<%=whichPage%>頁
  						</a>
  						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<%
									for (int i = 1; i <= pageNumber; i++) {
								%>
								 <a class="dropdown-item" href="<%=request.getRequestURI()%>?whichPage=<%=i%>">第<%=i%>頁</a>
								<%
									}
								%>
						</div>
					</div>
					<%} %>
				</div>
			</div>
		</div>
	</section>
	<!--  trainer end -->

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

var before = document.getElementById("before");
var after = document.getElementById("after");
var insert = document.getElementById("plus-button");

before.addEventListener("click", function(){
	<%if (whichPage > 1) {%>
	window.location.href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>";
	<%}%>
});
after.addEventListener("click", function(){
	<%if (whichPage < pageNumber) {%>
	window.location.href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>";
	<%}%>
	});
	
insert.addEventListener("click", function(){
	window.location.href="<%=request.getContextPath()%>/front-end/foodrecord/addFoodRecord.jsp";
});
</script>
</html>

<%
	}
%>