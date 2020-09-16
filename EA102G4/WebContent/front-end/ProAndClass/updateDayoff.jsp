<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.dayoff.model.*"%>

<%
	DayoffVO dayoffVO = (DayoffVO) request.getAttribute("dayoffVO");
%>

<%
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(dayoffVO.getMem_ID());
%>



<html>
<head>
<title>updatePro.jsp</title>

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
	width: 450px;
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
<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>updateDayoff.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="dayoff.do">
		<table>
			<tr>
				<td>教練編號:<font color=red><b>*</b></font></td>
				<td><%=dayoffVO.getPro_ID()%></td>
			</tr>
			<tr>
				<td>教練的會員編號:</td>
				<td><%=dayoffVO.getMem_ID()%></td>
			</tr>
			<tr>
				<td>教練名稱:</td>
				<td><%=memVO.getMem_name()%></td>
			</tr>
			<tr>
				<td>休息日:</td>
				<td><input type="text" name="close_date"
					value="<%=dayoffVO.getClose_date()%>"></td>
			</tr>

			<tr>
				<td>休息時數:</td>
				<td><input type="text" name="hr" value="<%=dayoffVO.getHr()%>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="ctime_no" value="<%=dayoffVO.getCtime_no()%>">
		<input type="hidden" name="pro_ID" value="<%=dayoffVO.getPro_ID()%>">
		<input type="hidden" name="mem_ID" value="<%=dayoffVO.getMem_ID()%>">

		<input type="submit" value="送出修改">
	</FORM>
</body>

</body>
</html>