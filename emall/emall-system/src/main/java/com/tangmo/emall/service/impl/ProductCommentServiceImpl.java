package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ProductCommentDao;
import com.tangmo.emall.service.ProductCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("productCommentService")
public class ProductCommentServiceImpl implements ProductCommentService {

    @Resource
    private ProductCommentDao productCommentDao;

    @Resource
    private FileDao fileDao;


}
