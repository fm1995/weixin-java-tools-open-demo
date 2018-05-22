package com.github.binarywang.demo.wechat.controller;

import com.github.binarywang.demo.wechat.service.WxOpenServiceDemo;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz000172 on 2018/5/22.
 */
@RestController()
@RequestMapping("/template-message")
public class WechatTemplateMessageController extends BaseController{
    @Autowired
    protected WxOpenServiceDemo wxOpenService;
    @RequestMapping("/sendTemplate__")
    public String sendTemplate__() {
        try {
            wxOpenService.getRedis().set("tests", "55555555");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "test";
    }
    // 发送会员卡信息
    @RequestMapping("/cardTemplate")
    public String cardTemplate() {
        String AppId = "wx407ff98f415361dc";
        WxMpService wxMpService = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(AppId);
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setToUser("otDARwHUnftwC-22qf9HRpZ4unYg");
        wxMpTemplateMessage.setTemplateId("K5aEHJ7rwW4PrvfmaKVDrI5tx3Wie5_Q2-bxifMmSpo");
        wxMpTemplateMessage.setUrl("https://www.baidu.com");

        List<WxMpTemplateData> list = new ArrayList<>();
        WxMpTemplateData wxMpTemplateData = new WxMpTemplateData();
        wxMpTemplateData.setName("first");
        wxMpTemplateData.setColor("#409EFF");
        wxMpTemplateData.setValue("恭喜你成为会员！");
        list.add(wxMpTemplateData);

        WxMpTemplateData wxMpTemplateData1 = new WxMpTemplateData();
        wxMpTemplateData1.setName("cardNumber");
        wxMpTemplateData1.setColor("#409EFF");
        wxMpTemplateData1.setValue("150215555");
        list.add(wxMpTemplateData1);

        WxMpTemplateData wxMpTemplateData2 = new WxMpTemplateData();
        wxMpTemplateData2.setName("type");
        wxMpTemplateData2.setColor("#409EFF");
        wxMpTemplateData2.setValue("广州");
        list.add(wxMpTemplateData2);

        WxMpTemplateData wxMpTemplateData3 = new WxMpTemplateData();
        wxMpTemplateData3.setName("first");
        wxMpTemplateData3.setColor("#409EFF");
        wxMpTemplateData3.setValue("恭喜你成为会员！");
        list.add(wxMpTemplateData3);

        WxMpTemplateData wxMpTemplateData4 = new WxMpTemplateData();
        wxMpTemplateData4.setName("address");
        wxMpTemplateData4.setColor("#409EFF");
        wxMpTemplateData4.setValue("广州越秀区");
        list.add(wxMpTemplateData4);

        WxMpTemplateData wxMpTemplateData5 = new WxMpTemplateData();
        wxMpTemplateData5.setName("VIPName");
        wxMpTemplateData5.setColor("#509EFF");
        wxMpTemplateData5.setValue("VIPName");
        list.add(wxMpTemplateData5);

        WxMpTemplateData wxMpTemplateData6 = new WxMpTemplateData();
        wxMpTemplateData6.setName("expDate");
        wxMpTemplateData6.setColor("#609EFF");
        wxMpTemplateData6.setValue("2100-10-10");
        list.add(wxMpTemplateData6);

        WxMpTemplateData wxMpTemplateData7 = new WxMpTemplateData();
        wxMpTemplateData7.setName("remark");
        wxMpTemplateData7.setColor("#709EFF");
        wxMpTemplateData7.setValue("欢迎再次购买！");
        list.add(wxMpTemplateData7);

        wxMpTemplateMessage.setData(list);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            this.logger.info("发送模板消息了，内容为：" + wxMpTemplateMessage.toString());
        }catch (WxErrorException e){
            this.logger.error(e.getMessage());
        }
        return "发送模板消息";
    }

}
