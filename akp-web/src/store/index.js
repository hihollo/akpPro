import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    api: 'http://www.jkdev.store/akphou', // 部署时
    // api: 'http://localhost:8080/akphou', // 开发时
    isLogin: 0 // 初始时候给一个 isLogin=0 表示用户未登录
  },
  mutations: {
    changeLogin (state, data) {
      state.isLogin = data
      localStorage.setItem(state.isLogin, data)
    }
  },
  actions: {
  },
  modules: {
  }
})
