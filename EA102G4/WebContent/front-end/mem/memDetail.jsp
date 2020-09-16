<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.memphoto.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group.model.*"%>
<%@ page import="com.grouplist.model.*"%>
<%@ page import="com.product_comment.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_photo.model.*"%>

<%	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	
	String mem_id =  (String)request.getParameter("mem_id");
	MemService memSvc = new MemService();
	MemVO memVO = memSvc.getOneMem(mem_id);
	
	if (memVO == null) {
		memVO = memVOLogin;
	}
	
	if (memVOLogin == null) {			//if (memVOLogin == null && empVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	}else{
		MemPhotoService memPhotoSvc = new MemPhotoService();
		List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto(memVO.getMem_id());
		pageContext.setAttribute("list",list);
	
%>

<%	
	double totalMember = 0;
	double totalRating = 0;
	int five = 0, four = 0, three = 0,two = 0, one = 0;
	GroupService groupSvc = new GroupService();
	GroupListService glSvc = new GroupListService();
	List<GroupListVO> reviewList = new ArrayList<GroupListVO>();
	
	List<GroupVO> groupList = groupSvc.getGroupByMember(memVO.getMem_id());

	
	for(int i = 0 ; i<groupList.size(); i++){
		totalMember += ((double)(groupList.get(i)).getGro_mem());
		totalRating += ((double)(groupList.get(i)).getGro_rating());
	}
	double ratingAvg = Math.floor(totalRating / totalMember *10.0) / 10.0;
	
	List<GroupListVO> groupMemberList = new ArrayList<GroupListVO>();
	for(int i = 0; i<groupList.size(); i++){
		List<GroupListVO> glList = glSvc.findGroupMember(groupList.get(i).getGro_no());//glList參團人總groupListVO
		
		
		for(int j = 0;j<glList.size();j++){
				if(glList.get(j).getRating_status().equals("GR1")){
				reviewList.add(glList.get(j));
		
				if(glList.get(j).getRating_num() == 5){
					five++;
				}else if (glList.get(j).getRating_num() == 4){
					four++;
				}else if(glList.get(j).getRating_num() == 3){
					three++;
				}else if(glList.get(j).getRating_num() == 2){
					two++;
				}else if(glList.get(j).getRating_num() == 1){
					one++;
				}
			}
		}
	}
	
	double fiveStar = 0,fourStar = 0, threeStar = 0, twoStar = 0, oneStar = 0;
	fiveStar = five/totalMember *100;
	fourStar = four/totalMember *100;
	threeStar = three/totalMember *100;
	twoStar = two/totalMember *100;
	oneStar = one/totalMember *100;
	
	pageContext.setAttribute("reviewList", reviewList);
	
%>

<% //商城
//商城-start 
//商城評價table memIdSeller是發送評價的人 memIdBuyer是接收評價的人 並不是買家賣家，這邊弄錯了在此備註一下 
//商城評論 
String memId = memVO.getMem_id();
ProductCommentService productCommentService = new ProductCommentService();
List<ProductCommentVO> productCommentlist = productCommentService.getByMemId(memVO.getMem_id());
Iterator<ProductCommentVO> it = productCommentlist.iterator();
Double pRating = 0.0;
Double pRatingCount = 0.0;
Double pRatingCount1Star = 0.0;
Double pRatingCount2Star = 0.0;
Double pRatingCount3Star = 0.0;
Double pRatingCount4Star = 0.0;
Double pRatingCount5Star = 0.0;

while(it.hasNext()){ 
	ProductCommentVO aProductCommentVO = (ProductCommentVO)it.next();
	if(aProductCommentVO.getMemIdBuyer().equals(memId) == false){
		it.remove();
	}
}
for(ProductCommentVO aProductCommentVO : productCommentlist){
	int pRatingEach = Integer.parseInt(aProductCommentVO.getpRatingEach());
	pRating= pRating + pRatingEach;
	
	if(pRatingEach == 1){
		pRatingCount1Star++;
	}else if(pRatingEach == 2){
		pRatingCount2Star++;
	}else if(pRatingEach == 3){
		pRatingCount3Star++;
	}else if(pRatingEach == 4){
		pRatingCount4Star++;
	}else if(pRatingEach == 5){
		pRatingCount5Star++;
	}
	
	pRatingCount++;
}
if(pRatingCount != 0){
	Double pRating1StarAvg = (pRatingCount1Star / pRatingCount) * 100 ;
	Double pRating2StarAvg = (pRatingCount2Star / pRatingCount) * 100 ;
	Double pRating3StarAvg = (pRatingCount3Star / pRatingCount) * 100 ;
	Double pRating4StarAvg = (pRatingCount4Star / pRatingCount) * 100 ;
	Double pRating5StarAvg = (pRatingCount5Star / pRatingCount) * 100 ;
	
//		System.out.println("1: " + pRating1StarAvg);
//		System.out.println("2: " + pRating2StarAvg);
//		System.out.println("3: " + pRating3StarAvg);
//		System.out.println("4: " + pRating4StarAvg);
//		System.out.println("5: " + pRating5StarAvg);
	
	pageContext.setAttribute("pRating1StarAvg", pRating1StarAvg);
	pageContext.setAttribute("pRating2StarAvg", pRating2StarAvg);
	pageContext.setAttribute("pRating3StarAvg", pRating3StarAvg);
	pageContext.setAttribute("pRating4StarAvg", pRating4StarAvg);
	pageContext.setAttribute("pRating5StarAvg", pRating5StarAvg);
}


pageContext.setAttribute("memId", memId);
pageContext.setAttribute("memPhotoSvc", memPhotoSvc);
pageContext.setAttribute("memSvc", memSvc);
pageContext.setAttribute("productCommentlist", productCommentlist);
pageContext.setAttribute("pRating", pRating);
pageContext.setAttribute("pRatingCount", pRatingCount);



//商城end
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
	
<!-- 	chatCss -->
<link rel="stylesheet" href="../chatBox/chatCss.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css">

<style>
#hi_mem {
	font-family:Microsoft JhengHei;
}
.resume-div {
	height:270px;
}

