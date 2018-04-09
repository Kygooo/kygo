package com.kygo.common.venum;

/**
 * 来源枚举
 * Created by lwy on 2017/12/27.
 */
public enum RegSourceEnum {

    IOS("iOS","iOS") ,

    ANDROID("Android","安卓"),

    WEB("Web","网页"),

    WEBCHAT("WebChat","微信");

    /**
     * 名称
     */
    private String value;

    /**
     * 描述
     */
    private String desc;


    private RegSourceEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
		return desc;
	}
}
