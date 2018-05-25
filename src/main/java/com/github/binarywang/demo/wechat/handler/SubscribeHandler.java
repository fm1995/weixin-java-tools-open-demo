package com.github.binarywang.demo.wechat.handler;

import com.github.binarywang.demo.wechat.builder.TextBuilder;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService()
        .userInfo(wxMessage.getFromUser(), null);

    if (userWxInfo != null) {
      // TODO 可以添加关注用户到本地
      String unionId = userWxInfo.getUnionId();
      String openId = userWxInfo.getOpenId();
      String appId = weixinService.getWxMpConfigStorage().getAppId();
      String getStr = "&unionId="+userWxInfo.getUnionId();
      getStr = getStr + "&openId="+userWxInfo.getOpenId();
      getStr = getStr + "&appId="+weixinService.getWxMpConfigStorage().getAppId();
      getStr = getStr + "&nickName="+userWxInfo.getNickname();
      getStr = getStr + "&city="+userWxInfo.getCity();
      getStr = getStr + "&country="+userWxInfo.getCountry();
      getStr = getStr + "&language="+userWxInfo.getLanguage();
      getStr = getStr + "&sex="+userWxInfo.getSex();
      getStr = getStr + "&avatar="+userWxInfo.getHeadImgUrl();

      String getUrl = getLinkByAppId(appId);
      if (getUrl.startsWith("http")) {
        weixinService.get(getUrl, getStr);
      }
    }
    WxMpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    if (responseResult != null) {
      return responseResult;
    }

    try {
      return new TextBuilder().build("感谢关注", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }
  private String getLinkByAppId(String AppId) {
    String Host = "";
    switch (AppId) { // 公众号的APPID
      case "wx407ff98f415361dc": // 医美客户管理系统
        Host = "https://gztest2g.uuyimei.com";
        break;
      case "111":
        break;
    }
    return Host + "/open-wechat/save-unionid";
  }
  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
      throws Exception {
    //TODO
    return null;
  }

}
