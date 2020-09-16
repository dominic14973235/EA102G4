package ecpay.payment.integration.domain;

/**
 * ä¿¡ç”¨?¡å®šæ?Ÿå?šé?è?‚å–®?Ÿ¥è©¢ç‰©ä»?
 * @author mark.chiu
 *
 */
public class QueryCreditCardPeriodInfoObj {
	
	/**
	 * MerchantID
	 * ??ˆä?œç‰¹åº—ç·¨???
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??ˆä?œç‰¹åº—äº¤??“ç·¨???
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * TimeStamp
	 * é©—è?‰æ?‚é??
	 */
	private String TimeStamp = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??–å?—MerchantID ??ˆä?œç‰¹åº—ç·¨???
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * è¨­å?šMerchantID ??ˆä?œç‰¹åº—ç·¨???
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
	 * ??–å?—TimeStamp é©—è?‰æ?‚é??
	 * @return TimeStamp
	 */
	public String getTimeStamp() {
		return TimeStamp;
	}
	/**
	 * è¨­å?šTimeStamp é©—è?‰æ?‚é??
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "QueryCreditCardPeriodInfoObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo
				+ ", TimeStamp=" + TimeStamp + "]";
	}
}
