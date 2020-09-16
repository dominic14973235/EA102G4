package com.product_category.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;



public interface Product_categoryDAO_interface {
    public void insert(Product_categoryVO product_categoryVO);
    public void update(Product_categoryVO product_categoryVO);
    public Product_categoryVO findByPrimaryKey(String category_no);
    public List<Product_categoryVO> getAll();
    public Set<ProductVO> getProductsByCategory(String category_no);
  
  
  
  
}
