<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* "%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.classType.model.*"%>
<%

	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");


//	for testing
// 	ProVO proVO =(ProVO)request.getAttribute("proVO");
// 	pageContext.setAttribute("proVO", proVO);
// 	GroupClassService gcSvc = new GroupClassService();
// 	GroupClassVO GroupClassVO = gcSvc.getOneGroupClass("GC1000001");
// 	pageContext.setAttribute("gcVO", GroupClassVO);

	//for get authorized class name		
	GroupClassVO GroupClassVO = (GroupClassVO) request.getAttribute("gcVO");
	pageContext.setAttribute("GroupClassVO", GroupClassVO);
	pageContext.setAttribute("gcVO", GroupClassVO);

	ClassTypeService ctSvc = new ClassTypeService();
	ClassTypeVO ctVO = ctSvc.getOneClassType(GroupClassVO.getC_type_no());
	pageContext.setAttribute("ctVO",ctVO);	
	
	ProService proSvc = new ProService();
	ProVO proVO = proSvc.getOnePro(GroupClassVO.getPro_id());
	pageContext.setAttribute("proVO", proVO);
%>


<html>
<head>
<title>GroupClassCalendar</title>
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/front-end/assets/css/bootstrap.min.css"> --%>
	
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/FullCalendar/lib/calendar.css">

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
				<div class="calhr">00:00<span></span></div>
				<div class="calhr">01:00<span></span></div>
				<div class="calhr">02:00<span></span></div>
				<div class="calhr">03:00<span></span></div>
				<div class="calhr">04:00<span></span></div>
				<div class="calhr">05:00<span></span></div>
				<div class="calhr">06:00<span></span></div>
				<div class="calhr">07:00<span></span></div>
				<div class="calhr">08:00<span></span></div>
				<div class="calhr">09:00<span></span></div>
				<div class="calhr">10:00<span></span></div>
				<div class="calhr">11:00<span></span></div>
				<div class="calhr">12:00<span></span></div>
				<div class="calhr">13:00<span></span></div>
				<div class="calhr">14:00<span></span></div>
				<div class="calhr">15:00<span></span></div>
				<div class="calhr">16:00<span></span></div>
				<div class="calhr">17:00<span></span></div>
				<div class="calhr">18:00<span></span></div>
				<div class="calhr">19:00<span></span></div>
				<div class="calhr">20:00<span></span></div>
				<div class="calhr">21:00<span></span></div>
				<div class="calhr">22:00<span></span></div>
				<div class="calhr">23:00<span></span></div>
			</div>
		</div>
	</div>


<!-- Modal for booking Group class -->
	<div class="modal fade" id="GroupModal" tabindex="-1"
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
								<div>預約教練:</div><div>${memSc.getOneMem(proVO.mem_ID).mem_name}</div>
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
<%if(memVOLogin != null){ %>				
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="addCart">加入購物車</button>	
					<button type="button" class="btn btn-primary" id="sendGroup">直接購買</button>
<%} %>					
					
				</div>
			</div>
		</div>
	</div>
	<!-- Modal for booking Group class -->	
	



	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/jquery-2.2.4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/assets/js/jquery.magnific-popup.js"></script>

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
// 						datsAction();
						getEvents();
// 						clickGroupHr()
					}).click();

			// 上月
			$('.top .btns .prev').click(function() {
				var y = parseInt($title.attr('data-y'), 10);
				var m = parseInt($title.attr('data-m'), 10);
				var p = prevMonth(y, m);
				renderMonth(p.y, p.m);
// 				datsAction();
				getEvents();
// 				clickGroupHr()
			});

			// 下月
			$('.top .btns .next').click(function() {
				var y = parseInt($title.attr('data-y'), 10);
				var m = parseInt($title.attr('data-m'), 10);
				var n = nextMonth(y, m);
				renderMonth(n.y, n.m);
// 				datsAction();
				getEvents();
// 				clickGroupHr()
			});

//------------------------------------------Original Create Calendar-----------------------------------------

							
		//timetable fade out
		$('div>.btn.btn-link').click(function(){
			$('.calendar-events').fadeout(700);
		});
		
		//click date and refresh 24hr section
		$('.calendar').on('click','.dats',function() {
			var yy = $(this).data('y');
			var dataM = $(this).data('m');
			var dataD = $(this).data('d');
			datsAction(yy,dataM,dataD); 
		});
	
		//click on hr to input data into modal and show modal
		$('.calendar-events').on('click', '.openGroup', function(){
			var dateForInsert = $('.calendar-events div div').data('date');
			var g_class_no = $(this).data('class_no');
			var hrForInsert = $(this).parent().prevAll().length-1;
			var g_order_no = $(this).data('order_no');
			clickGroupHr(dateForInsert,g_class_no,hrForInsert,g_order_no);
		})
		
		//book gropu class
		$("#GroupModal div.modal-content").on('click','#sendGroup', function(){
			bookGroupDirect()	
		});	
		
		//addCart
		$("#GroupModal div.modal-content").on('click','#addCart', function(){
			addCart();
		});	
		
	});

	//====================== 以下 比對資料  將課程放入正確日期===========================