.line {
	word-wrap:break-word;
	overflow:auto;
	border:1px solid lightgray;
	width:100%;
	height:80%
}

.memdetail-title {
	color:#161616;
	font-size:24px;
	letter-spacing:0.6px;
	font-weight:600;
	margin-bottom:30px;
	margin-top:10px;
	margin-bottom:10px;
}

.full-photo {
	background-image:url(<%=request.getContextPath()%>/front-end/mem/headphoto.do?mem_id=<%=memVO.getMem_id()%>);
	background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}
#plus-button{
	background-color:lightgray;
	opacity:.5;
	-webkit-border-radius:50%;
	-moz-border-radius:50%;
	border-radius:50%;
	position:absolute;
	left:78%;
	top:85%;
}
#plus-button:hover{
	opacity:1;
}
#plus-button:active{
	opacity:.5;
}
.button-div-item{
	display:inline-block;
	margin-top:30px;
}
.update-button{
	margin-top:30px;
}

#comments .comment-list img {
    height: 70px;
    width: 70px;
}


#update-button,#class-button,#sel-button,#play-button{
	background-color: #88c13e;
    border-color: #88c13e;
}
.feature-button{
	margin-left:10px;
}

.grid-layout {
    grid-template-columns: repeat(auto-fill, minmax(80px, 1fr));
    grid-auto-rows: minmax(180px, 1fr);
}

.biography-content {
    margin-top: 50px;
}

.tab-content{
	height:910px;
    overflow: auto;
   
}
.empty-div{
	width:100%;
	height:850px;
	
}
.empty-div .empty-span {
    position: relative;
    top: 114px;
    font-size:100px;
	color:#ccc;
}


@media screen and (max-width:1400px) {
	#hi_mem {
		display:none;
	}
	div.resume-div {
		height:250px;
	}
}

