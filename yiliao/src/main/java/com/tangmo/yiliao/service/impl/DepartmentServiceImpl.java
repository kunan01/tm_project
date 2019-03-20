package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.ArticleDao;
import com.tangmo.yiliao.dao.DepartmentDao;
import com.tangmo.yiliao.dao.DoctorDao;
import com.tangmo.yiliao.dao.VideoDao;
import com.tangmo.yiliao.entity.SystemTheArticle;
import com.tangmo.yiliao.service.DepartmentService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    @Resource
    private VideoDao videoDao;

    @Resource
    private DoctorDao doctorDao;

    @Resource
    private ArticleDao articleDao;

    @Override
    public Result getPartOfDepartment() {
        return ResultUtil.success(departmentDao.getPartOfDepartment());
    }

    @Override
    public Result getDepartmentAll() {
        return ResultUtil.success(departmentDao.getDepartmentAll());
    }

    @Override
    public Result getDepartmentDetails(String dtId) {
        Map<String,Object> res = new HashMap<>();
        //视频
        res.put("video",videoDao.getDepartmentVideoTop4ById(dtId));
        //医生
        res.put("doctor",doctorDao.getDepartmentDoctorTop2ById(dtId));
        List<SystemTheArticle> systemTheArticles =  articleDao.getDepartmentArticleTop4ById(dtId);
        if(systemTheArticles != null){
            for (int i = 0;i < systemTheArticles.size();i++){
                systemTheArticles.get(i).setSaContent(systemTheArticles.get(i).getSaContent().replaceAll("\n","").replaceAll("\\<style>.*?</style>","").replaceAll("\\<.*?>",""));
            }
        }
        //文章
        res.put("article",systemTheArticles);
        return ResultUtil.success(res);
    }
}
