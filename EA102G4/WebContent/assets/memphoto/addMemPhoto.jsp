<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.memphoto.model.*"%>
<%-- <%@ page import="com.mem.model.*"%> --%>

<%
	MemPhotoVO memPhotoVO = (MemPhotoVO) request.getAttribute("memPhotoVO");
// 	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>
<%=memPhotoVO == null%>--${memPhotoVO==null}--
<br>${memPhotoVO.photo_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員照片新增 - addBodyRecord.jsp</title>

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
		<tr>
			<td>
				<h3>身體數據資料新增 - addMem.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="memphoto.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
<!-- 			<td>會員編號:</td> -->
				<td><input type="hidden" name="mem_id" size="30" value="MEM0000001" /></td> <!-- ${memVO.mem_id} -->
			</tr>
			
			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" size="10" id="photo" accept="image/gif, image/jpeg, image/png"/></td>
			</tr>
			<tr>
				<td><img id="img_content"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>


var photo = document.getElementById('photo');
var img = document.getElementById('img_content')

//上傳圖片
photo.addEventListener('change', function(){
	readURL(this);
});

//顯示圖片
function readURL(input){
	if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       img.setAttribute('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
}


</script>

</html>