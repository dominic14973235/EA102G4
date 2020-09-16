<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bodyrecord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	BodyRecordService bodyRecordSvc = new BodyRecordService();
	List<BodyRecordVO> list = bodyRecordSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有會員身體數據資料 - listAllBodyRecord.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有會員身體數據資料 - listAllBodyRecord.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>身體數據紀錄編號</th>
			<th>會員編號</th>
			<th>紀錄日期</th>
			<th>身高</th>
			<th>體重</th>
			<th>BMI指數</th>
			<th>年齡</th>
			<th>基礎代謝率(BMR)</th>
			<th>運動頻率</th>
			<th>每日總消耗熱量(TDEE)</th>
			<th>照片</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="bodyRecordVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${bodyRecordVO.body_no}</td>
				<td>${bodyRecordVO.mem_id}</td>
				<td><fmt:formatDate value="${bodyRecordVO.body_date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${bodyRecordVO.mem_height}</td>
				<td>${bodyRecordVO.mem_weight}</td>
				<td>${bodyRecordVO.mem_bmi}</td>
				<td>${bodyRecordVO.mem_old}</td>
				<td>${bodyRecordVO.mem_bmr}</td>
				<td><c:choose>
						<c:when test="${bodyRecordVO.frequency == '0'}">
       						 久坐(辦公室工作、沒有運動習慣) 
    					</c:when>
						<c:when test="${bodyRecordVO.frequency == '1'}">
       						 輕度(運動1-2天/週)
						</c:when>
						<c:when test="${bodyRecordVO.frequency == '2'}">
        					中度(運動3-5天/週)
						</c:when>
						<c:when test="${bodyRecordVO.frequency == '3'}">
        					高度(運動6-7天/週)
						</c:when>
						<c:otherwise>
        					極高度(運動員等級，每天運動2次)
						</c:otherwise>
					</c:choose></td>
				<td>${bodyRecordVO.mem_tdee}</td>
				<td>${bodyRecordVO.photo}</td>
				
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>

</html>