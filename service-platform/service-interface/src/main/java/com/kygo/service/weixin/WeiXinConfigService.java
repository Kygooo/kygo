package com.kygo.service.weixin;

import com.kygo.service.dto.weixin.WeiXinSinatureConfigDTO;

/**
 * 微信通过config接口注入权限验证配置
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115
 * @author Keith Wang (王 杰)
 * @email wangjie01vcredit.com
 * @date 2017年9月5日
 * @version 1.0
 */
public interface WeiXinConfigService {
	
	WeiXinSinatureConfigDTO getSinatureConfig(String url) throws Exception;
	
	String getAccessToken() throws Exception;

}
