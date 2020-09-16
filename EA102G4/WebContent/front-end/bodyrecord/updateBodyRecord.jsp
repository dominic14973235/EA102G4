<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bodyrecord.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	BodyRecordVO bodyRecordVO = (BodyRecordVO) request.getAttribute("bodyRecordVO");
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
	
	
	if(bodyRecordVO == null){
		String body_no = request.getParameter("body_no");
		BodyRecordService bodyRecordSvc = new BodyRecordService();
		bodyRecordVO = bodyRecordSvc.getOneBodyRecordByBodyNo(body_no);
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
     <link rel=icon href=assets/img/favicon.png sizes="20x20" type="image/png">
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
</head>
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
	#body_date-div{
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
	#frequency{
		display: block;
    	width: 100%;
    	padding: 15px 10px;
    	font-size: 1rem;
    	line-height: 1.5;
    	color: #495057;
    	background-color: #fff;
    	background-clip: padding-box;
    	border: 1px solid #ced4da;
    	border-radius: .25rem;
    	transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
	}
 	#mem_bmi{ 
		display: inline; 
    	width: 60%; 
    	padding: 15px 10px; 
		font-size: 1rem; 
		line-height: 1.5; 
		color: #495057; 
    	background-color: #fff; 
    	background-clip: padding-box; 
    	border: 1px solid #ced4da; 
		border-radius: .25rem; 
   		transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out; 
 	}
 	#bmi_value{
 		margin-left:10px;
 		color:black;
 	}
 	.breadcrumb-style-1.breadcrumb-nobg{
 		min-height:200px;
 	}
 	.sign-area .sign-content {
    padding: 50px 50px 50px 50px;
    background: rgb(227 227 227);
    }
</style>
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
            <button type="submit" class="submit-btn"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <!--  search Popup -->

    	<%@ include file="../memInclude/navbar.file"%>
    
	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/service.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Update BodyData</h1>
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
                        <form METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/bodyrecord/bodyrecord.do" name="form1" enctype="multipart/form-data">
                            <div class="form-row">
                            	<div class="form-group">
                                    <input type="hidden" name="mem_id" size="30" value="<%=memVOLogin.getMem_id()%>" />
                                </div>
                                <div class="form-group col-md-7 text-left" id="body_date-div">
                                    <label for="body_date">紀錄日期</label>
                                    <input type="text" class="form-control" name="body_date" id="body_date" required><br>
                                </div>
                                <div class="col-md-7 text-left" id="sex-div">
                                    <label class="sex-margin">性別</label>
                                    <%if(memVOLogin.getSex().equals("男")){ %>
                                    <input type="radio" id="male" class="sex-margin" name="sex" value="男" checked> <label for="male" class="sex-margin">男</label>
                                    <input type="radio" id="female" class="sex-margin" name="sex" value="女"> <label class="sex-margin" for="female">女</label><br>
									<%}else{ %>
									<input type="radio" id="male" class="sex-margin" name="sex" value="男"><label class="sex-margin" for="male">男</label>
									<input type="radio" id="female" class="sex-margin" name="sex" value="女" checked><label class="sex-margin" for="female">女</label><br>
									<%} %>
                                </div>
                                <div class="form-group col-md-6 text-left">
                                    <label for="mem_height">身高</label>
                                    <input type="text" class="form-control" id="mem_height" name="mem_height" value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_height()%>" placeholder="cm" required>
                                </div>
                                <div class="form-group col-md-6 text-left">
                                    <label for="mem_weight">體重</label>
                                    <input type="text" class="form-control" id="mem_weight" name="mem_weight" value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_weight()%>" placeholder="kg" required>
                                </div>
                                <div class="form-group col-md-10 text-left">
                                    <label for="mem_bmi">BMI</label>
                                    <input type="text" id="mem_bmi" name="mem_bmi" value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_bmi()%>"><span id="bmi_value" required></span>
                                </div>
                                <div class="form-group col-md-6 text-left">
                                    <label for="mem_old">年齡</label>
                                    <input type="text" class="form-control" id="mem_old" name="mem_old" value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_old()%>" placeholder="years old" required>
                                </div>
                                <div class="form-group col-md-6 text-left">
                                    <label for="mem_bmr">基礎代謝率(BMR)</label>
                                    <input type="text" class="form-control" id="mem_bmr" name="mem_bmr" value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_bmr()%>" required>
                                </div>
                                <div class="form-group col-md-6 text-left">
                                    <label>運動頻率</label>
                                    <select id="frequency" name="frequency">
										<option value="0" />久坐(沒有運動習慣)
										<option value="1" />輕度(運動1-2天/週)
										<option value="2" />中度(運動3-5天/週)
										<option value="3" />高度(運動6-7天/週)
										<option value="4" />極高度(每天運動2次)
									</select>
                                </div>
                                <div class="form-group col-md-6 text-left">
                                    <label for="mem_tdee">每日總消耗熱量(TDEE)</label>
                                    <input type="text" class="form-control" id="mem_tdee" name="mem_tdee" value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_tdee()%>" required>
                                </div>
                                <div class="form-group text-left">
                                    <label for="photo">照片</label>
                                    <input type="file" class="form-control" id="photo" name="photo" value="<%=bodyRecordVO.getPhoto()%>" accept="image/gif, image/jpeg, image/png">
                                    <img id="img_content" src="<%=request.getContextPath()%>/front-end/bodyrecord/bodyphoto.do?body_no=<%=bodyRecordVO.getBody_no()%>">
                                </div>
                            </div>
                            <div class="form-group">
                            <input type="hidden" name="action" value="update"> 
                            <input type="hidden" name="body_no" value="<%=bodyRecordVO.getBody_no()%>">
                            <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
                            <input type="submit" class="btn btn-dark" value="Submit">
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
	java.sql.Date body_date = null;
	try {
		body_date = bodyRecordVO.getBody_date();
	} catch (Exception e) {
		body_date = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        $('#body_date').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: "<%=body_date%>"
	});


