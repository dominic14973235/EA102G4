<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@page import="com.art_comment.model.*"%>
<%@ page import="java.sql.Timestamp"%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.article.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem.model.MemVO"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	String article_no = request.getParameter("article_no");
	ArticleVO articleVO = new ArticleService().getOneArticle(article_no);
	pageContext.setAttribute("articleVO", articleVO);
	System.out.print(article_no);

// 	Set<Art_CommentVO> set = (Set<Art_CommentVO>) request.getAttribute("art_commentVOs");
	ArticleService articleSvc = new ArticleService();
	Set<Art_CommentVO> set = articleSvc.getArt_CommentsByArticle_no(article_no);
	pageContext.setAttribute("set", set);
	
	
	
	
	Art_CommentVO art_commentVO = (Art_CommentVO) request.getAttribute("art_commentVO");

	//手動測試用
// 	MemVO memVO = (MemVO) session.getAttribute("memVOLogin");
// 	MemService memSvc = new MemService();
// 	memVO = memSvc.getOneMem("MEM0000021");
// 	session.setAttribute("memVOLogin", memVO);

	
	MemVO memVOLogin = (MemVO) request.getSession().getAttribute("memVOLogin");
	if (memVOLogin == null) {			//if (memVOLogin == null && empVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	}else{
	
%>

<%-- 取出 對應的DeptVO物件--%>

<jsp:useBean id="art_commentSvc" scope="page"
	class="com.art_comment.model.Art_CommentService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
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


<style type="text/css">
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
.MSG-box2 {
	border-color: transparent;
}

.MSG-box2 {
	margin: 10px;
	border-bottom: 1px solid #CCCCCC;
	padding-bottom: 5px;
	_height: 1%;
	table-layout: fixed;
	word-wrap: break-word;
}

#map {
	height: 500px;
	width: 500px;
	border: 2px solid #333;
	background: #CCC;
	display: inline-block;
	color: black;
}

/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#right-panel select, #right-panel input {
	font-size: 15px;
}

#right-panel select {
	width: 100%;
}

#right-panel i {
	font-size: 12px;
}

#right-panel {
	line-height: 30px;
	padding-left: 10px;
	height: 500px;
	width: 390px;
	overflow: auto;
	display: inline-block;
	border: 2px solid #333;
}

#hi_mem {
	font-family: Microsoft JhengHei;
}

@media screen and (max-width: 1400px) {
	#hi_mem {
		display: none;
	}
}

#logout {
	border-style: none;
	background: none;
	transition: all .3s;
	color: rgba(255, 255, 255, 0.5);
}

#logout:hover {
	text-decoration: none;
	outline: none;
	color: inherit;
}

.footer-bg {
    background: url(assets/img/20.jpg);
    background-position: 50% 15%;
}

</style>

</head>
<body>

	<!-- <div class="breadcrumb-style-1" style="background-image:url(assets/img/bg/blog-details.png);"> -->
<!-- <body style="background-image: url(assets/img/bg/C01.png);"> -->

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
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/m1.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Article Detail</h1>
			<ul class="page-list margin-bottom-4">

				<li><a href="<%=request.getContextPath()%>/front-end/index.jsp">Home</a></li>
				<li><a href="/EA102G4/front-end/article/listAllArticle.jsp">Back to previous page </a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->




	<!-- home blog start -->
	<div class="blog-details-area margin-top-100">
		<main id="main" class="site-main">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
						<article class="blog-details">
							<div class="blog-img">
								<!--                                 <img src="assets/img/bg/blog-top.png" class="attachment-siiimple_full size-siiimple_full wp-post-image" alt="">	 -->
							</div>

							<!--                             <input type="hidden" name="com_status" value="AC1"> -->
							<!-- <input  type="hidden" name="action" value="insert">  -->
							<!-- <button  type="submit" class="site-btn">送出 </button>   -->

							

<!-- 				<div class="widget widget_search"> -->
<!-- 							<form class="search-form"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<input type="text" name="search" class="form-control" -->
<!-- 										placeholder="Search here"> -->
<!-- 								</div> -->
<!-- 								<button class="submit-btn" type="submit"> -->
<!-- 									<i class="fa fa-search"></i> -->
<!-- 								</button> -->
<!-- 							</form> -->
<!-- 						</div> -->
						<div class="widget latest-post-widget">
					
										<!-- 跳轉修改文章區塊 -->
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/article/article.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改" style='display:${(memVOLogin.mem_id == articleVO.mem_id)?"":"none" }'> 
								<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
									<!-- 跳轉修改文章區塊 -->
									
			<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
							<!--                             <div class="blog-details-content"> -->
							<h6>
								發文時間:<a href="#"> ${SimpleDateFormat("yyyy/MM/dd hh:mm").format(articleVO.a_release)}</a>
