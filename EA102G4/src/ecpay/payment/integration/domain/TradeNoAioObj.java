package ecpay.payment.integration.domain;

/**
 * ä¸‹è?‰å?ˆä?œç‰¹åº—å?å¸³åª’é?”æ?”ç‰©ä»?
 * @author mark.chiu
 *
 */
public class TradeNoAioObj {
	
	/**
	 * MerchantID
	 * ??ˆä?œç‰¹åº—ç·¨???(?”±ç¶ ç?Œæ?ä??)
	 */
	private String MerchantID = "";
	
	/**
	 * DateType
	 * ?Ÿ¥è©¢æ—¥??Ÿé?åˆ¥
	 */
	private String DateType = "";
	
	/**
	 * BeginDate
	 * ?Ÿ¥è©¢é?‹å?‹æ—¥???
	 */
	private String BeginDate = "";
	
	/**
	 * EndDate
	 * ?Ÿ¥è©¢ç?æ?Ÿæ—¥???
	 */
	private String EndDate = "";
	
	/**
	 * PaymentType
	 * ä»˜æ¬¾?–¹å¼?
	 */
	private String PaymentType = "";
	
	/**
	 * PlatformStatus
	 * è¨‚å–®é¡å??
	 */
	private String PlatformStatus = "";
	
	/**
	 * PaymentStatus
	 * ä»˜æ¬¾?????
	 */
	private String PaymentStatus = "";
	
	/**
	 * AllocateStatus
	 * ?’¥æ¬¾ç????
	 */
	private String AllocateStatus = "";
	
	/**
	 * MediaFormatted
	 * CSV? ¼å¼?
	 */
	private String MediaFormated = "";
	
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
	 * ??–å?—DateType ?Ÿ¥è©¢æ—¥??Ÿé?åˆ¥
	 * @return DateType
	 */
	public String getDateType() {
		return DateType;
	}
	/**
	 * è¨­å?šDateType ?Ÿ¥è©¢æ—¥??Ÿé?åˆ¥
	 * @param dateType
	 */
	public void setDateType(String dateType) {
		DateType = dateType;
	}
	/**
	 * ??–å?—BeginDate ?Ÿ¥è©¢é?‹å?‹æ—¥???
	 * @return BeginDate
	 */
	public String getBeginDate() {
		return BeginDate;
	}
	/**
	 * è¨­å?šBeginDate ?Ÿ¥è©¢é?‹å?‹æ—¥???
	 * @param beginDate
	 */
	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}
	/**
	 * ??–å?—EndDate ?Ÿ¥è©¢ç?æ?Ÿæ—¥???
	 * @return EndDate
	 */
	public String getEndDate() {
		return EndDate;
	}
	/**
	 * è¨­å?šEndDate ?Ÿ¥è©¢ç?æ?Ÿæ—¥???
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	/**
	 * ??–å?—PaymentType ä»˜æ¬¾?–¹å¼?
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * è¨­å?šPaymentType ä»˜æ¬¾?–¹å¼?
	 * @param paymentType
	 */
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	/**
	 * ??–å?—PlatformStatus è¨‚å–®é¡å??
	 * @return PlatformStatus
	 */
	public String getPlatformStatus() {
		return PlatformStatus;
	}
	/**
	 * è¨­å?šPlatformStatus è¨‚å–®é¡å??
	 * @param platformStatus
	 */
	public void setPlatformStatus(String platformStatus) {
		PlatformStatus = platformStatus;
	}
	/**
	 * ??–å?—PaymentStatus ä»˜æ¬¾?????
	 * @return PaymentStatus
	 */
	public String getPaymentStatus() {
		return PaymentStatus;
	}
	/**
	 * è¨­å?šPaymentStatus ä»˜æ¬¾?????
	 * @param paymentStatus
	 */
	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
	}
	/**
	 * ??–å?—AllocateStatus ?’¥æ¬¾ç????
	 * @return AllocateStatus
	 */
	public String getAllocateStatus() {
		return AllocateStatus;
	}
	/**
	 * è¨­å?šAllocateStatus ?’¥æ¬¾ç????
	 * @param allocateStatus
	 */
	public void setAllocateStatus(String allocateStatus) {
		AllocateStatus = allocateStatus;
	}
	/**
	 * ??–å?—MediaFormatted CSV? ¼å¼?
	 * @return MediaFormatted
	 */
	public String getMediaFormated() {
		return MediaFormated;
	}
	/**
	 * è¨­å?šMediaFormatted CSV? ¼å¼?
	 * @param mediaFormated
	 */
	public void setMediaFormated(String mediaFormated) {
		MediaFormated = mediaFormated;
	}
	@Override
	public String toString() {
		return "TradeNoAioObj [MerchantID=" + MerchantID + ", DateType=" + DateType + ", BeginDate=" + BeginDate
				+ ", EndDate=" + EndDate + ", PaymentType=" + PaymentType + ", PlatformStatus=" + PlatformStatus
				+ ", PaymentStatus=" + PaymentStatus + ", AllocateStatus=" + AllocateStatus + ", MediaFormated="
				+ MediaFormated + "]";
	}
}