var male = document.getElementById('male');
var female = document.getElementById('female');
var mem_height = document.getElementById('mem_height');
var mem_weight = document.getElementById('mem_weight');
var mem_bmi = document.getElementById('mem_bmi');
var bmi_value = document.getElementById('bmi_value');
var mem_old = document.getElementById('mem_old');
var mem_bmr = document.getElementById('mem_bmr');
var frequency = document.getElementById('frequency');
var mem_tdee = document.getElementById('mem_tdee');
var photo = document.getElementById('photo');
var img = document.getElementById('img_content')
var myheight = mem_height.value, myweight = mem_weight.value, myold = mem_old.value, myfrequency = frequency.value, sex;

if(male.checked){
	sex = '男';
}else{
	sex = '女';
}
//輸入身高
mem_height.addEventListener('input', function(){
	myheight = mem_height.value;
	if(myweight != null){
		getMem_bmi();
	}
	if(myweight != null && myold != null){
		getMem_bmi();
		getMem_bmr();
		getMem_tdee();
	}
});

//輸入體重
mem_weight.addEventListener('input', function(){
	myweight = mem_weight.value;
	if(myheight != null){
		getMem_bmi();
	}
	if(myheight != null && myold != null){
		getMem_bmi();
		getMem_bmr();
		getMem_tdee();
	}
});

//輸入年齡
mem_old.addEventListener('input', function(){
	myold = mem_old.value;
	if(myheight != null && myweight != null){
		getMem_bmr();
		getMem_tdee();
	}
});

//改變性別
male.addEventListener('change', function(){
	if(myold != null && myheight != null && myweight != null){
		getMem_bmr();
		getMem_tdee();
	}
});
female.addEventListener('change', function(){
	if(myold != null && myheight != null && myweight != null){
		getMem_bmr();
		getMem_tdee();
	}
});

//改變運動頻率
frequency.addEventListener('change', function(){
	myfrequency = frequency.value;
	if(myold != null && myheight != null && myweight != null){
		getMem_tdee();
	}
});

//上傳圖片
photo.addEventListener('change', function(){
	readURL(this);
});

//顯示圖片
function readURL(input){
	if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       img.setAttribute('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
}

//計算BMI指數
function getMem_bmi(){
	mem_bmi.value = (Math.floor(myweight/Math.pow(myheight/100, 2)*10))/10;
	if(mem_bmi.value >= 27){
		bmi_value.textContent = "肥胖!!";
	}else if(mem_bmi.value >= 24 && mem_bmi.value < 27){
		bmi_value.textContent = "體重過重!!";
	}else if(mem_bmi.value >= 18.5 && mem_bmi.value < 24){
		bmi_value.textContent = "恭喜~體重正常!!";
	}else{
		bmi_value.textContent ="體重過輕!!";
	}
}

//計算BMR
function getMem_bmr(){
	if(male.checked){
		sex = male.value;
	}
	if(female.checked){
		sex = female.value;
	}
	
	if(sex == "男"){
		mem_bmr.value = (Math.floor((10*myweight+6.25*myheight-5*myold+5)*10))/10;
	}else{
		mem_bmr.value = (Math.floor((10*myweight+6.25*myheight-5*myold-161)*10))/10;
	}
}

//計算TDEE
function getMem_tdee(){
	if(myfrequency == 0){
		mem_tdee.value = (Math.floor((1.2*mem_bmr.value)*10))/10;
	}else if(myfrequency == 1){
		mem_tdee.value = (Math.floor((1.375*mem_bmr.value)*10))/10;
	}else if(myfrequency == 2){
		mem_tdee.value = (Math.floor((1.55*mem_bmr.value)*10))/10;
	}else if(myfrequency == 3){
		mem_tdee.value = (Math.floor((1.725*mem_bmr.value)*10))/10;
	}else{
		mem_tdee.value = (Math.floor((1.9*mem_bmr.value)*10))/10;
	}
}

</script>
<script>
    	var cancel = document.getElementById("input-cancel");
    	
    	cancel.addEventListener("click", function(){
    		window.location.href='<%=request.getContextPath()%>/front-end/bodyrecord/bodyRecord.jsp';
    	});
    
    </script>

</html>
<%}%>