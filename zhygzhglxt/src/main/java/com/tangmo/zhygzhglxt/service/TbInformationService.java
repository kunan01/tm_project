package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbInformation;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by chengge on 2018/11/1.
 */
public interface TbInformationService {

    Result findInformationByCode(String code);

    Result deleteByInformationCode(String code);

    Result appFindPage(String name, String twoClassifyCode, Integer pageNo, Integer pageSize);

    Result findClassifyCode(String classifCode, Integer pageNo, Integer pageSize, String name);

    Result addInformation(TbInformation tbInformation);

    Result modifyInfomation(TbInformation tbInformation);

    Result getTwoClassifyCode(String classifCode);

    Result getOneClassifyCode();
}
