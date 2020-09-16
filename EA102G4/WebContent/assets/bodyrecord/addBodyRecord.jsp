<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bodyrecord.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	BodyRecordVO bodyRecordVO = (BodyRecordVO) request.getAttribute("bodyRecordVO");
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>身體數據資料新增 - addBodyRecord.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>身體數據資料新增 - addMem.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="bodyrecord.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<!-- 			<td>會員編號:</td> -->
				<td><input type="hidden" name="mem_id" size="30"
					value=" ${memVOLogin.mem_id}" /></td>
				<!-- ${memVO.mem_id} -->
			</tr>
			<tr>
				<td>紀錄日期:</td>
				<td><input type="text" name="body_date" id="f_date1" /></td>
			</tr>
			<tr>
				<td>姓別:</td>
				<td>
					<%-- 			<%if (memVO.getSex().equals("男")) {%> --%> <input
					type="radio" id="male" name="sex" value="男" checked> <label
					for="male">男</label> <input type="radio" id="female" name="sex"
					value="女"> <label for="female">女</label><br> <%-- 			<%} else {%> --%>
					<!-- 			<input type="radio" id="male" name="sex" value="男"> --> <!-- 			<label for="male">男</label> -->
					<!-- 			<input type="radio" id="female" name="sex" value="女" checked> -->
					<!--			<label for="female">女</label><br> --> <%-- 			<%}--%>
				</td>

			</tr>
			<tr>
				<td>身高:</td>
				<td><input type="text" id="mem_height" name="mem_height"
					size="25"
					value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_height()%>" /></td>
			</tr>
			<tr>
				<td>體重:</td>
				<td><input type="text" id="mem_weight" name="mem_weight"
					size="25"
					value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_weight()%>" /></td>
			</tr>
			<tr>
				<td>BMI指數:</td>
				<td><input type="text" id="mem_bmi" name="mem_bmi" size="25"
					value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_bmi()%>" /><span
					id="bmi_value"></span></td>
			</tr>
			<tr>
				<td>年齡:</td>
				<td><input type="text" id="mem_old" name="mem_old" size="25"
					value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_old()%>" /></td>
			</tr>
			<tr>
				<td>基礎代謝率(BMR):</td>
				<td><input type="text" id="mem_bmr" name="mem_bmr" size="25"
					value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_bmr()%>" /></td>
			</tr>
			<tr>
				<td>運動頻率:</td>
				<td><select id="frequency" name="frequency">
						<option value="0" />久坐(辦公室工作、沒有運動習慣)
						<option value="1" />輕度(運動1-2天/週)
						<option value="2" />中度(運動3-5天/週)
						<option value="3" />高度(運動6-7天/週)
						<option value="4" />極高度(運動員等級，每天運動2次)
				</select>
			</tr>
			<tr>
				<td>每日總消耗熱量(TDEE):</td>
				<td><input type="text" id="mem_tdee" name="mem_tdee" size="25"
					value="<%=(bodyRecordVO == null) ? "" : bodyRecordVO.getMem_tdee()%>" /></td>
			</tr>

			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" size="10" id="photo"
					accept="image/gif, image/jpeg, image/png" /></td>
			</tr>
			<tr>
				<td><img id="img_content"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
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
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: "<%=body_date%>"
	});
</script>

<script>

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
var myheight, myweight, myold, myfrequency = 0, sex="男";


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

</html>