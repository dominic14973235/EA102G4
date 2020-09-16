<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.individualClass.model.*"%>
<%@ page import="com.classType.model.*" %>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.license.model.*" %>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	//get VO from servlet
	IndividualClassVO individualClassVO = (IndividualClassVO) request.getAttribute("individualClassVO");
	pageContext.setAttribute("individualClassVO",individualClassVO);
	
	//get the pro name and for Calendar
	ProService proSvc = new ProService();
	ProVO proVO = proSvc.getOnePro(individualClassVO.getPro_ID());
	pageContext.setAttribute("proVO", proVO);

	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(proVO.getMem_ID());
	pageContext.setAttribute("memVO",memVO);
	
	//get Class Type name
	ClassTypeService ctSvc = new ClassTypeService();
	ClassTypeVO ctVO = ctSvc.getOneClassType(individualClassVO.getC_type_no());
	pageContext.setAttribute("ctVO",ctVO);
	
	LicenseService licenseSvc = new LicenseService();
	List <LicenseVO> licList = licenseSvc.findByPro(individualClassVO.getPro_ID());
	pageContext.setAttribute("licList",licList);
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
     .class-details-area .container .col-lg-12 > div:nth-child(2) > div {
	    display: flex;
	    flex-flow: wrap;
	 }
	 #home .tab-content-description .class-content > div {
	    display: flex;
	 }
	 #home .tab-content-description .class-content > div > div:nth-child(1),
	 .class-details-area .container .col-lg-12 > div:nth-child(2) > div > div:nth-child(1) {
	    width: 20%;
	 }
 	 #home .tab-content-description .class-content > div > div:nth-child(2), 
 	 .class-details-area .container .col-lg-12 > div:nth-child(2) > div > div:nth-child(2) { 
 	    width: 80%; 
 	 } 
	 .biography-area {
	 	width:100%;
	 }
/* 	 width: 100%; */
     .footer-area{
         margin-top: 25px;
     }
     
     .blog-breadcrumb-overlay {
	    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(assets/img/1244995.jpg);
	    background-position: 97px -340px;
	    background-size: 90%;
	 }

#className{
	text-align: center;
}


img.attachment-siiimple_full.size-siiimple_full.wp-post-image {
    float: left;
    margin-right: 15px;
    width: 50%;
}

.single-class-item .bottom-content p:nth-child(3) {
    min-height: 140px;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 4;

}

     
     </style>

</head>
<body>

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
            <h1 class="page-title"><%=individualClassVO.getC_name() %></h1>
            <ul class="page-list margin-bottom-0 margin-top-3">
                <li><a href="index.html">Home</a></li>
                <li><a href="#">Class Details</a></li>
            </ul>
        </div>
    </div>
    <!-- breadcrumb area end -->

    <!-- class details start -->
    <div class="class-details-area">
        <main id="main" class="site-main">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
