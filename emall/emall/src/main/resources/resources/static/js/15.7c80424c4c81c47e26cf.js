webpackJsonp([15],{"5LDq":function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=s("Dd8w"),o=s.n(a),n=s("blfI"),r=s("/W+i"),i=s("NYxO"),c={data:function(){return{password:"",email:"",show:!1,errTxt:"Login and password are required."}},created:function(){},mounted:function(){},methods:o()({signIn:function(){var e=this;if(this.email.trim()&&this.password.trim()){console.log("发送请求");var t={userEmail:this.email,password:Object(r.a)(this.password)};Object(n.d)(t).then(function(t){0==t.data.code?(sessionStorage.setItem("token",t.data.data.token),sessionStorage.setItem("userId",t.data.data.userId),e.setToken(t.data.data.token),e.setUserId(t.data.data.userId),e.$router.push("./")):(e.show=!0,e.errTxt="Invalid username or password <br> (check your CAPS LOCK key)")})}else this.show=!0},jump:function(){this.$router.push("/forgorPas")},jumps:function(){this.$router.push("/register")},facebook:function(e){var t=this;FB.getLoginStatus(function(e){console.log(e),"connected"==e.status?FB.api("/me",{fields:"last_name,first_name"},function(s){console.log(s);var a={accessToken:e.authResponse.accessToken,facebookUserId:e.authResponse.userID,firstName:s.first_name,lastName:s.last_name};Object(n.c)(a).then(function(e){0==e.data.code?(sessionStorage.setItem("token",e.data.data.token),sessionStorage.setItem("userId",e.data.data.userId),t.setToken(e.data.data.token),t.setUserId(e.data.data.userId),t.$router.push("./")):(t.show=!0,t.errTxt="Invalid username or password <br> (check your CAPS LOCK key)")})}):FB.login(function(e){console.log(e),FB.api("/me",{fields:"last_name,first_name"},function(s){console.log(s);var a={accessToken:e.authResponse.accessToken,facebookUserId:e.authResponse.userID,firstName:s.first_name,lastName:s.last_name};Object(n.c)(a).then(function(e){0==e.data.code?(sessionStorage.setItem("token",e.data.data.token),sessionStorage.setItem("userId",e.data.data.userId),t.setToken(e.data.data.token),t.setUserId(e.data.data.userId),t.$router.push("./")):(t.show=!0,t.errTxt="Invalid username or password <br> (check your CAPS LOCK key)")})})})})},google:function(){}},Object(i.b)({setToken:"SET_TOKEN",setUserId:"SET_USERID"}))},d={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"ell-bac"},[a("div",{staticClass:"ell-container"},[a("router-link",{attrs:{to:"/index/home"}},[a("img",{staticClass:"ell-logImg",attrs:{src:s("W1y7"),alt:""}})]),e._v(" "),a("div",{staticClass:"ell-b"},[e._v("Login")]),e._v(" "),a("div",{staticClass:"ell-we"},[e._v("Welcome to Jmoptical")]),e._v(" "),a("div",{staticClass:"ell-btn"},[a("a",{attrs:{href:"javascript:void(0)"},on:{click:function(t){e.facebook("facebook")}}},[e._v("\n        SIGN IN WITH FACEBOOK\n        "),a("svg",{staticClass:"icon",attrs:{"aria-hidden":"true"}},[a("use",{attrs:{"xlink:href":"#icon-facebook"}})])]),e._v(" "),a("a",{attrs:{href:"javascript:void(0)"},on:{click:function(t){e.google("google")}}},[e._v("\n        SIGN IN WITH GOOGLE\n        "),a("svg",{staticClass:"icon",attrs:{"aria-hidden":"true"}},[a("use",{attrs:{"xlink:href":"#icon-facebook"}})])])]),e._v(" "),e.show?a("div",{staticClass:"ell-prompt",domProps:{innerHTML:e._s(e.errTxt)}}):e._e(),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.email,expression:"email"}],attrs:{type:"text",placeholder:"Email"},domProps:{value:e.email},on:{input:function(t){t.target.composing||(e.email=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.password,expression:"password"}],attrs:{type:"password",placeholder:"Password"},domProps:{value:e.password},on:{input:function(t){t.target.composing||(e.password=t.target.value)}}}),e._v(" "),a("button",{staticClass:"ell-sign",on:{click:e.signIn}},[e._v("SIGN IN")]),e._v(" "),a("div",{staticClass:"ell-bottom"},[a("p",{on:{click:e.jump}},[e._v("Forgot Password?")]),e._v(" "),a("p",{on:{click:e.jumps}},[e._v("Join Free")])])],1),e._v(" "),a("div",{staticClass:"ell-bq"},[e._v("©️2018 JMOPTICAL Eyewear")])])},staticRenderFns:[]};var l=s("VU/8")(c,d,!1,function(e){s("HAIb")},"data-v-cf8494f2",null);t.default=l.exports},HAIb:function(e,t){},blfI:function(e,t,s){"use strict";t.d=function(e){return Object(a.a)({url:"user/login",method:"post",data:e})},t.a=function(e){return Object(a.a)({url:"/user/addUserBySystem",method:"post",data:e})},t.b=function(e){return Object(a.a)({url:"user/getEmailCode?email="+e,method:"post"})},t.c=function(e){return Object(a.a)({url:"/user/facebookLogin",method:"post",data:e})};var a=s("YlX4")}});
//# sourceMappingURL=15.7c80424c4c81c47e26cf.js.map