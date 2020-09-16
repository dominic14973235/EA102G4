<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dayoff.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	DayoffService DayoffSvc = new DayoffService();
	List<DayoffVO> list = DayoffSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

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
				<h3>所有員工資料 - listAllEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
			<th>ctime_no</th>
			<th>教練編號</th>
			<th>教練會員編號</th>
			<th>教練姓名</th>
			<th>休息日</th>
			<th>休息小時</th>

		</tr>

		<c:forEach var="dayoffVO" items="${list}">

			<tr>
			<td>${dayoffVO.ctime_no} </td>
				<td>${dayoffVO.pro_ID}</td>
				<td>${dayoffVO.mem_ID}</td>
				<td>
					<c:forEach var="memVO" items="${memSvc.all}">
						<c:if test="${dayoffVO.mem_ID==memVO.mem_id}">
	                    ${memVO.mem_name}
                    	</c:if>
					</c:forEach>
				</td>
				<td>${dayoffVO.close_date}</td>
				<td>${dayoffVO.hr}</td>


				<td>
					<FORM METHOD="post"
						ACTION="dayoff.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="ctime_no" value="${dayoffVO.ctime_no}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="dayoff.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="ctime_no" value="${dayoffVO.ctime_no}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>