// 	get data from database
	function getEvents(){
		$.ajax({
			url : '<%=request.getContextPath()%>/front-end/ProAndClass/MyCalendar.do',
			type : "get",
			dataType: "json",
			data : {
				action : "Display_TrainerPublic",
				pro_ID : "<%=proVO.getPro_ID() %>"
			},
			success : function(res) {
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
	    		 if(dateArray[i] === res[j].rDate&&res[j].class_no.charAt(0)==='G'){
	    			 if(res[j].status!=0){
		    			 $('.days>div').eq(i).append(`<div data-order_no =`+res[j].order_no+`
		    					 					data-class_no=`+res[j].class_no+`
		    					 					data-pro_ID=`+res[j].pro_ID+`
		    					 					data-rDate=`+res[j].rDate+`
		    					 					data-calhr=`+res[j].hr+`
		    					 					data-g_max=`+res[j].g_max+`
		    					 					data-gOrderNo=`+res[j].gOrderNo+
		    					 					`>`+res[j].class_name+'</div>')
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
//  		$('span').addClass('open');
		
		for(var k =0; k < res.length; k++){
			if(res[k].status!=='1'){
				if(res[k].class_no.charAt(0)==="G"){
					for(var i = 0 ; i<24; i++){	
						if(res[k].hr.charAt(i)==='1'){
							calendarEvents(i, res[k].class_name, 'openGroup',res[k].class_no, res[k].order_no);
						}
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
				action : "Display_OneDay",
				rDate : dateCheck,
				pro_ID: '<%=proVO.getPro_ID() %>'
			},
			success : function(res) {
//					console.log(res);
				insertHR(res);
			},
			error: function(){
				alert("AJAX-class Error!")
			}
					
			})
	}

//====================== 以上  點選日期  與insertHR(res)配對  ===========================

	
	//====================== 以下  輸入預約訂單===========================

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
				
				$('.g_class_no').append('<div value="'+res.class_no+'" id="class_no"><div>課程名稱 :</div><div> '+res.G_name+'</div></div>');
				$('.c_type_no').append('<div value="'+res.c_type_no+'" id="c_type_no"><div>課程類別 :</div><div> '+res.c_type_no+'</div></div>');
				$('.loc').append('<div value="'+res.loc+'" id="loc"><div>課程地點 :</div><div> '+res.loc+'</div></div>');
				$('.rDate').append('<div value="'+dateForInsert+'"  id="rDate"><div>日期:</div><div> ' +dateForInsert+'</div></div>');
				$('.hrInsert').append('<div value="'+hrForInsert+'" id="hrIndex"><div>時間:</div><div> ' +hrForInsert+':00</div></div>');
				$(".g_max").append('<div value="'+res.g_max+'" id="g_max"><div>人數上限 :</div><div> '+res.g_max+'</div></div>');
				$(".quanity").append('<div value="'+res.quantity+'" id="quanity"><div>已預約人數 :</div><div> '+res.quantity+'</div></div>');
				$(".gp_coin").append('<div value="'+res.p_coin+'" id="gp_coin"><div>價格 :</div><div>'+res.p_coin+'</div></div>');
				$('#gp_coin').after('<div value="'+g_order_no+'" id="g_order_no"><div></div><div></div></div>');

			},
			
            error: function(){
            	alert("AJAX-class Error!")
            }

		})
		
		$('#GroupModal').modal('show');
	}

	//====================== 以上  輸入預約訂單===========================
	
	//====================== 以上  輸入預約訂單===========================
	
			//團課直購 事件
	function bookGroupDirect(){
		var g_class_no = $('#class_no').attr('value');
		var g_order_no = $('#g_order_no').attr('value');
		var rDate = $('#rDate').attr('value');
		var hrIndex = $('#hrIndex').attr('value');
		var p_coin = $('#gp_coin').attr('value');

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
			type : "POST",
			dataType: "text",
			data : {
				"action":"insert","mem_id":"${memVOLogin.mem_id}",
				"g_time_no":g_order_no,
				"g_class_no":g_class_no,
				"hr":hr,
				"rdate":rDate,
				"p_coin":p_coin
			},
		
			success:function(data){
				console.log("buyButton success", data);
				if('你還沒有商品結什麼帳?'.match('('+data+')')||'該團課已達人數上限!'.match('('+data+')')||'你已在此時段預定其他課程!'.match('('+data+')')
						||'你在此時段已有開課或者有被預約一對一課程哦!'.match('('+data+')')||'該團課已達人數上限!'.match('('+data+')')){
					Swal.fire({
						  icon: 'error',
						  title: '抱歉',
						  text: data+'!',
						  footer: ''
						})
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
							  title:'購買成功!',
							  icon:'success',
					}).then((value) => {
						var pros =document.getElementsByClassName('memidByPro');
						for(var i=0;i<pros.length;i++){
							var proMemId = pros[i];
							console.log(proMemId);
							$('.top .btns .today').click();
							webSocket.send(proMemId.value);
						}
						
						$('#cartBody').empty();
						$('.float-right.text-right').text('0');
<%-- 						var url = "<%=request.getContextPath()%>"+ "/front-end/groupclass/listOneOrder.jsp?g_order_no="+data; --%>
//	 					console.log(url);
// 						var orderView = $(location).attr('href', url);
// 						var timeoutID = window.setTimeout( ( () => orderView ), 5000);
					});
					
				}
			},
			error:function(data){
				console.log("buyButton error", data);
			}

		})	
	}
	
	
	//團課家入購物車事件
	function addCart(){
		var g_class_no = $('#class_no').attr('value');
		var g_order_no = $('#g_order_no').attr('value');
		var rDate = $('#rDate').attr('value');
		var hrIndex = $('#hrIndex').attr('value');
		var p_coin = $('#gp_coin').attr('value');

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
				type : "POST",
				dataType: "text",
				data : {
					"action":"orderDetailList","cart":"add","mem_id":"${memVOLogin.mem_id}",
					"g_time_no":g_order_no,
					"g_class_no":g_class_no,
					"hr":hr,
					"rdate":rDate,
					"p_coin":p_coin
				},
			
				success : function(data) {
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
					
					}else{
						Swal.fire({
							  icon: 'error',
							  title: '抱歉',
							  text: data+'!',
							  footer: ''
							})
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
