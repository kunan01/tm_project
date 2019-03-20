package com.tangmo.yiliao.config;

public class PayZFBConfig {
    // 1.商户appid
    public static String APPID = "2016030301181082";
    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDigiZ1au6wjcNZc+alySVq8h547+DIkPXDU2dgqJGbrCFT+LvYz+eQQKPMtBG7+DKLF5kcu06vCyehl6zAL3rFB/ATiII9IfYiZ0VMgmVT3Jj0YZk5Y5k4zzYUMLz3o2xRyljVqRlYeqqb9o2vz/y8N8tJ7NiTvD0RDinPkKPIwLzCz4g5H1+4cRUAXAxpSA7bKfqAR22Cxyy8eXbOaqt49UBdbSDLwZ1PJBiVw6Nkyus/rXs9TzzUtmwpe5ODzerdE01rLouk7OPM2GPnCERe1N2HeWfW3XQwNdFLa23uEsPA+MEl7yorVfOI1SM1ZiYwu+nZIpk+7mQXtINJk+TvAgMBAAECggEBAKj6564ZRFC6sc3+RUfRM/UBA/K9Ijj+zBoYErtthpNYatS2b41kLSDU2F5SyBgvJaXWyQdCRYUZEUvgV6RYx4JOVbB82oy41WL9IBnf/a/+lq1aUnEZ+KkFDRcq9Q7aAGlUR8m+CYaUnMsVuMY8qBPSm/pek4RDnlsm8dJD2axdliboMOBtdnzVXHccChaw/bFjWQHRQ/E9pA/DoECha9wEEhMP5qSGS+AN83pxUX0xNZ0RSpCJatQd2jIZIGyf9f7oHxQlo5wZ1IvufrisMYAT4grDQfNo/QqUbTN+E5QgE7A+uaryi8hzCPr7GhwMQHTy/r2G1iB2cwS5KTqtzEECgYEA8qGpjy9N72rVdZr178qB19YJf1XyIFo2OaeXTxlilmv6ELw435m2Rs5m8BQnvyuf7yfsREdGnsRWzFqy65cGNkEY6GDpQUyQ6QzJ5lAfUHehHHYZlr8/pyNL4UpGcQTWFtpKsjkU6bsrcH8SCU1k8xyzjSQ/1bXqBbXPQYz8omkCgYEA7v0R/kVpr+tcHP7ak2amGwa2kzlb89GTtRRh5Knmx0xxN1d4cJb9cym/lxbU5tlgVe5f+Rp/Wl1RlF9dWJRUwB0RC7ukhkyPajSi+HO6wnPgaAqfm9v5HW+tuWys5jI1SHjrikxbS4s2C2prNsWPaE/aGSNbl557VYGlWJZqMZcCgYADLiFF0i3oVHMlznkownO/O3v2Yk8BI42QZeTy0XdcV1/4ti7cp2wfM3df8DOxKsY24UQUhQak8BffJ9Uuh1U6gtHrwdFs3K8NjvBah3RRnq/K6rVdJ3aTH0BQXKPduV2BAS13do4SnjRsjg3Td2M6WIb186Kuj2rY1lA4ILvy+QKBgQDepPehWgtW77516Nu6nNO8s+ldiP9oS2P/O8jjXYF5RHbpJt02jwP5yPgLiyK3GeZMx/GzQYNJrUx2HM362t2FyM57jsl8sFCfkgAN1Ms1ovx6EQOXETyotsUyzNLa9gV7yeMtiWs4Gq4zvmW5th2fAV1fM/h9bRDbnRdsS10NawKBgQCVPwL5UF29gmB/YqvexsMC0NtUL15yYE36shEGxhl267yZcrWT+Cc5uWB72aq7kTYyasZtPxrch1DVhi+wX22KnuTwEMAdySqSOwvP8mn9qDdj7KDx9uc6ZxcwIWxTsSULUTVFhYm50tZAFS1LglBcPJ25dLyDUQs4PAjCFfBYLQ==";
    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhh08fczSSo2wQPMGetAPPqEBg6/hGvMY/VyOtGHtlwvhyuSr5WXAwc4zzy8OyEwXog3B2j9n/GR8X3fTESJdKahedonxjy62HJWE6TI5VlbRUF2c3AfIc553909ZiMlIx6o48vSK6Ru5XNCTo3NwW0DGPOMzfzYG0tzn7fEYI+Xi0xY2ITwOSC0c6wn9Ro06CoeX8zW61szJIxoFKLOQ6gSROWxreBwDXLY0u2Ejj+afliF9oAzJNNTT2bb663smevZxofQgEssgZjM/yPrih2zYJ9Seks+F4hJWFfVmn/P+J0Bzb/VTYtgrwQ2EV1uy7pOxT4E8o3j2Xgo5Pz5pXwIDAQAB";
    // 4.服务器异步通知页面路径
    public static String notify_url = "http://www.yl120.cn:8080/pay/payTreasure/order/notify_url";
    //5.页面跳转同步通知页面路径
    public static String return_url = "http://www.yl120.cn:8080/pay/payTreasure/order/return_url";
    // 6.请求支付宝的网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 7.编码
    public static String CHARSET = "UTF-8";
    // 8.返回格式
    public static String FORMAT = "json";
    // 9.加密类型
    public static String SIGNTYPE = "RSA2";
}
