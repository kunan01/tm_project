package service;

import com.tangmo.zhjy.app.AppApplication;
import com.tangmo.zhjy.app.modules.dao.CommodityDao;
import com.tangmo.zhjy.app.modules.dao.ShopVerifyDao;
import com.tangmo.zhjy.app.modules.dto.AppUserDto;
import com.tangmo.zhjy.app.modules.service.AppInformationService;
import com.tangmo.zhjy.app.modules.service.AppSlideService;
import com.tangmo.zhjy.app.modules.service.AppUserService;
import com.tangmo.zhjy.app.modules.service.CommodityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by chengge on 2018/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestServiceImpl {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private ShopVerifyDao shopVerifyDao;

    @Resource
    private CommodityDao commodityDao;

        @Test
        public void testShopService(){
//            System.out.println(101/100);
//            System.out.println(Double.parseDouble((101/100)+""));
//            appUserService.smsLogin("17691118069","1234",null);

//            shopVerifyDao.selectByUserId(104);
//            AppUserDto appUserDto = new AppUserDto();
//            appUserDto.setPhone("15996970497");
//            appUserDto.setPassword(passwordEncoder.encode("123456"));
//            appUserService.addUser(appUserDto);

//            for (int i = 0;i<10;i++){
//
//                System.out.println(commodityDao.getCommentClassType(1).size());
//            }
//
//            System.out.println(passwordEncoder.encode("123456"));
//            System.out.println(passwordEncoder.encode("123456"));
//
//
//
//
//            System.out.println(passwordEncoder.matches("123456",passwordEncoder.encode("123456")));

//            AppUserDto appUserDto = new AppUserDto();
//            appUserDto.setPhone("18888888888");
//            appUserDto.setPassword("123456");
//            System.out.println(appUserService.addUser(appUserDto));


//            String str = "<img sta/><p style=\"text-indent: 2em; line-height: 1.5em;\"><img atrqwe/><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">1、以下违法、事故车辆在2018年4月30日前因涉及交通违法行为，已被公安机关交通管理部门依法扣留，其驾驶人、所有人和管理人均未按规定期限到公安机关交通管理部门接受处理。根据《中华人民共和国道路交通安全法》第一百一十二条、《中华人民共和国道路交通安全法实施条例》第一百零七条等相关法律法规的规定，现予以公告。自本公告发布之日起经过三个月，当事人仍不前来接受处理的，公安机关交通管理部门将对扣留的车辆依法处理。</span></p><p style=\"text-indent: 2em; line-height: 1.5em; text-align: center;\"><a href=\"http://cloud.189.cn/\" target=\"_blank\"><img src=\"asdqwqdq/w/free/tyy/250x250.jpg\" height=\"250\" width=\"250\"/><img src=\"http://market.21cn.com/w/free/tyy/250x250.jpg\" height=\"250\" width=\"250\"/></a></p><p style=\"text-indent: 2em; line-height: 1.5em;\"><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">2、以下无主财物车辆在2018年4月30日前因涉及交通违法行为，已被公安机关交通管理部门依法拖移/查扣，其驾驶人、所有人和管理人均未按规定期限到公安机关交通管理部门接受处理。根据《公安机关办理行政案件程序规定》第一百七十一条，现予以公告。自本公告发布之日起经过六个月，当事人仍不前来接受处理的，公安机关交通管理部门将对拖移/查扣的车辆依法处理。</span></p><p style=\"text-indent: 2em; line-height: 1.5em;\"><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">3、请以下车辆的驾驶人、所有人或管理人在本公告发布之日起携带身份证、驾驶证、行驶证或车辆合法来源证明到广州市公安局交通警察支队直属各大队接受处理。</span></p><p style=\"text-indent: 2em; line-height: 1.5em;\"><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span></p><p style=\"text-indent: 2em; line-height: 1.5em;\"><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span></p><p style=\"text-indent: 2em; line-height: 1.5em; text-align: right;\"><font face=\"微软雅黑, Microsoft YaHei\"><span style=\"color: rgb(191, 191, 191);\">180326</span></font></p>";
//
//            while (true){
//                if(str.indexOf("<img") != -1){
//                    str = str.substring(str.indexOf("<img"));
//                    System.out.println(str.substring(str.indexOf("<img"),str.indexOf("/>")+2));
//                    str = str.substring(str.indexOf("/>")+2);
//                }else{
//                    break;
//                }
//            }





//            str.substring(str.indexOf("<img"),);
        }
}
