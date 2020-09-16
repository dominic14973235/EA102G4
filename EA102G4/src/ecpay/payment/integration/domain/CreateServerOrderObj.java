package ecpay.payment.integration.domain;

/**
 * ApplePay ä¿¡ç”¨?¡??ˆæ?Šä?œæ¥­?‰©ä»?
 * @author mark.chiu
 *
 */
public class CreateServerOrderObj {
	
	/**
	 * MerchantID
	 * ??ˆä?œå? å?†ç·¨??Ÿï?Œç”±ç¶ ç?Œæ?ä??
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??ˆä?œå? å?†äº¤??“å?è??
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * MerchantTradeDate
	 * ??ˆä?œå? å?†äº¤??“æ?‚é??( yyyy/MM/dd HH:mm:ss )
	 */
	private String MerchantTradeDate = "";
	
	/**
	 * TotalAmount
	 * äº¤æ?“é?‘é?ï?Œè?‹å¸¶?•´?•¸ï¼Œä?å¯??‰å?æ•¸é»ï?Œå?…é?æ–°?°å¹?ï¼Œé?‘é?ä?å¯?‚º0???
	 */
	private String TotalAmount = "";
	
	/**
	 * CurrencyCode
	 * å¹??ˆ¥ï¼ŒApple Server??šå?†å?—é?—è?‰æ??‚³??„CurrencyCode
	 */
	private String CurrencyCode = "";
	
	/**
	 * ItemName
	 * ??†å?å?ç¨±ï¼Œå?‚æ?œå?†å?å?ç¨±??‰å?šç?†æ?‚ï?Œè?‹ä»¥äº•è?Ÿå?†é??(#)
	 */
	private String ItemName = "";
	
	/**
	 * PlatformID
	 * ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???
	 */
	private String PlatformID = "";
	
	/**
	 * TradeDesc
	 * äº¤æ?“æ?è¿°
	 */
	private String TradeDesc = "";
	
	/**
	 * TradeType
	 * ä»˜æ¬¾?–¹å¼?  1. In App   2. On the Web
	 */
	private String TradeType = "";
	
	/**
	 * PaymentToken
	 * ä»˜æ¬¾è³‡æ?™ç‰©ä»¶ï?ŒApple Server??šå?†å?—é?—è?‰å?Œå?å‚³??„Merchant Session?‰©ä»¶ä¸­payment??„JSONå­—ä¸²
	 */
	private String PaymentToken = "";
	
	/********************* getters and setters *********************/

	/**
	 * ??–å?—MerchantID ??ˆä?œå? å?†ç·¨??Ÿï?Œç”±ç¶ ç?Œæ?ä??
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}

	/**
	 * è¨­å?šMerchantID ??ˆä?œå? å?†ç·¨??Ÿï?Œç”±ç¶ ç?Œæ?ä??
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	/**
	 * ??–å?—MerchantTradeNo ??ˆä?œå? å?†äº¤??“å?è??
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}

	/**
	 * è¨­å?šMerchantTradeNo ??ˆä?œå? å?†äº¤??“å?è??
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}

	/**
	 * ??–å?—MerchantTradeDate ??ˆä?œå? å?†äº¤??“æ?‚é??( yyyy/MM/dd HH:mm:ss )
	 * @return MerchantTradeDate
	 */
	public String getMerchantTradeDate() {
		return MerchantTradeDate;
	}

	/**
	 * è¨­å?šMerchantTradeDate ??ˆä?œå? å?†äº¤??“æ?‚é??( yyyy/MM/dd HH:mm:ss )
	 * @param merchantTradeDate
	 */
	public void setMerchantTradeDate(String merchantTradeDate) {
		MerchantTradeDate = merchantTradeDate;
	}

	/**
	 * ??–å?—TotalAmount äº¤æ?“é?‘é?ï?Œè?‹å¸¶?•´?•¸ï¼Œä?å¯??‰å?æ•¸é»ï?Œå?…é?æ–°?°å¹?ï¼Œé?‘é?ä?å¯?‚º0???
	 * @return TotalAmount
	 */
	public String getTotalAmount() {
		return TotalAmount;
	}

