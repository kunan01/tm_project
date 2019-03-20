package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.OrderDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.entity.OrderDetail;
import com.tangmo.emall.entity.RsFile;
import com.tangmo.emall.service.FileService;
import com.tangmo.emall.utils.EncryptUtil;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanjialin
 * @date 2019/1/9.
 * @Description
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private ProductDao productDao;

    @Override
    @Transactional
    public Result addFile(String base64) {

        RsFile file = new RsFile();

        file.setFId(String.valueOf(EncryptUtil.randomPwd() + System.currentTimeMillis()));
        file.setFBase(base64);

        fileDao.addFile(file);
        return ResultUtil.success(file.getFId());
    }

    @Override
    public int updateFile() {
        try {
            fileDao.updateFile();
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("job定时更新图片接口异常"+e);
            return 0;
        }
    }

    @Override
    public void updateExpiredOrders() {
        try {
            List<OrderDetail> orderDetails = orderDao.getBackOrder();
            if(orderDetails != null && orderDetails.size() != 0){
                for (int i = 0;i<orderDetails.size();i++){
                    //更新订单状态
                    orderDao.updateFailure(orderDetails.get(i).getOrderId());
                    //更新库存数量
                    productDao.increaseInventory(orderDetails.get(i).getSpecId(),orderDetails.get(i).getProductCount());
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("job定时更新订单接口异常"+e);
        }

    }

    @Override
    public String getBaseByImageId(String imageId) {
        try {
            if(imageId == null){
                return null;
            }
            RsFile rsFile = fileDao.getFileById(imageId);
            if(rsFile == null){
                return null;
            }
            return rsFile.getFBase();
        }catch (Exception e){
            System.out.println("获取图片接口异常"+e.getMessage());
            return null;
        }
    }
}
