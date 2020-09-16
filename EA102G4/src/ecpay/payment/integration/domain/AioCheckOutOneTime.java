package ecpay.payment.integration.domain;

/**
 * ?”¢??Ÿä¿¡?”¨?¡ä¸?æ¬¡ä?˜æ?…è?‚å–®?‰©ä»?
 * @author mark.chiu
 *
 */
public class AioCheckOutOneTime {
	
	/**
	 * MerchantID
	 * ??ˆä?œç‰¹åº—ç·¨???(?”±ç¶ ç?Œæ?ä??)
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??ˆä?œç‰¹åº—äº¤??“ç·¨???(?”±??ˆä?œç‰¹åº—æ?ä??)ï¼Œè©²äº¤æ?“ç·¨??Ÿä?å¯??è??
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * MerchantTradeDate
	 * ??ˆä?œç‰¹åº—äº¤??“æ?‚é??
	 */
	private String MerchantTradeDate = "";
	
	/**
	 * PaymentType
	 * äº¤æ?“é?å??
	 */
	private String PaymentType = "aio";
	
	/**
	 * TotalAmount
	 * äº¤æ?“é?‘é??
	 */
	private String TotalAmount = "";
	
	/**
	 * TradeDesc
	 * äº¤æ?“æ?è¿°
	 */
	private String TradeDesc = "";
	
	/**
	 * ItemName
	 * ??†å?å?ç¨±
	 */
	private String ItemName = "";
	
	/**
	 * ReturnURL
	 * ä»˜æ¬¾å®Œæ?é?šçŸ¥??å‚³ç¶²å?
	 */
	private String ReturnURL = "";
	
	/**
	 * ChoosePayment
	 * ?¸??‡é?è¨­ä»˜æ¬¾?–¹å¼?
	 */
	private String ChoosePayment = "Credit";
	
	/**
	 * ClientBackURL
	 * Clientç«¯è?”å?å?ˆä?œç‰¹åº—ç³»çµ±ç?„æ?‰é?•é??çµ?
	 */
	private String ClientBackURL = "";
	
	/**
	 * ItemURL
	 * ??†å?éŠ·?”®ç¶²å?
	 */
	private String ItemURL = "";
	
	/**
	 * Remark
	 * ??™è¨»æ¬„ä??
	 */
	private String Remark = "";
	
	/**
	 * ChooseSubPayment
	 * ?¸??‡é?è¨­ä»˜æ¬¾å­é?…ç›®
	 */
	private String ChooseSubPayment = "";
	
	/**
	 * OrderResultURL
	 * Clientç«¯å?å‚³ä»˜æ¬¾çµæ?œç¶²??
	 */
	private String OrderResultURL = "";
	
	/**
	 * NeedExtraPaidInfo
	 * ?˜¯?¦??è¦é?å?–ç?„ä?˜æ¬¾è³‡è??
	 */
	private String NeedExtraPaidInfo = "";
	
	/**
	 * DeviceSource
	 * è£ç½®ä¾†æ??
	 */
	private String DeviceSource = "";
	
	/**
	 * IgnorePayment
	 * ?š±??ä?˜æ¬¾?–¹å¼?
	 */
	private String IgnorePayment = "";
	
	/**
	 * PlatformID
	 * ?‰¹ç´„å?ˆä?œå¹³?°??†ä»£???(?”±ç¶ ç?Œæ?ä??)
	 */
	private String PlatformID = "";
	
	/**
	 * InvoiceMark
	 * ?›»å­ç™¼ç¥¨é?‹ç?‹è¨»è¨?
	 */
	private String InvoiceMark = "";
	
	/**
	 * EncryptType
	 * CheckMacValue?? å?†é?å??
	 */
	private String EncryptType = "1";
	
	/**
	 * Redeem
	 * ä¿¡ç”¨?¡?˜¯?¦ä½¿ç”¨ç´…åˆ©??˜æŠµ
	 */
	private String Redeem = "";
	
	/**
	 * UnionPay
	 * ?˜¯?¦?‚º???¯?¡äº¤æ??
	 */
	private String UnionPay = "";
	
	/**
	 * Language
	 * èªç³»è¨­å??
	 */
	private String Language = "";
	
	/**
	 * StoreID
	 * ??ˆä?œç‰¹åº—å?†å?—ä»£ç¢¼ï?Œæ?ä?›å?ˆä?œç‰¹åº—å¡«?…¥åº—å®¶ä»?ç¢¼ä½¿?”¨
	 */
	private String StoreID = "";
	
