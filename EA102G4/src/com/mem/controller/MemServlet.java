package com.mem.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.mem.model.*;
import com.memphoto.model.*;
//import com.pro.model.*;
import com.pro.model.ProService;
import com.pro.model.ProVO;

import tool.EmailThread;
import tool.MailService;


public class MemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//聊天室取的所有會員
		if ("getAll".equals(action)) { // 來自index.jsp的請求
			System.out.println("getAll");
			MemService memSvc = new MemService();
			List<MemVO> allMember = memSvc.getAll();
			
			String str = "{";
			for(int i = 0;i<allMember.size();i++) {
				str += (str == "{" ) ? "\""+allMember.get(i).getMem_id()+"\":"+"\""+allMember.get(i).getMem_name()+"\"" : "," + "\""+allMember.get(i).getMem_id()+"\":"+"\""+allMember.get(i).getMem_name()+"\"";
			}
			str += "}";
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(str);
			out.flush();
			out.close();
			return;// 程式中斷
			
		}
		
		//登出
		if ("logout".equals(action)) { // 來自index.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
						
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
			successView.forward(req, res);
		}
		
		//登入
		if ("login".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//帳號
				String mem_email = req.getParameter("mem_email").trim();
				mem_email = mem_email.toLowerCase();
				if (mem_email == null || mem_email.length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}
				
				//密碼
				String mypsw = req.getParameter("mem_psw").trim();
				String pswReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (mypsw == null || mypsw.length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if(!mypsw.matches(pswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMemByMemAcc(mem_email);
				if (memVO == null || !(memVO.getMem_psw().equals(mypsw))) {
					errorMsgs.add("帳號或密碼錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				ProService proSvc = new ProService();
			    ProVO proVO = proSvc.getProByMem(memVO.getMem_id());
			    
			    /***************************3.查詢完成,準備轉交(Send the Success view)*************/
			    HttpSession session = req.getSession();
			    session.setAttribute("memVOLogin", memVO);
			    if(proVO != null) {
			    	if(proVO.getPro_auth()==1) {
			    session.setAttribute("proVOLogin", proVO);
			    }
			    }
			    
			    String url = (String)session.getAttribute("location");
			    System.out.println( "location =" + url);
			    if(url == "" || url == null) {
			    	url = "/front-end/index.jsp";
			    }
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/signin.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/front-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的memVO物件,存入req
				String url = "/front-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		System.out.println(action);

		if ("update_auth".equals(action)) { // 來自update_mem_input.jsp的請求
		System.out.println(1);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//會員編號
				String mem_id = req.getParameter("mem_id");
				System.out.println(mem_id);
				
				//賣家權限
				String sel_auth = req.getParameter("sel_auth");
				
				//發文權限
				String art_auth = req.getParameter("art_auth");
				
				//留言權限
				String com_auth = req.getParameter("com_auth");
				

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setSel_auth(sel_auth);
				memVO.setArt_auth(art_auth);
				memVO.setCom_auth(com_auth);
				
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMemAuth(sel_auth, art_auth, com_auth, mem_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mem_id", memVO.getMem_id()); // 資料庫update成功後,正確的的memVO物件,存入req
				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//會員編號
				String mem_id = req.getParameter("mem_id");
				
				//會員姓名
				String mem_name = req.getParameter("mem_name").trim();
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (mem_name == null || mem_name.length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母和_ , 且長度必需在2到10之間");
	            }
				
				//密碼
				String mem_psw = req.getParameter("mem_psw").trim();
				
				
				//生日
				java.sql.Date mem_bir = null;
				try {
					mem_bir = java.sql.Date.valueOf(req.getParameter("mem_bir").trim());
				} catch (IllegalArgumentException e) {
					mem_bir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				//性別
				String sex = req.getParameter("sex").trim();
				
				
				//地址
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,66}$";
				if (address == null || address.length() == 0 || country == null ||country.length() == 0 || district == null || district.length() == 0 || zipcode == null || zipcode.length() == 0) {
					errorMsgs.add("地址: 請勿空白 ");
				} else if(!address.matches(addrReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中文、英文字母、數字 ");
	            }
				String mem_addr = zipcode+country+district+address;
				
				
				//電子信箱
				String mem_email = req.getParameter("mem_email").trim();
				
				
				
				//電話
				String mem_phone = req.getParameter("mem_phone").trim();
				String phoneReg = "^(09)[(0-9)]{8}$";
				if(mem_phone == null || mem_phone.length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				}else if(!mem_phone.matches(phoneReg)) {
					errorMsgs.add("手機號碼: 格式錯誤");
				}
				
				//請假次數
				Integer mem_absent = new Integer(req.getParameter("mem_absent"));
				
				//點數
				Integer coin = new Integer(req.getParameter("coin"));
				
				//自我介紹
				String mem_resume = req.getParameter("mem_resume");
				
				//註冊時間
				java.sql.Timestamp m_reg_date = Timestamp.valueOf(req.getParameter("m_reg_date"));
				
				//賣家權限
				String sel_auth = req.getParameter("sel_auth");
				
				//發文權限
				String art_auth = req.getParameter("art_auth");
				
				//留言權限
				String com_auth = req.getParameter("com_auth");
				

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_name(mem_name);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_bir(mem_bir);
				memVO.setSex(sex);
				memVO.setMem_addr(mem_addr);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_absent(mem_absent);
				memVO.setCoin(coin);
				memVO.setMem_resume(mem_resume);
				memVO.setM_reg_date(m_reg_date);
				memVO.setSel_auth(sel_auth);
				memVO.setArt_auth(art_auth);
				memVO.setCom_auth(com_auth);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/updateMemDetail.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_name, mem_psw, mem_bir, sex, mem_addr, mem_email, mem_phone, mem_absent, coin, mem_resume, m_reg_date, sel_auth, art_auth, com_auth, mem_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				HttpSession session = req.getSession();
			    session.setAttribute("memVOLogin", memVO);
				String url = "/front-end/mem/memDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/updateMemDetail.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自signup.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				//會員姓名
				String mem_name = req.getParameter("mem_name").trim();
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (mem_name == null || mem_name.length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母和_ , 且長度必需在2到10之間");
	            }
				//密碼
				String mem_psw = "";
				int category;
				for (int i = 0; i < 8; i++) {
					category = (int) (Math.random() * 3 + 1);
					if (category == 1) {
						mem_psw += (char) ((int) (Math.random() * 10) + 48);
					} else if (category == 2) {
						mem_psw += (char) ((int) (Math.random() * 26) + 65);
					} else {
						mem_psw += (char) ((int) (Math.random() * 26) + 97);
					}
				}
				System.out.println(mem_psw);
				
				
				//生日
				java.sql.Date mem_bir = null;
				try {
					mem_bir = java.sql.Date.valueOf(req.getParameter("mem_bir").trim());
				} catch (IllegalArgumentException e) {
					mem_bir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				//性別
				String sex = req.getParameter("sex").trim();
				
				
				//地址
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,66}$";
				if (address == null || address.length() == 0 || country == null ||country.length() == 0 || district == null || district.length() == 0 || zipcode == null || zipcode.length() == 0) {
					errorMsgs.add("地址: 請勿空白 ");
				} else if(!address.matches(addrReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中文、英文字母、數字 ");
	            }
				String mem_addr = zipcode+country+district+address;
				
				
				//電子信箱
				String mem_email = req.getParameter("mem_email").trim();
				
				String emailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (mem_email == null || mem_email.length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if(!mem_email.matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子信箱: 格式錯誤");
	            }
				mem_email = mem_email.toLowerCase();
				MemService memSvc = new MemService();
				List<String> list = memSvc.getAllEmail(); 
				if(list.contains(mem_email)){
					errorMsgs.add("電子信箱: 此信箱已被註冊!");
				}
				
				
				//電話
				String mem_phone = req.getParameter("mem_phone").trim();
				String phoneReg = "^(09)[(0-9)]{8}$";
				if(mem_phone == null || mem_phone.length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				}else if(!mem_phone.matches(phoneReg)) {
					errorMsgs.add("手機號碼: 格式錯誤");
				}
				

				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_bir(mem_bir);
				memVO.setSex(sex);
				memVO.setMem_addr(mem_addr);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/signup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//發送密碼給會員
			    EmailThread emailThread = new EmailThread(memVO);
			    emailThread.start();
				
				/***************************2.開始新增資料***************************************/
				memVO = memSvc.addMem(mem_name, mem_psw, mem_bir,sex, mem_addr, mem_email, mem_phone);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/mem/signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/signup.jsp");
				failureView.forward(req, res);
			}
		}
        
if ("update_psw".equals(action)) { // 來自update_mem_input_psw.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				HttpSession session = req.getSession();
				MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
				MemService memSvc = new MemService();
				memVOLogin = memSvc.getOneMem(memVOLogin.getMem_id());
				
				//密碼
				String mem_psw = memVOLogin.getMem_psw();
				String pswReg = "^[(a-zA-Z0-9)]{2,10}$";
				
				//原密碼確認
				String mem_psw_check = req.getParameter("mem_psw_check").trim();
				if (mem_psw_check == null || mem_psw_check.length() == 0) {
					errorMsgs.add("原密碼: 請勿空白");
				} else if(!mem_psw.equals(mem_psw_check)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("原密碼 :與原密碼不符");
	            }
				//新密碼
				String mem_psw_new = req.getParameter("mem_psw_new").trim();
				if (mem_psw_new == null || mem_psw_new.length() == 0) {
					errorMsgs.add("新密碼: 請勿空白");
				} else if(!mem_psw_new.matches(pswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("新密碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }
				if(mem_psw.equals(mem_psw_new)) {
					errorMsgs.add("新密碼: 不可與原密碼重複");
				}
				//新密碼確認
				String mem_psw_newcheck = req.getParameter("mem_psw_newcheck").trim();
				if (mem_psw_newcheck == null || mem_psw_newcheck.length() == 0) {
					errorMsgs.add("確認密碼: 請勿空白");
				} else if(!mem_psw_new.equals(mem_psw_newcheck)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("確認密碼 :與新密碼不符");
	            }
				
				System.out.println(mem_psw+"    659");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					 // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/changeMyPassword.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				memVOLogin = memSvc.updateMem(memVOLogin.getMem_name(), mem_psw_new, memVOLogin.getMem_bir(), memVOLogin.getSex(), memVOLogin.getMem_addr(), memVOLogin.getMem_email(), memVOLogin.getMem_phone(), memVOLogin.getMem_absent(), memVOLogin.getCoin(), memVOLogin.getMem_resume(), memVOLogin.getM_reg_date(), memVOLogin.getSel_auth(), memVOLogin.getArt_auth(), memVOLogin.getCom_auth(),memVOLogin.getMem_id());
				
				System.out.println(memVOLogin.getMem_psw() +"  673   修改成功 ????");
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				session.setAttribute("memVOLogin", memVOLogin); // 資料庫update成功後,正確的的memVO物件,存入req
				String url = "/front-end/mem/memDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/changeMyPassword.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.開始刪除資料***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
