package com.product.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.product_photo.model.PhotoVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}

	public ProductVO addProduct(String p_name, String mem_id, Double p_price, Double p_stock, String category_no,
			String p_detail) {
		ProductVO productVO = new ProductVO();
		productVO.setpName(p_name);
		productVO.setMemId(mem_id);
		productVO.setpPrice(p_price);
		productVO.setpStock(p_stock);
		productVO.setCategoryNo(category_no);
		productVO.setpDetail(p_detail);
		dao.insert(productVO);
		return productVO;
	}

	public ProductVO addProductAndPhoto(String p_name, String mem_id, Double p_price, Double p_stock,
			String category_no, String p_detail, List<PhotoVO> list, String poPayment, String poDelivery) {
		ProductVO productVO = new ProductVO();
		productVO.setpName(p_name);
		productVO.setMemId(mem_id);
		productVO.setpPrice(p_price);
		productVO.setpStock(p_stock);
		productVO.setCategoryNo(category_no);
		productVO.setpDetail(p_detail);
		productVO.setPoPayment(poPayment);
		productVO.setPoDelivery(poDelivery);

		List<PhotoVO> list2 = new ArrayList<PhotoVO>();
		Iterator<PhotoVO> it = list.iterator();
		System.out.println(list);
		while (it.hasNext()) {
			PhotoVO photoVO = (PhotoVO) it.next();
			photoVO.setpPhoto(photoVO.getpPhoto());
			list2.add(photoVO);
		}

		dao.insertProductAndPhoto(productVO, list2);

		return productVO;
	}

	public List<ProductVO> getAll() {
		List<ProductVO> list = dao.getAll();
		Iterator<ProductVO> it = list.iterator();
		while(it.hasNext()) {
			ProductVO p = it.next();
			if(p.getpStatus().equals("20")) {
				it.remove();
			}
		}
		
		
		return list;
	}
	
	public List<ProductVO> getAllWithOut() {
		return dao.getAll();
	}

	public ProductVO getOneProduct(String product_no) {
		return dao.findByPrimaryKey(product_no);
	}

	public ProductVO deleteProduct(String product_no) {
		ProductVO productVO = new ProductVO();
		productVO.setProductNo(product_no);
		;
		productVO.setpStatus("20");
		dao.deleteFromFront(productVO);
		return productVO;
	}

	public ProductVO updateProduct(String product_no, String p_name, Double p_price, Double p_stock,
			String category_no, String p_detail,String poPayment,String poDelivery, List<PhotoVO> list) {
		ProductVO productVO = new ProductVO();
		productVO.setProductNo(product_no);
		
		productVO.setpName(p_name);
		productVO.setpPrice(p_price);
		productVO.setpStock(p_stock);
		productVO.setCategoryNo(category_no);
		productVO.setpDetail(p_detail);
		productVO.setProductNo(product_no);
		productVO.setPoPayment(poPayment);
		productVO.setPoDelivery(poDelivery);

		List<PhotoVO> list2 = new ArrayList<PhotoVO>();
		Iterator<PhotoVO> it = list.iterator();
		System.out.println(list);
		while (it.hasNext()) {
			PhotoVO photoVO = (PhotoVO) it.next();
			photoVO.setpPhoto(photoVO.getpPhoto());
			list2.add(photoVO);
		}

		dao.update(productVO, list);
		return productVO;
	}

	public ProductVO afterBuyStock(String productNo, Double pStock) {
		ProductVO productVO = new ProductVO();
		productVO.setProductNo(productNo);
		productVO.setpStock(pStock);
		dao.updateStock(productVO);
		return productVO;
	}

	public List<ProductVO> findByMemId(String memId) {
		return dao.findByMemId(memId);
	}
	
	 public ProductVO updateRating(double pRating,ProductVO productVO) {
		 dao.updateRating(pRating, productVO);
		 return null;
	 }
}
