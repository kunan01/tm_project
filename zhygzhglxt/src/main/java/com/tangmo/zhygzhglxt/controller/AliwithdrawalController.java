package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.dto.BalanceOrderDto;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.HttpClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chengge on 2019/1/14.
 */
@Api("支付宝提现")
@RestController
@RequestMapping("/aliwithdrawal")
public class AliwithdrawalController extends BaseController {

    @ApiOperation(value = "支付宝提现订单")
    @ApiImplicitParam(name = "balanceOrderDto", value = "余额订单Dto", required = true, dataType = "BalanceOrderDto")
    @PostMapping("/withdrawal/aliwithdrawalOrder")
    public Result aliwithdrawalOrder(@RequestBody BalanceOrderDto balanceOrderDto) {


        return aliwithdrawalService.aliwithdrawalOrder(balanceOrderDto);

    }
}