@media screen and (max-width: 1199px) {
	div.resume-div{
		height:200px;
	}
	#plus-button{
		width:2em;
		height:2em;
	}
}

@media screen and (max-width: 991px) {
	div.resume-div{
		height:180px;
	}
	#plus-button{
		width:2em;
		height:2em;
	}
}

@media screen and (max-width: 767px) {
	div.resume-div {
		height:150px;
	}
	div.photo-div{
		height:500px;
	}
	#plus-button{
		width:4em;
		height:4em;
	}
	#update-button{
		width:80%;
		margin-top:30px;
	}
}

.comments-area.comments-area-wrapper{
	height: 500px;
	overflow-y: auto;
}

.comments-area.comments-area-wrapper::-webkit-scrollbar {
	width: 8px;
	background-color: transparent;
}

.comments-area.comments-area-wrapper::-webkit-scrollbar-thumb {
	border-radius: 10px;
	background-color: #ececec;
}

.comments-area.comments-area-wrapper::-webkit-scrollbar-track {
	border-radius: 10px;
	background-color: transparent;
}

#Box1{
    width:70px;
    height:70px;
    padding:10px;
    margin: 0 auto;
 	display:none;
}
svg.bi.bi-trash {
    color: black;
}

.jq-toast-single { 
    font-size: 20px; 
    font-family: microsoft JhengHei !important; 
    line-height: 25px; 
    width:400px;
} 

h2.jq-toast-heading {
	font-size: 30px; 
}


</style>


</head>

<body onload="connect(); connectChat()" onunload="disconnect(); disconnectChat();" ondrop="BodyDrop(event)" ondragover="AllowDrop(event)">

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

