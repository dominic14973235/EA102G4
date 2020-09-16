<%@ page contentType="text/html; charse=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.classType.model.*"%>
<%@ page import="com.license.model.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="classTypeSvc" scope="page"
	class="com.classType.model.ClassTypeService" />
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
%>


<head>

<title>專業資格申請</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table td, table th {
	border: 1px solid #ddd;
	padding: 8px;
}
</style>

</head>

<body>

	<p align="center">資格申請</p>
	<form id="myForm" name="myForm" action="pro.do" method="POST" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td>姓名</td>
				<td><input type="hidden" name="mem_id" value="${ memVOLogin.mem_id}">${ memVOLogin.mem_name}
				</td>
			</tr>



			<tr>
				<td>課程類別</td>
				<td><c:forEach var="ClassTypeVO" items="${classTypeSvc.all}">
						<label> <input type="checkbox" name="c_type_no"
							value="${ClassTypeVO.c_type_no}">${ClassTypeVO.t_name}
						</label>

					</c:forEach></td>
			</tr>

			<tr>
				<td>證照名稱</td>
				<td><input type="text" name="lic_name" value=""> 證照編碼 
				<input type="text" name="no_reg"> <input type="file" name="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
			
			<tr>
				<td>證照名稱</td>
				<td><input type="text" name="lic_name" value=""> 證照編碼 
				<input type="text" name="no_reg"> <input type="file" name="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
			
			<tr>
				<td>證照名稱</td>
				<td><input type="text" name="lic_name" value=""> 證照編碼 
				<input type="text" name="no_reg"> <input type="file" name="l_pic">
					<!-- <div id="preview"> --></td>
			</tr>
			

			<tr>
				<td>經歷</td>
				<td><textarea cols="100" rows="10" name="expr" placeholder="請輸入經歷...">
					</textarea>
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="action" value="insert"> 
					<input type="submit" value="送出">
				</td>
			</tr>

		</table>

	</form>
	
	 <script type="text/javascript">
  var i = 1;
  $("#gchr").on("click",".remove",function(){
     $(this).parent("div").remove();
     i--;
   });

  $("#button").click(function(){
      $("#gchr").append('<div class="time'+i+'"><input type="date" name="c_date'+i+'"><input type="text" name="hr'+i+'"><button class="remove">刪除</button><br></div>');
      $("input[name='addCount']").val(i);
      $(":button").click(function(e) {
      e.preventDefault();
   });
   i++;
  });

 </script>
	
	
</body>
</html>