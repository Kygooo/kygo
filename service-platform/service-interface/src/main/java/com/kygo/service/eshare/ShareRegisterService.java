package com.kygo.service.eshare;

import com.kygo.common.base.exception.BusinessException;
import com.kygo.service.dto.eshare.RegisterParamDTO;

/**
 *
 * 注册service
 * Created by lwy on 2017/12/27.
 */
public interface ShareRegisterService {

    /**
     * 注册短信
     * @param registerParamDTO
     */
   void register(RegisterParamDTO registerParamDTO) throws BusinessException;

    /**
     * 发送短息验证码
     * @param mobile
     */
   void sendVerify(String mobile) throws BusinessException;

}
