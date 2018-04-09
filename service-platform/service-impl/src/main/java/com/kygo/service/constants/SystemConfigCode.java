package com.kygo.service.constants;

public class SystemConfigCode {

    /**
     * 系统配置令牌
     */
    public static final String SYSTEM_CONFIG_TOKEN = "system.config.token";
    
    /**
     * 保险生效日期显示的方式
     */
    public static final String INSURE_DATE_PATTERN = "insure_date_pattern";

    /**
     * 系统配置发送短信URL
     */
    public static final String SMS_SEND_URL = "sms.send.url";

    /**
     * 发送短信方式
     */
    public static final String SMS_SEND_BY = "sms.send.by";
    
    /**
     * 服务器地址
     */
    public static final String SERVER_URL = "server.url";
    
    /**
     * 设置报价状态多少分钟没有回调就认为报价失败
     */
    public static final String LIMIT_OFFER_STATUS_CLOSE ="limit_offer_status_close";

    /**
     * FTP上传图片父路径的code
     */
    public static final String FTP_IMAGE_PARENT_PATH = "ftp.image.parent.path";

    /**
     * FTP上传文档父路径的code
     */
    public static final String FTP_DOC_PARENT_PATH = "ftp.doc.parent.path";

    /**
     * FTP图片url前缀
     */
    public static final String FTP_IMAGE_URL_PREIFIX = "ftp.image.url.prefix";

    /**
     * FTP文档url前缀
     */
    public static final String FTP_DOC_URL_PREIFIX = "ftp.doc.parent.prefix";

    /**
     * 泛华保网 channelId
     */
    public static final String FH_CHANNEL_ID = "fanhua.channelId";

    /**
     * 泛华保网 channelUserId
     */
    public static final String FH_CHANNEL_USER_ID = "fanhua.channelId";

    /**
     * 泛华保网 channelSecret
     */
    public static final String FH_CHANNEL_SECRET = "fanhua.channelSecret";

    /**
     * 泛华保网 私钥路径
     */
    public static final String FH_PRIVATE_KEY_PATH = "fanhua.privateKey.path";

    /**
     * 泛华保网 支付回调地址
     */
    public static final String FH_PAY_RETURN_URL = "fanhua.returnUrl";

    /**
     * 查询车型信息每页数量
     */
    public static final String FH_QUERY_CAR_MODEL_INFO_PAGE_SIZE = "query.car.model.info.pageSize";

    /**
     * 京A 区域码
     */
    public static final String FH_INSUREAREACODE_DEFAULT_JING_A = "insureAreaCode.default.jingA";
    
    public static final String FH_INSUREAREACODE = "insureAreaCode.default";

    /**
     * 默认的身份类型，身份证号
     */
    public static final String IDCARD_TYPE_DEFAULT = "idcard_type_default";
    
    /**
     * 用户最多绑定的次数
     */
    public static final String CUSTOMER_BIND_CAR_MAX = "bind_car_max_time";

    /**
     * WeixinAppid
     */
    public static final String WEI_XIN_APP_ID = "WeixinAppid";

    /**
     * WeixinSecret
     */
    public static final String WEI_XIN_SECRET = "WeixinSecret";

	/*------------------------------------------------------------------------------
		vbs
	-------------------------------------------------------------------------------- */
	/**
	 * 风控云认证Appid
	 */
	public static final String RMCLOUD_OAUTH_APPID = "rmcloud.oauth.appid";

	/**
	 * 风控云认证Appsecret
	 */
	public static final String RMCLOUD_OAUTH_APPSECRET = "rmcloud.oauth.appsecret";

	/**
	 * 验卡请求来源
	 */
	public static final String RMCLOUD_CHECK_CARD_SOURCE = "rmcloud.check.card.source";

    /**
     * 收货地址限制
     */
    public static final String RECV_ADDR_LIMIT = "recvaddr.limit";

    /**
     * 销售邀请链接
     */
    public static final String USER_INVITATION_CODE_URL = "url.user.invitation.code";

    /**
     * 销售邀请链接
     */
    public static final String EMPLOYEE_INVITATION_CODE_URL = "url.employee.invitation.code";

    /**
     * SSO登录链接
     */
    public static final String SSO_LOGIN_URL = "url.sso.login";

    /**
     * SSO员工信息链接
     */
    public static final String SSO_SERVICE_VALIDATE = "url.sso.service.validate";
}
