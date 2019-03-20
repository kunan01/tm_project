package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ProductCommentDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.dao.UserDao;
import com.tangmo.emall.entity.ProductComment;
import com.tangmo.emall.entity.RsFile;
import com.tangmo.emall.entity.SortingWay;
import com.tangmo.emall.entity.User;
import com.tangmo.emall.service.ProductCommentService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("productCommentService")
public class ProductCommentServiceImpl implements ProductCommentService {

    @Resource
    private ProductCommentDao productCommentDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private FileDao fileDao;

    @Resource
    private UserDao userDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result addComment(ProductComment productComment) {
        try {
            //非空校验
            if(productComment.getContent() == null || productComment.getProductId() == null || productComment.getOrderId() == null
                    || productComment.getUserId() == null){
                return ResultUtil.paramError();
            }

            //校验用户
            User user = userDao.findUserById(productComment.getUserId());
            if(user == null){
                return ResultUtil.serviceError();
            }

            //用户状态验证
            if(user.getUserStatus().toString().equals("0")){
                return ResultUtil.userError();
            }

            //校验订单（预留）

            //订单状态以及订单中的用户id，商品id验证（预留）

            //图片校验
            if(productComment.getImgId() != null){
                String[] str = productComment.getImgId().split(",");
                for (int i = 0;i < str.length;i++){
                    //校验头像图片是否存在
                    RsFile rsFile = fileDao.getFileById(str[i]);
                    if(rsFile == null){
                        return ResultUtil.imgError();
                    }

                    //把头像图片改为已用状态
                    fileDao.updFile(str[i]);
                }
            }

            //添加评论
            productCommentDao.addComment(productComment);

            return ResultUtil.success("评论成功");
        }catch (Exception e){
            log.error("商品评论模块： '添加商品评论'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCommentList(Integer sType, Integer productId, Integer pageNo, Integer pageSize,Integer userId) {
        try {

            //非空校验
            if(sType == null || productId == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            SortingWay sortingWay = productDao.getSortingWayById(sType);
            if(sortingWay == null){
                return ResultUtil.error("排序方式验证失败");
            }

            PageHelper.startPage(pageNo, pageSize);

            //分页获取商品评论
            PageInfo<ProductComment> page = new PageInfo<ProductComment>(productCommentDao.getCommentList(sortingWay.getSortingRules(),productId,userId));

            //获取评论图片
            List<ProductComment> list = page.getList();
            if(list != null && list.size() != 0){
                for (int i = 0; i < list.size(); i++){

                    String img = list.get(i).getImgId();
                    if(img != null){
                        list.get(i).setImgList(img.split(","));
                    }
                }

                page.setList(list);
            }

            return ResultUtil.success(page);
        }catch (Exception e){
            log.error("商品评论模块： '获取商品评论'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }

    }

    @Override
    @Transactional
    public Result giveALike(Integer userId, Integer commentId) {
        try{

            //非空校验
            if(userId == null || commentId == null){
                return ResultUtil.paramError();
            }

            //判断用户是否已经点过赞了
            if(productCommentDao.getUserGiveCount(userId,commentId) != 0){
                return ResultUtil.giveError();
            }

            //增加获赞数量
            productCommentDao.giveALike(commentId);

            //增加点赞记录
            productCommentDao.addGive(userId,commentId);

            return ResultUtil.success("点赞成功");
        }catch (Exception e){
            log.error("商品评论模块： '点赞评论'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCommentSortingWay() {
        try{

            String rediskey = "SortingWay1";

            if(!jedisKeys.exists(rediskey)){

                List<SortingWay> sortingWays = productDao.getSortingWay(1);

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(sortingWays));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(sortingWays);
            }else{
                List<SortingWay> sortingWays = (List<SortingWay>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
                return ResultUtil.success(sortingWays);
            }
        }catch (Exception e){
            log.error("商品评论模块： '获取评论排序方式'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result canYouComment(Integer userId, Integer productId) {
        try{

            if(userId == null || productId == null){
                return ResultUtil.paramError();
            }
            String orderNo = productCommentDao.canYouComment(userId,productId);
            if(orderNo != null){
                return ResultUtil.success(orderNo);
            }else{
                return ResultUtil.success(false);
            }
        }catch (Exception e){
            log.error("商品评论模块： '用户是否可以对当前商品进行评论'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
