<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.individualClass.model.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.classAuth.model.*"%>
<%@ page import="com.license.model.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.IClassOrder.model.*"%>
<%@ page import="com.classType.model.*"%>

<%
	//after login
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	// 	ProService proSvc = new ProService();
	// 	ProVO proVOLogin = proSvc.getProByMem(memVOLogin.getMem_id());
	ProVO proVOLogin = (ProVO) session.getAttribute("proVOLogin");
	pageContext.setAttribute("proVOLogin", proVOLogin);

	//get class info;
	IndividualClassService IndividualClassSvc = new IndividualClassService();
	List<IndividualClassVO> icList = IndividualClassSvc.findByPro(proVOLogin.getPro_ID());
	pageContext.setAttribute("iclist", icList);
	
	List <String> classList = new ArrayList<String>();
	for(IndividualClassVO ind: icList){
		classList.add(ind.getI_class_no());
	}
	pageContext.setAttribute("classList",classList);

	//get pro auth;
	ClassAuthService classAuthSvc = new ClassAuthService();
	List<ClassAuthVO> caList = classAuthSvc.getAllFromOnePro(proVOLogin.getPro_ID());
	List <String> list = new ArrayList<String> ();
	for(ClassAuthVO caVO : caList){
		list.add(caVO.getC_type_no());
	}	
	pageContext.setAttribute("list", list);
	
	//get type Name;
	ClassTypeService ctSvc = new ClassTypeService();
	List<ClassTypeVO> ctList = ctSvc.getAll();
	pageContext.setAttribute("ctList", ctList);
	
	//get license;
	LicenseService licenseSvc = new LicenseService();
	List <LicenseVO> licList = licenseSvc.findByPro(proVOLogin.getPro_ID());
	pageContext.setAttribute("licList", licList);
	
	//get pro order	 SERVICE
	ProService proSvc = new ProService();
	pageContext.setAttribute("proSvc", proSvc);
	
	MemService memSvc = new MemService();
	pageContext.setAttribute("memSvc", memSvc);
	
	IndividualClassService indSvc = new IndividualClassService();
	pageContext.setAttribute("indSvc",indSvc);
	
	IClassOrderService iClassSvc = new IClassOrderService();
	pageContext.setAttribute("iClassSvc", iClassSvc);
	
	//get pro individual Class
	List <IClassOrderVO> icoList = iClassSvc.getAll();
	pageContext.setAttribute("icoList", icoList);
	
%>

<% 
	//團課
	GroupClassService gcSvc = new GroupClassService();
	List<GroupClassVO> gcList = gcSvc.getGroupClassesByProId(proVOLogin.getPro_ID());
	pageContext.setAttribute("gcList", gcList);

%>
<jsp:useBean id="classTypeSvc" scope="page" class="com.classType.model.ClassTypeService" />
<jsp:useBean id="tool" scope="page" class="com.tool.ShowPhotos" />

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
a.addsomething {
	color: #88C139;
	margin-bottom: 15px;
	display: inline-block;
}

.all-classes-area:before {
	width: 0;
}

ol.breadcrumb {
	color: #88c13f;
	background-color: #fff;
}

.single-class-item .bottom-content p:nth-child(3) {
	min-height: 140px;
	overflow: hidden;
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 4;
}

.modal-body {
	color: black;
}

.modal-body>#exp {
	margin-top: 0px;
	margin-bottom: 0px;
	height: 600px;
	width: 100%;
	resize: none;
}

  pre{
 width: 100%;
 white-space: pre-wrap; 
 font-size: 100.5%;

}

.content{
	padding-top:15px
}

.biography-area .biography-content p{
	font-size: 20px;
}

pre+button.btn.btn-primary {
    float: right;
}


h2+button.btn.btn-primary {
    color: #88C139;
    margin-top:0px
    margin-bottom: 15px;
    display: inline-block;
    background-color: white;
    border: none;
    padding:0px;
}
.container.all-class-slider{
	margin-top:15px;
}

.biography-area .biography-content h2 {
	margin-bottom:0px;
}

<!-- -->

.content>pre{
 height: 600px;
 overflow-y: auto;
}

.content>pre::-webkit-scrollbar {
 width: 8px;
 background-color: transparent;
}

.content>pre::-webkit-scrollbar-thumb {
 border-radius: 10px;
 background-color: #ececec;
}

.content>pre::-webkit-scrollbar-track {
 border-radius: 10px;
 background-color: transparent;
}

