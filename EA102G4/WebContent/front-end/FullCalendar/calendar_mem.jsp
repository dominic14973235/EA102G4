<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* "%>
<%@ page import="com.mem.model.*"%>

<%@ page import="com.pro.model.*"%>
<%@ page import="com.individualClass.model.*"%>
<%@ page import="com.classType.model.*"%>
<%
	//for include
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	
//	for testing
// 	MemService memS = new MemService();
// 	MemVO memVOLogin = memS.getOneMem("MEM0000006");
// 	pageContext.setAttribute("memVOLogin",memVOLogin );
	
// // 	//for get authorized class name		
// 	IndividualClassVO individualClassVO = (IndividualClassVO) request.getAttribute("individualClassVO");
// 	pageContext.setAttribute("individualClassVO", individualClassVO);
			
// 	ClassTypeService ctSvc = new ClassTypeService();
// 	ClassTypeVO ctVO = ctSvc.getOneClassType(individualClassVO.getC_type_no());
// 	pageContext.setAttribute("ctVO",ctVO);	
	
// 	ProService proSvc = new ProService();
// 	ProVO proVO = proSvc.getOnePro(individualClassVO.getPro_ID());
// 	pageContext.setAttribute("proVO", proVO);
%>


<html>
<head>
<title>Calendar_mem</title>

	
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/FullCalendar/lib/calendar.css">

	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/jquery-2.2.4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/jquery.magnific-popup.js"></script>

<style>
div[data-order_no] {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 180px;
}
</style>


</head>

<body>

	<div id='container'>
		<div id='row'>
			<div class='calendar'>
				<div class='top'>
					<div class='title'></div>
					<div class='btns'>
						<button class='today'>本月</button>
						<button class='icon-keyboard_arrow_left prev'>上月</button>
						<button class='icon-keyboard_arrow_right next'>下月</button>
					</div>
				</div>

				<div class='month'></div>
			</div>
			<div class = 'calendar-events'>
				<div></div>
				<div class="hr">00:00<span></span></div>
				<div class="hr">01:00<span></span></div>
				<div class="hr">02:00<span></span></div>
				<div class="hr">03:00<span></span></div>
				<div class="hr">04:00<span></span></div>
				<div class="hr">05:00<span></span></div>
				<div class="hr">06:00<span></span></div>
				<div class="hr">07:00<span></span></div>
				<div class="hr">08:00<span></span></div>
				<div class="hr">09:00<span></span></div>
				<div class="hr">10:00<span></span></div>
				<div class="hr">11:00<span></span></div>
				<div class="hr">12:00<span></span></div>
				<div class="hr">13:00<span></span></div>
				<div class="hr">14:00<span></span></div>
				<div class="hr">15:00<span></span></div>
				<div class="hr">16:00<span></span></div>
				<div class="hr">17:00<span></span></div>
				<div class="hr">18:00<span></span></div>
				<div class="hr">19:00<span></span></div>
				<div class="hr">20:00<span></span></div>
				<div class="hr">21:00<span></span></div>
				<div class="hr">22:00<span></span></div>
				<div class="hr">23:00<span></span></div>
			</div>
		</div>
	</div>

<!-- Modal for cancel Individual class -->
	<div class="modal fade" id="myModal"  tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New message</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
						<div class="order_no"></div>
						
						<div class ="pro_ID"></div>	
			
						<div class ="i_class_no"></div>
							
						<div class ="c_type_no"></div>
						
						<div class = "loc"></div>
												
						<div class="rDate"></div>
						
						<div class="hrInsert"></div>

						<div class="p_coin"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="cancelClass">取消預約</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal for cancel Individual class -->
	

