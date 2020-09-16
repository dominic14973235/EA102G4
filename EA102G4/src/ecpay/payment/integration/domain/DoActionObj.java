package ecpay.payment.integration.domain;

/**
 * ä¿¡ç”¨?¡??œå¸³/???ˆ·/??–æ??/?”¾æ£„ç‰©ä»?
 * @author mark.chiu
 *
 */
public class DoActionObj {
	
	/**
	 * MerchantID
	 * ??ˆä?œç‰¹åº—ç·¨???(?”±ç¶ ç?Œæ?ä??)
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??ˆä?œç‰¹åº—äº¤??“ç·¨???
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * TradeNo
	 * ç¶ ç?Œç?„äº¤??“ç·¨???
	 */
	private String TradeNo = "";
	
	/**
	 * Action
	 * ?Ÿ·è¡Œå?•ä??
	 */
	private String Action = "";
	
	/**
	 * TotalAmount
	 * ??‘é??
	 */
	private String TotalAmount = "";
	
	/**
	 * PlatformID
	 * ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???(?”±ç¶ ç?Œæ?ä??)
	 */
	private String PlatformID = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??–å?—MerchantID ??ˆä?œç‰¹åº—ç·¨???(?”±ç¶ ç?Œæ?ä??)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * è¨­å?šMerchantID ??ˆä?œç‰¹åº—ç·¨???(?”±ç¶ ç?Œæ?ä??)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * ??–å?—MerchantTradeNo ??ˆä?œç‰¹åº—äº¤??“ç·¨???
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * è¨­å?šMerchantTradeNo ??ˆä?œç‰¹åº—äº¤??“ç·¨???
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * ??–å?—TradeNo ç¶ ç?Œç?„äº¤??“ç·¨???
	 * @return TradeNo
	 */
	public String getTradeNo() {
		return TradeNo;
	}
	/**
	 * è¨­å?šTradeNo ç¶ ç?Œç?„äº¤??“ç·¨???
	 * @param tradeNo
	 */
	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}
	/**
	 * ??–å?—Action ?Ÿ·è¡Œå?•ä??
	 * @return Action
	 */
	public String getAction() {
		return Action;
	}
	/**
	 * è¨­å?šAction ?Ÿ·è¡Œå?•ä??
	 * @param action
	 */
	public void setAction(String action) {
		Action = action;
	}
	/**
	 * ??–å?—TotalAmount ??‘é??
	 * @return TotalAmount
	 */
	public String getTotalAmount() {
		return TotalAmount;
	}
	/**
	 * è¨­å?šTotalAmount ??‘é??
	 * @param totalAmount
	 */
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}
	/**
	 * ??–å?—PlatformID ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???(?”±ç¶ ç?Œæ?ä??)
	 * @return PlatformID
	 */
	public String getPlatformID() {
		return PlatformID;
	}
	/**
	 * è¨­å?šPlatformID ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???(?”±ç¶ ç?Œæ?ä??)
	 * @param platformID
	 */
	public void setPlatformID(String platformID) {
		PlatformID = platformID;
	}
	@Override
	public String toString() {
		return "DoActionObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", TradeNo=" + TradeNo
				+ ", Action=" + Action + ", TotalAmount=" + TotalAmount + ", PlatformID=" + PlatformID + "]";
	}
}
