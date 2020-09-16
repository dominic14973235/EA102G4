<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), 存入req的memVO物件
%>

<html>
<head>
<title>會員資料 - listOneMem.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員資料 - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>姓名</th>
		<th>密碼</th>
		<th>生日</th>
		<th>性別</th>
		<th>地址</th>
		<th>電子信箱</th>
		<th>手機號碼</th>
		<th>請假次數</th>
		<th>點數</th>
		<th>自我介紹</th>
		<th>註冊時間</th>
		<th>賣家權限</th>
		<th>發文權限</th>
		<th>留言權限</th>
	</tr>
	<tr>
		<td><%=memVO.getMem_id()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_psw()%></td>
		<td><%=memVO.getMem_bir()%></td>
		<td><%=memVO.getSex()%></td>
		<td><%=memVO.getMem_addr()%></td>
		<td><%=memVO.getMem_email()%></td>
		<td><%=memVO.getMem_phone()%></td>
		<td><%=memVO.getMem_absent()%></td>
		<td><%=memVO.getCoin()%></td>
		<td><%=memVO.getMem_resume()%></td>
		<td><%=memVO.getM_reg_date()%></td>
		<td><%=memVO.getSel_auth()%></td>
		<td><%=memVO.getArt_auth()%></td>
		<td><%=memVO.getCom_auth()%></td>
		
	</tr>
</table>

</body>
</html>