<!-- Modal for booking Group class -->
	<div class="modal fade" id="groupClass" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New message</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
				<div class="modal-body">
					<jsp:useBean id="memSc" scope="page" class="com.mem.model.MemService"/>				
						<div id="mem" value ="${memVOLogin.mem_id }">
							<div>
								<div>預約會員:</div><div> ${memVOLogin.mem_name}</div>
							</div>
						</div>
						<div class="pro" value ="${proVO.pro_ID }">
							<div>
								<div>預約教練:</div><div class="pro_name">${memSc.getOneMem(proVO.mem_ID).mem_name}</div>
							</div>
						</div>
					
						<div name="g_class_no" class="g_class_no"></div>
						
						<div class ="c_type_no"></div>
						
						<div class = "loc"></div>
						
						<div class="rDate"></div>
						
						<div class="hrInsert"></div>
						
						<div class= "g_max"></div>
							
						<div class= "quanity"></div>
						
						<div class="gp_coin"></div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="cancelgClass">取消預約</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal for booking Group class -->	
	

	<script type="text/javascript">

//------------------------------------------Original Create Calendar-----------------------------------------
		// BELOW: Original JS code
		let dateCheck; // the Database date;]
		$(document).ready(
			function() {
				var _weeks = [ '日', '一', '二', '三', '四', '五', '六' ];
				// 1 產生出 二維 陣列
				// 2 二維陣列 是月份內的每天 資訊(日期)
				// 3 產生出 html 元素，加到 month
				function monthDayCount(y, m) {
					return (--m == 1) ? ((y % 4) === 0)
							&& ((y % 100) !== 0)
							|| ((y % 400) === 0) ? 29 : 28 : [ 31,
							28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
							31 ][m];
				}
				function prevMonth(y, m) {
					return {
						y : m == 1 ? y - 1 : y,
						m : m == 1 ? 12 : (m - 1)
					};
				}
				function nextMonth(y, m) {
					return {
						y : m == 12 ? y + 1 : y,
						m : m == 12 ? 1 : (m + 1)
					};
				}
				function createMonthArr(y, m) {
					// 1 號 在星期幾
					// 本月 有幾天
					// 本月 有幾週
					var firstDayWeek = new Date(y, m - 1, 1)
							.getDay();
					var monthCount = monthDayCount(y, m);
					var weekCount = parseInt(
							(firstDayWeek + monthCount) / 7, 10)
							+ (((firstDayWeek + monthCount) % 7) ? 1
									: 0);

					var p = prevMonth(y, m);
					var prevMonthCount = monthDayCount(p.y, p.m);
					var n = nextMonth(y, m);

					var date = new Date();

					return Array.apply(null, Array(weekCount)).map(function(_, i) {
							return Array.apply(null,Array(7)).map(function(_,j) {
									var d = i* 7+ j- firstDayWeek+ 1;
									var m2 = m;
									var y2 = y;
									var l = true; // 是否為本月份

									if (d > monthCount) {
											d = d- monthCount;
											m2 = n.m;
											y2 = n.y;
											l = false;
										}

									if (d <= 0) {
											d = d+ prevMonthCount;
											m2 = p.m;
											y2 = p.y;
											l = false;
										}
									
									var t = y2 == date.getFullYear()&& m2 == date.getMonth() + 1 && d == date.getDate();

										return {
											y : y2,
											m : m2,
											d : d,
											l : l,
											t : t
												};
											});
									});
				}

				var $month = $('.month');
				var $title = $('.calendar .top .title');

				function renderMonth(y, m) {
					var monthArr = createMonthArr(y, m);

					var w = $('<div />').addClass('weeks').append(
							_weeks.map(function(t) {
								return $('<div />').text(t);
							}));

					var ds = monthArr.map(function(w) {
							return $('<div />').addClass('days').append(w.map(function(d) {
								return $('<div />').addClass(d.l ? 'dats': 'next-prev-month dats')
													.addClass(d.t ? 'today': null)
													.attr('data-y',d.y)
													.attr('data-m',d.m)
													.attr('data-d',d.d)
													.attr('data-date', new Date(d.y,d.m-1,d.d))
										}));
							});

					$month.empty().append(w).append(ds);
					$title.attr('data-y', y).attr('data-m', m);
				}

				// 今月
				$('.top .btns .today').click(
						function() {
							var date = new Date();
							renderMonth(date.getFullYear(), date.getMonth() + 1);
							getEvents();
						}).click();

				// 上月
				$('.top .btns .prev').click(function() {
					var y = parseInt($title.attr('data-y'), 10);
					var m = parseInt($title.attr('data-m'), 10);
					var p = prevMonth(y, m);
					renderMonth(p.y, p.m);
					getEvents();
				});

				// 下月
				$('.top .btns .next').click(function() {
					var y = parseInt($title.attr('data-y'), 10);
					var m = parseInt($title.attr('data-m'), 10);
					var n = nextMonth(y, m);
					renderMonth(n.y, n.m);
					getEvents();
				});

//------------------------------------------Original Create Calendar-----------------------------------------

							
			//timetable fade out
			$('div>.btn.btn-link').click(function(){
				$('.calendar-events').fadeout(700);
			});
			
			//binding click date and refresh 24hr section
			$('.calendar').on('click','.dats',function() {
				var yy = $(this).data('y');
				var dataM = $(this).data('m');
				var dataD = $(this).data('d');
				datsAction(yy,dataM,dataD);
			});
			
			//binding click hr section for insert data in modal 
			$('.calendar-events').on('click', '.open', function(){
				var dateForInsert = $('.calendar-events div div').data('date');
				var hrForInsert = $(this).parent().prevAll().length-1;
				var class_no = $(this).data('class_no');
				var order_no = $(this).data('order_no');			
				clickIndHr(dateForInsert,hrForInsert,class_no,order_no);
			});
			
			//binding cancel button
			$("#myModal").on('click','#cancelClass', function(){
				cancelBooking()
			});
			
			
			
			//click on hr, show on modal and show modal
			$('.calendar-events').on('click', '.openGroup', function(){
				var dateForInsert = $('.calendar-events div div').data('date');
				var g_class_no = $(this).data('class_no');
				var hrForInsert = $(this).parent().prevAll().length-1;
				var g_order_no = $(this).data('order_no');
				clickGroupHr(dateForInsert,g_class_no,hrForInsert,g_order_no);
			})

			//cancel group class
			$("#groupClass").on('click','#cancelgClass', function(){
				cancelgClass()
			});
			
		});