<!-- 								<a href="#"><span>Admin</span></a> -->
							</h6>
							
							<h5><font color="blue">發文者:&nbsp;${memSvc.getOneMem(articleVO.mem_id).mem_name}</font></h5>
							
							  ${memSvc.getOneMem(art_commentVO.mem_id).mem_name}
							<h2><%=articleVO.getTitle()%></h2>


							<h5><%=articleVO.getArt_content()%></h5>


							<div class="widget widget_tag_cloud d-flex">
								<h4 class="tag-title">Tag</h4>
								<ul class="tagcloud d-sm-flex">
									<li><a href="#">Blog</a></li>
									<li><a href="#">Gym</a></li>
									<li><a href="#">Fitness</a></li>
									<li><a href="#">Health</a></li>
								</ul>
							
							</div>
					</div>
					</article>
					
					<div id="comments" class="comments-area comments-area-wrapper">
						<h4 class="comments-title">留言</h4>
						<ul class="comment-list">
							<li class="comment">
								<div class="single-comment justify-content-between d-flex">
									<div class="user justify-content-between d-flex">
										<div class="thumb">
											<!--                                                 <img  class="avatar"> -->
										</div>
										<div class="desc">
											<div class="d-flex justify-content-between comment_title">
												<div class="d-flex align-items-center"></div>
											</div>
	
						
							<h6><%@ include file="page1.file"%></h6>
												<!-- 抓取所有相關留言區塊 -->
												<c:forEach var="art_commentVO" items="${set}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
												
													<br>
			  <div style="border: 5px #AAAAAA double;"><h5>
			  
			
														<font color="#009FCC">${memSvc.getOneMem(art_commentVO.mem_id).mem_name}:<br></font>
														   ${art_commentVO.mes_content} <br> <br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
														留言時間:${SimpleDateFormat("yyyy/MM/dd hh:mm").format(art_commentVO.com_release)}
													
<!-- 				<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/front-end/art_comment/art_comment.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除">  -->
<%-- 						<input type="hidden"name="com_no" value="${art_commentVO.com_no}"> --%>
<!-- 					    <input type="hidden" name="action" value="delete"> -->
							
<!-- 					</FORM> -->
													</h5></div>

												</c:forEach>
												<!-- 抓取所有相關留言區塊 -->

														<h6><%@ include file="page2.file"%></h6>
												<div class="reply-btn">
