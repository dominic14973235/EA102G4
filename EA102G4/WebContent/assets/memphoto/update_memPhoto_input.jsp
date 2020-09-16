<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.memphoto.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	MemPhotoVO memPhotoVO = (MemPhotoVO) request.getAttribute("memPhotoVO"); //BodeyRecordServlet.java (Concroller) 存入req的bodyRecordVO物件 (包括幫忙取出的bodyRecordVO, 也包括輸入資料錯誤時的bodyRecordVO物件)
%>
<%=memPhotoVO == null%>--${memPhotoVO==null}--
<br>${memPhotoVO.photo_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員照片修改 - update_memPhoto_input.jsp</title>

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
/* 會員無法自行更改的欄位 */

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
				<h3>會員照片修改 - update_memPhoto_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
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

	<FORM METHOD="post" ACTION="memphoto.do" name="form1">
		<table>
		<tr>
<!-- 				<td>照片編號:</td> -->
				<td><input type="hidden" name="photo_no" size="45"
					value="<%=memPhotoVO.getPhoto_no()%>" /></td>
			</tr>
			<tr>
				<td>照片狀態:</td>
				<td><select id="photo_status" name = "photo_status">
						<option value="P0" />
						<option value="P1" />設成大頭貼
				</select>
			
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="photo_no" value="<%=memPhotoVO.getPhoto_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>

</html>