button.btn.btn-primary{
    color: #fff;
    background-color: #88c13f;
    border-color: #88c13f;
}

.blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(assets/img/1244995.jpg);
    background-position: 97px -340px;
    background-size: 90%;
}

pre#placeExp {
    height: auto;
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


</style>



</head>
<body onload="connect()" onunload="disconnect()">

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
	
	<!-- class biography area -->
	<div class="biography-area margin-top-60">
		<div class="biography-content">
			<ul class="nav nav-pills">
				<li><a data-toggle="pill" href="#home" class="active">簡介</a></li>
				<li><a data-toggle="pill" href="#menu1">行事曆</a></li>
				<li><a data-toggle="pill" href="#menu2">課表</a></li>
				<li><a data-toggle="pill" href="#menu3">訂單總覽</a></li>
				
			</ul>
			
			       <%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
			

			<div class="tab-content">
				<div id="home" class="tab-pane fade in active show">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="row border-custom">
									<div class="col-md-4">
										<div class="thumb">
											<img
												src="<%=request.getContextPath()%>/front-end/ProAndClass/showPhotos.do?type=memPhoto&pic_no=<%=memVOLogin.getMem_id()%>"
												alt="trainer">
										</div>
									</div>
									<div class="col-md-3 d-flex align-items-top">
										<div class="content">
											<h3><%=memVOLogin.getMem_name()%></h3>
											<p class="pb-3">
												課程權限 :
												
											<c:forEach var="ctVO" items="${ctList}">
											<c:if test="${fn:contains(list,ctVO.c_type_no)}">
											<br>${ctVO.t_name }
											</c:if>
											</c:forEach>
											<p class="pb-3">
												總評分 :
												<c:choose>
												<c:when test="${proVOLogin.p_rating!=0}">
													${proVOLogin.t_rating/proVOLogin.p_rating}
												</c:when>
												<c:otherwise>
													0
												</c:otherwise>
												</c:choose>
												
											</p>
<!-- 											<p class="pb-3"> -->
<!-- 												Total people rated : -->
<%-- 												<%=proVOLogin.getP_rating()%> --%>
<!-- 											</p> -->

											<ul class="social">

											</ul>
										</div>
									</div>
									<div class="col-md-5 d-flex align-items-top">
										<div class="content">
											<h3>Experience</h3>	
											<!-- Button trigger modal popup window -->
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#exampleModalLong">
												修改</button>
											<pre id="placeExp"><%=proVOLogin.getExpr()%></pre>


										</div>
									</div>
								</div>
							</div>
						</div>
					
					
					<!-- License area -->	
						<div class="container all-class-slider">
							<h2>證照</h2>
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
  															新增證照
															</button>
							<div class="row classes-items text-center">
								<c:forEach var="licenseVO" items="${licList}">
									<div class="col-lg-4">
										<div class="single-class-item">
											<div class="top-content">
												<div class="content-img">
													<img
														src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=proPhoto&pic_no=${licenseVO.license_no}"
														width="240" height="250" alt="shopping">
												</div>
												<div class="hover-content">

												</div>
											</div>
											<div class="bottom-content">
												<h3>
												${licenseVO.lic_name}
												</h3>
												<p>${licenseVO.no_reg}</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>	
					<!-- License area -->

						
					</div>
				</div>


				<div id="menu1" class="tab-pane fade">
						  <jsp:include page="${ request.getContextPath()}/front-end/FullCalendar/proBack_end.jsp" flush="true" />                            
					
				</div>
				
				<div id="menu2" class="tab-pane fade">
					<div class="all-classes-area">
						<div class="container all-class-slider">
							<h2>私人課程 (一對一)</h2>
							<a href="addIndClass.jsp" class="addsomething">加入新課程</a>
							<div class="row classes-items text-center">
								<c:forEach var="iClassVO" items="${iclist}">
									<div class="col-lg-4">
										<div class="single-class-item">
											<div class="top-content">
												<div class="content-img">
													<img
														src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=iClassPhoto&pic_no=${iClassVO.i_class_no}"
														width="240" height="250" alt="shopping">
												</div>
												<div class="hover-content">
													<div class="btn-wrapper desktop-center">
														<a
															href="<%=request.getContextPath() %>/front-end/ProAndClass/IndividualClassServlet.do?i_class_no=${iClassVO.i_class_no}&action=getOne_For_Update"
															class="btn btn-element btn-normal-size btn-rounded">修改</a>
													</div>
												</div>
											</div>
											<div class="bottom-content">
												<h3>
													<a
														href="IndividualClassServlet.do?i_class_no=${iClassVO.i_class_no}&action=getOne_For_Display">${iClassVO.c_name}</a>
												</h3>
												<p>${classTypeSvc.getOneClassType(classAuthVO.getC_type_no()).t_name}</p>
												<p>${iClassVO.c_detail }</p>
												<h4>Course Price : ${iClassVO.p_coin}</h4>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>


					<div class="all-classes-area">
						<div class="container all-class-slider">
							<h2>多人課程 (一對多)</h2>
							<a href="<%=request.getContextPath()%>/front-end/groupclass/addGroupClass.jsp" class="addsomething">加入新課程</a>
							<div class="row classes-items text-center">
								<c:forEach var="gcVO" items="${gcList}">
									<div class="col-lg-4">
										<div class="single-class-item">
											<div class="top-content">
												<div class="content-img">
													<img
														src="../groupclass/groupClass.do?g_class_no=${gcVO.g_class_no}"
														width="240" height="250" alt="shopping">
												</div>
												<div class="hover-content">
													<div class="btn-wrapper desktop-center">
														<a
															href="<%=request.getContextPath()%>/front-end/groupclass/update_GroupClass_input.jsp?g_class_no=${gcVO.g_class_no}"
															class="btn btn-element btn-normal-size btn-rounded">修改</a>
													</div>
												</div>
											</div>
											<div class="bottom-content">
												<h3>
													<a href="<%=request.getContextPath()%>/front-end/groupclass/groupClass.do?g_class_no=${gcVO.g_class_no}&action=getOne_For_Display">${gcVO.g_name}</a>
												</h3>
												<p>${classTypeSvc.getOneClassType(gcVO.getC_type_no()).t_name}</p>
												<p>${gcVO.g_detail }</p>
												<h4>Course Price : ${gcVO.p_coin}</h4>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				