<%@ include file="../memInclude/navbar.file"%>
	
	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/service.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Personal Information</h1>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!--  trainer start -->
	<section class="trainer-area bg-none margin-top-100">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="row border-custom">
						<div class="col-md-5 full-photo photo-div">
						<%System.out.println("====================================="+memVO.getMem_id());%>
						<%System.out.println("====================================="+memVOLogin.getMem_id());%>
						
						<%if(memVO.getMem_id().equals(memVOLogin.getMem_id())){ %>
							<svg id ="plus-button" width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  								<path fill-rule="evenodd" d="M8 3.5a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5H4a.5.5 0 0 1 0-1h3.5V4a.5.5 0 0 1 .5-.5z" />
								<path fill-rule="evenodd" d="M7.5 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1H8.5V12a.5.5 0 0 1-1 0V8z" />
								<path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
							</svg>
						<%} %>
							<!--                            照片在css,當div的背景 -->
						</div>
						<div class="col-md-7 d-flex align-items-center">
							<div class="content ">
								<h3><%=memVO.getMem_name()%></h3>
								<p class="pb-3">
									性別 :
									<%=memVO.getSex()%></p>
								<p class="pb-3">
									生日 :
									<%=memVO.getMem_bir()%></p>
								<p class="pb-3">
									Email :
									<%=memVO.getMem_email()%></p>
								<p class="pb-3">
									Phone :
									<%=memVO.getMem_phone()%></p>
								<p class="pb-3">
									註冊日期 :
									<fmt:formatDate value="<%=memVO.getM_reg_date()%>"
										pattern="yyyy-MM-dd" />
								</p>
								<ul class="social">
								<%if(!memVO.getMem_id().equals(memVOLogin.getMem_id())){ %>
									<li class="icon-list">
										<a class="icon-text" id="contact" value="<%=memVO.getMem_id() %>"> 
											<!-- 連絡我小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
  												<path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
											Contact
										</a>
									</li>
									<%} %>
									<%if(memVO.getMem_id().equals(memVOLogin.getMem_id())){ %>
									<li class="icon-list">
										<a href="#" class="icon-text"> 
											<!-- 課程小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-book" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd" d="M3.214 1.072C4.813.752 6.916.71 8.354 2.146A.5.5 0 0 1 8.5 2.5v11a.5.5 0 0 1-.854.354c-.843-.844-2.115-1.059-3.47-.92-1.344.14-2.66.617-3.452 1.013A.5.5 0 0 1 0 13.5v-11a.5.5 0 0 1 .276-.447L.5 2.5l-.224-.447.002-.001.004-.002.013-.006a5.017 5.017 0 0 1 .22-.103 12.958 12.958 0 0 1 2.7-.869zM1 2.82v9.908c.846-.343 1.944-.672 3.074-.788 1.143-.118 2.387-.023 3.426.56V2.718c-1.063-.929-2.631-.956-4.09-.664A11.958 11.958 0 0 0 1 2.82z"/>
  												<path fill-rule="evenodd" d="M12.786 1.072C11.188.752 9.084.71 7.646 2.146A.5.5 0 0 0 7.5 2.5v11a.5.5 0 0 0 .854.354c.843-.844 2.115-1.059 3.47-.92 1.344.14 2.66.617 3.452 1.013A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.276-.447L15.5 2.5l.224-.447-.002-.001-.004-.002-.013-.006-.047-.023a12.582 12.582 0 0 0-.799-.34 12.96 12.96 0 0 0-2.073-.609zM15 2.82v9.908c-.846-.343-1.944-.672-3.074-.788-1.143-.118-2.387-.023-3.426.56V2.718c1.063-.929 2.631-.956 4.09-.664A11.956 11.956 0 0 1 15 2.82z"/>
											</svg>
											Class
										</a>
									</li>
									<%} %>
									<li class="icon-list">
										<a href="#" class="icon-text"> 
											<!-- 商城小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-shop" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd" d="M0 15.5a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zM3.12 1.175A.5.5 0 0 1 3.5 1h9a.5.5 0 0 1 .38.175l2.759 3.219A1.5 1.5 0 0 1 16 5.37v.13h-1v-.13a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.13H0v-.13a1.5 1.5 0 0 1 .361-.976l2.76-3.22z"/>
  												<path d="M2.375 6.875c.76 0 1.375-.616 1.375-1.375h1a1.375 1.375 0 0 0 2.75 0h1a1.375 1.375 0 0 0 2.75 0h1a1.375 1.375 0 1 0 2.75 0h1a2.375 2.375 0 0 1-4.25 1.458 2.371 2.371 0 0 1-1.875.917A2.37 2.37 0 0 1 8 6.958a2.37 2.37 0 0 1-1.875.917 2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.5h1c0 .76.616 1.375 1.375 1.375z"/>
  												<path d="M4.75 5.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm3.75 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm3.75 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
  												<path fill-rule="evenodd" d="M2 7.846V7H1v.437c.291.207.632.35 1 .409zm-1 .737c.311.14.647.232 1 .271V15H1V8.583zm13 .271a3.354 3.354 0 0 0 1-.27V15h-1V8.854zm1-1.417c-.291.207-.632.35-1 .409V7h1v.437zM3 9.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 .5.5V15H7v-5H4v5H3V9.5zm6 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4zm1 .5v3h2v-3h-2z"/>
											</svg>
											Mall
										</a>
									</li>
									
									<li class="icon-list">
									<%if(memVO.getMem_id().equals(memVOLogin.getMem_id())){ %>
										<a href="<%=request.getContextPath()%>/front-end/gpt/group.do?action=viewMyGroup&mem_id=${memVOLogin.mem_id }" class="icon-text"> 
									<%}else{ %>
										<a href="#" class="icon-text"> 
									<%} %>
											<!-- 揪團小圖 -->
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-door-open" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
 												<path fill-rule="evenodd" d="M1 15.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13a.5.5 0 0 1-.5-.5zM11.5 2H11V1h.5A1.5 1.5 0 0 1 13 2.5V15h-1V2.5a.5.5 0 0 0-.5-.5z"/>
  												<path fill-rule="evenodd" d="M10.828.122A.5.5 0 0 1 11 .5V15h-1V1.077l-6 .857V15H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117z"/>
  												<path d="M8 9c0 .552.224 1 .5 1s.5-.448.5-1-.224-1-.5-1-.5.448-.5 1z"/>
											</svg>
											Activity
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 resume-div">
					<h3 class="memdetail-title">自我介紹:</h3>
					<div class="color-black line"><%=memVO.getMem_resume()%></div>
				</div>
				<%if(memVO.getMem_id().equals(memVOLogin.getMem_id())){ %>
				<button type="button" class="btn btn-dark col-md-6 update-button" id="update-button">修改個人資料</button>
				<%} %>
					
			</div>
			
		</div>
		
	</section>
	<!--  memDetail end -->
