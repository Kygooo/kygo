package com.kygo.common.venum;

/**
 * Created by Administrator on 2017/12/28.
 */
public enum CustomerChannelEnum {

    OFFICIAL("Official","官方"),

    SPREAD("Spread","推广");

    /**
     *
     */
    private String value;

    private String desc;


    CustomerChannelEnum(String value, String desc) {
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
