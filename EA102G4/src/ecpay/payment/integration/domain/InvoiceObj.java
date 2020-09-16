package ecpay.payment.integration.domain;

/**
 * ??‹ç?‹ç™¼ç¥¨ç‰©ä»?
 * @author mark.chiu
 *
 */
public class InvoiceObj {
	
	/**
	 * RelateNumber
	 * ??ˆä?œç‰¹åº—è‡ªè¨‚ç·¨???
	 */
	private String RelateNumber = "";
	
	/**
	 * CustomerID
	 * å®¢æˆ¶ä»????
	 */
	private String CustomerID = "";
	
	/**
	 * CustomerIdentifier
	 * çµ±ä?ç·¨è??
	 */
	private String CustomerIdentifier = "";
	
	/**
	 * CustomerName
	 * å®¢æˆ¶??ç¨±
	 */
	private String CustomerName = "";
	
	/**
	 * CustomerAddr
	 * å®¢æˆ¶?œ°??
	 */
	private String CustomerAddr = "";
	
	/**
	 * CustomerPhone
	 * å®¢æˆ¶??‹æ?Ÿè?Ÿç¢¼
	 */
	private String CustomerPhone = "";
	
	/**
	 * CustomerEmail
	 * å®¢æˆ¶?›»å­ä¿¡ç®?
	 */
	private String CustomerEmail = "";
	
	/**
	 * ClearanceMark
	 * ?šé?œæ–¹å¼?
	 */
	private String ClearanceMark = "";
	
	/**
	 * TaxType
	 * èª²ç?…é?åˆ¥
	 */
	private String TaxType = "";
	
	/**
	 * CarruerType
	 * è¼‰å…·é¡åˆ¥
	 */
	private String CarruerType = "";
	
	/**
	 * CarruerNum
	 * è¼‰å…·ç·¨è??
	 */
	private String CarruerNum = "";
	
	/**
	 * Donation
	 * ??è?ˆè¨»è¨?
	 */
	private String Donation = "";
	
	/**
	 * LoveCode
	 * ??›å?ƒç¢¼
	 */
	private String LoveCode = "";
	
	/**
	 * Print
	 * ??—å°è¨»è??
	 */
	private String Print = "";
	
	/**
	 * InvoiceItemName
	 * ??†å?å?ç¨±
	 */
	private String InvoiceItemName = "";
	
	/**
	 * InvoiceItemCount
	 * ??†å?æ•¸???
	 */
	private String InvoiceItemCount = "";
	
	/**
	 * InvoiceItemWord
	 * ??†å?å–®ä½?
	 */
	private String InvoiceItemWord = "";
	
	/**
	 * InvoiceItemPrice
	 * ??†å?åƒ¹? ¼
	 */
	private String InvoiceItemPrice = "";
	
	/**
	 * InvoiceItemTaxType
	 * ??†å?èª²ç¨…åˆ¥
	 */
	private String InvoiceItemTaxType = "";
	
	/**
	 * InvoiceRemark
	 * ??™è¨»
	 */
	private String InvoiceRemark = "";
	
	/**
	 * DelayDay
	 * å»¶é²å¤©æ•¸
	 */
	private String DelayDay = "";
	
