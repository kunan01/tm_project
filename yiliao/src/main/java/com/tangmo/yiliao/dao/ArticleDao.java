package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Mapper
public interface ArticleDao {

    //根据用户id文章
    List<SystemTheArticle> getPartOfArticleByUserId(@Param("saTitle") String saTitle);

    //获取首页部分文章
    List<SystemTheArticle> getPartOfArticle(@Param("start")Integer start,@Param("end") Integer end);

    //获取我的问答
    List<SystemTheArticle> getArticleById(@Param("userId")String userId,@Param("start")Integer start,@Param("end") Integer end);

    //根据科室id获取当前科室下排名前四的文章
    List<SystemTheArticle> getDepartmentArticleTop4ById(String dtId);

    //获取用户文章足迹(分页)
    List<SystemTheArticle> getUserArticleFootprint(@Param("userId") String userId,@Param("start") Integer start,@Param("end") Integer end);

    //通过文章id获取文章详情
    SystemTheArticle getDepartmentArticlById(String aId);

    //根据科室id获取推荐文章
    List<SystemTheArticle> getDepartmentArticleTop2T(@Param("dtId") String dtId,@Param("userId") String userId);

    //增加文章游览次数
    int updVisitNumber(String aId);

    //每天修改验证码限制
    int updaucodeByDay();

    //每个月修改验证码限制
    int updaucodeByMonth();

    //后台分页查询异常验证码电话
    List<String> getMesCode(@Param("phone") String phone,@Param("type") Integer type, @Param("start") Integer start, @Param("end") Integer end);

    //通过电话查询验证码使用数量
    Integer getMesCodecount(@Param("phone") String phone,@Param("type") Integer type);

    //查询已禁止用户
    List<String> getMesCodeBan(@Param("phone") String phone,@Param("start") Integer start, @Param("end") Integer end);

}
