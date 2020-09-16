package com.purchase_order.model;
import java.util.List;

public interface PurchaseOrderDAO_interface {
        public void insert(PurchaseOrderVO productVO);
        public void update(PurchaseOrderVO productVO);
        public void deleteFromFront(PurchaseOrderVO productVO);
        public PurchaseOrderVO findByPrimaryKey(String product_no);
        public List<PurchaseOrderVO> getAll();
        public List<PurchaseOrderVO> getOrderByMemId(String memId);
        public List<Object> getOrderBySellerMemId(String memId);
        

}
