<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	ProVO proVOLogin = (ProVO) session.getAttribute("proVOLogin");
	String gostatus=(String)request.getAttribute("gostatus");
	pageContext.setAttribute("gostatus", gostatus);
	System.out.println(gostatus);
	%>

<!DOCTYPE html>
<html lang="en">
<head>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>Insert title here</title>
<link rel=icon
	href="${pageContext.request.contextPath}/assets/img/favicon.png"
	sizes="20x20" type="image/png">
<!-- animate -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

<!-- magnific popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/wedy.css">
	
	 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
	
	<style>
	body{
		text-align: center;
	}
h1 {
/*     position: relative; */
/*     left: 45%; */
		 top: 200px; 
/*     text-align: center */
    font-family:Microsoft JhengHei
}
	</style>
</head>

<body>
<%@ include file="../artInclude/navbar.file"%>
<h1 class="col-lg-12">${gostatus}</h1>
<%-- <%@ include file="../footerFile/pageFooter.file"%> --%>
</body>
</html>