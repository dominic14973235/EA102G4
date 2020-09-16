package com.product_photo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_photo.model.PhotoJDBCDAO;
import com.product_photo.model.PhotoService;
import com.product_photo.model.PhotoVO;

public class PhotoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//////////////////////////getOneForPhoteInsert////////////////////////////////
		if ("getOneForInsertPhoto".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String productNo = req.getParameter("productNo");
				System.out.println("************" + productNo + "*********************");
				
				/***************************2.開始查詢資料****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);  
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/product/update_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
//////////////////////////getOneForPhoteInsert////////////////////////////////
		//////////////////////////insert_photo////////////////////////////////
		if (action.equals("insert_photo")) {
			List<String> errorMsgs = new LinkedList<String>();
			Collection<Part> parts = req.getParts();

			byte[] pPhoto = null;
			for (Part part : parts) {
				String name = part.getName();
				if ("p_photo".equals(name)) {
					try {
						InputStream in = part.getInputStream();
						System.out.println(in.toString());
						byte[] buf = new byte[in.available()];
						in.read(buf);
						pPhoto = buf;
						in.close();
					} catch (IOException e) {
						errorMsgs.add("照片有誤");
					}
				}
			}
			PhotoVO vo = new PhotoVO();
			PhotoService photoService = new PhotoService();
			
			//把圖片一口氣放進去 21個測試商品各放一張
			for(int i = 1 ; i <= 21 ; i++) {
				
				String productNo = "P00000" + i;
				if (i >= 10) {
					productNo = "P0000" + i;
				}
				vo.setProductNo(productNo);
				vo.setpPhoto(pPhoto);
				photoService.addPhoto(productNo, pPhoto);
			}

		}
		//////////////////////////insert_photo////////////////////////////////
		////////////////////////// 下面是顯示圖片////////////////////////////////
		if (action.equals("show")) {
			String photoNo = req.getParameter("photo_no");
//			photoNo = "1";
			System.out.println("有顯示:" + photoNo);
			PhotoJDBCDAO dao = new PhotoJDBCDAO();

			PhotoVO book = dao.get(photoNo);
			System.out.println("有顯示:" + book);
			req.setAttribute("p_photo", book);
			
		}
		
	}
}