<%-- 												<%@ include file="include/js_src"%> --%>
													<!--                                                     <a class="comment-reply-link" href="#">Reply</a> -->
												</div>
											</div>
										</div>

									</div>
									<ul class="children">
										<li class="comment">
											<div class="single-comment justify-content-between d-flex">
												<div class="user justify-content-between d-flex">
													<div class="thumb">
														<!--                                                         <img alt="" src="assets/img/blog/avatar2.jpg" class="avatar">  -->
													</div>
													<div class="desc">
														<div class="d-flex justify-content-between comment_title">
															<div class="d-flex align-items-center">
																<!--                                                                 <h5>Naeem</h5> -->
															</div>
														</div>
														<div class="comment-content">
															<!--                                                             <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p> -->
														</div>
														<div class="reply-btn">
															<!--                                                             <a class="comment-reply-link" href="#">Reply</a> -->
														</div>
													</div>
												</div>
											</div>
										</li>
									</ul>
							</li>
							<li class="comment">
								<div class="single-comment justify-content-between d-flex">
									<div class="user justify-content-between d-flex">
										<div class="thumb">
											<!--                                                 <img alt="" src="assets/img/blog/avatar3.jpg" class="avatar">                 -->
										</div>
										<div class="desc">
											<div class="d-flex justify-content-between comment_title">
												<div class="d-flex align-items-center">
													<!--                                                         <h5>Asade</h5> -->
												</div>
											</div>
											<div class="comment-content">
												<!--                                                     <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p> -->
											</div>
											<div class="reply-btn">
												<!--                                                     <a class="comment-reply-link" href="#">Reply</a>  -->
											</div>
										</div>
									</div>
								</div>
							</li>
						</ul>
						
						<div METHOD="post" class="comment-respond">
							<h3 class="comment-reply-title">新增留言</h3>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/art_comment/art_comment.do"
								name="form1">

								<div class="form-group margin-bottom-30">

									<input type="hidden" name="article_no"
										value="${articleVO.article_no}"> <input type="hidden"
										name="mem_id" value="${memVOLogin.mem_id}"> <input
										type="TEXT" name="mes_content" size="100" required
										value="${art_commentVO.mes_content}"> <input
										type="hidden" name="com_status" value="AC1"> <input
										type="hidden" name="action" value="insert">
									<button type="submit" class="site-btn">送出</button>



								</div>
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group">
											<!--                                                 <input type="text" id="author" name="author" value="" class="form-control" placeholder="Write your name"> -->
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<!--                                                 <input type="email" class="form-control" name="email" id="email" value="" placeholder="Enter your email"> -->
										</div>
									</div>
									<div class="col-sm-4">
										<p class="form-submit"></p>
									</div>
								</div>
							</FORM>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="blog-right-content widget-area sidebar">
						
						
							<div class="eidget-title">
								<h1>官方消息</h1>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<a
										href="#/EA102G4/front-end/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Display"><img
										src="/EA102G4/front-end/article/assets/img/blog/work01.jpg" alt=""></a>
								</div>
								<div class="content-part">
									<h4>健身飲食</h4>
									<span class="text">如果你是要好體態的話，
									你可以透過攝取蛋白質以維持一定的肌肉量。
									你或許聽說過每一磅的體重就需約一克的蛋白質來維持。
									也就是說，如果你的體重是70公斤，你大約需要 1.0*70=70公克的蛋白質。
									從事運動的人需要比平常人更多的蛋白質，以應付運動的消耗和肌肉組織的生長和修護。</span>
								</div>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<a
										href="#/EA102G4/front-end/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Display"><img
										src="/EA102G4/front-end/article/assets/img/blog/work02.jpeg" alt=""></a>
								</div>
								<div class="content-part">
									<h4>健身飲食(二)</h4>
									<span class="text">在蛋白質的攝取方面可以選擇瘦牛肉、魚(鮭魚)、雞胸肉，也可以喝高蛋白、脫脂牛奶或是吃些低脂肪的奶酪。</span>
								</div>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<a
										href="#/EA102G4/front-end/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Display"><img
										src="/EA102G4/front-end/article/assets/img/blog/work03.png" alt=""></a>
								</div>
								<div class="content-part">
									<h4>健身飲食(三)</h4>
									<span class="text">脂肪是不可少的，健康的脂肪食物更是有許多功用，像是大腦和心臟活動、激素調節和能量來源，都是靠脂肪來供應。平日就可以吃魚油、杏仁、鱷梨或是花生醬。</span>
								</div>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<a
										href="#/EA102G4/front-end/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Display"><img
										src="/EA102G4/front-end/article/assets/img/blog/work04.png" alt=""></a>
								</div>
								<div class="content-part">
									<h4>健身飲食(四)</h4>
									<span class="text">水果、蔬菜所提供的營養就不在話下啦！其中的纖維、維生素、礦物質和天然的糖分對身體有數不完的好處。蔬菜就盡量吃深色的綠葉，如：菠菜、碗豆、綠豆等；水果可以選擇香蕉、蘋果、藍莓和柚子。</span>
								</div>
						<div class="widget">
							<div class="thumb only-thumb">
								<img src="assets/img/blog/G1.jpg" alt="">
							</div>
						</div>
<!-- 						<div class="widget widget_tag_cloud"> -->
<!-- 							<h5 class="widget-title margin-top-50">Popular Tags</h5> -->
<!-- 							<ul class="tagcloud"> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Travel</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Fitness</a></li> -->
<!-- 								<li><a href="#">Health</a></li> -->
<!-- 								<li><a href="#">Life style</a></li> -->
<!-- 								<li><a href="#">Strength</a></li> -->
<!-- 								<li><a href="#">Gym</a></li> -->
<!-- 								<li><a href="#">Training</a></li> -->
<!-- 								<li><a href="#">Messages</a></li> -->
<!-- 								<li><a href="#">Yoga</a></li> -->
<!-- 								<li><a href="#">Spa</a></li> -->
<!-- 								<li><a href="#">Beauty</a></li> -->
<!-- 								<li><a href="#">Surgery</a></li> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Fitness</a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 						<div class="widget widget_categories"> -->
<!-- 							<h5 class="widget-title">Categories</h5> -->
<!-- 							<ul class="cat-menu"> -->
<!-- 								<li><a href="#">Boxing</a></li> -->
<!-- 								<li><a href="#">Street</a></li> -->
<!-- 								<li><a href="#">Fitness</a></li> -->
<!-- 								<li><a href="#">Uncategorized</a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
	</div>
	</main>
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
	<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%}%>









</body>
</html>