package com.tangmo.yiliao.controller.webController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.AppVersion;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-9-4
 * @description 系统版本
 */
@Api("system系统版本")
@RestController
@RequestMapping("/version")
public class VersionController extends BaseController {

    /**
     * @api {GET} /version/getVersion 获取app最新版本
     * @apiGroup Version
     * @apiVersion 0.0.1
     * @apiDescription 获取app最新版本
     * @apiParam {Version} version 版本对象
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"0",
     *                     "msg": "请求成功",
     *                     "data":{
     *                         vNumber:"版本号",
     *                         vContent:"更新内容",
     *                         onlineTime:"更新时间"
     *                     }}
     */
    @GetMapping("/getVersion")
    public Result getVersion(){
        return versionService.getVersion();
    }

    //获取app所有版本
    @PostMapping("/getVersionAll")
    public Result getVersionAll(@RequestBody SelectUser selectUser){
        return versionService.getVersionAll(selectUser);
    }

    //获取app所有版本数量
    @PostMapping("/getVersionAllCount")
    public Result getVersionAllCount(@RequestBody SelectUser selectUser){
        return versionService.getVersionAllCount(selectUser);
    }

    //删除当前版本
    @GetMapping("/delVersion/{vid}")
    public Result delVersion(@PathVariable String vid){
        return versionService.delVersion(vid);
    }

    //添加app版本
    @PostMapping("/addVersion")
    public Result addVersion(@RequestBody AppVersion appVersion){
        return versionService.addVersion(appVersion);
    }

    //修改app版本
    @PostMapping("/updVersion")
    public Result updVersion(@RequestBody AppVersion appVersion){
      return versionService.updVersion(appVersion);

    }
}
