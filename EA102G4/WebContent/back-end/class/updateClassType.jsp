<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.classType.model.*"%>

<%
ClassTypeVO classTypeVO = (ClassTypeVO) request.getAttribute("classTypeVO");
%>

<html>
<head>
<title>課程更新</title>

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

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>updateDayoff.jsp</h3> -->
<!-- 				<h4> -->
<%-- 					<a href="<%=request.getContextPath()%>/select_page.jsp"><img --%>
<!-- 						src="images/back1.gif" width="100" height="32" border="0">回首頁</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- 	<h3>資料修改:</h3> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="classType.do">
		<table>
			<tr>
				<td>課程類別編號:<font color=red><b>*</b></font></td>
				<td><%=classTypeVO.getC_type_no()%></td>
			</tr>
			<tr>
				<td>課程類別名稱:</td>
				<td><input type="text" name="t_name"
					value="<%=classTypeVO.getT_name()%>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="c_type_no" value="<%=classTypeVO.getC_type_no()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>

</body>
</html>