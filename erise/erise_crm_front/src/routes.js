import Vue from 'vue'
import echarts from './views/charts/echarts.vue'
import Home from './views/Home.vue'
import axios from 'axios'
import bind from './views/bind.vue'

//配置axios的全局基本路径
axios.defaults.baseURL='http://localhost/'


import Login from './views/Login.vue'
import account from './views/account.vue'
import NotFound from './views/404.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/bind',
        component: bind,
        name: '',
        hidden: true
    },
    {
        path: '/',
        component: Home,
        name: '报表',
        leaf:true,
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/echarts', component: echarts, name: '报表' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '账户管理',
        leaf:true,
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/account', component: account, name: '账户管理' }
        ]
    }
];




export default routes;

