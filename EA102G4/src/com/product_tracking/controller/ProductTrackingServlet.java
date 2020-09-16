package com.product_tracking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_photo.model.PhotoService;
import com.product_photo.model.PhotoVO;
import com.product_tracking.model.ProductTrackingService;
import com.product_tracking.model.ProductTrackingVO;
import com.purchase_order.model.PurchaseOrderService;
import com.purchase_order.model.PurchaseOrderVO;

public class ProductTrackingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		/** insertProductTracking-start **/
		if ("insertProductTracking".equals(action)) {

//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memId = req.getParameter("memId");
				String productNo = req.getParameter("productNo");
				System.out.println("memId : " + memId);
				System.out.println("productNo : " + productNo);

				/*************************** 2.開始查詢資料 ****************************************/
				ProductTrackingService productTrackingService = new ProductTrackingService();
				productTrackingService.addProductTracking(memId, productNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
//				req.setAttribute("productVO", productVO);
////				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/front-end/purchase-order/list-one-order-by-pono-seller.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** insertProductTracking-end **/
		/** deleteProductTracking-start **/
		if ("deleteProductTracking".equals(action)) {

//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memId = req.getParameter("memId");
				String productNo = req.getParameter("productNo");
				System.out.println("memId : " + memId);
				System.out.println("productNo : " + productNo);

				/*************************** 2.開始查詢資料 ****************************************/
				ProductTrackingService productTrackingService = new ProductTrackingService();
				productTrackingService.deleteProductTracking(memId, productNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
//				req.setAttribute("productVO", productVO);
////				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/front-end/purchase-order/list-one-order-by-pono-seller.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** deleteProductTracking-end **/
		/** initProductTrackingProduct-start **/
		if ("initProductTrackingProduct".equals(action)) {
			PrintWriter out = null;
			try {
				String memId = null; // 這裡到時候要從SESSION GET
				///

				MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
				String mem_id = (String) req.getAttribute("mem_id");
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);

				if (memVO == null) {
					memVO = memVOLogin;
				}

				if (memVOLogin == null) { // if (memVOLogin == null && empVO == null) {
					res.sendRedirect(req.getContextPath() + "/front-end/mem/signin.jsp");
				} else {
					memId = memVO.getMem_id();
					System.out.println("memId: " + memId);
				}
				///

				ProductTrackingService productTrackingService = new ProductTrackingService();
				ProductService productService = new ProductService();
				PhotoService photoService = new PhotoService();

				JSONArray array = new JSONArray();
				List<ProductTrackingVO> list = productTrackingService.getAllProductTrackingByMemId(memId);

				for (ProductTrackingVO productTrackingVO : list) {
					JSONObject obj = new JSONObject();
					String productNo = productTrackingVO.getProductNo();

					ProductVO productVO = productService.getOneProduct(productNo);
					PhotoVO photoVO = photoService.getFirstPhoto(productNo);

					obj.put("pName", productVO.getpName());
					obj.put("pPrice", productVO.getpPrice());
//					obj.put("pStock", productVO.getpStock());
//					obj.put("pStatus", productVO.getpStatus());
					obj.put("productNo", productVO.getProductNo());
					obj.put("base64Image", photoVO.getBase64Image());

					array.put(obj);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				out = res.getWriter();
				out.write(array.toString());

			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			} finally {
				out.flush();
				out.close();
			}
		}
		/** initProductTrackingProduct-end **/

		/** initProductTracking-start **/
		if ("initProductTracking".equals(action)) {
			PrintWriter out = null;
			try {
				ProductTrackingService productTrackingService = new ProductTrackingService();
				JSONArray array = new JSONArray();
				List<ProductTrackingVO> list = productTrackingService.getAllProductTracking();

				for (ProductTrackingVO productTrackingVO : list) {
					JSONObject obj = new JSONObject();
					obj.put("memId", productTrackingVO.getMemId());
					obj.put("productNo", productTrackingVO.getProductNo());
					array.put(obj);
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				out = res.getWriter();
				out.write(array.toString());

			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			} finally {
				out.flush();
				out.close();
			}
		}
		/** initProductTracking-end **/

	}
}
