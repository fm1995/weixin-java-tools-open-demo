package com.github.binarywang.demo.wechat.handler;

import com.github.binarywang.demo.wechat.builder.TextBuilder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.membercard.NameValues;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by gz000172 on 2018/5/28.
 */
@Component
public class MemberCardHandler extends AbstractHandler{
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager){
        String AppId = weixinService.getWxMpConfigStorage().getAppId();
        String cardId = wxMessage.getCardId();
        String cardCode = wxMessage.getUserCardCode();
        String openId = wxMessage.getFromUser();
        try {
            WxMpMemberCardUserInfoResult info = weixinService.getMemberCardService().getUserInfo(cardId, cardCode);
            NameValues[] nameValues = info.getUserInfo().getCommonFieldList();
            String phone = "";
            for(NameValues item:nameValues) {
                if(item.getName().toUpperCase().equals("USER_FORM_INFO_FLAG_MOBILE")) {
                    phone = item.getValue();
                    break;
                }
            }
            // 获取微信用户基本信息
            WxMpUser userWxInfo = weixinService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);

            String getStr = "";
            getStr = getStr + "&unionId=0";
            getStr = getStr + "&nickName="+userWxInfo.getNickname();
            getStr = getStr + "&city="+userWxInfo.getCity();
            getStr = getStr + "&country="+userWxInfo.getCountry();
            getStr = getStr + "&language="+userWxInfo.getLanguage();
            getStr = getStr + "&sex="+userWxInfo.getSex();
            getStr = getStr + "&avatar="+userWxInfo.getHeadImgUrl();
            getStr = getStr + "&openId="+openId;
            getStr = getStr + "&appId="+AppId;
            getStr = getStr + "&phone=" + phone;
            String url = this.getLinkByAppId(AppId);
            this.logger.info("请求的地址：" + url);
            weixinService.get(url, getStr);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getLinkByAppId(String AppId) {
        String Host = "";
        switch (AppId) { // 公众号的APPID
            case "wx407ff98f415361dc": // 医美客户管理系统
//                Host = "https://gztest2g.uuyimei.com";
                Host = "http://127.0.0.1:8011";
                break;
            case "111":
                break;
        }
        return Host + "/open-wechat/save-phone";
    }



}
