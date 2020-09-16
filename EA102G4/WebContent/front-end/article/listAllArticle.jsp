<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.article.model.*"%>
<%@ page import="org.json.*"%>
<%@ page import="com.mem.model.*"%>


<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="ArticleService" scope="page"
	class="com.article.model.ArticleService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>listAllArticle</title>
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
<!-- videoplayer -->
<link rel="stylesheet" href="assets/css/rvideoplayer.css">
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

</head>

<style>
@font-face {
	font-family: 'Material Icons';
	font-style: normal;
	font-weight: 400;
	src: url(https://example.com/MaterialIcons-Regular.eot);
	/* For IE6-8 */
	src: local('Material Icons'), local('MaterialIcons-Regular'),
		url(https://example.com/MaterialIcons-Regular.woff2) format('woff2'),
		url(https://example.com/MaterialIcons-Regular.woff) format('woff'),
		url(https://example.com/MaterialIcons-Regular.ttf) format('truetype');
}

.material-icons {
	font-family: 'Material Icons';
	font-weight: normal;
	font-style: normal;
	font-size: 24px; /* Preferred icon size */
	display: inline-block;
	line-height: 1;
	text-transform: none;
	letter-spacing: normal;
	word-wrap: normal;
	white-space: nowrap;
	direction: ltr;
	/* Support for all WebKit browsers. */
	-webkit-font-smoothing: antialiased;
	/* Support for Safari and Chrome. */
	text-rendering: optimizeLegibility;
}

.intro {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 4;
}

.nice-select {
	color: black;
}

.list::-webkit-scrollbar {
	width: 8px;
	background-color: transparent;
}

.list::-webkit-scrollbar-thumb {
	border-radius: 10px;
	background-color: #ececec;
}

.list::-webkit-scrollbar-track {
	border-radius: 10px;
	background-color: transparent;
}

.nice-select .list {
	height: 40px;
	overflow: auto;
	text-align: center;
}

.nice-select {
	text-align: center;
}

.listAllArticle {
	color: black;
	background-color: white;
	border-color: #e8e8e8;
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

<body>
<!-- <body style="background-image:url(assets/img/bg/C01.png);"> -->

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
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay"
		style="background-image: url(assets/img/blog/Gymbeginner.jpg);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Article</h1>
			<ul class="page-list margin-bottom-10">
				<li><a href="<%=request.getContextPath()%>/front-end/index.jsp">Home</a></li>
				<li><a href="/EA102G4/front-end/article/listAllArticle.jsp">Article</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- home blog start -->
	<div class="blog-details-area margin-top-100 ">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="row">
						<div class="col-lg-12">
							<div class="blog-single-item">
								<div class="row">
									<div class="col-lg-4"
										style="padding: 0px; flex: auto; max-width: none;">
										<div class="blog-item-inner">

											<!--                                         <ul> -->
											<!--  <h3> <li><a href='addArticle.jsp'>新增文章</a> </li></h3> -->

											<!--  <button  type="submit" class="site-btn">送出 </button>   -->

											<!-- <h3><a href='addArticle.jsp'> -->
											<!-- <input type=button onclick="新增文章"> -->
											<!-- </a> </h3> -->

											<input type="button"
												class="btn btn-primary btn-lg btn-success   btn-outline-primary"
												value="開始新增文章" onClick="location.href='addArticle.jsp'">



											<!-- </ul> -->
											<div  class="blog-detail; ">
<!-- <body style="background-image:url(assets/img/bg/C01.png);"> -->

												<!--                                                 <div class="blog-meta"> -->
												<!--                                                     <h6><a href="#">Dec 7, 2019</a><span class="float-right text-right"><a href="#">Admin</a></span></h6> -->
												<!--                                                 </div> -->
												<%@ include file="include/newPage1.file"%>
												<c:forEach var="articleVO" items="${list}">

													<br>
													<h5>
														<a
															href="./article.do?article_no=${articleVO.article_no}&action=getOne_For_Display"
															style="color: blue;">${articleVO.title}
															-發佈時間:${SimpleDateFormat("yyyy/MM/dd hh:mm").format(articleVO.a_release)}</a>
													</h5>
													<br>
												</c:forEach>

											</div>
											<!--                                             <div class="blog-img"> -->
											<!--                                                 <img src="assets/img/blog/blog-sm-item.png" alt="">         -->
											<!--                                             </div> -->
										</div>
									</div>
									<!--                                     <div class="col-lg-8"> -->
									<!--                                         <div class="blog-item-inner"> -->
									<!--                                             <div class="blog-img"> -->
									<!--                                                 <img src="assets/img/blog/blog-lg-item.png" alt="">         -->
									<!--                                             </div> -->
									<!--                                             <div class="blog-detail">        -->
									<!--                                                 <div class="blog-meta"> -->
									<!--                                                     <h6 class="d-flex"><a href="#">Oct 7, 2019</a><a href="#">Admin</a><a href="#">3 Comments</a></h6> -->
									<!--                                                 </div> -->
									<!--                                                 <h5><a href="blog-details.html">Best Cardio Exercises</a></h5>    -->
									<!--                                                 <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be.</p>  -->
									<!--                                                 <a href="blog-details.html" class="blog-more">Read More</a> -->
									<!--                                             </div> -->
									<!--                                         </div> -->
									<!--                                     </div> -->
									<!--                                     <div class="col-lg-8"> -->
									<!--                                         <div class="blog-item-inner"> -->
									<!--                                             <div class="blog-img"> -->
									<!--                                                 <img src="assets/img/blog/blog-lg-item.png" alt="">         -->
									<!--                                             </div> -->
									<!--                                             <div class="blog-detail">       -->
									<!--                                                 <div class="blog-meta"> -->
									<!--                                                     <h6 class="d-flex"><a href="#">Oct 09, 2019</a><a href="#">Admin</a><a href="#">3 Comments</a></h6> -->
									<!--                                                 </div> -->
									<!--                                                 <h5><a href="blog-details.html">Best Cardio Exercises</a></h5>    -->
									<!--                                                 <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be.</p>   -->
									<!--                                                 <a href="blog-details.html" class="blog-more">Read More</a> -->
									<!--                                             </div> -->
									<!--                                         </div> -->
									<!-- <!--                                     </div> -->
									-->
									<!--                                     <div class="col-lg-4"> -->
									<!--                                         <div class="blog-item-inner"> -->
									<!--                                             <div class="blog-detail">        -->
									<!--                                                 <div class="blog-meta"> -->
									<!--                                                     <h6><a href="#">Sept 07, 2019</a><span class="float-right text-right"><a href="#">Admin</a></span></h6> -->
									<!--                                                 </div> -->
									<!--                                                 <h5><a href="blog-details.html">Something new</a></h5>    -->
									<!--                                                 <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree.</p> -->
									<!--                                                 <a href="blog-details.html" class="blog-more">Read More</a>  -->
									<!--                                             </div> -->
									<!--                                             <div class="blog-img"> -->
									<!--                                                 <img src="assets/img/blog/blog-sm-item.png" alt="">         -->
									<!--                                             </div> -->
									<!--                                         </div> -->
									<!--                                     </div> -->
									<!--                                     <div class="col-lg-4"> -->
									<!--                                         <div class="blog-item-inner"> -->
									<!--                                             <div class="blog-detail">        -->
									<!--                                                 <div class="blog-meta"> -->
									<!--                                                     <h6><a href="#">Oct 07, 2019</a><span class="float-right text-right"><a href="#">Admin</a></span></h6> -->
									<!--                                                 </div> -->
									<!--                                                 <h5><a href="blog-details.html">Make healthy life</a></h5>    -->
									<!--                                                 <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree.</p>  -->
									<!--                                                 <a href="blog-details.html" class="blog-more">Read More</a>  -->
									<!--                                             </div> -->
									<!--                                             <div class="blog-img"> -->
									<!--                                                 <img src="assets/img/blog/blog-sm-item.png" alt="">         -->
									<!--                                             </div> -->
									<!--                                         </div> -->
									<!--                                     </div> -->
									<!--                                     <div class="col-lg-8"> -->
									<!--                                         <div class="blog-item-inner"> -->
									<!--                                             <div class="blog-img"> -->
									<!--                                                 <img src="assets/img/blog/blog-lg-item.png" alt="">         -->
									<!--                                             </div> -->
									<!--                                             <div class="blog-detail">        -->
									<!--                                                 <div class="blog-meta"> -->
									<!--                                                     <h6 class="d-flex"><a href="#">Oct 7, 2019</a><a href="#">Admin</a><a href="#">3 Comments</a></h6> -->
									<!--                                                 </div> -->
									<!--                                                 <h5><a href="blog-details.html">Best Cardio Exercises</a></h5>    -->
									<!--                                                 <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be.</p>  -->
									<!--                                                 <a href="blog-details.html" class="blog-more">Read More</a>  -->
									<!--                                             </div> -->
									<!--                                         </div> -->
									<!--                                     </div> -->
								</div>
							</div>
						</div>
						<!--                         <div class="col-md-12"> -->
						<!--                             <div class="pagination-area d-flex justify-content-center margin-top-50"> -->
						<!--                                 <ul> -->
						<!--                                     <li> -->
						<!--                                         <span class="next page-bumber"><i class="fa fa-angle-left"></i> Prev</span> -->
						<!--                                     </li> -->
						<!--                                     <li> -->
						<!--                                         <span class="page-bumber">1</span> -->
						<!--                                     </li> -->
						<!--                                     <li> -->
						<!--                                         <span class="page-bumber current">2</span> -->
						<!--                                     </li> -->
						<!--                                     <li> -->
						<!--                                         <span class="page-bumber">3</span> -->
						<!--                                     </li> -->
						<!--                                     <li> -->
						<!--                                         <span class="next page-bumber">Next <i class="fa fa-angle-right"></i></span> -->
						<!--                                     </li> -->
						<!--                                 </ul> -->
						<!--                             </div> -->
						<!--                         </div> -->
					</div>
				</div>
				<div class="col-lg-4">
					<div class="blog-right-content widget-area sidebar">
						<div class="widget widget_search">
							<form class="search-form">
								<div class="form-group">
									<input type="text" name="search" class="form-control"
										placeholder="Search here">
								</div>
								<button class="submit-btn" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</form>
						</div>
						<div class="widget latest-post-widget">
							<div class="eidget-title">
								<h1>官方消息</h1>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<img
										src="assets/img/blog/work01.jpg" alt="">
								</div>
								<div class="content-part">
									<h4>健身的好處</h4>
									<span class="text">1.增加基礎代謝率（BMR)，讓身體不易堆積脂肪。<br>
													   2.提升運動能力，避免運動傷害。
													   3.增進骨質密度，使骨骼強壯、延緩老化。<br>
                                                        </span>
								</div>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<img
										src="assets/img/blog/work02.jpeg" alt="">
								</div>
								<div class="content-part">
									<h4>健身的好處(二)</h4>
									<span class="text">
									                  4.使生活減少因久坐或久站造成的肌肉疲勞。<br>
													   5.降低心血管等慢性疾病的風險。<br>
													   6.避免骨質疏鬆或罹患肌少症。<br>
													   7.身形線條更好看。</span>
								</div>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<img
										src="assets/img/blog/work03.png" alt="">
								</div>
								<div class="content-part">
									<h4>健身的好處(三)</h4>
									<span class="text">8.改變你的心情健身可以讓人體感到愉悅，
									腦內也會分泌腦內啡來幫助我們釋放壓力，
									讓我們更快樂。<br>
									9.幫助睡眠健身能幫助我們睡得更好，美國國家睡眠基金會最新研究表示，
									不管健身的時間是在早上、中午、晚上，67%有健身習慣的人，
									深夜睡眠品質比沒健身習慣的人好!
									</span>
								</div>
							</div>
							<div class="share-img-item">
								<div class="img-part">
									<img
										src="assets/img/blog/work04.png" alt="">
								</div>
								<div class="content-part">
									<h4>運動的優點</h4>
									<span class="text">10.增強記憶力每個人都希望自己能有比較好的記憶力來面對工作上的一些問題或者是考試，
									最新發表在腦行為研究期刊的研究上顯示，
									有氧運動可以有效的增加血液中有關於記憶的荷爾蒙增加!<br>
									11.增加創造力專家研究的證實，
									在57~75歲的人，在每周3次，每次一小時，
									共12周的有氧運動後(慢跑、騎腳踏車)，
									中老年健身者的大腦血液明顯含氧量變多，
									在認知以及創造測驗表現也比較優異!
								</span>
								</div>
							</div>
						</div>
						<div class="widget">
							<div class="thumb only-thumb">
								<img src="assets/img/blog/work08.png" alt="">
							</div>
						</div>
<!-- 						<div class="widget widget_tag_cloud"> -->
<!-- 							<h5 class="widget-title margin-top-50">Popular Tags</h5> -->
<!-- 							<ul class="tagcloud"> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Travel</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Travel</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Travel</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Travel</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
<!-- 								<li><a href="#">Blog</a></li> -->
<!-- 								<li><a href="#">Travel</a></li> -->
<!-- 								<li><a href="#">Music</a></li> -->
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
	<!-- rvideoPlayer -->
	<script src="assets/js/jquery.rvideoPlayer.js"></script>
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