//====================== 以下 比對資料  將課程放入正確日期===========================
// 	get data from database
	function getEvents(){
				$.ajax({
					url : "<%=request.getContextPath()%>/front-end/ProAndClass/MyCalendar.do",
					type : "get",
					dataType: "json",
					data : {
						action : "Display_Calendar_mem",
						mem_id : "${memVOLogin.mem_id}"
					},
					success : function(res) {
						console.log(res);
						compareDate(res);
					},
		            error: function(){
		            	alert("AJAX-class Error!")
		            }

				})
	}
	//取得月曆的data 轉為字串 	&&	比對資料 放入正確日期
	function compareDate (res){
		let dateArray = [];
        for(var i = 0; i<=41; i++){
  	      var yy = $('.days div').eq(i).data('y');
	      var dataM = $('.days div').eq(i).data('m');
	      var dataD = $('.days div').eq(i).data('d');
	      var mm;
	      var dd;

	      if(dataM<10){
	       mm='0'+dataM;
	      }else if(dataM >9){
	       mm=dataM;
	      }
	      
	      if(dataD<10){
	       dd='0'+dataD;
	      }else if(dataD >9){
	       dd=dataD;
	      }
	      
	      var dateCheck= yy+'-'+mm+'-'+dd;
	      dateArray.push(dateCheck);
	    }
        
      //比對資料 放入正確日期
        for(var i =0; i<dateArray.length;i++){
	    	 for(var j =0; j<res.length; j++){
	    		 if(dateArray[i] === res[j].rDate){
	    			 if(res[j].status<2){ //if status isn't 0, append, why dayoff can append?
		    			 $('.days>div').eq(i).append(`<div data-order_no =`+res[j].order_no+`
		    					 					data-class_no=`+res[j].class_no+`
		    					 					data-pro_ID=`+res[j].pro_ID+`
		    					 					data-rDate=`+res[j].rDate+`
		    					 					data-hr=`+res[j].hr+`
		    					 					data-g_max=`+res[j].g_max+`
		    					 					>`+res[j].class_name+'</div>')
	    			 }	    			 
	    		}
	    	 }
	    }        
	}
