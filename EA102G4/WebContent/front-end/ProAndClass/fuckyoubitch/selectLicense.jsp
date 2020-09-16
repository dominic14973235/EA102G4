<%@ page contentType="text/html; charse=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>EA102G4 Pro:Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id='table-1'>
		<tr>
			<td><h3>EA102G4 Pro</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is home page for EA102G4</p>

	<h3>資料查詢:</h3>

	<!-- 錯誤列表 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllLicense.jsp'>List</a> all license. <br> <br></li>


		<li>
			<form method='post' action="license.do">
				<b>輸PRO編號(PRO1000000):</b> <input type='text' name='pro_ID'>
				<input type='hidden' name='action' value='getOne_For_Display'>
				<input type='submit' value='送出'>
			</form>
		</li>

		<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
		<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

		<li>
			<Form method="post" action="license.do">
				<b>選擇教練編號</b> <select size="1" name="pro_ID">
					<c:forEach var="proVO" items="${proSvc.all}">
						<option value="${proVO.pro_ID}">${proVO.pro_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">

			</Form>
		</li>

		<li>
			<Form method="post" action="license.do">
				<b>請選擇教練姓名</b> <select size="1" name="pro_ID">
					<c:forEach var="proVO" items="${proSvc.all}">
						<option value="${proVO.pro_ID}">${memSvc.getOneMem(proVO.getMem_ID()).mem_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</Form>
		</li>
	</ul>

	<h3>員工管理</h3>

	<ul>
		<li><a href='addDayoff.jsp'>Add</a> a new dayoff.</li>
	</ul>



</body>
</html>