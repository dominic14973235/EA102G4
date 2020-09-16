package com.classType.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.classType.model.*;

public class ClassTypeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/***************************************
		 * DISPLAY ClassType
		 ***************************************/

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("c_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String c_type_no = null;
				try {
					c_type_no = str.toUpperCase();
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(c_type_no);
				if (classTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("classTypeVO", classTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "listOneClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************************
		 * UPDATE ClassType
		 ***************************************/

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String c_type_no = req.getParameter("c_type_no");
				/*************************** 2.開始查詢資料 ****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(c_type_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("classTypeVO", classTypeVO); // 資料庫取出的empVO物件,存入req
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				String url = "listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllClassType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String c_type_no = req.getParameter("c_type_no").trim();

				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.add("課程名稱: 請勿空白");
				} else if (!t_name.trim().matches(t_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setC_type_no(c_type_no);
				classTypeVO.setT_name(t_name);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classTypeVO", classTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeVO = classTypeSvc.updateClassType(c_type_no, t_name);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("classTypeVO", classTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/updateClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************************
		 * INSERT ClassType
		 ***************************************/
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!t_name.trim().matches(t_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setT_name(t_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classTypeVO", classTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("addClassType.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeVO = classTypeSvc.addClassType(t_name);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "listAllClassType.jsp";
				req.setAttribute("classTypeVO", classTypeVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************************
		 * DELETE ClassType
		 ***************************************/

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String c_type_no = req.getParameter("c_type_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ClassTypeService ClassTypeSvc = new ClassTypeService();
				ClassTypeSvc.deleteClassType(c_type_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllClassType.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
