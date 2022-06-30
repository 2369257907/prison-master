import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "@/layout"
import User from "@/views/User";
import Prisoner from "@/views/Prisoner";
import Warn from "@/views/Warn";
import jailInfo from "@/views/jailInfo";
import Login from "@/views/systeam/Login";
import Init from "@/views/systeam/Init";
import RealPosition from "@/views/RealPosition";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: Index,
    children:[
      {
        path: '/user',
        name: 'user',
        component: User
      },
      {
        path: '/prisoner',
        name: 'prisoner',
        component: Prisoner
      },
      {
        path: '/warn',
        name: 'warn',
        component: Warn
      },
      {
        path: '/jailinfo',
        name: 'jailinfo',
        component: jailInfo,
        // children:[
        //   {
        //     path: '/realposition',
        //     name: 'realposition',
        //     component: RealPosition
        //   },
        // ]
      },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/init',
    name: 'init',
    component: Init
  },
  {
    path: '/realposition',
    name: 'realposition',
    component: RealPosition
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