<%--                            <h2 id="className"><%=individualClassVO.getC_name() %></h2> --%>
                           <div>
	                            <div class="class-img">
	                                 <img src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=iClassPhoto&pic_no=<%=individualClassVO.getI_class_no() %>" class="attachment-siiimple_full size-siiimple_full wp-post-image" alt="">	
	                            </div>
	                        </div>
	                        	
	                        <div>  
	                         	<div><div><h4>課程類別:</h4></div><div><h5>${ ctVO.t_name}</h5></div></div>
	                         	<div><div><h4>教練名稱:</h4></div><div><h5>${memVO.mem_name }</h5></div></div>
	                         	<div>
		                         	<div>
		                         		<h4>總評分 :</h4>
		                         	</div>
	                         		<div>
										
										<c:choose>
										<c:when test="${proVOLogin.p_rating!=0}">
											<h5>${proVOLogin.t_rating/proVOLogin.p_rating}</h5>
										</c:when>
										<c:otherwise>
											<h5>0</h5>
										</c:otherwise>
										</c:choose>
										
	                         		</div>
	                         	</div>	
	                         		
	 	                         	<div>
	 	                         		<div>
	 	                         			<h4>地址:</h4>
	 	                         		</div>
	 	                         		<div>
	 	                         			<h5>${individualClassVO.loc }</h5>
	 	                         		</div>
	 	                         	</div>
	 	                         	
	 	                         	
	 	                         	<div>
		 	                         	<div>
		 	                         	<h4>價格:</h4>
		 	                         	</div>
	 	                         		<div>
	 	                         			<h5>${individualClassVO.p_coin }</h5>
	 	                         		</div>
	 	                         	</div>
                      		
	                         		<div>
	                         			<div>
	                         				<h4>課程簡介:</h4>
	                         			</div>
	                         			<div>
	                         				<pre><h5> <%=individualClassVO.getC_detail() %> </h5></pre>
	                         			</div>
	                         		</div>
	                         		
	                         	</div>
                            </div>   
                    </div>
                 </div> 
                     
                        <!-- biography area -->
                    <div class="row">    
                        <div class="biography-area margin-top-60">
                            <div class="biography-content">
                                <ul class="nav nav-pills">
                                    <li>
                                        <a data-toggle="pill" href="#home" class="active">教練簡介</a>
                                    </li>
                                    <li>
                                        <a data-toggle="pill" href="#menu1">時間預約</a>
                                    </li>
                                </ul>
            
                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active show">
                                        <div class="tab-content-description">
                                            <div class="class-content">
	                                           
		                                            <div>
		                                               	<div>
		                                               		<h3>教練簡介:</h3>
		                                               	</div>
		                                               	<div>
		                                               		<h4>
		                                               			${proVO.expr }
		                                               		</h4>
		                                               	</div>
		                                            </div>
	                                            
	                                            
	                                             <div>		                                         
	                                             	<div>
	                                             		<h3>證照資格:</h3>
	                                             	</div>
	                                             	<div><h4>
		                                             	<c:forEach var="lcVO" items="${licList}">
		                                             		${lcVO.lic_name},
		                                             	</c:forEach>
		                                             	</h4>	
	                                             	</div>		                                             
                                               </div> 
                                            </div>

                                        </div>
                                    </div>
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="tab-content-description">
						<jsp:include page="${ request.getContextPath()}/front-end/FullCalendar/calendar_IndClass.jsp" flush="true" />
<%--                         <%@include file ="${request.getContextPath()}/front-end/FullCalendar/calendar_IndClass.jsp" %> --%>
                                        
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                     </div>   
                        <!-- biography end -->
<!--                     </div> -->
        </main>
    </div>
    <!-- class details end -->
					<div class="all-classes-area">
						<div class="container all-class-slider">
							<h2>教練的其他課程</h2>
							<jsp:useBean id="icSvc" scope="page" class="com.individualClass.model.IndividualClassService"/>
							<div class="row classes-items text-center">
								<c:forEach var="iClassVO" items="${icSvc.findByPro(proVO.getPro_ID())}">
									<div class="col-lg-4">
										<div class="single-class-item">
											<div class="top-content">
												<div class="content-img">
													<img
														src="<%=request.getContextPath() %>/front-end/ProAndClass/showPhotos.do?type=iClassPhoto&pic_no=${iClassVO.i_class_no}"
														width="240" height="250" alt="shopping">
												</div>
												<div class="hover-content">
													<div class="btn-wrapper desktop-center">
														<a
															href="<%=request.getContextPath() %>/front-end/ProAndClass/IndividualClassServlet.do?i_class_no=${iClassVO.i_class_no}&action=getOne_For_Display"
															class="btn btn-element btn-normal-size btn-rounded">查看詳情</a>
													</div>
												</div>
											</div>
											<div class="bottom-content">
												<h3>
													<a
														href="IndividualClassServlet.do?i_class_no=${iClassVO.i_class_no}&action=getOne_For_Display">${iClassVO.c_name}</a>
												</h3>
												<p>${classTypeSvc.getOneClassType(classAuthVO.getC_type_no()).t_name}</p>
												<p>${iClassVO.c_detail }</p>
												<h4>Course Price : ${iClassVO.p_coin}</h4>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>






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

</body>
</html>