<!-- 	垃圾桶start	 -->
	<div id="Box1" ondrop="Drop(event)" ondragover="AllowDrop(event)" class="text-center">
		<svg width="50px" height="50px" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  		<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  		<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
		</svg>
</div>
<!-- 	垃圾桶end	 -->
	
<!-- 		分頁 --START				-->
	<div class="product-specification padding-bottom-95">
        <div class="container">
            
                <div class="biography-area">
                    <div class="biography-content">
                        <ul class="nav nav-pills">
                        	<li>
                                <a data-toggle="pill" href="#allmemphoto" class="active"><%=memVO.getMem_name() %>'s PhotoBox</a>
                            </li>
                            <li>
                                <a data-toggle="pill" href="#trainerreview">TrainerReview</a>
                            </li>
                            <li>
                                <a data-toggle="pill" href="#sellreview">SellReview</a>
                            </li>
                            <li>
                                <a data-toggle="pill" href="#activityreview">ActivityReview</a>
                            </li>
                        </ul>
    
                        <div class="tab-content">
                        	<div id="allmemphoto" class="tab-pane fade in active show">
                        		<div class="tab-content-description">
									<div class="col-lg-12 text-center">
										<div class="container">
											
											<c:choose>
												<c:when test="${list.size() != 0 }">
												<div class="grid-layout">
													<c:forEach var ="memphotoVO" items="${list}">	
														<div class="grid-item span-4 grid-item-3 dragphoto"  id =${memphotoVO.photo_no} ondragstart="Drag(event)" draggable="true">
															<img src="<%=request.getContextPath() %>/front-end/memphoto/allmemphoto.do?photo_no=${memphotoVO.photo_no}">
														</div>
													</c:forEach>
												</div>
												</c:when>
												<c:otherwise>
												<div class="empty-div">
													<span class="empty-span">尚無圖片</span>
												</div>
												</c:otherwise>
											</c:choose>
											</div>
										
									</div>
								</div>
                        	</div>
                        	<div id="trainerreview" class="tab-pane fade">
                                <div class="tab-content-description">
                                    <jsp:include page="${ request.getContextPath()}/front-end/FullCalendar/calendar_mem.jsp" flush="true" />                                    
                                </div>
                            </div>
							<div id="sellreview" class="tab-pane fade">
								<div class="tab-content-description">
									<h2 class="margin-top-25">
										<span class="rating">
											<i id="wedy-star1" class="fa fa-star-o"></i>
											<i id="wedy-star2" class="fa fa-star-o"></i>
											<i id="wedy-star3" class="fa fa-star-o"></i>
											<i id="wedy-star4" class="fa fa-star-o"></i>
											<i id="wedy-star5" class="fa fa-star-o"></i>
											<input type="hidden" class="pRating" value="${pRating / pRatingCount}">
											
											<input type="hidden" class="pRating1StarAvg" value="${pRating1StarAvg}">
											<input type="hidden" class="pRating2StarAvg" value="${pRating2StarAvg}">
											<input type="hidden" class="pRating3StarAvg" value="${pRating3StarAvg}">
											<input type="hidden" class="pRating4StarAvg" value="${pRating4StarAvg}">
											<input type="hidden" class="pRating5StarAvg" value="${pRating5StarAvg}">
										</span>
									</h2>
									<div class="element-progress-bar pagination-progressbar">
										<div class="d-flex">
											<p>5 Star</p>
											<div class="single-progressbar col-md-6">
													<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress wedy-starBar5" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: 100%;"></div><div class="percentCount"></div></div>
											</div>
											<p class="wedy-starBarText5">80%</p>
										</div>
										<div class="d-flex">
											<p>4 Star</p>
											<div class="single-progressbar col-md-6">
												<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress wedy-starBar4" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: 100%;"></div><div class="percentCount"></div></div>
											</div>
											<p class="wedy-starBarText4">90%</p>
										</div>
										<div class="d-flex">
											<p>3 Star</p>
											<div class="single-progressbar col-md-6">
												<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress wedy-starBar3" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: 100%;"></div><div class="percentCount"></div></div>
											</div>
											<p class="wedy-starBarText3">75%</p>
										</div>
										<div class="d-flex">
											<p>2 Star</p>
											<div class="single-progressbar col-md-6">
												<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress wedy-starBar2" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: 100%;"></div><div class="percentCount"></div></div>
											</div>
											<p class="wedy-starBarText2">10%</p>
										</div>
										<div class="d-flex">
											<p>1 Star</p>
											<div class="single-progressbar col-md-6 mb-0">
												<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress wedy-starBar1" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: 100%;"></div><div class="percentCount"></div></div>
											</div>
											<p class="wedy-starBarText1">0%</p>
										</div>
									</div>
									<div id="comments" class="comments-area comments-area-wrapper">
										<h4 class="comments-title">${productCommentlist.size()} Reviews</h4>
										<ul class="comment-list">
										
											<c:forEach var="productCommentVO" items="${productCommentlist}">
											
												<li class="comment">
													<div class="single-comment justify-content-between d-flex">
														<div class="user justify-content-between d-flex">
															<div class="thumb">
																<img src="<%=request.getContextPath() %>/front-end/mem/headphoto.do?mem_id=${productCommentVO.memIdSeller}" alt="" style="width: 100px; height: 100px"> 