	/**
	 * CustomField1
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??1ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField1 = "";
	
	/**
	 * CustomField2
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??2ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField2 = "";
	
	/**
	 * CustomField3
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??3ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField3 = "";
	
	/**
	 * CustomField4
	 * ?‡ªè¨‚å?ç¨±æ¬„ä??4ï¼Œæ?ä?›å?ˆä?œå? å?†ä½¿?”¨è¨˜é?„ç”¨å®¢è£½??–ä½¿?”¨æ¬„ä??
	 */
	private String CustomField4 = "";
	
	/**
	 * BidingCard
	 * è¨˜æ†¶?¡??Ÿï?Œä½¿?”¨è¨˜æ†¶ä¿¡ç”¨?¡ 1:ä½¿ç”¨  0:ä¸ä½¿?”¨
	 */
	private String BidingCard = "";
	
	/**
	 * MerchantMemberID
	 * è¨˜æ†¶?¡??Ÿè?˜åˆ¥ç¢¼ï?Œç‚º??ˆä?œç‰¹åº—ä½¿??„æ?ƒå“¡è­˜åˆ¥ç¢¼ï?Œè‹¥è¨˜æ†¶?¡??Ÿç‚º1??‚ï?Œè?˜æ†¶?¡??Ÿè?˜åˆ¥ç¢¼ç‚ºå¿…å¡«
	 */
	private String MerchantMemberID = "";
	
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
	 * ??–å?—MerchantTradeNo ??ˆä?œç‰¹åº—äº¤??“ç·¨???(?”±??ˆä?œç‰¹åº—æ?ä??)ï¼Œè©²äº¤æ?“ç·¨??Ÿä?å¯??è??
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * è¨­å?šMerchantTradeNo ??ˆä?œç‰¹åº—äº¤??“ç·¨???(?”±??ˆä?œç‰¹åº—æ?ä??)ï¼Œè©²äº¤æ?“ç·¨??Ÿä?å¯??è??
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * ??–å?—MerchantTradeDate ??ˆä?œç‰¹åº—äº¤??“æ?‚é??
	 * @return MerchantTradeDate
	 */
	public String getMerchantTradeDate() {
		return MerchantTradeDate;
	}
	/**
	 * è¨­å?šMerchantTradeDate ??ˆä?œç‰¹åº—äº¤??“æ?‚é?“ï?Œè?‹ä»¥ yyyy/MM/dd HH:mm:ss? ¼å¼å¸¶?…¥
	 * @param merchantTradeDate
	 */
	public void setMerchantTradeDate(String merchantTradeDate) {
		MerchantTradeDate = merchantTradeDate;
	}
	/**
	 * ??–å?—PaymentType äº¤æ?“é?å??
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * è¨­å?šPaymentType äº¤æ?“é?å??
	 * @param paymentType
	 */
//	public void setPaymentType(String paymentType) {
//		PaymentType = paymentType;
//	}
	/**
	 * ??–å?—TotalAmount äº¤æ?“é?‘é??
	 * @return TotalAmount
	 */
	public String getTotalAmount() {
		return TotalAmount;
	}
	/**
	 * è¨­å?šTotalAmount äº¤æ?“é?‘é??
	 * @param totalAmount
	 */
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
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
	 * ??–å?—ItemName ??†å?å?ç¨±
	 * @return ItemName
	 */
	public String getItemName() {
		return ItemName;
	}
	/**
	 * è¨­å?šItemName ??†å?å?ç¨±
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	/**
	 * ??–å?—ReturnURL ä»˜æ¬¾å®Œæ?é?šçŸ¥??å‚³ç¶²å?
	 * @return ReturnURL
	 */
	public String getReturnURL() {
		return ReturnURL;
	}
	/**
	 * è¨­å?šReturnURL ä»˜æ¬¾å®Œæ?é?šçŸ¥??å‚³ç¶²å?
	 * @param returnURL
	 */
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	/**
	 * ??–å?—ChoosePayment ?¸??‡é?è¨­ä»˜æ¬¾?–¹å¼?
	 * @return ChoosePayment
	 */
	public String getChoosePayment() {
		return ChoosePayment;
	}
	/**
	 * è¨­å?šChoosePayment ?¸??‡é?è¨­ä»˜æ¬¾?–¹å¼?
	 * @param choosePayment
	 */
//	public void setChoosePayment(String choosePayment) {
//		ChoosePayment = choosePayment;
//	}
	/**
	 * ??–å?—ClientBackURL Clientç«¯è?”å?å?ˆä?œç‰¹åº—ç³»çµ±ç?„æ?‰é?•é??çµ?
	 * @return ClientBackURL
	 */
	public String getClientBackURL() {
		return ClientBackURL;
	}
	/**
	 * è¨­å?šClientBackURL Clientç«¯è?”å?å?ˆä?œç‰¹åº—ç³»çµ±ç?„æ?‰é?•é??çµ?
	 * @param clientBackURL
	 */
	public void setClientBackURL(String clientBackURL) {
		ClientBackURL = clientBackURL;
	}
	/**
	 * ??–å?—ItemURL ??†å?éŠ·?”®ç¶²å?
	 * @return ItemURL
	 */
	public String getItemURL() {
		return ItemURL;
	}
	/**
	 * è¨­å?? ItemURL ??†å?éŠ·?”®ç¶²å?
	 * @param itemURL
	 */
	public void setItemURL(String itemURL) {
		ItemURL = itemURL;
	}
	/**
	 * ??–å?—Remark ??™è¨»æ¬„ä??
	 * @return Remark
	 */
	public String getRemark() {
		return Remark;
	}
	/**
	 * è¨­å?šRemark ??™è¨»æ¬„ä??
	 * @param remark
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}
	/**
	 * ??–å?—ChooseSubPayment ?¸??‡é?è¨­ä»˜æ¬¾å­é?…ç›®
	 * @return ChooseSubPayment
	 */
	public String getChooseSubPayment() {
		return ChooseSubPayment;
	}
	/**
	 * è¨­å?šChooseSubPayment ?¸??‡é?è¨­ä»˜æ¬¾å­é?…ç›®
	 * @param chooseSubPayment
	 */
	public void setChooseSubPayment(String chooseSubPayment) {
		ChooseSubPayment = chooseSubPayment;
	}
	/**
	 * ??–å?—OrderResultURL Clientç«¯å?å‚³ä»˜æ¬¾çµæ?œç¶²??
	 * @return OrderResultURL
	 */
	public String getOrderResultURL() {
		return OrderResultURL;
	}
	/**
	 * è¨­å?šOrderResultURL Clientç«¯å?å‚³ä»˜æ¬¾çµæ?œç¶²??
	 * @param orderResultURL
	 */
	public void setOrderResultURL(String orderResultURL) {
		OrderResultURL = orderResultURL;
	}
	/**
	 * ??–å?—NeedExtraPaidInfo ?˜¯?¦??è¦é?å?–ç?„ä?˜æ¬¾è³‡è?? 
	 * @return NeedExtraPaidInfo
	 */
	public String getNeedExtraPaidInfo() {
		return NeedExtraPaidInfo;
	}
	/**
	 * è¨­å?šNeedExtraPaidInfo ?˜¯?¦??è¦é?å?–ç?„ä?˜æ¬¾è³‡è?? 
	 * @param needExtraPaidInfo
	 */
	public void setNeedExtraPaidInfo(String needExtraPaidInfo) {
		NeedExtraPaidInfo = needExtraPaidInfo;
	}
	/**
	 * ??–å?—DeviceSource è£ç½®ä¾†æ??
	 * @return DeviceSource
	 */
	public String getDeviceSource() {
		return DeviceSource;
	}
	/**
	 * è¨­å?šDeviceSource è£ç½®ä¾†æ??
	 * @param deviceSource
	 */
	public void setDeviceSource(String deviceSource) {
		DeviceSource = deviceSource;
	}
	/**
	 * ??–å?—IgnorePayment ?š±??ä?˜æ¬¾?–¹å¼?
	 * @return IgnorePayment
	 */
	public String getIgnorePayment() {
		return IgnorePayment;
	}
	/**
	 * è¨­å?šIgnorePayment ?š±??ä?˜æ¬¾?–¹å¼?
	 * @param ignorePayment
	 */
	public void setIgnorePayment(String ignorePayment) {
		IgnorePayment = ignorePayment;
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
	/**
	 * ??–å?—InvoiceMark ?›»å­ç™¼ç¥¨é?‹ç?‹è¨»è¨?
	 * @return InvoiceMark
	 */
	public String getInvoiceMark() {
		return InvoiceMark;
	}
	/**
	 * è¨­å?šInvoiceMark ?›»å­ç™¼ç¥¨é?‹ç?‹è¨»è¨?
	 * @param invoiceMark
	 */
	public void setInvoiceMark(String invoiceMark) {
		InvoiceMark = invoiceMark;
	}
	/**
	 * ??–å?—EncryptType CheckMacValue?? å?†é?å??
	 * @return EncryptType
	 */
	public String getEncryptType() {
		return EncryptType;
	}
	/**
	 * è¨­å?šEncryptType CheckMacValue?? å?†é?å??
	 * @param encryptType
	 */
//	public void setEncryptType(String encryptType) {
//		EncryptType = encryptType;
//	}
	/**
	 * ??–å?—Redeem ä¿¡ç”¨?¡?˜¯?¦ä½¿ç”¨ç´…åˆ©??˜æŠµ
	 * @return Redeem
	 */
	public String getRedeem() {
		return Redeem;
	}
	/**
	 * è¨­å?šRedeem ä¿¡ç”¨?¡?˜¯?¦ä½¿ç”¨ç´…åˆ©??˜æŠµ
	 * @param redeem
	 */
	public void setRedeem(String redeem) {
		Redeem = redeem;
	}
	/**
	 * ??–å?—UnionPay ?˜¯?¦?‚º???¯?¡äº¤æ??
	 * @return UnionPay
	 */
	public String getUnionPay() {
		return UnionPay;
	}
	/**
	 * è¨­å?šUnionPay ?˜¯?¦?‚º???¯?¡äº¤æ??
	 * @param unionPay
	 */
	public void setUnionPay(String unionPay) {
		UnionPay = unionPay;
	}
	/**
	 * ??–å?—Language èªç³»è¨­å??
	 * @return Language
	 */
	public String getLanguage() {
		return Language;
	}
	/**
	 * è¨­å?šLanguage èªç³»è¨­å??
	 * @param language
	 */
	public void setLanguage(String language) {
		Language = language;
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
	 * ??–å?—BidingCard è¨˜æ†¶?¡??Ÿï?Œä½¿?”¨è¨˜æ†¶ä¿¡ç”¨?¡ 1:ä½¿ç”¨  0:ä¸ä½¿?”¨
	 * @return BidingCard
	 */
	public String getBidingCard() {
		return BidingCard;
	}
	/**
	 * è¨­å?šBidingCard è¨˜æ†¶?¡??Ÿï?Œä½¿?”¨è¨˜æ†¶ä¿¡ç”¨?¡ 1:ä½¿ç”¨  0:ä¸ä½¿?”¨
	 * @param bidingCard
	 */
	public void setBidingCard(String bidingCard) {
		BidingCard = bidingCard;
	}
	/**
	 * ??–å?—MerchantMemberID è¨˜æ†¶?¡??Ÿè?˜åˆ¥ç¢¼ï?Œç‚º??ˆä?œç‰¹åº—ä½¿??„æ?ƒå“¡è­˜åˆ¥ç¢¼ï?Œè‹¥è¨˜æ†¶?¡??Ÿç‚º1??‚ï?Œè?˜æ†¶?¡??Ÿè?˜åˆ¥ç¢¼ç‚ºå¿…å¡«
	 * @return MerchantMemberID
	 */
	public String getMerchantMemberID() {
		return MerchantMemberID;
	}
	/**
	 * è¨­å?šMerchantMemberID è¨˜æ†¶?¡??Ÿè?˜åˆ¥ç¢¼ï?Œç‚º??ˆä?œç‰¹åº—ä½¿??„æ?ƒå“¡è­˜åˆ¥ç¢¼ï?Œè‹¥è¨˜æ†¶?¡??Ÿç‚º1??‚ï?Œè?˜æ†¶?¡??Ÿè?˜åˆ¥ç¢¼ç‚ºå¿…å¡«
	 * @param merchantMemberID
	 */
	public void setMerchantMemberID(String merchantMemberID) {
		MerchantMemberID = merchantMemberID;
	}
	@Override
	public String toString() {
		return "AioCheckOutOneTime [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo
				+ ", MerchantTradeDate=" + MerchantTradeDate + ", PaymentType=" + PaymentType + ", TotalAmount="
				+ TotalAmount + ", TradeDesc=" + TradeDesc + ", ItemName=" + ItemName + ", ReturnURL=" + ReturnURL
				+ ", ChoosePayment=" + ChoosePayment + ", ClientBackURL=" + ClientBackURL + ", ItemURL=" + ItemURL
				+ ", Remark=" + Remark + ", ChooseSubPayment=" + ChooseSubPayment + ", OrderResultURL=" + OrderResultURL
				+ ", NeedExtraPaidInfo=" + NeedExtraPaidInfo + ", DeviceSource=" + DeviceSource + ", IgnorePayment="
				+ IgnorePayment + ", PlatformID=" + PlatformID + ", InvoiceMark=" + InvoiceMark + ", EncryptType=" + EncryptType + ", Redeem=" + Redeem + ", UnionPay=" + UnionPay
				+ ", Language=" + Language + ", StoreID=" + StoreID + ", CustomField1=" + CustomField1
				+ ", CustomField2=" + CustomField2 + ", CustomField3=" + CustomField3 + ", CustomField4=" + CustomField4
				+ ", BidingCard=" + BidingCard + ", MerchantMemberID=" + MerchantMemberID + "]";
	}
}
