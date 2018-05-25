package com.github.binarywang.demo.wechat.controller;

import com.github.binarywang.demo.wechat.service.WxOpenServiceDemo;
import com.google.common.reflect.ClassPath;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by gz000172 on 2018/5/23.
 */
@RestController
@RequestMapping("html")
// https://shwx1.uuyimei.com/html/bingphone
public class HtmlController extends BaseController{
    @Autowired
    protected WxOpenServiceDemo wxOpenService;
    @RequestMapping("/bindphone")
    public String bindPhone(String openid) {
        return "<a href='https://www.baidu.com'>baidu.com(" + openid + ")</a>";
    }

}
