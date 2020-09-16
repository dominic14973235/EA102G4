package com.purchase_order.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductVO;

public class PurchaseOrderService {

	private PurchaseOrderDAO_interface dao;

	public PurchaseOrderService() {
		dao = new PurchaseOrderJDBCDAO();
	}

	public PurchaseOrderVO addOrder(String poPayment, String poDelivery, String deliveryAddress, Double quantity,
			String productNo, String poStatus, String purchaseDetail, String buyerName, String buyerPhone,
			String memId) {
		PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();

		purchaseOrderVO.setPoPayment(poPayment);
		purchaseOrderVO.setPoDelivery(poDelivery);
		purchaseOrderVO.setDeliveryAddress(deliveryAddress);
		purchaseOrderVO.setQuantity(quantity);
		purchaseOrderVO.setProductNo(productNo);
		purchaseOrderVO.setPoStatus(poStatus);
		purchaseOrderVO.setPurchaseDetail(purchaseDetail);
		purchaseOrderVO.setBuyerName(buyerName);
		purchaseOrderVO.setBuyerPhone(buyerPhone);
		purchaseOrderVO.setMemId(memId);
		dao.insert(purchaseOrderVO);
		return purchaseOrderVO;
	}

	public List<PurchaseOrderVO> getAll() {
		return dao.getAll();
	}

	public PurchaseOrderVO getOneProduct(String productNo) {
		return dao.findByPrimaryKey(productNo);
	}

//	public PurchaseOrderVO deleteProduct(String product_no) {
//		PurchaseOrderVO productVO = new PurchaseOrderVO();
//		productVO.setProductNo(product_no);;
//		productVO.setpStatus("已下架");
//		dao.deleteFromFront(productVO);
//		return productVO;
//	}
	public PurchaseOrderVO updateQuantity(Double quantity) {
		PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
		purchaseOrderVO.setQuantity(quantity);

		dao.update(purchaseOrderVO);
		return purchaseOrderVO;
	}

	public PurchaseOrderVO updateOrder(String purchaseDetail,String poPayment, String buyerName, String buyerPhone,
			String poDelivery, String deliveryAddress, Double quantity, String poStatus, String poNo) {
		PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();

		purchaseOrderVO.setPurchaseDetail(purchaseDetail);
		purchaseOrderVO.setBuyerName(buyerName);
		purchaseOrderVO.setBuyerPhone(buyerPhone);
		purchaseOrderVO.setPoNo(poNo);
		purchaseOrderVO.setPoPayment(poPayment);
		purchaseOrderVO.setPoDelivery(poDelivery);
		purchaseOrderVO.setDeliveryAddress(deliveryAddress);
		purchaseOrderVO.setQuantity(quantity);
		purchaseOrderVO.setPoStatus(poStatus);

		dao.update(purchaseOrderVO);

		return purchaseOrderVO;
	}
	
	public void updateOrderStatus(PurchaseOrderVO purchaseOrderVO,String poStatus) {
		purchaseOrderVO.setPoStatus(poStatus);
		dao.update(purchaseOrderVO);
	}

	public PurchaseOrderVO getOneOrder(String poNo) {
		return dao.findByPrimaryKey(poNo);
	}

	public PurchaseOrderVO deleteProduct(String poNo) {
		PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
		purchaseOrderVO.setPoStatus("70");
		purchaseOrderVO.setPoNo(poNo);

		dao.deleteFromFront(purchaseOrderVO);
		return purchaseOrderVO;
	}

	public List<PurchaseOrderVO> getOrderByMemId(String memId) { // 訂單-根據會員編號瀏覽訂單
		return dao.getOrderByMemId(memId);
	}

	public List<PurchaseOrderVO> getOrderBySellerMemIdPurchaseOrderVO(String memId) {
		List<PurchaseOrderVO> purchaseOrderVOList6 = new ArrayList<PurchaseOrderVO>();
		List<Object> list6 = dao.getOrderBySellerMemId(memId);

		for (Object aOrder : list6) {
			if (aOrder.toString().indexOf("PurchaseOrderVO") != -1) {
				System.out.println(aOrder);
				purchaseOrderVOList6.add((PurchaseOrderVO) aOrder);
			}
		}
		return purchaseOrderVOList6;
	}

	public List<ProductVO> getOrderBySellerMemIdProductVO(String memId) {
		List<Object> list6 = dao.getOrderBySellerMemId(memId);
		List<ProductVO> productVOList = null;
		for (Object aOrder : list6) {
			if (aOrder.toString().indexOf("ProductVO") != -1) {
				productVOList = new ArrayList<ProductVO>();
				productVOList.add((ProductVO) aOrder);
			} else {
				System.out.println("aOrder==ProductVO");
			}
		}
		return productVOList;
	}

}
