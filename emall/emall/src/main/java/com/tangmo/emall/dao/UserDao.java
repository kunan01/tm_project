package com.tangmo.emall.dao;

import com.tangmo.emall.entity.CustomerService;
import com.tangmo.emall.entity.Help;
import com.tangmo.emall.entity.MessageUs;
import com.tangmo.emall.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Chamber
 * @date 2019/1/9.
 * @Description
 */
@Repository
public interface UserDao {

    //增加用户
    int addUser(User user);

    //根据用户id修改用户资料
    int updUser(User user);

    //通过邮箱获取用户信息
    User getUserByEmail(String email);

    //通过id获取用户信息
    User findUserById(Integer userId);

    //通过facebookId获取用户信息
    User findUserByFacebookId(String facebookId);

    //添加用户反馈
    int addCustomerFeedback(MessageUs us);

    //获取用户当天反馈次数
    Integer getCustomerFeedbackCount(Integer userId);

    //获取客服服务信息
    List<CustomerService> getCustomerService();

    //通过级别查询帮助信息
    List<Help> getHelpListByLevel(Integer level);

    //通过父级id查询下级帮助信息
    List<Help> getHelpListByParentId(Integer parentId);

}
