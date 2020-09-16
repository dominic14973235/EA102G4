package com.product.model;
import java.util.List;

import com.product_photo.model.PhotoVO;

public interface ProductDAO_interface {
        public void insert(ProductVO productVO);
        public void update(ProductVO productVO,List<PhotoVO> list);
        public void deleteFromFront(ProductVO productVO);
        public ProductVO findByPrimaryKey(String productNo);
        public List<ProductVO> findByMemId(String memId);
        public List<ProductVO> getAll();
        public ProductVO updateStock(ProductVO productVO);
        public ProductVO updateRating(double pRating,ProductVO productVO);
        public void insertProductAndPhoto(ProductVO productVO,List<PhotoVO> photoVO);
}
