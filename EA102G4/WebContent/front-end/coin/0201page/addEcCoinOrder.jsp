<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coin.model.*"%>
<%
  CoinVO coinVO = (CoinVO) request.getAttribute("coinVO");
%>
<%= coinVO==null %>--${coinVO==null}<br>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>課金 - showReqOrder.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>點數儲值- addCoinOrder.jsp</h3></td><td>
		 <h4><a href="select_coin_order.jsp"><img src="images/123.jpeg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>課金訂單++:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="coin.do" name="form1">
<table>
	<tr>
		<td>你是誰:</td>
		<td><input type="TEXT" name="mem_id" size="25" 
			 value="<%= (coinVO==null)? "MEM0100000" : coinVO.getMem_id()%>" /></td>
	</tr>
	<tr>
		<td>課多少:</td>
		<td><input type="TEXT" name="amount" size="25"
			 value="<%= (coinVO==null)? "2690" : coinVO.getAmount()%>" /></td>
	</tr>
	<tr>
		<td>拿多少寶石:</td>
		<td><input type="TEXT" name="deposit_coin" size="25"
			 value="<%= (coinVO==null)? "9990" : coinVO.getDeposit_coin()%>" /></td>
	</tr>
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>



</html>