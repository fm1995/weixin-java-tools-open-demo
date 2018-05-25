package com.github.binarywang.demo.wechat.controller;

import com.github.binarywang.demo.wechat.service.WxOpenServiceDemo;
import com.github.binarywang.demo.wechat.utils.CardInfo;
import com.github.binarywang.demo.wechat.utils.JsonUtils;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.membercard.NameValues;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gz000172 on 2018/5/24.
 */
@RestController
@RequestMapping("/member-card")
public class MemberCardController extends BaseController {
    @Autowired
    protected WxOpenServiceDemo wxOpenService;
    @RequestMapping("/card-info")
    public String cardInfo() {
        String AppId = "wx407ff98f415361dc";
        String openId = "otDARwHUnftwC-22qf9HRpZ4unYg";
        String cardId = "ptDARwFT9HdfSbJnaq1qP1dcIt2s";
        WxMpService wxMpService = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(AppId);
        try {
            String code = this.getCardCode(wxMpService, openId, cardId);
            WxMpMemberCardUserInfoResult info = wxMpService.getMemberCardService().getUserInfo(cardId,code);
            NameValues[] nameValues = info.getUserInfo().getCommonFieldList();
            String phone = "";
            for (NameValues item:nameValues) {
                if (item.getName().equals("USER_FORM_INFO_FLAG_MOBILE")) {
                    phone = item.getValue();
                    break;
                }
            }
            return phone;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCardCode(WxMpService wxMpService, String openId, String cardId) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("card_id", cardId);
            jsonObject.addProperty("openid",openId);
            String postUrl = "https://api.weixin.qq.com/card/user/getcardlist";
            String responseContent = wxMpService.post(postUrl, jsonObject.toString());
            org.json.JSONObject jsonConfig = new org.json.JSONObject(responseContent);
            org.json.JSONArray cardList = jsonConfig.getJSONArray("card_list");
            org.json.JSONObject cardList2 =  (org.json.JSONObject)cardList.get(0);
            return cardList2.getString("code");
        }catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
