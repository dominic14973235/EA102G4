package com.product_category.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class CategoryService {

	private Product_categoryDAO_interface dao;

	public CategoryService() {
		dao = new Product_categoryJDBCDAO();
	}

	public Product_categoryVO addCategory(String categoryName) {
		Product_categoryVO product_categoryVO = new Product_categoryVO();
		product_categoryVO.setCategoryName(categoryName);

		return product_categoryVO;
	}

	public List<Product_categoryVO> getAll() {
		return dao.getAll();
	}

	public Product_categoryVO getOneCategory(String categoryNo) {
		return dao.findByPrimaryKey(categoryNo);
	}

	public Set<ProductVO> getProductsByCategory(String categoryNo) {
		Set<ProductVO> set = dao.getProductsByCategory(categoryNo);
		Iterator<ProductVO> it = set.iterator();
		while(it.hasNext()) {
			ProductVO p = it.next();
			if(p.getpStatus().equals("20")) {
				it.remove();
			}
		}
		
		return set;
	}

}
