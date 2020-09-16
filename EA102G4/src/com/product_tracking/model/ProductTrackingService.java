package com.product_tracking.model;

import java.util.List;

public class ProductTrackingService {

	private ProductTrackingDAO_interface dao;

	public ProductTrackingService() {
		dao = new ProductTrackingJDBCDAO();
	}

	public void addProductTracking(String memId, String productNo) {
		ProductTrackingVO productTrackingVO = new ProductTrackingVO();
		productTrackingVO.setMemId(memId);
		productTrackingVO.setProductNo(productNo);
		dao.insert(productTrackingVO);
		System.out.println("新增追蹤-成功");
	}

	public List<ProductTrackingVO> getAllProductTracking() {
		System.out.println("查詢全部追蹤-成功");
		return dao.getAll();
	}

	public List<ProductTrackingVO> getAllProductTrackingByMemId(String memId) {
		System.out.println("查詢全部根據會員編號-成功");
		return dao.getAllByMemId(memId);
	}

	public void deleteProductTracking(String memId, String productNo) {
		ProductTrackingVO productTrackingVO = new ProductTrackingVO();
		productTrackingVO.setMemId(memId);
		productTrackingVO.setProductNo(productNo);
		dao.delete(productTrackingVO);
		System.out.println("刪除追蹤-成功");
	}

}
