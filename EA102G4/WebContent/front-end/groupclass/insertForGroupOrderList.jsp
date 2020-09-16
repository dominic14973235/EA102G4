<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.godetail.model.*"%>
<%@ page import="com.pro.model.*"%>
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	GroupClassService gcSvc = new GroupClassService();
	pageContext.setAttribute("gcSvc", gcSvc);
	GroupOrderDetailService godSvc= new GroupOrderDetailService();
	pageContext.setAttribute("godSvc", godSvc);
	ProService proSvc =new ProService();
	pageContext.setAttribute("proSvc",proSvc);
	MemService memSvc = new MemService();
	MemVO memVO=(MemVO)session.getAttribute("memVOLogin");
%>
<%
// 	測試環境   不手動登入用
// 	memVO=memSvc.getOneMem("MEM0000001"); 
// 	pageContext.setAttribute("memVOLogin", memVO);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Zarxio Fitness & Gym HTML Template</title>
<!-- favicon -->
<link rel=icon href=assets/img/favicon.png sizes="20x20"
	type="image/png">
<!-- animate -->
<link rel="stylesheet" href="assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet" href="assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet" href="assets/css/slick.css" />
<link rel="stylesheet" href="assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet" href="assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet" href="assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet" href="assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet" href="assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet" href="assets/css/responsive.css">

 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
<style type="text/css">
.gcpic{
   width: 40%;
   height: 50%;
}
</style>
</head>
<body onload="connectByGC();" onunload="disconnectByGC();">

	<!-- preloader area start -->
	<div class="preloader" id="preloader">
		<div class="preloader-inner">
			<div class="spinner">
				<div class="dot1"></div>
				<div class="dot2"></div>
			</div>
		</div>
	</div>
	<!-- preloader area end -->

	<!-- search Popup -->
	<div class="body-overlay" id="body-overlay"></div>
	<div class="search-popup" id="search-popup">
		<form action="index.html" class="search-form">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search.....">
			</div>
			<button type="submit" class="submit-btn">
				<i class="fa fa-search"></i>
			</button>
		</form>
	</div>
	<!-- //. search Popup -->

	<%@ include file="../artInclude/navbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 cart-breadcrumb-overlay"
		style="background-image: url(img/cartCOUT.jpg);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">團課購物車結帳</h1>
			<ul class="page-list margin-bottom-0 margin-top-10">
				<li><a href="index.html">Home</a></li>
				<li><a href="/EA102G4/front-end/groupclass/groupClassSelectPage.jsp">GroupClass List</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- cart content start -->
	<section class="cart-content-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-title">
						<h2>團課結帳</h2>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="single-cart-list">
						<table class="table table-borderless">
							<tbody id="cartBody">
								<c:forEach var="godVO" items="${orderDetails}">
								<input type="hidden" class="memidByPro" value="${proSvc.getOnePro(gcSvc.getOneGroupClass(godVO.g_class_no).pro_id).mem_ID}">
									<tr id="${godVO.g_time_no}" class="buyCart">

										<td><img
											src="<%=request.getContextPath()+"/front-end/groupclass/groupClass.do?g_class_no="%>${godVO.g_class_no}"
											alt="" max-width="100%" max-height="100%" class="gcpic"></td>
										<td ><p class="font-semibold">${gcSvc.getOneGroupClass(godVO.g_class_no).g_name}</p></td>
										<td  style="width:15%;"><p>${godVO.rdate}</p></td>
										<td><p class="hr">${godVO.hr}</p></td>
										<td>金額:<p class="money">${godVO.p_coin}</p></td>
										<td><button class="aButton" style="border-color: lightslategrey;
    font-size: 25px;
    font-family: cursive;">刪除</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="cart-total-list">
						<div class="cart-price-total">
							<p>
								Total Price <span class="float-right text-right"> 
								<c:set var="count" value="${0}" scope="page" />
								<c:forEach var="godVO" items="${orderDetails}">
									<c:set var="count" value="${count+gcSvc.getOneGroupClass(godVO.g_class_no).p_coin}" />
								</c:forEach>
									${count}
								</span>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="btn-wrapper">
						<button id="buyButton" 
							class="btn btn-block btn-element btn-lg-size btn-main-color btn-rounded">Procced
							To Checkout</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- cart content end -->

	  <%@ include file="../footerFile/pageFooter.file"%>
	  
	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->

	<!-- jquery -->
	<script src="assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script src="assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script src="assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script src="assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script src="assets/js/nice-select.js"></script>
	<!-- counter up -->
	<script src="assets/js/counter-up.js"></script>
	<!-- owl carousel -->
	<script src="assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<script src="assets/js/slick.min.js"></script>
	<!-- Slick Animation -->
	<script src="assets/js/slick-animation.js"></script>
	<!-- waypoint -->
	<script src="assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script src="assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script src="assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/script.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<!--
	官方說明文件：http://kamranahmed.info/toast
-->

<!-- 引用css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css">

<div class="container">
	<!--
		觸發的 a 或 butto n加上 class="js-trigger-toast"
		要顯示的句子就放 data-strings 裡
		有標題就放 data-head
		想改計時的顏色就放 data-color，沒有的話預設是 #CD0505
		想改維持的時間就放 data-time，沒有的話預設是 3000(3秒)
	-->
	<button type="button" class="js-trigger-toast" data-head="消息通知" data-strings="有會員購買你的團課哦" data-color="#2196F3" data-time="5000" style="display:none;"></button>
