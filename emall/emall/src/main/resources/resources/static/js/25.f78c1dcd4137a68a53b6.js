webpackJsonp([25],{"/X/2":function(s,e){},"b+OD":function(s,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=t("Dd8w"),d=t.n(r),n=t("mvHQ"),i=t.n(n),a=t("qOVy"),c=t("NYxO"),u={name:"aaa",components:{History:t("8KRF").a},data:function(){return{widthH:"",userInfo:{},addressList:[],history:[{name:"My Account",url:"/myAccount"},{name:"Addresses",url:""}],tableList:["SHIPPING & RETURNS","SHOPPING ONLINE","WARRANTY & REPAIRS"],list:[{src:t("GarE"),url:"/myAccount/orders",text:"ORDERS"},{src:t("IQ5I"),url:"/myAccount/payment",text:"PAYMENT"},{src:t("CUK1"),url:"/myAccount/address",text:"ADDRESSES"},{src:t("HKz0"),url:"/myAccount/securityset",text:"SECURITY SET"},{src:t("tZ2C"),url:"/myAccount/giftcards",text:"GIFT CARDS"},{src:t("OjuJ"),url:"/myAccount/help",text:"HELP"}]}},created:function(){this.widthH=this.$root.widthH,this.$route.query.userInfo&&(this.userInfo=JSON.parse(this.$route.query.userInfo)),console.log(this.userInfo),this.getAddresslist()},methods:{jump:function(){this.$router.push({path:"/myAccount/address2",query:{userInfo:i()(this.userInfo)}})},getAddresslist:function(){var s=this;Object(a.d)(this.userId).then(function(e){0==e.data.code?s.addressList=e.data.data:s.addressList=[],console.log(e)})},AddressDelete:function(s){var e=this,t={addressId:s,userId:this.userId};Object(a.b)(t).then(function(s){0==s.data.code&&(e.$message({message:"Delete the success",type:"success"}),e.getAddresslist())})},edit:function(s){var e={addressId:s.addressId,userName:s.userName,addressLine1:s.addressLine1,addressLine2:s.addressLine2,city:s.city,province:s.province,zipCode:s.zipCode,country:s.country,userPhone:s.userPhone,instructions:s.instructions,security:s.security};this.$router.push({path:"/myAccount/address2",query:{addressItem:i()(e)}})},Default:function(s,e){var t=this,r={addressId:s,userId:this.userId,isDefault:e?0:1};Object(a.c)(r).then(function(s){0==s.data.code&&(t.$message({message:"Set up the success",type:"success"}),t.getAddresslist())}),1==r.isDefault&&this.$route.query.sign&&this.$router.go(-1)}},computed:d()({currentClass:function(){return this.widthH>=768?"h_center1":"h_center2"}},Object(c.c)(["userId"]))},o={render:function(){var s=this,e=s.$createElement,t=s._self._c||e;return t("div",{class:s.currentClass},[t("div",{staticClass:"w"},[t("History",{attrs:{history:s.history}}),s._v(" "),t("div",{staticClass:"my_address"},[t("h1",[s._v("Your Addresses")]),s._v(" "),t("ul",[t("li",{on:{click:s.jump}},[t("span",[s._v("+")]),s._v(" "),t("p",[s._v("Add addressrs")])]),s._v(" "),s._l(s.addressList,function(e,r){return t("li",{key:r},[t("div",{staticClass:"my_addressTop"},[s._v("\n            Default:\n            "),t("span",[s._v(s._s(e.isDefault?"true":"false"))])]),s._v(" "),t("div",{staticClass:"my_addressCenter"},[t("div",{staticClass:"name"},[s._v(s._s(e.userName))]),s._v(" "),t("div",{staticClass:"address"},[s._v(s._s(e.province)+s._s(e.city)+s._s(e.addressLine1)+s._s(e.addressLine2)+s._s(e.zipCode))]),s._v(" "),t("div",{staticClass:"city"},[s._v(s._s(e.country))]),s._v(" "),t("div",{staticClass:"phone"},[s._v("\n              phone:\n              "),t("span",[s._v(s._s(e.userPhone))])]),s._v(" "),t("div",{staticClass:"add"},[s._v("Add delivery instructions")]),s._v(" "),t("div",{staticClass:"btn"},[t("span",{on:{click:function(t){s.edit(e)}}},[s._v("edit")]),s._v(" "),t("span",{on:{click:function(t){s.AddressDelete(e.addressId)}}},[s._v("delete")]),s._v(" "),e.isDefault?t("span",{on:{click:function(t){s.Default(e.addressId,e.isDefault)}}},[s._v("Cancel the default")]):t("span",{on:{click:function(t){s.Default(e.addressId,e.isDefault)}}},[s._v("Set to default")])])])])})],2)])],1)])},staticRenderFns:[]};var l=t("VU/8")(u,o,!1,function(s){t("/X/2")},"data-v-48723556",null);e.default=l.exports}});
//# sourceMappingURL=25.f78c1dcd4137a68a53b6.js.map