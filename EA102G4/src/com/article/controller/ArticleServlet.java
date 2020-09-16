package com.article.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.art_comment.model.Art_CommentVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


@MultipartConfig()
public class ArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 會員
		String action1 = req.getParameter("action1");// 員工
//		String action2 = req.getParameter("action2");// 員工

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne_For_Display test");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("111");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("article_no");
				System.out.println(str);	
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String article_no = null;
				try {
					article_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

				
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				req.setAttribute("article_no", article_no);
//				Set<Art_CommentVO> art_commentVOs = articleSvc.getArt_CommentsByArticle_no(article_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
//				req.setAttribute("art_commentVOs", art_commentVOs);
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//			System.out.println("getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String article_no = new String(req.getParameter("article_no"));
//				System.out.println("+++"+article_no+"+++");

				/*************************** 2.開始查詢資料 ****************************************/
				ArticleService articleService = new ArticleService();
				ArticleVO articleVO = articleService.getOneArticle(article_no);
//				System.out.println("222+++"+article_no+"+++");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/article/update_Article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
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

				String article_no = new String(req.getParameter("article_no").trim());

//				String query_mem_id = req.getParameter("query_mem_id").trim(); // 修改前的會員編號
//				boolean isMenId = !(query_mem_id == null || query_mem_id.trim().length() == 0);
//
//				String query_work_id = req.getParameter("query_work_id").trim(); // 修改前的舊員工編號
//				boolean isWorkId = !(query_work_id == null || query_work_id.trim().length() == 0);
//
//				String mem_id = req.getParameter("mem_id").trim();
//				if (isMenId && (mem_id == null || mem_id.trim().length() == 0)) {
//					errorMsgs.add("會員編號");
//				}
//
//				String work_id = req.getParameter("work_id").trim();
//				if (isWorkId && (work_id == null || work_id.trim().length() == 0)) {
//					errorMsgs.add("員工編號");
//				}

				String title = req.getParameter("title");
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("文章標題");
				} else if (!title.trim().matches(title)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題");
				}

				String art_content = req.getParameter("art_content").trim();
				if (art_content == null) {
					errorMsgs.add("文章內容");
				}

				ArticleVO articleVO = new ArticleVO();
//				if (isWorkId) {
//					articleVO.setWork_id(work_id);
//				}
//				if (isMenId) {
//					articleVO.setMem_id(mem_id);
//				}
				articleVO.setTitle(title);
				articleVO.setArt_content(art_content);
				articleVO.setArticle_no(article_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_Article.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ArticleService articleSvc = new ArticleService();
//				if (isWorkId && isMenId) { // 修改會員及員工編號
				articleVO = articleSvc.updateArticle(title, art_content, article_no);
//				} else if (isWorkId) { // 修改員工編號
//					articleVO = articleSvc.updateArticle1( title, art_content, article_no);
//				} else { // 修改會員編號
//					articleVO = articleSvc.updateArticle(mem_id, title, art_content, link_count, com_count, a_status,
//							article_no);
//				}

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				articleVO = articleSvc.getOneArticle(article_no);
				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				Set<Art_CommentVO> Art_CommentVOs = articleSvc.getArt_CommentsByArticle_no(article_no);
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("Art_CommentVOs", Art_CommentVOs);
				
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_Article.jsp");
				failureView.forward(req, res);
			}
		}

		// ---------------------------員工資料修改---------------