<!-- 訂單總攬 -->
				<div id="menu3" class="tab-pane fade">
					<div class="all-classes-area">
							<h2>私人課程訂單</h2>
								<div class="row">
									<div class="col-lg-12">
										<div class="single-cart-list">
											<table class="table table-borderless">
												<tbody id="cartBody">
													<tr>
													<td style="width:10%;">訂單編號</td>
													<td style="width:10%;">課程名稱</td>
													<td style="width:10%;">會員</td>
													<td style="width:15%;">上課日期</td>
													<td style="width:10%;">上課時間</td>
													<td style="width:10%;">消費金額</td>
													<td style="width:25%;">上課地點</td>
													<td style="width:10%;">上課地點</td>
													
											<c:forEach var="icoVO" items="${icoList}">
											<c:if test="${fn:contains(classList,icoVO.i_class_no)}">
												<tr  class="order" >
													<td>${icoVO.i_order_no}</td>
													<td>${indSvc.getOneIndividualClass(icoVO.i_class_no).c_name }</td>
													<td value="${memSvc.getOneMem(icoVO.mem_ID).mem_id}">${memSvc.getOneMem(icoVO.mem_ID).mem_name }</td>
													<td><p class="dateOrder">${icoVO.RDate}</p></td>
													<td><p class="hrOrder">${icoVO.hr}</p></td>
													<td><p class="money">
													<c:set var="total" value="${0}"/>
															${icoVO.p_coin}
													<c:out value="${total}"/></p></td>
													<td><p class="loc">${indSvc.getOneIndividualClass(icoVO.i_class_no).loc}</p></td>
													<td><button type="button" id="confirmBooking" class="btn btn-primary" onclick="cancelBookOrder()" >確認預約</button></td>
												</tr>
											</c:if>
											</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
						</div>
					</div>


					<div class="all-classes-area">
						<div class="container all-class-slider">
						</div>
					</div>
				</div>
<!-- 訂單總攬 -->	
			</div>
		</div>
	
	</div>
	<!-- biography end -->

	<!-- home shopping end -->

	<div class="pagination-content">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="pagination-area d-flex justify-content-center">
						<ul>
							<li><span class="next page-bumber"><i
									class="fa fa-angle-left"></i> Prev</span></li>
							<li><span class="page-bumber">1</span></li>
							<li><span class="page-bumber current">2</span></li>
							<li><span class="page-bumber">3</span></li>
							<li><span class="next page-bumber">Next <i
									class="fa fa-angle-right"></i></span></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	
  <%@ include file="../footerFile/pageFooter.file"%>

	<!-- Modal Exp -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">修改經歷</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<textarea name="exp" id="exp"  rows="10"><%=proVOLogin.getExpr()%>
					</textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="send">確認修改</button>
				</div>
			</div>
		</div>
	</div>


