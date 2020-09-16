package com.groupclass.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.godetail.model.GroupOrderDetailService;
import com.godetail.model.GroupOrderDetailVO;
import com.groupclass.model.*;
import com.grouphour.model.*;
import com.tool.hong.CalendarForINGYM;
import com.tool.hong.myExceptionForTime;
import com.trainerreservation.model.*;
@MultipartConfig
public class GroupClassServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServletContext context;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String g_class_no = req.getParameter("g_class_no");
		String action = req.getParameter("action");
		if (action == null || action.trim().length() == 0) {
			res.setContentType("image/jpeg");
			ServletOutputStream out = res.getOutputStream();
			GroupClassService gcSvc = new GroupClassService();
			byte[] g_pic = gcSvc.getG_picByClassNo(g_class_no);
			if (g_pic == null||g_pic.length==0)
				g_pic = getPictureByteArray(context);
			out.write(g_pic);
		} else
			doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		res.setCharacterEncoding("UTF-8");
		if("delete".equals(action)) {
			String g_time_no = req.getParameter("g_time_no");
			GroupHourService ghSvc = new GroupHourService();
//			GroupHourVO ghVO = ghSvc.getOneTimeNo(g_time_no);
			GroupOrderDetailService godSvc = new GroupOrderDetailService();
			System.out.println(g_time_no +"    gc 75");
			List<GroupOrderDetailVO> list = godSvc.getDetailsByGTimeNo(g_time_no, 0);
			List<GroupOrderDetailVO> list1 = godSvc.getDetailsByGTimeNo(g_time_no, 1);
			List<GroupOrderDetailVO> list2 = godSvc.getDetailsByGTimeNo(g_time_no, 2);
			int allSize = list.size()+list1.size()+list2.size();
			if(allSize!=0) {
				PrintWriter writer = res.getWriter();
				writer.write("已有人預約上課之團課不可取消");
				return;
			}else {
				ghSvc.delete(g_time_no);
				PrintWriter writer = res.getWriter();
				writer.write("取消成功");
				return;
			}
		
		}	
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String g_class_no = req.getParameter("g_class_no");
				if (g_class_no == null || (g_class_no.trim()).length() == 0) {
					errorMsgs.add("請輸入團課編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				GroupClassService gcsSvc = new GroupClassService();
				GroupClassVO gcVO = gcsSvc.getOneGroupClass(g_class_no);
				if (g_class_no == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gcVO", gcVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("gcVO", gcVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/groupclass/listOneGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {

				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String g_class_no = req.getParameter("g_class_no");

				if (g_class_no == null || (g_class_no.trim()).length() == 0) {
					errorMsgs.add("請輸入團課編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ****************************************/
				GroupClassService gcsSvc = new GroupClassService();
				GroupClassVO gcVO = gcsSvc.getOneGroupClass(g_class_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("gcVO", gcVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/groupclass/update_GroupClass_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/groupClassSelectPage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			GroupClassService gcSvc = new GroupClassService();
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String g_class_no = req.getParameter("g_class_no");
				if (g_class_no == null || (g_class_no.trim()).length() == 0) {
					errorMsgs.put("g_class_no","請輸入團課編號");
				}
				String pro_id = req.getParameter("pro_id");
				if (pro_id == null || pro_id.trim().length() == 0)
					errorMsgs.put("pro_id","請輸入團課名稱");
				String g_name = req.getParameter("g_name");
				if (g_name == null || g_name.trim().length() == 0) {
					errorMsgs.put("g_name","請輸入團課名稱");
				} else if ((g_name.trim()).length() >= 20) {
					errorMsgs.put("g_name","團課名稱不可超過20字元");
				}
				String loc = req.getParameter("loc");
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				loc = county+district+loc;
				if (loc == null || loc.trim().length() == 0) {
					errorMsgs.put("loc","請輸入上課地點");	
				} else if ((loc.trim()).length() >= 16) {
					errorMsgs.put("loc","上課地點不可超過16字元");
				}

				Integer g_max = null;
				try {
					g_max = new Integer(req.getParameter("g_max").trim());
				} catch (NumberFormatException e) {
					g_max = 0;
					errorMsgs.put("g_max","團課人數上限請填數字");
				}

				Integer p_coin = null;
				try {
					p_coin = new Integer(req.getParameter("p_coin").trim());
				} catch (NumberFormatException e) {
					p_coin = 0;
					errorMsgs.put("p_coin","課程金額請填數字");
				}

				String g_detail = req.getParameter("g_detail");
				if (g_detail == null || g_detail.trim().length() == 0) {
					errorMsgs.put("g_detail","請輸入團課介紹");
				}
				byte[] g_pic = null;
				Part part = req.getPart("g_pic");
				g_pic = getPictureByteArray(part);
				if (g_pic.length == 0)
					g_pic = gcSvc.getG_picByClassNo(g_class_no);

				Integer c_status = null;
				try {
					c_status = new Integer(req.getParameter("c_status").trim());
				} catch (NumberFormatException e) {
					c_status = 1;
					errorMsgs.put("c_status","狀態請輸入數字");
				} catch (NullPointerException ne) {
					c_status = 1;
					errorMsgs.put("c_status","狀態不得為空");
				}

				String c_type_no = req.getParameter("c_type_no");
				if (c_type_no == null || c_type_no.trim().length() == 0)
					errorMsgs.put("c_type_no","請輸入團課類別");

				GroupClassVO gcVO = new GroupClassVO();
				gcVO.setC_type_no(c_type_no);
				gcVO.setG_name(g_name);
				gcVO.setLoc(loc);
				gcVO.setG_max(g_max);
				gcVO.setP_coin(p_coin);
				gcVO.setG_detail(g_detail);
//				gcVO.setG_pic(g_pic);		
				gcVO.setC_status(c_status);
				gcVO.setG_class_no(g_class_no);
				gcVO.setPro_id(pro_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gcVO", gcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/update_GroupClass_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				gcVO = gcSvc.updateGroupClass(c_type_no, g_name, loc, g_max, p_coin, g_detail, g_pic, c_status,
						g_class_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("gcVO", gcVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/ProAndClass/myClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("updateException  "+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupclass/update_GroupClass_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String g_name = req.getParameter("g_name");
				if (g_name == null || g_name.trim().length() == 0) {
					errorMsgs.put("g_name","請輸入團課名稱");
				} else if ((g_name.trim()).length() >= 20) {
					errorMsgs.put("g_name","團課名稱不可超過20個字");
				}
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				String loc = req.getParameter("loc");
				if (loc == null || loc.trim().length() == 0) {
					errorMsgs.put("loc","請輸入上課地點");
				} else if ((loc.trim()).length() >= 10) {
					errorMsgs.put("loc","上課地點不可超過10字元");
				}
				loc = county+district+loc;
				String pro_id = req.getParameter("pro_id");
				if (pro_id == null || pro_id.trim().length() == 0)
					errorMsgs.put("pro_id","請輸入團課名稱");
				Integer g_max = null;
				try {
					g_max = new Integer(req.getParameter("g_max").trim());
				} catch (NumberFormatException e) {
					g_max = 0;
					errorMsgs.put("g_max","團課人數上限請填數字");
				}

				Integer p_coin = null;
				try {
					p_coin = new Integer(req.getParameter("p_coin").trim());
				} catch (NumberFormatException e) {
					p_coin = 0;
					errorMsgs.put("p_coin","金額請填數字");
				}
				
				String g_detail = req.getParameter("g_detail");
				if (g_detail == null || g_detail.trim().length() == 0) {
					errorMsgs.put("g_detail","請輸入團課介紹");
				}
				byte[] g_pic = null;
				Part part = req.getPart("g_pic");

				if (part != null || part.toString().trim().length() != 0)
					g_pic = getPictureByteArray(part);
				else
					g_pic = getPictureByteArray(getServletContext()); // 無上傳圖片的預設圖片

				String c_type_no = req.getParameter("c_type_no");
				if (c_type_no == null || c_type_no.trim().length() == 0)
					errorMsgs.put("c_type_no","請選擇團課類別");
				GroupClassVO gcVO = new GroupClassVO();
				gcVO.setG_detail(g_detail);
				gcVO.setG_name(g_name);
				gcVO.setG_pic(g_pic);
				gcVO.setLoc(loc);
				gcVO.setP_coin(p_coin);
				gcVO.setG_max(g_max);
				gcVO.setC_type_no(c_type_no);
				gcVO.setPro_id(pro_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gcVO", gcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/addGroupClass.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始新增資料 ***************************************/
				GroupClassService gcSvc = new GroupClassService();
				gcVO = gcSvc.addGroupClass(pro_id, c_type_no, g_name, loc, g_max, p_coin, g_detail, g_pic);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				gcVO=null;
				gcVO=gcSvc.getNewGroupClassesByProId(pro_id);
				String url = "/front-end/ProAndClass/myClass.jsp";
				req.setAttribute("gcVO", gcVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("GCinsertExcption   "+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/addGroupClass.jsp");
				failureView.forward(req, res);
			}

		}
		if ("insertForOneGCTime".equals(action)) { // 來自listOneGroupClass的請求
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

//			HashSet<GroupHourVO> ghVOs = new HashSet<GroupHourVO>();			
			GroupHourService ghSvc = new GroupHourService();
			TrainerReservationService trSvc = new TrainerReservationService();
			GroupClassService gcSvc = new GroupClassService();
			CalendarForINGYM tool = new CalendarForINGYM();
			String g_class_no = req.getParameter("g_class_no");
			GroupClassVO gcVO = gcSvc.getOneGroupClass(g_class_no);
			
			req.setAttribute("gcVO", gcVO);
//			if (g_class_no == null || g_class_no.trim().length() == 0)
//				errorMsgs.add("請輸入團課編號");
			try {
//				Integer addCount = null;
//				try {
//					addCount = new Integer(req.getParameter("addCount"));
//				} catch (NumberFormatException ne) {
//					errorMsgs.add("addCountError"+ne.getMessage());
//				}

//				for (int i = 1; i <= addCount; i++) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = sdf.parse(req.getParameter("c_date"));
					java.sql.Date c_date = new java.sql.Date(utilDate.getTime());
					String hr = req.getParameter("hr");
//					if (hr == null || hr.trim().length() == 0)
//						errorMsgs.add("請輸入團課時間");
					GroupHourVO ghVO = null;
					ghVO = new GroupHourVO();
					ghVO.setG_class_no(g_class_no);
					ghVO.setC_date(c_date);
					ghVO.setHr(hr);
//					ghVOs.add(ghVO);
//				}
//				if (ghVOs.size() != addCount)
//					errorMsgs.add("新增團課時間重複");

//				HashSet<GroupHourVO> gClassNoAll_ghVOs = ghSvc.getAllByGroupClassNo(g_class_no); // 判斷資料庫裡的團課時間有無重複
				HashSet<CalendarForINGYM> dataBaseTime = tool.getCalendarSetTRVO(trSvc.getAllForRetainByPro_id(gcVO.getPro_id()));

				int allSize = dataBaseTime.size();
				
//				int i = 0;
//				for (GroupHourVO ghVO : ghVOs) {
					dataBaseTime.add(new CalendarForINGYM(ghVO));
//					++i;
					
					if (allSize + 1 != dataBaseTime.size()) 
						throw new myExceptionForTime("你在此時段已有安排一對一或團課課程!");
//						errorMsgs.add("新增團課時間重複");
//						break;
//				}
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("ghVOs", ghVOs);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				// 開始新增團課時間
//				for (GroupHourVO ghVO : ghVOs) {
					ghSvc.addGroupHour(ghVO);
//				}
//				String url = "/front-end/groupclass/listOneGroupClass.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
					PrintWriter writer = res.getWriter();
					writer.write("新增成功");
					return;
			}catch(myExceptionForTime eft){
				System.out.println(eft.getMessage());
//				RequestDispatcher view = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//				view.forward(req, res);
				PrintWriter writer = res.getWriter();
				writer.write(eft.getMessage());
				return;
			}catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
//				failureView.forward(req, res);
				PrintWriter writer = res.getWriter();
				writer.write(e.getMessage());
				return;
			}
		}

		if ("insertForGCTime".equals(action)) { // 來自listOneGroupClass的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			HashSet<GroupHourVO> ghVOs = new HashSet<GroupHourVO>();			
			GroupHourService ghSvc = new GroupHourService();
			TrainerReservationService trSvc = new TrainerReservationService();
			GroupClassService gcSvc = new GroupClassService();
			CalendarForINGYM tool = new CalendarForINGYM();
			String g_class_no = req.getParameter("g_class_no");
			GroupClassVO gcVO = gcSvc.getOneGroupClass(g_class_no);
			
			req.setAttribute("gcVO", gcVO);
			if (g_class_no == null || g_class_no.trim().length() == 0)
				errorMsgs.add("請輸入團課編號");
			try {
				Integer addCount = null;
				try {
					addCount = new Integer(req.getParameter("addCount"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("addCountError"+ne.getMessage());
				}

				for (int i = 1; i <= addCount; i++) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = sdf.parse(req.getParameter("c_date" + i));
					java.sql.Date c_date = new java.sql.Date(utilDate.getTime());
					String hr = req.getParameter("hr" + i);
					if (hr == null || hr.trim().length() == 0)
						errorMsgs.add("請輸入團課時間");
					GroupHourVO ghVO = null;
					ghVO = new GroupHourVO();
					ghVO.setG_class_no(g_class_no);
					ghVO.setC_date(c_date);
					ghVO.setHr(hr);
					ghVOs.add(ghVO);
				}
				if (ghVOs.size() != addCount)
					errorMsgs.add("新增團課時間重複");

//				HashSet<GroupHourVO> gClassNoAll_ghVOs = ghSvc.getAllByGroupClassNo(g_class_no); // 判斷資料庫裡的團課時間有無重複
				HashSet<CalendarForINGYM> dataBaseTime = tool.getCalendarSetTRVO(trSvc.getAllForRetainByPro_id(gcVO.getPro_id()));

				int allSize = dataBaseTime.size();
				
				int i = 0;
				for (GroupHourVO ghVO : ghVOs) {
					dataBaseTime.add(new CalendarForINGYM(ghVO));
					++i;
					if (allSize + i != dataBaseTime.size()) { 
						errorMsgs.add("新增團課時間重複");
						break;
					}
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ghVOs", ghVOs);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
					failureView.forward(req, res);
					return;
				}
				// 開始新增團課時間
				for (GroupHourVO ghVO : ghVOs) {
					ghSvc.addGroupHour(ghVO);
				}
				String url = "/front-end/groupclass/listOneGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("updateForGCTime".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);			
			GroupHourService ghSvc = new GroupHourService();
			TrainerReservationService trSvc = new TrainerReservationService();
			GroupHourVO ghVO = null;
			
			try {
				String g_time_no = req.getParameter("g_time_no");
				if(g_time_no==null||g_time_no.trim().length()==0)
					errorMsgs.add("沒有抓到團課時間編號");
				String g_class_no = req.getParameter("g_class_no");
				if(g_class_no==null||g_class_no.trim().length()==0)
					errorMsgs.add("沒有抓到團課編號");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date c_date = new java.sql.Date(sdf.parse(req.getParameter("c_date")).getTime());
				String hr = req.getParameter("hr");
				if(hr==null||hr.trim().length()==0)
					errorMsgs.add("請輸入時間");
				String pro_id = req.getParameter("pro_id");
				ghVO = new GroupHourVO();
				ghVO.setC_date(c_date);
				ghVO.setG_class_no(g_class_no);
				ghVO.setG_time_no(g_time_no);
				ghVO.setHr(hr);
				
				HashSet<TrainerReservationVO> dataBaseTime = trSvc.getAllForRetainByPro_id(pro_id);
				int totalSize = dataBaseTime.size()+1;
//				dataBaseTime.add(new ClendarForINGYM(ghVO));
				if(totalSize!=dataBaseTime.size())
					errorMsgs.add("更新時間與現有團課時間重複");
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ghVO", ghVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
					failureView.forward(req, res);
					return;
				}
				// 開始新增團課時間
			
				String url = "/front-end/groupclass/listOneGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupclass/listOneGroupClass.jsp");
				failureView.forward(req, res);
			}
		}

	}

	public void init() {
		this.context = getServletContext();
	}

	public static byte[] getPictureByteArray(Part part) throws IOException {
	
		InputStream in = part.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		in.close();

		return baos.toByteArray();
	}

	public static byte[] getPictureByteArray(ServletContext context) throws IOException { // 專案路徑用
		InputStream ras = context.getResourceAsStream("/images/noImage.jpeg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = ras.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		ras.close();
		return baos.toByteArray();
	}
}