	/**
	 * InvType
	 * å­—è?Œé?åˆ¥
	 */
	private String InvType = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??–å?—RelateNumber ??ˆä?œç‰¹åº—è‡ªè¨‚ç·¨???
	 * @return RelateNumber
	 */
	public String getRelateNumber() {
		return RelateNumber;
	}
	/**
	 * è¨­å?šRelateNumber ??ˆä?œç‰¹åº—è‡ªè¨‚ç·¨???
	 * @param relateNumber
	 */
	public void setRelateNumber(String relateNumber) {
		RelateNumber = relateNumber;
	}
	/**
	 * ??–å?—CustomerID å®¢æˆ¶ä»????
	 * @return CustomerID
	 */
	public String getCustomerID() {
		return CustomerID;
	}
	/**
	 * è¨­å?šCustomerID å®¢æˆ¶ä»????
	 * @param customerID
	 */
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	/**
	 * ??–å?—CustomerIdentifier çµ±ä?ç·¨è??
	 * @return CustomerIdentifier
	 */
	public String getCustomerIdentifier() {
		return CustomerIdentifier;
	}
	/**
	 * è¨­å?šCustomerIdentifier çµ±ä?ç·¨è??
	 * @param customerIdentifier
	 */
	public void setCustomerIdentifier(String customerIdentifier) {
		CustomerIdentifier = customerIdentifier;
	}
	/**
	 * ??–å?—CustomerName å®¢æˆ¶??ç¨±
	 * @return CustomerName
	 */
	public String getCustomerName() {
		return CustomerName;
	}
	/**
	 * è¨­å?šCustomerName å®¢æˆ¶??ç¨±
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	/**
	 * ??–å?—CustomerAddr å®¢æˆ¶?œ°??
	 * @return CustomerAddr
	 */
	public String getCustomerAddr() {
		return CustomerAddr;
	}
	/**
	 * è¨­å?šCustomerAddr å®¢æˆ¶?œ°??
	 * @param customerAddr
	 */
	public void setCustomerAddr(String customerAddr) {
		CustomerAddr = customerAddr;
	}
	/**
	 * ??–å?—CustomerPhone å®¢æˆ¶??‹æ?Ÿè?Ÿç¢¼
	 * @return CustomerPhone
	 */
	public String getCustomerPhone() {
		return CustomerPhone;
	}
	/**
	 * è¨­å?šCustomerPhone å®¢æˆ¶??‹æ?Ÿè?Ÿç¢¼
	 * @param customerPhone
	 */
	public void setCustomerPhone(String customerPhone) {
		CustomerPhone = customerPhone;
	}
	/**
	 * ??–å?—CustomerEmail å®¢æˆ¶?›»å­ä¿¡ç®?
	 * @return CustomerEmail
	 */
	public String getCustomerEmail() {
		return CustomerEmail;
	}
	/**
	 * è¨­å?šCustomerEmail å®¢æˆ¶?›»å­ä¿¡ç®?
	 * @param customerEmail
	 */
	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}
	/**
	 * ??–å?—ClearanceMark ?šé?œæ–¹å¼?
	 * @return ClearanceMark
	 */
	public String getClearanceMark() {
		return ClearanceMark;
	}
	/**
	 * è¨­å?šClearanceMark ?šé?œæ–¹å¼?
	 * @param clearanceMark
	 */
	public void setClearanceMark(String clearanceMark) {
		ClearanceMark = clearanceMark;
	}
	/**
	 * ??–å?—TaxType èª²ç?…é?åˆ¥
	 * @return TaxType
	 */
	public String getTaxType() {
		return TaxType;
	}
	/**
	 * è¨­å?šTaxType èª²ç?…é?åˆ¥
	 * @param taxType
	 */
	public void setTaxType(String taxType) {
		TaxType = taxType;
	}
	/**
	 * ??–å?—CarruerType è¼‰å…·é¡åˆ¥
	 * @return CarruerType
	 */
	public String getCarruerType() {
		return CarruerType;
	}
	/**
	 * è¨­å?šCarruerType è¼‰å…·é¡åˆ¥
	 * @param carruerType
	 */
	public void setCarruerType(String carruerType) {
		CarruerType = carruerType;
	}
	/**
	 * ??–å?—CarruerNum è¼‰å…·ç·¨è??
	 * @return CarruerNum
	 */
	public String getCarruerNum() {
		return CarruerNum;
	}
	/**
	 * è¨­å?šCarruerNum è¼‰å…·ç·¨è??
	 * @param carruerNum
	 */
	public void setCarruerNum(String carruerNum) {
		CarruerNum = carruerNum;
	}
	/**
	 * ??–å?—Donation ??è?ˆè¨»è¨?
	 * @return Donation
	 */
	public String getDonation() {
		return Donation;
	}
	/**
	 * è¨­å?šDonation ??è?ˆè¨»è¨?
	 * @param donation
	 */
	public void setDonation(String donation) {
		Donation = donation;
	}
	/**
	 * ??–å?—LoveCode ??›å?ƒç¢¼
	 * @return LoveCode
	 */
	public String getLoveCode() {
		return LoveCode;
	}
	/**
	 * è¨­å?šLoveCode ??›å?ƒç¢¼
	 * @param loveCode
	 */
	public void setLoveCode(String loveCode) {
		LoveCode = loveCode;
	}
	/**
	 * ??–å?—Print ??—å°è¨»è??
	 * @return Print
	 */
	public String getPrint() {
		return Print;
	}
	/**
	 * è¨­å?šPrint ??—å°è¨»è??
	 * @param print
	 */
	public void setPrint(String print) {
		Print = print;
	}
	/**
	 * ??–å?—InvoiceItemName ??†å?å?ç¨±
	 * @return InvoiceItemName
	 */
	public String getInvoiceItemName() {
		return InvoiceItemName;
	}
	/**
	 * è¨­å?šInvoiceItemName ??†å?å?ç¨±
	 * @param invoiceItemName
	 */
	public void setInvoiceItemName(String invoiceItemName) {
		InvoiceItemName = invoiceItemName;
	}
	/**
	 * ??–å?—InvoiceItemCount ??†å?æ•¸???
	 * @return InvoiceItemCount
	 */
	public String getInvoiceItemCount() {
		return InvoiceItemCount;
	}
	/**
	 * è¨­å?šInvoiceItemCount ??†å?æ•¸???
	 * @param invoiceItemCount
	 */
	public void setInvoiceItemCount(String invoiceItemCount) {
		InvoiceItemCount = invoiceItemCount;
	}
	/**
	 * ??–å?—InvoiceItemWord ??†å?å–®ä½?
	 * @return InvoiceItemWord
	 */
	public String getInvoiceItemWord() {
		return InvoiceItemWord;
	}
	/**
	 * è¨­å?šInvoiceItemWord ??†å?å–®ä½?
	 * @param invoiceItemWord
	 */
	public void setInvoiceItemWord(String invoiceItemWord) {
		InvoiceItemWord = invoiceItemWord;
	}
	/**
	 * ??–å?—InvoiceItemPrice ??†å?åƒ¹? ¼
	 * @return InvoiceItemPrice
	 */
	public String getInvoiceItemPrice() {
		return InvoiceItemPrice;
	}
	/**
	 * è¨­å?šInvoiceItemPrice ??†å?åƒ¹? ¼
	 * @param invoiceItemPrice
	 */
	public void setInvoiceItemPrice(String invoiceItemPrice) {
		InvoiceItemPrice = invoiceItemPrice;
	}
	/**
	 * ??–å?—InvoiceItemTaxType ??†å?èª²ç¨…åˆ¥
	 * @return InvoiceItemTaxType
	 */
	public String getInvoiceItemTaxType() {
		return InvoiceItemTaxType;
	}
	/**
	 * è¨­å?šInvoiceItemTaxType ??†å?èª²ç¨…åˆ¥
	 * @param invoiceItemTaxType
	 */
	public void setInvoiceItemTaxType(String invoiceItemTaxType) {
		InvoiceItemTaxType = invoiceItemTaxType;
	}
	/**
	 * ??–å?—InvoiceRemark ??™è¨»
	 * @return InvoiceRemark
	 */
	public String getInvoiceRemark() {
		return InvoiceRemark;
	}
	/**
	 * è¨­å?šInvoiceRemark ??™è¨»
	 * @param invoiceRemark
	 */
	public void setInvoiceRemark(String invoiceRemark) {
		InvoiceRemark = invoiceRemark;
	}
	/**
	 * ??–å?—DelayDay å»¶é²å¤©æ•¸
	 * @return DelayDay
	 */
	public String getDelayDay() {
		return DelayDay;
	}
	/**
	 * è¨­å?šDelayDay å»¶é²å¤©æ•¸
	 * @param delayDay
	 */
	public void setDelayDay(String delayDay) {
		DelayDay = delayDay;
	}
	/**
	 * ??–å?—InvType å­—è?Œé?åˆ¥
	 * @return InvType
	 */
	public String getInvType() {
		return InvType;
	}
	/**
	 * è¨­å?šInvType å­—è?Œé?åˆ¥
	 * @param invType
	 */
	public void setInvType(String invType) {
		InvType = invType;
	}
	@Override
	public String toString() {
		return "InvoiceObj [RelateNumber=" + RelateNumber + ", CustomerID=" + CustomerID + ", CustomerIdentifier="
				+ CustomerIdentifier + ", CustomerName=" + CustomerName + ", CustomerAddr=" + CustomerAddr
				+ ", CustomerPhone=" + CustomerPhone + ", CustomerEmail=" + CustomerEmail + ", ClearanceMark="
				+ ClearanceMark + ", TaxType=" + TaxType + ", CarruerType=" + CarruerType + ", CarruerNum=" + CarruerNum
				+ ", Donation=" + Donation + ", LoveCode=" + LoveCode + ", Print=" + Print + ", InvoiceItemName="
				+ InvoiceItemName + ", InvoiceItemCount=" + InvoiceItemCount + ", InvoiceItemWord=" + InvoiceItemWord
				+ ", InvoiceItemPrice=" + InvoiceItemPrice + ", InvoiceItemTaxType=" + InvoiceItemTaxType
				+ ", InvoiceRemark=" + InvoiceRemark + ", DelayDay=" + DelayDay + ", InvType=" + InvType + "]";
	}
}
