define({ "api": [
  {
    "type": "GET",
    "url": "/article/getArticleById/{aId}/{userId}",
    "title": "获取文章详情",
    "group": "Article",
    "version": "0.0.1",
    "description": "<p>获取首页部分科室</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "aId",
            "description": "<p>文章id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>当前用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/article/partOfArticle/{aId}/{userId}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        \"article\":{    //文章\n               saTitle:\"文章标题\",\n               saContent:\"文章内容\",\n               visitNumber:\"游览次数\",\n               createTime:\"发布时间\"\n        }\n        \"comments\":{    //评论\n                cId:\"评论id\",\n                userName:\"评论人姓名\",\n                userImgUrl:\"评论人头像\",\n                cContent:\"评论内容\"\n                cAttribute:\"评论属性 0文字 1语音\"\n                cStatus:\"评论类别 0评论 1回复\"\n                replyCount:\"总回复数量\"\n                roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n                createTime:\"评论时间\"\n                commentsList:{   //回复\n                       cId:\"回复id\",\n                       userName:\"评论人姓名\",\n                       userImgUrl:\"评论人头像\",\n                       cContent:\"回复内容\",\n                       cAttribute:\"回复属性 0文字 1语音\",\n                       cStatus:\"回复类别 0评论 1回复\",\n                       roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n                       createTime:\"回复时间\"\n                }\n        }\n        \"recommended\":{     //推荐文章\n               saId:\"文章id\",\n               saTitle:\"文章标题\"\n               visitNumber:\"游览次数\"\n               createTime:\"发布时间\"\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/ArticleController.java",
    "groupTitle": "Article",
    "name": "GetArticleGetarticlebyidAidUserid"
  },
  {
    "type": "GET",
    "url": "/article/partOfArticle",
    "title": "获取首页部分文章",
    "group": "Article",
    "version": "0.0.1",
    "description": "<p>获取首页部分科室</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/article/partOfArticle",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        saId: 文章id，\n        saTitle: 文章标题,\n        visitNumber:\"游览次数\",\n        createTime:\"发布时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/ArticleController.java",
    "groupTitle": "Article",
    "name": "GetArticlePartofarticle"
  },
  {
    "type": "GET",
    "url": "/articleOrVideo/getArticleAll/{start}/{end}",
    "title": "获取所有文章(分页)",
    "group": "Article",
    "version": "0.0.1",
    "description": "<p>获取所有文章(分页)</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/articleOrVideo/getArticleAll/{start}/{end}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        saId: 文章id，\n        saTitle: 文章标题,\n        saImgId: 文章导图,\n        saSource:文章来源,\n        visitNumber:\"游览次数\",\n        createTime:\"发布时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/webController/ArticleOrVideoController.java",
    "groupTitle": "Article",
    "name": "GetArticleorvideoGetarticleallStartEnd"
  },
  {
    "type": "POST",
    "url": "/article/homeSearch",
    "title": "首页搜索",
    "group": "Article",
    "version": "0.0.1",
    "description": "<p>获取首页部分科室</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    condName:\"搜索内容\"\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        doctorList{      //医生集合\n           userId:\"用户id\",\n           name:\"姓名\",\n           ddHospital:\"坐诊医院\",\n           ddPosition:\"医院职务\",\n           integral:\"积分\",\n           ddService:\"擅长疾病\",\n           userImgUrl:\"头像\"\n        },\n        articleList{\n           saId: 文章id，\n           dtId: 科室id，\n           saTitle: 文章标题,\n           visitNumber:\"游览次数\",\n           createTime:\"发布时间\"，\n           saSource:\"文章来源\",\n           saImgId:\"文章导图\"\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/ArticleController.java",
    "groupTitle": "Article",
    "name": "PostArticleHomesearch"
  },
  {
    "type": "GET",
    "url": "/comments/getCommentsALLWById/{id}/{type}",
    "title": "通过视频或者文章id查询所有评论以及评论下3条回复",
    "group": "Comments",
    "version": "0.0.1",
    "description": "<p>通过视频或者文章id查询所有评论以及评论下3条回复</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>文章id或视频id</p>"
          },
          {
            "group": "Parameter",
            "type": "Byte",
            "optional": false,
            "field": "type",
            "description": "<p>0文章 1视频</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/comments/getCommentsALLWById/{cId}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n         cId:\"评论id\",\n         userName:\"评论人姓名\",\n         userImgUrl:\"评论人头像\",\n         cContent:\"评论内容\"\n         cAttribute:\"评论属性 0文字 1语音\"\n         cStatus:\"评论类别 0评论 1回复\"\n         replyCount:\"总回复数量\"\n         roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n         createTime:\"评论时间\"\n         commentsList:{   //回复\n                cId:\"回复id\",\n                userName:\"评论人姓名\",\n                userImgUrl:\"评论人头像\",\n                cContent:\"回复内容\",\n                cAttribute:\"回复属性 0文字 1语音\",\n                cStatus:\"回复类别 0评论 1回复\",\n                roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n                createTime:\"回复时间\"\n         }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/CommentsController.java",
    "groupTitle": "Comments",
    "name": "GetCommentsGetcommentsallwbyidIdType"
  },
  {
    "type": "GET",
    "url": "/comments/getCommentsDById/{cId}",
    "title": "通过评论id查询所有回复",
    "group": "Comments",
    "version": "0.0.1",
    "description": "<p>通过评论id查询所有回复</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cId",
            "description": "<p>评论id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/comments/getCommentsDById/{cId}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n         cId:\"评论id\",\n         userName:\"评论人姓名\",\n         userImgUrl:\"评论人头像\",\n         cContent:\"评论内容\",\n         cAttribute:\"评论属性 0文字 1语音\",\n         cStatus:\"评论类别 0评论 1回复\",\n         replyCount:\"总回复数量\",\n         roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n         createTime:\"评论时间\"\n         commentsList:{   //回复\n                cId:\"回复id\",\n                userName:\"评论人姓名\",\n                userImgUrl:\"评论人头像\",\n                cContent:\"回复内容\",\n                cAttribute:\"回复属性 0文字 1语音\",\n                cStatus:\"回复类别 0评论 1回复\",\n                roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n                createTime:\"回复时间\"\n         }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/CommentsController.java",
    "groupTitle": "Comments",
    "name": "GetCommentsGetcommentsdbyidCid"
  },
  {
    "type": "POST",
    "url": "/comments/addComments",
    "title": "添加评论",
    "group": "Comments",
    "version": "0.0.1",
    "description": "<p>添加评论</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Comments",
            "optional": false,
            "field": "comments",
            "description": "<p>评论对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n     cType:\"评论类型 0文章 1视频\",\n     ArticlesOrVideoId:\"文章id或视频id\",\n     userId:\"评论人id\",\n     cContent:\"评论内容\",\n     cAttribute:\"评论属性 0文字 1语音\",\n     cStatus:\"评论类别 0评论 1回复\",\n     replyId:\"回复id(评论id)\",(可以为空)\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/CommentsController.java",
    "groupTitle": "Comments",
    "name": "PostCommentsAddcomments"
  },
  {
    "type": "GET",
    "url": "/department/getDepartmentAll",
    "title": "获取全部科室",
    "group": "Department",
    "version": "0.0.1",
    "description": "<p>获取全部科室</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/department/getDepartmentAll",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        dtId: 科室id，\n        dtName: 科室名称,\n        dtImgId:\"科室图片\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DepartmentController.java",
    "groupTitle": "Department",
    "name": "GetDepartmentGetdepartmentall"
  },
  {
    "type": "GET",
    "url": "/department/getDepartmentDetails/{dtId}",
    "title": "科室详情",
    "group": "Department",
    "version": "0.0.1",
    "description": "<p>科室详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dtId",
            "description": "<p>科室id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/department/getDepartmentAll/",
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
            "description": "<p>0:请求成功; 10005:参数有误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n          \"video\":{\n               dvId:\"视频主键id\",\n               dvImgId:\"视频图片id\",\n               dvTitle:\"视频标题\",\n               dvIntegral:\"积分\"\n          },\n          \"doctor\":{\n               ddId:\"医生id\",\n               name:\"医生姓名\",\n               ddField:\"医生科室\"\n               ddPosition:\"医院职务\",\n               userImgUrl:\"医生头像\"\n          },\n          \"article\":{\n               saId:\"文章id\",\n               saTitle:\"文章标题\",\n               visitNumber:\"游览次数\",\n               createTime:\"发布时间\"\n          }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DepartmentController.java",
    "groupTitle": "Department",
    "name": "GetDepartmentGetdepartmentdetailsDtid"
  },
  {
    "type": "GET",
    "url": "/department/partOfDepartment",
    "title": "获取首页部分科室",
    "group": "Department",
    "version": "0.0.1",
    "description": "<p>获取首页部分科室</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/department/partOfDepartment",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        dtId: 科室id，\n        dtName: 科室名称,\n        dtImgId:\"科室图片\",\n        sortingId:\"排序\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DepartmentController.java",
    "groupTitle": "Department",
    "name": "GetDepartmentPartofdepartment"
  },
  {
    "type": "GET",
    "url": "/doctor/finePhysician",
    "title": "获取首页精品医师",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>获取首页轮播视频</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/doctor/finePhysician",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        userId: 医师主键id,\n        name:\"医师姓名\",\n        ddPosition:\"医院职务\",\n        ddField:\"科室id\",\n        userImgUrl:\"头像路径\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorFinephysician"
  },
  {
    "type": "GET",
    "url": "/doctor/getDepartmentAllDoctorTop3",
    "title": "获取所有科室排名前三医师",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>获取所有科室排名前三医师</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/doctor/getDepartmentAllDoctorTop3",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       [dtId:\"科室id\",dtName:\"科室名称\"]:{\n           userId: 医师主键id,\n           name:\"医师姓名\",\n           ddPosition:\"医院职务\",\n           ddService:\"擅长领域\",\n           integral:\"积分\",\n           userImgUrl:\"头像路径\"\n       }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorGetdepartmentalldoctortop3"
  },
  {
    "type": "GET",
    "url": "/doctor/getDepartmentDoctorAllById/{dtId}/{start}/{end}",
    "title": "获取某个科室下所有医师(分页)",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>获取某个科室下所有医师(分页)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dtId",
            "description": "<p>科室id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "start",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "end",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/doctor/getDepartmentDoctorAllById/{dtId}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n           userId: 医师主键id,\n           name:\"医师姓名\",\n           ddPosition:\"医院职务\",\n           integral:\"积分\",\n           userImgUrl:\"头像路径\"\n\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorGetdepartmentdoctorallbyidDtidStartEnd"
  },
  {
    "type": "GET",
    "url": "/doctor/getDoctorDetailsById/{userId}",
    "title": "医生详情",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>医生详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/doctor/getDoctorDetailsById/{userId}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        \"doctor\":{\n            name:\"医生姓名\",\n            userImgUrl:\"医生头像\",\n            integral:\"医生积分\",\n            ddHospital:\"坐诊医院\",\n            ddField:\"就诊科室\",\n            ddPosition:\"职位\",\n            ddService:\"擅长疾病\",\n            ddExperience:\"主要成就\",\n            ddCertificate:\"证书图片id\",\n            ddCertificateName:\"证书名称\",\n            ddPhone:\"联系电话\"\n        },\n        \"video\":{\n            dvId:\"视频id\",\n            dvImgId:\"视频展示图片id\",\n            dvTitle:\"视频标题\",\n            dvIntegral:\"视频积分\",\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorGetdoctordetailsbyidUserid"
  },
  {
    "type": "GET",
    "url": "/doctor/getDoctorInformation/{userId}",
    "title": "获取医生资料",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>获取医生我的资料</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/doctor/getDoctorInformation/{userId}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        ddHospital:\"坐诊医院\",\n        ddField:\"就诊科室\",\n        ddPosition:\"职位\",\n        ddService:\"擅长疾病\",\n        ddExperience:\"主要成就\",\n        ddCertificate:\"证书图片id\",\n        ddCertificateName:\"证书名称\",\n        ddPhone:\"联系电话\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorGetdoctorinformationUserid"
  },
  {
    "type": "GET",
    "url": "/doctor/getUserApplicationStatus/{userId}/{type}",
    "title": "获取医生申请状态",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>申请医师</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Byte",
            "optional": false,
            "field": "type",
            "description": "<p>0申请医生   1修改资料</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/doctor/getUserApplicationStatus/{userId}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{0:\"未申请过\";1:\"正在审核中\";2审核已通过;3审核未通过}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorGetuserapplicationstatusUseridType"
  },
  {
    "type": "GET",
    "url": "/doctor/updDoctorInfo",
    "title": "修改医生资料(重新提交审核)",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>获取医生我的资料</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",\n    daHospital:\"坐诊医院\",\n    dtId:\"就诊科室id\",\n    daPosition:\"职位\",\n    daDisease:\"擅长疾病\",\n    daCertificateName:\"证书名称\",\n    daCertificate:\"证书图片id\",\n    phone:\"联系电话\",\n    daAchievement:\"主要成就\"\n}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
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
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "GetDoctorUpddoctorinfo"
  },
  {
    "type": "POST",
    "url": "/doctor/applyForDoctor",
    "title": "申请医师",
    "group": "Doctor",
    "version": "0.0.1",
    "description": "<p>申请医师</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "DoctorApplied",
            "optional": false,
            "field": "doctorApplied",
            "description": "<p>医师对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    userId:\"用户id\",\n    daHospital:\"坐诊医院\",\n    dtId:\"就诊科室id\",\n    daPosition:\"职位\",\n    daDisease:\"擅长疾病\",\n    daCertificateName:\"证书名称\",\n    daCertificate:\"证书图片id\",\n    phone:\"联系电话\",\n    daAchievement:\"主要成就\"\n}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
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
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/DoctorController.java",
    "groupTitle": "Doctor",
    "name": "PostDoctorApplyfordoctor"
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
    "filename": "E:/yiliao/src/main/resources/static/main.js",
    "group": "E__yiliao_src_main_resources_static_main_js",
    "groupTitle": "E__yiliao_src_main_resources_static_main_js",
    "name": ""
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
    "filename": "E:/yiliao/target/classes/static/main.js",
    "group": "E__yiliao_target_classes_static_main_js",
    "groupTitle": "E__yiliao_target_classes_static_main_js",
    "name": ""
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
    "filename": "E:/yiliao/target/yiliao-0.0.1-SNAPSHOT/WEB-INF/classes/static/main.js",
    "group": "E__yiliao_target_yiliao_0_0_1_SNAPSHOT_WEB_INF_classes_static_main_js",
    "groupTitle": "E__yiliao_target_yiliao_0_0_1_SNAPSHOT_WEB_INF_classes_static_main_js",
    "name": ""
  },
  {
    "type": "GET",
    "url": "/file/video/{rfId}",
    "title": "播放视频",
    "group": "File",
    "version": "0.0.1",
    "description": "<p>查看文件, 需要传输文件id, 返回ResponseEntity&lt;byte[]&gt;</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "rfId",
            "description": "<p>文件id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/file/video/d5540ff2db804a8daf1050a06679155f",
          "type": "uri"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/FileController.java",
    "groupTitle": "File",
    "name": "GetFileVideoRfid"
  },
  {
    "type": "POST",
    "url": "/file/read",
    "title": "附件查看",
    "group": "File",
    "version": "0.0.1",
    "description": "<p>查看文件, 需要传输文件id, 返回ResponseEntity&lt;byte[]&gt;</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "rfId",
            "description": "<p>文件id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "/file/read/d5540ff2db804a8daf1050a06679155f",
          "type": "uri"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/FileController.java",
    "groupTitle": "File",
    "name": "PostFileRead"
  },
  {
    "type": "POST",
    "url": "/file/upload/{userId}",
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
            "description": "<p>请求成功; -1请求失败</p>"
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
          "content": "{\"code\":\"0\"}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/FileController.java",
    "groupTitle": "File",
    "name": "PostFileUploadUserid"
  },
  {
    "type": "POST",
    "url": "/file/video/upload/{userId}",
    "title": "上传视频",
    "group": "File",
    "version": "0.0.1",
    "description": "<p>上传视频</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n file:\"图片文件\",\n}",
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
            "description": "<p>0:请求成功; -1:请求失败</p>"
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
          "content": "{\"code\":\"0\"}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/FileController.java",
    "groupTitle": "File",
    "name": "PostFileVideoUploadUserid"
  },
  {
    "type": "GET",
    "url": "/message/getMessageAllByType/{type}/{userId}/{start}/{end}",
    "title": "获取消息集合(分页)",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>获取消息读取状态</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Byte",
            "optional": false,
            "field": "type",
            "description": "<p>0系统消息  1回复消息   2打赏消息</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/message/getMessageState/{type}/{userId}/{start}/{end}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       \"title\":\"标题\",\n       \"miContent\":\"内容\",\n       \"commId\":\"评论id\",\n       \"createTime\":\"创建时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "GetMessageGetmessageallbytypeTypeUseridStartEnd"
  },
  {
    "type": "GET",
    "url": "/message/getMessageState/{roleId}/{userId}",
    "title": "获取消息读取状态",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>获取消息读取状态</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "roleId",
            "description": "<p>身份标识</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/message/getMessageState/{roleId}/{userId}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       \"systemMessage\":\"系统消息读取状态\",      0未读 1已读\n       \"replyMessage\":\"回复消息读取状态\",       0未读 1已读\n       \"exceptionalMessage\":\"打赏消息读取状态\"  0未读 1已读\n       \"remindMessage\":\"提醒消息读取状态\"       0未读 1已读\n       \"advisoryMessages\":\"咨询消息读取状态\"    0未读 1已读\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "GetMessageGetmessagestateRoleidUserid"
  },
  {
    "type": "GET",
    "url": "/message/getMessageTitle/{roleId}",
    "title": "获取消息标题",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>获取消息标题</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "roleId",
            "description": "<p>身份标识</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/message/getMessageTitle/{roleId}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       \"system\":\"系统消息标题\",\n       \"reply\":\"回复消息标题\",\n       \"exceptional\":\"打赏消息标题\",\n       \"remind\":\"提醒消息标题\",\n       \"advisory\":\"咨询消息标题\"。\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "GetMessageGetmessagetitleRoleid"
  },
  {
    "type": "POST",
    "url": "/message/addUserDia",
    "title": "发表对话信息",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>发表对话信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n  originatorId:\"用户id\",\n  peopleId:\"医生id\",\n  dType:\"类型： 0文字 1语音\",\n  dContent:\"内容\",\n  createUserId:\"发表人id\",\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "PostMessageAdduserdia"
  },
  {
    "type": "POST",
    "url": "/message/EnterTheDialogue",
    "title": "用户进入对话调用接口",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>用户进入对话调用接口</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    originatorId:\"用户id\",\n    peopleId:\"医生id\",\n    type:\"当前用户身份。0用户，1医生\"\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "PostMessageEnterthedialogue"
  },
  {
    "type": "POST",
    "url": "/message/getConsultingAll",
    "title": "获取咨询消息列表",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>获取咨询消息列表</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n  originatorId:\"当前用户id\",\n  type:\"当前用户身份。0用户，1医生\",\n  start:\"页数\",\n  end:\"条数\"\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        peopleId:\"医生或用户id\"\n        dContent:\"内容\",\n        dType:\"类型：0文字 1语音\",\n        userImgUrl:\"头像\",\n        name:\"姓名\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "PostMessageGetconsultingall"
  },
  {
    "type": "POST",
    "url": "/message/getUserDia",
    "title": "获取对话信息",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>获取对话信息</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n  originatorId:\"用户id\",\n  peopleId:\"医生id\",\n  start:\"页数\",\n  end:\"条数\"\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n       dlId:\"主键\",\n       originatorId:\"用户id\",\n       peopleId:\"医生id\"\n       dType:\"类型： 0文字 1语音\",\n       dContent:\"内容\",\n       createTime:\"发表时间\",\n       createUserId:\"发表人id\",\n       userImgUrl:\"发表人头像\",\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "PostMessageGetuserdia"
  },
  {
    "type": "POST",
    "url": "/message/IntegralExceptional",
    "title": "积分打赏",
    "group": "Message",
    "version": "0.0.1",
    "description": "<p>获取消息读取状态</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "{\n    \"miCategory\":\"0问答  1视频\",\n    \"miId\":\"医生id或者视频id\",\n    \"title\":\"文章标题或者视频标题\",\n    \"userId\":\"当前用户id\",\n    \"count\":\"打赏数量\"\n}",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/MessageController.java",
    "groupTitle": "Message",
    "name": "PostMessageIntegralexceptional"
  },
  {
    "type": "GET",
    "url": "/user/clearUserFootprint/{userId}",
    "title": "清空用户足迹",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户足迹</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/clearUserFootprint/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
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
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserClearuserfootprintUserid"
  },
  {
    "type": "GET",
    "url": "/user/getBasicInformationById/{userId}",
    "title": "获取用户基本信息",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户基本信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getBasicInformationById/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n         userId: 用户id,\n         name:\"性名\",\n         roleId:\"角色\", DOCTOR:\"医生\"  MEMBER:\"用户\"\n         userImgUrl:\"头像id\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetbasicinformationbyidUserid"
  },
  {
    "type": "GET",
    "url": "/user/getPersonalInformation/{userId}",
    "title": "获取用户个人信息",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户个人信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getPersonalInformation/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n         userId: 用户id,\n         name:\"性名\",\n         userName:\"昵称\",\n         userImgUrl:\"头像id\",\n         userGender:\"性别\",  1男2女\n         userAge:\"年龄\",\n         city:\"城市\",\n         county:\"区县\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetpersonalinformationUserid"
  },
  {
    "type": "GET",
    "url": "/user/getUserArticleFootprint/{userId}/{start}/{end}",
    "title": "获取用户文章足迹(分页)",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户足迹</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "start",
            "description": "<p>页数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getUserArticleFootprint/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        saId: 文章id，\n        saTitle: 文章标题,\n        visitNumber:\"游览次数\",\n        createTime:\"游览时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetuserarticlefootprintUseridStartEnd"
  },
  {
    "type": "GET",
    "url": "/user/getUserDoctorVideoById/{userId}/{start}/{end}我的视频(分页)",
    "title": "",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取某个科室下所有医师(分页)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>科室id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "start",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "end",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getUserDoctorVideoById/{dtId}",
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
            "description": "<p>0:请求成功;  10005:参数有误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n           dvId: 视频主键id,\n           dvImgId:\"视频展示图片\",\n           dvTitle:\"视频标题\",\n           dvIntegral:\"所获积分\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetuserdoctorvideobyidUseridStartEnd"
  },
  {
    "type": "GET",
    "url": "/user/getUserIntegral/{userId}",
    "title": "获取用户积分余额",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户积分余额</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getUserIntegral/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
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
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetuserintegralUserid"
  },
  {
    "type": "GET",
    "url": "/user/getUserIntegralDetail/{userId}",
    "title": "获取用户积分明细",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户积分明细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getUserIntegralDetail/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        levelName:\"明细名称\",\n        superiorId:\"明细类型\", 注：ADD_RECORDS=\"增加\"   RECORDS_OF_CONSUMPTION=\"消费\"\n        syBean:\"数量\",\n        createTime:\"时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetuserintegraldetailUserid"
  },
  {
    "type": "GET",
    "url": "/user/getUserVideoFootprint/{userId}/{start}/{end}",
    "title": "获取用户视频足迹(分页)",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取用户足迹</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "start",
            "description": "<p>页数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/getUserVideoFootprint/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        dvId: 视频id，\n        dvImgId: 视频展示图片,\n        dvTitle:\"视频标题\",\n        visitNumber:\"游览次数\",\n        createTime:\"游览时间\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserGetuservideofootprintUseridStartEnd"
  },
  {
    "type": "GET",
    "url": "/user/mobile/auth/{mobile}",
    "title": "获取手机验证码",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>获取手机验证码</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/user/mobile/auth/18710829356",
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
            "description": "<p>0:请求成功; 10005：请求参数错误; 10006:请求次数超限; 10007:当前账号已禁止请求验证码;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\"验证码\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "GetUserMobileAuthMobile"
  },
  {
    "type": "POST",
    "url": "/user/backPwd",
    "title": "找回密码",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>用户登录</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n   password:\"加密后的新密码\",\n   userPhone:\"手机号\",\n   userCons: \"验证码\"\n}",
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
            "description": "<p>0:请求成功; 10003: 该手机号未注册; 10004:验证码错误; 10005:请求参数错误; 10008:当前账号已封停;</p>"
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
          "content": "{\"code\":\"0\"\n \"msg\": \"请求成功\",\n \"data\":\"\"}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "PostUserBackpwd"
  },
  {
    "type": "POST",
    "url": "/user/pwdLogin",
    "title": "用户密码登录",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>用户登录</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n   password:\"加密后的新密码\",\n   userPhone:\"18710889234\",\n   userCard:\"hanakjsdnkjj12\"\n}",
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
            "description": "<p>0:请求成功; 10002:账号密码错误; 10003：该手机号未注册 10005:请求参数错误;10008:当前账号已封停;</p>"
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
          "content": "{\"code\":\"0\",\n \"msg\": \"请求成功\",\n \"data\":{\n     userId:\"用户id\",\n     token:\"登录验证\",\n }}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "PostUserPwdlogin"
  },
  {
    "type": "POST",
    "url": "/user/register",
    "title": "增加用户",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>增加用户</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n   userCard: \"登录名\",\n   userPhone:\"手机号\",\n   userCons: \"验证码\",\n   password: \"加密后的密码\"\n   state:\"0app注册  1邀请注册\"\n   updateUserId:\"邀请人id\"\n}",
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
            "description": "<p>0:请求成功; 10001:该手机号已注册; 10004:验证码错误; 10005:请求参数错误; 10010:服务器发生未知错误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n     \"code\":\"0\",\n     \"msg\": \"请求成功\",\n     \"data\":\"\"\n }",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "PostUserRegister"
  },
  {
    "type": "POST",
    "url": "/user/updPwd",
    "title": "修改密码",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>用户登录</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n   userId:\"用户id\",\n   password:\"加密后的旧密码\",\n   userCard:\"加密后的新密码\"\n}",
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
            "description": "<p>0:请求成功; -1:&quot;原密码不正确&quot;, 10005:请求参数错误;</p>"
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
          "content": "{\n \"code\":\"0\"\n \"msg\": \"请求成功\",\n \"data\":\"\"}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpdpwd"
  },
  {
    "type": "POST",
    "url": "/user/updUser",
    "title": "修改用户个人信息 (改什么传什么)",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>修改用户个人信息 (改什么传什么)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例:",
          "content": "{\n   userId:\"用户id\",\n   name:\"姓名\",\n   userName:\"昵称\",\n   userImgUrl:\"头像id\",\n   userGender:\"性别\",  1男 2女\n   userAge:\"年龄\",\n   city:\"城市\",\n   county:\"区县\"\n}",
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
            "description": "<p>0:请求成功; 10005:请求参数错误;</p>"
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
          "content": "{\n \"code\":\"0\"\n \"msg\": \"请求成功\",\n \"data\":\"\"}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/UserController.java",
    "groupTitle": "User",
    "name": "PostUserUpduser"
  },
  {
    "type": "GET",
    "url": "/video/getDepartmentAllVideoTop4",
    "title": "获取所有科室下排名前四视频",
    "group": "Video",
    "version": "0.0.1",
    "description": "<p>获取所有科室下排名前四视频</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/video/getDepartmentAllVideoTop4",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        [dtId:\"科室id\",dtName:\"科室名称\"]:{\n               dvId: 视频主键id,\n               dvImgId:\"视频展示图片id\",\n               dvTitle:\"视频标题\",\n               dvIntegral:\"积分\"\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/VideoController.java",
    "groupTitle": "Video",
    "name": "GetVideoGetdepartmentallvideotop4"
  },
  {
    "type": "GET",
    "url": "/video/getDepartmentByIdVideoAll/{dtId}/{start}/{end}",
    "title": "根据科室id获取科室下所有视频(分页)",
    "group": "Video",
    "version": "0.0.1",
    "description": "<p>根据科室id获取科室下所有视频(分页)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dtId",
            "description": "<p>科室id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "start",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "end",
            "description": "<p>条数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/video/getDepartmentByIdVideoAll/{dtId}/{start}/{end}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n         dvId: 视频主键id,\n         dvImgId:\"视频展示图片id\",\n         dvTitle:\"视频标题\",\n         dvIntegral:\"积分\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/VideoController.java",
    "groupTitle": "Video",
    "name": "GetVideoGetdepartmentbyidvideoallDtidStartEnd"
  },
  {
    "type": "GET",
    "url": "/video/getVideoDDById/{dvId}/{userId}",
    "title": "获取视频详情",
    "group": "Video",
    "version": "0.0.1",
    "description": "<p>获取视频详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dvId",
            "description": "<p>视频id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样例：",
          "content": "/video/getVideoDDById/{dvId}/{userId}",
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
            "description": "<p>0:请求成功;  10005:&quot;参数有误&quot;</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回样例:",
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n         \"video\":{\n               dvId: 视频主键id,\n               userId:\"用户id\"\n               userImgId:\"头像id\"\n               name:\"姓名\",\n               ddHospital:\"医院\",\n               ddPosition:\"职位\",\n               ddField:\"科室\",\n               dvVideo:\"视频id\",\n               dvTitle:\"视频标题\",\n               dvContent:\"视频介绍\",\n               dvIntegral:\"积分\",\n               visitNumber:\"游览次数\"\n               createTime:\"发布时间\"\n         }\n         \"comments\":{    //评论\n                cId:\"评论id\",\n                userName:\"评论人姓名\",\n                userImgUrl:\"评论人头像\",\n                cContent:\"评论内容\"\n                cAttribute:\"评论属性 0文字 1语音\"\n                cStatus:\"评论类别 0评论 1回复\"\n                replyCount:\"总回复数量\"\n                roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n                createTime:\"评论时间\"\n                commentsList:{   //回复\n                       cId:\"回复id\",\n                       userName:\"评论人姓名\",\n                       userImgUrl:\"评论人头像\",\n                       cContent:\"回复内容\",\n                       cAttribute:\"回复属性 0文字 1语音\",\n                       cStatus:\"回复类别 0评论 1回复\",\n                       roleId:\"评论人身份  DOCTOR:\"医生\"，MEMBER:\"普通用户\" \",\n                       createTime:\"回复时间\"\n                }\n        }\n        \"recommended\":{\n              dvId: 视频主键id,\n              dvTitle:\"视频标题\",\n              dvIntegral:\"积分\",\n              dvImgId:\"视频展示图片id\"\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/VideoController.java",
    "groupTitle": "Video",
    "name": "GetVideoGetvideoddbyidDvidUserid"
  },
  {
    "type": "GET",
    "url": "/video/shufflingVideo",
    "title": "获取首页轮播视频",
    "group": "Video",
    "version": "0.0.1",
    "description": "<p>获取首页轮播视频</p>",
    "parameter": {
      "examples": [
        {
          "title": "请求样例：",
          "content": "/video/shufflingVideo",
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
          "content": "{\n    \"code\":\"0\",\n    \"msg\":\"请求成功\",\n    \"data\":{\n        dvId: 视频主键id，\n        dvImgId: 视频图片路径\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/yiliao/src/main/java/com/tangmo/yiliao/controller/appController/VideoController.java",
    "groupTitle": "Video",
    "name": "GetVideoShufflingvideo"
  }
] });