//====================== 以上 比對資料  將課程放入正確日期===========================
	
//====================== 以下  顯示可否預約到前端===========================
	//點選日期 顯示是否可預約的狀態
	function insertHR(res){
		$('span').removeClass();
		for(var k =0; k < res.length; k++){		
			if(res[k].class_no.charAt(0)==="I"&& res[k].status<2){
// 				&& res[k].status<2
				for(var i = 0 ; i<24; i++){	
					if(res[k].hr.charAt(i)==='1'){
						calendarEvents(i, res[k].class_name+"(私教)", 'open',res[k].class_no, res[k].order_no);
					}
				}	
			}else if(res[k].class_no.charAt(0)==="-"){
				for(var i = 0 ; i<24; i++){	
					if(res[k].hr.charAt(i)==='1'){	
						calendarEvents(i, "不開放預約", 'close',res[k].class_no);
					}
				}	
// 				 && res[k].status!=0
			}else if(res[k].class_no.charAt(0)==="G" && res[k].status<2){
				for(var i = 0 ; i<24; i++){	
					if(res[k].hr.charAt(i)==='1'){
						calendarEvents(i, res[k].class_name+"   (團課)", 'openGroup',res[k].class_no, res[k].order_no);
					}
				}	
			}
		}	
	}
	
	function calendarEvents(index, msg, isOpen, class_no, order_no){
		$('.calendar-events > div:nth-child('+ (index+2) +') span').text(msg).removeClass('close')
																				.removeClass('open')
																				.removeClass('openIndividual')
																				.removeClass('openGroup')
																				.addClass(isOpen)
																				.attr('data-class_no', class_no)
																				.attr('data-order_no', order_no);

		
	}
	//====================== 以上  顯示可否預約到前端===========================
		
	//====================== 以下  點選日期  與insertHR(res)配對  ===========================
	//點選日期區塊 重載小時區塊
	// 功能 dateClick
	function datsAction(yy,dataM,dataD) {
		$('.calendar-events div span').empty(); // clean Calendar-events span	
		$('.calendar-events').fadeIn(700);

		var mm;
		var dd;
		if(dataM<10){
			mm='0'+dataM;
			}else if(dataM >9){
			mm=dataM;
			}
						      
		if(dataD<10){
			dd='0'+dataD;
			}else if(dataD >9){
			dd=dataD;
			}				      
		var dateCheck= yy+'-'+mm+'-'+dd;
		
		$('.calendar-events div:first').text('');
		$('.calendar-events div:first').append(`<div data-date=`+dateCheck+`>`+dateCheck+`</div>`);
		
		$.ajax({
			url : '<%=request.getContextPath()%>/front-end/ProAndClass/MyCalendar.do',
			type : "get",
			dataType: "json",
			data : {
				action : "Display_Mem_OneDay",
				rDate : dateCheck,
				mem_id : "${memVOLogin.mem_id}"
			},
			success : function(res) {
				console.log(res);
				insertHR(res);
			},
			error: function(){
				alert("AJAX-class Error!")
			}
					
		})
	
	}
	
	//====================== 以上  點選日期  與insertHR(res)配對  ===========================

	
	//====================== 以下  輸入預約訂單===========================
	//點選預約 一對一課
	function clickIndHr(dateForInsert,hrForInsert,class_no,order_no){
		$.ajax({
			url : '<%=request.getContextPath()%>/front-end/ProAndClass/MyCalendar.do',
			type : "get",
			dataType: "json",
			data : {
				action : "getMemBooked",
				class_no : class_no,
				order_no : order_no
			},
			success : function(res) {
				$('.order_no').empty();
				$('.pro_ID').empty();
				$('.i_class_no').empty();
				$('.c_type_no').empty();
				$('.loc').empty();
				$('.rDate').empty();
				$('.hrInsert').empty();
				$(".p_coin").empty();
				
				if(res.status!='2'){
	 			$('.order_no').append('<div value="'+order_no+'" id="order_no"><div> 預約編號:</div><div>'+order_no+'</div></div>');
 				$('.pro_ID').append('<div value="'+res.pro_ID+'" id="pro_ID"><div> 預約教練:</div><div>'+res.pro_name+'</div></div>');
				$('.i_class_no').append('<div value="'+class_no+'" id="class_no"><div> 課程類別:</div><div id="className">'+res.class_name+'</div></div>');
 				$('.c_type_no').append('<div value="'+res.t_name+'" id="c_type_no"><div> 課程類別:</div><div>'+res.t_name+'</div></div>');
				$('.loc').append('<div value="'+res.loc+'" id="loc"><div> 授課地點:</div><div>' +res.loc+'</div></div>');
 				$('.rDate').append('<div value="'+dateForInsert+'" id="rDate"><div> 日期:</div><div>' +dateForInsert+'</div></div>');
 				$('.hrInsert').append('<div value="'+hrForInsert+'" id="hrIndex"><div> 時間:</div><div>' +hrForInsert+':00</div></div>');
 				$(".p_coin").append('<div value="'+res.p_coin+'" id="p_coin"><div> 價格 :</div><div>$ '+res.p_coin+'</div></div>');					
				}
			},

            error: function(){
            	alert("AJAX-class Error!")
            }
		})		
		$('#myModal').modal('show');
	}

	//====================== 以上  輸入預約訂單===========================
	
	//====================== 以上  輸入預約訂單===========================
		
		// 綁定取消私人課程一對一預約事件
		function cancelBooking(){
			var i_class_no = $('#class_no').attr('value');
			var i_order_no = $('#order_no').attr('value');
			var rDate = $('#rDate').attr('value');
			var hrIndex = $('#hrIndex').attr('value');
			var p_coin = $('#p_coin').attr('value');
			var pro_ID = $('#pro_ID').attr('value');
			var className =$('#className').text();
			console.log(className);
			
			$.ajax({
			url : '<%=request.getContextPath()%>/front-end/ProAndClass/MyCalendar.do',
				type : "get",
				dataType: "text",
				data : {
					action : "update_Booking",
					i_order_no : i_order_no,
					i_class_no : i_class_no,
					rDate : rDate,
					hrIndex : hrIndex,
					p_coin : p_coin,
					pro_ID : pro_ID
				},
			
				success : function(res) {
					alert(res);
					$('[data-dismiss="modal"]').click();
					$('.top .btns .today').click();
					var arr = rDate.split('-');
					var m = parseInt(arr[1]);
					var d = parseInt(arr[2]);
					for(var i = 0; i < $('.days > .dats').size(); i++) {
						var mp = parseInt($('.days > .dats').eq(i).data('m'));
						var dp = parseInt($('.days > .dats').eq(i).data('d'));
						if(mp === m && dp === d) {
							$('.days > .dats').eq(i).click();
							break;
						}
					}

				},
			
	            error: function(){
	            	alert("AJAX-class Error!")
	            }

			})
		}
	
