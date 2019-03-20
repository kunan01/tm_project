package service;


import com.tangmo.zhjy.system.SystemApplication;
import com.tangmo.zhjy.system.modules.dao.SysTwoClassifyMapper;
import com.tangmo.zhjy.system.modules.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestService {

        @Autowired
        private SysUserService sysUserService;

        @Autowired
        private SysInformationService sysInformationService;


        @Test
        public void testShopService(){
//            System.out.println(sysUserService.getNotAuditAll());
//            commodityService.isQuality(97);
//            sysUserService.queryVerifyInfo("1");

//            System.out.println(sysUserService.selectByPage("",1,100));
//            commodityService.searchCdList(1,1,10,1);
        }
}

