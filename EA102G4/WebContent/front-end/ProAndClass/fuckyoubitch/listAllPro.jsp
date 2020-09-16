<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>

<%
	ProService proSvc = new ProService();
	List<ProVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<meta charset="BIG5">
<title>listAllPro.jsp</title>

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
			<th>Mem_name</th>
			<th>t_rating</th>
			<th>Expr</th>
			<th>修改</th>
			<th>刪除</th>

		</tr>
		<c:forEach var="proVO" items="${list}">
			<tr>
				<td>${proVO.pro_ID}</td>
				<td>${proVO.mem_ID}</td>
				<td><c:forEach var="memVO" items="${memSvc.all}">
						<c:if test="${proVO.mem_ID==memVO.mem_id}">
	                    ${memVO.mem_name}
                    	</c:if>
					</c:forEach></td>
				<td>${proVO.t_rating/proVO.p_rating}</td>
				<td>${proVO.expr}</td>
				<td>
					<FORM METHOD="post" action="pro.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="pro_ID" value="${proVO.pro_ID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" action="pro.do" style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="pro_ID" value="${proVO.pro_ID}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>


		</c:forEach>

	</table>

</body>
</html>