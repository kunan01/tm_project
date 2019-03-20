package com.tangmo.zhjy.system.utils;

/**
 * Created by chengge on 2018/8/13.
 */
public  class Transfor {

    public static String transfor(String str) {
        String name="暂无信息";
        if("/system/system/user/me".equals(str)){
            name= "用户登录,获取用户信息";
        }
        if("/system/system/user/saveSysUser.do".equals(str)){
            name= "新增管理员";
        }
        if("/system/system/user/modifySysUser.do".equals(str)){
            name= "修改管理员信息";
        }
        if("/system/system/user/deleteSysUser.do".equals(str)){
            name= "删除管理员";
        }
        if("/system/system/user/findSelectUserName".equals(str)){
            name= "查看管理员详情";
        }
        if("/system/system/user/findByUserPage".equals(str)){
            name= "分页查询用户";
        }
        if("/system/system/user/queryCommunity".equals(str)){
            name= "根据管理员ID查询";
        }
        if("/system/system/user/allotCommunity".equals(str)){
            name= "分配社区";
        }
        if("/system/system/user/verify".equals(str)){
            name= "认证用户";
        }
        if("/system/system/user/unverify".equals(str)){
            name= "驳回用户实名认证请求";
        }
        if("/system/system/user/info/verify".equals(str)){
            name= "查询已实名认证用户";
        }
        if("/system/system/user/info/unverify".equals(str)){
            name= "查询未实名认证用户";
        }
        if("/system/app/classify/queryAll".equals(str)){
            name= "查询所有一级菜单";
        }
        if("/system/commodity/verify".equals(str)){
            name= "审核app发布的商品信息";
        }
        if("/system/commodity/isQuality".equals(str)){
            name= "/推送精品商品";
        }
        if("/system/commodity/list".equals(str)){
            name= "获取商品列表信息";
        }
        if("/system/commodity/detail".equals(str)){
            name= "获取商品详情";
        }
        if("/system/commodity/delete".equals(str)){
            name= "删除商品信息";
        }
        if("/system/feedback/addFeedBack".equals(str)){
            name= "增加反馈信息";
        }
        if("/system/feedback/deleteByPrimaryKey".equals(str)){
            name= "删除反馈";
        }
        if("/system/feedback/serchListFeedback".equals(str)){
            name= "分页查询所有反馈信息";
        }
        if("/system/file/read".equals(str)){
            name= "附件查看";
        }
        if("/system/file/upload".equals(str)){
            name= "上传用户图片";
        }
        if("/system/app/findAbsolutePath".equals(str)){
            name= "获取图片绝对路径";
        }
        if("/system/app/getBase64".equals(str)){
            name= "获取图片Base64";
        }
        if("/system/log/deleteByPrimaryKey".equals(str)){
            name= "删除日志";
        }
        if("/system/log/serchListLog".equals(str)){
            name= "分页查询所有日志信息";
        }
        if("/system/shop/service/changeShopService".equals(str)){
            name= "修改商家服务";
        }
        if("/system/shop/service/detail".equals(str)){
            name= "获取服务详情";
        }
        if("/system/shop/service/verify".equals(str)){
            name= "审核未通过的同城服务信息,相当于修改";
        }
        if("/system/shop/service/selectByState".equals(str)){
            name= "查询同城服务信息:state 1:未审核 2审核通过 3审核失败";
        }
        if("/system/shop/service/deleteUserShopGoods".equals(str)){
            name= "删除发布的服务信息";
        }
        if("/system/app/community/saveCommunity".equals(str)){
            name= "添加社区";
        }
        if("/system/app/community/updateCommunity".equals(str)){
            name= "修改社区信息";
        }
        if("/system/app/community/findById".equals(str)){
            name= "根据社区编号查找";
        }
        if("/system/app/community/delById".equals(str)){
            name= "彻底删除社区";
        }
        if("/system/app/community/findByPage".equals(str)){
            name= "分页查询社区";
        }
        if("/system/app/community/queryAll".equals(str)){
            name= "查询所有社区";
        }
        if("/system/app/community/uploadLogo".equals(str)){
            name= "上传社区Logo";
        }
        if("/system/app/communityInfor/saveCommunityInfom".equals(str)){
            name= "添加社区通知";
        }
        if("/system/app/communityInfor/saveCommunityInfom".equals(str)){
            name= "添加社区通知";
        }
        if("/system/app/communityInfor/modifyCommunityInfom".equals(str)){
            name= "彻底删除社区通知";
        }
        if("/system/app/communityInfor/findByPage".equals(str)){
            name= "分页查询通知信息";
        }
        if("/system/app/communityInfor/fileUpload".equals(str)){
            name= "上传社区通知封面图";
        }
        if("/system/app/information/addInformation".equals(str)){
            name= "增加文章";
        }
        if("/system/app/information/addInformation".equals(str)){
            name= "修改文章";
        }
        if("/system/app/information/findById".equals(str)){
            name= "根据文章ID查找";
        }
        if("/system/app/information/delById".equals(str)){
            name= "根据文章ID彻底删除办事指南";
        }
        if("/system/app/information/sysFindPage".equals(str)){
            name= "后台分页查询";
        }
        if("/system/app/information/sysFindPageNameType".equals(str)){
            name= "后台分页二级查询";
        }
        if("system/app/information/findClassifyId".equals(str)){
            name= "根据一级菜单编号查找文章";
        }
        if("/system/app/informationImages/filesUpload".equals(str)){
            name= "多图片上传接口";
        }
        if("/system/app/navigation/addNavigation".equals(str)){
            name= "添加导航菜单";
        }
        if("/system/app/navigation/addNavigation".equals(str)){
            name= "添加导航菜单";
        }
        if("/system/app/navigation/delNavigation".equals(str)){
            name= "彻底删除首页导航菜单";
        }
        if("/system/app/navigation/modifyNavigation".equals(str)){
            name= "修改导航菜单";
        }
        if("/system/app/navigation/findByNavigationPage".equals(str)){
            name= "分页查询导航菜单";
        }
        if("/system/app/navigation/findById".equals(str)){
            name= "根据编号查找";
        }
        if("/system/app/navigation/fileUpload".equals(str)){
            name= "上传导航LOGO";
        }
        if("/system/system/permission/queryAll".equals(str)){
            name= "查询所有菜单及操作";
        }
        if("/system/system/role/saveSysRole".equals(str)){
            name= "添加角色";
        }
        if("/system/system/role/findByRolePage".equals(str)){
            name= "分页查询角色";
        }
        if("/system/system/role/saveSysRole".equals(str)){
            name= "修改角色";
        }
        if("/system/system/role/updateSysRole".equals(str)){
            name= "修改角色";
        }
        if("/system/system/role/deleteSysRole".equals(str)){
            name= "删除角色";
        }
        if("/system/system/role/delSysRole".equals(str)){
            name= "彻底删除角色";
        }
        if("/system/shop/unverify".equals(str)){
            name= "查询未审核商铺信息";
        }
        if("/system/shop/verify".equals(str)){
            name= "查询已审核商铺信息";
        }
        if("/system/shop/verify".equals(str)){
            name= "通过商家审核";
        }
        if("/system/shop/unverify/".equals(str)){
            name= "驳回商家审核";
        }
        if("/system/app/slide/fileUpload".equals(str)){
            name= "上传轮播图";
        }
        if("/system/app/slide/modifySlide".equals(str)){
            name= "修改轮播图";
        }
        if("/system/app/slide/findBySlidePage".equals(str)){
            name= "分页查询轮播图";
        }
        if("/system/app/slide/findSlideId".equals(str)){
            name= "根据轮播图Id查询";
        }
        if("/system/app/slide/delById".equals(str)){
            name= "根据Id彻底删除轮播图";
        }
        if("/system/app/twoClassify/addTwoClassify".equals(str)){
            name= "增加二级分类";
        }
        if("/system/app/twoClassify/modifyTwoClassify".equals(str)){
            name= "修改二级分类";
        }
        if("/system/app/twoClassify/findByPage".equals(str)){
            name= "模糊分页查询";
        }
        if("/system/app/twoClassify/findByAppClassifyId".equals(str)){
            name= "根据一级菜单ID查找二级菜单";
        }
        if("system/app/twoClassify/twoClassifyIconUpload".equals(str)){
            name= "上传二级分类ICON图标";
        }
        if("system/app/twoClassify/delByTwoClassifyId".equals(str)){
            name= "根据二级菜单id彻底删除有关所有信息";
        }
        return name;
    }
}
