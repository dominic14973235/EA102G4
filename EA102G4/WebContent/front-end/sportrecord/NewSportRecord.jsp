<%@page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.sports.model.*"%>

<%
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	pageContext.setAttribute("memVO", memVOLogin);

	SportsService sportsSvc = new SportsService();
	List<SportsVO> sportslist = sportsSvc.getAll();
	pageContext.setAttribute("sportslist", sportslist);

	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);

	try {
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

		//會員編號
		String mem_id = request.getParameter("mem_id");

		//紀錄日期
		java.sql.Date record_date = null;
		String record_date_string = request.getParameter("record_date");
		try {
			record_date = java.sql.Date.valueOf(record_date_string);
		} catch (IllegalArgumentException e) {
			record_date = new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("請輸入日期!");
		}

		//運動項目
		String sport_no = request.getParameter("sport_no");
		String sport_no_name;
		if (sport_no == "SPORT00001") {
			sport_no_name = "舉重";
		} else if (sport_no == "SPORT00002") {
			sport_no_name = "跑步";
		} else if (sport_no == "SPORT00003") {
			sport_no_name = "游泳";
		} else if (sport_no == "SPORT00004") {
			sport_no_name = "瑜珈";
		} else {
			sport_no_name = "籃球";
		}

		//記錄一
		float record1 = 0;
		String myrecord1 = request.getParameter("record1");
		String record1Reg = "^[0-9]{0,4}+.{0,1}[0-9]{0,1}$";
		if (myrecord1 == null) {
			errorMsgs.add("紀錄不能為空值!!");
		} else if (!myrecord1.matches(record1Reg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("紀錄:格式錯誤");
		} else {
			record1 = Float.parseFloat(myrecord1);
		}

		//記錄二
		float record2 = 0;
		String myrecord2 = request.getParameter("record2").trim();
		String record2Reg = "^[0-9]{0,4}+.{0,1}[0-9]{0,1}$";
		if (!myrecord2.matches(record2Reg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("紀錄:格式錯誤");
		} else {
			record2 = Float.parseFloat(myrecord2);
		}

		SportRecordVO sportRecordVO = new SportRecordVO();
		sportRecordVO.setMem_id(mem_id);
		sportRecordVO.setRecord_date(record_date);
		sportRecordVO.setSport_no(sport_no);
		sportRecordVO.setRecord1(record1);
		sportRecordVO.setRecord2(record2);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			request.setAttribute("sportRecordVO", sportRecordVO); // 含有輸入格式錯誤的sportRecordVO物件,也存入req
			RequestDispatcher failureView = request
					.getRequestDispatcher(request.getContextPath() + "/front-end/sportrecord/sportRecord.jsp");
			failureView.forward(request, response);
			return;
		}

		/***************************2.開始新增資料***************************************/

		SportRecordService sportRecordSvc = new SportRecordService();
		sportRecordVO = sportRecordSvc.addSportRecord(sport_no, mem_id, record_date, record1, record2);
		
		System.out.print(sportRecordVO.getRecord_no());
		pageContext.setAttribute("insertRecordNo", sportRecordVO.getRecord_no());

		/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = request
				.getRequestDispatcher(request.getContextPath() + "/front-end/sportrecord/sportRecord.jsp");
		failureView.forward(request, response);
	}
%>
<%
	SportRecordService sportRecordSvc = new SportRecordService();
	List<SportRecordVO> list = sportRecordSvc.getOneSportRecordByMemId(memVOLogin.getMem_id());
	pageContext.setAttribute("list", list);
%>




<div class="alert alert-dark line col-md-12" id="sportdata-bar">
	<div class="col-md-2 right-border-bar">紀錄日期</div>
	<div class="col-md-2 right-border-bar">運動項目</div>
	<div class="col-md-6 right-border-bar">記錄</div>
	<div class="col-md-2 right-border-bar ">修改</div>
</div>
<%@ include file="page1.file"%>

<input type="hidden" name="whichPage" id="whichPage" value="<%=whichPage%>">
<div id="data-div" class="col-md-12">
	<c:forEach var="sportRecordVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<c:choose>
			<c:when test="${sportRecordVO.record_no == insertRecordNo }">
				<div class="alert-primary line col-md-12 sportdata-div changecolor">
			</c:when>
			<c:otherwise>
				<div class="alert-primary line col-md-12 sportdata-div">
			</c:otherwise>
		</c:choose>
		<div class="col-md-2 right-border-data">
			<fmt:formatDate value="${sportRecordVO.record_date}"
				pattern="yyyy-MM-dd" />
		</div>
		<c:forEach var="sportsVO" items="${sportslist}" begin="0" end="<%=sportslist.size()%>">
			<c:choose>
				<c:when test="${sportRecordVO.sport_no == sportsVO.sport_no}">
					<div class="col-md-2 right-border-data">${sportsVO.sport_name}</div>
					<div class="col-md-3 right-border-data">${sportRecordVO.record1}${sportsVO.unit1}</div>
					<c:choose>
						<c:when test="${sportRecordVO.record2==0}">
							<div class="col-md-3 right-border-data"></div>
						</c:when>
						<c:otherwise>
							<div class="col-md-3 right-border-data">${sportRecordVO.record2}${sportsVO.unit2}</div>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
		</c:forEach>
		<center>
			<div class="col-md-2">
				<!-- Trigger the modal with a button -->

				<FORM METHOD="post" class="align-center"
					ACTION="<%=request.getContextPath()%>/front-end/sportrecord/sportrecord.do">
					<input type="submit" class="btn btn-primary" value="修改資料">
					<input type="hidden" name="action" value="getOne_From">
					<input type="hidden" name="whichPage" id="whichPage" value="<%=whichPage%>">
					<input type="hidden" name="record_no" value="${sportRecordVO.record_no}">
				</FORM>
			</div>
		</center>
</div>
</c:forEach>
<div class="row">
	<div class="col-md-12">
		<div
			class="pagination-area d-flex justify-content-center margin-top-50">
			<ul>
				<li id="before"><span class="next page-bumber"><i
						class="fa fa-angle-left"></i> Prev</span></li>

				<li><span class="page-bumber current"><%=whichPage%></span></li>

				<li id="after"><span class="next page-bumber">Next <i
						class="fa fa-angle-right"></i></span></li>
			</ul>
			<%if (pageNumber > 1) {%>
			<div class="dropdown">
				<a class="btn btn-secondary dropdown-toggle" type="button"
					id="PageSelecter" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 第<%=whichPage%>頁
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<%for (int i = 1; i <= pageNumber; i++) {%>
					<a class="dropdown-item"
						href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp?whichPage=<%=i%>">第<%=i%>頁
					</a>
					<%}%>
				</div>
			</div>
			<%}%>
		</div>
	</div>
</div>

<script type="text/javascript">
		var before = document.getElementById("before");
		var after = document.getElementById("after");
		
		before.addEventListener("click", function(){
			<%if (whichPage > 1) {%>
				window.location.href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp?whichPage=<%=whichPage - 1%>";
			<%}%>
		});
		
		after.addEventListener("click", function(){
			<%if (whichPage < pageNumber) {%>
				window.location.href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp?whichPage=<%=whichPage + 1%>";
			<%}%>
		});

	$('.btn-secondary').click(function() {
		if ($('.dropdown-menu').css('display') === 'none') {
			$('.dropdown-menu').css('display', 'block');
		}
		$('.dropdown-menu').mouseover(function() {
			$('.dropdown-menu').css('display', 'block');
		})
		$('.dropdown-menu').mouseout(function() {
			$('.dropdown-menu').css('display', 'none');
		})
	});
</script>