</div>


<!-- 引用js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
<script>
var dataStrigByGC;
	$(function() {
		var triggerToast = function(head, strings, color, time) {
			$.toast({
				heading: head,
				text: strings,
				loaderBg: color,
				hideAfter: Number(time)
			});
		}
		$('.js-trigger-toast').each(function() {
			$(this).on('click', function() {
				var h = $(this).data('head') || false,
						s = $(this).data('strings'),
						c = $(this).data('color') || '#CD0505',
						t = $(this).data('time') || 5000;
				triggerToast(h, s, c, t);
			});
		});
	});
</script>

	<script>

		$(".aButton").click(function() {
			console.log($(this).parent().parent().attr('id'));
			
			Swal.fire({
				  title: '您確定要刪除嗎?',
				  text: "",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '對,我要刪除!'
				}).then((result) => {
				  if (result.value) {
				    
					$(this).parent().parent().remove();
					var total=0;
					$('.money').each(function(){
						var money=$(this).text();
						total+=parseInt(money);
					});

					$('.float-right').text(total);
					console.log(total);
					$.ajax({
						type:"POST",
						url:"groupOrder.do",
						dataType:"text",
						data: {"action":"orderDetailList", "cart":"delete", "g_time_no":$(this).parent().parent().attr('id'),"mem_id":"${memVOLogin.mem_id}"},
						error:function(){
//		 					alert("error");
						},
						success:function(data){
							Swal.fire(
								      '成功刪除!',
								      '不用擔心,您還是可以再去購買此筆團課',
								      'success'
								    )
						}
					});
				  }
				})


		});
	</script>
	<script>
	var PointByGC = "/front-end/groupclass/GroupOrderSocket/${memVOLogin.mem_id}";
	var hostByGC = window.location.host;
	var pathByGC = window.location.pathname;
	var webCtxByGC = pathByGC.substring(0, pathByGC.indexOf('/', 1));
	var endPointURLByGC = "ws://" + window.location.host + webCtxByGC + PointByGC;
	var selfByGC = '${memVOLogin.mem_id}';
	var webSocketByGC;
	console.log(endPointURLByGC);
	function connectByGC() {
		webSocketByGC = new WebSocket(endPointURLByGC);
		webSocketByGC.onopen = function(event) {
			console.log('websocket-onopen from cart' + selfByGC);
			
		};
		webSocketByGC.onmessage = function(event) {
			console.log(event.data);
			dataStrigByGC=event.data;
			$('.js-trigger-toast').trigger('click');
		};
		webSocketByGC.onclose = function(event) {
			console.log("Disconnected!");
		};
	};
	
	$("#buyButton").click(function() {
		var flag = ${memVOLogin==null};
		if(flag){
			  Swal.fire({
				  title: '請先登入',
				  text: "",
				  icon: 'warning',
				  showCancelButton: false,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '現在登入'
				}).then((result) => {
					if (result.isConfirmed) {
//	 					 Swal.fire(
//	 						      'Deleted!',
//	 						      'Your file has been deleted.',
//	 						      'success'
//	 						    )
				  	var url = "<%=request.getContextPath()%>/front-end/mem/signin.jsp";
						console.log(url);
						var orderView = $(location).attr('href', url);
						var timeoutID = window.setTimeout( ( () => orderView ), 5000);
					}
					})
		  }else{
		$.ajax({
			type:"POST",
			url:"groupOrder.do",
			dataType:"text",
			data: {"action":"insertForOrderDetails","mem_id":"${memVOLogin.mem_id}"},
			error:function(data){
				console.log("buyButton error", data);
			},
			success:function(data){
				console.log("buyButton success", data);
				if('success'.match('('+data+')')){
					Swal.fire({
						  title:'購買成功!',
						  icon:'success',
				}).then((value) => {
					var pros =document.getElementsByClassName('memidByPro');
					for(var i=0;i<pros.length;i++){
						var proMemId = pros[i];
						
						console.log(proMemId.value);
						webSocketByGC.send(proMemId.value);
					}
					
					$('#cartBody').empty();
					$('.float-right.text-right').text('0');
<%-- 					var url = "<%=request.getContextPath()%>"+ "/front-end/groupclass/listOneOrder.jsp?g_order_no="+data; --%>
//					console.log(url);
// 					var orderView = $(location).attr('href', url);
// 					var timeoutID = window.setTimeout( ( () => orderView ), 5000);
				});
				}else if('餘額不足'.match('('+data+')')){
					Swal.fire({
						  title: data,
						  text: "若欲購買請先儲值點數補足差額!",
						  icon: 'warning',
						  showCancelButton: true,
						  confirmButtonColor: '#3085d6',
						  cancelButtonColor: '#d33',
						  confirmButtonText: '我要現在儲爆!'
						}).then((result) => {
						  if (result.value) {
							  url="<%=request.getContextPath()%>/front-end/coin/addCoinOrder.jsp";
							  $(location).attr('href', url);
						  }
						})
				}else{
					Swal.fire({
						  icon: 'error',
						  title: '注意',
						  text: data+'!',
						  footer: '?'
						})
					
				}
			}
		});
		  }
	});

	function disconnectByGC() {
		webSocketByGC.close();
	}
	</script>
	<script>
	$('.hr').each(function(){
				var hr = $(this).text();
				$(this).text(hr.indexOf(1)+'時');
				
			});
			
			</script>
			
</body>
</html>