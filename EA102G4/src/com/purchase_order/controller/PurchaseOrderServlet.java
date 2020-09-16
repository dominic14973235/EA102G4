package com.purchase_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.purchase_order.model.PurchaseOrderService;
import com.purchase_order.model.PurchaseOrderVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import ecpay.payment.integration.domain.InvoiceObj;
import tool.MailService;

public class PurchaseOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		/** green-pay-start **/
//		綠界
		if ("green-pay".equals(action)) {
			String poNo = req.getParameter("poNo");
			String member_id = req.getParameter("member_id");
			double amountD = new Double(req.getParameter("amountStr"));
			int i = (int) amountD;
			String amount = String.valueOf(i);
			HttpSession session = req.getSession();
			String pName = req.getParameter("pName");

			InvoiceObj invoice = null;
			System.out.println(pName);
			System.out.println(member_id);
			System.out.println(amount);
			System.out.println(new String(req.getRequestURL()));
			System.out.println("-------new String(req.getRequestURL())--------");
			AllInOne allInOne = new AllInOne("");
			AioCheckOutOneTime obj = new AioCheckOutOneTime();
			Date date = new Date();
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			System.out.println(sdf.format(date));

			obj.setMerchantTradeNo("P0" + date.getTime());
			obj.setMerchantTradeDate(sdf.format(date));
			obj.setTotalAmount(amount);
			obj.setTradeDesc("test Description");
			obj.setItemName(pName);
			obj.setReturnURL(new String(req.getRequestURL()));
			obj.setNeedExtraPaidInfo("N");
			obj.setRedeem("N");
			obj.setClientBackURL(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
					+ "/front-end/purchase-order/list-one-order-by-pono-buyer.jsp?poNo=" + poNo + "&showSuccess=true");
			obj.setChooseSubPayment("Credit");
			res.setContentType("text/html; charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			String form = allInOne.aioCheckOut(obj, invoice);
			out.println("<HTML>");
			out.println("<HEAD><TITLE></TITLE></HEAD>");
			out.println("<BODY>");
			out.print(form);
			out.println("</BODY></HTML>");

		}
		/** green-pay-end **/

		/** get-one-order-for-display-seller-start **/
		if ("get-one-order-for-display-seller".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String poNo = req.getParameter("poNo");
				System.out.println("************" + poNo + "**********getOneForUpdate***********");

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/purchase-order/list-one-order-by-pono-seller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** get-one-order-for-display-seller-end **/

		/** get-one-order-for-display-buyer-start **/
		if ("get-one-order-for-display-buyer".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String poNo = req.getParameter("poNo");
				System.out.println("************" + poNo + "**********getOneForUpdate***********");

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/purchase-order/list-one-order-by-pono-buyer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** get-one-order-for-display-buyer-end **/

		/** getOneForUpdateStatusSeller-start **/
		if ("getOneForUpdateStatusSeller".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String poNo = req.getParameter("poNo");
				String poStatus = req.getParameter("changePoStatus");
				String listAllOrListOne = req.getParameter("listAllOrListOne");
				System.out.println("poNo: " + poNo);
				System.out.println("poStatus: " + poStatus);
				System.out.println("listAllOrListOne: " + listAllOrListOne);

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
				purchaseOrderService.updateOrderStatus(purchaseOrderVO, poStatus);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());

//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = null;
				if (listAllOrListOne.equals("one")) {
					url = "/front-end/purchase-order/list-one-order-by-pono-seller.jsp";
				} else {
					url = "/front-end/purchase-order/list-order-by-memId-seller.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** getOneForUpdateStatusSeller-end **/

		/** getOneForUpdateStatusBuyer-start **/
		if ("getOneForUpdateStatusBuyer".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String poNo = req.getParameter("poNo");
				String poStatus = req.getParameter("changePoStatus");
				String listAllOrListOne = req.getParameter("listAllOrListOne");
				System.out.println("poNo : " + poNo);
				System.out.println("poStatus : " + poStatus);
				System.out.println("listAllOrListOne : " + listAllOrListOne);

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
				purchaseOrderService.updateOrderStatus(purchaseOrderVO, poStatus);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());

//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = null;
				if (listAllOrListOne.equals("one")) {
					url = "/front-end/purchase-order/list-one-order-by-pono-buyer.jsp";
				} else {
					url = "/front-end/purchase-order/list-order-by-memId-buyer.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** getOneForUpdateStatusBuyer-end **/

		/****/
		if ("getOneForUpdateStatusBack".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String poNo = req.getParameter("poNo");
				String poStatus = req.getParameter("changePoStatus");
				System.out.println("poNo : " + poNo);
				System.out.println("poStatus : " + poStatus);

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
				purchaseOrderService.updateOrderStatus(purchaseOrderVO, poStatus);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/****/

		if ("getOneForUpdate".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String poNo = req.getParameter("poNo");
				System.out.println("************" + poNo + "**********getOneForUpdate***********");

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);

				System.out.println("getOneForUpdate的資料" + purchaseOrderVO.getPoDelivery());
				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/purchase-order/update-order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/update-order.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			ProductVO productVO = new ProductVO();
			PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				////
				String productNo = req.getParameter("productNo");

				String poPayment = req.getParameter("poPayment");
				System.out.println("poPayment : " + poPayment);

				if (poPayment.equals("請選擇付款方式")) {
					errorMsgs.add("請選擇付款方式");
				}
				String memId = req.getParameter("memId");
				String buyerName = req.getParameter("buyerName");
				if (buyerName == null || buyerName.trim().length() == 0) {
					errorMsgs.add("訂購人姓名: 請勿空白");
				}
				String buyerPhone = req.getParameter("buyerPhone");
				if (buyerPhone == null || buyerPhone.trim().length() == 0) {
					errorMsgs.add("訂購人電話: 請勿空白");
				}

				String poDelivery = req.getParameter("poDelivery");

				if (poDelivery.equals("請選擇寄送方式")) {
					errorMsgs.add("請選擇寄送方式");
				}

				String deliveryAddress = req.getParameter("deliveryAddress");
				System.out.println("deliveryAddress :　" + deliveryAddress);

				try {

					if (poDelivery.indexOf("10") != -1) {
						if (deliveryAddress.trim().length() == 0) {
							errorMsgs.add("請輸入地址");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Double pStock = Double.parseDouble(req.getParameter("pStock"));
				System.out.println("庫存顯示中! : " + pStock);
				Double quantity = Double.parseDouble(req.getParameter("qty"));
				System.out.println("quantity : " + quantity);
				Double afterBuyStock = pStock - quantity;

				String poStatus = "10"; // 訂單剛下預設為10-待付款 如果為取貨付款則是直接30-待出貨
				String poNo = req.getParameter("poNo");

				if (poPayment.equals("30")) {
					poStatus = "30";
				}

				String purchaseDetail = null;

				purchaseDetail = req.getParameter("purchaseDetail");
				System.out.println("purchaseDetail : " + purchaseDetail);

				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoPayment(poPayment);
				purchaseOrderVO.setPoDelivery(poDelivery);
				purchaseOrderVO.setDeliveryAddress(deliveryAddress);
				purchaseOrderVO.setQuantity(quantity);
				purchaseOrderVO.setProductNo(productNo);
				purchaseOrderVO.setPoStatus(poStatus);
				purchaseOrderVO.setPurchaseDetail(purchaseDetail);
				purchaseOrderVO.setBuyerName(buyerName);
				purchaseOrderVO.setBuyerPhone(buyerPhone);
				purchaseOrderVO.setPoNo(poNo);
				System.out.println(poPayment);
				System.out.println(poDelivery);
				System.out.println(deliveryAddress);
				System.out.println(quantity);
				System.out.println(productNo);
				System.out.println(purchaseDetail);

				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();
				if (!errorMsgs.isEmpty()) {
					productVO = productService.getOneProduct(productNo);
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("purchaseOrderVO", purchaseOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/add-order.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				purchaseOrderService = new PurchaseOrderService();
				productService = new ProductService();

				productVO = productService.afterBuyStock(productNo, afterBuyStock);// 處理訂單庫存 總數-購買數量
				purchaseOrderVO = purchaseOrderService.updateOrder(purchaseDetail, poPayment, buyerName, buyerPhone,
						poDelivery, deliveryAddress, quantity, poStatus, poNo);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/front-end/purchase-order/list-order-by-memId-buyer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/update_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOneForOrder".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				String productNo = req.getParameter("productNo");
				System.out.println("************" + productNo + "*********************");

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", productVO);

				String url = "/front-end/purchase-order/add-order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 addOrder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			ProductVO productVO = new ProductVO();
			PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				////

				String productNo = req.getParameter("productNo");

				String poPayment = req.getParameter("poPayment");
				System.out.println("poPayment : " + poPayment);

				if (poPayment.equals("請選擇付款方式")) {
					errorMsgs.add("請選擇付款方式");
				}
				String memId = req.getParameter("memId");
				System.out.println("memId: " + memId);

				String buyerName = req.getParameter("buyerName");
				if (buyerName == null || buyerName.trim().length() == 0) {
					errorMsgs.add("訂購人姓名: 請勿空白");
				}
				String buyerPhone = req.getParameter("buyerPhone");
				if (buyerPhone == null || buyerPhone.trim().length() == 0) {
					errorMsgs.add("訂購人電話: 請勿空白");
				}

				String poDelivery = req.getParameter("poDelivery");

				if (poDelivery.equals("請選擇寄送方式")) {
					errorMsgs.add("請選擇寄送方式");
				}

				String deliveryAddress = req.getParameter("deliveryAddress");
				System.out.println("deliveryAddress :　" + deliveryAddress);

				try {

					if (poDelivery.indexOf("10") != -1) {
						if (deliveryAddress.trim().length() == 0) {
							errorMsgs.add("請輸入地址");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Double pStock = Double.parseDouble(req.getParameter("pStock"));
				System.out.println("庫存顯示中! : " + pStock);
				Double quantity = Double.parseDouble(req.getParameter("qty"));
				System.out.println("quantity : " + quantity);
				Double afterBuyStock = pStock - quantity;

				String poStatus = "10"; // 訂單剛下預設為10-待付款 如果為取貨付款則是直接30-待出貨
				if (poPayment.equals("30")) {
					poStatus = "30";
				}

				String purchaseDetail = null;

				purchaseDetail = req.getParameter("purchaseDetail");
				System.out.println("purchaseDetail : " + purchaseDetail);
				String pName = req.getParameter("pName");
				////
//				productVO = new ProductVO();
//				productVO.setpStock(afterBuyStock);
//				productVO.setProductNo(productNo);

				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoPayment(poPayment);
				purchaseOrderVO.setPoDelivery(poDelivery);
				purchaseOrderVO.setDeliveryAddress(deliveryAddress);
				purchaseOrderVO.setQuantity(quantity);
				purchaseOrderVO.setProductNo(productNo);
				purchaseOrderVO.setPoStatus(poStatus);
				purchaseOrderVO.setPurchaseDetail(purchaseDetail);
				purchaseOrderVO.setBuyerName(buyerName);
				purchaseOrderVO.setBuyerPhone(buyerPhone);
//				purchaseOrderVO.setCreditCardNumber(creditCardNumber);
//				purchaseOrderVO.setLastThreeNumber(lastThreeNumber);

				// 測試區域//測試區域//測試區域//測試區域//測試區域//測試區域
				System.out.println(poPayment);
				System.out.println(poDelivery);
				System.out.println(deliveryAddress);
				System.out.println(quantity);
				System.out.println(productNo);
				System.out.println(purchaseDetail);
//				System.out.println(creditCardNumber);
//				System.out.println(lastThreeNumber);
				// 測試區域//測試區域//測試區域//測試區域//測試區域//測試區域

				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();
				// Send the use back to the form, if there were errors

				// 下訂單-寄信通知
//				MailService mailservice = new MailService();
//				String subject = "INGYM商城-商品:" + pName + "已售出";
//				String messageText ="賣家編號:" +  memIdSeller + "商品:" + pName + "已售出!" + "買家姓名:" + buyerName + "，請確認出貨。";
//				mailservice.sendMail("wedy5678@gmail.com", subject, messageText);

				if (!errorMsgs.isEmpty()) {
//					purchaseOrderVO = purchaseOrderService.updateQuantity(quantity);
//					req.setAttribute("purchaseOrderVO", purchaseOrderVO);
					productVO = productService.getOneProduct(productNo);
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("purchaseOrderVO", purchaseOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/add-order.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				purchaseOrderService = new PurchaseOrderService();
				productService = new ProductService();

				// 測試區域//測試區域//測試區域//測試區域//測試區域//測試區域
				System.out.println("商品編號: " + productNo);
				System.out.println("購買後庫存: " + afterBuyStock);

				// 測試區域//測試區域//測試區域//測試區域//測試區域//測試區域
				productVO = productService.afterBuyStock(productNo, afterBuyStock);// 處理訂單庫存 總數-購買數量
				purchaseOrderVO = purchaseOrderService.addOrder(poPayment, poDelivery, deliveryAddress, quantity,
						productNo, poStatus, purchaseDetail, buyerName, buyerPhone, memId);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/front-end/purchase-order/list-order-by-memId-buyer.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/add-order.jsp");
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
				String poNo = req.getParameter("poNo");
				System.out.println(poNo);
				/*************************** 2.開始刪除資料 ***************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				purchaseOrderService.deleteProduct(poNo);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/purchase_order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("sendEmail".equals(action)) {
			try {
				MemService memService = new MemService();

				String buyerName = null;
				try {
					buyerName = req.getParameter("buyerName");
				} catch (Exception e) {
					System.out.println("buyerName為null");
				}
				String pName = req.getParameter("pName");
				String memAcc = null;

				String memIdBuyer = null;
				try {
					memIdBuyer = req.getParameter("memIdBuyer");
					MemVO memVO = memService.getOneMem(memIdBuyer);
					memAcc = memVO.getMem_email();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("PurchaseOrderServlet.java:684");
				}

				String memIdSeller = null;
				try {
					memIdSeller = req.getParameter("memIdSeller");
					MemVO memVO = memService.getOneMem(memIdSeller);
					memAcc = memVO.getMem_email();
				} catch (Exception e) {
					System.out.println("memIdSeller為null");
				}

				System.out.println("sendEmail已執行");

				MailService mailservice = new MailService();
				if (buyerName == null && memIdSeller == null && memIdBuyer != null) {
					System.out.println("商品已寄出-信傳送至買家帳號: " + memAcc);
					String subject = "INGYM商城-商品:" + pName + "已出貨";
					String messageText_selled = "INGYM商城-商品:" + pName + "已出貨" + "\n" + "煩請留意取貨";
					mailservice.sendMail(memAcc, subject, messageText_selled);
				} else {
					String subject = "INGYM商城-商品:" + pName + "已售出";
					String messageText_selled = "賣家帳號:" + memAcc + "\n" + "商品:" + pName + " 已售出" + "\n" + "買家姓名:"
							+ buyerName + "\n" + "請確認出貨";
					System.out.println("信件正在傳送至: " + memAcc);
					mailservice.sendMail(memAcc, subject, messageText_selled);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
