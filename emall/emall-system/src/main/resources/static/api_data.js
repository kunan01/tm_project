define({ "api": [
  {
    "type": "GET",
    "url": "/advertising/delAdvertising",
    "title": "删除热门活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>删除热门活动广告</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/advertising/delAdvertising?raId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "GetAdvertisingDeladvertising"
  },
  {
    "type": "GET",
    "url": "/advertising/getAdvertisingList",
    "title": "获取热门活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>获取热门活动广告</p>",
    "parameter": {
      "fields": {
        "Parameter": [
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
          "content": "/advertising/getAdvertisingList?pageN=1&pageNo=10",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "GetAdvertisingGetadvertisinglist"
  },
  {
    "type": "GET",
    "url": "/advertising/getAdvertisingProductList",
    "title": "获取热门活动广告商品",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>获取热门活动广告商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "raId",
            "description": "<p>热门活动id</p>"
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
          "content": "/advertising/getAdvertisingProductList?raId=1&pageNo=1&pageSize=10",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "GetAdvertisingGetadvertisingproductlist"
  },
  {
    "type": "POST",
    "url": "/advertising/addAdvertising",
    "title": "添加热门活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>添加热门活动广告</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    advertisingImage:\"广告图片\"\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingAddadvertising"
  },
  {
    "type": "POST",
    "url": "/advertising/addAdvertisingProduct",
    "title": "添加热门活动广告商品",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>添加热门活动广告商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "raId",
            "description": "<p>热门活动广告id</p>"
          },
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
          "title": "请求样例：",
          "content": "/advertising/addAdvertisingProduct?raId=1&productId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingAddadvertisingproduct"
  },
  {
    "type": "POST",
    "url": "/advertising/batchAddAdvertisingProduct",
    "title": "批量添加热门活动广告商品",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>批量添加热门活动广告商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    productIdList:[商品id数组],\n    raId:热门活动广告id\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingBatchaddadvertisingproduct"
  },
  {
    "type": "POST",
    "url": "/advertising/batchDelAdvertising",
    "title": "批量删除热门活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>批量删除热门活动广告</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    raIdList:[主键数组]\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingBatchdeladvertising"
  },
  {
    "type": "POST",
    "url": "/advertising/batchDelAdvertisingProduct",
    "title": "批量删除热门活动广告商品",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>批量删除热门活动广告商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    trIdList:[数组：热门活动商品主键id]\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingBatchdeladvertisingproduct"
  },
  {
    "type": "POST",
    "url": "/advertising/delAdvertisingProduct",
    "title": "删除热门活动广告商品",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>删除热门活动广告商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "trId",
            "description": "<p>热门活动商品主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/advertising/delAdvertisingProduct?trId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingDeladvertisingproduct"
  },
  {
    "type": "POST",
    "url": "/advertising/updateAdvertising",
    "title": "修改热门活动广告",
    "group": "Activity",
    "version": "0.0.1",
    "description": "<p>修改热门活动广告</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    raId:\"广告id\" （必填）\n    advertisingImage:\"广告图片\" （可选）\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/AdvertisingController.java",
    "groupTitle": "Activity",
    "name": "PostAdvertisingUpdateadvertising"
  },
  {
    "type": "GET",
    "url": "/category/batchDelCateGory",
    "title": "批量删除商品分类",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>批量删除商品分类（如果当前分类下有子集分类，则删除）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    categoryIdList:[数组]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "GetCategoryBatchdelcategory"
  },
  {
    "type": "GET",
    "url": "/category/delCateGory",
    "title": "删除商品分类",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>删除商品分类（如果当前分类下有子集分类，则删除）</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "cId",
            "description": "<p>商品分类id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/category/delCateGory?cId=1&userId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "GetCategoryDelcategory"
  },
  {
    "type": "GET",
    "url": "/category/delSortingWay",
    "title": "删除商品筛选方式",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>删除商品筛选方式</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "sId",
            "description": "<p>筛选方式id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/category/delSortingWay?sId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "GetCategoryDelsortingway"
  },
  {
    "type": "GET",
    "url": "/category/getCateGoryList",
    "title": "获取商品分类",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>获取商品分类</p>",
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
          "title": "请求样例:",
          "content": "/category/getCateGoryList?level=1&type=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n           categoryId:\"主键id\",\n           categoryName:\"分类名称\",\n           parentId:\"父级id\",\n           categoryLevel:\"当前级别\",\n           cateGoryList:\"下级分类{...}\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "GetCategoryGetcategorylist"
  },
  {
    "type": "GET",
    "url": "/category/getIsPType",
    "title": "校验当前分类下是否存在子集分类",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>校验当前分类下是否存在子集分类</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "cId",
            "description": "<p>商品分类id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/category/getIsPType?cId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"true:存在/false:不存在\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "GetCategoryGetisptype"
  },
  {
    "type": "GET",
    "url": "/category/getSortingWay",
    "title": "获取商品排序方式",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>获取商品排序方式</p>",
    "parameter": {
      "fields": {
        "Parameter": [
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
          "title": "请求样例:",
          "content": "/category/getSortingWay?pageN=1&pageNo=10",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        sortingId:\"筛选方式id\",\n        title:\"描述\",\n        sortingRules:\"排序规则sql定义\",\n        createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "GetCategoryGetsortingway"
  },
  {
    "type": "POST",
    "url": "/category/addCategory",
    "title": "增加商品分类",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>增加商品分类</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    categoryName:\"分类名称\",    (必填)\n    parentId:\"父级id\",         (必填 添加一级分类父级id填0)\n    categoryLevel:\"当前等级\",   (必填 例 1，2，3)\n    sysUserId:\"操作用户id\"      (必填)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "PostCategoryAddcategory"
  },
  {
    "type": "POST",
    "url": "/category/addSortingWay",
    "title": "增加商品排序方式",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>增加商品排序方式</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    title:\"排序名称\"\n    sortingRules:\"排序方式sql规则\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "PostCategoryAddsortingway"
  },
  {
    "type": "POST",
    "url": "/category/batchDelSortingWay",
    "title": "批量删除商品排序方式",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>批量删除商品排序方式</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    sortingIdList:[数组：筛选方式id]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "PostCategoryBatchdelsortingway"
  },
  {
    "type": "POST",
    "url": "/category/updateSortingWay",
    "title": "修改商品排序方式",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>修改商品排序方式</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    sortingId:\"规则id\",\n    title:\"排序名称\",\n    sortingRules:\"排序方式sql规则\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "PostCategoryUpdatesortingway"
  },
  {
    "type": "POST",
    "url": "/category/updCategory",
    "title": "修改商品分类名称",
    "group": "CateGory",
    "version": "0.0.1",
    "description": "<p>修改商品分类</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    categoryId:\"分类id\"，       (必填)\n    categoryName:\"分类名称\",    (必填)\n    sysUserId:\"操作用户id\"      (必填)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/CateGoryController.java",
    "groupTitle": "CateGory",
    "name": "PostCategoryUpdcategory"
  },
  {
    "type": "GET",
    "url": "/distribution/delDistribution",
    "title": "删除店铺配送方式",
    "group": "Distribution",
    "version": "0.0.1",
    "description": "<p>删除店铺配送方式</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "shipId",
            "description": "<p>配送方式主键</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/distribution/delDistribution?shipId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/DistributionController.java",
    "groupTitle": "Distribution",
    "name": "GetDistributionDeldistribution"
  },
  {
    "type": "GET",
    "url": "/distribution/getStoreDistribution",
    "title": "获取店铺配送方式",
    "group": "Distribution",
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
            "description": "<p>店铺id(死值:1)</p>"
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
          "title": "请求样例:",
          "content": "/distribution/getStoreDistribution?shopId=1&pageNo=1&pageSize=10",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        shipId:\"店铺配送主键\",\n        descript:\"描述\",\n        price:\"价格\",\n        shopId:\"店铺id\",\n        createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/DistributionController.java",
    "groupTitle": "Distribution",
    "name": "GetDistributionGetstoredistribution"
  },
  {
    "type": "POST",
    "url": "/distribution/addDistribution",
    "title": "添加店铺配送方式",
    "group": "Distribution",
    "version": "0.0.1",
    "description": "<p>修改店铺配送方式</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    shopId:\"店铺配送主键\",(必填)\n    descript:\"描述\",     (必填)\n    price:\"价格\"         (必填)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/DistributionController.java",
    "groupTitle": "Distribution",
    "name": "PostDistributionAdddistribution"
  },
  {
    "type": "POST",
    "url": "/distribution/batchDelDistribution",
    "title": "批量删除店铺配送方式",
    "group": "Distribution",
    "version": "0.0.1",
    "description": "<p>批量删除店铺配送方式</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    shipIdList:[主键数组]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/DistributionController.java",
    "groupTitle": "Distribution",
    "name": "PostDistributionBatchdeldistribution"
  },
  {
    "type": "POST",
    "url": "/distribution/updateDistribution",
    "title": "修改店铺配送方式",
    "group": "Distribution",
    "version": "0.0.1",
    "description": "<p>修改店铺配送方式</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    shipId:\"店铺配送主键\",(必填)\n    descript:\"描述\",     (可选)\n    price:\"价格\"         (可选)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/DistributionController.java",
    "groupTitle": "Distribution",
    "name": "PostDistributionUpdatedistribution"
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
    "filename": "E:/emall-system/src/main/resources/static/main.js",
    "group": "E__emall_system_src_main_resources_static_main_js",
    "groupTitle": "E__emall_system_src_main_resources_static_main_js",
    "name": ""
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/FileController.java",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/FileController.java",
    "groupTitle": "File",
    "name": "PostFileUpload"
  },
  {
    "type": "GET",
    "url": "/help/deleteSysHelpList",
    "title": "删除帮助信息",
    "group": "Help",
    "version": "0.0.1",
    "description": "<p>删除帮助信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "helpId",
            "description": "<p>帮助信息id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/help/deleteSysHelpList?helpId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\":{\"删除成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/HelpController.java",
    "groupTitle": "Help",
    "name": "GetHelpDeletesyshelplist"
  },
  {
    "type": "GET",
    "url": "/help/getSysHelpList",
    "title": "查询帮助信息",
    "group": "Help",
    "version": "0.0.1",
    "description": "<p>查询帮助信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "level",
            "description": "<p>查询级别 （1，2）</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "isPage",
            "description": "<p>是否分页 0全部 1分页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "parentId",
            "description": "<p>父级id（查询级别等于2时必填）</p>"
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
          "title": "请求样例:",
          "content": "/help/getSysHelpList?pageNo=1&pageSize=10",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/HelpController.java",
    "groupTitle": "Help",
    "name": "GetHelpGetsyshelplist"
  },
  {
    "type": "POST",
    "url": "/help/batchDeleteSysHelpList",
    "title": "批量删除帮助信息",
    "group": "Help",
    "version": "0.0.1",
    "description": "<p>批量删除帮助信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    helpIdList:[数组：帮助信息id]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\":{\"删除成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/HelpController.java",
    "groupTitle": "Help",
    "name": "PostHelpBatchdeletesyshelplist"
  },
  {
    "type": "POST",
    "url": "/help/getSysHelpList",
    "title": "添加帮助信息",
    "group": "Help",
    "version": "0.0.1",
    "description": "<p>添加帮助信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    helpTitle:\"类目\",  (必填)\n    helpInstructions:\"说明\", (必填)\n    level:\"级别(1,2)\", (必填)\n    parentId:\"父级id(如果级别为2，则父级id必传)\", (选填)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\":{\"添加成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/HelpController.java",
    "groupTitle": "Help",
    "name": "PostHelpGetsyshelplist"
  },
  {
    "type": "POST",
    "url": "/help/updateSysHelpList",
    "title": "修改帮助信息",
    "group": "Help",
    "version": "0.0.1",
    "description": "<p>修改帮助信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    helpId:\"帮助信息id\"  （必填）\n    helpTitle:\"类目\",  (选填)\n    helpInstructions:\"说明\", (选填)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\":{\"修改成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/HelpController.java",
    "groupTitle": "Help",
    "name": "PostHelpUpdatesyshelplist"
  },
  {
    "type": "GET",
    "url": "/param/delShopParamType",
    "title": "删除店铺参数类型",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>删除店铺参数类型（如果当前类型下有value 则删除）</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "typeId",
            "description": "<p>参数类型主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/param/delShopParamValue?typeId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "GetParamDelshopparamtype"
  },
  {
    "type": "GET",
    "url": "/param/delShopParamValue",
    "title": "删除店铺参数值",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>删除店铺参数值</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "valueId",
            "description": "<p>参数值主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/param/delShopParamValue?valueId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "GetParamDelshopparamvalue"
  },
  {
    "type": "GET",
    "url": "/param/getShopParamKeyList",
    "title": "查询店铺参数类型",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>查询店铺参数类型</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "shopId",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "state",
            "description": "<p>查询状态 1查询全部 2分页查询</p>"
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
          "title": "请求样例:",
          "content": "/param/getShopParamKeyList?shopId=1&state=1&pageNo=1&pageSize=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        typeId:\"参数类型id\",\n        typeName:\"参数类型名称\",\n        shopId:\"店铺id\",\n        createdTime:\"添加时间\",\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "GetParamGetshopparamkeylist"
  },
  {
    "type": "GET",
    "url": "/param/getShopParamValueList",
    "title": "查询店铺参数值",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>查询店铺参数值</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "typeId",
            "description": "<p>类型id</p>"
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
          "title": "请求样例:",
          "content": "/param/getShopParamKeyList?typeId=1&pageNo=1&pageSize=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n           valueId:\"参数值主键\",\n           paramValue:\"参数值内容\",\n           typeId:\"店铺id\",\n           createdTime:\"添加时间\",\n           paramImage:\"参数图\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "GetParamGetshopparamvaluelist"
  },
  {
    "type": "POST",
    "url": "/param/addShopParamType",
    "title": "增加店铺参数类型",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>增加店铺参数类型</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    typeName:\"类型名称\",\n    shopId:\"店铺id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "PostParamAddshopparamtype"
  },
  {
    "type": "POST",
    "url": "/param/addShopParamValue",
    "title": "增加店铺参数值",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>增加店铺参数值</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    paramValue:\"参数值\",\n    typeId:\"类型id\",\n    paramImage:\"参数图片\" （可选）\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "PostParamAddshopparamvalue"
  },
  {
    "type": "POST",
    "url": "/param/batchDelShopParamType",
    "title": "批量删除店铺参数类型",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>批量删除店铺参数类型（如果当前类型下有value 则删除）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    typeIdList:[主键数组]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "PostParamBatchdelshopparamtype"
  },
  {
    "type": "POST",
    "url": "/param/batchDelShopParamValue",
    "title": "批量删除店铺参数值",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>批量删除店铺参数值</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    valueIdList:[主键数组]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "PostParamBatchdelshopparamvalue"
  },
  {
    "type": "POST",
    "url": "/param/updShopParamType",
    "title": "修改店铺类型",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>修改店铺类型</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    typeName:\"类型名称\",\n    typeId:\"类型id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "PostParamUpdshopparamtype"
  },
  {
    "type": "POST",
    "url": "/param/updShopParamValue",
    "title": "修改店铺参数值",
    "group": "Param",
    "version": "0.0.1",
    "description": "<p>修改店铺参数值</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    valueId:\"主键id\",     (必填)\n    paramValue:\"参数值\",\n    typeId:\"类型id\",\n    paramImage:\"参数图片\", （不能和原图一致）\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopParamController.java",
    "groupTitle": "Param",
    "name": "PostParamUpdshopparamvalue"
  },
  {
    "type": "GET",
    "url": "/product/delProductParam",
    "title": "删除商品标签(多个以逗号隔开)",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>删除商品标签(多个以逗号隔开)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lId",
            "description": "<p>: 标签主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/delProductParam?lId=1,2",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "GetProductDelproductparam"
  },
  {
    "type": "POST",
    "url": "/product/addProduct",
    "title": "增加商品基本信息",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>增加商品基本信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    shopId:\"店铺id\",\n    productName:\"商品名\",\n    brandId:\"品牌id\",\n    categoryId:\"商品分类id\",\n    descript:\"商品描述\",\n    title:\"商品标题\",\n    subtitle:\"商品子标题\",\n    seoText:\"seo描述\",\n    baseProp:\"基本属性，json串（例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'}）\",\n    sellProp:\"销售属性，json串 (例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'})\",\n    productImage:\"此图为商品的展示图片，只能一张！\",\n    price:\"价格：此为商品展示价格(真实价格还需填充规格)\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductAddproduct"
  },
  {
    "type": "POST",
    "url": "/product/addProductParam",
    "title": "添加商品标签",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>添加商品标签</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productId:\"商品id\",\n    valueId:\"属性值id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductAddproductparam"
  },
  {
    "type": "POST",
    "url": "/product/addProductProp",
    "title": "添加商品规格",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>添加商品规格</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productId:\"商品id\",\n    productSpecs:\"商品规格json（例：{'Color':'#00000|Black','Size':'Size库id'}）\",\n    stock:\"库存\",\n    price:\"价格\",\n    productImages[\n        {\n            imageUrl:\"规格图片\",\n            imageDesc:\"图片描述\",  (选填)\n            isMaster:\"是否主图 0不是 1是\"\n            imageOrder:\"图片排序\",\n        },\n        {\n            imageUrl:\"规格图片\",\n            imageDesc:\"图片描述\",  (选填)\n            isMaster:\"是否主图 0不是 1是\"\n            imageOrder:\"图片排序\",\n        },\n        ....\n    ]\n\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductAddproductprop"
  },
  {
    "type": "POST",
    "url": "/product/delProduct",
    "title": "删除商品",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>删除商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productId:\"商品id\",\n    shopUserId:\"店铺工作人员id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductDelproduct"
  },
  {
    "type": "POST",
    "url": "/product/delProductProp",
    "title": "删除商品规格",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>删除商品规格</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    specId:\"规格id\",\n    shopUserId:\"店铺工作人员id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductDelproductprop"
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
          "content": "{\n    shopId:\"店铺id\",                       （可选）\n    productName:\"商品名模糊\",               （可选）\n    brandId:\"品牌id\",                      （可选）\n    publishStatus:\"上下架状态 0未上架 1已上架\",（可选）\n    auditStatus:\"审核状态 0正在审核 1审核通过 2审核未通过\",    （可选）\n    isGood:\"是否精品 0不是 1是\",              （可选）\n    isNew:\"是否新品 0不是 1是\",               （可选）\n    isPopular:\"是否热销 0不是 1是\",           （可选）\n    sorting:\"排序方式 0发布时间正序 1发布时间倒叙 2销售量正序 3销售量倒叙 4游览量正序 5浏览量倒叙\" （必填）\n    pageNo:\"页码\",                           （必填）\n    pageSize:\"条数\"                          （必填）\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        productId:\"商品表主键\",\n        shopId:\"店铺id\",\n        productName:\"商品名\",\n        brandId:\"品牌id\",\n        categoryId:\"商品分类id\",\n        price:\"商品价格\",\n        publishStatus:\"上下架状态 0未上架 1已上架\",\n        auditStatus:\"审核状态 0正在审核 1审核通过 2审核未通过\",\n        publishDate:\"上架日期\",\n        descript:\"商品描述\",\n        title:\"商品标题\",\n        subtitle:\"商品子标题\",\n        isGood:\"是否精品 0不是 1是\",\n        isNew:\"是否新品 0不是 1是\",\n        isPopular:\"是否热销 0不是 1是\",\n        seoText:\"seo描述\",\n        baseProp:\"基本属性 json串\",\n        sellProp:\"销售属性 json串\",\n        productImage:\"商品图片\",\n        sellCount:\"销售数量\",\n        viewCount:\"查看数量\",\n        createdTime:\"创建时间\",\n        updatedTime:\"修改时间\",\n        specList[      //商品规格集合\n            {\n                specId:\"商品规格主键\",\n                productId:\"商品id\",\n                productSpecs:\"商品规格json\",\n                stock:\"库存\",\n                price:\"价格\",\n                createdTime:\"预留字段\",\n                updatedTime:\"创建时间\",\n                productImage[\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    },\n                    {\n                        imageId:\"商品图片表主键\",\n                        imageUrl:\"图片\",\n                        imageDesc:\"图片描述\",\n                        isMaster:\"是否主图 0不是 1是\",\n                        imageStatus:\"图片状态 0无效 1有效\"\n                    }.....\n                ]\n            }....\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductGetproductlist"
  },
  {
    "type": "POST",
    "url": "/product/setOrCancelNewProduct",
    "title": "设置或取消新品商品",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>设置或取消新品商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "productId",
            "description": "<p>商品id（多个以逗号隔开）</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "isNew",
            "description": "<p>是否新品：0不是 1是</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/product/setOrCancelNewProduct?productId=1,2&isNew=0",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"上架成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductSetorcancelnewproduct"
  },
  {
    "type": "POST",
    "url": "/product/shelvesProduct",
    "title": "上架商品",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>上架商品（上架成功后不能对商品进行编辑）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productId:\"商品id\",\n    shopUserId:\"店铺工作人员id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"上架成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductShelvesproduct"
  },
  {
    "type": "POST",
    "url": "/product/theShelvesProduct",
    "title": "下架商品",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>下架商品（下架操作会导致用户购物车或者未支付的订单不能进行购买，请谨慎操作）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productId:\"商品id\",\n    shopUserId:\"店铺工作人员id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"上架成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductTheshelvesproduct"
  },
  {
    "type": "POST",
    "url": "/product/updProduct",
    "title": "修改商品基本信息",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>修改商品基本信息（修改哪个属性传那个属性，不修改千万不能传，尤其是图片和json）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    productId:\"商品id\",               (必填)\n    productName:\"商品名\",\n    brandId:\"品牌id\",\n    categoryId:\"商品分类id  三级id\",\n    descript:\"商品描述\",\n    title:\"商品标题\",\n    subtitle:\"商品子标题\",\n    seoText:\"seo描述\",\n    baseProp:\"基本属性，json串（例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'}）\",\n    sellProp:\"销售属性，json串 (例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'})\",\n    productImage:\"图片id：此图为商品的展示图片，只能一张！\",\n    price:\"价格：此为商品展示价格(真实价格还需填充规格)\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductUpdproduct"
  },
  {
    "type": "POST",
    "url": "/product/updProductProp",
    "title": "修改商品规格",
    "group": "Product",
    "version": "0.0.1",
    "description": "<p>修改商品规格</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    specId:\"规格id\",\n    productSpecs:\"商品规格json（例：{'颜色':'红色','尺码':'L'}）\",    (选填)\n    stock:\"库存\",     (选填)\n    price:\"价格\",     (选填)\n    productImages:[   (选填)\n        {\n            imageId:\"图片主键\",\n            imageUrl:\"规格图片\", (选填)\n            imageDesc:\"图片描述\",(选填)\n            isMaster:\"是否主图 0不是 1是\"  (选填)\n            imageOrder:\"图片排序\", (选填)\n        },\n        {\n            imageId:\"图片主键\",\n            imageUrl:\"规格图片\",  (选填)\n            imageDesc:\"图片描述\",  (选填)\n            isMaster:\"是否主图 0不是 1是\"   (选填)\n            imageOrder:\"图片排序\", (选填)\n        },\n        ....\n    ]\n\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ProductController.java",
    "groupTitle": "Product",
    "name": "PostProductUpdproductprop"
  },
  {
    "type": "GET",
    "url": "/shopProp/getIsValueByKey",
    "title": "校验当前key下是否存在value",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>校验当前key下是否存在value</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "keyId",
            "description": "<p>规格KeyId</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/shopProp/getIsValueByKey?keyId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"true:存在/false:不存在\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "GetShoppropGetisvaluebykey"
  },
  {
    "type": "GET",
    "url": "/shopProp/getListKey",
    "title": "获取规格key集合",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>获取规格key集合</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "shopId",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "state",
            "description": "<p>查询状态 1查询全部 2分页查询</p>"
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
          "title": "请求样例:",
          "content": "/shopProp/getListKey?shopId=1&state=1&pageNo=1&pageSize=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        keyId:\"key主键id\"\n        keyName:\"key属性名\"\n        shopId:\"店铺id\"\n        createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "GetShoppropGetlistkey"
  },
  {
    "type": "GET",
    "url": "/shopProp/getListValue",
    "title": "获取规格value集合",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>获取规格value集合</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "keyId",
            "description": "<p>规格keyId</p>"
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
          "title": "请求样例:",
          "content": "/shopProp/getListValue?keyId=1&pageNo=1&pageSize=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        valueId:\"\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "GetShoppropGetlistvalue"
  },
  {
    "type": "POST",
    "url": "/shopProp/addPropKey",
    "title": "添加店铺规格key",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>添加店铺规格key</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    keyName:\"属性名称\",\n    shopId:\"店铺id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropAddpropkey"
  },
  {
    "type": "POST",
    "url": "/shopProp/addPropValue",
    "title": "添加店铺规格Value",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>添加店铺规格Value</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    keyId:\"规格keyId\",\n    propValue:\"规格value\",\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropAddpropvalue"
  },
  {
    "type": "POST",
    "url": "/shopProp/batchDelPropKey",
    "title": "批量删除店铺规格key（如果当前key下存在子集value，则删除）",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>批量删除店铺规格key（如果当前key下存在子集value，则删除）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    KeyIdList:[数组:规格key主键id]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropBatchdelpropkey"
  },
  {
    "type": "POST",
    "url": "/shopProp/batchDelPropValue",
    "title": "批量删除店铺规格Value",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>批量删除店铺规格Value</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    valueIdList:[数组:规格Value主键id]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropBatchdelpropvalue"
  },
  {
    "type": "POST",
    "url": "/shopProp/delPropKey",
    "title": "删除店铺规格key（如果当前key下存在子集value，则删除）",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>删除店铺规格key（如果当前key下存在子集value，则删除）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    keyId:\"规格key主键id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropDelpropkey"
  },
  {
    "type": "POST",
    "url": "/shopProp/delPropValue",
    "title": "删除店铺规格Value",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>删除店铺规格Value</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    valueId:\"规格Value主键id\",\n    shopUserId:\"操作用户id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropDelpropvalue"
  },
  {
    "type": "POST",
    "url": "/shopProp/updPropKey",
    "title": "修改店铺规格key 属性名称",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>修改店铺规格key 属性名称</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    keyId:\"主键\"\n    keyName:\"属性名称\",\n    shopUserId:\"操作用户id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropUpdpropkey"
  },
  {
    "type": "POST",
    "url": "/shopProp/updPropValue",
    "title": "修改店铺规格value 属性名称",
    "group": "Prop",
    "version": "0.0.1",
    "description": "<p>修改店铺规格value 属性名称</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    valueId:\"主键\"\n    propValue:\"属性名称\",\n    shopUserId:\"操作用户id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/ShopPropController.java",
    "groupTitle": "Prop",
    "name": "PostShoppropUpdpropvalue"
  },
  {
    "type": "GET",
    "url": "/slides/getSlidesList",
    "title": "查询轮播图",
    "group": "Slide",
    "version": "0.0.1",
    "description": "<p>查询轮播图</p>",
    "parameter": {
      "fields": {
        "Parameter": [
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
          "title": "请求样例:",
          "content": "/slides/getSlidesList?pageNo=1&pageSize=10",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\":{\n        slidesId:\"轮播图id\",\n        slideImage:\"图片\",\n        slideSort:\"排序\",\n        imageUrl:\"图片跳转链接\",\n        isEffect:\"是否有效\",\n        createdTime:\"创建时间\",\n        objectType:\"目标分类 1商品类别 2商品\", (选填)\n        objectId:\"目标id\"             (选填)\n     }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SlidesController.java",
    "groupTitle": "Slide",
    "name": "GetSlidesGetslideslist"
  },
  {
    "type": "POST",
    "url": "/slides/addSlides",
    "title": "添加轮播图",
    "group": "Slide",
    "version": "0.0.1",
    "description": "<p>添加轮播图</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    slideImage:\"图片\",\n    slideSort:\"排序\",\n    objectType:\"目标分类 1商品类别 2商品\",\n    objectId:\"目标id\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SlidesController.java",
    "groupTitle": "Slide",
    "name": "PostSlidesAddslides"
  },
  {
    "type": "POST",
    "url": "/slides/batchDeleteSlides",
    "title": "批量删除轮播图",
    "group": "Slide",
    "version": "0.0.1",
    "description": "<p>批量删除轮播图</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    slidesIdList:\"轮播图id数组\"(必传)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SlidesController.java",
    "groupTitle": "Slide",
    "name": "PostSlidesBatchdeleteslides"
  },
  {
    "type": "POST",
    "url": "/slides/delSlides",
    "title": "删除轮播图",
    "group": "Slide",
    "version": "0.0.1",
    "description": "<p>删除轮播图</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    slidesId:\"轮播图id\"             (必传)\n    sysUserId:\"平台用户id\"          (必传)\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"删除成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SlidesController.java",
    "groupTitle": "Slide",
    "name": "PostSlidesDelslides"
  },
  {
    "type": "POST",
    "url": "/slides/updSlides",
    "title": "修改轮播图",
    "group": "Slide",
    "version": "0.0.1",
    "description": "<p>修改轮播图</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    slidesId:\"轮播图id\"             (必传)\n    slideImage:\"图片\",              (选填)\n    slideSort:\"排序\",               (选填)\n    isEffect:\"是否有效 1有效 2无效\"    (选填)\n    objectType:\"目标分类 1商品类别 2商品\", (选填)\n    objectId:\"目标id\"             (选填)\n}",
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
            "description": "<p>请求成功;z</p>"
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SlidesController.java",
    "groupTitle": "Slide",
    "name": "PostSlidesUpdslides"
  },
  {
    "type": "GET",
    "url": "/support/dealWithMessage",
    "title": "处理客户反馈信息",
    "group": "Support",
    "version": "0.0.1",
    "description": "<p>处理客户反馈信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "mId",
            "description": "<p>信息主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/support/dealWithMessage?mId=1",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"处理完成\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SupportController.java",
    "groupTitle": "Support",
    "name": "GetSupportDealwithmessage"
  },
  {
    "type": "GET",
    "url": "/support/getCustomerService",
    "title": "获取客服服务信息列表",
    "group": "Support",
    "version": "0.0.1",
    "description": "<p>获取客服服务信息列表</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "/support/getCustomerService",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SupportController.java",
    "groupTitle": "Support",
    "name": "GetSupportGetcustomerservice"
  },
  {
    "type": "GET",
    "url": "/support/getUsersMessageList",
    "title": "获取客户反馈信息列表",
    "group": "Support",
    "version": "0.0.1",
    "description": "<p>获取客户反馈信息列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "state",
            "description": "<p>查询状态 (0查询全部,1查询未处理的反馈信息,2查询已处理的反馈信息)</p>"
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
            "description": "<p>分页条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/support/getUsersMessageList?state=1&pageNo=1&pageSize=2",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : {\n        mId:\"主键id\",\n        userId:\"用户ID\",\n        mTopic:\"主题\",\n        mEmail:\"邮箱地址\",\n        mContent:\"内容\",\n        firstName:\"名\",\n        lastName:\"性\",\n        orderNumber:\"订单号\",\n        state:\"反馈状态 0未处理  1已处理\",\n        createdTime:\"发布时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SupportController.java",
    "groupTitle": "Support",
    "name": "GetSupportGetusersmessagelist"
  },
  {
    "type": "POST",
    "url": "/support/batchDealWithMessage",
    "title": "批量处理客户反馈信息",
    "group": "Support",
    "version": "0.0.1",
    "description": "<p>批量处理客户反馈信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    mIdList:[数组:主键id]\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"处理完成\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SupportController.java",
    "groupTitle": "Support",
    "name": "PostSupportBatchdealwithmessage"
  },
  {
    "type": "POST",
    "url": "/support/updateCustomerService",
    "title": "修改客服服务信息",
    "group": "Support",
    "version": "0.0.1",
    "description": "<p>修改客服服务信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n    serviceId:\"\",\n    serviceText:\"\"\n}",
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
          "content": "{\n    \"code\" : \"0\",\n    \"msg\"  : \"请求成功\",\n    \"data\" : \"修改成功\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SupportController.java",
    "groupTitle": "Support",
    "name": "PostSupportUpdatecustomerservice"
  },
  {
    "type": "GET",
    "url": "/trend/delTrend",
    "title": "删除趋势信息",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>删除趋势信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "taId",
            "description": "<p>趋势id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/trend/delTrend?taId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "GetTrendDeltrend"
  },
  {
    "type": "GET",
    "url": "/trend/getTrendList",
    "title": "获取趋势列表",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>获取趋势列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
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
          "content": "/trend/getTrendList?pageNo=1&pageSize=1",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        taId:\"趋势广告主键\",\n        title:\"趋势标题\"\n        advertisingImage:\"广告图片\",\n        descript:\"描述语\",\n        location:\"描述语位置 12345\",\n        trends:\"包含4个商品信息\"\n        createdTime:\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "GetTrendGettrendlist"
  },
  {
    "type": "GET",
    "url": "/trend/getTrendProductList",
    "title": "删除趋势信息（多个以逗号隔开）",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>删除趋势信息（多个以逗号隔开）</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/trend/getTrendProductList?taId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "GetTrendGettrendproductlist"
  },
  {
    "type": "POST",
    "url": "/trend/addTrend",
    "title": "添加趋势信息",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>添加趋势信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    title:\"趋势标题\",\n    advertisingImage:\"广告图\",\n    descript:\"描述语\",\n    location:\"样式状态 1-5\"\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendAddtrend"
  },
  {
    "type": "POST",
    "url": "/trend/addTrendProduct",
    "title": "添加趋势商品",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>添加趋势商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "taId",
            "description": "<p>趋势id</p>"
          },
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
          "title": "请求样例：",
          "content": "/trend/addTrendProduct?taId=1&productId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendAddtrendproduct"
  },
  {
    "type": "POST",
    "url": "/trend/batchAddTrendProduct",
    "title": "批量添加趋势商品",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>批量添加趋势商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    taId:趋势id,\n    productIdList:[数组：商品id]\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendBatchaddtrendproduct"
  },
  {
    "type": "POST",
    "url": "/trend/batchDelTrend",
    "title": "批量删除趋势信息",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>批量删除趋势信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    taIdList:[数组：趋势id]\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"删除成功\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendBatchdeltrend"
  },
  {
    "type": "POST",
    "url": "/trend/batchDelTrendProduct",
    "title": "批量删除趋势商品",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>批量删除趋势商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    ttIdList:[数组：趋势商品信息id]\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendBatchdeltrendproduct"
  },
  {
    "type": "POST",
    "url": "/trend/delTrendProduct",
    "title": "删除趋势商品",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>删除趋势商品</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "ttId",
            "description": "<p>趋势商品信息id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/trend/delTrendProduct?productId=1",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendDeltrendproduct"
  },
  {
    "type": "POST",
    "url": "/trend/updTrend",
    "title": "修改趋势信息",
    "group": "Trend",
    "version": "0.0.1",
    "description": "<p>修改趋势信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    taId:\"主键id\",\n    title:\"趋势标题\",\n    advertisingImage:\"广告图\",\n    descript:\"描述语\",\n    location:\"样式状态 1-5\"\n}",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/TrendController.java",
    "groupTitle": "Trend",
    "name": "PostTrendUpdtrend"
  },
  {
    "type": "POST",
    "url": "/user/login",
    "title": "账号密码登录",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>账号密码登录</p>",
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
    "filename": "E:/emall-system/src/main/java/com/tangmo/emall/controller/SysUserController.java",
    "groupTitle": "User",
    "name": "PostUserLogin"
  }
] });
