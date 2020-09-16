<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.grouphour.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.godetail.model.*" %>
<%@ page import="com.classType.model.*" %>
<%
	MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
	ProService proSvc =new ProService();
	MemService memSvc = new MemService();
    GroupClassVO gcVO = (GroupClassVO) request.getAttribute("gcVO");
    ProVO proVO = proSvc.getOnePro(gcVO.getPro_id());
    GroupClassService gcSvc =  new GroupClassService();
    GroupHourService ghSvc = new GroupHourService();
    ClassTypeService ctSvc = new ClassTypeService();
    List<GroupClassVO> proClass = gcSvc.getGroupClassesByProId(proVO.getPro_ID())
    		.stream()
    		.filter(proGcVO -> proGcVO.getC_status()==1
    				&&gcSvc.getAllGhSize(proGcVO.getG_class_no())>=1
    				&&!(proGcVO.getG_class_no().equals(gcVO.getG_class_no())))
			.collect(Collectors.toList());
    pageContext.setAttribute("proClass", proClass);
    pageContext.setAttribute("gcSvc", gcSvc);
    request.setAttribute("set",ghSvc.getAllByGroupClassNo(gcVO.getG_class_no()));
    GroupOrderDetailService godSvc = new GroupOrderDetailService(); 
	List<GroupHourVO> list = ghSvc.getAll();
	list= list.stream()
		 	.filter(ghVO -> ghVO.getG_class_no().equals(gcVO.getG_class_no()))
		 	.filter(ghVO -> ghVO.getC_date().getTime()+ghVO.getHr().indexOf('1')*3600000 > new Date().getTime())
		 	.filter(ghVO -> godSvc.getDetailsByGTimeNo(ghVO.getG_time_no(), 0).size() <= gcSvc.getOneGroupClass(ghVO.getG_class_no()).getG_max())
			.sorted((gh1,gh2)->gh2.getHr()
			.compareTo(gh1.getHr()))
			.sorted((gh1,gh2)->gh1.getC_date()
			.compareTo(gh2.getC_date()))
			.collect(Collectors.toList());
	request.setAttribute("list",list);

%>
<%		
// 這區塊記得之後要刪
// 	memVOLogin=memSvc.getOneMem("MEM0000001");
// 	session.setAttribute("memVOLogin", memVOLogin);
%>


<!DOCTYPE html>
<html lang="en">	
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Zarxio Fitness & Gym HTML Template</title>
     <!-- favicon -->
     <link rel=icon href=assets/img/favicon.png sizes="20x20" type="image/png">
    <!-- animate -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <!-- Slick -->
    <link rel="stylesheet" href="assets/css/slick.css" />
    <link rel="stylesheet" href="assets/css/slick-theme.css" />
    <!-- owl carousel -->
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <!-- fontawesome -->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <!-- flaticon -->
    <link rel="stylesheet" href="assets/css/flaticon.css">
    <!-- hamburgers -->
    <link rel="stylesheet" href="assets/css/hamburgers.min.css">
    <!-- nice select -->
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="assets/css/style.css">
     <!-- responsive Stylesheet -->
     <link rel="stylesheet" href="assets/css/responsive.css">
     
      <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
	
	<style>