<!-- 																																																																							這邊看起來有點奇怪不過測試後這樣才是對的 -->
															</div>
															<div class="desc">
																<div class="d-flex justify-content-between comment_title">
																	<div class="d-flex align-items-center">
																		<h5>${memSvc.getOneMem(productCommentVO.memIdSeller).mem_name}</h5> <!-- 商品給評論的會員名稱 -->
																	</div>
																</div>
																<div class="comment-content">
																	<p>${productCommentVO.productComment}</p>
																</div>
															</div>
														</div>
													</div>
												</li>
												
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
                            <div id="activityreview" class="tab-pane fade">
                                <div class="tab-content-description">
                                    <h2 class="margin-top-25">
                                        <span class="rating">
<!--                                           第一顆星 		 -->
                                        <%if(ratingAvg < 0.5 || Double.isNaN(ratingAvg)){ %>
                                            <i class="fa fa-star-o"></i>
										<%}else if(ratingAvg >= 0.5 && ratingAvg < 1){ %>
											<i class="fa fa-star-half-o"></i>
										<%}else{ %>
											<i class="fa fa-star"></i>
										<%} %>
<!-- 									      第二顆星		 -->
										<%if(ratingAvg < 1.5 || Double.isNaN(ratingAvg)){ %>
                                            <i class="fa fa-star-o"></i>
										<%}else if(ratingAvg >= 1.5 && ratingAvg < 2){ %>
											<i class="fa fa-star-half-o"></i>
										<%}else{ %>
											<i class="fa fa-star"></i>
										<%} %>
<!-- 									      第三顆星		 -->
										<%if(ratingAvg < 2.5 || Double.isNaN(ratingAvg)){ %>
                                            <i class="fa fa-star-o"></i>
										<%}else if(ratingAvg >= 2.5 && ratingAvg < 3){ %>
											<i class="fa fa-star-half-o"></i>
										<%}else{ %>
											<i class="fa fa-star"></i>
										<%} %>
