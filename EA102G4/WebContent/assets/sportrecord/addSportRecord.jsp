<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.sports.model.*"%>

<%-- <%@ page import="com.mem.model.*"%> --%>

<%
	SportRecordVO sportRecordVO = (SportRecordVO) request.getAttribute("sportRecordVO");
	SportsVO sportsVO = (SportsVO) request.getAttribute("sportsVO");
// 	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>
<%=sportRecordVO == null%>--${sportRecordVO==null}--
<br>${sportRecordVO.record_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>運動紀錄新增 - addSportRecord.jsp</title>

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

	<FORM METHOD="post" ACTION="sportrecord.do" name="form1">
		<table>
			<tr>
<!-- 			<td>會員編號:</td> -->
				<td><input type="hidden" name="mem_id" size="30" value="MEM0000001" /></td> <!-- ${memVO.mem_id} -->
			</tr>
			<tr>
				<td>紀錄日期:</td>
				<td><input type="text" name="record_date" id="f_date1" /></td>
			</tr>
			
			<jsp:useBean id="sportsSvc" scope="page" class="com.sports.model.SportsService" />
			
			<tr>
				<td>運動項目:</td>
				<td>
 				<select id = "sport_no" name="sport_no" value = "<%=(sportRecordVO == null)?"":sportRecordVO.getSport_no() %>">
         			<c:forEach var="sportsVO" items="${sportsSvc.all}" > 
          				<option value="${sportsVO.sport_no}">${sportsVO.sport_name}
         			</c:forEach>   
       			</select>
				</td>
				
			</tr>
			<tr>
				<td></td>
				<td id="unit1"><input type="text" name="record1" value="<%=(sportRecordVO == null) ? "" : sportRecordVO.getRecord1()%>" /></td>
			</tr>
			<tr>
			<td></td>
				<td id="unit2"><input type="text" name="record2" value="<%=(sportRecordVO == null) ? "" : sportRecordVO.getRecord2()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date record_date = null;
	try {
		record_date = sportRecordVO.getRecord_date();
	} catch (Exception e) {
		record_date = new java.sql.Date(System.currentTimeMillis());
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
		   value: "<%=record_date%>"
	});
</script>

<script>

var sport_no = document.getElementById('sport_no');
var unit1 = document.getElementById('unit1');
var unit2 = document.getElementById('unit2');



sport_no.addEventListener('change', function(){
	if(sport_no.value == "SPORT00001"){
		unit1.innerHTML='<input type="text" name="unit1" value="" />公斤';
		unit2.innerHTML='<input type="text" name="unit2" value="" />次';
		unit2.style.display = "inline";
	}else if(sport_no.value == "SPORT00002" || sport_no.value == "SPORT00003"){
		unit1.innerHTML='<input type="text" name="unit1" value="" />公尺';
		unit2.innerHTML='<input type="text" name="unit2" value="" />分鐘';
		unit2.style.display = "inline";
	}else{
		unit1.innerHTML='<input type="text" name="unit1" value="" />分鐘';
		unit2.style.display = "none";
	}
});

window.onload = function(){
	if(sport_no.value == "SPORT00001"){
		unit1.append("公斤");
		unit2.append("次");
	}else if(sport_no.value == "SPORT00002" || sport_no.value == "SPORT00003"){
		unit1.append("公尺");
		unit2.append("分鐘");
		
	}else{
		unit1.append("分鐘");
		unit2.style.display = "none";
	}
};

</script>

</html>