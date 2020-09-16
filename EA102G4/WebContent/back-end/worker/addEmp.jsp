<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.worker.model.*"%>

<%
	WorkerVO workerVOlogin = (WorkerVO) session.getAttribute("workerVOLogin");
	WorkerVO workerVO = (WorkerVO) request.getAttribute("workerVO");
	String wp = (String)request.getAttribute("wp");
    pageContext.setAttribute("wp",wp);
%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>新增員工</title>

  <!-- Custom fonts for this template-->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

  <style>
    table {
      width: 500px;
      background-color: #00FFFF;
      margin-top: 1px;
      margin-bottom: 1px;
   	  border: 8px #FFD382 groove; 
    }
    table, th, td {
      border: 0px solid #CCCCFF;
    }
    th, td {
      padding: 1px;
    }
  </style>

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/back-end/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-dumbbell"></i>   
        </div>
        <div class="sidebar-brand-text mx-3">IN GYM</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        	選單列表
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#one" aria-expanded="true" aria-controls="one">
          <i class="far fa-grin"></i>
          <span>員工</span>
        </a>
        <div id="one" class="collapse show" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Worker Components:</h6>
			<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/worker/listAllEmp.jsp">搜尋員工</a> 
			<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/worker/addEmp.jsp">新增員工</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#two" aria-expanded="true" aria-controls="two">
          <i class="fas fa-user"></i>
          <span>會員</span>
        </a>
        <div id="two" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Member Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/mem/listAllMem.jsp">管理會員</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#three" aria-expanded="true" aria-controls="three">
          <i class="fas fa-object-ungroup"></i>
          <span>課程</span>
        </a>
        <div id="three" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Class Components:</h6>
			<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/class/listAllClassType.jsp">管理課程</a>
			<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/class/listCoachClassAuth.jsp">專長審查</a>
			<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/class/listCoachAuth.jsp">教練審查</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#four" aria-expanded="true" aria-controls="four">
          <i class="fas fa-store"></i>
          <span>商城</span>
        </a>
        <div id="four" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Mall Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp">商品管理</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/product/listAllOrder.jsp">訂單管理</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#six" aria-expanded="true" aria-controls="six">
          <i class="fas fa-bullhorn"></i>
          <span>檢舉</span>
        </a>
        <div id="six" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Report Components:</h6>
			<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/report/listAllReport.jsp">揪團檢舉</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#seven" aria-expanded="true" aria-controls="seven">
          <i class="fas fa-hamburger"></i>
          <span>食品資料庫</span>
        </a>
        <div id="seven" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Food Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/food/listAllFoodBackEnd.jsp">搜尋食物資料庫</a>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/food/addFood.jsp">新增食物資料</a>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <%	if (workerVOlogin != null) {%>
				<li class="nav-item dropdown no-arrow">
				<a class="nav-link dropdown-toggle" href="#" id="userDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <span
						class="mr-2 d-none d-lg-inline text-gray-600 small"><%=workerVOlogin.getW_name()%></span>
						<img class="img-profile rounded-circle"
						src="<%=request.getContextPath()%>/workerptool.do?work_id=<%=workerVOlogin.getWork_id()%>">
				</a> <!-- Dropdown - User Information -->
					<div
						class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
						aria-labelledby="userDropdown">
						<a class="dropdown-item" href="#" data-toggle="modal"
							data-target="#Setting"> <i
							class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
							Settings
						</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/back-end/worker/worker.do?action=logout">
							<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
							Logout
						</a>
					</div></li>
				<%} %>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">新增員工</h1>
          </div>

        </div>
        <!-- /.container-fluid -->

		<div class="row justify-content-center">

         

    <FORM METHOD="post" ACTION="worker.do" name="form1" enctype="multipart/form-data">
    	<%-- 錯誤表列 --%>
         <c:if test="${not empty errorMsgs}">
         <ul>
          <c:forEach var="message" items="${errorMsgs}">
          <li style="color:red">${message}</li>
         </c:forEach>
      	 </ul>
         </c:if>
      <table style="border:3px #cccccc solid;" cellpadding="10" border='0'>
      	<tr>
      	<td colspan=2><img src="images/gym-logo.jpg" width=100% height="100" border="0"></td>
      	</tr>
        <tr>
          <td Width="100">員工姓名:</td>
          <td><input type="TEXT" name="w_name" id="w_name" size="30" 
           value="<%= (workerVO==null)? "" : workerVO.getW_name()%>" /></td>
         </tr>
         <tr>
          <td>帳號:</td>
          <td><input type="TEXT" name="w_acc" id="w_acc" size="30"
           value="<%= (workerVO==null)? "" : workerVO.getW_acc()%>" /></td>
         </tr>