.blog-breadcrumb-overlay{
    background-image:url(img/gc.png) !important;
/*     background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(assets/img/1244995.jpg);  */
    background-position: 97px -340px;
    background-size: 90%;
}
.calendar{
color:black;
}
.modal-body{
color:black;
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
            <button type="submit" class="submit-btn"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <!-- //. search Popup -->

   
<%@ include file="../artInclude/navbar.file"%>

    <!-- breadcrumb area -->
    <div class="breadcrumb-style-1 blog-breadcrumb-overlay">
        <div class="breadcrumb-inner">
            <h1 class="page-title">Class Details</h1>
            <ul class="page-list margin-bottom-0 margin-top-3">
                <li><a href="index.html">Home</a></li>
<!--                 <li><a href="#">Class Details</a></li> -->
            </ul>
        </div>
    </div>
    <!-- breadcrumb area end -->

    <!-- class details start -->
    <div class="class-details-area">
        <main id="main" class="site-main">
            <div class="container">
                <div class="row">
                 <div class="class-details col-lg-4">
                            <div class="class-img ">
                                <img src="../groupclass/groupClass.do?g_class_no=<%=gcVO.getG_class_no()%>" class="attachment-siiimple_full size-siiimple_full wp-post-image" style="max-width:100%;max-height:100%;" alt="">	
                            </div>
                           	
                        </div>
                 <div class="col-lg-8">
                        <div class="class-right-content widget-area sidebar">
<!--                             <div class="widget widget_search"> -->
<!--                                 <form class="search-form"> -->
<!--                                     <div class="form-group"> -->
<!--                                         <input type="text" name="search" class="form-control" placeholder="Search here"> -->
<!--                                     </div> -->
<!--                                     <button class="submit-btn" type="submit"><i class="fa fa-search"></i></button> -->
<!--                                 </form> -->
<!--                             </div> -->
<!--                             <div class="widget"> -->
<!--                                 <div class="thumb only-thumb"> -->
<%--                                     <img class="mt-0" src="<%=request.getContextPath()%>/front-end/memphoto/memphoto.do?mem_id=<%=proVO.getMem_ID()%>" alt=""> --%>
<!--                                 </div> -->
<!--                             </div> -->
                            <div class="widget widget_tag_cloud">
                                <h5 class="widget-title">教練 : <%=memSvc.getOneMem(proVO.getMem_ID()).getMem_name()%></h5>			
                                <br>
                                <p class="widget-title othergc">其他上架中課程</p>
                                <ul class="tagcloud proGC">
                                <c:forEach var="proGcVO" items="${proClass}">
                                    <li class ="othergcname"><a href="<%=request.getContextPath()%>/front-end/groupclass/groupClass.do?action=getOne_For_Display&g_class_no=${proGcVO.g_class_no}">${proGcVO.g_name}</a></li>
                                </c:forEach>
                                </ul>
                            </div>
<!--                             <div class="widget widget_categories"> -->
<!--                                 <h5 class="widget-title">Categories</h5>			 -->
<!--                                 <ul class="cat-menu"> -->
<!--                                     <li class="proAuth"><a href="#">Boxing</a></li> -->
<!--                                     <li><a href="#">Street</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li> -->
<!--                                     <li><a href="#">Uncategorized</a></li> -->
<!--                                 </ul> -->
<!--                             </div> -->
                        </div>
                    </div>
                    <div class="col-lg-12">
                       
                        <!-- biography area -->
                        <div class="biography-area margin-top-60">
                            <div class="biography-content">
                                <ul class="nav nav-pills">
                                    <li>
                                        <a data-toggle="pill" href="#home" class="active">課程詳情</a>
                                    </li>
                                    <li>
                                        <a data-toggle="pill" href="#menu1">開課時間</a>
                                    </li>
                                    <li>
                                        <a data-toggle="pill" href="#menu2">Review</a>
                                    </li>
                                </ul>
            
                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active show">
                                        <div class="tab-content-description">
                                            <div class="class-content">
                                                <h2 class="margin-top-5"><%=gcVO.getG_name()%></h2>
                                                <p></p>
                                                <p><%=gcVO.getG_detail()%></p>
                                            </div>
                                            <div class="class-list d-sm-flex justify-content-between">
                                                <ul>
                                                    <li><i class="fa fa-check"></i>課程類別:<%=ctSvc.getOneClassType(gcVO.getC_type_no()).getT_name()%></li>
                                                    <li><i class="fa fa-check"></i>上課地點:<%=gcVO.getLoc()%></li>
                                                    <li><i class="fa fa-check"></i>一堂金額:<%=gcVO.getP_coin() %></li>
                                                </ul>
<!--                                                 <ul> -->
<!--                                                     <li><a href="#"><i class="fa fa-check"></i> Pellentesque pellentesque odio et porta.</a></li> -->
<!--                                                     <li><a href="#"><i class="fa fa-check"></i> Proin et augue et justo accumsan.</a></li> -->
<!--                                                     <li><a href="#"><i class="fa fa-check"></i> Donec viverra urna id congue.</a></li> -->
<!--                                                 </ul> -->
                                            </div>
                                        </div>
                                    </div>
                                    
<%--                                     <c:forEach var="godVO" items="${orderDetails}"> --%>
<!--                                     <form action="groupOrder.do" METHOD="post"> -->
<%--                                    <p>${godVO.g_class_no}                         --%>
<%--                                     ${godVO.rdate} --%>
<%--                                     ${godVO.hr}</p> --%>
<!--                                     <input type="submit"  name="送出"  value="刪除"> -->
<!--                                     <input type="hidden" name="cart" value="delete">  -->
<!--                                     <input type="hidden" name="action" value="orderDetailList">  -->
<%--                                     <input type="hidden" name="g_time_no" value="${godVO.g_time_no }">  --%>
<%--                                     <input type="hidden" name="g_class_no" value="${godVO.g_class_no }">  --%>
<!--                                     </form> -->
<!--                                     <br> -->
                                    
<%--                                     </c:forEach> --%>
                                   <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

  										
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="tab-content-description">
                                            <h2 class="margin-top-10 margin-bottom-30"></h2>
                                              <c:forEach var="ghVO" items="${list}">
                                            <div class="d-flex justify-content-between align-items-center text-center margin-bottom-15">
                                                <h4 class="course-overview"><i class="fa fa-file"></i>${ghVO.c_date}  </h4><p class="hr">${ghVO.hr}</p>
                                                <div class="curiculam-right d-flex align-items-center">
                                                    <p>$<%=gcVO.getP_coin() %></p>
                                                    <div class="btn-wrapper">
                                                      <button class="addButton btn btn-element btn-normal-size btn-black-color"  <c:forEach var="godVO" items="${orderDetails}">${godVO.g_time_no eq ghVO.g_time_no ? "disabled" : ""} </c:forEach>>加入欲上課清單</button>
                                                       <input type="hidden" name="g_time_no" value="${ghVO.g_time_no}">
                                            <input type="hidden" name="g_class_no" value="${ghVO.g_class_no}">
                                            <input type="hidden" name="hr" value="${ghVO.hr}">
                                            <input type="hidden" name="rdate" value="${ghVO.c_date}">
                                            <input type="hidden" name="p_coin" value="<%=gcVO.getP_coin()%>">
                                            <input type="hidden" name="action" value="orderDetailList">
                                            <input type="hidden" name="cart" value="add">
<!--                                             <input type="hidden" name="change" value="buyOne"> -->
                                                    </div>
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div id="menu2" class="tab-pane fade">
                                        <div class="tab-content-description">
                                    <jsp:include page="${ request.getContextPath()}/front-end/FullCalendar/calendar_group_class.jsp" flush="true" />                            

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                        
                        <!-- biography end -->
                    </div>
                </div>
            </div>
        </main>
    </div>
    <!-- class details end -->

    <!-- class routine area -->
    
    <div class="class-routine">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 d-flex flex-md-row flex-column flex-wrap justify-content-sm-center justify-content-md-between text-center">
                 <div class="single-routine">
                        <h1></h1>
                        <h3></h3>
                    </div>
                    <div class="single-routine">
                        <h1></h1>
                        <h3></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- class routine area end -->

	
    <!-- jquery -->
    <script src="assets/js/jquery-2.2.4.min.js"></script>
    <!-- popper -->
    <script src="assets/js/popper.min.js"></script>
    <!-- bootstrap -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- magnific popup -->
    <script src="assets/js/jquery.magnific-popup.js"></script>
    <!-- Slick -->
    <script src="assets/js/slick.min.js"></script>
    <!-- Slick Animation -->
    <script src="assets/js/slick-animation.js"></script>
    <!-- wow -->
    <script src="assets/js/wow.min.js"></script>
    <!-- nice select -->
    <script src="assets/js/nice-select.js"></script>
    <!-- owl carousel -->
    <script src="assets/js/owl.carousel.min.js"></script>
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
       <%@ include file="/front-end/groupclass/cart.file"%>
    <script type="text/javascript">
    var g_name ='${gcVO.g_name}';
    var p_coin ='${gcVO.p_coin}';
	
    $(".proAuth").click(function(e){
    	e.preventDefault();
    })
    	$(".addButton").each(function(){
    		var isDisabled =$(this).attr('disabled');
    		if(isDisabled){
    			$(this).text('你已加進購物車');
    		}
    		var flag = ${memVOLogin==null};
    		if(flag){
    			$(this).text('請先登入會員');
    			$(this).attr('disabled','disabled');
    			
    		}else if(${proVOLogin.pro_ID eq gcVO.pro_id}){
    			$(this).text('你正在瀏覽自己團課');
    			$(this).attr('disabled','disabled');
    		}
    	});
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
// 		$(".addButton").click(function() {
// 			$(this).attr('disabled','disabled');
// 			$(this).text('你已加進購物車');
// 		});

		var flag = false;
		$(".addButton").click(function() {
			var thisBtn = $(this);
			$.ajax({
				type:"POST",
				url:"groupOrder.do",
				dataType:"text",
				data: {"action":"orderDetailList","cart":"add","mem_id":"${memVOLogin.mem_id}",
					"g_time_no":$(this).siblings().filter("input[name='g_time_no']").val(),
					"g_class_no":$(this).siblings().filter("input[name='g_class_no']").val(),
					"hr":$(this).siblings().filter("input[name='hr']").val(),
					"rdate":$(this).siblings().filter("input[name='rdate']").val(),
					"p_coin":$(this).siblings().filter("input[name='p_coin']").val()
					},
				error:function(){
					alert("error");
				},
				success:function(data){
					console.log(data);
					if(data==='success'){
						flag=true;
					Swal.fire(
							  '加入成功!',
							  '您所選取的團課已成功加入購物車!',
							  'success'
							)
							console.log(flag);
					$(thisBtn).attr('disabled','disabled');
					$(thisBtn).text('你已加進購物車');
					$('.godVO').append('<tr ><td><a href="#"><i class="far fa-trash-alt"></i></a></td><td>'
							+g_name+'</td><td>$ '+p_coin+"</td></tr>");
					var cartSize=$('#cartSize').text();
					cartSize=parseInt(cartSize);
					$('#cartSize').text(cartSize+1);
					console.log($('#cartSize').text());
					}else{
						Swal.fire({
							  icon: 'error',
							  title: '抱歉',
							  text: data+'!',
							  footer: ''
							})
					}
					console.log($(this));
// 					if(flag){
// 						$(this).attr('disabled','disabled');
// 						$(this).text('你已加進購物車');
// 						flag=false;
// 						}; 
				}
			
			});

		});
		
		$('.hr').each(function(){
			var hr = $(this).text();
			var leng =hr.indexOf(1);
			if(leng<10){
				$(this).text('0'+(hr.indexOf(1))+':00');
			}else{
				$(this).text(hr.indexOf(1)+':00');
			}
		});
		if($('.othergcname').size()==0){
			$('.othergc').hide();
		}
// 		<button class="addButton btn btn-element btn-normal-size btn-black-color">加入欲上課清單</button>
//         <input type="hidden" name="g_time_no" value="${ghVO.g_time_no}">
// <input type="hidden" name="g_class_no" value="${ghVO.g_class_no}">
// <input type="hidden" name="hr" value="${ghVO.hr}">
// <input type="hidden" name="rdate" value="${ghVO.c_date}">
<%-- <input type="hidden" name="p_coin" value="<%=gcVO.getP_coin()%>"> --%>
// <input type="hidden" name="action" value="orderDetailList">
// <input type="hidden" name="cart" value="add">
	</script>

</body>


  <%@ include file="/front-end/groupclass/socket.file"%>
  
</html>
</html>