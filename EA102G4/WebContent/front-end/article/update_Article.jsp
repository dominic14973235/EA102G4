<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.article.model.ArticleService"%>
<%@page import="com.article.model.ArticleVO"%>
<%@page import="com.art_comment.model.*"%>

<%
//   ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
 	
	String article_no = request.getParameter("article_no");
	ArticleVO articleVO = new ArticleService().getOneArticle(article_no);
	pageContext.setAttribute("articleVO", articleVO);
	
	
	
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");


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
</style>

</head>
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
<!--     <div class="body-overlay" id="body-overlay"></div> -->
<!--     <div class="search-popup" id="search-popup"> -->
<!--         <form action="index.html" class="search-form"> -->
<!--             <div class="form-group"> -->
<!--                 <input type="text" class="form-control" placeholder="Search....."> -->
<!--             </div> -->
<!--             <button type="submit" class="submit-btn"><i class="fa fa-search"></i></button> -->
<!--         </form> -->
<!--     </div> -->
    <!-- //. search Popup -->

   <%@ include file="../artInclude/navbar.file"%>
   
    <!-- breadcrumb area -->
     <div class="breadcrumb-style-1" style="background-image:url(assets/img/blog/Gymbeginner.jpg);">
        <div class="breadcrumb-inner">
            <h1 class="page-title">Article revision</h1>
            <ul class="page-list margin-bottom-4">
                <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">Home</a></li>
                <li><a href="/EA102G4/front-end/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Display">Back to previous page</a></li>  
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
                            
<!--                             <div class="widget widget_search"> -->
<!--                                 <form class="search-form"> -->
<!--                                     <div class="form-group"> -->
<!--                                         <input type="text" name="search" class="form-control" placeholder="Search here"> -->
<!--                                     </div> -->
<!--                                     <button class="submit-btn" type="submit"><i class="fa fa-search"></i></button> -->
<!--                                 </form> -->
<!--                             </div> -->
                            <div class="widget latest-post-widget">
                           
                           <FORM METHOD="post" ACTION="article.do" name="form1">


	
	
	
		
<%-- 		<h5>文章標題:<span> <input type="TEXT" name="title" size="30" required value="${articleVO.getTitle}"></h5>	<br> --%>
	<h5>文章標題:&nbsp;<input type="TEXT" name="title" size="30"  value="<%=articleVO.getTitle()%>"/></h5>

		
	<h5>文章內容:<textarea style="width:329.5px" name="art_content" cols="30" rows="3" ><%= articleVO.getArt_content()%></textarea></h5>
	

	<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
	
		<!-- <td>文章編號:<font color=red><b>*</b></font></td> -->
		<input type="hidden" name="article_no" size="45" value="<%=articleVO.getArticle_no()%>" />


<input type="hidden" name="action" value="update">
<input type="hidden" name="articleVO" value="<%=articleVO.getArticle_no()%>">
<button  style="float:right" type="submit" class="site-btn">確定送出 </button> </FORM>
                           
                           
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
<!--                             <h4 class="comments-title">Comments</h4> -->
                            <ul class="comment-list">
                                <li class="comment">
                                    <div class="single-comment justify-content-between d-flex">
                                        <div class="user justify-content-between d-flex">
                                            <div class="thumb">
<!--                                                 <img alt="" src="assets/img/blog/avatar.png" class="avatar"> -->
                                            </div>
                                            <div class="desc">
                                                <div class="d-flex justify-content-between comment_title">
                                                    <div class="d-flex align-items-center">
<!--                                                         <h5>Sharifur</h5>                        -->
                                                    </div>
                                                </div>
                                                <div class="comment-content">
<!--                                                     <p>Sometimes ‘short and sweet’ workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p> -->
                                                </div>
                                                <div class="reply-btn">
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
                            <div id="respond" class="comment-respond">
<!--                                 <h3 class="comment-reply-title">Leave A Reply</h3>			 -->
                                <form id="comment_form" class="commentform">
                                    <div class="form-group margin-bottom-30">
                                        <textarea name="comment" id="comment" class="form-control w-100" cols="30" rows="9"></textarea>
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
                                            <p class="form-submit">
