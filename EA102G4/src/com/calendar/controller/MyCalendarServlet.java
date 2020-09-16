package com.calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.IClassOrder.model.IClassOrderService;
import com.IClassOrder.model.IClassOrderVO;
import com.classType.model.ClassTypeService;
import com.classType.model.ClassTypeVO;
import com.dayoff.model.DayoffService;
import com.dayoff.model.DayoffVO;
import com.godetail.model.GroupOrderDetailService;
import com.godetail.model.GroupOrderDetailVO;
import com.groupclass.model.GroupClassService;
import com.groupclass.model.GroupClassVO;
import com.grouporder.model.GroupOrderService;
import com.grouporder.model.GroupOrderVO;
import com.individualClass.model.IndividualClassService;
import com.individualClass.model.IndividualClassVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.memTimeV.model.MemTimeService;
import com.memTimeV.model.MemTimeVO;
import com.pro.model.ProService;
import com.pro.model.ProVO;
import com.tool.hong.myExceptionForTime;
import com.trainerPublicV.model.*;

public class MyCalendarServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		// MyCalendar.jsp
		// calendar_IndClass.jsp
		// calendar_group_class.jsp
		// display trainer public time table
		if ("Display_TrainerPublic".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pro_ID = req.getParameter("pro_ID");

				// get trainerPublic reserved hours
				TrainerPublicService tpSvc = new TrainerPublicService();
				List<TrainerPublicVO> ptList = tpSvc.getAllTimeForShow(pro_ID);

				GroupClassService gcSvc = new GroupClassService();

				GroupOrderDetailService godSvc = new GroupOrderDetailService();
				GroupOrderDetailVO godVO = new GroupOrderDetailVO();

				JSONArray arr = new JSONArray();

				for (TrainerPublicVO tpVO : ptList) {
					CalendarVO calVO = new CalendarVO();
					JSONObject obj = new JSONObject();

					// get className by class_no;
					if (tpVO.getI_class_no().charAt(0) == 'I') {
						// get ind order status (if status<0 will not show in calendar)
						IClassOrderService icoSvc = new IClassOrderService();
						IClassOrderVO icVO = icoSvc.getOneIClassOrderVO(tpVO.getI_order_no());
						calVO.setIo_status(icVO.getIo_status()); // Order Status
						calVO.setClass_name("");

					} else if (tpVO.getI_class_no().charAt(0) == 'G') {
						// get group class CLASS status (if status is 0 won't show on calendar)
						GroupClassVO gcVO = gcSvc.getOneGroupClass(tpVO.getI_class_no());
						calVO.setIo_status(gcVO.getC_status()); // Class Status

						// get number of order of group class
						int quantity = godSvc.getDetailsByGTimeNo(tpVO.getI_order_no(), 0).size();

						calVO.setClass_name(gcVO.getG_name());
						calVO.setG_max(gcVO.getG_max());
						calVO.setQuantity(quantity);

						obj.put("g_max", calVO.getG_max());
						obj.put("quantity", calVO.getQuantity());

					} else if (tpVO.getI_class_no().charAt(0) == '-') {
						calVO.setClass_name("休息日");
					}

					calVO.setClass_no(tpVO.getI_class_no());
					calVO.setOrder_no(tpVO.getI_order_no());
					calVO.setPro_ID(tpVO.getPro_ID());
					calVO.setrDate(tpVO.getrDate());
					calVO.setHr(tpVO.getHr());

					obj.put("class_name", calVO.getClass_name());
					obj.put("order_no", calVO.getOrder_no());
					obj.put("class_no", calVO.getClass_no());
					obj.put("pro_ID", calVO.getPro_ID());
					obj.put("rDate", calVO.getrDate());
					obj.put("hr", calVO.getHr());
					obj.put("status", calVO.getIo_status());

					arr.put(obj);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(arr.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());

			}

		}

		// abandon?
		// display hr data from trainer public
		if ("Display_Hr".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String order_no = req.getParameter("order_no");

				// get trainerPublic reserved hours
				TrainerPublicService tpSvc = new TrainerPublicService();
				TrainerPublicVO tpVO = tpSvc.getOneDay(order_no);
				CalendarVO calVO = new CalendarVO();

				IndividualClassService icSvc = new IndividualClassService();
				GroupClassService gcSvc = new GroupClassService();

				// get className by class_no;
				if (tpVO.getI_class_no().charAt(0) == 'I') {
					calVO.setClass_name(icSvc.getOneIndividualClass(tpVO.getI_class_no()).getC_name());
				} else if (tpVO.getI_class_no().charAt(0) == 'G') {
					calVO.setClass_name(gcSvc.getOneGroupClass(tpVO.getI_class_no()).getG_name());
				} else if (tpVO.getI_class_no().charAt(0) == '-') {
					calVO.setClass_name("休息日");
				}

				calVO.setClass_no(tpVO.getI_class_no());
				calVO.setOrder_no(tpVO.getI_order_no());
				calVO.setPro_ID(tpVO.getPro_ID());
				calVO.setrDate(tpVO.getrDate());
				calVO.setHr(tpVO.getHr());

				JSONObject obj = new JSONObject();
				obj.put("class_name", calVO.getClass_name());
				obj.put("order_no", calVO.getOrder_no());
				obj.put("class_no", calVO.getClass_no());
				obj.put("pro_ID", calVO.getPro_ID());
				obj.put("rDate", calVO.getrDate());
				obj.put("hr", calVO.getHr());

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());

			}

		}
		//// MyCalendar.jsp
		// calendar_IndClass.jsp
		// calendar_group_class.jsp
		// ===== for one day data from trainer public
		// click on dats and refresh the 24hr section
		if ("Display_OneDay".equals(action)) {
			try {
				// get value
				String pro_ID = req.getParameter("pro_ID");
				String date = req.getParameter("rDate").trim();
				java.sql.Date rDate = java.sql.Date.valueOf(date);

				// get trainerPublic reserved hours
				TrainerPublicService tpSvc = new TrainerPublicService();
				List<TrainerPublicVO> ptList = tpSvc.getAllDay(rDate, pro_ID);

				JSONArray arr = new JSONArray();
				for (TrainerPublicVO tpVO : ptList) {
					CalendarVO calVO = new CalendarVO();
					JSONObject obj = new JSONObject();

					// get className by class_no;
					if (tpVO.getI_class_no().charAt(0) == 'I') {
						IndividualClassService icSvc = new IndividualClassService();
						calVO.setClass_name(icSvc.getOneIndividualClass(tpVO.getI_class_no()).getC_name());

						// get ind order status (if status<0 will not show in calendar)
						IClassOrderService icoSvc = new IClassOrderService();
						IClassOrderVO icVO = icoSvc.getOneIClassOrderVO(tpVO.getI_order_no());
						calVO.setIo_status(icVO.getIo_status()); // Order Status

					} else if (tpVO.getI_class_no().charAt(0) == 'G') {
						GroupClassService gcSvc = new GroupClassService();
						calVO.setClass_name(gcSvc.getOneGroupClass(tpVO.getI_class_no()).getG_name());

						// get group class CLASS status (if status is 0 won't show on calendar)
						GroupClassVO gcVO = gcSvc.getOneGroupClass(tpVO.getI_class_no());
						calVO.setIo_status(gcVO.getC_status()); // Class Status

					} else if (tpVO.getI_class_no().charAt(0) == '-') {
						calVO.setClass_name("休息日");
					}

					calVO.setClass_no(tpVO.getI_class_no());
					calVO.setOrder_no(tpVO.getI_order_no());
					calVO.setPro_ID(tpVO.getPro_ID());
					calVO.setrDate(tpVO.getrDate());
					calVO.setHr(tpVO.getHr());

					obj.put("class_name", calVO.getClass_name());
					obj.put("order_no", calVO.getOrder_no());
					obj.put("class_no", calVO.getClass_no());
					obj.put("pro_ID", calVO.getPro_ID());
					obj.put("rDate", calVO.getrDate());
					obj.put("hr", calVO.getHr());
					obj.put("status", calVO.getIo_status());
					arr.put(obj);

				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(arr.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());
			}

		}
		// MyCalendar.jsp
		// calendar_IndClass.jsp
		// 私人課程 新增訂單
		if ("Insert_New_Order".equals(action)) {
			try {
				String mem_ID = req.getParameter("mem_ID").trim();
				String i_class_no = req.getParameter("i_class_no").trim();
				Integer p_coin = new Integer(req.getParameter("p_coin").trim());
				Date rDate = Date.valueOf(req.getParameter("rDate").trim());
				String pro_ID = req.getParameter("pro_ID").trim();

				// transfer hrIndex into binary time
				Integer hrIndex = new Integer(req.getParameter("hrIndex").trim());
				StringBuilder strHr = new StringBuilder("000000000000000000000000");
				strHr.setCharAt(hrIndex, '1');
				String hr = new String(strHr);

				IClassOrderService icSvc = new IClassOrderService();
				IClassOrderVO icVO = new IClassOrderVO();

				// checking if the time is available
				MemTimeService mTSvc = new MemTimeService();
				List<MemTimeVO> getAllTimeForShow = mTSvc.getAllTimeForShow(mem_ID);

				MemService memSvc = new MemService();
				MemVO memVO = null;

				JSONObject obj = new JSONObject();
				for (MemTimeVO mtVO : getAllTimeForShow) {
					if (rDate.equals(mtVO.getrDate())) {
						if (hr.equals(mtVO.getHr()) && mtVO.getIo_status() <= 1) {
							obj.put("msg", "這個時段你有約課了喔");
						}
					}
				}

				// if member has enough coin
				memVO = new MemVO();
				memVO = memSvc.getOneMem(mem_ID);
				if (memVO.getCoin() < p_coin) {
					obj.put("msg", "你沒錢了");
				}

				if (obj.length() == 0) {
					icVO.setMem_ID(mem_ID);
					icVO.setI_class_no(i_class_no);
					icVO.setP_coin(p_coin);
					icVO.setRDate(rDate);
					icVO.setHr(hr);

					memVO.setMem_id(mem_ID);
					memVO.setCoin(memVO.getCoin() - p_coin);
					icSvc.insert2(icVO, memVO);

					// get pro data for adding payment
					ProService proSvc = new ProService();
					ProVO proVO = proSvc.getOnePro(pro_ID);
					MemVO memVO2 = memSvc.getOneMem(proVO.getMem_ID());

					memVO2.setCoin(memVO2.getCoin() + p_coin);
					memSvc.updateMem(memVO2.getMem_name(), memVO2.getMem_psw(), memVO2.getMem_bir(), memVO2.getSex(),
							memVO2.getMem_addr(), memVO2.getMem_email(), memVO2.getMem_phone(), memVO2.getMem_absent(),
							memVO2.getCoin(), memVO2.getMem_resume(), memVO2.getM_reg_date(), memVO2.getSel_auth(),
							memVO2.getArt_auth(), memVO2.getCom_auth(), memVO2.getMem_id());

					obj.put("msg", "預約成功!");
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());

			}

		}
		// MyCalendar.jsp
		// calendar_group_class.jsp =======display Group Class data in modal
		if ("getGroup_For_Display".equals(action)) { // get groupClass info for hr click
			JSONArray arr = new JSONArray();
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String g_class_no = req.getParameter("g_class_no");
				String g_order_no = req.getParameter("g_order_no");

				GroupClassService gcSvc = new GroupClassService();
				GroupClassVO gcVO = gcSvc.getOneGroupClass(g_class_no);

				ClassTypeService ctSvc = new ClassTypeService();
				ClassTypeVO ctVO = ctSvc.getOneClassType(gcVO.getC_type_no());

				GroupOrderDetailService godSvc = new GroupOrderDetailService();

				JSONObject obj = new JSONObject();
				int quantity = godSvc.getDetailsByGTimeNo(g_order_no, 0).size();
				MemService memSvc=new MemService();
				ProService proSvc=new ProService();
				obj.put("pro_name", memSvc.getOneMem(proSvc.getOnePro(gcVO.getPro_id()).getMem_ID()).getMem_name());
				obj.put("G_name", gcVO.getG_name());
				obj.put("class_no", gcVO.getG_class_no());
				obj.put("p_coin", gcVO.getP_coin());
				obj.put("c_type_no", ctVO.getT_name());
				obj.put("loc", gcVO.getLoc());
				obj.put("g_max", gcVO.getG_max());
				obj.put("g_detail", gcVO.getG_detail());
				obj.put("c_status", gcVO.getC_status());
				obj.put("quantity", quantity);

				res.setContentType("plain/text");
				res.setCharacterEncoding("utf-8");
				PrintWriter w = res.getWriter();
				w.write(obj.toString());
				w.flush();
				w.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		// calendar_mem.jsp
		// display member time table, mem's booked schedule
		if ("Display_Calendar_mem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String mem_id = req.getParameter("mem_id");

				// get trainerPublic reservated hours
				MemTimeService mtSvc = new MemTimeService();
				List<MemTimeVO> mtList = mtSvc.getAllTimeForShow(mem_id);

				IndividualClassService icSvc = new IndividualClassService();
				GroupClassService gcSvc = new GroupClassService();

				JSONArray arr = new JSONArray();
				for (MemTimeVO mtVO : mtList) {
					CalendarVO calVO = new CalendarVO();
					JSONObject obj = new JSONObject();

					// get className by class_no;
					if (mtVO.getI_class_no().charAt(0) == 'I') {
						IndividualClassVO icVO = icSvc.getOneIndividualClass(mtVO.getI_class_no());
						calVO.setClass_name(icVO.getC_name());
					} else if (mtVO.getI_class_no().charAt(0) == 'G') {
						GroupClassVO gcVO = gcSvc.getOneGroupClass(mtVO.getI_class_no());
						calVO.setClass_name(gcVO.getG_name());
					}

					calVO.setClass_no(mtVO.getI_class_no());
					calVO.setOrder_no(mtVO.getI_order_no());
					calVO.setMem_ID(mtVO.getMem_ID());
					calVO.setrDate(mtVO.getrDate());
					calVO.setHr(mtVO.getHr());
					calVO.setP_coin(mtVO.getP_coin());
					calVO.setIo_status(mtVO.getIo_status());

					obj.put("class_name", calVO.getClass_name());
					obj.put("order_no", calVO.getOrder_no());
					obj.put("class_no", calVO.getClass_no());
					obj.put("mem_ID", calVO.getMem_ID());
					obj.put("rDate", calVO.getrDate());
					obj.put("hr", calVO.getHr());
					obj.put("p_coin", calVO.getP_coin());
					obj.put("status", calVO.getIo_status()); // MEM_TIME has status, doesn't need to join

					arr.put(obj);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(arr.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());

			}

		}

		// calendar_mem.jsp=======display one day data for mem
		if ("Display_Mem_OneDay".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String mem_id = req.getParameter("mem_id");
				String rdate = req.getParameter("rDate").trim();
				java.sql.Date rDate = java.sql.Date.valueOf(rdate);

				// get mem_time reserved hours
				MemTimeService mtSvc = new MemTimeService();
				List<MemTimeVO> mtList = mtSvc.getAllTimeByDate(mem_id, rDate);

				IndividualClassService icSvc = new IndividualClassService();
				GroupClassService gcSvc = new GroupClassService();

				JSONArray arr = new JSONArray();
				for (MemTimeVO mtVO : mtList) {
					CalendarVO calVO = new CalendarVO();

					// get className by class_no;
					if (mtVO.getI_class_no().charAt(0) == 'I') {
						calVO.setClass_name(icSvc.getOneIndividualClass(mtVO.getI_class_no()).getC_name());
					} else if (mtVO.getI_class_no().charAt(0) == 'G') {
						calVO.setClass_name(gcSvc.getOneGroupClass(mtVO.getI_class_no()).getG_name());
					}

					calVO.setIo_status(mtVO.getIo_status());
					calVO.setClass_no(mtVO.getI_class_no());
					calVO.setOrder_no(mtVO.getI_order_no());
					calVO.setMem_ID(mtVO.getMem_ID());
					calVO.setrDate(mtVO.getrDate());
					calVO.setHr(mtVO.getHr());

					JSONObject obj = new JSONObject();
					obj.put("class_name", calVO.getClass_name());
					obj.put("order_no", calVO.getOrder_no());
					obj.put("class_no", calVO.getClass_no());
					obj.put("pro_ID", calVO.getPro_ID());
					obj.put("rDate", calVO.getrDate());
					obj.put("hr", calVO.getHr());
					obj.put("status", calVO.getIo_status());
					arr.put(obj);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(arr.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());
			}

		}

		// calendar_mem.jsp =========== 會員頁面取得訂單資訊
		if ("getMemBooked".equals(action)) {
			try {
//				String mem_id = req.getParameter("mem_id");
				String class_no = req.getParameter("class_no").trim();
				String order_no = req.getParameter("order_no").trim();

				JSONObject obj = new JSONObject();

				ClassTypeService ctSvc = new ClassTypeService();
				ProService proSvc = new ProService();
				MemService memSvc = new MemService();

				// identify which model need to be called;
				if (class_no.charAt(0) == 'I') {
					IndividualClassService icSvc = new IndividualClassService();
					IndividualClassVO classVO = icSvc.getOneIndividualClass(class_no);
					IClassOrderService ioSvc = new IClassOrderService();

					obj.put("class_name", classVO.getC_name());
					obj.put("t_name", ctSvc.getOneClassType(classVO.getC_type_no()).getT_name());
					obj.put("pro_name",
							memSvc.getOneMem(proSvc.getOnePro(classVO.getPro_ID()).getMem_ID()).getMem_name());
					obj.put("pro_ID", classVO.getPro_ID());
					obj.put("loc", classVO.getLoc());
					obj.put("p_coin", classVO.getP_coin());
					obj.put("status", ioSvc.getOneIClassOrderVO(order_no).getIo_status());

				} else {
					GroupClassService gcSvc = new GroupClassService();
					GroupClassVO classVO = gcSvc.getOneGroupClass(class_no);
					GroupOrderDetailService goSvc = new GroupOrderDetailService();
					obj.put("class_name", classVO.getG_name());
					obj.put("pro_ID", classVO.getPro_id());
					obj.put("t_name", ctSvc.getOneClassType(classVO.getC_type_no()).getT_name());
					obj.put("pro_name",
							memSvc.getOneMem(proSvc.getOnePro(classVO.getPro_id()).getMem_ID()).getMem_name());
					obj.put("pro_ID", classVO.getPro_id());
					obj.put("loc", classVO.getLoc());
					obj.put("p_coin", classVO.getP_coin());
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());
			}

		}

		// calendar_mem.jsp ====== cancel reservation 會員取消預約 退費
		if ("update_Booking".equals(action)) {

			// getParameter
			String mem_id = null;
			mem_id = req.getParameter("mem_ID");
			if (mem_id == null) { // if i can't get mem_id from jsp, get from session
				MemVO memVO = (MemVO) session.getAttribute("memVOLogin");
				mem_id = memVO.getMem_id();
			}

			String pro_ID = req.getParameter("pro_ID");
			if (pro_ID == null) {
				ProVO proVO = (ProVO) session.getAttribute("ProVOLogin");
				pro_ID = proVO.getPro_ID();
			}

			String order_no = req.getParameter("i_order_no");
			String class_no = req.getParameter("i_class_no");
			java.sql.Date rDate = java.sql.Date.valueOf(req.getParameter("rDate"));
			Integer p_coin = new Integer(req.getParameter("p_coin"));
			Integer hrIndex = new Integer(req.getParameter("hrIndex").trim());
			// transfer hrIndex into binary time;
			StringBuilder strHr = new StringBuilder("000000000000000000000000");
			strHr.setCharAt(hrIndex, '1');
			String hr = new String(strHr);

			System.out.println(mem_id);
			System.out.println(pro_ID);
			System.out.println(order_no);
			System.out.println(class_no);

			// get VO for update
			IClassOrderVO icoVO = new IClassOrderVO();
			icoVO.setI_class_no(class_no);
			icoVO.setI_order_no(order_no);
			icoVO.setMem_ID(mem_id);
			icoVO.setRDate(rDate);
			icoVO.setHr(hr);
			icoVO.setP_coin(p_coin);
			icoVO.setIo_status(2);

			// update class order status
			IClassOrderService icoSvc = new IClassOrderService();
			icoSvc.updateIClassVO(icoVO);

			// member refund
			MemService memSvc = new MemService();
			// member get back money
			MemVO memVO1 = memSvc.getOneMem(mem_id);
			memVO1.setCoin(memVO1.getCoin() + p_coin);
			memSvc.updateMem(memVO1.getMem_name(), memVO1.getMem_psw(), memVO1.getMem_bir(), memVO1.getSex(),
					memVO1.getMem_addr(), memVO1.getMem_email(), memVO1.getMem_phone(), memVO1.getMem_absent(),
					memVO1.getCoin(), memVO1.getMem_resume(), memVO1.getM_reg_date(), memVO1.getSel_auth(),
					memVO1.getArt_auth(), memVO1.getCom_auth(), memVO1.getMem_id());

			// pro subtract refund
			ProService proSvc = new ProService();
			ProVO proVO = proSvc.getOnePro(pro_ID);
			MemVO memVO2 = memSvc.getOneMem(proVO.getMem_ID());
			memVO2.setCoin(memVO2.getCoin() - p_coin);
			memSvc.updateMem(memVO2.getMem_name(), memVO2.getMem_psw(), memVO2.getMem_bir(), memVO2.getSex(),
					memVO2.getMem_addr(), memVO2.getMem_email(), memVO2.getMem_phone(), memVO2.getMem_absent(),
					memVO2.getCoin(), memVO2.getMem_resume(), memVO2.getM_reg_date(), memVO2.getSel_auth(),
					memVO2.getArt_auth(), memVO2.getCom_auth(), memVO2.getMem_id());

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write("預約已取消!");
			out.flush();
			out.close();
		}

		// display trainer public time table
		if ("Display_proBackEnd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pro_ID = req.getParameter("pro_ID");

				// get trainerPublic reservated hours
				TrainerPublicService tpSvc = new TrainerPublicService();
				List<TrainerPublicVO> ptList = tpSvc.getAllTimeForShow(pro_ID);
				JSONArray arr = new JSONArray();

				for (TrainerPublicVO tpVO : ptList) {
					CalendarVO calVO = new CalendarVO();
					JSONObject obj = new JSONObject();

					// get className by class_no;
					if (tpVO.getI_class_no().charAt(0) == 'I') {
						// get class Name
						IndividualClassService icSvc = new IndividualClassService();
						IndividualClassVO icVO = new IndividualClassVO();
						icVO = icSvc.getOneIndividualClass(tpVO.getI_class_no());
						calVO.setClass_name(icVO.getC_name());

						// get order status
						IClassOrderService icoSvc = new IClassOrderService();
						IClassOrderVO icoVO = icoSvc.getOneIClassOrderVO(tpVO.getI_order_no());
						calVO.setIo_status(icoVO.getIo_status());
						obj.put("status", calVO.getIo_status());

					} else if (tpVO.getI_class_no().charAt(0) == 'G') {
						GroupClassService gcSvc = new GroupClassService();
						GroupClassVO gcVO = gcSvc.getOneGroupClass(tpVO.getI_class_no());

						GroupOrderDetailService godSvc = new GroupOrderDetailService();
						int quantity = godSvc.getDetailsByGTimeNo(tpVO.getI_order_no(), 0).size();

						calVO.setIo_status(gcVO.getC_status()); // Class Status
						obj.put("status", calVO.getIo_status());

						calVO.setClass_name(gcVO.getG_name());
						calVO.setG_max(gcVO.getG_max());
						calVO.setQuantity(quantity);

						obj.put("g_max", calVO.getG_max());
						obj.put("quantity", calVO.getQuantity());

					} else if (tpVO.getI_class_no().charAt(0) == '-') {
						calVO.setClass_name("休息日");
					}

					calVO.setClass_no(tpVO.getI_class_no());
					calVO.setOrder_no(tpVO.getI_order_no());
					calVO.setPro_ID(tpVO.getPro_ID());
					calVO.setrDate(tpVO.getrDate());
					calVO.setHr(tpVO.getHr());

					obj.put("class_name", calVO.getClass_name());
					obj.put("order_no", calVO.getOrder_no());
					obj.put("class_no", calVO.getClass_no());
					obj.put("pro_ID", calVO.getPro_ID());
					obj.put("rDate", calVO.getrDate());
					obj.put("hr", calVO.getHr());
					arr.put(obj);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(arr.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				System.out.println("無法取得要修改的資料:" + e.getMessage());

			}

		}

		// pro back-end
		if ("getOneOrder".equals(action)) { // 來自select_page.jsp的請求
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = req.getParameter("order_no");

				IClassOrderService icSvc = new IClassOrderService();
				IClassOrderVO icVO = icSvc.getOneIClassOrderVO(order_no);

				IndividualClassService iSvc = new IndividualClassService();
				IndividualClassVO iVO = iSvc.getOneIndividualClass(icVO.getI_class_no());

				MemService memSvc = new MemService();
				String name = memSvc.getOneMem(icVO.getMem_ID()).getMem_name();

				// get Type Name
				ClassTypeService ctSvc = new ClassTypeService();
				ClassTypeVO ctVO = ctSvc.getOneClassType(iVO.getC_type_no());

				JSONObject obj = new JSONObject();
				obj.put("mem_name", name);
				obj.put("mem_ID", icVO.getMem_ID());
				obj.put("class_no", icVO.getI_class_no());
				obj.put("class_name", iVO.getC_name());
				obj.put("loc", iVO.getLoc());
				obj.put("t_name", ctVO.getT_name());
				obj.put("order_no", icVO.getI_order_no());
				obj.put("rDate", icVO.getRDate());
				obj.put("hr", icVO.getHr());
				obj.put("p_coin", icVO.getP_coin());

				res.setContentType("plain/text");
				res.setCharacterEncoding("utf-8");
				PrintWriter w = res.getWriter();
				w.write(obj.toString());
				w.flush();
				w.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		// proBack_end.jsp
		if ("ProUpdate_Booking".equals(action)) {

			// getParameter
			String mem_id = null;
			mem_id = req.getParameter("mem_ID");

			ProVO proVO = (ProVO) session.getAttribute("proVOLogin");
			String pro_ID = proVO.getPro_ID();

			String order_no = req.getParameter("i_order_no");
			String class_no = req.getParameter("i_class_no");
			java.sql.Date rDate = java.sql.Date.valueOf(req.getParameter("rDate"));
			Integer p_coin = new Integer(req.getParameter("p_coin"));
			Integer hrIndex = new Integer(req.getParameter("hrIndex").trim());
			// transfer hrIndex into binary time;
			StringBuilder strHr = new StringBuilder("000000000000000000000000");
			strHr.setCharAt(hrIndex, '1');
			String hr = new String(strHr);

			System.out.println("mem_id" + mem_id);
			System.out.println("pro_id" + pro_ID);
			System.out.println("order_no" + order_no);
			System.out.println("class_no" + class_no);
			System.out.println("rDate" + rDate);
			System.out.println("p_coin" + p_coin);
			System.out.println("hrIndex" + hrIndex);

			// get VO for update
			IClassOrderVO icoVO = new IClassOrderVO();
			icoVO.setI_class_no(class_no);
			icoVO.setI_order_no(order_no);
			icoVO.setMem_ID(mem_id);
			icoVO.setRDate(rDate);
			icoVO.setHr(hr);
			icoVO.setP_coin(p_coin);
			icoVO.setIo_status(2);

			// update class order status
			IClassOrderService icoSvc = new IClassOrderService();
			icoSvc.updateIClassVO(icoVO);

			// member refund
			MemService memSvc = new MemService();
			// member get back money
			MemVO memVO1 = memSvc.getOneMem(mem_id);
			memVO1.setCoin(memVO1.getCoin() + p_coin);
			memSvc.updateMem(memVO1.getMem_name(), memVO1.getMem_psw(), memVO1.getMem_bir(), memVO1.getSex(),
					memVO1.getMem_addr(), memVO1.getMem_email(), memVO1.getMem_phone(), memVO1.getMem_absent(),
					memVO1.getCoin(), memVO1.getMem_resume(), memVO1.getM_reg_date(), memVO1.getSel_auth(),
					memVO1.getArt_auth(), memVO1.getCom_auth(), memVO1.getMem_id());

			// pro subtract refund
			ProService proSvc = new ProService();
			MemVO memVO2 = memSvc.getOneMem(proVO.getMem_ID());
			memVO2.setCoin(memVO2.getCoin() - p_coin);
			memSvc.updateMem(memVO2.getMem_name(), memVO2.getMem_psw(), memVO2.getMem_bir(), memVO2.getSex(),
					memVO2.getMem_addr(), memVO2.getMem_email(), memVO2.getMem_phone(), memVO2.getMem_absent(),
					memVO2.getCoin(), memVO2.getMem_resume(), memVO2.getM_reg_date(), memVO2.getSel_auth(),
					memVO2.getArt_auth(), memVO2.getCom_auth(), memVO2.getMem_id());

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write("預約已取消!");
			out.flush();
			out.close();
		}

		// proBack_end.jsp
		if ("addDayoff".equals(action)) { // 來自addEmp.jsp的請求

			try {
				ProVO proVO = (ProVO) session.getAttribute("proVOLogin");
				String pro_ID = proVO.getPro_ID();
				String mem_ID = proVO.getMem_ID();
				java.sql.Date close_date = java.sql.Date.valueOf(req.getParameter("rDate").trim());
				String hr = "111111111111111111111111";

				TrainerPublicService tpSvc = new TrainerPublicService();
				List<TrainerPublicVO> tpList = tpSvc.getAllDay(close_date, pro_ID);
				IClassOrderService icoSvc = new IClassOrderService();
				GroupOrderDetailService godSvc = new GroupOrderDetailService();
				
				JSONObject obj = new JSONObject();
				if (tpList.size() == 0) {
					DayoffService dayoffSvc = new DayoffService();
					DayoffVO dayoffVO = new DayoffVO();
					dayoffVO.setPro_ID(pro_ID);
					dayoffVO.setMem_ID(mem_ID);
					dayoffVO.setClose_date(close_date);
					dayoffVO.setHr(hr);
					dayoffVO = dayoffSvc.addDayoff(pro_ID, mem_ID, close_date, hr);
					obj.put("msg", "請好好休息吧!");

				} else if(tpList.size() != 0){
					int countClass = 0;
					for(TrainerPublicVO tpVO:tpList) {
						if(tpVO.getStatus()!=2) {
							countClass+=0;
						}
					}
					if(countClass>0) {
						obj.put("msg", "這天已有預約了!上班加油!");
					}else {
						DayoffService dayoffSvc = new DayoffService();
						DayoffVO dayoffVO = new DayoffVO();
						dayoffVO.setPro_ID(pro_ID);
						dayoffVO.setMem_ID(mem_ID);
						dayoffVO.setClose_date(close_date);
						dayoffVO.setHr(hr);
						dayoffVO = dayoffSvc.addDayoff(pro_ID, mem_ID, close_date, hr);
						obj.put("msg", "請好好休息吧!");
					}
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		// proBack_end.jsp
		if ("findGroupClass".equals(action)) { // 來自addEmp.jsp的請求
			String pro_ID= req.getParameter("pro_ID");
			JSONArray arr = new JSONArray();
			
			try {
				GroupClassService gcSvc = new GroupClassService();
				List<GroupClassVO> gcList = gcSvc.getGroupClassesByProId(pro_ID);
				
				ClassTypeService ctSvc = new ClassTypeService();
				List<ClassTypeVO> ctList = ctSvc.getAll();

				for (GroupClassVO gcVO : gcList) {
					if(gcVO.getC_status()==1) {
						JSONObject obj = new JSONObject();
						obj.put("g_class_no", gcVO.getG_class_no());
						obj.put("c_type_no", gcVO.getC_type_no());
						obj.put("g_name", gcVO.getG_name());
						obj.put("loc", gcVO.getLoc());
						obj.put("g_max", gcVO.getG_max());
						for(int i=0; i<ctList.size(); i++) {//get correct classType name
							if(ctList.get(i).getC_type_no().equals(gcVO.getC_type_no())) {
								obj.put("t_name", ctList.get(i).getT_name());
							}
						}
						arr.put(obj);
					}
				}
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(arr.toString());
				out.flush();
				out.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
