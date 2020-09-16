package ecpay.payment.integration.domain;

/**
 * ?•¶ChoosePayment?‚ºATM??‚ç?„å?–è?Ÿç?æ?œé?šçŸ¥?‰©ä»?
 * @author mark.chiu
 *
 */
public class ATMRequestObj {
	
	/**
	 * MerchantID
	 * ??ˆä?œç‰¹åº—ç·¨???
	 */
	private String MerchantID;
	
	/**
	 * MerchantTradeNo
	 * ??ˆä?œç‰¹åº—äº¤??“ç·¨???
	 */
	private String MerchantTradeNo;
	
	/**
	 * StoreID
	 * ??ˆä?œç‰¹åº—å?†å?—ä»£ç¢¼ï?Œæ?ä?›å?ˆä?œç‰¹åº—å¡«?…¥åº—å®¶ä»?ç¢¼ä½¿?”¨
	 */
	private String StoreID;
	
	/**
	 * RtnCode
	 * äº¤æ?“ç????
	 */
	private String RtnCode;
	
	/**
	 * RtnMsg
	 * äº¤æ?“è?Šæ¯
	 */
	private String RtnMsg;
	
	/**
	 * TradeNo
	 * ç¶ ç?Œç?„äº¤??“ç·¨???
	 */
	private String TradeNo;
	
	/**
	 * TradeAmt
	 * äº¤æ?“é?‘é??
	 */
	private String TradeAmt;

	/**
	 * PaymentType
	 * ??ˆä?œç‰¹åº—é¸??‡ç?„ä?˜æ¬¾?–¹å¼?
	 */
	private String PaymentType;
	
	/**
	 * PaymentTypeChargeFee
	 * ?šè·¯è²? 
	 */
	private String PaymentTypeChargeFee;
	
	/**
	 * TradeDate
	 * è¨‚å–®??ç?‹æ?‚é??
	 */
	private String TradeDate;
	
	/**
	 * SimulatePaid
	 * ?˜¯?¦?‚ºæ¨¡æ“¬ä»˜æ¬¾ 
	 */
	private String SimulatePaid;
	
	/**
	 * CustomField1
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??1ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField1;
	
	/**
	 * CustomField2
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??2ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField2;
	
	/**
	 * CustomField3
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??3ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField3;
	
	/**
	 * CustomField4
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??4ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField4;
	
	/**
	 * CheckMacValue
	 * æª¢æŸ¥ç¢?
	 */
	private String CheckMacValue;
	
	/**
	 * BankCode
	 * ç¹³è²»??è¡Œä»£ç¢?
	 */
	private String BankCode;
	
	/**
	 * vAccount
	 * ç¹³è²»??›æ“¬å¸³è??
	 */
	private String vAccount;
	
