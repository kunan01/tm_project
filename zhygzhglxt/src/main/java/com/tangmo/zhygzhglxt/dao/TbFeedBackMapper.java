package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbFeedBack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbFeedBackMapper {

    /**
     * 根据主键删除反馈信息
     */
    int deleteByPrimaryKey(Integer fbId);

    /**
     * 解决反馈信息
     */
    int verifyFeedBack(@Param("fbId") Integer fbId);

    /**
     * 添加
     */
    int insert(TbFeedBack record);

    /**
     * 动态添加
     */
    int insertSelective(TbFeedBack record);

    /**
     * 根据主键查找
     */
    TbFeedBack selectByPrimaryKey(Integer fbId);

    /*
     * 查询反馈信息
     *fbState：传0 查询未读 传1查询已读 不传：查询全部
     * */
    List<TbFeedBack> selFeedBack(@Param("name") String name, @Param("fbState") Integer fbState);

    /**
     * 根据主键修改
     */
    int updateByPrimaryKeySelective(TbFeedBack record);

    /**
     * 修改
     */
    int updateByPrimaryKey(TbFeedBack record);
}