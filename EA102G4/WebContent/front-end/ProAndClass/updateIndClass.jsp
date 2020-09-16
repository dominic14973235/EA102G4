<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.license.model.*"%>
<%@ page import="com.classAuth.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.individualClass.model.*" %>
<%@ page import="com.mem.model.*"%>



<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");

	ProVO proVOLogin = (ProVO) session.getAttribute("proVOLogin");//modify base on previous page
	
	IndividualClassService IndividualClassSvc = new IndividualClassService();
	IndividualClassVO individualClassVO =  (IndividualClassVO) request.getAttribute("individualClassVO");
	pageContext.setAttribute("individualClassVO",individualClassVO);
	
	ClassAuthService classAuthSvc = new ClassAuthService();
	List<ClassAuthVO> list = (List) classAuthSvc.getAllFromOnePro(proVOLogin.getPro_ID());
	pageContext.setAttribute("list", list);
	
	List<String> list2 = new ArrayList<String>();
	for(ClassAuthVO caVO : list){
		list2.add(caVO.getC_type_no());
	}
	pageContext.setAttribute("list2", list2);
	
%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>填寫課程資料</title>
<!-- favicon -->
<link rel=icon
	href="${pageContext.request.contextPath}/front-end/product/assets/img/favicon.png
	sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/product/assets/css/wedy.css">
	
	 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
	
</head>
<body>

	<!-- product-details-tab start -->
	<div class="product-details-tab padding-top-100 padding-bottom-135">
		<!--------- bottom想改成200不知道為什麼動style動不了 --------->
		<div class="container">
			<div class="row">

				<div class="col-lg-7" >
					<FORM METHOD="post"
						ACTION="IndividualClassServlet.do"
						enctype="multipart/form-data">
						<div class="content-part">
							<!---------------------------------填寫購物資料------------------------------------>

							<h2>填寫課程修改資料:</h2>
							
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<div class="form-group">
								<label for="usr">課程名稱:</label> <input type="text"
									class="form-control" id="usr" name="c_name" value="<%=individualClassVO.getC_name()%>">
							</div>
							
							
						<jsp:useBean id="classTypeSvc" scope="page" class="com.classType.model.ClassTypeService" />
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="sel1">課程類別:</label> <select class="form-control"
										id="sel1" name="c_type_no">
											<c:forEach var="classTypeVO" items="${classTypeSvc.all}">
												<c:if test="${fn:contains(list2,classTypeVO.c_type_no)}">
													<option value="${classTypeVO.c_type_no}">
														${classTypeVO.t_name}</option>
												</c:if>
											</c:forEach>										
									</select>
								</div>
							</span> <span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">價格:</label> <input type="text"
										class="form-control" id="usr" name="p_coin" value=<%=individualClassVO.getP_coin()%>>
								</div>
							</span> 
							
							
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">狀態:</label> 
									<input type="radio" id="forSale" name="c_status" value="1" ${(individualClassVO.c_status==1)?'checked':'' }> 
									<label for="forSale">上架</label> 
									<input type="radio" id="notForSale" name="c_status" value="0" ${(individualClassVO.c_status==0)?'checked':'' }> 
									<label for="notForSale">下架</label><br>
								</div>
							</span> 
							
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">地點:</label> <input type="text"
										class="form-control" id="usr" name="loc" value="<%=individualClassVO.getLoc()%>">
								</div>
							</span>

							<div class="form-group">
								<label for="cate">課程詳情:</label>
								<textarea id="cate" class="form-control" rows="7"
									name="c_detail"><%=individualClassVO.getC_detail()%></textarea>
							</div>

							<div class="col-lg-5">

								<!-- 圖片區域上 -->
								<div class="row">
									<label>請上傳圖片檔案: </label>
									<!-- 這裡一定要有一個<input type="file">的元素，當上傳點 -->
									<input type="file" id="myFile" name="c_pic" value=<%=individualClassVO.getC_pic()%>>
								</div>
								<div class="row">
									<input type="text" name="filename" id="filename">
								</div>
								
								<div class="row" >
									<label>檔案內容: </label>
									<div id="preview"></div>
								</div>

							</div>
							<!-- 圖片區域 -->
							
							<input type="hidden" name="pro_ID" value="${proVOLogin.pro_ID}">
							<input type="hidden" name="i_class_no" value="${individualClassVO.i_class_no}">
							<input type="hidden" name="action" value="update"> 
							<input type="submit" value="送出">
						</div>
						<!---------------------------------填寫購物資料------------------------------------>
					</FORM>
				</div>
			</div>
		</div>
	</div>
	<!-- product-details-tab end -->


  <%@ include file="../footerFile/pageFooter.file"%>

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->


	<!-- jquery -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/nice-select.js"></script>
	<!-- counter up -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/script.js"></script>

	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/front-end/product/assets/js/wedy.js"></script>
</body>
</html>

