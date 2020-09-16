package ecpay.payment.integration.domain;

/**
 * ?Ÿ¥è©¢ä¿¡?”¨?¡?–®ç­†æ?ç´°è¨˜é?„ç‰©ä»?
 * @author mark.chiu
 *
 */
public class QueryTradeObj {
	
	/**
	 * MerchantID
	 * ??ˆä?œç‰¹åº—ç·¨???(?”±ecPay??ä??)
	 */
	private String MerchantID = "";
	
	/**
	 * CreditRefundId
	 * ä¿¡ç”¨?¡??ˆæ?Šå–®???
	 */
	private String CreditRefundId = "";
	
	/**
	 * CreditAmount
	 * ??‘é??
	 */
	private String CreditAmount = "";
	
	/**
	 * CreditCheckCode
	 * ??†å®¶æª¢æŸ¥ç¢?
	 */
	private String CreditCheckCode = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??–å?—MerchantID ??ˆä?œç‰¹åº—ç·¨???(?”±ecPay??ä??)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * è¨­å?šMerchantID ??ˆä?œç‰¹åº—ç·¨???(?”±ecPay??ä??)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * ??–å?—CreditRefundId ä¿¡ç”¨?¡??ˆæ?Šå–®???
	 * @return CreditRefundId
	 */
	public String getCreditRefundId() {
		return CreditRefundId;
	}
	/**
	 * è¨­å?šCreditRefundId ä¿¡ç”¨?¡??ˆæ?Šå–®???
	 * @param creditRefundId
	 */
	public void setCreditRefundId(String creditRefundId) {
		CreditRefundId = creditRefundId;
	}
	/**
	 * ??–å?—CreditAmount ??‘é??
	 * @return CreditAmount
	 */
	public String getCreditAmount() {
		return CreditAmount;
	}
	/**
	 * è¨­å?šCreditAmount ??‘é??
	 * @param creditAmount
	 */
	public void setCreditAmount(String creditAmount) {
		CreditAmount = creditAmount;
	}
	/**
	 * ??–å?—CreditCheckCode ??†å®¶æª¢æŸ¥ç¢?
	 * @return CreditCheckCode
	 */
	public String getCreditCheckCode() {
		return CreditCheckCode;
	}
	/**
	 * è¨­å?šCreditCheckCode ??†å®¶æª¢æŸ¥ç¢?
	 * @param creditCheckCode
	 */
	public void setCreditCheckCode(String creditCheckCode) {
		CreditCheckCode = creditCheckCode;
	}
	@Override
	public String toString() {
		return "QureyTradeObj [MerchantID=" + MerchantID + ", CreditRefundId=" + CreditRefundId + ", CreditAmount="
				+ CreditAmount + ", CreditCheckCode=" + CreditCheckCode + "]";
	}
}
