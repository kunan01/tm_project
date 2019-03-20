package com.tangmo.emall;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.entity.ProductParam;
import com.tangmo.emall.entity.ProductSpec;
import com.tangmo.emall.entity.PropKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmallApplicationTests {

    @Resource
    private ProductDao productDao;

	@Test
	public void contextLoads() {

	}

}