<!-- Modal license -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		
	<form method="post" action="<%=request.getContextPath() %>/front-end/ProAndClass/license.do" enctype="multipart/form-data">
		<table>
     	    <tr>
				<td>證照名稱<input type="text" name="lic_name" id="lic_name"></td>
			</tr>
				
			<tr>
				<td>證照編碼<input type="text" name="no_reg" id="no_reg"></td>
			</tr>
			
			<tr>	
				<td><input type="file" name="l_pic"  id="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
		</table>
		<input type="hidden" name="pro_ID" value="${proVOLogin.pro_ID}">
		<div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary" name="action" value="insert" id="sendLic">Save changes</button>
      </div>
		</form>
		
      </div>
      
    </div>
  </div>
</div>




	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
	<script>
		$(document).ready(function() {

// 			ProExp
			$('#send').on('click', function() {
				var exp = $('#exp').val();
				$("pre#placeExp").text(exp);
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath() %>/front-end/ProAndClass/pro.do",
					data : {
						"action": "updateExp",
						"exp" : exp,
						"pro_ID": "<%=proVOLogin.getPro_ID()%>"
					},
					dataType : "json"
				})
				$('[data-dismiss="modal"]').click();
			})
			
			$(".dateOrder").each(function(){
				var format = $(this).text().split('.');
				$(this).text(format[0]);
			});
			$('.hrOrder').each(function(){
				var hr = $(this).text();
				$(this).text(hr.indexOf(1)+'時');
				
			});
		})			
		
// 		function cancelBook(){
// 			console.log("hello");	
		
// 			 var jsonObj = {
// 						"type" : "confirm",
// 						"sender" :"${memVOLogin.mem_id}",
// 						"receiver" :"MEM0000001",
// 						"message":"我愛明峰",
// 		}
// 			 webSocket.send(JSON.stringify(jsonObj));
// 		}

	</script>

<script>
	var MyPoint = "/MembersCenter/${memVOLogin.mem_id}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket;
	
	function connect() {
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			console.log("${memVOLogin.mem_id} Connect Success!");
		};
		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("notice" === jsonObj.type) {
				console.log("成功接收加入訊息!!!!!!!!!");
	           var h = '入團通知' || false,
	               s = jsonObj.message,
	               c = $(this).data('color') || '#CD0505',
	               t = 10000;
	        triggerToast(h, s, c, t);
			}else if("statusCheck" === jsonObj.type){
				console.log("成功接收揪團訊息!!!!!!!!!");
	               var h = '揪團狀態' || false,
	                   s = jsonObj.message,
	                   c = $(this).data('color') || '#CD0505',
	                   t = 10000;
	        triggerToast(h, s, c, t);
			}else if("finalStatus" === jsonObj.type){
				console.log("成功接收揪團訊息!!!!!!!!!");
	               var h = '揪團狀態' || false,
	                   s = jsonObj.message,
	                   c = $(this).data('color') || '#CD0505',
	                   t = 10000;
	        triggerToast(h, s, c, t);
			}else if("confirm" === jsonObj.type){
				console.log("confirm!!!!!!!!!");
	               var h = '您的預約已取消' || false,
	                   s = jsonObj.message,
	                   c = $(this).data('color') || '#CD0505',
	                   t = 10000;
			$('.top .btns .today').click();
	        triggerToast(h, s, c, t);
			}
		}
		
		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
		
	}
	
	function sendMessage() {
		var message = '${memVOLogin.mem_name}加入你的${groupVO.gro_name}揪團囉!';
			var jsonObj = {
				"type" : "notice",
				"sender" : "${memVOLogin.mem_id}",
				"receiver" : "${groupVO.mem_id}",
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
	}
	 function triggerToast(head, strings, color, time) {
	      $.toast({
	          heading: head,
	          text: strings,
	          loaderBg: color,
	          hideAfter: Number(time),
	          position: 'bottom-left'
	      });
	  }
		connect();	
</script>	
</body>
</html>