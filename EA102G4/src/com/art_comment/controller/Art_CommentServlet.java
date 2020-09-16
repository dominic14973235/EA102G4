package com.art_comment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.art_comment.model.*;
import com.art_comment.model.Art_CommentVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;




@MultipartConfig()
public class Art_CommentServlet extends HttpServlet {

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
				String str = req.getParameter("com_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String com_no = null;
				try {
					com_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				Art_CommentVO art_commentVO = art_commentSvc.getOneArt_Comment(com_no);
				if (art_commentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("art_commentVO", art_commentVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/art_comment/listOneArt_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
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
				String com_no = new String(req.getParameter("com_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Art_CommentService artcommentService = new Art_CommentService();
				Art_CommentVO art_commentVO = artcommentService.getOneArt_Comment(com_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("art_commentVO", art_commentVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/art_comment/update_Art_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/listAllArt_Comment.jsp");
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

				String com_no = new String(req.getParameter("com_no").trim());
				System.out.println(com_no+"142");
														
				String mes_content = req.getParameter("mes_content");
				System.out.println(mes_content+"145");
				if (mes_content == null) {
					errorMsgs.add("留言內容修改");
				}
				
				Art_CommentVO art_commentVO = new Art_CommentVO();

				art_commentVO.setMes_content(mes_content);			
				art_commentVO.setCom_no(com_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("art_commentVO", art_commentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/update_Art_Comment.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				art_commentVO = art_commentSvc.updateArt_Comment( mes_content, com_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				art_commentVO = art_commentSvc.getOneArt_Comment(com_no);
				req.setAttribute("art_commentVO", art_commentVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/art_comment/listOneArt_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/update_Art_Comment.jsp");
				failureView.forward(req, res);
			}
		}
		


		if ("insert".equals(action)) { // 來自addArticle.jsp的請求 會員留言資料新增

			System.out.println("insert test");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String article_no = req.getParameter("article_no").trim();
				String article_noReg = "^[(ART)(0-9)]{3,3}$";
				if (article_no == null || article_no.trim().length() == 0) {
					errorMsgs.put("article_no", "文章編號請勿空白");
				}else if (!article_no.trim().matches(article_no)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("article_no", "文章編號: 只能是英文字母、數字(如:ART001) , 且長度必需在3到6之間");
				}
				
				String mem_id = req.getParameter("mem_id").trim();
				String mem_idReg = "^[(MeM)(0-9)]{3,7}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.put("mem_id", "會員編號請勿空白");
				}else if (!mem_id.trim().matches(mem_id)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mem_id", "會員編號: 只能是英文字母、數字(如:MEM0000001) , 且長度必需在2到10之間");
				}
				

				String mes_content = req.getParameter("mes_content").trim();
				if (mes_content == null) {
					errorMsgs.put("mes_content", "留言內容請勿空白");
				}
				
				String com_status = req.getParameter("com_status").trim();
				if (com_status == null || com_status.trim().length() == 0) {
					errorMsgs.put("com_status", "留言狀態 : 請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				System.out.println("Servlet");
				System.out.println("article_no = "+article_no);
				System.out.println("mem_id = "+mem_id);
				System.out.println("mes_content = "+mes_content);
				System.out.println("com_status = "+com_status);

				/*************************** 2.開始新增資料 ***************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				art_commentSvc.addArt_Comment(article_no, mem_id, mes_content, com_status);
				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = new ArticleService().getOneArticle(article_no);
				
				Set<Art_CommentVO> Art_CommentVOs = articleSvc.getArt_CommentsByArticle_no(article_no);
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("Art_CommentVOs", Art_CommentVOs);
				
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			System.out.println("action="+action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String com_no = new String(req.getParameter("com_no"));
				System.out.println("com_no="+com_no);
				/***************************2.開始刪除資料***************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				art_commentSvc.deleteArt_Comment(com_no);
				System.out.println("com_no12="+com_no);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/article/listOneArticle.jsp";
				System.out.println("com_nowwwww="+com_no);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art_comment/listAllArt_Comment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}