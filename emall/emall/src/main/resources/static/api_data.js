define({ "api": [
  {
    "type": "GET",
    "url": "/activity/getAdvertisingList",
    "title": "获取热门活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>获取热门活动广告</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/activity/getAdvertisingList",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        raId:\"热门活动广告id\",\n        advertisingImage:\"广告图片\",\n        createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ActivityController.java",
    "groupTitle": "Activity",
    "name": "GetActivityGetadvertisinglist"
  },
  {
    "type": "GET",
    "url": "/activity/getTimeLimitedAdvertisingList",
    "title": "获取限时活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>获取限时活动广告</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/activity/getTimeLimitedAdvertisingList",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        discountId:\"优惠活动主键\",\n        title:\"活动标题\",\n        description:\"宣传语\",\n        adImage:\"宣传图片\",\n        startDate:\"开始日期\",\n        endDate:\"结束日期\",\n        eventType:\"活动类型  0满减 1打折 2免邮 3买送 4降价\",\n        eventRule:\"活动规则\",\n        createdTime:\"创建时间\",\n        objectType:\"活动目标  1商品分类 2商品\",\n        objectId:\"商品分类id\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ActivityController.java",
    "groupTitle": "Activity",
    "name": "GetActivityGettimelimitedadvertisinglist"
  },
  {
    "type": "GET",
    "url": "/activity/getTimeLimitedAdvertisingState",
    "title": "获取当前是否存在限时活动",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>获取当前是否存在限时活动</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/activity/getTimeLimitedAdvertisingState",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{true:存在，false:不存在}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ActivityController.java",
    "groupTitle": "Activity",
    "name": "GetActivityGettimelimitedadvertisingstate"
  },
  {
    "type": "GET",
    "url": "/activity/getTrendList",
    "title": "获取趋势列表",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>获取趋势列表</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/activity/getTrendList",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        taId:\"趋势广告主键\",\n        title:\"趋势标题\"\n        advertisingImage:\"广告图片\",\n        descript:\"描述语\",\n        location:\"描述语位置 1左 2下 3右\",\n        trends:\"包含4个商品信息\"\n        createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ActivityController.java",
    "groupTitle": "Activity",
    "name": "GetActivityGettrendlist"
  },
  {
    "type": "POST",
    "url": "/activity/getExchangeCode",
    "title": "用户获取兑换码",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>用户获取兑换码</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",\n    disId:\"优惠活动id\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"兑换码\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ActivityController.java",
    "groupTitle": "Activity",
    "name": "PostActivityGetexchangecode"
  },
  {
    "type": "POST",
    "url": "/address/addUserAddress",
    "title": "添加用户地址",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>添加用户地址</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId : \"用户id\",                              (必填)\n    userName : \"收货人姓名\",                         (必填)\n    addressLine1 : \"地址行一：街道地址/邮政信箱\",       (必填)\n    addressLine2 : \"地址行二：公寓套房等\"              (必填)\n    city : \"城市\",                                  (必填)\n    province : \"州/省\",                             (必填)\n    zipCode : \"邮政编码\",                            (必填)\n    country : \"国家\",                               (必填)\n    userPhone : \"收货人电话\",                        (必填)\n    instructions : \"交货说明\",                       (可选)\n    security : \"送货说明：进入大楼的安全码或电话\",        (可选)\n    isReceivePackage : \"是否可以在周末交货 0不是 1是\",  (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"添加成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressAdduseraddress"
  },
  {
    "type": "POST",
    "url": "/address/delAddById",
    "title": "删除用户地址信息",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>删除用户地址信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    addressId:\"地址id\",       (必填)\n    userId:\"用户id\",          (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressDeladdbyid"
  },
  {
    "type": "POST",
    "url": "/address/getDeliveryAddress",
    "title": "获取店铺发货地址",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>获取店铺发货地址</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "shopId",
            "description": "<p>店铺id （必填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/address/getDeliveryAddress?shopId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        daId : \"发货地址id\",\n        shopId : \"店铺id\",\n        senderName : \"发件人姓名\",\n        senderPhone : \"收货人电话\",\n        addressLine1 : \"地址行一：街道地址/邮政信箱\",\n        addressLine2 : \"地址行二：公寓套房等\"\n        city : \"城市\",\n        province : \"州/省\",\n        zipCode : \"邮政编码\",\n        country : \"国家\",\n        createdTime : \"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressGetdeliveryaddress"
  },
  {
    "type": "POST",
    "url": "/address/getUserAddressById",
    "title": "获取地址详情",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>获取用户地址集合</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "addressId",
            "description": "<p>地址id （必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id （必填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/address/getUserAddressById?addressId=1&userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        addressId : \"地址id\",\n        userId : \"用户id\",\n        userName : \"收货人姓名\",\n        addressLine1 : \"地址行一：街道地址/邮政信箱\",\n        addressLine2 : \"地址行二：公寓套房等\"\n        city : \"城市\",\n        province : \"州/省\",\n        zipCode : \"邮政编码\",\n        country : \"国家\",\n        userPhone : \"收货人电话\",\n        instructions : \"交货说明\",\n        security : \"送货说明：进入大楼的安全码或电话\",\n        isReceivePackage : \"是否可以在周末交货 0不是 1是\",\n        isDefault : \"是否默认地址 0不是 1是\",\n        dataStatus : \"地址是否有效 0无效 1有效\",\n        createdTime : \"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressGetuseraddressbyid"
  },
  {
    "type": "POST",
    "url": "/address/getUserAddressListByUserId",
    "title": "获取用户地址集合",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>获取用户地址集合</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id （必填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/address/getUserAddressListByUserId?userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        addressId : \"地址id\",\n        userId : \"用户id\",\n        userName : \"收货人姓名\",\n        addressLine1 : \"地址行一：街道地址/邮政信箱\",\n        addressLine2 : \"地址行二：公寓套房等\"\n        city : \"城市\",\n        province : \"州/省\",\n        zipCode : \"邮政编码\",\n        country : \"国家\",\n        userPhone : \"收货人电话\",\n        instructions : \"交货说明\",\n        security : \"送货说明：进入大楼的安全码或电话\",\n        isReceivePackage : \"是否可以在周末交货 0不是 1是\",\n        isDefault : \"是否默认地址 0不是 1是\",\n        dataStatus : \"地址是否有效 0无效 1有效\",\n        createdTime : \"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressGetuseraddresslistbyuserid"
  },
  {
    "type": "POST",
    "url": "/address/updAddById",
    "title": "修改用户地址信息",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>修改用户地址信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    addressId:\"地址id\",                           (必填)\n    userId:\"用户id\",                              (必填)\n    userName:\"收货人姓名\",                         (可选)\n    addressLine1:\"地址行一：街道地址/邮政信箱\",       (可选)\n    addressLine2:\"地址行二：公寓套房等\",             (可选)\n    city:\"城市\",                                  (可选)\n    province:\"州/省\",                             (可选)\n    zipCode:\"邮政编码\",                            (可选)\n    country:\"国家\",                               (可选)\n    userPhone:\"收货人电话\",                        (可选)\n    instructions:\"交货说明\",                       (可选)\n    security:\"送货说明：进入大楼的安全码或电话\",        (可选)\n    isReceivePackage:\"是否可以在周末交货 0不是 1是\",   (可选)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressUpdaddbyid"
  },
  {
    "type": "POST",
    "url": "/address/updAddDefaultById",
    "title": "设置或取消默认地址",
    "group": "Address",
    "version": "0.0.1",
    "description": "<p>设置或取消默认地址</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    addressId:\"地址id\",\n    userId:\"用户id\",\n    isDefault:\"是否默认地址 0不是 1是\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/AddressController.java",
    "groupTitle": "Address",
    "name": "PostAddressUpdadddefaultbyid"
  },
  {
    "type": "GET",
    "url": "/cart/delShopCart",
    "title": "删除购物车",
    "group": "Cart",
    "version": "0.0.1",
    "description": "<p>删除购物车</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>当前用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cartId",
            "description": "<p>购物车id（多个以逗号隔开）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/cart/delShopCart?userId=1&cartId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ShopCartController.java",
    "groupTitle": "Cart",
    "name": "GetCartDelshopcart"
  },
  {
    "type": "GET",
    "url": "/cart/emptyShopCart",
    "title": "清空购物车",
    "group": "Cart",
    "version": "0.0.1",
    "description": "<p>清空购物车</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>当前用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/cart/emptyShopCart?userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"清空完成\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ShopCartController.java",
    "groupTitle": "Cart",
    "name": "GetCartEmptyshopcart"
  },
  {
    "type": "GET",
    "url": "/cart/getShopCartList",
    "title": "获取用户购物车信息",
    "group": "Cart",
    "version": "0.0.1",
    "description": "<p>获取用户购物车信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>当前用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNo",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/cart/getShopCartList?userId=1&pageNo=1&pageSize=10",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        cartId:\"购物车主键\",\n        userId:\"用户id\",\n        productId:\"商品id\",\n        specId:\"规格id\",\n        productNum:\"商品数量\",\n        createdTime:\"创建时间\",\n        dataFailure:\"是否失效 0未失效 1已失效\"\n        product:{                  //商品信息\n             productId:\"商品表主键\",\n             shopId:\"店铺id\",\n             productName:\"商品名\",\n             brandId:\"品牌id\",\n             categoryId:\"商品分类id\",\n             price:\"商品价格\",\n             publishStatus:\"上下架状态 0未上架 1已上架\",\n             auditStatus:\"审核状态 0正在审核 1审核通过 2审核未通过\",\n             publishDate:\"上架日期\",\n             descript:\"商品描述\",\n             title:\"商品标题\",\n             subtitle:\"商品子标题\",\n             isGood:\"是否精品 0不是 1是\",\n             isNew:\"是否新品 0不是 1是\",\n             isPopular:\"是否热销 0不是 1是\",\n             seoText:\"seo描述\",\n             baseProp:\"基本属性 json串\",\n             sellProp:\"销售属性 json串\",\n             productImage:\"商品图片\",\n             sellCount:\"销售数量\",\n             viewCount:\"查看数量\",\n             createdTime:\"创建时间\"\n        },\n        productSpec:{              //规格信息\n             specId:\"商品规格主键\",\n             productId:\"商品id\",\n             productSpecs:\"商品规格json\",\n             stock:\"库存\",\n             price:\"价格\",\n             createdTime:\"预留字段\",\n             updatedTime:\"创建时间\",\n             productImage{\n                 imageId:\"商品图片表主键\",\n                 imageUrl:\"图片\",\n                 imageDesc:\"图片描述\",\n                 isMaster:\"是否主图 0不是 1是\",\n                 imageStatus:\"图片状态 0无效 1有效\"\n             }\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ShopCartController.java",
    "groupTitle": "Cart",
    "name": "GetCartGetshopcartlist"
  },
  {
    "type": "POST",
    "url": "/cart/addShopCart",
    "title": "加入购物车",
    "group": "Cart",
    "version": "0.0.1",
    "description": "<p>加入购物车</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",\n    productId:\"商品id\",\n    specId:\"规格id\",\n    productNum:\"商品数量\",\n    prescriptionId:\"处方id\",\n    prescriptionImage:\"处方图片\",\n    prescriptionPrice:\"处方价格\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ShopCartController.java",
    "groupTitle": "Cart",
    "name": "PostCartAddshopcart"
  },
  {
    "type": "POST",
    "url": "/cart/updateTheNumber",
    "title": "修改购物车商品数量",
    "group": "Cart",
    "version": "0.0.1",
    "description": "<p>修改购物车商品数量</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    cartId:\"购物车id\",\n    userId:\"用户ID\"\n    productNum:\"最终数量\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"清空完成\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ShopCartController.java",
    "groupTitle": "Cart",
    "name": "PostCartUpdatethenumber"
  },
  {
    "type": "GET",
    "url": "/collect/QueryCollectionList",
    "title": "获取用户收藏列表",
    "group": "Collect",
    "version": "0.0.1",
    "description": "<p>清空收藏</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNo",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/product/QueryCollectionList?userId=1&pageNo=1&pageSize=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        collectId:\"收藏信息主键\",\n        userId:\"用户id\",\n        productId:\"商品id\",\n        createdTime:\"添加时间\",\n        dataFailure:\"是否失效 0为失效 1已失效\",\n        product{\n            商品信息.....\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/CollectController.java",
    "groupTitle": "Collect",
    "name": "GetCollectQuerycollectionlist"
  },
  {
    "type": "POST",
    "url": "/collect/AddCollection",
    "title": "添加或取消收藏",
    "group": "Collect",
    "version": "0.0.1",
    "description": "<p>添加或取消收藏</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",\n    productId:\"商品id\",\n    dataFailure:\"收藏状态 0取消收藏 1收藏\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/CollectController.java",
    "groupTitle": "Collect",
    "name": "PostCollectAddcollection"
  },
  {
    "type": "POST",
    "url": "/collect/delCollection",
    "title": "删除收藏",
    "group": "Collect",
    "version": "0.0.1",
    "description": "<p>删除收藏</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "collectId",
            "description": "<p>收藏id（多个以逗号隔开）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/product/delCollection?userId=1&collectId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/CollectController.java",
    "groupTitle": "Collect",
    "name": "PostCollectDelcollection"
  },
  {
    "type": "POST",
    "url": "/collect/EmptyCollection",
    "title": "清空收藏",
    "group": "Collect",
    "version": "0.0.1",
    "description": "<p>清空收藏</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/product/EmptyCollection?userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/CollectController.java",
    "groupTitle": "Collect",
    "name": "PostCollectEmptycollection"
  },
  {
    "type": "GET",
    "url": "/comment/getCommentList",
    "title": "获取商品评论",
    "group": "Comment",
    "version": "0.0.1",
    "description": "<p>添加商品评论</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "sType",
            "description": "<p>0：按时间倒序，（其他预留）</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "productId",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNo",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/comment/getCommentList?productId=1&pageNo=1&pageSize=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       commentId:\"评论id\",\n       imgList:\"评论图片集合\"\n       content:\"评论内容\",\n       productId:\"商品id\",\n       orderId:\"订单id\",\n       userId:\"用户id\",\n       commentStar:\"评分（星星数量）\",\n       auditStatus:\"审核状态 0未审核 1已审核\",\n       auditTime:\"审核时间\",\n       upvoteNum:\"\",\n       createdTime:\"\",\n       updatedTime:\"\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductCommentController.java",
    "groupTitle": "Comment",
    "name": "GetCommentGetcommentlist"
  },
  {
    "type": "GET",
    "url": "/comment/giveALike",
    "title": "点赞评论",
    "group": "Comment",
    "version": "0.0.1",
    "description": "<p>添加商品评论</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "commentId",
            "description": "<p>评论id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/comment/giveALike?userId=1&commentId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"点赞成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductCommentController.java",
    "groupTitle": "Comment",
    "name": "GetCommentGivealike"
  },
  {
    "type": "POST",
    "url": "/comment/addComment",
    "title": "添加商品评论",
    "group": "Comment",
    "version": "0.0.1",
    "description": "<p>添加商品评论</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    content:\"评论内容\",                (必填)\n    imgId:\"评论图片（多个以逗号隔开）\",   (必填)\n    productId:\"商品id\",               (必填)\n    orderId:\"订单id\",                 (必填)\n    userId:\"用户id\",                  (必填)\n    commentStar:\"评分（星星数）\"        (可选)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"添加成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductCommentController.java",
    "groupTitle": "Comment",
    "name": "PostCommentAddcomment"
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "E:/emall/src/main/resources/static/main.js",
    "group": "E__emall_src_main_resources_static_main_js",
    "groupTitle": "E__emall_src_main_resources_static_main_js",
    "name": ""
  },
  {
    "type": "GET",
    "url": "/file/baseToImage",
    "title": "获取base64图片",
    "group": "File",
    "version": "0.0.1",
    "description": "<p>获取base64图片</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "/file/baseToImage?imageId=asdasd",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" :\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/FileController.java",
    "groupTitle": "File",
    "name": "GetFileBasetoimage"
  },
  {
    "type": "POST",
    "url": "/file/showPicture",
    "title": "直接展示图片",
    "group": "File",
    "version": "0.0.1",
    "description": "<p>直接展示图片</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "/file/showPicture?imageId=asdasd",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/FileController.java",
    "groupTitle": "File",
    "name": "PostFileShowpicture"
  },
  {
    "type": "POST",
    "url": "/file/upload",
    "title": "上传图片",
    "group": "File",
    "version": "0.0.1",
    "description": "<p>上传用户图片</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    file:\"图片文件\",\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"图片id\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/FileController.java",
    "groupTitle": "File",
    "name": "PostFileUpload"
  },
  {
    "type": "GET",
    "url": "/help/getHelpList",
    "title": "查询帮助信息",
    "group": "Help",
    "version": "0.0.1",
    "description": "<p>查询帮助信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "/help/getHelpList",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\":{\n        helpId:\"帮助信息主键id\",\n        helpTitle:\"类目\",\n        helpInstructions:\"说明\",\n        level:\"级别\",\n        parentId:\"父级id\",\n        createdTime:\"创建时间\",\n     }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "Help",
    "name": "GetHelpGethelplist"
  },
  {
    "type": "POST",
    "url": "/order/addOrder",
    "title": "添加订单",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>添加订单（每个订单 十分钟内有效）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId : \"用户id\",\n    productId:\"商品id\",\n    specId:\"规格id\",\n    productCount:\"购买数量\",\n    productPrice:\"购买单价\",\n    addressId:\"地址id\",\n    disId:\"优惠券id\",（预留）\n    expressId:\"配送方式id\"（预留）,\n    prescriptionId:\"处方id\",\n    prescriptionImage:\"处方图片\",\n    prescriptionPrice:\"处方价格\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderAddorder"
  },
  {
    "type": "POST",
    "url": "/order/cancelOrder",
    "title": "取消订单",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>取消订单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "detailId",
            "description": "<p>订单明细id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/order/cancelOrder?detailId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"成功取消\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderCancelorder"
  },
  {
    "type": "POST",
    "url": "/order/ConfirmOrder",
    "title": "确认收货",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>确认收货</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "detailId",
            "description": "<p>订单明细id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/order/ConfirmOrder?detailId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"确认成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderConfirmorder"
  },
  {
    "type": "POST",
    "url": "/order/deleteOrder",
    "title": "删除订单",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>删除订单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "detailId",
            "description": "<p>订单明细id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/order/deleteOrder?detailId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"成功取消\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderDeleteorder"
  },
  {
    "type": "POST",
    "url": "/order/getOrderDetailById",
    "title": "查看订单详情",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>查看订单详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "detailId",
            "description": "<p>订单明细id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/order/getOrderDetailById?detailId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       detailId:\"订单明细主键\",\n       orderId:\"订单id\",\n       productId:\"商品id\",\n       shopId:\"店铺id\",\n       specId:\"规格id\",\n       productName:\"商品名称\",\n       productCount:\"购买数量\",\n       productPrice:\"购买单价\",\n       updatedTime:\"修改时间\",\n       order{\"订单详细信息\"},\n       product{\"商品详细信息\"},\n       productSpec{\"规格详细信息\"}\n       shop{\"店铺基本信息\"}（预留）\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderGetorderdetailbyid"
  },
  {
    "type": "POST",
    "url": "/order/getPayInformation",
    "title": "获取支付页面信息",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>获取支付页面信息 /order/getPayInformation?detailId=1,2</p>",
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        \"\":\"\",\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderGetpayinformation"
  },
  {
    "type": "POST",
    "url": "/order/queryOrderList",
    "title": "查看订单列表",
    "group": "Order",
    "version": "0.0.1",
    "description": "<p>查看订单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "state",
            "description": "<p>查询状态 (0=全部,1=代付款,2=代发货,3=派送中,4=已收获,5=退款订单)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNo",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/order/queryOrderList?userId=1&state=1&pageNo=1&pageSize=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       detailId:\"订单明细主键\",\n       orderId:\"订单id\",\n       productId:\"商品id\",\n       shopId:\"店铺id\",\n       specId:\"规格id\",\n       productName:\"商品名称\",\n       productCount:\"购买数量\",\n       productPrice:\"购买单价\",\n       updatedTime:\"修改时间\",\n       order{\"订单详细信息\"},\n       product{\"商品详细信息\"},\n       productSpec{\"规格详细信息\"}\n       shop{\"店铺基本信息\"}（预留）\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostOrderQueryorderlist"
  },
  {
    "type": "POST",
    "url": "/paypal/createPay",
    "title": "创建paypal支付",
    "group": "Paypal",
    "version": "0.0.1",
    "description": "<p>创建paypal支付</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "detailId",
            "description": "<p>订单明细id（多个以,号隔开）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/paypal/createPay?userId=1&detailId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"请求地址\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/PaymentController.java",
    "groupTitle": "Paypal",
    "name": "PostPaypalCreatepay"
  },
  {
    "type": "GET",
    "url": "/prescription/addPrescriptionByUser",
    "title": "添加用户处方信息",
    "group": "Prescription",
    "version": "0.0.1",
    "description": "<p>添加用户处方信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    prescriptionName:\"处方名称\",\n    rightSphere:\"镜片度数\",\n    rightCylinder:\"散光度数\",\n    rightAxis:\"散光轴度\"\n    rightAdd:\"需要增加的度数\"\n    rightPd:\"远用瞳距\"\n    leftSphere:\"镜片度数\"\n    leftCylinder:\"散光度数\"\n    leftAxis:\"散光轴度\"\n    leftAdd:\"需要增加的度数\"\n    leftPd:\"远用瞳距\"\n    nearPd:\"近用瞳距\"\n    userId:\"用户id\"\n    comment:\"附加信息\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"添加成功返回处方id\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/PrescriptionController.java",
    "groupTitle": "Prescription",
    "name": "GetPrescriptionAddprescriptionbyuser"
  },
  {
    "type": "GET",
    "url": "/prescription/deletePrescription",
    "title": "删除用户处方信息",
    "group": "Prescription",
    "version": "0.0.1",
    "description": "<p>删除用户处方信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "prescriptionId",
            "description": "<p>处方id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/prescription/deletePrescription?prescriptionId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"删除成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/PrescriptionController.java",
    "groupTitle": "Prescription",
    "name": "GetPrescriptionDeleteprescription"
  },
  {
    "type": "GET",
    "url": "/prescription/getPrescriptionKVList",
    "title": "获取处方定义信息",
    "group": "Prescription",
    "version": "0.0.1",
    "description": "<p>获取处方定义信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/prescription/getPrescriptionKVList",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       pkName:\"key说明\",\n       state:\"0处方使用信息 1处方度数信息\"\n       valueList{\n           pvDetailed:\"标题/度数的取值范围（范围用_表示 逗号后面表示差值）\"\n           pvIntroduce:\"介绍/插入的其他选择值\"\n           pvPrice:\"价格\"\n           stringList:\"select标签里的数据\"\n       }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/PrescriptionController.java",
    "groupTitle": "Prescription",
    "name": "GetPrescriptionGetprescriptionkvlist"
  },
  {
    "type": "GET",
    "url": "/prescription/getPrescriptionUserList",
    "title": "获取用户处方信息",
    "group": "Prescription",
    "version": "0.0.1",
    "description": "<p>获取用户处方信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/prescription/getPrescriptionUserList?userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       prescriptionId:\"处方id\"\n       prescriptionName:\"处方名称\",\n       rightSphere:\"镜片度数\",\n       rightCylinder:\"散光度数\",\n       rightAxis:\"散光轴度\",\n       rightAdd:\"需要增加的度数\",\n       rightPd:\"远用瞳距\",\n       leftSphere:\"镜片度数\",\n       leftCylinder:\"散光度数\",\n       leftAxis:\"散光轴度\",\n       leftAdd:\"需要增加的度数\",\n       leftPd:\"远用瞳距\",\n       nearPd:\"近用瞳距\",\n       userId:\"用户id\",\n       comment:\"附加信息\",\n       createdTime:\"创建时间\",\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/PrescriptionController.java",
    "groupTitle": "Prescription",
    "name": "GetPrescriptionGetprescriptionuserlist"
  },
  {
    "type": "GET",
    "url": "/product/getCommodityPriceRange",
    "title": "查询商品价格区间",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>查询商品价格区间</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getCommodityPriceRange",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\"100-120\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductGetcommoditypricerange"
  },
  {
    "type": "GET",
    "url": "/product/getNewGoods",
    "title": "查询首页新品商品",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>查询首页新品商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNO",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getNewGoods?pageNO=1&pageSize=10",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        productId:\"商品表主键\",\n        shopId:\"店铺id\",\n        productName:\"商品名\",\n        brandId:\"品牌id\",\n        categoryId:\"商品分类id\",\n        price:\"商品价格\",\n        publishStatus:\"上下架状态 0未上架 1已上架\",\n        auditStatus:\"审核状态 0正在审核 1审核通过 2审核未通过\",\n        publishDate:\"上架日期\",\n        descript:\"商品描述\",\n        title:\"商品标题\",\n        subtitle:\"商品子标题\",\n        isGood:\"是否精品 0不是 1是\",\n        isNew:\"是否新品 0不是 1是\",\n        isPopular:\"是否热销 0不是 1是\",\n        seoText:\"seo描述\",\n        baseProp:\"基本属性 json串\",\n        sellProp:\"销售属性 json串\",\n        productImage:\"商品图片\",\n        sellCount:\"销售数量\",\n        viewCount:\"查看数量\",\n        createdTime:\"创建时间\",\n        updatedTime:\"修改时间\",\n        key:\"规格key集合\",\n        value:\"规格value集合\"\n        specList[      //商品规格集合\n            {\n                specId:\"商品规格主键\",\n                productId:\"商品id\",\n                productSpecs:\"商品规格json\",\n                stock:\"库存\",\n                price:\"价格\",\n                createdTime:\"预留字段\",\n                updatedTime:\"创建时间\",\n                productImage[\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    },\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    }.....\n                ]\n            }....\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductGetnewgoods"
  },
  {
    "type": "GET",
    "url": "/product/getProductCategory",
    "title": "查询商品类别",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>查询商品类别</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "level",
            "description": "<p>分类级别 (1,2,3)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>查询类别（1：查询当前级别，2查询当前级别及下一级级别，3查询当前级别以及下级所有级别）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getProductCategory?level=1&type=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n           categoryId:\"主键id\",\n           categoryName:\"分类名称\",\n           parentId:\"父级id\",\n           categoryLevel:\"当前级别\",\n           cateGoryList:\"下级分类{...}\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductGetproductcategory"
  },
  {
    "type": "GET",
    "url": "/product/getProductParamList",
    "title": "查询店铺标签",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>查询店铺标签</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "shopId",
            "description": "<p>店铺id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getProductParamList?shopId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        typeId:\"参数类型id\",\n        typeName:\"参数类型名称\",\n        shopId:\"店铺id\",\n        createdTime:\"添加时间\",\n        paramValues[ //参数value\n            {\n                valueId:\"参数值主键\",\n                paramValue:\"参数值内容\",\n                typeId:\"店铺id\",\n                createdTime:\"添加时间\",\n                paramImage:\"参数图\",\n                propCount:\"商品数量\"\n            }\n            ...\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductGetproductparamlist"
  },
  {
    "type": "GET",
    "url": "/product/getSortingWay",
    "title": "获取排序方式规则",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>获取排序方式规则</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getSortingWay",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        sortingId:\"主键id\",\n        title:\"描述\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductGetsortingway"
  },
  {
    "type": "GET",
    "url": "/product/getStoreDistribution",
    "title": "获取店铺配送方式",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>获取店铺配送方式</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "shopId",
            "description": "<p>店铺id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getStoreDistribution?shopId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductGetstoredistribution"
  },
  {
    "type": "POST",
    "url": "/product/getProductById",
    "title": "商品详情",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>商品详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "productId",
            "description": "<p>商品id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/getProductById?productId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        productId:\"商品表主键\",\n        shopId:\"店铺id\",\n        productName:\"商品名\",\n        brandId:\"品牌id\",\n        categoryId:\"商品分类id\",\n        price:\"商品价格\",\n        publishStatus:\"上下架状态 0未上架 1已上架\",\n        auditStatus:\"审核状态 0正在审核 1审核通过 2审核未通过\",\n        publishDate:\"上架日期\",\n        descript:\"商品描述\",\n        title:\"商品标题\",\n        subtitle:\"商品子标题\",\n        isGood:\"是否精品 0不是 1是\",\n        isNew:\"是否新品 0不是 1是\",\n        isPopular:\"是否热销 0不是 1是\",\n        seoText:\"seo描述\",\n        baseProp:\"基本属性 json串\",\n        sellProp:\"销售属性 json串\",\n        productImage:\"商品图片\",\n        sellCount:\"销售数量\",\n        viewCount:\"查看数量\",\n        createdTime:\"创建时间\",\n        updatedTime:\"修改时间\",\n        key:\"规格key集合\",\n        value:\"规格value集合\"\n        specList[      //商品规格集合\n            {\n                specId:\"商品规格主键\",\n                productId:\"商品id\",\n                productSpecs:\"商品规格json\",\n                stock:\"库存\",\n                price:\"价格\",\n                createdTime:\"预留字段\",\n                updatedTime:\"创建时间\",\n                productImage[\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    },\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    }.....\n                ]\n            }....\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductGetproductbyid"
  },
  {
    "type": "POST",
    "url": "/product/getProductList",
    "title": "查询商品",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>查询商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productName:\"商品名称\"\n    categoryId:\"商品分类id\",  （选填）\n    lIdList:\"标签id（数组格式）\"（选填）\n    pageNo:\"页码\"   （必填）,\n    pageSize:\"条数\" （必填）,\n    sorting:\"排序方式id\"\n    startPrice:\"开始价格\"（选填）,\n    endPrice:\"结束价格\"（选填）,\n    trId:\"趋势id\"（选填）,\n    disType:\"0:折扣商品\"（选填）,\n    raId:\"热门活动id\"（选填）\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "0:",
            "description": "<p>请求成功;</p>"
          },
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        productId:\"商品表主键\",\n        shopId:\"店铺id\",\n        productName:\"商品名\",\n        brandId:\"品牌id\",\n        categoryId:\"商品分类id\",\n        price:\"商品价格\",\n        publishStatus:\"上下架状态 0未上架 1已上架\",\n        auditStatus:\"审核状态 0正在审核 1审核通过 2审核未通过\",\n        publishDate:\"上架日期\",\n        descript:\"商品描述\",\n        title:\"商品标题\",\n        subtitle:\"商品子标题\",\n        isGood:\"是否精品 0不是 1是\",\n        isNew:\"是否新品 0不是 1是\",\n        isPopular:\"是否热销 0不是 1是\",\n        seoText:\"seo描述\",\n        baseProp:\"基本属性 json串\",\n        sellProp:\"销售属性 json串\",\n        productImage:\"商品图片\",\n        sellCount:\"销售数量\",\n        viewCount:\"查看数量\",\n        createdTime:\"创建时间\",\n        updatedTime:\"修改时间\",\n        key:\"规格key集合\",\n        value:\"规格value集合\"\n        preferentialType:\"优惠类型 0满减 1打折 2免邮 3买送 4降价\",\n        preferentialStr:\"优惠信息 （满100减30）\"\n        specList[      //商品规格集合\n            {\n                specId:\"商品规格主键\",\n                productId:\"商品id\",\n                productSpecs:\"商品规格json\",\n                stock:\"库存\",\n                price:\"价格\",\n                preferentialPrice:\"优惠价格\",\n                createdTime:\"预留字段\",\n                updatedTime:\"创建时间\",\n                productImage[\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    },\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    }.....\n                ]\n            }....\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductGetproductlist"
  },
  {
    "type": "GET",
    "url": "/slides/getSlidesList",
    "title": "获取轮播图",
    "group": "Slides",
    "version": "0.0.1",
    "description": "<p>获取轮播图</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/slides/getSlidesList",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       slidesId:\"轮播图id\",\n       slideImage:\"图片\",\n       slideSort:\"排序\",\n       imageUrl:\"图片跳转链接\",\n       createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/SlidesController.java",
    "groupTitle": "Slides",
    "name": "GetSlidesGetslideslist"
  },
  {
    "type": "GET",
    "url": "/user/getCustomerService",
    "title": "获取客服服务信息",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>添加客户反馈</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getCustomerService",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "GET",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        customerCareHours:\"分别对应前端展示的三个参数\",\n        callMe:\"分别对应前端展示的三个参数\",\n        warrantySpare:\"分别对应前端展示的三个参数\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetcustomerservice"
  },
  {
    "type": "POST",
    "url": "/user/addUserBySystem",
    "title": "系统用户注册",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>系统用户注册</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userEmail:\"邮箱\",(必填)\n    password:\"密码\", (必填)\n    lastName:\"姓\",   (必填)\n    firstName:\"名\",  (必填)\n    code:\"验证码\"     (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        \"userId\":\"用户id\"\n        \"token\":\"token\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserAdduserbysystem"
  },
  {
    "type": "POST",
    "url": "/user/customerFeedback",
    "title": "添加客户反馈",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>添加客户反馈</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",\n    mTopic:\"主题\",\n    mEmail:\"邮箱地址\",\n    mContent:\"反馈内容\",\n    firstName:\"名\",\n    lastName:\"姓\",\n    orderNumber:\"订单号\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"反馈成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserCustomerfeedback"
  },
  {
    "type": "POST",
    "url": "/user/facebookLogin",
    "title": "faceBook登录（默认注册）",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>faceBook或谷歌登录（默认注册）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    lastName:\"姓\",   (必填)\n    firstName:\"名\",  (必填)\n    facebookUserId:\"用户id\" （必填）\n    accessToken:\"token\" （必填）\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        \"userId\":\"用户id\"\n        \"token\":\"token\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserFacebooklogin"
  },
  {
    "type": "POST",
    "url": "/user/getEmailCode/",
    "title": "发送邮箱验证",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>生成图文验证码</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱地址 （必填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getEmailCode?email=1473747181@qq.com",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"发送成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserGetemailcode"
  },
  {
    "type": "POST",
    "url": "/user/getUserById",
    "title": "获取用户详细信息",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户详细信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id （必填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getUserById?userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserGetuserbyid"
  },
  {
    "type": "POST",
    "url": "/user/login",
    "title": "邮箱密码登陆",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>邮箱密码登陆</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userEmail:\"邮箱\", (必填)\n    password:\"密码\"   (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        \"userId\":\"用户id\"\n        \"token\":\"token\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserLogin"
  },
  {
    "type": "POST",
    "url": "/user/retrievePassword",
    "title": "找回密码",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户头像</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    password:\"密码\",      (必填)\n    code :\"验证码\",       (必填)\n    userEmail:\"邮箱\",      (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"找回密码成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserRetrievepassword"
  },
  {
    "type": "POST",
    "url": "/user/updUserEmail",
    "title": "修改用户邮箱",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户邮箱</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",      (必填)\n    userEmail:\"用户旧邮箱\",(必填)\n    newEmail:\"用户新邮箱\", (必填)\n    password:\"用户密码\",   (必填)\n    code:\"图形验证码\"      (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpduseremail"
  },
  {
    "type": "POST",
    "url": "/user/updUserNikeName",
    "title": "修改用户昵称",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户昵称</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",      (必填)\n    nickName:\"用户昵称\"    (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpdusernikename"
  },
  {
    "type": "POST",
    "url": "/user/updUserPassword",
    "title": "修改用户密码",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户密码</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",     (必填)\n    password:\"原密码\",   (必填)\n    newPassword:\"新密码\" (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpduserpassword"
  },
  {
    "type": "POST",
    "url": "/user/updUserPhone",
    "title": "修改用户电话",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户电话</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",(必填)\n    phone:\"用户电话\" (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpduserphone"
  },
  {
    "type": "POST",
    "url": "/user/updUserPhoto",
    "title": "修改用户头像",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户头像</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",      (必填)\n    userPhoto:\"用户头像\",  (必填)\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpduserphoto"
  },
  {
    "type": "POST",
    "url": "/user/verification",
    "title": "生成图文验证码",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>生成图文验证码</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id （必填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/verification?userId=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "success": [
          {
            "group": "success",
            "type": "POST",
            "optional": false,
            "field": "code",
            "description": "<p>0:请求成功;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall/src/main/java/com/tangmo/emall/controller/UserController.java",
    "groupTitle": "User",
    "name": "PostUserVerification"
  }
] });
