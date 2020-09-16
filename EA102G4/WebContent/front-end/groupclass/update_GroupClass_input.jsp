<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.pro.model.*"%>
<%-- <%@ page import="com.license.model.*"%> --%>
<%-- <%@ page import="com.classAuth.model.*"%> --%>
<%-- <%@ page import="com.classType.model.*"%> --%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.classAuth.model.*"%>

<%MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin"); %>
<%
// 	測試環境   不手動登入用
// 	MemService memSvc = new MemService();
// 	memVOLogin=memSvc.getOneMem("MEM0000001"); 
// 	session.setAttribute("memVOLogin", memVOLogin);
%>

<%
	ProService proSvc = new ProService();
	ProVO proVOLogin = (ProVO) session.getAttribute("proVOLogin");
	if(proVOLogin==null)
		proVOLogin = proSvc.getProByMem(memVOLogin.getMem_id());
	
	pageContext.setAttribute("proVOLogin", proVOLogin);
	GroupClassService gcSvc = new GroupClassService();
// 	ClassAuthService classAuthSvc = new ClassAuthService();
// 	List<ClassAuthVO> list = (List) classAuthSvc.getAllFromOnePro(proVOLogin.getPro_ID());
	GroupClassVO gcVO = (GroupClassVO) request.getAttribute("gcVO");
	pageContext.setAttribute("gcVO", gcVO);
	ClassAuthService classAuthSvc = new ClassAuthService();
	List<ClassAuthVO> list = (List) classAuthSvc.getAllFromOnePro(proVOLogin.getPro_ID());
	pageContext.setAttribute("list", list);
	if(gcVO==null)
		gcVO=gcSvc.getOneGroupClass(request.getParameter("g_class_no"));
// 	pageContext.setAttribute("list", list);
%>

<%-- <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> --%>
<%-- <jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" /> --%>
<jsp:useBean id="classTypeSvc" scope="page" class="com.classType.model.ClassTypeService" />





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
	
	 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js"></script>
<script type="text/javascript">
// 運用 jQuery 展開新的實例(Instance)
$(function () {
  new TwCitySelector()
})
 </script>


 <style type="text/css" media="screen">

select,.zipcode{
  width: 20%;
  display: inline-block;
  height: calc(1.5em + .75rem + 2px);
  padding: .375rem .75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
   
 </style>
</head>
</head>
<body>
<%@ include file="../artInclude/navbar.file"%>
	<!-- product-details-tab start -->
	<div class="product-details-tab padding-top-100 padding-bottom-135">
		<!--------- bottom想改成200不知道為什麼動style動不了 --------->
		<div class="container">
			<div class="row">

				<div class="col-lg-7" >
					<FORM METHOD="post"
						ACTION="groupClass.do"
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
							<input type="hidden" name="g_class_no" value="<%=gcVO.getG_class_no()%>">
							<div class="form-group">
								<label for="usr">團課名稱:</label> <input type="text"
									class="form-control" id="usr" name="g_name" value="<%=gcVO.getG_name()%>">
								<div class="error">${errorMsgs.g_name}</div>
							</div>
							

							<span class="specifications mt-2">
								<div class="form-group">
									<label for="sel1">課程類別:</label> <select class="form-control"
										id="sel1" name="c_type_no">
										<c:forEach var="classAuthVO" items="${list}">
											<option value="${classAuthVO.c_type_no}">
												${classTypeSvc.getOneClassType(classAuthVO.getC_type_no()).t_name}
										</c:forEach>
									</select>
									<div class="error">${errorMsgs.c_type_no}</div>
								</div>
							</span> 
							
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">價格:   </label> 
									<input type="number" class="form-control" id="price" name="p_coin" max="3000" min="200" value="<%=gcVO==null||gcVO.getP_coin()==0?200:gcVO.getP_coin()%>">
									<div class="error" id="errPrice">${errorMsgs.p_coin}</div>
								</div>
							</span> 

							<span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">狀態:</label> 
									<input type="radio" id="forSale" name="c_status" value="1" <%=gcVO.getC_status()==1? "checked" : ""%>> 
									<label for="forSale">上架</label> 
									<input type="radio" id="notForSale" name="c_status" value="0" <%=gcVO.getC_status()==0? "checked" : ""%>> 
									<label for="notForSale">下架</label>
									<div class="error">${errorMsgs.c_status}</div><br>
								</div>
							</span> 
							
							<span class="specifications mt-2" >
								<div class="form-group">
									<label for="usr">地點:</label> 
									<div role="tw-city-selector" id="twcity" ></div>
									<input type="text"
										class="form-control" id="loc" name="loc" style="width:45%;">
								</div>
								<div class="error">${errorMsgs.loc}</div>
							</span>

							<div class="form-group">
								<label for="cate" color="black">團課詳情:</label>
								<textarea id="cate" class="form-control" rows="7"
									name="g_detail"><%=gcVO.getG_detail() %></textarea>
								<div class="error">${errorMsgs.g_detail}</div>
							</div>
							<span class="specifications mt-2">
								<div class="form-group">
									<label for="usr">上課人數上限:   </label><label id="g_maxText"><%=gcVO.getG_max()%></label><label for="usr">人</label>  
									<input type="range" id="g_max" name="g_max" min="2" max="50" step="1" value="<%=gcVO.getG_max()%>">
									<div class="error">${errorMsgs.g_max}</div>
								</div>
							</span>
							

							<div class="col-lg-5">
								<!-- 圖片區域上 -->
								<div class="row">
									<label>請上傳圖片檔案: </label>
									<!-- 這裡一定要有一個<input type="file">的元素，當上傳點 -->
									<input type="file" id="myFile" name="g_pic" />
									<div id="preview">
						<%="<img src=\"../groupclass/groupClass.do?g_class_no=" + ((gcVO == null) ? "" :gcVO.getG_class_no()) + "\""%>
					</div>
								</div>
							</div>
							<!-- 圖片區域 -->
							
							<input type="hidden" name="pro_id" value="${proVOLogin.pro_ID}">
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
		src="${pageContext.request.contextPath}/assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script
		src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script
		src="${pageContext.request.contextPath}/assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script
		src="${pageContext.request.contextPath}/assets/js/nice-select.js"></script>
	<!-- counter up -->
	<script
		src="${pageContext.request.contextPath}/assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script
		src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script
		src="${pageContext.request.contextPath}/assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script
		src="${pageContext.request.contextPath}/assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script
		src="${pageContext.request.contextPath}/assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script
		src="${pageContext.request.contextPath}/assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script
		src="${pageContext.request.contextPath}/assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script
		src="${pageContext.request.contextPath}/assets/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/script.js"></script>

	<!-- rProgressbar -->
	<script
		src="${pageContext.request.contextPath}/assets/js/wedy.js"></script>
		<script>
		if('<%=gcVO%>'!=null){
			var loc = '<%=gcVO.getLoc()%>';
			$('#twcity').attr( 'data-county-value', loc.slice(0,3) );
			$('#twcity').attr( 'data-district-value', loc.slice(3,6) );
			$('#loc').val(loc.substring(6));
		}
		$('#price').change(function(){
			if($(this).val()>3000){
				$(this).val(3000);
				$('#errPrice').text('金額不可超過3000')
			}else if($(this).val()<200){
				$(this).val(200);
				$('#errPrice').text('金額不可小於200')
			}else{
				$('#errPrice').text('')
			}
		});
		$('#g_max').change(function(){
			$('#g_maxText').text($('#g_max').val());
		});
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
	</script>
</body>
</html>