package com.tangmo.zhjy.app.enums;

public class PayConfig {
    // 1.商户appid
    public static String APPID = "2018122462685702";
    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjgOTcs+Wa6K0ypeLbn2kQO6ViNyEMo6lni5BrvMYAHER1i8J0qlJgYADQu9uTCCMZx5Er3NfXWSnnNSObNoA9afG91bdnn8pxT+IfSqv3oZe+hAAAQ8emz7GNCujlz6YIvTYzZF75Bcxv/c8gHldHIM6Z19XGF52HKcQ14fsEXilj2janK0uE5BelKniF3ZPbjEvnMyRFoaf/A4kycmCaJk0WBbWiGiwHbqWG1vzoc2R+2dDSRZtdF+VNfsNg55T4+khtKXPC8Y5tl2RgS5Vi5QHo7PZHd73BqzMBONeOLBgUz+e8kPhPMeLU8in0+TQMS7WRWXAWNzkUOwcnt3nDAgMBAAECggEBAJOtIoj470P4Lh8V143YMHPZ+6l286HP3H1KgBnfOdL1tRuaLPtkrtyfOX1Of6cQbWumZceBQ24c+Yz74Poawr81hoOYmXu9xoKLzUfIEQSr7jvES5/xmE1hQBONLAgNggBYrbDDUkiHZTwtuTtgTCJRTmmDmXC++agQIEkun6Mz21yipWHhsOFevKEpmtKjBVDQV05RU20OHytzPb54vJoWVN1AfFcj6a4Q++jIqjOkWwcyl1WzJN5cnLnSifitL+lN7cErHodIsx60u7iVd2zRBMcc73RYL5CY5xEGKlUbcwnXJVVqdndndhCid30EUcRXIvp10ORVxirU0gfSWjECgYEA0vMN+XeLzTy0W4ZLJ6hA17imr8tIJm5cTACHGRnOjWka6UYwV4abC5uJ53p2umQHejZVqFxAd52o8dbgOpMtp3Uw+2Js/1Lbq02l0+vsiZoNco0hKWXjU7N+HVtOIniE50SETnY8Nr2ttEDoDxJjlb/S8b1xRSFNkzLXfMjbo9sCgYEAxmvktpK/eN8QeHntKOT9uDSsaLSd0xsFfTyTcxGTTcRPTHWkTvWJUjEXFoA6K8sS7/sjT0inoUG0Ou3sTNa+LTM/+5rYNEx8nht30Xxapub2bHyixKU9vrXtAl5Js2n9Axg1N4onbq77VEKZu3bSxtvGjNFN4uvsPIMHlQYvWjkCgYBBuW66MjABow9CkOXrljWVwLdWgEybhLkHCQcOoAbF8mBrG+5F5u95v11JHNPPIQlfX3xyt0NywVF5AAze0ypfSVkYicMSDfvZNJkdHR2WhcP409XoMxGP65W4idEfVlN6RtO+KoJ6P8DWL37d60nRx3Fu1iprcoPb//Q5sc8VDwKBgBUgc8/EvEJgbd6GDOxnxWQRp7HWWV+a5vC4vHJqEqcbGd5oQEBwAFkSBQVOTeNP0gpfURkmAUM4PbpwjtJ3ovQpGsztZcvbwQEMp6tUdEqFGQPgGWyas4JU2JGB/JLv3BKyRyhMu3obg4MrPDcrpbGqwO9JceORfyvKP8psSCpxAoGBAKrC2U8th4FJ0XL98sMKPZdCgpWPCov0/VSQW4OZ+AvbawkKJu7U/lzOLNbRMMbTiuYqDG4chqYKO5SdlvOlK6eU8Uw2URJ2Buo/I9a/ZvQPH1mkGWd12bRIrjoIeCyzGzsXRprEqTgWr+jJYbhD00frg8hh0eryPcV1U9pAX/hH";
    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqa0Le6LNH4wUaTXZ46EkE8IyYVxnNdBW/L/cjD3Ut4iFQu2tzGpyiAyxGMVPezpAG0mWfrhfYdXhstaAPoDAMbhisvLpR8MOPCtsvIdLKWW2YJIBcC3bmus3y6qx+S8g2tyAOtrW/L/8B2ySXEAL2mBMhCJcMsGpsuCOWeYNEryZ3/SsBtnp/uQ9/p+1NlaBsboeKXUOdQFEFqxH6yvzZryKrf2yRon99FjGuaPf4gaIvChiclyAKnUwN8abKN4ab+3ADBVQr3gp+VFZ18H1rd92SY7m7IpnQG661Q1YwbUlm93k/kbwEFkgWGbvlaHCLKJim0jP4aRTYrIMiYGCPwIDAQAB";
    // 4.服务器异步通知页面路径
    public static String notify_url = "http://60.8.218.156:8090/app/treasure/order/notify_url";
    //5.页面跳转同步通知页面路径
    public static String return_url = "http://60.8.218.156:8090/app/treasure/order/return_url";
    // 6.请求支付宝的网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 7.编码
    public static String CHARSET = "UTF-8";
    // 8.返回格式
    public static String FORMAT = "json";
    // 9.加密类型
    public static String SIGNTYPE = "RSA2";
}
