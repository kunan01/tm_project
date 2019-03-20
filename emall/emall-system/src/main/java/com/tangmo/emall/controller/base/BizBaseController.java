package com.tangmo.emall.controller.base;

import com.tangmo.emall.service.*;

import javax.annotation.Resource;

/**
 * @author Chamber
 * @date 2019/1/9.
 * @Description
 */
public class BizBaseController extends BaseController {

    @Resource
    protected FileService fileService;

    @Resource
    protected SlidesService slidesService;

    @Resource
    protected ProductCommentService productCommentService;

    @Resource
    protected CateGoryService cateGoryService;

    @Resource
    protected ShopPropService shopPropService;

    @Resource
    protected ProductService productService;

    @Resource
    protected ShopParamService shopParamService;

    @Resource
    protected DistributionService distributionService;

    @Resource
    protected AdvertisingService advertisingService;

    @Resource
    protected TrendService trendService;

    @Resource
    protected SysUserService sysUserService;

    @Resource
    protected SupportService supportService;

    @Resource
    protected HelpService helpService;
}
