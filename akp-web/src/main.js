import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import VideoPlayer from 'vue-video-player'
import 'video.js/dist/video-js.css'
import axios from 'axios' // 引入axios
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI)

Vue.prototype.$axios = axios // 添加axios到Vue的原型对象上
Vue.use(VideoPlayer)

Vue.config.productionTip = false

/* router.beforeEach((to, from, next) => {
  if (to.matched.some(m => m.meta.auth)) {
    // 对路由进行验证
    if (store.state.isLogin === '100') { // 已经登陆
      next() // 正常跳转到你设置好的页面
    } else {
      // 未登录则跳转到登陆界面，query:{ Rurl: to.fullPath}表示把当前路由信息传递过去方便登录后跳转回来；
      next({ path: '/login' })
    }
  } else {
    next()
  }
}) */
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
