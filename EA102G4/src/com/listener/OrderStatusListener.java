package com.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OrderStatusListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();		
		Map<String,String> poStatusListener = new HashMap<>();
//		10--處理中 20--訂單已確認 30--貨物運送中 40--已送達 50--已取貨 60--已完成 70--已取消
//		poStatusListener.put("10", "處理中");
//		poStatusListener.put("20", "訂單已確認");
//		poStatusListener.put("30", "貨物運送中");
//		poStatusListener.put("40", "已送達");
//		poStatusListener.put("50", "已取貨");
//		poStatusListener.put("60", "已完成");
//		poStatusListener.put("70", "已取消");
//		context.setAttribute("poStatusListener", poStatusListener);
		
		Map<String,String> poPaymentListener = new HashMap<>();
		poPaymentListener.put("10", "信用卡");
		poPaymentListener.put("20", "轉帳付款");
		poPaymentListener.put("30", "取貨付款");
		context.setAttribute("poPaymentListener", poPaymentListener);
		
		Map<String,String> poDeliveryListener = new HashMap<>();
		poDeliveryListener.put("10", "宅配到府");
		poDeliveryListener.put("20", "取貨付款");
		context.setAttribute("poDeliveryListener", poDeliveryListener);
		
		Map<String,String> listProductByMemId = new HashMap<>();
		listProductByMemId.put("10", "上架中");
		listProductByMemId.put("20", "已下架");
		context.setAttribute("listProductByMemId", listProductByMemId);
		
		Map<String,String> OrderStatusListener = new HashMap<>();
		OrderStatusListener.put("10", "待付款");
//		OrderStatusListener.put("20", "待付款");
		OrderStatusListener.put("30", "待出貨");
		OrderStatusListener.put("40", "已出貨");
		OrderStatusListener.put("50", "已送達");
//		OrderStatusListener.put("60", "已取貨");
		OrderStatusListener.put("70", "訂單已取消");
		OrderStatusListener.put("711", "賣方-提出修改訂單中");
		OrderStatusListener.put("712", "買方-提出修改訂單中");
		OrderStatusListener.put("721", "賣方-訂單修改中");
		OrderStatusListener.put("722", "買方-訂單修改中");
		OrderStatusListener.put("731", "賣方-提出取消交易");
		OrderStatusListener.put("732", "買方-提出取消交易");
		OrderStatusListener.put("80", "交易完成");//雙方未給評
		OrderStatusListener.put("81", "交易完成");//賣家已給評
		OrderStatusListener.put("82", "交易完成");//買家已給評
		OrderStatusListener.put("83", "交易完成");//雙方皆給評
	
		context.setAttribute("OrderStatusListener", OrderStatusListener);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}