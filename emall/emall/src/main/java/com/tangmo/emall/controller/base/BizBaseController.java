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
    protected UserService userService;

    @Resource
    protected PaypalService paypalService;

    @Resource
    protected FileService fileService;

    @Resource
    protected AddressService addressService;

    @Resource
    protected SlidesService slidesService;

    @Resource
    protected ProductCommentService productCommentService;

    @Resource
    protected ShopCartService shopCartService;

    @Resource
    protected CollectService collectService;

    @Resource
    protected ProductService productService;

    @Resource
    protected OrderService orderService;

    @Resource
    protected ActivityService activityService;

    @Resource
    protected PrescriptionService prescriptionService;
}
