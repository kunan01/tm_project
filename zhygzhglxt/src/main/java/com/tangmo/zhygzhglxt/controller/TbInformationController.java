package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbInformation;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengge on 2018/11/1.
 */
@Api("文章信息的接口")
@RestController
@RequestMapping("/information")
public class TbInformationController extends BaseController {


    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据文章code查找", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationCode", value = "文章的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/findInformationByCode")
    public Result findInformationByCode(String informationCode) {
        return tbInformationService.findInformationByCode(informationCode);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据文章code概念性删除文章", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationCode", value = "文章的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/deleteByInformationCode")
    public Result deleteByInformationCode(String informationCode) {
        return tbInformationService.deleteByInformationCode(informationCode);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据二级栏目前台模糊分页查询", notes = "前台分页查询是根据访问量来排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊查询参数,必填：false", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "twoClassifyCode", value = "二级菜单code,必填：false", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页,必填：false", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "显示多少条参数,必填：false", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping(value = "/appFindPage")
    public Result appFindPage(String name, String twoClassifyCode, Integer pageNo, Integer pageSize) {
        return tbInformationService.appFindPage(name, twoClassifyCode, pageNo, pageSize);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据一级菜单编号查找文章", notes = "根据一级菜单编号查找文章及其搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "模糊查询参数,必填：false", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页,必填：false", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "显示多少条参数,必填：false", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "classifCode", value = "一级菜单ID,必填：true", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/findClassifyCode")
    public Map findClassifyId(String classifCode, Integer pageNo, Integer pageSize, String title) {

        Result result = tbInformationService.findClassifyCode(classifCode, pageNo, pageSize, title);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());

        return map;
    }

//    @SuppressWarnings("rawtypes")
//    @ApiOperation(value="浏览记录",notes="根据用户ID获取浏览记录")
//    @GetMapping(value="/queryBrowsingHistory/{userId}")
//    public Result queryBrowsingHistory(@PathVariable Integer userId){
//        return tbInformationService.queryBrowsingHistory(userId);
//    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "单个添加文章信息", notes = "")
    @ApiImplicitParam(name = "tbInformation", value = "文章信息实体", required = true, dataType = "TbInformation")
    @PostMapping(value = "/addInformation")
    public Result addInformation(@RequestBody TbInformation tbInformation) {

        return tbInformationService.addInformation(tbInformation);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据文章的唯一标识code修改文章", notes = "")
    @ApiImplicitParam(name = "tbInformation", value = "文章信息实体", required = true, dataType = "TbInformation")
    @PutMapping(value = "/updateInformation")
    public Result modifyInfomation(@RequestBody TbInformation tbInformation) {

        return tbInformationService.modifyInfomation(tbInformation);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据一级栏目的唯一标识查找二级栏目", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classifCode", value = "一级栏目的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/getTwoClassifyCode")
    public Result getTwoClassifyCode(String classifCode) {

        return tbInformationService.getTwoClassifyCode(classifCode);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "查询所有的一级栏目", notes = "")
    @GetMapping(value = "/getOneClassifyCode")
    public Result getOneClassifyCode() {

        return tbInformationService.getOneClassifyCode();
    }
}
