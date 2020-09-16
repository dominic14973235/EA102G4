<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupclass.model.*"%>

<%
  GroupClassVO gcVO = (GroupClassVO) request.getAttribute("gcVO");
%>
<%= gcVO==null %>--${gcVO==null}<br>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增團課 - addGroupClass.jsp</title>

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
		 <h3>新增團課 - addGroupClass.jsp</h3></td><td>
		 <h4><a href="groupClassSelectPage.jsp"><img src="../images/123.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="groupClass.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>團課名稱:</td>
		<td><input type="TEXT" name="g_name" size="45" 
			 value="<%= (gcVO==null)? "多人運動" : gcVO.getG_name()%>" /></td>
	</tr>
	<tr>
		<td>團課類別:</td>
		<td><input type="TEXT" name="c_type_no" size="45"
			 value="<%= (gcVO==null)? "CT001" : gcVO.getC_type_no()%>" /></td>
	</tr>
		<tr>
		<td>一堂多少:</td>
		<td><input type="TEXT" name="p_coin" size="45"
			 value="<%= (gcVO==null)? "300" : gcVO.getP_coin()%>" /></td>
	</tr>
	<tr>
		<td>上課地點:</td>
		<td><input type="TEXT" name="loc" size="45"
			 value="<%= (gcVO==null)? "台北市內湖區三劍客" : gcVO.getLoc()%>" /></td>
	</tr>
	<tr>
		<td>教練ID:</td>
		<td><input type="TEXT" name="pro_id" size="45"
			 value="<%= (gcVO==null)? "PRO1000001" : gcVO.getPro_id()%>" /></td>
	</tr>
	<tr>
		<td>人數上限:</td>
		<td><input type="TEXT" name="g_max" size="45"
			 value="<%= (gcVO==null)? "5" : gcVO.getG_max()%>" /></td>
	</tr>
	
	<tr><td>圖片上傳:</td>
	<td><input type="file" id="g_pic" name="g_pic" /></td></tr>
	
	<tr><td>團課介紹:</td>
	<td><textarea cols="30" rows="10" name="g_detail" ><%= (gcVO==null)? "安安" : gcVO.getG_detail()%></textarea>
	</td></tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>
<button id="button">新增團課時間</button>
	<Form id="gchr" METHOD="post" ACTION="groupClass.do">

		<input type="hidden" name="g_class_no"
			value="<%=gcVO.getG_class_no()%>"> <input type="hidden"
			name="action" value="insertForGCTime"> <input type="hidden"
			name="addCount"> <input type="submit" name="送出">
	</Form>