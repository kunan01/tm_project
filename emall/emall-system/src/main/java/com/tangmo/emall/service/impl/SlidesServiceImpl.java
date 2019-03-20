package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.SlidesDao;
import com.tangmo.emall.entity.RsFile;
import com.tangmo.emall.entity.Slides;
import com.tangmo.emall.service.SlidesService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("slidesService")
public class SlidesServiceImpl implements SlidesService {

    @Resource
    private SlidesDao slidesDao;

    @Resource
    private FileDao fileDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result addSlides(Slides slides) {
        try {

            if(slides == null || slides.getSlideImage() == null || slides.getSlideSort() == null){
                return ResultUtil.paramError();
            }

            //校验图片
            RsFile rsFile = fileDao.getFileById(slides.getSlideImage());

            if(rsFile == null){
                return ResultUtil.imgError();
            }

            if(slidesDao.getSlidesCount() >= slides.getSlideSort()){
                slidesDao.updSlidesSort(slides.getSlideSort());
            }

            //修改图片为已用状态
            fileDao.updFile(slides.getSlideImage());

            //添加轮播图
            slidesDao.addSlides(slides);

            //清理缓存
            String key = "SlidesList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加轮播图接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updSlides(Slides slides) {
        try {

            if(slides == null || slides.getSlidesId() == null){
                return ResultUtil.paramError();
            }

            //校验轮播图是否存在
            Slides slides1 = slidesDao.getSlidesById(slides.getSlidesId());

            if(slides1 == null){
                return ResultUtil.dataNoError();
            }

            if(slides.getSlideImage() != null){

                if(!slides1.getSlideImage().equals(slides.getSlideImage())){
                    //校验图片
                    RsFile rsFile = fileDao.getFileById(slides.getSlideImage());

                    if(rsFile == null){
                        return ResultUtil.imgError();
                    }

                    //删除原有图片
                    fileDao.delFile(slides1.getSlideImage());

                    //修改图片为已用状态
                    fileDao.updFile(slides.getSlideImage());
                }

            }

            if(slides.getSlideSort() != null){
                if(slides1.getSlideSort() > slides.getSlideSort()){
                    slidesDao.updSlidesByJia(slides1.getSlideSort(),slides.getSlideSort());
                }else if(slides1.getSlideSort() < slides.getSlideSort()){
                    slidesDao.updSlidesByJian(slides1.getSlideSort(),slides.getSlideSort());
                }
            }

            //修改轮播图
            slidesDao.updSlides(slides);

            //清理缓存
            String key = "SlidesList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改轮播图接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delSlides(Slides slides) {
        try {

            if(slides == null || slides.getSlidesId() == null){
                return ResultUtil.paramError();
            }

            //校验轮播图是否存在
            Slides slides1 = slidesDao.getSlidesById(slides.getSlidesId());

            if(slides1 == null){
                return ResultUtil.dataNoError();
            }

            //删除轮播图排序
            slidesDao.delSlidesCount(slides1.getSlideSort());

            //删除图片资源
            fileDao.delFile(slides1.getSlideImage());

            //删除轮播图
            slidesDao.delSlides(slides.getSlidesId());

            //清理缓存
            String key = "SlidesList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }
            return ResultUtil.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("删除轮播图接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result batchDeleteSlides(Slides slides) {
        try {

            if(slides == null || slides.getSlidesIdList() == null){
                return ResultUtil.paramError();
            }

            if(slides.getSlidesIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (int i = 0; i<slides.getSlidesIdList().length;i++){

                //校验轮播图是否存在
                Slides slides1 = slidesDao.getSlidesById(slides.getSlidesIdList()[i]);

                if(slides1 != null){
                    //删除轮播图排序
                    slidesDao.delSlidesCount(slides1.getSlideSort());

                    //删除图片资源
                    fileDao.delFile(slides1.getSlideImage());

                    //删除轮播图
                    slidesDao.delSlides(slides1.getSlidesId());
                }
            }

            //清理缓存
            String key = "SlidesList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }
            return ResultUtil.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("删除轮播图接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getSlidesList(Integer pageNo,Integer pageSize) {
        try {

            if(pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            PageHelper.startPage(pageNo,pageSize);

            List<Slides> slides = slidesDao.getSlidesList();

            PageInfo<Slides> page = new PageInfo<Slides>(slides);

            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("查询轮播图接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