<!--          <tr> -->
<!--           <td>密碼:</td> -->
<!--           <td><input type="TEXT" name="w_pw" size="30" -->
<%--            value="<%= (workerVO==null)? "" : workerVO.getW_pw()%>" /></td> --%>
<!--          </tr> -->
         <tr>
          <td>信箱:</td>
          <td><input type="TEXT" name="email" id="email" size="30"
           value="<%= (workerVO==null)? "" : workerVO.getEmail()%>" /></td>
         </tr>
         <tr>
          <td>雇用日期:</td>
          <td><input name="reg_date" id="f_date1" type="text"></td>
        </tr>
        <tr>
          <td>圖片:</td>
          <td><input type="file" name="photo" onchange="loadImageFile(event)"></td>
        </tr>
        <tr>
      	<td colspan="2" height=200><img id="image" style="display:block; margin:auto;" ></td>
      	</tr>
      	<tr>
      		<td colspan="2">權限:
      		<button type="button" id="checkAll">全選</button>
      		<button type="button" id="cancel">清除</button>
      		</td>
      	<tr>
      	<tr>    	
      		<td colspan="2">
      		<jsp:useBean id="PowerListSvc" scope="page" class="com.powerlist.model.PowerListService"/>
			<c:forEach var="powerlistVO" items="${PowerListSvc.all}">
				<input type="checkbox" name="POWER" value="${powerlistVO.power_id}" 
			  ${fn:contains(wp, powerlistVO.power_id)? 'checked':''}>${powerlistVO.power_name}
			</c:forEach></td>   	
      	</tr>
      </table>
  			
      <input type="hidden" name="action" value="insert">
      <input type="submit" value="送出新增">
	  <button type="button" id="magic">小按鈕</button>
		
      </FORM>
      </div>

    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
    <footer class="sticky-footer bg-white">
      <div class="container my-auto">
        <div class="copyright text-center my-auto">
          <span>Copyright &copy; Your Website 2020</span>
        </div>
      </div>
    </footer>
    <!-- End of Footer -->

  </div>
  <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="assets/js/sb-admin-2.min.js"></script>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date reg_date = null;
  try {
    reg_date = workerVO.getReg_date();
   } catch (Exception e) {
    reg_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
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

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
         theme: '',              //theme: 'dark',
         timepicker:false,       //timepicker:true,
         step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
         format:'Y-m-d',         //format:'Y-m-d H:i:s',
       value: '<%=reg_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:             '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //            if (  date.getYear() <  somedate1.getYear() || 
        //               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //            if (  date.getYear() >  somedate2.getYear() || 
        //               (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //               (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //            if (  date.getYear() <  somedate1.getYear() || 
        //               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //                 ||
        //                date.getYear() >  somedate2.getYear() || 
        //               (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //               (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
<script>
  function loadImageFile(event){ 
    var image = document.getElementById('image');
    image.src = URL.createObjectURL(event.target.files[0]); 
    image.height= 200;
    image.width= 200;
  };
  
  $("#magic").click(function(){
	  $("#w_name").val("牛番茄");
	  $("#w_acc").val("tomato79");
	  $("#email").val("EA10241YEN@gmail.com");
  });
  
  $("#checkAll").click(function(){
  	$("input[name='POWER']").prop("checked",true);//把所有的核取方框的property都變成勾選
  });
  $("#cancel").click(function(){
  	$("input[name='POWER']").prop("checked",false);//把所有的核取方框的property都變成不勾選
  });
	 
</script>

</html>
