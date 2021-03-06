<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>IBM SportRecord: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<%List<String> list = null;%>

<table id="table-1">
   <tr><td><h3>IBM SportRecord: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM SportRecord: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllSportRecord.jsp'>List</a> all SportRecords.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="sportrecord.do" >
        <b>輸入會員編號 (如MEM0000001):</b>
        <input type="text" name="mem_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='addSportRecord.jsp'>Add</a> a new SportRecord.</li>
</ul>

</body>
</html>