package com.github.binarywang.demo.wechat.controller;

import com.google.common.reflect.ClassPath;
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
    @RequestMapping("/bindphone")
    public String bindPhone(String openid) {
        return "<a href='https://www.baidu.com'>baidu.com(" + openid + ")</a>";
    }
}
