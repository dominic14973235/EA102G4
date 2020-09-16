package com.individualClass.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.classType.model.ClassTypeService;
import com.classType.model.ClassTypeVO;
import com.individualClass.model.IndividualClassService;
import com.individualClass.model.IndividualClassVO;


@MultipartConfig()
public class IndividualClassServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("i_class_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
	
				String i_class_no = null;
				try {
					i_class_no = str;
				} catch (Exception e) {
					errorMsgs.add("課程編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("Class.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				IndividualClassService individualClassSvc = new IndividualClassService();
				IndividualClassVO individualClassVO = individualClassSvc.getOneIndividualClass(i_class_no);
				if (individualClassVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("myClass.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("individualClassVO", individualClassVO); // 資料庫取出的empVO物件,存入req
				String url = "class-details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("myClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getType_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("c_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String c_type_no = null;
				try {
					c_type_no = str;
				} catch (Exception e) {
					errorMsgs.add("課程編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("class-details.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				IndividualClassService individualClassSvc = new IndividualClassService();
				List <IndividualClassVO> list = individualClassSvc.findByTypeNo(c_type_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("class-details.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "classes2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("class-details.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		 if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String i_class_no = req.getParameter("i_class_no");
					
					/***************************2.開始查詢資料****************************************/
					IndividualClassService individualClassSvc = new IndividualClassService();
					IndividualClassVO individualClassVO = individualClassSvc.getOneIndividualClass(i_class_no);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("individualClassVO", individualClassVO);         // 資料庫取出的empVO物件,存入req
					String url = "updateIndClass.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("myClass.jsp");
					failureView.forward(req, res);
				}
			}
		 
		 
		 
		 			/***********************UPDATE*************************/
			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String i_class_no = req.getParameter("i_class_no").trim();
					if (i_class_no == null || i_class_no.trim().length() == 0) {
						errorMsgs.add("課程編號:請勿空白");
					}
					
					String pro_ID = req.getParameter("pro_ID").trim();
					if (pro_ID == null || pro_ID.trim().length() == 0) {
						errorMsgs.add("教練編號:請勿空白");
					}

					String c_name = req.getParameter("c_name");
					String c_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
					if (c_name == null || c_name.trim().length() == 0) {
						errorMsgs.add("課程名稱: 請勿空白");
					} else if(!c_name.trim().matches(c_nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("課程名稱: 只能是中、英文字母, 且長度必需在2到10之間");
		            }
					
					String c_type_no = req.getParameter("c_type_no").trim();
					if (c_type_no == null || c_type_no.trim().length() == 0) {
						errorMsgs.add("類別:請勿空白");
					}
					
					Integer p_coin = null;
					try {
						p_coin = new Integer(req.getParameter("p_coin").trim());
					} catch (NumberFormatException e) {
						p_coin = 0;
						errorMsgs.add("價格請填數字.");
					}
					
					String loc = req.getParameter("loc");
					if (loc == null || loc.trim().length() == 0) {
						errorMsgs.add("地點: 請勿空白");
					}

					String c_detail = req.getParameter("c_detail");
					if (c_detail == null || c_detail.trim().length() == 0) {
						errorMsgs.add("課程詳情: 請勿空白");
					}

					Integer c_status = null;					
					try {
						 c_status = new Integer(req.getParameter("c_status"));
					}catch(NumberFormatException e) {
						errorMsgs.add("價格請填數字.");
					}

					IndividualClassService IndividualClassSvc = new IndividualClassService();			
					byte[] c_pic = null;
					Part part = req.getPart("c_pic");
					InputStream in = part.getInputStream();
					
					if(in.available()!=0) {			
								byte[] buf = new byte[in.available()];
								in.read(buf);
								c_pic = buf;
								in.close();
					} else {
						c_pic = IndividualClassSvc.getOneIndividualClass(i_class_no).getC_pic();
					}
					
					IndividualClassVO individualClassVO = new IndividualClassVO();
					individualClassVO.setPro_ID(pro_ID);
					individualClassVO.setC_name(c_name);
					individualClassVO.setC_type_no(c_type_no);
					individualClassVO.setLoc(loc);
					individualClassVO.setP_coin(p_coin);
					individualClassVO.setC_status(c_status);
					individualClassVO.setC_pic(c_pic);
					individualClassVO.setC_detail(c_detail);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("individualClassVO", individualClassVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("updateIndClass.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始修改資料*****************************************/
					IndividualClassService individualClassSvc = new IndividualClassService();
					individualClassVO = individualClassSvc.updateIndividualClass( i_class_no,  pro_ID,  c_type_no,  c_name,
							 loc,  c_detail,  c_pic,  c_status,  p_coin);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("individualClassVO", individualClassVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "myClass.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("updateIndClass.jsp");
					failureView.forward(req, res);
				}
			}
			
					/***********************INSERT*************************/
			 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						String c_name = req.getParameter("c_name");
						String c_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
						if (c_name == null || c_name.trim().length() == 0) {
							errorMsgs.add("課程名稱: 請勿空白");
						} else if(!c_name.trim().matches(c_nameReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("課程名稱: 只能是中、英文字母, 且長度必需在2到10之間");
			            }
						
						String pro_ID = req.getParameter("pro_ID").trim();
						if (pro_ID == null || pro_ID.trim().length() == 0) {
							errorMsgs.add("教練編號:請勿空白");
						}
						
						
						String c_type_no = req.getParameter("c_type_no").trim();
						if (c_type_no == null || c_type_no.trim().length() == 0) {
							errorMsgs.add("類別:請勿空白");
						}
						
						Integer p_coin = null;
						try {
							p_coin = new Integer(req.getParameter("p_coin").trim());
						} catch (NumberFormatException e) {
							p_coin = 0;
							errorMsgs.add("價格請填數字.");
						}
						
						String loc = req.getParameter("loc");
						if (loc == null || loc.trim().length() == 0) {
							errorMsgs.add("地點: 請勿空白");
						}
						
						String c_detail = req.getParameter("c_detail");
						if (c_detail == null || c_detail.trim().length() == 0) {
							errorMsgs.add("課程詳情: 請勿空白");
						}
						
						byte[] c_pic = null;
						Part part = req.getPart("c_pic");
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						c_pic = buf;
						in.close();
						
						Integer c_status= null;
						
						try {
							c_status = new Integer(req.getParameter("c_status"));
						}catch(NumberFormatException e) {
							errorMsgs.add("價格請填數字.");
						}
						
						IndividualClassVO individualClassVO = new IndividualClassVO();
						individualClassVO.setPro_ID(pro_ID);
						individualClassVO.setC_name(c_nameReg);
						individualClassVO.setC_type_no(c_type_no);
						individualClassVO.setLoc(loc);
						individualClassVO.setP_coin(p_coin);
						individualClassVO.setC_status(c_status);
						individualClassVO.setC_pic(c_pic);
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("individualClassVO", individualClassVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("addIndClass.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************2.開始新增資料***************************************/
						IndividualClassService individualClassSvc = new IndividualClassService();
						individualClassVO = individualClassSvc.addIndividualClass(pro_ID,  c_type_no, c_name,
								 loc,  c_detail, c_pic,c_status,p_coin);
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						req.setAttribute("individualClassVO", individualClassVO); 
						String url = "/front-end/ProAndClass/myClass.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/ProAndClass/addIndClass.jsp");
						failureView.forward(req, res);
					}
				}
			
			 //MyCalendar.jsp ============= AJAX start from here
			 if ("getPro_For_Display".equals(action)) { //get value for insert order
					JSONArray arr = new JSONArray();
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						String pro_ID = req.getParameter("pro_ID");
						//get classVO
						IndividualClassService src = new IndividualClassService();
						List<IndividualClassVO> list = src.findByPro(pro_ID);
						
						//get type name
						ClassTypeService ctSvc = new ClassTypeService();
						List <ClassTypeVO> ctList = ctSvc.getAll();

//						System.out.println(2323/0);

						for(IndividualClassVO vo: list) {
							JSONObject obj = new JSONObject();
							obj.put("C_name", vo.getC_name());
							obj.put("I_class_no", vo.getI_class_no());
							obj.put("p_coin", vo.getP_coin());
							obj.put("pro_ID", vo.getPro_ID());
							obj.put("loc", vo.getLoc());
							for(int i=0; i<ctList.size(); i++) {//get correct classType name
								if(ctList.get(i).getC_type_no().equals(vo.getC_type_no())) {
									obj.put("t_name", ctList.get(i).getT_name());
								}
							}
							arr.put(obj);
						}
						
						res.setContentType("plain/text");
						res.setCharacterEncoding("utf-8");
						PrintWriter w = res.getWriter();
						w.write(arr.toString());
						w.flush();
						w.close();
					} catch(Exception e) {
						System.out.println(e);
					} 
			 }
			 
			 
			 //Calendar_IndClass.jsp get data and input into modal
			 if ("getClass_Detail".equals(action)) { // Ajax 請求一個individualClassVO

					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						String str = req.getParameter("i_class_no");
						String i_class_no = str;

						/***************************2.開始查詢資料*****************************************/
						IndividualClassService individualClassSvc = new IndividualClassService();
						IndividualClassVO individualClassVO = individualClassSvc.getOneIndividualClass(i_class_no);
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						JSONObject obj = new JSONObject();
						obj.put("C_name", individualClassVO.getC_name());
						obj.put("I_class_no", individualClassVO.getI_class_no());
						obj.put("p_coin", individualClassVO.getP_coin());
						obj.put("pro_ID", individualClassVO.getPro_ID());
						obj.put("c_type_no", individualClassVO.getC_type_no());
						obj.put("loc", individualClassVO.getLoc());

						res.setContentType("plain/text");
						res.setCharacterEncoding("utf-8");
						PrintWriter w = res.getWriter();
						w.write(obj.toString());
						w.flush();
						w.close();

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						System.out.println("無法取得資料:" + e.getMessage());
					}
				}
		 
	}
}
