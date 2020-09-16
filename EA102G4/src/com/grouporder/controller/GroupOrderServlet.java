package com.grouporder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.godetail.model.*;
import com.google.zxing.WriterException;
import com.groupclass.model.GroupClassService;
import com.groupclass.model.GroupClassVO;
import com.grouphour.model.GroupHourService;
import com.grouphour.model.GroupHourVO;
import com.grouporder.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.memTimeV.model.MemTimeService;
import com.pro.model.ProService;
import com.pro.model.ProVO;
import com.tool.hong.CalendarForINGYM;
import com.tool.hong.MyThread;
import com.tool.hong.myExceptionForTime;
import com.trainerreservation.model.TrainerReservationService;

public class GroupOrderServlet extends HttpServlet {

	/**
	 * 
	 */
//	String pathForQRCode;
	private static final long serialVersionUID = 1L;
//	public void init() {
//		ServletContext context = getServletContext();
//		this.pathForQRCode=context.getContextPath()+"/front-end/groupclass/img/";
//	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		res.setCharacterEncoding("UTF-8");
		if ("getOneOrder_For_DisplayDetails".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String g_order_no = req.getParameter("g_order_no");

				if (g_order_no == null || g_order_no.trim().length() == 0)
					errorMsgs.add("訂單編號問題g_order_no= " + g_order_no);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				GroupOrderDetailService godSvc = new GroupOrderDetailService();
				List<GroupOrderDetailVO> detailList = godSvc.getAllByG_order_no(g_order_no);
				req.setAttribute("detailList", detailList);

				String url = "/front-end/groupclass/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("getOneException" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
			GroupOrderVO goVO = new GroupOrderVO();
			MemService memSvc =  new MemService();
			memVO= memSvc.getOneMem(memVO.getMem_id()); //保證session中的會員資料是最新的
			goVO.setMem_id(memVO.getMem_id());
			List<GroupOrderDetailVO> orderDetails = new ArrayList<GroupOrderDetailVO>();
			List<GroupOrderDetailVO> errorElements = new ArrayList<GroupOrderDetailVO>();
			GroupHourService ghSvc = new GroupHourService();
			GroupClassService gcSvc= new GroupClassService();
			String g_time_no = req.getParameter("g_time_no");
			GroupOrderDetailVO addGodVO = new GroupOrderDetailVO();
			GroupHourVO ghVO = ghSvc.getOneTimeNo(g_time_no);
			addGodVO.setG_class_no(ghVO.getG_class_no());
			addGodVO.setG_time_no(ghVO.getG_time_no());
			addGodVO.setHr(ghVO.getHr());
			addGodVO.setRdate(ghVO.getC_date());
			addGodVO.setP_coin(gcSvc.getOneGroupClass(ghVO.getG_class_no()).getP_coin());
			GroupOrderDetailService godSvc = new GroupOrderDetailService();
			GroupClassVO gcVO =gcSvc.getOneGroupClass(addGodVO.getG_class_no());
			boolean flag = false;
			try {
				
				errorElements = 
						orderDetails
						.stream()
						.filter(godVO -> 
								gcSvc.getOneGroupClass(godVO.getG_class_no()).getG_max() 
								<= godSvc.getDetailsByGTimeNo(godVO.getG_time_no(), 0).size())
						.collect(Collectors.toList());
				if (errorElements.size() != 0)
					throw new myExceptionForTime("該團課已達人數上限!");
				List<GroupOrderDetailVO> gTimeOrderList = godSvc.getDetailsByGTimeNo(addGodVO.getG_time_no(), 0);
				if (gcVO.getG_max() == gTimeOrderList.size()) throw new myExceptionForTime("該團課已達人數上限!");
//				for (int i = 0; i < orderDetails.size(); i++) {
//					GroupOrderDetailVO godVO = orderDetails.get(i);
//					flag = addGodVO.getG_time_no().equals(godVO.getG_time_no());
//					if (flag)  throw new myExceptionForTime("重複選取同一門團課時間");
//					flag = addGodVO.getRdate().equals(godVO.getRdate()) && addGodVO.getHr().equals(godVO.getHr());
//					if (flag)  throw new myExceptionForTime("欲購團課清單已有該時段");
//				}
				String mem_id = req.getParameter("mem_id");
				CalendarForINGYM tool = new CalendarForINGYM();
				MemTimeService mtSvc = new MemTimeService();
				HashSet<CalendarForINGYM> memTimeSet = tool.getCalendarSetForMTVO(mtSvc.getAllTimeForCheck(mem_id));
				CalendarForINGYM addTime = new CalendarForINGYM(addGodVO);
				for (CalendarForINGYM memTime : memTimeSet) {
					flag = memTime.equals(addTime);
					if (flag) {
						throw new myExceptionForTime("你已在此時段預定其他課程!");
					}
				}
				ProService proSvc =new ProService();
				ProVO proVO =  proSvc.getProByMem(mem_id);
				if (!(proVO == null)) {
					TrainerReservationService trSvc = new TrainerReservationService();
					HashSet<CalendarForINGYM> proTimeSet = tool
							.getCalendarSetTRVO(trSvc.getAllForRetainByPro_id(proVO.getPro_ID()));
					for (CalendarForINGYM proTime : proTimeSet) {
						flag = proTime.equals(addTime);
						if (flag)throw new myExceptionForTime("你在此時段已有開課或者有被預約一對一課程哦!");
					}
				}
				if (!flag)orderDetails.add(addGodVO);
				session.setAttribute("orderDetails", orderDetails);
//				RequestDispatcher view = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//				view.forward(req, res);
				goVO.setMem_id(memVO.getMem_id());
				int memCoin=memVO.getCoin();
				for(GroupOrderDetailVO godVO : orderDetails)
					memCoin-=godVO.getP_coin();
				if(memCoin<0) {
					PrintWriter writer = res.getWriter();
					writer.write("餘額不足");
					return;
				}
				GroupOrderService goSvc = new GroupOrderService();
				memVO.setCoin(memCoin);
				String g_order_no =  goSvc.insertWithDetail(goVO, orderDetails , memVO);
				session.setAttribute("memVOLogin", memVO); 
				orderDetails = null;
				session.removeAttribute("orderDetails");
				
				PrintWriter writer = res.getWriter();
				writer.write("success");
				System.out.println(178);
				return;
			} catch (myExceptionForTime eft) {
				System.out.println(eft.getMessage());
				errorMsgs.add(eft.getMessage());
//				RequestDispatcher view = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//				view.forward(req, res);
				PrintWriter writer = res.getWriter();
				writer.write(eft.getMessage());
				return;
			}
		}

