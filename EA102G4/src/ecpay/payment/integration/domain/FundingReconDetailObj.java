package ecpay.payment.integration.domain;

/**
 * ä¸‹è?‰ä¿¡?”¨?¡?’¥æ¬¾å?å¸³è³‡æ?™æ?”ç‰©ä»?
 * @author mark.chiu
 *
 */
public class FundingReconDetailObj {
	
	/**
	 * MerchantID
	 * ??ƒå“¡ç·¨è??(?”±allPay??ä??)
	 */
	private String MerchantID = "";
	
	/**
	 * PayDateType
	 * ?Ÿ¥è©¢æ—¥??Ÿé?åˆ¥
	 */
	private String PayDateType = "";
	
	/**
	 * StartDate
	 * ?Ÿ¥è©¢é?‹å?‹æ—¥???
	 */
	private String StartDate = "";
	
	/**
	 * EndDate
	 * ?Ÿ¥è©¢ç?æ?Ÿæ—¥???
	 */
	private String EndDate = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??–å?—MerchantID ??ƒå“¡ç·¨è??(?”±allPay??ä??)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * è¨­å?šMerchantID ??ƒå“¡ç·¨è??(?”±allPay??ä??)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * ??–å?—PayDateType ?Ÿ¥è©¢æ—¥??Ÿé?åˆ¥
	 * @return PayDateType
	 */
	public String getPayDateType() {
		return PayDateType;
	}
	/**
	 * è¨­å?šPayDateType ?Ÿ¥è©¢æ—¥??Ÿé?åˆ¥
	 * @param payDateType
	 */
	public void setPayDateType(String payDateType) {
		PayDateType = payDateType;
	}
	/**
	 * ??–å?—StartDate ?Ÿ¥è©¢é?‹å?‹æ—¥???
	 * @return StartDate
	 */
	public String getStartDate() {
		return StartDate;
	}
	/**
	 * è¨­å?šStartDate ?Ÿ¥è©¢é?‹å?‹æ—¥???
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		StartDate = startDate;
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
	@Override
	public String toString() {
		return "FundingReconDetailObj [MerchantID=" + MerchantID + ", PayDateType=" + PayDateType + ", StartDate="
				+ StartDate + ", EndDate=" + EndDate + "]";
	}
}
