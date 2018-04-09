package com.kygo.service.constants;

public class RequestCode {


    public static final class FH {
        //泛华接口requestName
        public static final String getAgreementAreas = "fanhuabaowang.getAgreementAreas";//泛华保网获取accessToken
        public static final String getProviders = "fanhuabaowang.getProviders";//获取开通车险投保的地市列表
        public static final String createTaskA = "fanhuabaowang.createTaskA";//创建报价A
        public static final String createTaskB = "fanhuabaowang.createTaskB";//创建报价（标准接口）
        public static final String queryCarModelInfos = "fanhuabaowang.queryCarModelInfos";//查询车型信息
        public static final String updateTask = "fanhuabaowang.updateTask";//修改报价/投保数据
        public static final String submitQuote = "fanhuabaowang.submitQuote";//提交报价任务
        public static final String submitInsure = "fanhuabaowang.submitInsure";//提交核保任务
        public static final String recognizeImage = "fanhuabaowang.recognizeImage";//影像识别
        public static final String uploadImage = "fanhuabaowang.uploadImage";//影像上传
        public static final String pay = "fanhuabaowang.pay";//支付请求
        public static final String queryPlatInfo = "fanhuabaowang.queryPlatInfo";//查询平台信息
    }

    public static final class WX {
        public static final String weixinAuthUrl = "WeixinAuthUrl";//微信认证url
        public static final String getticket = "weixin.getticket";//获得jsapi_ticket
        public static final String getAccessTokenJs = "weixin.getAccessTokenJs";//获得getAccessTokenJs
    }

}
