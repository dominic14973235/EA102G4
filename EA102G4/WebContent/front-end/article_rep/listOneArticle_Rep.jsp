<%@page import="com.article_rep.model.Article_RepVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.art_comment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%Article_RepVO article_repVO = (Article_RepVO) request.getAttribute("article_repVO");%>

<%-- 取出 對應的DeptVO物件--%>

<html>
<head>
<title>檢舉資料 - listOneArticle_Rep.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>留言資料 - listOneArticle_Rep.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/article_rep/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
			<th>檢舉編號</th>
			<th>會員編號</th>
			<th>文章編號</th>
			<th>檢舉內容</th>
			<th>檢舉時間</th>
			<th>檢舉狀態</th>

	</tr>
	
	<tr>
		<td>${article_repVO.art_rep_no}</td>
		<td>${article_repVO.mem_id}</td>
		<td>${article_repVO.article_no}</td>
		<td>${article_repVO.rep_content}</td>
		<td>${SimpleDateFormat("yyyy/MM/dd hh:mm").format(article_repVO.rep_time)}</td>
		<td>${article_repVO.com_status == 'R0' ? "未處理":"處理"}</td>
		
	</tr>
</table>

</body>
</html>