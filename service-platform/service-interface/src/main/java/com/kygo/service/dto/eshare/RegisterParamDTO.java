package com.kygo.service.dto.eshare;

/**
 * Created by lwy on 2017/12/27.
 */
public class RegisterParamDTO {

    /**
     * 注册手机号
     */
   // @NotBlank
    private String regMobile;

    /**
     * 预留 手机密码
     */
    private String applyPassword;

    /**
     * 临时短信校验码
     */
  //  @NotBlank
    private String tmpVerify;

    /**
     * 邀请码
     */
  //  @NotBlank
    private String invitationCode;

    public String getRegMobile() {
        return regMobile;
    }

    public void setRegMobile(String regMobile) {
        this.regMobile = regMobile;
    }

    @Deprecated
    public String getApplyPassword() {
        return applyPassword;
    }

    @Deprecated
    public void setApplyPassword(String applyPassword) {
        this.applyPassword = applyPassword;
    }

    public String getTmpVerify() {
        return tmpVerify;
    }

    public void setTmpVerify(String tmpVerify) {
        this.tmpVerify = tmpVerify;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