<!--                                                 <input name="submit" type="submit" id="submit" value="Submit Comment">  -->
                                            </p>
                                        </div>
                                    </div>		
                                </form>
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
                                        <img src="assets/img/blog/food04.jpeg" alt="">
                                    </div>
                                    <div class="content-part">
                                        <h4>健身飲食</h4>
                                        <span class="text">讓自己一天減少200~400卡路里，幫助身體燃燒脂肪。換句話說，利用飢餓感讓身體來控制脂肪組織。</span>
                                    </div>
                                </div>
                                <div class="share-img-item">
                                    <div class="img-part">
										<img src="assets/img/blog/food01.jpg" alt="">                                    </div>
                                    <div class="content-part">
                                        <h4>健身飲食(二)</h4>
                                        <span class="text">讓自己一天減少200~400卡路里，幫助身體燃燒脂肪。換句話說，利用飢餓感讓身體來控制脂肪組織。</span>
                                    </div>
                                </div>
                                <div class="share-img-item">
                                    <div class="img-part">
										<img src="assets/img/blog/food02.jpg" alt="">                                    </div>
                                    <div class="content-part">
                                        <h4>健身飲食(三)</h4>
                                        <span class="text">當你早晨起來一定要確保你有吃到蛋白質，例如：蛋所做成的料理(雞蛋擁有好的胺基酸而且也好消化)。研究顯示，在早上吃含蛋白質的食物，能幫助我們穩固血糖並且促進一天的新陳代謝！</span>
                                    </div>
                                </div>
                                <div class="share-img-item">
                                    <div class="img-part">
										<img src="assets/img/blog/food03.png" alt="">                                    </div>
                                    <div class="content-part">
                                        <h4>健身飲食(四)</h4>
                                        <span class="text">早餐的組合中碳水化合物和蛋白質都要有，而且最好吃複合性的碳水化合物，如全麥麵包、燕麥片等，血糖比較穩定。</span>
                                    </div>
                                </div>
                            </div>
                            <div class="widget">
                                <div class="thumb only-thumb">
                                    <img src="assets/img/blog/work08.png" alt="">
                                </div>
                            </div>
<!--                             <div class="widget widget_tag_cloud"> -->
<!--                                 <h5 class="widget-title margin-top-50">Popular Tags</h5>			 -->
<!--                                 <ul class="tagcloud"> -->
<!--                                     <li><a href="#">Blog</a></li> -->
<!--                                     <li><a href="#">Travel</a></li> -->
<!--                                     <li><a href="#">Music</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li> -->
<!--                                     <li><a href="#">Health</a></li> -->
<!--                                     <li><a href="#">Life style</a></li>	 -->
<!--                                     <li><a href="#">Strength</a></li> -->
<!--                                     <li><a href="#">Gym</a></li> -->
<!--                                     <li><a href="#">Training</a></li>	 -->
<!--                                     <li><a href="#">Messages</a></li>	 -->
<!--                                     <li><a href="#">Yoga</a></li> -->
<!--                                     <li><a href="#">Spa</a></li> -->
<!--                                     <li><a href="#">Beauty</a></li>	 -->
<!--                                     <li><a href="#">Surgery</a></li>	 -->
<!--                                     <li><a href="#">Blog</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li>		 -->
<!--                                 </ul> -->
<!--                             </div> -->
<!--                             <div class="widget widget_categories"> -->
<!--                                 <h5 class="widget-title">Categories</h5>			 -->
<!--                                 <ul class="cat-menu"> -->
<!--                                     <li><a href="#">Boxing</a></li> -->
<!--                                     <li><a href="#">Street</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li> -->
<!--                                     <li><a href="#">Uncategorized</a></li> -->
<!--                                 </ul> -->
<!--                             </div> -->
                        </div>
                    </div>
                </div>
            </div>
<!--         </main> -->
<!--     </div> -->

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


<!-- CKEditor用 -->
	<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace('art_content');
	</script>
	
</body>
</html>





<%-- <%= articleVO==null %>--${articleVO==null}<br>--${articleVO.article_no}--  --%>
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> -->
<!-- <title>文章資料修改 - update_Article.jsp</title> -->

<!-- <style> -->

<!-- </style> -->

<!-- </head> -->
<!-- <body bgcolor='white'> -->

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>文章資料修改 - update_Article.jsp</h3> -->
<!-- 		<h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<!-- <h3>資料修改:</h3> -->


<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <FORM METHOD="post" ACTION="article.do" name="form1"> -->
<!-- <table> -->

	
	
<!-- 	<tr> -->
<!-- 		<td>文章標題:</td> -->
<%-- 		<td><input type="TEXT" name="title" size="45" value="<%=articleVO.getTitle()%>"/></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>文章內容:</td> -->
<%-- 		<td><!--<input type="TEXT" name="art_content" size="45" value="<%=articleVO.getArt_content()%>"--> --%>
<%-- 		 <textarea style="width:329.5px" name="art_content" cols="30" rows="3" align="center"><%= articleVO.getArt_content()%></textarea></td> --%>
<!-- 	</tr> -->

<%-- 	<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" /> --%>
<!-- 	<tr> -->
<!-- 		<!-- <td>文章編號:<font color=red><b>*</b></font></td> --> -->
<%-- 		<td><input type="hidden" name="article_no" size="45" value="<%=articleVO.getArticle_no()%>" /> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
	
	
<!-- </table> -->


<!-- 	</script> -->


<!-- <br> -->
<!-- <input type="hidden" name="action" value="update"> -->
<%-- <input type="hidden" name="articleVO" value="<%=articleVO.getArticle_no()%>"> --%>
<!-- <input type="submit" value="送出修改"></FORM> -->


