//package com.tangmo.zhygzhglxt.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @author chengge
// * @date 18/1/15
// * @description 拦截器,匹配app登录和未登录两种状态下可访问的接口
// */
//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TokenInterceptor())
//                .addPathPatterns("/**")
//                /*
//                .excludePathPatterns("/amenities/**")
//                .excludePathPatterns("/history/**")
//                .excludePathPatterns("/appUser/**")
//                .excludePathPatterns("/bedType/**")
//                .excludePathPatterns("/consumption/**")
//                .excludePathPatterns("/distance/**")
//                .excludePathPatterns("/conv/comment/list/**")
//                .excludePathPatterns("/commodity/select")
//                .excludePathPatterns("/file/**")
//                .excludePathPatterns("/common/**")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/user/register")
//                .excludePathPatterns("/user/mobile/auth/*")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/pwd/reset")
//                .excludePathPatterns("/shop/goods/list/**")
//                .excludePathPatterns("/list/**")
//                .excludePathPatterns("/moments/list/**")*/
//                .excludePathPatterns("/zhygzhglxt/swagger-ui.html");
//
//    }
//}
