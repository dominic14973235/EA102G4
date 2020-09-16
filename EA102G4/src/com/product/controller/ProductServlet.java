package com.product.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product.model.ProductJDBCDAO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_photo.model.PhotoService;
import com.product_photo.model.PhotoVO;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("searchProduct".equals(action)) {
			ProductService productService = new ProductService();
			List<ProductVO> list = new ArrayList<ProductVO>();
			list = productService.getAll();
			String searchStr = null;
			try {
				searchStr = req.getParameter("searchStr");

				String[] searchStrSplit = searchStr.split(" ");

				List<String> searchStrSplitList = new ArrayList<String>();
				for (String s : searchStrSplit) {
					if (s.trim().length() != 0) {
						searchStrSplitList.add(s);
					}
				}
				System.out.println(searchStrSplitList);
				List<ProductVO> collect = null;
				List<ProductVO> collect2 =  new ArrayList<ProductVO>();
				for (String s : searchStrSplitList) {
					collect = list.stream().filter(productVO -> productVO.getpName().indexOf(s) > -1)
							.collect(Collectors.toList());
					collect2.addAll(collect);
				}
				System.out.println(collect2);
				List<ProductVO> collect3 = new ArrayList<ProductVO>(new HashSet(collect2));

				String url = null;
				if (collect != null) {
					url = "/front-end/product/search-product.jsp";
					req.setAttribute("collect", collect3); // 資料庫取出的empVO物件,存入req
					req.setAttribute("searchStr", searchStr);
					System.out.println("collect != null");
				} else {
					url = "/front-end/product/search-product-none.jsp";
					req.setAttribute("searchStr", searchStr);
					System.out.println("else");
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("getOneForDisplay".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String productNo = req.getParameter("productNo");
				System.out.println("getOneForDisplay-productNo : " + productNo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
				
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/product/product-detail-mine.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		///////////
		if ("getOneForDisplayRelated".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String productNo = req.getParameter("productNoRelated");
				System.out.println("getOneForDisplayRelated-productNo : " + productNo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/product/product-detail-mine.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		///////
		if ("get-one-product-for-update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String productNo = req.getParameter("productNo");
				System.out.println("************" + productNo + "*********************");

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/product/update-product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product/list-product-by-memId.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String productNo = req.getParameter("productNo");
//				System.out.println("productNo = " + productNo);
//				String pName = req.getParameter("pName");
//				System.out.println("pName = " + pName);
////				String pNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (pName == null || pName.trim().length() == 0) {
//					errorMsgs.add("商品名稱: 請勿空白");
//				}
//				Double pPrice = null;
//				try {
//					pPrice = new Double(req.getParameter("pPrice").trim());
//				} catch (NumberFormatException e) {
//					pPrice = 0.0;
//					errorMsgs.add("價格請輸入數字.");
//				}
//
//				Double pStock = null;
//				try {
//					pStock = new Double(req.getParameter("pStock").trim());
//				} catch (NumberFormatException e) {
//					pStock = 0.0;
//					errorMsgs.add("庫存請輸入數字.");
//				}
//				String categoryNo = req.getParameter("categoryNo");
//				if (categoryNo == null || categoryNo.trim().length() == 0) {
//					errorMsgs.add("請選擇商品分類");
//				}
//				String memId = "MEM0000001";
//				String pDetail = req.getParameter("pDetail");
//				ProductVO productVO = new ProductVO();
//				productVO.setpName(pName);
//				productVO.setMemId(memId);
//				productVO.setpPrice(pPrice);
//				productVO.setpStock(pStock);
//				productVO.setCategoryNo(categoryNo);
//				productVO.setpDetail(pDetail);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始修改資料 *****************************************/
//				ProductService productService = new ProductService();
//				productVO = productService.updateProduct(productNo, pName, memId, pPrice, pStock, categoryNo, pDetail);
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("productVO", productVO);
////				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/product/listAllProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("update".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				try {
					String ifIamgeExist = req.getParameter("ifIamgeExist");
					System.out.println("ifIamgeExist : " + ifIamgeExist);
					if (ifIamgeExist != null) {
						errorMsgs.add("商品圖片: 請上傳至少一張圖片");
					}
				} catch (Exception e) {
					System.out.println("這是我要的，ifIamgeExist因為有圖片而消失了");
				}

				String productNo = req.getParameter("productNo");

				String pName = req.getParameter("pName");
				if (pName == null || pName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}

				Double pStock = null;
				try {
					pStock = new Double(req.getParameter("pStock").trim());
				} catch (NumberFormatException e) {
					pStock = 0.0;
					errorMsgs.add("數量: 請輸入數字");
				}
				Double pPrice = null;
				try {
					pPrice = new Double(req.getParameter("pPrice").trim());
				} catch (NumberFormatException e) {
					pPrice = 0.0;
					errorMsgs.add("價格: 請輸入數字");
				}
				String categoryNo = req.getParameter("categoryNo");
//				if (categoryNo == null || categoryNo.trim().length() == 0) {
//					errorMsgs2.add("請選擇商品分類");
//				}
				String[] poPaymentArray = req.getParameterValues("poPayment");
				System.out.println("poPaymentArray : " + poPaymentArray);
				String poPayment = null;
				try {

					for (int i = 0; i < poPaymentArray.length; i++) {
						if (poPayment == null)
							poPayment = poPaymentArray[i];
						else
							poPayment = poPayment + poPaymentArray[i];
					}
					System.out.println("poPayment : " + poPayment);

				} catch (Exception e) {
					errorMsgs.add("付款方式: 請選擇至少一種付款方式");
				}

				String[] poDeliveryArray = req.getParameterValues("poDelivery");
				System.out.println("poDeliveryArray : " + poDeliveryArray);
				String poDelivery = null;
				try {
					for (int i = 0; i < poDeliveryArray.length; i++) {
						if (poDelivery == null)
							poDelivery = poDeliveryArray[i];
						else
							poDelivery = poDelivery + poDeliveryArray[i];
					}
					System.out.println("poDelivery : " + poDelivery);
				} catch (Exception e) {
					errorMsgs.add("運送方式: 請選擇至少一種運送方式");
				}
				////////////////////////// insert_photo////////////////////////////////

				Collection<Part> parts = req.getParts();
				List<PhotoVO> list = new ArrayList<PhotoVO>();
				byte[] pPhoto = null;
				for (Part part : parts) {
					String name = part.getName();
					if ("p_photo".equals(name)) {
						try {
							System.out.println("YESYESYESYES");
							InputStream in = part.getInputStream();

							byte[] buf = new byte[in.available()];
							in.read(buf);
							pPhoto = buf;
							in.close();
							PhotoVO vo = new PhotoVO();
							vo.setpPhoto(pPhoto);

							list.add(vo);
						} catch (IOException e) {
							e.printStackTrace();
							errorMsgs.add("商品圖片: 上傳圖片發生異常");
						}
					} else {
						System.out.println("NONONONO");
					}

				}
				////////////////////////// insert_photo////////////////////////////////

				String memId = "MEM0000001";
				String pDetail = req.getParameter("pDetail");
				ProductVO productVO = new ProductVO();
				productVO.setpName(pName);
				productVO.setMemId(memId);
				productVO.setpPrice(pPrice);
				productVO.setpStock(pStock);
				productVO.setCategoryNo(categoryNo);
				productVO.setpDetail(pDetail);
				productVO.setPoPayment(poPayment);
				productVO.setPoDelivery(poDelivery);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/update-product.jsp");
					failureView.forward(req, res);
					System.out.println("有問題");
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.updateProduct(productNo, pName, pPrice, pStock, categoryNo, pDetail,
						poPayment, poDelivery, list);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/front-end/product/list-product-by-memId.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/add-product.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("updateRating".equals(action)) { // 來自addEmp.jsp的請求
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
					Double pRating = new Double(req.getParameter("pRating"));
					String productNo = req.getParameter("productNo");
					ProductVO productVO = new ProductVO();
					productVO.setProductNo(productNo);
				/*************************** 2.開始新增資料 ***************************************/
					ProductService productService = new ProductService();
					productService.updateRating(pRating, productVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				try {
					String ifIamgeExist = req.getParameter("ifIamgeExist");
					System.out.println("ifIamgeExist : " + ifIamgeExist);
					if (ifIamgeExist != null) {
						errorMsgs.add("商品圖片: 請上傳至少一張圖片");
					}
				} catch (Exception e) {
					System.out.println("這是我要的，ifIamgeExist因為有圖片而消失了");
				}

				String pName = req.getParameter("pName");
				if (pName == null || pName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}

				Double pStock = null;
				try {
					pStock = new Double(req.getParameter("pStock").trim());
				} catch (NumberFormatException e) {
					pStock = 0.0;
					errorMsgs.add("數量: 請輸入數字");
				}
				Double pPrice = null;
				try {
					pPrice = new Double(req.getParameter("pPrice").trim());
				} catch (NumberFormatException e) {
					pPrice = 0.0;
					errorMsgs.add("價格: 請輸入數字");
				}
				String categoryNo = req.getParameter("categoryNo");
//				if (categoryNo == null || categoryNo.trim().length() == 0) {
//					errorMsgs2.add("請選擇商品分類");
//				}
				String[] poPaymentArray = req.getParameterValues("poPayment");
//				System.out.println("poPaymentArray : " + poPaymentArray);
				String poPayment = null;
				try {

					for (int i = 0; i < poPaymentArray.length; i++) {
						if (poPayment == null)
							poPayment = poPaymentArray[i];
						else
							poPayment = poPayment + poPaymentArray[i];
					}
//					System.out.println("poPayment : " + poPayment);

				} catch (Exception e) {
					errorMsgs.add("付款方式: 請選擇至少一種付款方式");
				}

				String[] poDeliveryArray = req.getParameterValues("poDelivery");
//				System.out.println("poDeliveryArray : " + poDeliveryArray);
				String poDelivery = null;
				try {
					for (int i = 0; i < poDeliveryArray.length; i++) {
						if (poDelivery == null)
							poDelivery = poDeliveryArray[i];
						else
							poDelivery = poDelivery + poDeliveryArray[i];
					}
//					System.out.println("poDelivery : " + poDelivery);
				} catch (Exception e) {
					errorMsgs.add("運送方式: 請選擇至少一種運送方式");
				}
				////////////////////////// insert_photo////////////////////////////////

				Collection<Part> parts = req.getParts();
				List<PhotoVO> list = new ArrayList<PhotoVO>();
				byte[] pPhoto = null;
				for (Part part : parts) {
					String name = part.getName();
					if ("p_photo".equals(name)) {
						try {
//							System.out.println("YESYESYESYES");
							InputStream in = part.getInputStream();

							byte[] buf = new byte[in.available()];
							in.read(buf);
							pPhoto = buf;
							in.close();
							PhotoVO vo = new PhotoVO();
							vo.setpPhoto(pPhoto);

							list.add(vo);
						} catch (IOException e) {
							e.printStackTrace();
							errorMsgs.add("商品圖片: 上傳圖片發生異常");
						}
					} else {
//						System.out.println("NONONONO");
					}

				}
				////////////////////////// insert_photo////////////////////////////////

//				String memId = "MEM0000001";
				String memId = req.getParameter("memId");

				String pDetail = req.getParameter("pDetail");
				ProductVO productVO = new ProductVO();
				productVO.setpName(pName);
				productVO.setMemId(memId);
				productVO.setpPrice(pPrice);
				productVO.setpStock(pStock);
				productVO.setCategoryNo(categoryNo);
				productVO.setpDetail(pDetail);
				productVO.setPoPayment(poPayment);
				productVO.setPoDelivery(poDelivery);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVOError", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/add-product.jsp");
					failureView.forward(req, res);
					System.out.println("有問題");
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.addProductAndPhoto(pName, memId, pPrice, pStock, categoryNo, pDetail, list,
						poPayment, poDelivery);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/front-end/product/list-product-by-memId.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/add-product.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String productNo = req.getParameter("productNo");
				System.out.println("productNo : " + productNo);

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(productNo);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/product/list-product-by-memId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	if ("deleteBack".equals(action)) { // 來自listAllEmp.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String productNo = req.getParameter("productNo");
				System.out.println("productNo : " + productNo);
				
				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(productNo);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
