"use strict";(self["webpackChunkfronted_vue"]=self["webpackChunkfronted_vue"]||[]).push([[666],{4833:function(t,e,s){var a=s(5398);class r{getAll(t,e,s){return a.A.get(`/shop/simple-cart?title=${t}&page=${e}&size=${s}`)}create(t){return a.A.post("/shop/simple-cart",t)}remove(t){return a.A.delete(`/shop/simple-cart/deletion/${t}`)}}e.A=new r},2136:function(t,e,s){var a=s(5398);class r{getAll(t,e,s){return a.A.get(`/shop/simple-product?title=${t}&page=${e}&size=${s}`)}get(t){return a.A.get(`/shop/simple-product/${t}`)}}e.A=new r},6666:function(t,e,s){s.r(e),s.d(e,{default:function(){return S}});var a=s(6768),r=s(4232);const c={class:"mt-5"},l={key:0,class:"card mb-3"},o={class:"row g-0"},n={class:"col-md-4"},i=["src"],u={class:"col-md-8"},d={class:"card-body"},p={class:"card-title"},m={class:"card-title"},b={class:"card-text"},g=(0,a.Lk)("br",null,null,-1),k={class:"btn-group col",role:"group","aria-label":"Basic outlined example"},C={type:"button",class:"btn btn-outline-dark",disabled:""},v={class:"mt-3"},h={class:"mt-3"},L={key:0,class:"alert alert-success mt-3 text-center"};function y(t,e,s,y,P,A){return(0,a.uX)(),(0,a.CE)("div",c,[(0,a.Lk)("div",null,[P.simpleProduct?((0,a.uX)(),(0,a.CE)("div",l,[(0,a.Lk)("div",o,[(0,a.Lk)("div",n,[(0,a.Lk)("img",{src:P.simpleProduct.imgPath,class:"img-fluid rounded-start",alt:"..."},null,8,i)]),(0,a.Lk)("div",u,[(0,a.Lk)("div",d,[(0,a.Lk)("h5",p,(0,r.v_)(P.simpleProduct.title),1),(0,a.Lk)("h5",m,"₩ "+(0,r.v_)(P.simpleProduct.unitPrice),1),(0,a.Lk)("p",b,[(0,a.eW)(" 영원한 아이콘인 "+(0,r.v_)(P.simpleProduct.title)+" 으로 스타일링 해보세요. ",1),g,(0,a.eW)(" 또한, 4계절을 함께 할 "+(0,r.v_)(P.simpleProduct.title)+" 으로 여러분의 OOTD 를 표현해 보세요. ",1)]),(0,a.Lk)("div",k,[(0,a.Lk)("button",{type:"button",class:"btn btn-outline-secondary opacity-50",onClick:e[0]||(e[0]=(...t)=>A.decreaseCount&&A.decreaseCount(...t))}," - "),(0,a.Lk)("button",C,(0,r.v_)(P.cartCount),1),(0,a.Lk)("button",{type:"button",class:"btn btn-outline-secondary opacity-50",onClick:e[1]||(e[1]=(...t)=>A.increaseCount&&A.increaseCount(...t))}," + ")]),(0,a.Lk)("div",v,[(0,a.Lk)("button",{type:"submit",class:"btn btn-primary w-25",onClick:e[2]||(e[2]=(...t)=>A.saveSimpleCart&&A.saveSimpleCart(...t))}," 장바구니 담기 "),(0,a.Lk)("button",{type:"submit",class:"btn btn-success w-25 ms-2",onClick:e[3]||(e[3]=(...t)=>A.goSimpleCart&&A.goSimpleCart(...t))}," 장바구니 가기 ")]),(0,a.Lk)("div",h,[(0,a.Lk)("button",{type:"button",class:"btn btn-warning w-25",onClick:e[4]||(e[4]=(...t)=>A.goOrder&&A.goOrder(...t))}," 주문하기 ")]),P.message?((0,a.uX)(),(0,a.CE)("p",L,(0,r.v_)(P.message),1)):(0,a.Q3)("",!0)])])])])):(0,a.Q3)("",!0)])])}s(4114);var P=s(2136),A=s(4833),f={data(){return{simpleProduct:{},message:"",cartCount:0}},methods:{async getSimpleProduct(t){try{let e=await P.A.get(t);this.simpleProduct=e.data,console.log(e.data)}catch(e){console.log(e)}},async saveSimpleCart(){try{let t={spno:this.simpleProduct.spno,cartCount:this.cartCount},e=await A.A.create(t);console.log(e.data),this.message="장바구니에 잘 담겼습니다."}catch(t){console.log(t)}},goSimpleCart(){this.$router.push("/simple-cart")},increaseCount(){this.cartCount+=1},decreaseCount(){this.cartCount>0&&(this.cartCount-=1)},goOrder(){}},mounted(){this.getSimpleProduct(this.$route.params.spno)}},w=s(1241);const $=(0,w.A)(f,[["render",y]]);var S=$}}]);
//# sourceMappingURL=666.a2472b14.js.map