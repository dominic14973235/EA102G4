<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	ProVO proVO = (ProVO) request.getAttribute("proVO");
%>

<%
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(proVO.getMem_ID());
%>

<html>
<head>
<title>listOnePRO.jsp</title>

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

		<tr>
			<td><%=proVO.getPro_ID() %></td>
			<td><%=proVO.getMem_ID() %></td>
			<td><%=memVO.getMem_name()%></td>
			<td>${proVO.t_rating/proVO.p_rating}</td>
			<td><%=proVO.getExpr() %></td>
			<td>
				<FORM METHOD="post"
					ACTION="pro.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="pro_ID" value="${proVO.pro_ID}"> <input type="hidden"
						name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="pro.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="pro_ID" value="${proVO.pro_ID}"> <input type="hidden"
						name="action" value="delete">
				</FORM>
			</td>
		</tr>

	</table>
</body>
</html>