<template>
    <div>
      <h1>管理员登陆页面</h1>
     <!-- <button @click="handleLogin1">登陆</button>-->
      <form>
        用户名<input type="text" v-model="managerName">
        密码<input type="text" v-model="managerPassword">
        <button type="button" @click="handleLogin1">登陆</button>
      </form>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Login',
  data () {
    return {
      api: this.$store.state.api,
      managerName: '',
      managerPassword: ''
    }
  },
  methods: {
    handleLogin1 () {
      const _this = this
      const formData = new FormData()
      formData.append('managerName', this.managerName)
      formData.append('masword', this.managerPassword)
      axios.get(_this.api + '/manager/loginTo?' + 'managerName=' + this.managerName + '&managerPassword=' + this.managerPassword).then(function (resp) {
        alert(resp.data)
        if (resp.data === 'success') {
          _this.$store.commit('changeLogin', '100') // 登录后改变登录状态 isLogin = 100 ；
          _this.$router.push('/manager') // window.location.href = '/manager'
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