//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//			System.out.println("getOne_For_Update");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				String article_no = new String(req.getParameter("article_no"));
//				System.out.println("22+++" + article_no + "+++");
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				ArticleService articleService = new ArticleService();
//				ArticleVO articleVO = articleService.getOneArticle(article_no);
//				System.out.println("222+++" + article_no + "+++");
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
//				String url = "/article/update_Article1.jsp";
//				System.out.println("22002+++" + article_no + "+++");
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				System.out.println("22002+++");
//				RequestDispatcher failureView = req.getRequestDispatcher("/article/listAllArticle.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			System.out.println("update1 test");
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//
//				String article_no = new String(req.getParameter("article_no").trim());
//				String work_id = req.getParameter("work_id").trim();
//				if (work_id == null || work_id.trim().length() == 0) {
//					errorMsgs.add("work_id");
//				}
//
//				String title = req.getParameter("title");
//				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.add("文章標題");
//				} else if (!title.trim().matches(title)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章標題");
//				}
//
//				String art_content = req.getParameter("art_content").trim();
//				if (art_content == null || art_content.trim().length() == 0) {
//					errorMsgs.add("文章內容");
//				}
//
//				Integer link_count = null;
//				try {
//					link_count = new Integer(req.getParameter("link_count").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("讚數");
//				}
//
//				Integer com_count = null;
//				try {
//					com_count = new Integer(req.getParameter("com_count").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("留言數");
//				}
//
//				String a_status = req.getParameter("a_status").trim();
//				System.out.println(a_status);
//				if (a_status == null || a_status.trim().length() == 0) {
//					errorMsgs.add("文章狀態");
//				}
//
//				String Article_no = new String(req.getParameter("article_no").trim());
//
//				ArticleVO articleVO = new ArticleVO();
//
//				articleVO.setWork_id(work_id);
//				articleVO.setTitle(title);
//				articleVO.setArt_content(art_content);
//				articleVO.setLink_count(link_count);
//				articleVO.setCom_count(com_count);
//				articleVO.setA_status(a_status);
//				articleVO.setArticle_no(article_no);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/article/update_Article1.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				/*************************** 2.開始修改資料 *****************************************/
//				ArticleService articleSvc = new ArticleService();
//				articleVO = articleSvc.updateArticle(work_id, title, art_content, link_count, com_count, a_status,
//						article_no);
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/article/listOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/article/update_Article1.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		System.out.println("action = "+action);

		if ("insert".equals(action)) { // 來自addArticle.jsp的請求 會員文章資料新增

//			System.out.println("insert test");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String mem_id = req.getParameter("mem_id").trim();
				String mem_idReg = "^[(MeM)(0-9)]{3,7}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.put("mem_id", "會員編號請勿空白");
				} else if (!mem_id.trim().matches(mem_id)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mem_id", "會員編號: 只能是英文字母、數字(如:MEM0000001) , 且長度必需在2到10之間");
				}

				String title = req.getParameter("title");
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.put("title", "文章標題: 請勿空白");
				} else if (!title.trim().matches(title)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("title", "文章標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String art_content = req.getParameter("art_content").trim();
				if (art_content == null || art_content.trim().length() == 0) {
					errorMsgs.put("art_count", "文章內容請勿空白");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.addArticle(mem_id, title, art_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// -----------員工資料新增判斷---------------------------------------------

		if ("insert1".equals(action1)) { // 來自addArticleWork.jsp的請求 員工文章資料新增

//			System.out.println("insert test");

			Map<String, String> errorMsgs1 = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs1", errorMsgs1);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String work_id = req.getParameter("work_id").trim();
				String work_idReg = "^[(WORK)(0-9)]{4,6}$";
				if (work_id == null || work_id.trim().length() == 0) {
					errorMsgs1.put("work_id", "員工編號請勿空白");
				} else if (!work_id.trim().matches(work_id)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs1.put("work_id", "會員編號: 只能是英文字母、數字(如WORK000001) 且長度必需在2到10之間");
				}

				String title = req.getParameter("title");
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs1.put("title", "文章標題: 請勿空白");
				} else if (!title.trim().matches(title)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs1.put("title", "文章標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String art_content = req.getParameter("art_content").trim();
				if (art_content == null || art_content.trim().length() == 0) {
					errorMsgs1.put("art_count", "文章內容請勿空白");
				}

				if (!errorMsgs1.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticleWork.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.addArticle1(work_id, title, art_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs1.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticleWork.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("delete_Article".equals(action)) { // 來自/dept/listAllDept.jsp的請求
			
			System.out.println("action="+action);

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String article_no = new String(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_no);
				
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
			
		
	}
}