		if ("orderDetailList".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<GroupOrderDetailVO> orderDetails = (List<GroupOrderDetailVO>) session.getAttribute("orderDetails");

		
			
			String cart = req.getParameter("cart");

			if ("delete".equals(cart)) {
				String g_time_no = req.getParameter("g_time_no");
				System.out.println(g_time_no  +"          203 godController");
				orderDetails.removeIf((GroupOrderDetailVO godVO) -> godVO.getG_time_no().equals(g_time_no));
				PrintWriter writer = res.getWriter();
				writer.write("success");
				return;
			} else if ("add".equals(cart)) {
				String g_class_no = req.getParameter("g_class_no");
				GroupClassService gcSvc = new GroupClassService();
				GroupClassVO gcVO = gcSvc.getOneGroupClass(g_class_no);
				req.setAttribute("gcVO", gcVO);
				boolean flag = false;
				GroupOrderDetailVO addGodVO = new GroupOrderDetailVO();
				// (G_ORDER_NO, G_CLASS_NO,G_TIME_NO, RDATE, HR, P_COIN)
				addGodVO.setG_class_no(g_class_no);
				addGodVO.setG_time_no(req.getParameter("g_time_no"));
				addGodVO.setHr(req.getParameter("hr"));
				addGodVO.setP_coin(new Integer(req.getParameter("p_coin")));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date utilDate = null;
				GroupOrderDetailService godSvc = new GroupOrderDetailService();
				try {
					utilDate = sdf.parse(req.getParameter("rdate"));
				} catch (ParseException e) {
					throw new myExceptionForTime("我的錯啦");
				}
				java.sql.Date rdate = new java.sql.Date(utilDate.getTime());
				addGodVO.setRdate(rdate);
				try {
					if (orderDetails == null || orderDetails.size() == 0)
						orderDetails = new ArrayList<GroupOrderDetailVO>();
					List<GroupOrderDetailVO> gTimeOrderList = godSvc.getDetailsByGTimeNo(addGodVO.getG_time_no(), 0);
					if (gcVO.getG_max() == gTimeOrderList.size()) throw new myExceptionForTime("該團課已達人數上限!");
					for (int i = 0; i < orderDetails.size(); i++) {
						GroupOrderDetailVO godVO = orderDetails.get(i);
						flag = addGodVO.getG_time_no().equals(godVO.getG_time_no());
						if (flag)  throw new myExceptionForTime("重複選取同一門團課時間");
						flag = addGodVO.getRdate().equals(godVO.getRdate()) && addGodVO.getHr().equals(godVO.getHr());
						if (flag)  throw new myExceptionForTime("欲購團課清單已有該時段");
					}
					String mem_id = req.getParameter("mem_id");
					CalendarForINGYM tool = new CalendarForINGYM();
					MemTimeService mtSvc = new MemTimeService();
					HashSet<CalendarForINGYM> memTimeSet = tool.getCalendarSetForMTVO(mtSvc.getAllTimeForCheck(mem_id));
					CalendarForINGYM addTime = new CalendarForINGYM(addGodVO);
					for (CalendarForINGYM memTime : memTimeSet) {
						flag = memTime.equals(addTime);
						if (flag) {
							throw new myExceptionForTime("你已在此時段預定其他課程!");
						}
					}
					ProService proSvc =new ProService();
					ProVO proVO =  proSvc.getProByMem(mem_id);
//					String pro_id = proVO.getPro_ID();
//					System.out.println(pro_id);
					if (proVO != null ) {
						String pro_id = proVO.getPro_ID();
						TrainerReservationService trSvc = new TrainerReservationService();
						HashSet<CalendarForINGYM> proTimeSet = tool
								.getCalendarSetTRVO(trSvc.getAllForRetainByPro_id(pro_id));
						for (CalendarForINGYM proTime : proTimeSet) {
							flag = proTime.equals(addTime);
							if (flag)throw new myExceptionForTime("你在此時段已有開課或者有被預約一對一課程哦!");
						}
					}
					if (!flag)orderDetails.add(addGodVO);
					session.setAttribute("orderDetails", orderDetails);
//					RequestDispatcher view = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//					view.forward(req, res);
						
					PrintWriter writer = res.getWriter();
					writer.write("success");
					return;
				} catch (myExceptionForTime eft) {
					System.out.println(eft.getMessage());
					errorMsgs.add(eft.getMessage());
//					RequestDispatcher view = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//					view.forward(req, res);
					PrintWriter writer = res.getWriter();
					writer.write(eft.getMessage());
					return;
				}
			}
//			System.out.println("執行錯誤區塊");
//			assert false;
		}
		if ("insertForOrderDetails".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			List<GroupOrderDetailVO> errorElements = new ArrayList<GroupOrderDetailVO>();
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("errorElements", errorElements);
			
			HttpSession session = req.getSession();
			GroupOrderDetailService godSvc = new GroupOrderDetailService();
			GroupOrderService goSvc = new GroupOrderService();
			GroupClassService gcSvc = new GroupClassService();
			try {
				@SuppressWarnings("unchecked")
				List<GroupOrderDetailVO> orderDetails = (List<GroupOrderDetailVO>) session.getAttribute("orderDetails");
				if (orderDetails == null || orderDetails.size() == 0)
					throw new myExceptionForTime("你還沒有商品結什麼帳?");
				
				errorElements = 
						orderDetails
						.stream()
						.filter(godVO -> 
								gcSvc.getOneGroupClass(godVO.getG_class_no()).getG_max() 
								<= godSvc.getDetailsByGTimeNo(godVO.getG_time_no(), 0).size())
						.collect(Collectors.toList());
				if (errorElements.size() != 0)
					throw new myExceptionForTime("該團課已達人數上限!");
				
				GroupOrderVO goVO = new GroupOrderVO();
				MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
				MemService memSvc =  new MemService();
				memVO= memSvc.getOneMem(memVO.getMem_id()); //保證session中的會員資料是最新的
				goVO.setMem_id(memVO.getMem_id());
				int memCoin=memVO.getCoin();
				for(GroupOrderDetailVO godVO : orderDetails)
					memCoin-=godVO.getP_coin();
				if(memCoin<0) {
					PrintWriter writer = res.getWriter();
					writer.write("餘額不足");
					return;
				}
				//開始進行新增與點數修改
				memVO.setCoin(memCoin);
				String g_order_no =  goSvc.insertWithDetail(goVO, orderDetails , memVO);
				session.setAttribute("memVOLogin", memVO); 
				String path = getServletContext().getRealPath("/front-end/groupclass/img");
				new MyThread(orderDetails,memVO,path,req.getRequestURL()).start();
				
//				MailService mailSvc = new MailService();
				
//						System.out.println("123");
//					try {
//					List<GroupOrderDetailVO> carts = (List<GroupOrderDetailVO>) session.getAttribute("orderDetails");
//					MemVO mailToMemVO = (MemVO)session.getAttribute("memVOLogin");
//					for(GroupOrderDetailVO godVO :carts) {
//						StringBuffer str = req.getRequestURL();
//						str.append("?g_time_no="+godVO.getG_time_no()+"&g_order_no"+godVO.getG_order_no()+"&mem_id"+mailToMemVO.getMem_id());
//						String mailImg = QRCodeForINGYM.parameterQRcode(str , path, godVO.getG_time_no());
//						mailSvc.sendMail(mailToMemVO.getMem_email(), mailToMemVO.getMem_name(), godVO.getRdate()+"   "+godVO.getHr(), mailImg);
//						} 
//					}catch (IOException | WriterException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//					}
				System.out.println("123");
				orderDetails = null;
				session.removeAttribute("orderDetails");
				
				
				PrintWriter writer = res.getWriter();
				writer.write("success");
				return;
			} catch (myExceptionForTime eft) {
				errorMsgs.add(eft.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//				failureView.forward(req, res);
				PrintWriter writer = res.getWriter();
				System.out.println(eft.getMessage());
				writer.write(eft.getMessage());
				return;
			} 
		}

		
		if ("updateFor_Status".equals(action)) {
			GroupOrderDetailService godSvc = new GroupOrderDetailService();
			GroupOrderDetailVO godVO = godSvc.findByPrimaryKey(req.getParameter("g_order_no"), req.getParameter("g_time_no"));
			GroupHourService ghSvc = new GroupHourService();
			GroupHourVO ghVO= ghSvc.getOneTimeNo(godVO.getG_time_no());

			res.setContentType("text/html; charset=utf-8");
		    res.setCharacterEncoding("utf-8");
		    String gostatus = null;
			if(godVO.getGo_status()==1) {
				gostatus = "你已經簽到囉";
				req.setAttribute("gostatus", gostatus);
				RequestDispatcher view = req
						.getRequestDispatcher("/front-end/groupclass/checkin.jsp");
				view.forward(req, res);
				return;// 程式中斷
			}
			String cdate = ghVO.getC_date().toString();
			int time = ghVO.getHr().indexOf("1");
//			String timeString=null;
//
//			if(time<10) {
//				timeString="0"+time+":00";
//			}else {
//				timeString=time+":00";
//			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date gtDate =null;
			try {
				gtDate = sdf.parse(cdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long now = new java.util.Date().getTime();
			
			long gt = gtDate.getTime()+time*3600000;
			System.out.println(now-gt);
			if(gt-now>1800000*2) {
				gostatus = "太早簽到囉";
				req.setAttribute("gostatus", gostatus);
				RequestDispatcher view = req
						.getRequestDispatcher("/front-end/groupclass/checkin.jsp");
				view.forward(req, res);
				return;// 程式中斷
			}
				
			godVO.setGo_status(1);
			godSvc.update(godVO);
			gostatus = "簽到成功";
			req.setAttribute("gostatus", gostatus);
			RequestDispatcher view = req
					.getRequestDispatcher("/front-end/groupclass/checkin.jsp");
			view.forward(req, res);
			return;
		}
		if ("refund".equals(action)) {
			GroupOrderDetailService godSvc = new GroupOrderDetailService();
			HttpSession session =req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVOLogin");
			MemService memSvc = new MemService();
			memVO = memSvc.getOneMem(memVO.getMem_id());//確保最新資料
			Integer memCoin = memVO.getCoin();
			String g_order_no = req.getParameter("g_order_no");
			String hr = req.getParameter("hr");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			List<GroupOrderDetailVO> list = godSvc.getDetailsByOrder(req.getParameter("g_order_no"), 0);
			
			try {
				 final java.sql.Date rDate = new java.sql.Date(sdf.parse(req.getParameter("rDate")).getTime());
				 System.out.println(hr+"      "+rDate + "          ");
				
				 if(list.size()==0) {
						PrintWriter writer=  res.getWriter();
						writer.write("已完課不可取消預約");
						return;
					}else if(list.stream().noneMatch(godVO -> godVO.getHr().equals(hr) && godVO.getRdate().equals(rDate))){
						list.stream().peek(godVO -> System.out.println(godVO.getG_time_no()+"    "+godVO.getGo_status()));
						PrintWriter writer=  res.getWriter();
						writer.write("?");
						return;
					}
				 
				Optional<GroupOrderDetailVO> godVOOptional =list.stream()
						.filter(godVO -> godVO.getHr().equals(hr) && godVO.getRdate().equals(rDate))
						.findFirst();
				GroupOrderDetailVO resultVO = null;
				
				if(godVOOptional.isPresent()) {
					resultVO = godVOOptional.get();
					resultVO.setGo_status(2);
					godSvc.update(resultVO);
					memCoin+=resultVO.getP_coin();
					memVO.setCoin(memCoin);
					session.setAttribute("memVOLogin", memVO);
					PrintWriter writer=  res.getWriter();
					writer.write("suc");
				}else {
					PrintWriter writer=  res.getWriter();
					writer.write("failed(notError)");
				}
//			GroupOrderDetailVO godVO = godSvc.findByPrimaryKey(req.getParameter("g_order_no"), req.getParameter("g_time_no"));
				return;
			}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}

}