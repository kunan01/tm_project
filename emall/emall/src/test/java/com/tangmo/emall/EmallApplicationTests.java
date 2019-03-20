package com.tangmo.emall;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EmallApplicationTests {

	@Test
	public void contextLoads() {
//        String requestxml = null;
//        String openId = null;
//        try {
//            requestxml = new HttpsRequest().HttpsRequest("https://graph.facebook.com/debug_token","POST","access_token=366208474212027|16728656feef7c9dd002a3c6a5823b62&input_token=EAAFNEI4E5rsBAATyqgqKY7G7RvAl7pEsIjTdVPYxBfKZAj3ZC3D4hC54AGUHP5GFaQIUpxKH9lHWWw8I3rcHmE8c4IsZAQzujpMRplJRbSUnqdDTRqoc2IQke3b8MZCUZAuvUyDZBDPDzUnqqLQgCeH4SSN635JTQHaID8UFVk6tgRFrHveHzrF3QsC8TZAiEXo4EJ4mm8WP299NQ8qdJcX0pJJZAcNxfcAZD");
//            System.out.println(requestxml);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            JSONObject jsonObject = JSONObject.parseObject(requestxml);
//            openId = jsonObject.get("openid").toString();
//            Document doc= DocumentHelper.parseText(requestxml);
//            Element element_xml=doc.getRootElement();
//            List<Element> elementList = element_xml.elements();
//            for (int i = 0; i <elementList.size(); i++) {
//                System.out.println(elementList.get(i).getName()+" "+elementList.get(i).getText());
//                if("openid".equals(elementList.get(i).getName())){
//                    openId = elementList.get(i).getTextTrim();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
	}

}

