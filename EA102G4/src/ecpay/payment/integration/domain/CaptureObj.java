package ecpay.payment.integration.domain;

/**
 * ??ˆä?œç‰¹åº—ç”³è«‹æ’¥æ¬¾ç‰©ä»?
 * @author mark.chiu
 *
 */
public class CaptureObj {
	
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
	 * CaptureAMT
	 * ??ˆä?œç‰¹åº—ç”³è«‹æ’¥æ¬¾é?‘é??
	 */
	private String CaptureAMT = "";
	
	/**
	 * UserRefundAMT
	 * è¦é??æ¬¾çµ¦è²·æ–¹??„é?‘é??
	 */
	private String UserRefundAMT = "0";
	
	/**
	 * PlatformID
	 * ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???(?”±ç¶ ç?Œæ?ä??)
	 */
	private String PlatformID = "";
	
	/**
	 * Remark
	 * ??™è¨»
	 */
	private String Remark = "";
	
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
	 * ??–å?—CaptureAMT ??ˆä?œç‰¹åº—ç”³è«‹æ’¥æ¬¾é?‘é??
	 * @return CaptureAMT
	 */
	public String getCaptureAMT() {
		return CaptureAMT;
	}
	/**
	 * è¨­å?šCaptureAMT ??ˆä?œç‰¹åº—ç”³è«‹æ’¥æ¬¾é?‘é??
	 * @param captureAMT
	 */
	public void setCaptureAMT(String captureAMT) {
		CaptureAMT = captureAMT;
	}
	/**
	 * ??–å?—UserRefundAMT è¦é??æ¬¾çµ¦è²·æ–¹??„é?‘é??
	 * @return UserRefundAMT
	 */
	public String getUserRefundAMT() {
		return UserRefundAMT;
	}
	/**
	 * è¨­å?šUserRefundAMT è¦é??æ¬¾çµ¦è²·æ–¹??„é?‘é??
	 * @param userRefundAMT
	 */
//	public void setUserRefundAMT(String userRefundAMT) {
//		UserRefundAMT = userRefundAMT;
//	}
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
	/**
	 * ??–å?—Remark ??™è¨»
	 * @return Remark
	 */
	public String getRemark() {
		return Remark;
	}
	/**
	 * è¨­å?šRemark ??™è¨»
	 * @param remark
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "CaptureObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", CaptureAMT="
				+ CaptureAMT + ", UserRefundAMT=" + UserRefundAMT + ", PlatformID=" + PlatformID + ", Remark=" + Remark
				+ "]";
	}
}
