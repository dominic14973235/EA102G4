<%@page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.dayoff.model.*"%>
<%@page import="java.util.*"%>


<%
List <DayoffVO> list = (List<DayoffVO>)request.getAttribute("dayoffVO");
// String str = request.getParameter("pro_ID");
// DayoffService DayoffSvc = new DayoffService();
// List<DayoffVO> list = DayoffSvc.getOneProDayoff(str);
pageContext.setAttribute("list", list);
%>

<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />



<html>
<head>
<title>listProDayoff.jsp</title>

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
<body>
	<table>
		<tr>
			<th>Pro_ID</th>
			<th>Mem_ID</th>
			<th>教練姓名</th>
			<th>休息日</th>
			<th>休息小時</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		
		<c:forEach var="dayoffVO" items="${list}">

			<tr>
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