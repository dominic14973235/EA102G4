package com.product_comment.model;
import java.util.List;

public interface ProductCommentDAO_interface {
        public void insert(ProductCommentVO productTrackingVO);
        public List<ProductCommentVO> getAll();
        public List<ProductCommentVO> getByProductNo(String productNo);
        public List<ProductCommentVO> getByMemId(String memId);

}