<!-- 									      第四顆星 		 -->
										<%if(ratingAvg < 3.5 || Double.isNaN(ratingAvg)){ %>
                                            <i class="fa fa-star-o"></i>
										<%}else if(ratingAvg >= 3.5 && ratingAvg < 4){ %>
											<i class="fa fa-star-half-o"></i>
										<%}else{ %>
											<i class="fa fa-star"></i>
										<%} %>
<!-- 									      第五顆星		 -->
										<%if(ratingAvg < 4.5 || Double.isNaN(ratingAvg)){ %>
                                            <i class="fa fa-star-o"></i>
										<%}else if(ratingAvg >= 4.5 && ratingAvg < 5){ %>
											<i class="fa fa-star-half-o"></i>
										<%}else{ %>
											<i class="fa fa-star"></i>
										<%} %>
                                        </span>
                                    </h2>
                                    
                                    <div class="element-progress-bar pagination-progressbar">
                                         <div class="d-flex">
                                            <p>5 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                	<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: <%=(Double.isNaN(fiveStar))?"0":fiveStar+"%" %>;"></div><div class="percentCount"></div></div>
                                            </div>
                                            <p><%=(Double.isNaN(fiveStar))?"0%":fiveStar+"%" %></p>
                                        </div>
                                        <div class="d-flex">
                                            <p>4 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                	<div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: <%=(Double.isNaN(fourStar))?"0":fourStar+"%" %>;"></div><div class="percentCount"></div></div>
                                            </div>
                                            <p><%=(Double.isNaN(fourStar))?"0%":fourStar+"%" %></p>
                                        </div>
                                        <div class="d-flex">
                                            <p>3 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: <%=(Double.isNaN(threeStar))?"0":threeStar+"%" %>;"></div><div class="percentCount"></div></div>
                                            </div>
                                            <p><%=(Double.isNaN(threeStar))?"0%":threeStar+"%" %></p>
                                        </div>
                                        <div class="d-flex">
                                            <p>2 Star</p>
                                            <div class="single-progressbar col-md-6">
                                                <div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: <%=(Double.isNaN(twoStar))?"0":twoStar+"%" %>;"></div><div class="percentCount"></div></div>
                                            </div>
                                            <p><%=(Double.isNaN(twoStar))?"0%":twoStar+"%" %></p>
                                        </div>
                                        <div class="d-flex">
                                            <p>1 Star</p>
                                            <div class="single-progressbar col-md-6 mb-0">
                                               <div class="progressbar" style="width: 100%; background-color: rgb(238, 238, 238); border-radius: 0px;"><div class="proggress" style="background-color: rgb(129, 129, 129); height: 20px; border-radius: 0px; width: <%=(Double.isNaN(oneStar))?"0":oneStar+"%" %>;"></div><div class="percentCount"></div></div>
                                            </div>
                                            <p><%=(Double.isNaN(oneStar))?"0%":oneStar+"%" %></p>
                                        </div>
                                    </div>
                                    <div id="comments" class="comments-area comments-area-wrapper">
                                        <h4 class="comments-title">${reviewList.size() } Reviews</h4>
                                        <ul class="comment-list">
                                        <c:forEach var="reviewVO" items="${reviewList }">
                                        
                                        	<c:if test="${reviewVO.gro_rating_intro != null}">
                                        	<li class="comment">
                                                <div class="single-comment justify-content-between d-flex">
                                                    <div class="user justify-content-between d-flex">
                                                        <div class="thumb">
                                                            <img alt="" src="<%=request.getContextPath() %>/front-end/mem/headphoto.do?mem_id=${reviewVO.mem_id }" class="avatar">                
                                                        </div>
                                                        <div class="desc">
                                                            <div class="d-flex justify-content-between comment_title">
                                                                <div class="d-flex align-items-center">
                                                                    <h5>${reviewVO.mem_name }</h5>
                                                                </div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p>${reviewVO.gro_rating_intro }</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                        </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
<!-- 			分頁--END 			-->

<%@ include file="../footerFile/pageFooter.file"%>

	<!-- chatBox start -->
