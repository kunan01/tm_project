package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
public interface ArticleService {

    //首页搜索
    Result homeSearch(SelectUser selectUser);

    //获取首页部分文章
    Result getPartOfArticle(Integer start,Integer end);

    //获取我的问答
    Result getArticleById(String userId,Integer start,Integer end);

    //获取文章详情
    Result getArticleById(String aId,String userId);

    //每天修改验证码限制
    void updaucodeByDay();

    //每月修改验证码限制
    void updaucodeByMonth();

    //后台获取验证码异常账号
    Result getMesCode(SelectUser selectUser);

    //后台禁止或解除用户验证
    Result banMesCod(@PathVariable Integer type, @PathVariable String phone);
}
