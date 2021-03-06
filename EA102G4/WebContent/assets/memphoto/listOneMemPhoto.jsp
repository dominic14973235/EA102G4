<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memphoto.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%

// List<BodyRecordVO> list = (List<BodyRecordVO>)request.getAttribute("list"); //BodyRecordServlet.java(Concroller), 存入req的bodyRecordVO物件
MemPhotoService memPhotoSvc = new MemPhotoService();
List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto("MEM0000001"); //session.getAttribute("memVO").mem_id
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>會員照片 - listOneMemPhoto.jsp</title>

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
				<h3>會員照片資料 - listOneMemPhoto.jsp</h3>
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
			<th>照片編號</th>
			<th>會員編號</th>
			<th>照片</th>
			<th>照片狀態</th>
			<th>修改資料</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="memPhotoVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${memPhotoVO.photo_no}</td>
				<td>${memPhotoVO.mem_id}</td>
				<td>${memPhotoVO.photo}</td>
				<td>${memPhotoVO.photo_status}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/memphoto/memphoto.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改資料"> <input type="hidden"
							name="photo_no" value="${memPhotoVO.photo_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/memphoto/memphoto.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="photo_no" value="${memPhotoVO.photo_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
			
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>

</html>