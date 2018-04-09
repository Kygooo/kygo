package com.kygo.service.constants;

public class RedisKey {

    public static final long TOKEN_TIMEOUT = 30 * 24 * 60 * 60;//30天

    public static final long LOGIN_SMS_TIMEOUT = 60;//1分钟

    public static final long UNBIND_SMS_TIMEOUT = 60;//1分钟

    public static final String KEY_TEST = "test";

    /**
     * 系统配置key前缀
     */
    public static final String KEY_SYSTEM_CONFIG_PREFIX = "system_config";

    /**
     * 短信模板key前缀
     */
    public static final String KEY_SMS_TEMPLATE_PREFIX = "sms_template";

    /**
     * 字典key前缀(根据type)
     */
    public static final String KEY_DICTIONARY_TYPE_PREFIX = "dict_type";

    /**
     * 字典key前缀(根据name)
     */
    public static final String KEY_DICTIONARY_NAME_PREFIX = "dict_name";

    /**
     * 用户登录前缀
     */
    public static final String CUSTOMER_LOGIN_TOKEN = "customer_id_token";

    public static final String TOKEN_CUSTOMER_ID_PREFIX = "token_customer_id";

    public static final String CUSTOMER_ID_TASK_ID = "task_id";

    public static final String CUSTOMER_ID_CREATE_A_DTO = "createAdto_customer_id";

    public static final String CUSTOMER_ID_INSURE_RELATION_ID = "insure_relation_id_customer_id";

    public static final String STATUS_TASK_ID_PREFIX = "taskId_status";
    /**
     * 泛华保网accessToken前缀
     */
    public static final String FANHUA_ACCESS_TOKEN = "fanhua_access_token";

    /**
     * 泛华保网私钥
     */
    public static final String FANHUA_PRIVATE_KEY = "fanhua_private_key";

    /**
     * 登录手机验证码前缀
     */
    public static final String LOGIN_SMS_PREFIX = "customer_login_sms";

    /**
     * 解绑手机验证码前缀
     */
    public static final String UNBIND_SMS_PREFIX = "customer_unbind_sms";

    /**
     * 注册手机验证码前缀
     */
    public static final String REGISTER_SMS_PREFIX = "customer_register_sms";

    /**
     * 广告
     */
    public static final String KEY_SPEAD_TYPE_PREFIX = "spead_type";

    /**
     * 车俩查询信息前缀
     */
    public static final String CAR_QUERY_CONDITION_KEY_PREFIX = "car_search_condition";

    /**
     * 任务ID对应的对象KEY
     */
    public static final String CAR_QUERY_TASK_ID_CAR_SEARCH = "task_id:car_search_key";

    /**
     * 完善车辆信息KEY
     */
    public static final String CUSTOMER_INPUT_CAR_INFO_PREFIX = "customer_input_car_info";

    public static final String KEY_OFFER_ID_BY_TASK_ID = "offer_id_by_task_id";

    public static final String KEY_SEACH_CAR_INFO_BY_REQUEST_ID = "insure_are_code_by_task_id";

    /**
     * 获取开通车险的城市列表
     */
    public static final String CITY_LIST_OPENED_CAR_INSURE_PREFIX = "city_list_opened_car_insure";
    
    /**
     * 查询所有的报价状态列表
     */
    public static final String QUERY_OFFER_STATUS_PREFIX = "offer_all_status_list_task_id" ;
    
    /**
     * 微信token
     */
    public static final String WX_JS_ACCESS_TOKEN = "wx_js_access_token";
    public static final String WX_JS_TICTECT = "wx_js_api_ticket";

}
