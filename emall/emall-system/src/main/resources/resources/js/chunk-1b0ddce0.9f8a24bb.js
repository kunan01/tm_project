(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1b0ddce0"],{"2de9":function(e,t,s){"use strict";var a="http://114.115.211.170:8088/";t["a"]={IP:a,IMGURL:a+"system/file/showPicture?imageId=",LOGIN:a+"system/user/login",SHOP:a+"system/product/getProductList",SHOPDETAIL:a+"system/product/getProductListById",DELSHOP:a+"system/product/delProduct",ALLDELSHOP:a+"system/product/delProduct",DISCOUNT:a+"system/product/setProductDiscounts",DELSHOPLABAL:a+"system/product/delProductParam",DELSHOPPROP:a+"system/product/delProductProp",ADDSHOP:a+"system/product/addProduct",ADDSHOPLABAL:a+"system/product/addProductParam",ADDSHOPPROP:a+"system/product/addProductProp",EDOTSHOPPROP:a+"system/product/updProductProp",SHELVESSHOP:a+"system/product/shelvesProduct",THESHELVESSHOP:a+"system/product/theShelvesProduct",NEWSHOP:a+"system/product/setOrCancelNewProduct",PROPLIST:a+"system/shopProp/queryPropList",UPSHOP:a+"system/product/updProduct",SHUFFLING:a+"system/slides/getSlidesList",FREEZESHUFFLING:a+"system/slides/updSlides",DELSHUFFLING:a+"system/slides/delSlides",DELALLSHUFFLING:a+"system/slides/batchDeleteSlides",ADDSHUFFLING:a+"system/slides/addSlides",ALLCATEGORYLIST:a+"system/category/queryCateGoryList",CATEGORYLIST:a+"system/category/getCateGoryList",TWOCATEGORYLIST:a+"system/category/getCateGoryListByTwoLevel",ADDCATEGORY:a+"system/category/addCategory",UPCATEGORY:a+"system/category/updCategory",DELCATEGORY:a+"system/category/delCateGory",DELALLCATEGORY:a+"system/category/batchDelCateGory",ALLLABEL:a+"system/param/queryShopParamList",LABEL:a+"system/param/getShopParamKeyList",TLABEL:a+"system/param/getShopParamValueList",ADDLABEL:a+"system/param/addShopParamType",ADDTLABEL:a+"system/param/addShopParamValue",DELLABEL:a+"system/param/delShopParamType",DELTLABEL:a+"system/param/delShopParamValue",EDITLABEL:a+"system/param/updShopParamType",EDTITLABEL:a+"system/param/updShopParamValue",DELALLTABEL:a+"system/param/batchDelShopParamType",DELALLTTABEL:a+"system/param/batchDelShopParamValue",SPECKEY:a+"system/shopProp/getListKey",SPECVALUE:a+"system/shopProp/getListValue",ADDSPECKEY:a+"system/shopProp/addPropKey",ADDSPECVALUE:a+"system/shopProp/addPropValue",UPSPECKEY:a+"system/shopProp/updPropKey",UPSPECVALUE:a+"system/shopProp/updPropValue",DELSPECKEY:a+"system/shopProp/delPropKey",DELSPECVALUE:a+"system/shopProp/delPropValue",DELALLSPECKEY:a+"system/shopProp/batchDelPropKey",DELALLSPECVALUE:a+"system/shopProp/batchDelPropValue",DISTRIBUTION:a+"system/distribution/getStoreDistribution",ADDDISTRIBUTION:a+"system/distribution/addDistribution",UPDISTRIBUTION:a+"system/distribution/updateDistribution",DELDISTRIBUTION:a+"system/distribution/delDistribution",DELALLDISTRIBUTION:a+"system/distribution/batchDelDistribution",SERVICE:a+"system/support/getCustomerService",UPSERVICE:a+"system/support/updateCustomerService",HELP:a+"system/help/getSysHelpList",ADDHELP:a+"system/help/addHelp",UPHELP:a+"system/help/updateSysHelpList",DELHELP:a+"system/help/deleteSysHelpList",DELALLHELP:a+"system/help/batchDeleteSysHelpList",FEEDBACK:a+"system/support/getUsersMessageList",DEALWITHFEEDBACK:a+"system/support/dealWithMessage",DEALWITHALLFEEDBACK:a+"system/support/batchDealWithMessage",TREND:a+"system/trend/getTrendList",TRENDSHOP:a+"system/trend/getTrendProductList",ADDTRENDSHOP:a+"system/trend/addTrendProduct",ADDTREND:a+"system/trend/addTrend",ADDALLTRENDSHOP:a+"system/trend/batchAddTrendProduct",DELALLTREND:a+"system/trend/batchDelTrend",DELALLTRENDPRODUCT:a+"system/trend/batchDelTrendProduct",DELTREND:a+"system/trend/delTrendProduct",DELTRENDSHOP:a+"system/trend/delTrendProduct",UPTREND:a+"system/trend/updTrend",TRENDSHOPS:a+"system/product/getTrendProductList",POP:a+"system/advertising/getAdvertisingList",UPPOP:a+"system/advertising/updateAdvertising",SEARCHPOPSHOP:a+"system/product/getAdvertisingProductList",POPSHOP:a+"system/advertising/getAdvertisingProductList",POPSHOPS:a+"system/product/getAdvertisingProductList",ADDALLPOPSHOP:a+"system/advertising/batchAddAdvertisingProduct",DELPOPSHOP:a+"system/advertising/delAdvertisingProduct",DELALLPOPSHOP:a+"system/advertising/batchDelAdvertisingProduct"}},"3fb9":function(e,t,s){"use strict";var a=s("5496"),i=s.n(a);i.a},5496:function(e,t,s){},9579:function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"table"},[s("div",{staticClass:"crumbs"},[s("el-breadcrumb",{attrs:{separator:"/"}},[s("el-breadcrumb-item",[s("i",{staticClass:"el-icon-lx-cascades"}),e._v(" 趋势对应商品")])],1)],1),s("div",{staticClass:"container"},[s("el-select",{attrs:{placeholder:"请选择"},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}},e._l(e.list,function(e){return s("el-option",{key:e.raId,attrs:{label:e.title,value:e.raId}})}),1),s("div",{staticClass:"handle-box"},[s("el-button",{staticClass:"handle-del mr10",attrs:{type:"primary",icon:"delete"},on:{click:e.addSlide}},[e._v("增加")]),s("el-button",{staticClass:"handle-del mr10",attrs:{type:"primary",icon:"delete"},on:{click:e.delAll}},[e._v("批量删除")])],1),s("el-table",{ref:"multipleTable",staticClass:"table",attrs:{data:e.tableData,border:""},on:{"selection-change":e.handleSelectionChange}},[s("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),s("el-table-column",{attrs:{prop:"productId",label:"商品id",sortable:"",width:"200",align:"center"}}),s("el-table-column",{attrs:{prop:"productName",label:"商品名",sortable:"",width:"300",align:"center"}}),s("el-table-column",{attrs:{prop:"price",label:"商品价格",sortable:"",width:"300",align:"center"}}),s("el-table-column",{attrs:{label:"商品图片",width:"180",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return t.row.productImage?[s("img",{staticClass:"goods-img",attrs:{src:e.IMGURL+t.row.productImage},on:{click:function(s){return e.previewImg(e.IMGURL+t.row.productImage)}}})]:void 0}}],null,!0)}),s("el-table-column",{attrs:{label:"操作",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-button",{staticClass:"red",attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(s){return e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1),s("div",{staticClass:"pagination"},[s("el-pagination",{attrs:{background:"","page-size":e.pageSize,layout:"prev, pager, next",total:e.totals},on:{"current-change":e.handleCurrentChange}})],1)],1),s("el-dialog",{attrs:{title:"编辑",visible:e.editVisible,width:"30%"},on:{"update:visible":function(t){e.editVisible=t}}},[s("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px"}},[s("span",[e._v("选择添加的商品：")]),s("el-select",{attrs:{multiple:"",filterable:"",remote:"","reserve-keyword":"",placeholder:"请输入关键词","remote-method":e.remoteMethod,loading:e.loading},model:{value:e.value9,callback:function(t){e.value9=t},expression:"value9"}},e._l(e.options4,function(e){return s("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.editVisible=!1}}},[e._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.saveEdit}},[e._v("确 定")])],1)],1),s("el-dialog",{attrs:{title:"提示",visible:e.delVisible,width:"300px",center:""},on:{"update:visible":function(t){e.delVisible=t}}},[s("div",{staticClass:"del-dialog-cnt"},[e._v("删除不可恢复，是否确定删除？")]),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.delVisible=!1}}},[e._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.deleteRow}},[e._v("确 定")])],1)]),s("el-dialog",{attrs:{title:"提示",visible:e.delVisibles,width:"300px",center:""},on:{"update:visible":function(t){e.delVisibles=t}}},[s("div",{staticClass:"del-dialog-cnt"},[e._v("确认批量删除，删除不可恢复，是否确定删除？")]),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.delVisibles=!1}}},[e._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.deleteRows}},[e._v("确 定")])],1)]),s("el-dialog",{attrs:{title:"预览",visible:e.previewDialogVisible,width:"50%",center:""},on:{"update:visible":function(t){e.previewDialogVisible=t}}},[s("img",{staticClass:"preview-img",attrs:{src:e.previewImgUrl}})])],1)},i=[],o=s("2de9"),r={name:"hotShop",data:function(){return{IMGURL:o["a"].IMGURL,IP:o["a"].IP,value:"",list:[],totals:10,url:"./vuetable.json",tableData:[],cur_page:1,previewDialogVisible:!1,previewImgUrl:"",multipleSelection:[],is_search:!1,editVisible:!1,delVisible:!1,delVisibles:!1,form:{name:""},idx:-1,categoryId:"",pageSize:10,isModify:!1,imgList:[],options4:[],value9:[],searchList:[],loading:!1,states:[]}},created:function(){this.getLabel()},watch:{value:function(e,t){console.log(123),this.getData(e)}},methods:{remoteMethod:function(e){""!==e?(console.log(123),this.loading=!0,this.getShop(e)):this.options4=[]},addSlide:function(){this.value9=[],this.isModify=!1,this.editVisible=!0},getShop:function(e){var t=this,s={raId:this.value};e&&(s.productName=e),this.$axios.post(o["a"].POPSHOPS,s,{headers:{token:sessionStorage.getItem("token")}}).then(function(s){"-30"==s.data.code&&(t.$message.error("登录超时，请重新登录！"),setTimeout(function(){t.$router.push("/login")},3e3)),0==s.data.code&&(t.states=s.data.data,setTimeout(function(){t.searchList=t.states.map(function(e){return{value:e.productId,label:e.productName}}),t.loading=!1,t.options4=t.searchList.filter(function(t){return console.log(t),t.label.indexOf(e)>-1})},200))})},handleCurrentChange:function(e){this.cur_page=e,this.getData(this.value)},getLabel:function(){var e=this,t={pageSize:4,pageNo:this.cur_page};this.$axios.get(o["a"].POP,{params:t,headers:{token:sessionStorage.getItem("token")}}).then(function(t){if("-30"==t.data.code&&(e.$message.error("登录超时，请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)),0==t.data.code){e.list=t.data.data.list;var s=e.list[0];e.value=s.raId}})},getData:function(e){var t=this,s={raId:e,pageSize:this.pageSize,pageNo:this.cur_page};this.$axios.get(o["a"].POPSHOP,{params:s,headers:{token:sessionStorage.getItem("token")}}).then(function(e){"-30"==e.data.code&&(t.$message.error("登录超时，请重新登录！"),setTimeout(function(){t.$router.push("/login")},3e3)),0==e.data.code&&(t.totals=e.data.data.total,t.tableData=e.data.data.list)})},previewImg:function(e){""!==e&&(this.previewDialogVisible=!0,this.previewImgUrl=e)},handleDelete:function(e,t){this.delVisible=!0,this.categoryId=t.trId},delAll:function(){this.multipleSelection.length&&(this.delVisibles=!0)},handleSelectionChange:function(e){var t=this;this.multipleSelection=[],e.map(function(e){return t.multipleSelection.push(e.trId)})},deleteRow:function(){var e=this,t={};this.$axios.post(o["a"].DELPOPSHOP+"?trId="+this.categoryId,t,{headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code&&(e.$message.success("登录超时！请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)),0==t.data.code&&e.getData(e.value)}),this.delVisible=!1},deleteRows:function(){var e=this,t={trIdList:this.multipleSelection};console.log(t),this.$axios.post(o["a"].DELALLPOPSHOP,t,{headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code?(e.$message.success("登录超时！请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)):0==t.data.code?e.getData(e.value):e.$message.error("删除失败")}),this.delVisibles=!1},saveEdit:function(){var e=this,t={raId:this.value,productIdList:this.value9};this.$axios.post(o["a"].ADDALLPOPSHOP,t,{headers:{token:sessionStorage.getItem("token")}}).then(function(t){"-30"==t.data.code&&(e.$message.success("登录超时！请重新登录！"),setTimeout(function(){e.$router.push("/login")},3e3)),0==t.data.code&&e.getData(e.value),e.editVisible=!1})}}},l=r,d=(s("3fb9"),s("17cc")),n=Object(d["a"])(l,a,i,!1,null,"28b00488",null);t["default"]=n.exports}}]);