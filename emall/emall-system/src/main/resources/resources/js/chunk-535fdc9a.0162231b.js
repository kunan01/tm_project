(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-535fdc9a"],{"2de9":function(e,t,s){"use strict";var a="http://114.115.211.170:8088/";t["a"]={IP:a,IMGURL:a+"system/file/showPicture?imageId=",LOGIN:a+"system/user/login",SHOP:a+"system/product/getProductList",SHOPDETAIL:a+"system/product/getProductListById",DELSHOP:a+"system/product/delProduct",ALLDELSHOP:a+"system/product/delProduct",DISCOUNT:a+"system/product/setProductDiscounts",DELSHOPLABAL:a+"system/product/delProductParam",DELSHOPPROP:a+"system/product/delProductProp",ADDSHOP:a+"system/product/addProduct",ADDSHOPLABAL:a+"system/product/addProductParam",ADDSHOPPROP:a+"system/product/addProductProp",EDOTSHOPPROP:a+"system/product/updProductProp",SHELVESSHOP:a+"system/product/shelvesProduct",THESHELVESSHOP:a+"system/product/theShelvesProduct",NEWSHOP:a+"system/product/setOrCancelNewProduct",PROPLIST:a+"system/shopProp/queryPropList",UPSHOP:a+"system/product/updProduct",SHUFFLING:a+"system/slides/getSlidesList",FREEZESHUFFLING:a+"system/slides/updSlides",DELSHUFFLING:a+"system/slides/delSlides",DELALLSHUFFLING:a+"system/slides/batchDeleteSlides",ADDSHUFFLING:a+"system/slides/addSlides",ALLCATEGORYLIST:a+"system/category/queryCateGoryList",CATEGORYLIST:a+"system/category/getCateGoryList",TWOCATEGORYLIST:a+"system/category/getCateGoryListByTwoLevel",ADDCATEGORY:a+"system/category/addCategory",UPCATEGORY:a+"system/category/updCategory",DELCATEGORY:a+"system/category/delCateGory",DELALLCATEGORY:a+"system/category/batchDelCateGory",ALLLABEL:a+"system/param/queryShopParamList",LABEL:a+"system/param/getShopParamKeyList",TLABEL:a+"system/param/getShopParamValueList",ADDLABEL:a+"system/param/addShopParamType",ADDTLABEL:a+"system/param/addShopParamValue",DELLABEL:a+"system/param/delShopParamType",DELTLABEL:a+"system/param/delShopParamValue",EDITLABEL:a+"system/param/updShopParamType",EDTITLABEL:a+"system/param/updShopParamValue",DELALLTABEL:a+"system/param/batchDelShopParamType",DELALLTTABEL:a+"system/param/batchDelShopParamValue",SPECKEY:a+"system/shopProp/getListKey",SPECVALUE:a+"system/shopProp/getListValue",ADDSPECKEY:a+"system/shopProp/addPropKey",ADDSPECVALUE:a+"system/shopProp/addPropValue",UPSPECKEY:a+"system/shopProp/updPropKey",UPSPECVALUE:a+"system/shopProp/updPropValue",DELSPECKEY:a+"system/shopProp/delPropKey",DELSPECVALUE:a+"system/shopProp/delPropValue",DELALLSPECKEY:a+"system/shopProp/batchDelPropKey",DELALLSPECVALUE:a+"system/shopProp/batchDelPropValue",DISTRIBUTION:a+"system/distribution/getStoreDistribution",ADDDISTRIBUTION:a+"system/distribution/addDistribution",UPDISTRIBUTION:a+"system/distribution/updateDistribution",DELDISTRIBUTION:a+"system/distribution/delDistribution",DELALLDISTRIBUTION:a+"system/distribution/batchDelDistribution",SERVICE:a+"system/support/getCustomerService",UPSERVICE:a+"system/support/updateCustomerService",HELP:a+"system/help/getSysHelpList",ADDHELP:a+"system/help/addHelp",UPHELP:a+"system/help/updateSysHelpList",DELHELP:a+"system/help/deleteSysHelpList",DELALLHELP:a+"system/help/batchDeleteSysHelpList",FEEDBACK:a+"system/support/getUsersMessageList",DEALWITHFEEDBACK:a+"system/support/dealWithMessage",DEALWITHALLFEEDBACK:a+"system/support/batchDealWithMessage",TREND:a+"system/trend/getTrendList",TRENDSHOP:a+"system/trend/getTrendProductList",ADDTRENDSHOP:a+"system/trend/addTrendProduct",ADDTREND:a+"system/trend/addTrend",ADDALLTRENDSHOP:a+"system/trend/batchAddTrendProduct",DELALLTREND:a+"system/trend/batchDelTrend",DELALLTRENDPRODUCT:a+"system/trend/batchDelTrendProduct",DELTREND:a+"system/trend/delTrendProduct",DELTRENDSHOP:a+"system/trend/delTrendProduct",UPTREND:a+"system/trend/updTrend",TRENDSHOPS:a+"system/product/getTrendProductList",POP:a+"system/advertising/getAdvertisingList",UPPOP:a+"system/advertising/updateAdvertising",SEARCHPOPSHOP:a+"system/product/getAdvertisingProductList",POPSHOP:a+"system/advertising/getAdvertisingProductList",POPSHOPS:a+"system/product/getAdvertisingProductList",ADDALLPOPSHOP:a+"system/advertising/batchAddAdvertisingProduct",DELPOPSHOP:a+"system/advertising/delAdvertisingProduct",DELALLPOPSHOP:a+"system/advertising/batchDelAdvertisingProduct"}},"37ae":function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"table"},[s("div",{staticClass:"crumbs"},[s("el-breadcrumb",{attrs:{separator:"/"}},[s("el-breadcrumb-item",[s("i",{staticClass:"el-icon-lx-cascades"}),e._v(" 商品二级标签管理")])],1)],1),s("div",{staticClass:"container"},[s("el-select",{attrs:{placeholder:"请选择"},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}},e._l(e.list,function(e){return s("el-option",{key:e.typeId,attrs:{label:e.typeName,value:e.typeId}})}),1),s("p",{staticClass:"login-tips"},[e._v("Tips : 颜色值的填写应为16进制颜色和颜色名的拼接！示例：#000000|Black。")]),s("div",{staticClass:"handle-box"},[s("el-button",{staticClass:"handle-del mr10",attrs:{type:"primary",icon:"delete"},on:{click:e.addSlide}},[e._v("增加")]),s("el-button",{staticClass:"handle-del mr10",attrs:{type:"primary",icon:"delete"},on:{click:e.delAll}},[e._v("批量删除")])],1),s("el-table",{ref:"multipleTable",staticClass:"table",attrs:{data:e.tableData,border:""},on:{"selection-change":e.handleSelectionChange}},[s("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),s("el-table-column",{attrs:{prop:"valueId",label:"标签ID",sortable:"",width:"200",align:"center"}}),s("el-table-column",{attrs:{prop:"paramValue",label:"标签描述",sortable:"",width:"300",align:"center"}}),s("el-table-column",{attrs:{label:"标签图片",width:"180",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.paramImage?[s("img",{staticClass:"goods-img",attrs:{src:e.IMGURL+t.row.paramImage},on:{click:function(s){return e.previewImg(e.IMGURL+t.row.paramImage)}}})]:void 0}}],null,!0)}),s("el-table-column",{attrs:{label:"操作",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-button",{attrs:{type:"text",icon:"el-icon-edit"},on:{click:function(s){return e.handleEdit(t.$index,t.row)}}},[e._v("修改")]),s("el-button",{staticClass:"red",attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(s){return e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1),s("div",{staticClass:"pagination"},[s("el-pagination",{attrs:{background:"","page-size":e.pageSize,layout:"prev, pager, next",total:e.totals},on:{"current-change":e.handleCurrentChange}})],1)],1),s("el-dialog",{attrs:{title:"编辑",visible:e.editVisible,width:"30%"},on:{"update:visible":function(t){e.editVisible=t}}},[s("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px"}},[s("el-form-item",{attrs:{label:"商品标签名称"}},[s("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),s("el-form-item",{attrs:{label:"标签图片上传"}},[s("el-upload",{staticClass:"upload-demo",attrs:{action:e.IP+"system/file/upload",multiple:!1,"on-success":e.handleSuccess,"on-error":e.handleError,"on-remove":e.handleRemove,"file-list":e.imgList,"list-type":"picture"}},[s("el-button",{attrs:{size:"small",type:"primary",disabled:e.imgList.length>=1}},[e._v("点击上传")]),s("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过500kb")])],1)],1)],1),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.editVisible=!1}}},[e._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.saveEdit}},[e._v("确 定")])],1)],1),s("el-dialog",{attrs:{title:"提示",visible:e.delVisible,width:"300px",center:""},on:{"update:visible":function(t){e.delVisible=t}}},[s("div",{staticClass:"del-dialog-cnt"},[e._v("删除不可恢复，是否确定删除？")]),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.delVisible=!1}}},[e._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.deleteRow}},[e._v("确 定")])],1)]),s("el-dialog",{attrs:{title:"提示",visible:e.delVisibles,width:"300px",center:""},on:{"update:visible":function(t){e.delVisibles=t}}},[s("div",{staticClass:"del-dialog-cnt"},[e._v("确认批量删除，删除不可恢复，是否确定删除？")]),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.delVisibles=!1}}},[e._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.deleteRows}},[e._v("确 定")])],1)]),s("el-dialog",{attrs:{title:"预览",visible:e.previewDialogVisible,width:"50%",center:""},on:{"update:visible":function(t){e.previewDialogVisible=t}}},[s("img",{staticClass:"preview-img",attrs:{src:e.previewImgUrl}})])],1)},i=[],o=(s("3a23"),s("612f"),s("2de9")),r={name:"label",data:function(){return{IMGURL:o["a"].IMGURL,IP:o["a"].IP,value:"",list:[],totals:10,url:"./vuetable.json",tableData:[],cur_page:1,previewDialogVisible:!1,previewImgUrl:"",multipleSelection:[],is_search:!1,editVisible:!1,delVisible:!1,delVisibles:!1,form:{name:""},idx:-1,categoryId:"",pageSize:10,isModify:!1,imgList:[]}},created:function(){this.getLabel()},watch:{value:function(e,t){this.getData(e)}},computed:{},methods:{addSlide:function(){this.form={},this.imgList=[],this.isModify=!1,this.editVisible=!0},handleCurrentChange:function(e){this.cur_page=e,this.getData(this.value)},getLabel:function(){var e=this,t={shopId:1,state:1};this.$axios.get(o["a"].LABEL,{params:t,headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code&&(e.$message.error("登录超时，请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)),0==t.data.code&&(e.list=t.data.data.list,e.value=e.list[0].typeId)})},getData:function(e){var t=this,s={typeId:e,pageSize:this.pageSize,pageNo:this.cur_page};this.$axios.get(o["a"].TLABEL,{params:s,headers:{token:sessionStorage.getItem("token")}}).then(function(e){"-30"==e.data.code&&(t.$message.error("登录超时，请重新登录！"),setTimeout(function(){t.$router.push("/login")},3e3)),0==e.data.code&&(t.totals=e.data.data.total,t.tableData=e.data.data.list)})},previewImg:function(e){""!==e&&(this.previewDialogVisible=!0,this.previewImgUrl=e)},handleSuccess:function(e,t,s){console.log(e,t,s),this.$message.success("图片上传成功"),this.form.url=e.data,this.imgList[0]={url:t.url}},handleError:function(e,t,s){this.$message.error("图片上传失败")},handleRemove:function(e){this.imgList.forEach(function(t,s,a){t.uid===e.uid&&a.splice(s,1)}),this.form.url=""},handleDelete:function(e,t){this.delVisible=!0,this.categoryId=t.valueId},delAll:function(){this.multipleSelection.length&&(this.delVisibles=!0)},handleSelectionChange:function(e){var t=this;this.multipleSelection=[],e.map(function(e){return t.multipleSelection.push(e.valueId)})},deleteRow:function(){var e=this,t={valueId:this.categoryId};this.$axios.get(o["a"].DELTLABEL,{params:t,headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code&&(e.$message.success("登录超时！请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)),0==t.data.code&&e.getData(e.value)}),this.delVisible=!1},deleteRows:function(){var e=this,t={valueIdList:this.multipleSelection};console.log(t),this.$axios.post(o["a"].DELALLTTABEL,t,{headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code?(e.$message.success("登录超时！请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)):0==t.data.code?e.getData(e.value):e.$message.error("删除失败")}),this.delVisibles=!1},handleEdit:function(e,t){this.idx=e;var s=this.tableData[e];this.form={name:s.paramValue,id:s.valueId,url:s.paramImage},this.imgList=[].concat({url:this.IMGURL+s.paramImage}),this.isModify=!0,this.editVisible=!0},saveEdit:function(){var e=this;if(this.isModify){var t={valueId:this.form.id,paramValue:this.form.name,typeId:this.value,paramImage:this.form.url};this.$axios.post(o["a"].EDTITLABEL,t,{headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code?(e.$message.error("登录超时，请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)):0==t.data.code&&(e.$message.success("修改成功！"),e.getData(e.value))}),this.editVisible=!1}else{console.log(this.form.url);var s={paramValue:this.form.name,typeId:this.value,paramImage:this.form.url};this.$axios.post(o["a"].ADDTLABEL,s,{headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code?(e.$message.error("登录超时，请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)):0==t.data.code&&(e.$message.success("新增成功！"),e.getData(e.value))}),this.editVisible=!1}}}},l=r,d=(s("894a"),s("17cc")),n=Object(d["a"])(l,a,i,!1,null,"20e431e9",null);t["default"]=n.exports},"894a":function(e,t,s){"use strict";var a=s("c0be"),i=s.n(a);i.a},c0be:function(e,t,s){}}]);