package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.CateGoryDao;
import com.tangmo.emall.entity.CateGory;
import com.tangmo.emall.entity.SortingWay;
import com.tangmo.emall.service.CateGoryService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("cateGoryService")
public class CateGoryServiceImpl implements CateGoryService {

    @Resource
    private CateGoryDao cateGoryDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public Result addCategory(CateGory cateGory) {
        try {

            if(cateGory == null || cateGory.getCategoryName() == null || cateGory.getCategoryLevel() == null || cateGory.getParentId() == null){
                return ResultUtil.paramError();
            }

            //校验当前分类是否存在
            CateGory cateGory1 = cateGoryDao.getCateGoryByNameAndLevel(cateGory.getCategoryName(),cateGory.getCategoryLevel());
            if(cateGory1 != null){
                return ResultUtil.dataError();
            }

            //添加分类
            cateGoryDao.addCateGory(cateGory);

            String key = "CateGoryList";
            jedisKeys.batchdel(key);

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("添加商品分类接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updCategory(CateGory cateGory) {
        try {

            //非空校验
            if(cateGory == null || cateGory.getCategoryName() == null || cateGory.getCategoryId() == null){
                return ResultUtil.paramError();
            }

            //校验当前分类是否存在
            CateGory cateGory1 = cateGoryDao.getCateGoryById(cateGory.getCategoryId());
            if(cateGory1 == null){
                return ResultUtil.dataNoError();
            }

            //修改分类
            cateGoryDao.updCategory(cateGory);

            String key = "CateGoryList";
            jedisKeys.batchdel(key);

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改商品分类接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCateGoryList(Integer state,Integer pageNo,Integer pageSize) {
        try {
            //非空校验
            if(state == null){
                return ResultUtil.paramError();
            }
            if(state == 1){
                if(pageNo == null || pageSize == null){
                    return ResultUtil.paramError();
                }
                PageHelper.startPage(pageNo,pageSize);
            }
            PageInfo<CateGory> page = new PageInfo<>(cateGoryDao.getCateGoryList(1));
            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取一级商品类别接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result queryCateGoryList() {
        try {
            List<CateGory> cateGoryList = cateGoryDao.getCateGoryList(1);
            if(cateGoryList != null){
                for (CateGory cateGory: cateGoryList) {
                    cateGory.setCateGoryList(cateGoryDao.getCateGoryListByPId(cateGory.getCategoryId()));
                }
            }
            return ResultUtil.success(cateGoryList);
        }catch (Exception e){
            System.out.println("获取全部商品分类信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCateGoryListByTwoLevel(Integer state, Integer parentId, Integer pageNo, Integer pageSize) {
        try {
            //非空校验
            if(state == null || parentId == null){
                return ResultUtil.paramError();
            }
            if(state == 1){

                if(pageNo == null || pageSize == null){
                    return ResultUtil.paramError();
                }

                PageHelper.startPage(pageNo,pageSize);
            }

            PageInfo<CateGory> page = new PageInfo<>(cateGoryDao.getCateGoryListByPId(parentId));
            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取二级商品类别接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delCateGory(Integer cId) {
        try {

            //非空校验
            if(cId == null){
                return ResultUtil.paramError();
            }
            //校验系统用户
            CateGory cateGory = cateGoryDao.getCateGoryById(cId);

            if(cateGory != null){

                if(cateGory.getCategoryLevel() != 3){

                    if(cateGory.getCategoryLevel() == 2){

                        //删除当前二级分类下的三级分类
                        cateGoryDao.delCateGoryListByPId(cId);

                    }else{

                        //查询当前一级分类下的所有二级分类
                        List<CateGory> cateGoryList = cateGoryDao.getCateGoryListByPId(cId);

                        if(cateGoryList != null){
                            for (int i = 0; i< cateGoryList.size();i++){

                                //删除当前二级分类下的所有三级分类
                                cateGoryDao.delCateGoryListByPId(cateGoryList.get(i).getCategoryId());

                            }
                            //删除当前一级分类下的所有二级分类
                            cateGoryDao.delCateGoryListByPId(cId);
                        }
                    }
                }

                cateGoryDao.delCateGory(cId);

                String key = "CateGoryList";
                jedisKeys.batchdel(key);
            }

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("删除商品分类接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelCateGory(CateGory cateGorys) {
        try {

            if(cateGorys == null || cateGorys.getCategoryIdList() == null){
                return ResultUtil.paramError();
            }

            if(cateGorys.getCategoryIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (int i = 0;i<cateGorys.getCategoryIdList().length;i++){
                //校验系统用户
                CateGory cateGory = cateGoryDao.getCateGoryById(cateGorys.getCategoryIdList()[i]);

                if(cateGory != null){

                    if(cateGory.getCategoryLevel() != 3){

                        if(cateGory.getCategoryLevel() == 2){

                            //删除当前二级分类下的三级分类
                            cateGoryDao.delCateGoryListByPId(cateGorys.getCategoryIdList()[i]);

                        }else{

                            //查询当前一级分类下的所有二级分类
                            List<CateGory> cateGoryList = cateGoryDao.getCateGoryListByPId(cateGorys.getCategoryIdList()[i]);

                            if(cateGoryList != null){
                                for (int j = 0; j< cateGoryList.size();j++){

                                    //删除当前二级分类下的所有三级分类
                                    cateGoryDao.delCateGoryListByPId(cateGoryList.get(j).getCategoryId());

                                }
                                //删除当前一级分类下的所有二级分类
                                cateGoryDao.delCateGoryListByPId(cateGorys.getCategoryIdList()[i]);
                            }
                        }
                    }

                    cateGoryDao.delCateGory(cateGorys.getCategoryIdList()[i]);
                }
            }

            String key = "CateGoryList";
            jedisKeys.batchdel(key);

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("删除商品分类接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getIsPType(Integer cId) {
        try {
            //非空校验
            if(cId == null){
                return ResultUtil.paramError();
            }

            CateGory cateGory = cateGoryDao.getCateGoryById(cId);
            if(cateGory != null){
                if(cateGory.getCategoryLevel() == 3){
                    return ResultUtil.success(false);
                }else{
                    List<CateGory> cateGoryList = cateGoryDao.getCateGoryListByPId(cId);
                    if(cateGoryList == null || cateGoryList.size() == 0){
                        return ResultUtil.success(false);
                    }
                    return ResultUtil.success(true);
                }
            }

            return ResultUtil.dataNoError();
        }catch (Exception e){
            System.out.println("校验当前分类是否存在子集分类接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addSortingWay(SortingWay sortingWay) {
        try {

            if(sortingWay == null || sortingWay.getTitle() == null || sortingWay.getSortingRules() == null || sortingWay.getSortingType() == null){
                return ResultUtil.paramError();
            }

            if(sortingWay.getTitle().equals("") || sortingWay.getSortingRules().equals("")){
                return ResultUtil.paramError();
            }

            cateGoryDao.addSortingWay(sortingWay);

            //清理缓存
            if(sortingWay.getSortingType().toString().equals("0")){
                String rediskey = "SortingWay0";
                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey);
                }
            }else{
                String rediskey = "SortingWay1";
                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey);
                }
            }

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("增加商品筛选规则接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updateSortingWay(SortingWay sortingWay) {
        try {

            if(sortingWay == null || sortingWay.getSortingId() == null){
                return ResultUtil.paramError();
            }

            if(sortingWay.getTitle() == null || sortingWay.getSortingRules() == null){
                return ResultUtil.success("修改成功");
            }

            SortingWay sortingWay1 = cateGoryDao.getSortingWayById(sortingWay.getSortingId());

            if(sortingWay1 == null){
                return ResultUtil.dataNoError();
            }

            cateGoryDao.updateSortingWay(sortingWay);

            //清理缓存
            if(sortingWay1.getSortingType().toString().equals("0")){
                String rediskey = "SortingWay0";
                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey);
                }
            }else{
                String rediskey = "SortingWay1";
                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey);
                }
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改商品筛选规则接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delSortingWay(Integer sId) {
        try {

            if(sId == null){
                return ResultUtil.paramError();
            }

            //校验规则是否存在
            SortingWay sortingWay =  cateGoryDao.getSortingWayById(sId);
            if(sortingWay == null){
                return ResultUtil.dataNoError();
            }

            //删除商品筛选规则
            cateGoryDao.delSortingWay(sId);

            //清理缓存
            if(sortingWay.getSortingType().toString().equals("0")){
                String rediskey = "SortingWay0";
                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey);
                }
            }else{
                String rediskey = "SortingWay1";
                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey);
                }
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除商品筛选规则接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelSortingWay(SortingWay sortingWay) {
        try {

            if(sortingWay == null || sortingWay.getSortingIdList() == null){
                return ResultUtil.paramError();
            }
            if(sortingWay.getSortingIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer sId: sortingWay.getSortingIdList()) {

                //校验规则是否存在
                SortingWay sortingWay1 =  cateGoryDao.getSortingWayById(sId);
                if(sortingWay1 != null){
                    //删除商品筛选规则
                    cateGoryDao.delSortingWay(sId);

                    //清理缓存
                    if(sortingWay1.getSortingType().toString().equals("0")){
                        String rediskey = "SortingWay0";
                        if(jedisKeys.exists(rediskey)){
                            jedisKeys.del(rediskey);
                        }
                    }else{
                        String rediskey = "SortingWay1";
                        if(jedisKeys.exists(rediskey)){
                            jedisKeys.del(rediskey);
                        }
                    }
                }
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除商品筛选规则接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getSortingWay(Integer sortingType,Integer pageNo,Integer pageSize) {
        try{
            if(pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }
            PageHelper.startPage(pageNo,pageSize);

            PageInfo<SortingWay> page = new PageInfo<>(cateGoryDao.getSortingWay(sortingType));

            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取排序方式接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
