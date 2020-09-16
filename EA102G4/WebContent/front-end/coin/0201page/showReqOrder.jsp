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
		 <h3>ㄋ訂單- showReqOrder.jsp</h3></td><td>
		 <h4><a href="select_coin_order.jsp"><img src="images/123.jpeg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>你ㄉ課金帳單:</h3>

<FORM METHOD="post" ACTION="coin.do" name="form1">
<table>
	<tr>
		<td>你是誰:</td>
		<td><%= (coinVO==null)? "MEM0100000" : coinVO.getMem_id()%>   <input type="submit" value="查詢該會員訂單"></td>
	</tr>
	<tr>
		<td>課多少:</td>
		<td>
			<%= (coinVO==null)? "2690" : coinVO.getAmount()%></td>
	</tr>
	<tr>
		<td>拿多少寶石:</td>
		<td>
			<%= (coinVO==null)? "9990" : coinVO.getDeposit_coin()%></td>
	</tr>
	<tr>
		<td>訂單:</td>
		<td>
			課金大勝利</td>
	</tr>
	<input type="hidden" name="mem_id" value="${coinVO.mem_id}">
	<input type="hidden" name="action" value="getMem_For_Display">
    </FORM>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>


</table>
<br>
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