	/**
	 * è¨­å?šTotalAmount äº¤æ?“é?‘é?ï?Œè?‹å¸¶?•´?•¸ï¼Œä?å¯??‰å?æ•¸é»ï?Œå?…é?æ–°?°å¹?ï¼Œé?‘é?ä?å¯?‚º0???
	 * @param totalAmount
	 */
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}

	/**
	 * ??–å?—CurrencyCode å¹??ˆ¥ï¼ŒApple Server??šå?†å?—é?—è?‰æ??‚³??„CurrencyCode
	 * @return CurrencyCode
	 */
	public String getCurrencyCode() {
		return CurrencyCode;
	}

	/**
	 * è¨­å?šCurrencyCode å¹??ˆ¥ï¼ŒApple Server??šå?†å?—é?—è?‰æ??‚³??„CurrencyCode
	 * @param currencyCode
	 */
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	/**
	 * ??–å?—ItemName ??†å?å?ç¨±ï¼Œå?‚æ?œå?†å?å?ç¨±??‰å?šç?†æ?‚ï?Œè?‹ä»¥äº•è?Ÿå?†é??(#)
	 * @return ItemName
	 */
	public String getItemName() {
		return ItemName;
	}

	/**
	 * è¨­å?šItemName ??†å?å?ç¨±ï¼Œå?‚æ?œå?†å?å?ç¨±??‰å?šç?†æ?‚ï?Œè?‹ä»¥äº•è?Ÿå?†é??(#)
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	/**
	 * ??–å?—PlatformID ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???
	 * @return PlatformID
	 */
	public String getPlatformID() {
		return PlatformID;
	}

	/**
	 * è¨­å?šPlatformID ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???
	 * @param platformID
	 */
	public void setPlatformID(String platformID) {
		PlatformID = platformID;
	}

	/**
	 * ??–å?—TradeDesc äº¤æ?“æ?è¿°
	 * @return TradeDesc
	 */
	public String getTradeDesc() {
		return TradeDesc;
	}

	/**
	 * è¨­å?šTradeDesc äº¤æ?“æ?è¿°
	 * @param tradeDesc
	 */
	public void setTradeDesc(String tradeDesc) {
		TradeDesc = tradeDesc;
	}

	/**
	 * ??–å?—TradeType ä»˜æ¬¾?–¹å¼?  1. In App   2. On the Web
	 * @return TradeType
	 */
	public String getTradeType() {
		return TradeType;
	}

	/**
	 * è¨­å?šTradeType ä»˜æ¬¾?–¹å¼?  1. In App   2. On the Web
	 * @param tradeType
	 */
	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}

	/**
	 * ??–å?—PaymentToken ä»˜æ¬¾è³‡æ?™ç‰©ä»¶ï?ŒApple Server??šå?†å?—é?—è?‰å?Œå?å‚³??„Merchant Session?‰©ä»¶ä¸­payment??„JSONå­—ä¸²
	 * @return PaymentToken
	 */
	public String getPaymentToken() {
		return PaymentToken;
	}

	/**
	 * è¨­å?šPaymentToken ä»˜æ¬¾è³‡æ?™ç‰©ä»¶ï?ŒApple Server??šå?†å?—é?—è?‰å?Œå?å‚³??„Merchant Session?‰©ä»¶ä¸­payment??„JSONå­—ä¸²
	 * @param paymentToken
	 */
	public void setPaymentToken(String paymentToken) {
		PaymentToken = paymentToken;
	}

	@Override
	public String toString() {
		return "CreateServerOrderObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo
				+ ", MerchantTradeDate=" + MerchantTradeDate + ", TotalAmount=" + TotalAmount + ", CurrencyCode="
				+ CurrencyCode + ", ItemName=" + ItemName + ", PlatformID=" + PlatformID + ", TradeDesc=" + TradeDesc
				+ ", TradeType=" + TradeType + ", PaymentToken=" + PaymentToken + "]";
	}
}
