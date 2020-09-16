package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.model.ProductService;
import com.product.model.ProductVO;

public class AJAXProductServlet extends HttpServlet{

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("進入成功");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("init".equals(action)) {
			
			ProductService productService = new ProductService();
			
			JSONArray array = new JSONArray();
			
			List<ProductVO> list = productService.getAll();
			
			for(ProductVO productVO : list) {
				JSONObject obj = new JSONObject();
				
				obj.put("productNo", productVO.getProductNo());
				obj.put("pName", productVO.getpName());
				obj.put("pPrice",productVO.getpPrice());
				obj.put("categoryNo",productVO.getCategoryNo());
				obj.put("pPrice",productVO.getpPrice());
				
				array.put(obj);
				
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		if("FindProductByCategory".equals(action)) {
			
			ProductService productService = new ProductService();
			
			JSONArray array = new JSONArray();
			
			List<ProductVO> list = productService.getAll();
			
			for(ProductVO productVO : list) {
				JSONObject obj = new JSONObject();
				
				obj.put("productNo", productVO.getProductNo());
				obj.put("pName", productVO.getpName());
				obj.put("pPrice",productVO.getpPrice());
				obj.put("categoryNo",productVO.getCategoryNo());
				obj.put("pPrice",productVO.getpPrice());
				
				array.put(obj);
				
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		if("getAllproduct".equals(action)) {
			ProductService productService = new ProductService();
			
			JSONArray array = new JSONArray();
			
			List<ProductVO> list = productService.getAll();
			
			for(ProductVO productVO : list) {
				JSONObject obj = new JSONObject();
				
				obj.put("productNo", productVO.getProductNo());
				obj.put("pName", productVO.getpName());
				obj.put("pPrice",productVO.getpPrice());
				obj.put("categoryNo",productVO.getCategoryNo());
				obj.put("pPrice",productVO.getpPrice());
				
				array.put(obj);
				
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		

	}
}
