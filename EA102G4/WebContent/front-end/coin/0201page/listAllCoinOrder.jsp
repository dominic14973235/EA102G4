<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coin.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CoinService coinSvc = new CoinService();
    List<CoinVO> list = coinSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有課金資料 - listAllCoinOrder.jsp</title>

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
	<tr><td>
		 <h3>所有課金盤子 - listAllCoinOrder.jsp</h3>
		 <h4><a href="select_coin_order.jsp"><img src="images/321.jpeg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>課金編號</th>
		<th>課金會員</th>
		<th>課金時間</th>
		<th>課金$$</th>
		<th>課金點數</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="coinVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${coinVO.coin_id}</td>
			<td>${coinVO.mem_id}</td>
			<td><fmt:formatDate value="${coinVO.coin_date}" pattern="yyyy-MM-dd"/></td>
			<td>${coinVO.amount}</td>
			<td>${coinVO.deposit_coin}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/coin/coin.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coin_id"  value="${coinVO.coin_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>