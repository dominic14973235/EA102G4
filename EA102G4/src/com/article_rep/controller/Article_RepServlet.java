package com.article_rep.controller;

import java.io.*;
import java.util.*;


import javax.servlet.*;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.article_rep.model.*;



@MultipartConfig()
public class Article_RepServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 會員
	

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne_For_Display test");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("art_rep_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String art_rep_no = null;
				try {
					art_rep_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Article_RepService article_repSvc = new Article_RepService();
				Article_RepVO article_repVO = article_repSvc.getOneArticle_Rep(art_rep_no);
				if (article_repVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("article_repVO", article_repVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/article_rep/listOneArticle_Rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			System.out.println("getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String art_rep_no = new String(req.getParameter("art_rep_no"));
				System.out.println("01getOne_For_Update");
//				System.out.println("+++"+article_no+"+++");

				/*************************** 2.開始查詢資料 ****************************************/
				Article_RepService article_repService = new Article_RepService();
				Article_RepVO article_repVO = article_repService.getOneArticle_Rep(art_rep_no);
				System.out.println("222+++"+art_rep_no+"+++");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("article_repVO", article_repVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/article_rep/update_Article_Rep.jsp";
				System.out.println("099getOne_For_Update");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				System.out.println("199getOne_For_Update");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/listAllArticle_Rep.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("update test");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String art_rep_no = new String(req.getParameter("art_rep_no").trim());
		
				String rep_content = req.getParameter("rep_content");
				if (rep_content == null) {
					errorMsgs.add("文章內容");
				}
				
				String com_status = new String(req.getParameter("com_status"));

				Article_RepVO article_repVO = new Article_RepVO();

				article_repVO.setRep_content(rep_content);			
				article_repVO.setArt_rep_no(art_rep_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("article_repVO", article_repVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/update_Article_Rep.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Article_RepService article_repSvc = new Article_RepService();
				article_repVO = article_repSvc.updateArticle_Rep( rep_content, art_rep_no,com_status);
				System.out.println("01");

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				article_repVO = article_repSvc.getOneArticle_Rep(art_rep_no);
				req.setAttribute("article_repVO", article_repVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/article_rep/listOneArticle_Rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/update_Article_Rep.jsp");
				failureView.forward(req, res);
			}
		}
		


		if ("insert".equals(action)) { // 來自addArticle.jsp的請求 會員文章資料新增

			System.out.println("insert test");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_id = req.getParameter("mem_id").trim();
				System.out.println("mem_id="+mem_id);
				String mem_idReg = "^[(MeM)(0-9)]{3,7}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.put("mem_id", "會員編號請勿空白");
				}else if (!mem_id.trim().matches(mem_id)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mem_id", "會員編號: 只能是英文字母、數字(如:MEM0000001) , 且長度必需在2到10之間");
				}
				
				
				String article_no = req.getParameter("article_no").trim();
				String article_noReg = "^[(ART)(0-9)]{3,3}$";
				if (article_no == null || article_no.trim().length() == 0) {
					errorMsgs.put("article_no", "文章編號請勿空白");
				}else if (!article_no.trim().matches(article_no)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("article_no", "文章編號: 只能是英文字母、數字(如:ART001) , 且長度必需在3到6之間");
				}
				
				
				

				String rep_content = req.getParameter("rep_content").trim();
				if (rep_content == null) {
					errorMsgs.put("rep_content", "檢舉內容請勿空白");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/addArticle_Rep.jsp");
					failureView.forward(req, res);
					
				}
				
				System.out.println("Servlet");
				System.out.println("article_no = "+article_no);
				System.out.println("mem_id = "+mem_id);
				System.out.println("rep_content = "+rep_content);

				/*************************** 2.開始新增資料 ***************************************/
				Article_RepService article_repSvc = new Article_RepService();
				article_repSvc.addArticle_Rep(mem_id, article_no, rep_content);
				System.out.println(article_repSvc);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url ="/front-end/article_rep/listAllArticle_Rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/addArticle_Rep.jsp");
				failureView.forward(req, res);
			}
		}
	}
}