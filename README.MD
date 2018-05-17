### 本Demo基于Spring Boot构建，实现微信第三方平台开发功能。

#### 本项目为weixin-java-tools的Demo演示程序，更多信息请查阅：https://github.com/Wechat-Group/weixin-java-tools

[![Build Status](https://travis-ci.org/Wechat-Group/weixin-java-open-demo.svg?branch=master)](https://travis-ci.org/Wechat-Group/weixin-java-open-demo)
-----------------------

## 使用步骤：
1. 配置：修改`/src/main/resources/application.yml` ，根据自己需要填写相关配置（需要注意的是：yml文件内的属性冒号后面的文字之前需要加空格，可参考已有配置，否则属性会设置不成功）；	
1. 运行Java程序：`com.github.binarywang.demo.wechat.WxOpenDemoApplication`；
1. 配置微信第三方平台中的授权事件接收URL：http://my-domain/notify/receive_ticket 
1. 配置微信第三方平台中的公众号消息与事件接收URL http://my-domain/notify/$APPID$/callback
1. 首次启动后需要 等待收到 微信推送的 component_verify_ticket 后才可以使用接口 （在第三方平台创建审核通过后，微信服务器每隔10分钟会向第三方的消息接收地址推送一次component_verify_ticket，用于获取第三方平台接口调用凭据）
1. 浏览器访问：http://my-domain/api/auth/goto_auth_url_show 点击 go 跳转到微信授权页面 扫码授权
