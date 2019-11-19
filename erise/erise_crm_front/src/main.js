import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//import './assets/theme/theme-green/index.css'
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
//import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
import routes from './routes'
//注释掉mock传入真实数据
// import Mock from './mock'
// Mock.bootstrap();
import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import BaiduMap from 'vue-baidu-map'


//配置axios的全局基本路径
axios.defaults.baseURL='http://localhost/';
//全局属性配置，在任意组件内可以使用this.$http获取axios对象
Vue.prototype.$http = axios;



Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(BaiduMap,{
    // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
    ak: 'srcYo69OsEoDAKrkVHwbH2U8M7rLGKgv'
}) 



//NProgress.configure({ showSpinner: false });



const router = new VueRouter({
    routes:routes
});

router.$addRoutes = (params) => {
    router.matcher = new VueRouter({mode: 'history'}).matcher;
    router.addRoutes(params)
}





//在发送axios请求 拦截下来 我在他前面添加header  头里面放token
axios.interceptors.request.use(config => {
    if (sessionStorage.getItem('token')) {
        // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
        config.headers['X-TOKEN'] = sessionStorage.getItem('token')
    }
    return config
}, error => {
    // Do something with request error
    Promise.reject(error)
});

let dynamicRouter = {
    routes
}

var getRouter //用来获取后台拿到的路由

let newRoutes = null;

/* 前端登录权限判断 /home */
router.beforeEach((to, from, next) => {

    //NProgress.start();
    if (to.path == '/login') {
        sessionStorage.removeItem('user');
    }


    var array = window.location.href.split("?")
    var ifbind = array[0].split("#")
    console.log(ifbind[1])
    if(array!=null && array.length == 2 && ifbind[1] != "/bind") {
        console.log(array)
        let array3 = array[1].replace("%", "").replace("%", "").replace("#/login", "").split("&")
        console.log(array3)
        let username = array3[0].replace("username=", "")
        let id = array3[1].replace("id=", "")
        let token = array3[2].replace("token=", "").split("#")[0]
        console.log("token-----------")
        console.log(token)
        let user1 = {username: username, id: id}
        console.log(user1)
        sessionStorage.setItem('user', JSON.stringify(user1));
        sessionStorage.setItem("token", token);
    }



        //如果session没有user信息，跳转到都能页面
    let user = JSON.parse(sessionStorage.getItem('user'));
    if (!user && to.path != '/login' && to.path !='/bind') {
        next({ path: '/login' })
    } else {
            next()
    }


   /* console.log("-----------判断------------")
    console.log(sessionStorage.getItem("newRoutes"))
    if(!sessionStorage.getItem("newRoutes")) {
*/
       // if (!sessionStorage.getItem("newRoutes")) {//不加这个判断，路由会陷入死循环

    if (!newRoutes) {
            // if (!getObjArr('router')) {
            axios.post('/systemMenu/permission').then(res => {
                getRouter = res.data//后台拿到路由

                let pMenu = eval(getRouter);
                for (let i = 0; i < pMenu.length; i++) {
                    let value = pMenu[i].component;
                    pMenu[i].component = function component(resolve) {
                        require(["" + value], resolve);
                    };
                    let cMenu = pMenu[i].children;
                    if (cMenu != null && cMenu.length > 0) {
                        for (let j = 0; j < cMenu.length; j++) {
                            let value1 = cMenu[j].component;
                            cMenu[j].component = function component(resolve1) {
                                require(["" + value1], resolve1);
                            };
                        }
                    }
                    // 重要：添加至动态路由
                    dynamicRouter.routes.splice(i + 1, 0, pMenu[i]);
                    newRoutes = dynamicRouter.routes;

                        router.$addRoutes(dynamicRouter.routes)



                }
                next({...to, replace: true})
                /*console.log("------------newRoutes-------------")
                console.log(newRoutes)
                sessionStorage.setItem("newRoutes", JSON.stringify(newRoutes));*/
            })

        } else {
                var jump = window.location.href.split("?")
                if(array!=null && array.length == 5) {
                    window.location.href = "localhost:8080/#/eharts"
                }
                /*console.log("------------sessionStorage-------------")
                console.log(JSON.parse(sessionStorage.getItem("newRoutes")))
                router.$addRoutes(JSON.parse(sessionStorage.getItem("newRoutes")))
            */
            next()
        }
    /* }
     else {

         next()
     }*/

})



new Vue({
    //el: '#app',
    //template: '<App/>',
    router,
    store,
    //components: { App }
    render: h => h(App)
}).$mount('#app')


