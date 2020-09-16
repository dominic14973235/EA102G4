package com.coin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.coin.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;


public class CoinServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static AllInOne all;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("coin_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入點數訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
					
				String coin_id = null;				
					coin_id = str.trim();				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CoinService coinSvc = new CoinService();
				CoinVO coinVO = coinSvc.getOneCoinOreder(coin_id);
				if (coinVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coinVO", coinVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/coin/listOneCoinOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				return;
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getMem_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
					
				String mem_id = null;				
					mem_id = str.trim();				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CoinService coinSvc = new CoinService();
				List<CoinVO> listMem = coinSvc.getMemCoinOrder(mem_id);
				if (listMem == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listMem", listMem); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/coin/listMemCoinOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String coin_id = req.getParameter("coin_id").trim();
				
				/***************************2.開始查詢資料****************************************/
				CoinService coinSvc = new CoinService();
				CoinVO coinVO = coinSvc.getOneCoinOreder(coin_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("coinVO", coinVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/coin/update_coin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			}catch (NullPointerException ne){
				errorMsgs.add("訂單編號不得為空 3064:" + ne.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/listAllCoinOrder.jsp");
				failureView.forward(req, res);
			}
			catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/listAllCoinOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String coin_id = req.getParameter("coin_id").trim();
				Integer amount=null;
				Integer deposit_coin=null;
				try {
					amount = new Integer(req.getParameter("amount").trim()); 
				}catch (NumberFormatException e) {
	            	errorMsgs.add("儲值金額: 請輸入數字");
	            }
				if (amount == null) {
					errorMsgs.add("儲值金額: 請勿空白");
				}else if(amount>999999||amount<100) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("儲值金額: 只能在100~999999");
	            }
				
				try {
					deposit_coin = new Integer(req.getParameter("deposit_coin").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("儲值點數: 請輸入數字");
				}
				if (deposit_coin == null) {
					errorMsgs.add("儲值點數: 請勿空白");
				} else if(amount>999999||amount<=100) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("儲值點數: 只能在100~999999");
	            }
//				if(deposit_coin!=amount)
//					errorMsgs.add("儲值點數與儲值金額不符合");
				CoinVO coinVO = new CoinVO();
				coinVO.setAmount(amount);
				coinVO.setDeposit_coin(deposit_coin);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(1);
					req.setAttribute("coinVO", coinVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/update_coin_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CoinService coinSvc = new CoinService();
				coinVO = coinSvc.updateCoinOrder(deposit_coin, amount, coin_id,1);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coinVO", coinVO); // 資料庫update成功後,正確的的empVO物件,存入req
				System.out.println(2);
				String url = "/front-end/coin/showReqOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/update_coin_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			CoinVO coinVO = new CoinVO();
			try {
				MemVO memVO = null;
				String mem_id =null;
				
				memVO = (MemVO)session.getAttribute("memVOLogin");
				if(memVO==null) {
					throw new RuntimeException("memVO=null");
				}
				mem_id = memVO.getMem_id();
				if(mem_id==null) {
					throw new RuntimeException("mem_id=null");
				}
				
				Integer amount=null;
				try {	
					amount = new Integer(req.getParameter("amount").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請輸入數字");
				}
				if (amount>=150000||amount<100)
					errorMsgs.add("儲值金額只能在100~150000之間");
				Integer deposit_coin = amount;
				
				
				
				coinVO.setMem_id(mem_id);
				coinVO.setDeposit_coin(deposit_coin);
				coinVO.setAmount(amount);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coinVO", coinVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/addCoinOrder.jsp");
					failureView.forward(req, res);
					return;
				}
//				
				CoinService coinSvc = new CoinService();
				coinVO = coinSvc.addCoinOrder(mem_id, deposit_coin, amount);
				/******************************生成ECPAY訂單物件****************************************/
				initial();
				AioCheckOutOneTime  obj = new AioCheckOutOneTime();
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Date date = new Date();
				String strDate = sdFormat.format(date);
//				AioCheckOutOneTime obj = new AioCheckOutOneTime();
				SimpleDateFormat sdfMMdd = new SimpleDateFormat("MMddss");
				System.out.println(req.getContextPath());
				
				obj.setMerchantTradeNo(coinVO.getCoin_id()+sdfMMdd.format(date));

				res.setContentType("text/html; charset=utf-8");
			    res.setCharacterEncoding("utf-8");
				obj.setMerchantTradeDate(strDate);
				obj.setTotalAmount(amount.toString());
				obj.setTradeDesc("detail");
				obj.setItemName("INGYM點數  " + deposit_coin +"   點");
//				obj.setItemName("INGYM"+ deposit_coin);
				obj.setNeedExtraPaidInfo("N");
				obj.setRedeem("Y");
				obj.setReturnURL(
						req.getRequestURL()
						+ "?ResultCoin_id="+coinVO.getCoin_id()
						+"&mem_id="+memVO.getMem_id()
						);
//
//								
//								obj.setClientBackURL(req.getRequestURL()
//										+ "?action=getOne_For_Display&coin_id="+coinVO.getCoin_id());  //客戶端跳轉   記得設定  
//				
								obj.setClientBackURL(	req.getRequestURL()
										+ "?ResultCoin_id="+coinVO.getCoin_id()
										+"&mem_id="+memVO.getMem_id());   //沒有ssl收 讓客戶端自己觸發更新
				String form = all.aioCheckOut(obj, null);
				PrintWriter out = res.getWriter();
				 out.println("<!DOCTYPE html>");
			        out.println("<html>");
			        out.println("<body>");
			        out.println(form);
			        out.println("</ul>");
			        out.println("</body>");
			        out.println("</html>");
//				String url = "/front-end/coin/showReqOrder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
				
			} catch (RuntimeException e) { 
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/index.jsp");
				failureView.forward(req, res);
				return;
			}catch (Exception e) {
				req.setAttribute("coinVO", coinVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/addCoinOrder.jsp");
				failureView.forward(req, res);
				return;
			}
		}
//        String payResult = req.getParameter("RtnMsg");
//		if(payResult!=null) {
//			if(payResult.equals("交易成功")){
//				System.out.println(payResult);
//				Integer numResult = null;
//				try {
//				numResult = new Integer(req.getParameter("RtnCode"));
//				}catch(NumberFormatException ne){
//					System.out.println("numResult/RtnCode"+"  回傳值轉型失敗");
//				}
//				
//				if(numResult==1) {
				String coin_id=  req.getParameter("ResultCoin_id");
				if(coin_id!=null) {
				String mem_id = req.getParameter("mem_id");
				CoinService coSvc = new CoinService();
				MemService memSvc = new MemService();
				CoinVO coinVO =coSvc.getOneCoinOreder(coin_id);
				MemVO memVO = memSvc.getOneMem(mem_id);
				System.out.println(memVO.getCoin()+"修改前");
				int deposit_coin =coinVO.getDeposit_coin();
				int memCoin = memVO.getCoin()+deposit_coin;
				if(coinVO.getCo_status()!=1) {
				coSvc.updateCoinOrder(coinVO.getDeposit_coin(),coinVO.getAmount(), coinVO.getCoin_id(),1);
				memVO = memSvc.updateMem(memVO.getMem_name(), memVO.getMem_psw(),memVO.getMem_bir(), memVO.getSex()
						, memVO.getMem_addr(),memVO.getMem_email(),memVO.getMem_phone(), memVO.getMem_absent(),
						memCoin, memVO.getMem_resume(),memVO.getM_reg_date(),memVO.getSel_auth(),
						memVO.getArt_auth(),memVO.getCom_auth(), memVO.getMem_id());
				System.out.println(memVO.getCoin()+"修改後");
				}
				RequestDispatcher sucView = req
						.getRequestDispatcher("/front-end/coin/listMemCoinOrder.jsp");
				sucView.forward(req, res);
				}
//				}else if(numResult!=0) {
//					System.out.println("錯誤交易,代碼為:"+numResult);
//				}
				
				
//			}
//		}
		
	}

	private static void initial(){
		all = new AllInOne("");
	}
	public static String genAioCheckOutOneTime(){
		AioCheckOutOneTime obj = new AioCheckOutOneTime();
		obj.setMerchantTradeNo("INGYM0008");
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setRedeem("Y");
		String form = all.aioCheckOut(obj, null);
		return form;
	}

}

