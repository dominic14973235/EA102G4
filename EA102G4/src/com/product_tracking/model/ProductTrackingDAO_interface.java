package com.product_tracking.model;
import java.util.List;

public interface ProductTrackingDAO_interface {
        public void insert(ProductTrackingVO productTrackingVO);
        public void delete(ProductTrackingVO productTrackingVO);
        public List<ProductTrackingVO> getAll();
        public List<ProductTrackingVO> getAllByMemId(String memId);

}
