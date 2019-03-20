package com.tangmo.zhygzhglxt.utility;

import com.tangmo.zhygzhglxt.entity.vo.BusVo;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetBusGps {

    public static BusVo getBusGps(String NumberPlate, String loginKey) {
        String orderSoapXml = "<?xml version = \"1.0\" ?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "   <soap:Body>"
                + "      <getCurrentVehicleInfo xmlns=\"http://gpschina.cc/\">"
                + "         <strLoginKey>" + loginKey + "</strLoginKey>"
                + "         <NumberPlateLists>" + NumberPlate + "</NumberPlateLists>"
                + "      </getCurrentVehicleInfo>"
                + "</soap:Body>"
                + "</soap:Envelope>";
        //7A248EB5DB413A917E75408CAA8596CF
        String postUrl = "http://47.104.238.142:8999/Service.asmx";
        //采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
        BusVo busVo = doPostSoap1_1(postUrl, orderSoapXml, "http://gpschina.cc/getCurrentVehicleInfo");
        if (busVo != null) {
            busVo.setVehicleNum(NumberPlate);
        }
        return busVo;
    }


    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 30000;// 传输超时时间

    public static BusVo doPostSoap1_1(String postUrl, String soapXml,
                                      String soapAction) {
        Object retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {

                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("==fanhui===" + retStr + "======");
                try {
                    String xml1 = retStr.toString();
                    String xml2 = xml1.replace("utf-8", "utf8");
                    saveXMLString(xml2, "c://train.xml");//
                    //saveXMLString(retStr.toString(),"c://train.xml");//

                    return parserXml("c://train.xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                logger.info("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
//            logger.error("exception in doPostSoap1_1", e);
            System.out.println(e);
        }
        return null;
    }

    public static BusVo doPostSoap21_1(String postUrl, String soapXml,
                                       String soapAction) {
        Object retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {

                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("==fanhui===" + retStr + "======");
                try {
                    saveXMLString(retStr.toString(), "c://login.xml");//


                    return parserXml2("c://login.xml");//
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                logger.info("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
//            logger.error("exception in doPostSoap1_1", e);
            System.out.println(e);
        }
        return null;
    }


    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8 * 1024);

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            sb.delete(0, sb.length());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                return null;
            }
        }
        return sb.toString();
    }


    public static void saveXMLString(String XMLString, String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        if (file.canWrite()) {
            FileWriter fileOut = new FileWriter(file);
            fileOut.write(XMLString);
            fileOut.close();
        }


    }

    private static BusVo parserXml(String fileName) throws Exception {
        File inputXml = new File(fileName);
        System.out.println("============" + fileName + "===========");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);
            Element node1 = document.getRootElement();
            List<String> param = new ArrayList();
//            int no = 0;
            Iterator i = node1.elementIterator();

            while (i.hasNext()) {
                Element node2 = (Element) i.next();
                Iterator j = node2.elementIterator();
                while (j.hasNext()) {
                    Element node3 = (Element) j.next();
                    Iterator x = node3.elementIterator();
                    while (x.hasNext()) {
                        Element node4 = (Element) x.next();
                        System.out.println("==========" + node4.getText());
                        if (!node4.getText().equals("<NewDataSet />")) {

                            BusVo busVo = new BusVo();
                            busVo.setObjectCode(node4.getText().substring(node4.getText().lastIndexOf("<ObjectCode>") + 12, node4.getText().lastIndexOf("</ObjectCode>")));
//                            String utf8Str = (node4.getText().substring(node4.getText().lastIndexOf("<VehicleNum>")+12,node4.getText().lastIndexOf("</VehicleNum>")));
//                            byte[] utf8Bytes = gbkStr.getBytes("UTF-8");
//                            String utf8Str = new String(utf8Bytes, "UTF-8");
//                            busVo.setVehicleNum(utf8Str);
                            busVo.setGPSTime(node4.getText().substring(node4.getText().lastIndexOf("<GPSTime>") + 9, node4.getText().lastIndexOf("</GPSTime>")));
                            busVo.setRcvTime(node4.getText().substring(node4.getText().lastIndexOf("<RcvTime>") + 9, node4.getText().lastIndexOf("</RcvTime>")));
                            busVo.setLon(node4.getText().substring(node4.getText().lastIndexOf("<Lon>") + 5, node4.getText().lastIndexOf("</Lon>")));
                            busVo.setLat(node4.getText().substring(node4.getText().lastIndexOf("<Lat>") + 5, node4.getText().lastIndexOf("</Lat>")));
                            busVo.setSpeed(node4.getText().substring(node4.getText().lastIndexOf("<Speed>") + 7, node4.getText().lastIndexOf("</Speed>")));
                            busVo.setDirect(node4.getText().substring(node4.getText().lastIndexOf("<Direct>") + 8, node4.getText().lastIndexOf("</Direct>")));
                            busVo.setMileage(node4.getText().substring(node4.getText().lastIndexOf("<Mileage>") + 9, node4.getText().lastIndexOf("</Mileage>")));
                            busVo.setStatusDes(node4.getText().substring(node4.getText().lastIndexOf("<StatusDes>") + 11, node4.getText().lastIndexOf("</StatusDes>")));
                            busVo.setOilNum(node4.getText().substring(node4.getText().lastIndexOf("<OilNum>") + 8, node4.getText().lastIndexOf("</OilNum>")));
                            busVo.setIsOnline(node4.getText().substring(node4.getText().lastIndexOf("<IsOnline>") + 10, node4.getText().lastIndexOf("</IsOnline>")));
                            busVo.setIsAlarm(node4.getText().substring(node4.getText().lastIndexOf("<IsAlarm>") + 9, node4.getText().lastIndexOf("</IsAlarm>")));
                            busVo.setStatus(node4.getText().substring(node4.getText().lastIndexOf("<Status>") + 8, node4.getText().lastIndexOf("</Status>")));

//                            Map<String,Object> map = new HashMap<>();
//                            map.put("ObjectCode",node4.getText().substring(node4.getText().lastIndexOf("<ObjectCode>")+12,node4.getText().lastIndexOf("</ObjectCode>")));
//                            map.put("VehicleNum",node4.getText().substring(node4.getText().lastIndexOf("<VehicleNum>")+12,node4.getText().lastIndexOf("</VehicleNum>")));
//                            map.put("GPSTime",node4.getText().substring(node4.getText().lastIndexOf("<GPSTime>")+9,node4.getText().lastIndexOf("</GPSTime>")));
//                            map.put("RcvTime",node4.getText().substring(node4.getText().lastIndexOf("<RcvTime>")+9,node4.getText().lastIndexOf("</RcvTime>")));
//                            map.put("Lon",node4.getText().substring(node4.getText().lastIndexOf("<Lon>")+5,node4.getText().lastIndexOf("</Lon>")));
//                            map.put("Lat",node4.getText().substring(node4.getText().lastIndexOf("<Lat>")+5,node4.getText().lastIndexOf("</Lat>")));
//                            map.put("Speed",node4.getText().substring(node4.getText().lastIndexOf("<Speed>")+7,node4.getText().lastIndexOf("</Speed>")));
//                            map.put("Direct",node4.getText().substring(node4.getText().lastIndexOf("<Direct>")+8,node4.getText().lastIndexOf("</Direct>")));
//                            map.put("Mileage",node4.getText().substring(node4.getText().lastIndexOf("<Mileage>")+9,node4.getText().lastIndexOf("</Mileage>")));
//                            map.put("StatusDes",node4.getText().substring(node4.getText().lastIndexOf("<StatusDes>")+11,node4.getText().lastIndexOf("</StatusDes>")));
//                            map.put("OilNum",node4.getText().substring(node4.getText().lastIndexOf("<OilNum>")+8,node4.getText().lastIndexOf("</OilNum>")));
//                            map.put("IsOnline",node4.getText().substring(node4.getText().lastIndexOf("<IsOnline>")+10,node4.getText().lastIndexOf("</IsOnline>")));
//                            map.put("IsAlarm",node4.getText().substring(node4.getText().lastIndexOf("<IsAlarm>")+9,node4.getText().lastIndexOf("</IsAlarm>")));
//                            map.put("Status",node4.getText().substring(node4.getText().lastIndexOf("<Status>")+8,node4.getText().lastIndexOf("</Status>")));
//                            return map;

                            if (busVo.getObjectCode() == null) {
                                busVo.setObjectCode("");
                            }
                            if (busVo.getVehicleNum() == null) {
                                busVo.setVehicleNum("");
                            }
                            if (busVo.getGPSTime() == null) {
                                busVo.setGPSTime("");
                            }
                            if (busVo.getRcvTime() == null) {
                                busVo.setRcvTime("");
                            }
                            if (busVo.getLon() == null) {
                                return null;
                            }
                            if (busVo.getLat() == null) {
                                return null;
                            }
                            if (busVo.getSpeed() == null) {
                                busVo.setSpeed("");
                            }
                            if (busVo.getDirect() == null) {
                                busVo.setDirect("");
                            }
                            if (busVo.getMileage() == null) {
                                busVo.setMileage("");
                            }
                            if (busVo.getStatusDes() == null) {
                                busVo.setStatusDes("");
                            }
                            if (busVo.getOilNum() == null) {
                                busVo.setOilNum("");
                            }
                            if (busVo.getIsOnline() == null) {
                                busVo.setIsOnline("");
                            }
                            if (busVo.getIsAlarm() == null) {
                                busVo.setIsAlarm("");
                            }
                            if (busVo.getStatus() == null) {
                                busVo.setStatus("");
                            }
                            System.out.println("获取数据成功！------");
                            return busVo;
                        }
                    }
//                    System.out.print(node3.getName() + ":" + node3.getText() + " ");
                    param.add(node3.getText());
                }

//                System.out.print("\n");
            }
        } catch (Exception var13) {
            System.out.println("======" + var13.getMessage().trim() + "11111111111");
        }
        return null;
    }

    private static BusVo parserXml2(String fileName) throws Exception {
        File inputXml = new File(fileName);
        //System.out.println("============"+fileName+"===========");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);
            Element node1 = document.getRootElement();
            List<String> param = new ArrayList();
//            int no = 0;
            Iterator i = node1.elementIterator();

            while (i.hasNext()) {
                Element node2 = (Element) i.next();
                Iterator j = node2.elementIterator();
                while (j.hasNext()) {
                    Element node3 = (Element) j.next();
                    Iterator x = node3.elementIterator();
                    while (x.hasNext()) {
                        Element node4 = (Element) x.next();
                        System.out.println("==========" + node4.getText());
                        BusVo busVo = new BusVo();
                        busVo.setLoninKey(node4.getText());
                        System.out.println("获取数据成功！------");
                        return busVo;
                    }
//                    System.out.print(node3.getName() + ":" + node3.getText() + " ");
                    param.add(node3.getText());
                }

//                System.out.print("\n");
            }
        } catch (Exception var13) {
            System.out.println("======" + var13.getMessage().trim() + "11111111111");
        }
        return null;
    }


    public static BusVo busLogin() {
        String userName = "igps";
        String password = "123456";
        String orderSoapXml = "<?xml version = \"1.0\" ?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "   <soap:Body>"
                + "      <getLoginKey xmlns=\"http://gpschina.cc/\">"
                + "         <strLoginName>" + userName + "</strLoginName>"
                + "         <strPassword>" + password + "</strPassword>"
                + "      </getLoginKey>"
                + "</soap:Body>"
                + "</soap:Envelope>";
        //7A248EB5DB413A917E75408CAA8596CF
        String postUrl = "http://47.104.238.142:8999/Service.asmx";
        //采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
        return doPostSoap21_1(postUrl, orderSoapXml, "http://gpschina.cc/getLoginKey");
    }
}
