package com.product_comment.model;

import java.util.List;

public class ProductCommentService {

	private ProductCommentDAO_interface dao;

	public ProductCommentService() {
		dao = new ProductCommentJDBCDAO();
	}

	/**
	 * @param memId
	 * @param productNo
	 * @param productComment
	 */
	public void addProductComment(String memIdBuyer,String memIdSeller, String productNo,String productComment,String pRatingEach) {
		ProductCommentVO productCommentVO = new ProductCommentVO();
		productCommentVO.setMemIdBuyer(memIdBuyer);
		productCommentVO.setMemIdSeller(memIdSeller);
		productCommentVO.setProductNo(productNo);
		productCommentVO.setProductComment(productComment);
		productCommentVO.setpRatingEach(pRatingEach);
		dao.insert(productCommentVO);
		System.out.println("新增評論-成功");
	}

	public List<ProductCommentVO> getAllProductComment() {
		System.out.println("查詢全部評論-成功");
		return dao.getAll();
	}

	public List<ProductCommentVO> getByProductNo(String productNo) {
		return dao.getByProductNo(productNo);
	}

	public List<ProductCommentVO> getByMemId(String memId) {
		return dao.getByMemId(memId);
	}


}