	/**
	 * ExpireDate
	 * ç¹³è²»??Ÿé??
	 */
	private String ExpireDate;
	
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
	 * @param MerchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * ??–å?—RtnCode äº¤æ?“ç????
	 * @return RtnCode
	 */
	public String getRtnCode() {
		return RtnCode;
	}
	/**
	 * è¨­å?šRtnCode äº¤æ?“ç????
	 * @param RtnCode
	 */
	public void setRtnCode(String rtnCode) {
		RtnCode = rtnCode;
	}
	/**
	 * ??–å?—RtnMsg äº¤æ?“è?Šæ¯
	 * @return RtnMsg
	 */
	public String getRtnMsg() {
		return RtnMsg;
	}
	/**
	 * è¨­å?šRtnMsg äº¤æ?“è?Šæ¯
	 * @param RtnMsg
	 */
	public void setRtnMsg(String rtnMsg) {
		RtnMsg = rtnMsg;
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
	 * @param TradeNo
	 */
	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}
	/**
	 * ??–å?—TradeAmt äº¤æ?“é?‘é??
	 * @return TradeAmt
	 */
	public String getTradeAmt() {
		return TradeAmt;
	}
	/**
	 * è¨­å?šTradeAmt äº¤æ?“é?‘é??
	 * @param TradeAmt
	 */
	public void setTradeAmt(String tradeAmt) {
		TradeAmt = tradeAmt;
	}
	/**
	 * ??–å?—PaymentType ??ˆä?œç‰¹åº—é¸??‡ç?„ä?˜æ¬¾?–¹å¼?
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * è¨­å?šPaymentType ??ˆä?œç‰¹åº—é¸??‡ç?„ä?˜æ¬¾?–¹å¼?
	 * @param PaymentType
	 */
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	/**
	 * ??–å?—TradeDate è¨‚å–®??ç?‹æ?‚é??
	 * @return TradeDate
	 */
	public String getTradeDate() {
		return TradeDate;
	}
	/**
	 * è¨­å?šTradeDate è¨‚å–®??ç?‹æ?‚é??
	 * @param TradeDate
	 */
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	/**
	 * ??–å?—CheckMacValue æª¢æŸ¥ç¢?
	 * @return CheckMacValue
	 */
	public String getCheckMacValue() {
		return CheckMacValue;
	}
	/**
	 * è¨­å?šCheckMacValue æª¢æŸ¥ç¢?
	 * @param CheckMacValue
	 */
	public void setCheckMacValue(String checkMacValue) {
		CheckMacValue = checkMacValue;
	}
	/**
	 * ??–å?—BankCode ç¹³è²»??è¡Œä»£ç¢?
	 * @return BankCode
	 */
	public String getBankCode() {
		return BankCode;
	}
	/**
	 * è¨­å?šBankCode ç¹³è²»??è¡Œä»£ç¢?
	 * @param BankCode
	 */
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	/**
	 * ??–å?—vAccount ç¹³è²»??›æ“¬å¸³è??
	 * @return vAccount
	 */
	public String getvAccount() {
		return vAccount;
	}
	/**
	 * è¨­å?švAccount ç¹³è²»??›æ“¬å¸³è??
	 * @param vAccount
	 */
	public void setvAccount(String vAccount) {
		this.vAccount = vAccount;
	}
	/**
	 * ??–å?—ExpireDate ç¹³è²»??Ÿé??
	 * @return ExpireDate
	 */
	public String getExpireDate() {
		return ExpireDate;
	}
	/**
	 * è¨­å?šExpireDate ç¹³è²»??Ÿé??
	 * @param ExpireDate
	 */
	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}
	/**
	 * ??–å?—StoreID ??ˆä?œç‰¹åº—å?†å?—ä»£ç¢¼ï?Œæ?ä?›å?ˆä?œç‰¹åº—å¡«?…¥åº—å®¶ä»?ç¢¼ä½¿?”¨
	 * @return StoreID
	 */
	public String getStoreID() {
		return StoreID;
	}
	/**
	 * è¨­å?šStoreID ??ˆä?œç‰¹åº—å?†å?—ä»£ç¢¼ï?Œæ?ä?›å?ˆä?œç‰¹åº—å¡«?…¥åº—å®¶ä»?ç¢¼ä½¿?”¨
	 * @param storeID
	 */
	public void setStoreID(String storeID) {
		StoreID = storeID;
	}
	/**
	 * ??–å?—CustomField1 ?‡ªè¨‚å?ç¨±æ¬„ä??1ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @return CustomField1
	 */
	public String getCustomField1() {
		return CustomField1;
	}
	/**
	 * è¨­å?šCustomField1 ?‡ªè¨‚å?ç¨±æ¬„ä??1ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @param customField1
	 */
	public void setCustomField1(String customField1) {
		CustomField1 = customField1;
	}
	/**
	 * ??–å?—CustomField2 ?‡ªè¨‚å?ç¨±æ¬„ä??2ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @return CustomField2
	 */
	public String getCustomField2() {
		return CustomField2;
	}
	/**
	 * è¨­å?šCustomField2 ?‡ªè¨‚å?ç¨±æ¬„ä??2ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @param customField2
	 */
	public void setCustomField2(String customField2) {
		CustomField2 = customField2;
	}
	/**
	 * ??–å?—CustomField3 ?‡ªè¨‚å?ç¨±æ¬„ä??3ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @return CustomField3
	 */
	public String getCustomField3() {
		return CustomField3;
	}
	/**
	 * è¨­å?šCustomField3 ?‡ªè¨‚å?ç¨±æ¬„ä??3ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @param customField3
	 */
	public void setCustomField3(String customField3) {
		CustomField3 = customField3;
	}
	/**
	 * ??–å?—CustomField4 ?‡ªè¨‚å?ç¨±æ¬„ä??4ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @return CustomField4
	 */
	public String getCustomField4() {
		return CustomField4;
	}
	/**
	 * è¨­å?šCustomField4 ?‡ªè¨‚å?ç¨±æ¬„ä??4ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 * @param customField4
	 */
	public void setCustomField4(String customField4) {
		CustomField4 = customField4;
	}
	/**
	 * ??–å?—PaymentTypeChargeFee ?šè·¯è²?
	 * @return PaymentTypeChargeFee
	 */
	public String getPaymentTypeChargeFee() {
		return PaymentTypeChargeFee;
	}
	/**
	 * è¨­å?šPaymentTypeChargeFee ?šè·¯è²?
	 * @param paymentTypeChargeFee
	 */
	public void setPaymentTypeChargeFee(String paymentTypeChargeFee) {
		PaymentTypeChargeFee = paymentTypeChargeFee;
	}
	/**
	 * ??–å?—SimulatePaid ?˜¯?¦?‚ºæ¨¡æ“¬ä»˜æ¬¾ 
	 * @return SimulatePaid
	 */
	public String getSimulatePaid() {
		return SimulatePaid;
	}
	/**
	 * è¨­å?šSimulatePaid ?˜¯?¦?‚ºæ¨¡æ“¬ä»˜æ¬¾ 
	 * @param simulatePaid
	 */
	public void setSimulatePaid(String simulatePaid) {
		SimulatePaid = simulatePaid;
	}
	@Override
	public String toString() {
		return "ATMRequestObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", StoreID="
				+ StoreID + ", RtnCode=" + RtnCode + ", RtnMsg=" + RtnMsg + ", TradeNo=" + TradeNo + ", TradeAmt="
				+ TradeAmt + ", PaymentType=" + PaymentType + ", PaymentTypeChargeFee=" + PaymentTypeChargeFee
				+ ", TradeDate=" + TradeDate + ", SimulatePaid=" + SimulatePaid + ", CustomField1=" + CustomField1
				+ ", CustomField2=" + CustomField2 + ", CustomField3=" + CustomField3 + ", CustomField4=" + CustomField4
				+ ", CheckMacValue=" + CheckMacValue + ", BankCode=" + BankCode + ", vAccount=" + vAccount
				+ ", ExpireDate=" + ExpireDate + "]";
	}
}