//==========================================================
		//CLICK ON GROUP CLASS
		function clickGroupHr(dateForInsert,g_class_no,hrForInsert,g_order_no){
			$.ajax({
				url : '<%=request.getContextPath()%>/front-end/ProAndClass/MyCalendar.do',
				type : "get",
				dataType: "json",
				data : {
					action : "getGroup_For_Display",
					g_class_no : g_class_no,
					g_order_no : g_order_no
				},
				success : function(res) {

					$('.g_class_no').empty();
					$('.c_type_no').empty();
					$('.loc').empty();
					$('.rDate').empty();
					$('.hrInsert').empty();
					$(".g_max").empty();
					$(".quanity").empty();
					$(".gp_coin").empty();
					
					$('.g_class_no').append('<div value="'+res.class_no+'" id="g_class_no"><div>課程名稱 :</div><div> '+res.G_name+'</div></div>');
					$('.c_type_no').append('<div value="'+res.c_type_no+'" id="c_type_no"><div>課程類別 :</div><div> '+res.c_type_no+'</div></div>');
					$('.loc').append('<div value="'+res.loc+'" id="loc"><div>課程地點 :</div><div> '+res.loc+'</div></div>');
					$('.rDate').append('<div value="'+dateForInsert+'"  id="rDate"><div>日期:</div><div> ' +dateForInsert+'</div></div>');
					$('.hrInsert').append('<div value="'+hrForInsert+'" id="hrIndex"><div>時間:</div><div> ' +hrForInsert+':00</div></div>');
					$(".g_max").append('<div value="'+res.g_max+'" id="g_max"><div>人數上限 :</div><div> '+res.g_max+'</div></div>');
					$(".quanity").append('<div value="'+res.quantity+'" id="quanity"><div>已預約人數 :</div><div> '+res.quantity+'</div></div>');
					$(".gp_coin").append('<div value="'+res.p_coin+'" id="gp_coin"><div>價格 :</div><div>'+res.p_coin+'</div></div>');
					$('#gp_coin').after('<div value="'+g_order_no+'" id="g_order_no"><div></div><div></div></div>');
					$(".pro_name").text(res.pro_name);
				},
				
	            error: function(){
	            	alert("AJAX-class Error!")
	            }

			})
			
			$('#groupClass').modal('show');
		}
	
		//==========================================================


		// 綁定取消團課預約事件
		function  cancelgClass(){
			var g_class_no = $('#g_class_no').attr('value');
			var g_order_no = $('#g_order_no').attr('value');
			var rDate = $('#rDate').attr('value');
			var hrIndex = $('#hrIndex').attr('value');
			var p_coin = $('#gp_coin').attr('value');
			console.log(g_class_no);
			console.log(g_order_no);
			console.log(rDate);
			console.log(hrIndex);
			console.log(p_coin);

			var hr ="";
			for(var i =0;i<hrIndex-1;i++){
				hr+="0";
			}
			hr+="01";
			while(hr.length<24){
				hr+="0";
			}
			$.ajax({
			url : '<%=request.getContextPath()%>/front-end/groupclass/groupOrder.do',
				type : "post",
				dataType: "text",
				data : {
					action: "refund",
					g_order_no : g_order_no,
					g_class_no : g_class_no,
					rDate : rDate,
					hr : hr,
					p_coin : p_coin
				},
			
				success : function(data) {
					alert('成功取消');
					
					//refresh
					$('[data-dismiss="modal"]').click();
					$('.top .btns .today').click();
					var arr = rDate.split('-');
					var m = parseInt(arr[1]);
					var d = parseInt(arr[2]);
					for(var i = 0; i < $('.days > .dats').size(); i++) {
						var mp = parseInt($('.days > .dats').eq(i).data('m'));
						var dp = parseInt($('.days > .dats').eq(i).data('d'));
						if(mp === m && dp === d) {
							$('.days > .dats').eq(i).click();
							break;
						}
					}
					$('[data-dismiss="modal"]').click();
				},
			
	            error: function(){
	            	alert("AJAX-class Error!")
	            }
			})
		}
	</script>
</body>
</html>