<%@ include file="../chatBox/chatBoxBody.file"%>
	<!-- chatBox end -->

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
		<!-- product -->
	<script src="assets/js/wedy-mem-detail.js"></script>
	<!-- main js -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/script.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
	
	<!--  拖曳 -->
<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</body>
<script type="text/javascript">

var plusbutton = document.getElementById("plus-button");
var updatebutton = document.getElementById("update-button");


plusbutton.addEventListener('click', function(){
	window.location.href='<%=request.getContextPath()%>/front-end/memphoto/changeHeadPhoto.jsp';
});
updatebutton.addEventListener('click', function(){
	window.location.href='<%=request.getContextPath()%>/front-end/mem/updateMemDetail.jsp';
});

	function AllowDrop(event){
	    event.preventDefault();
	}
	function Drag(event){
	    event.dataTransfer.setData("text",event.currentTarget.id);
	}
	function Drop(event){
	    event.preventDefault();
	    var data=event.dataTransfer.getData("text");
	    var photo_data = document.getElementById(data);
	    event.currentTarget.appendChild(photo_data);
	    $('#'+data).css('display','none');
	    $.ajax({
	    	url: '<%=request.getContextPath()%>/front-end/memphoto/memphoto.do?action=delete', //請求的url地址
	    	dataType: "text", //返回格式為json
	    	async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
	    	data: { "photo_no": data }, //引數值
	    	type: "GET", //請求方式
	    	success: function(data){
	    		$("#Box1").css('display','none');
	    		swal("Good job!", "刪除成功!!", "success");
	    	},
	    });
	    
	}
	    function BodyDrop(event){
	        event.preventDefault();
	        $("#Box1").css('display','none');
	    }

	<%if(memVO.getMem_id().equals(memVOLogin.getMem_id())){%>
		$(".dragphoto").mousedown(function(e){
			 $("#Box1").css('display','block');
		 
		});
	<%}%>
	
		$("body").mouseup(function(e){
			$("#Box1").css('display','none');
	
		});
</script>

<script>
    var MyPoint = "/MembersCenter/${memVOLogin.mem_id}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket;
	
	function connect() {
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			console.log("${memVOLogin.mem_id} Connect Success!");
		};
		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("notice" === jsonObj.type) {
				console.log("成功接收加入訊息!!!!!!!!!");
               var h = '入團通知' || false,
                   s = jsonObj.message,
                   c = $(this).data('color') || '#CD0505',
                   t = 10000;
            triggerToast(h, s, c, t);
			}else if("statusCheck" === jsonObj.type){
				console.log("成功接收揪團訊息!!!!!!!!!");
	               var h = '揪團狀態' || false,
	                   s = jsonObj.message,
	                   c = $(this).data('color') || '#CD0505',
	                   t = 10000;
	        triggerToast(h, s, c, t);
			}else if("finalStatus" === jsonObj.type){
				console.log("成功接收揪團訊息!!!!!!!!!");
	               var h = '揪團狀態' || false,
	                   s = jsonObj.message,
	                   c = $(this).data('color') || '#CD0505',
	                   t = 10000;
	        triggerToast(h, s, c, t);
			}else if("confirm" === jsonObj.type){
				console.log("confirm!!!!!!!!!");
	               var h = '您的預約已取消' || false,
	                   s = jsonObj.message,
	                   c = $(this).data('color') || '#CD0505',
	                   t = 10000;
				$('.top .btns .today').click();
	        	triggerToast(h, s, c, t);
			}
		}
		
		
		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
		
	}
	
	function sendMessage() {
		var message = '${memVOLogin.mem_name}加入你的${groupVO.gro_name}揪團囉!';
			var jsonObj = {
				"type" : "notice",
				"sender" : "${memVOLogin.mem_id}",
				"receiver" : "${groupVO.mem_id}",
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
	}
	 function triggerToast(head, strings, color, time) {
          $.toast({
              heading: head,
              text: strings,
              loaderBg: color,
              hideAfter: Number(time),
              position: 'bottom-left'
          });
      }
    </script>
    


<%@ include file="../chatBox/chatBox.file"%>
</html>
<%} %>
