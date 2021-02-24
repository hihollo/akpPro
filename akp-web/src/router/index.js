import Vue from 'vue'
import VueRouter from 'vue-router'
import Test from '../views/Test'
import Hou from '../views/Hou'
import Add2 from '../views/manager/Add2'

import PictureManager from '../views/manager/PictureManager'
import UpdatePicture from '../views/manager/UpdatePicture'
import AddPicture from '../views/manager/AddPicture'

import HomePage from '../views/client/HomePage'
import Main from '../components/client/Main'
import VariousType from '../components/client/VariousType'

import Login from '../views/manager/Login'
import DownLoad from '../views/client/DownLoad'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: '/home/main' // 当输入的路由不存在时，默认到该路径（重定向）
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/manager',
    component: PictureManager,
    meta: { auth: true } // 设置当前路由需要校验 不需要校验的路由就不用写了；不要问为什么，自己去看官网
  },
  {
    path: '/add',
    component: AddPicture,
    meta: { auth: true } // 设置当前路由需要校验 不需要校验的路由就不用写了；不要问为什么，自己去看官网
  },
  {
    path: '/update',
    component: UpdatePicture,
    meta: { auth: true } // 设置当前路由需要校验 不需要校验的路由就不用写了；不要问为什么，自己去看官网
  },
  {
    path: '/home',
    component: HomePage,
    children: [{
      path: '/home/main',
      component: Main
    }, {
      path: '/home/varioustype',
      component: VariousType
    }, {
      path: '/download',
      component: DownLoad
    }
    ]
  },
  {
    path: '/test',
    component: Test
  },
  {
    path: '/hou',
    component: Hou
  },
  {
    path: '/a2',
    component: Add2
  }
]
const router = new VueRouter({
  // mode: 'history',
  // base: '/myvue/', // base: process.env.BASE_URL, // 部署时修改的
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

// const auth = {
//   isLogin () {
//     return true
//   }
// }
// 全局路径守卫
// router.beforeEach((to, from, next) => {
//   alert(this)
//   if (to.path === '/manager') {
//     console.log('盘查')
//     if (auth.isLogin()) {
//       next()
//     } else {
//       next('/login')
//     }
//   } else {
//     next()
//   }
// })

export default router
