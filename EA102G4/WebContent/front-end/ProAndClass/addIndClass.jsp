<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.license.model.*"%>
<%@ page import="com.classAuth.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.mem.model.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
// 	ProService proSvc = new ProService();
// 	ProVO proVOLogin = proSvc.getProByMem(memVOLogin.getMem_id());
	ProVO proVOLogin = (ProVO) session.getAttribute("proVOLogin");
	pageContext.setAttribute("proVOLogin", proVOLogin);
	
	ClassAuthService classAuthSvc = new ClassAuthService();
	List<ClassAuthVO> list = (List) classAuthSvc.getAllFromOnePro(proVOLogin.getPro_ID());
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
<jsp:useBean id="classTypeSvc" scope="page"
	class="com.classType.model.ClassTypeService" />

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
	href="${pageContext.request.contextPath}/assets/img/favicon.png"
	sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/wedy.css">
	
	<style>
	.error{
		color:red;
	}
	</style>
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

							<h2>填寫課程資料:</h2>
							
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
								<label for="c_name">課程名稱:</label> <input type="text"
									class="form-control" id="c_name" name="c_name">
							</div>

							<span class="specifications mt-2">
								<div class="form-group">
									<label for="sel1">課程類別:</label> 
									<select class="form-control"
										id="sel1" name="c_type_no">
										<c:forEach var="classAuthVO" items="${list}">
											<option value="${classAuthVO.c_type_no}">
												${classTypeSvc.getOneClassType(classAuthVO.getC_type_no()).t_name}
										</c:forEach>
									</select>
								</div>
							</span> 
							
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="p_coin">價格:</label> <input type="text"
										class="form-control" id="p_coin" name="p_coin">
								</div>
							</span> 
							
							
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">狀態:</label> 
									<input type="radio" id="forSale" name="c_status" value="1"> 
									<label for="forSale">上架</label> 
									<input type="radio" id="notForSale" name="c_status" value="0"> 
									<label for="notForSale">下架</label><br>
								</div>
							</span> 
							
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="loc">地點:</label> <input type="text"
										class="form-control" id="loc" name="loc">
								</div>
							</span>

							<div class="form-group">
								<label for="cate">課程詳情:</label>
								<textarea id="c_detail" class="form-control" rows="7"
									name="c_detail"></textarea>
							</div>

							<div class="col-lg-5">
							
											<!-- 圖片區域上 -->
								<div class="row">
									<label>請上傳圖片檔案: </label>
									<!-- 這裡一定要有一個<input type="file">的元素，當上傳點 -->
									<input type="file" id="myFile" name="c_pic" />
									<div id="preview"></div>
								</div>
							</div>
						
							<!-- 圖片區域 -->
							<br>
							<br>
							<br>
							<input type="hidden" name="pro_ID" value="${proVOLogin.pro_ID }">
							<input type="hidden" name="action" value="insert"> 
							<input type="submit" value="送出">
							<button type="button" id="magic">神奇小按鈕</button>
						</div>
						<!---------------------------------填寫購物資料------------------------------------>
					</FORM>
				</div>
			</div>
		</div>
	</div>


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
		
	<script>	
	function init(){
		var myFile = document.getElementById("myFile");
		var preview = document.getElementById('preview');
		myFile.addEventListener('change', function(e){
			var files = e.srcElement.files;
			if(files){
				var file = files[0];
				if(file.type.indexOf('image') > -1){
					var reader = new FileReader();
					 reader.addEventListener('load', function(e){
						 var result = e.srcElement.result;
						 var img = document.createElement('img');
						 img.src = result;
						 img.style.height = "200px";
						 preview.innerHTML='';
						 preview.append(img);
					 });
					 reader.readAsDataURL(file);
				}else{
					alert('請上傳圖片');
				}
			}
		});
	}
	window.onload = init;
	
	$('#magic').click(function(event){
		event.preventDefault();
		$("#c_name").val("瑜珈").text("瑜珈");
		$("#p_coin").val("1500").text("1500");
		$("#loc").val("中壢市平鎮區中央大學健身房").text("中壢市平鎮區中央大學健身房");
		$("#c_detail").val("我是個會教JAVA的男人，要身材有身材，要腦袋有腦袋!").text("我是個會教JAVA的男人，要身材有身材，要腦袋有腦袋!");
		
	})
	</script>
</body>
</html>