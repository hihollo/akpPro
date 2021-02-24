<template>
    <div>
      <h1 style="text-align: center">{{msg}}</h1>
      <div>
        <img :src="pictures.purl" alt="小黄鸭" width="220px" height="180px">
      </div>
      <form>
        <table class="vtable">
          <tr>
            <td>
              ID
            </td>
            <td>
              <input type="text" v-model="pictures.pid" disabled="disabled" >
            </td>
          </tr>
          <tr>
          <tr>
            <td>
              图片名称
            </td>
            <td>
              <input type="text" v-model="pictures.ptitle">
            </td>
          </tr>
          <tr>
            <td>
              图片高宽（如500*400）
            </td>
            <td>
              <input type="text" v-model="pictures.psize" disabled="true">
            </td>
          </tr>
          <tr>
            <td>
              图片类型
            </td>
            <td>
              <select v-model="pictures.ptype">
                <option value="风景">风景</option>
                <option value="美女">美女</option>
                <option value="动漫">动漫</option>
                <option value="游戏">游戏</option>
                <option value="影视">影视</option>
                <option value="汽车">汽车</option>
                <option value="动物">动物</option>
                <option value="人物">人物</option>
                <option value="美食">美食</option>
                <option value="宗教">宗教</option>
                <option value="背景">背景</option>
              </select>
            </td>
          </tr>
          <tr>
            <td>
              搜索关键字
            </td>
            <td>
              <input type="text" v-model="pictures.psearch">
            </td>
          </tr>
          <tr>
            <td>
              制作年份（如2018）
            </td>
            <td>
              <input type="text" v-model="pictures.pyear">
            </td>
          </tr>
          <tr>
            <td>
              图片大小（如4K）
            </td>
            <td>
              <input type="text" v-model="pictures.plength" disabled="true">
            </td>
          </tr>
          <tr>
            <td>
              作者
            </td>
            <td>
              <input type="text" v-model="pictures.author">
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <button class="button button-primary button-pill button-small" @click="submitInfo">提交</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'UpdatePicture',
  data () {
    return {
      msg: '修改图片页面',
      api: this.$store.state.api,
      pictures: [{
        pid: 0,
        ptitle: '', // 图片名称
        psize: '', // 图片高宽 如500*400
        ptype: '', // 图片类型（如风景、人物、动漫。。。）
        psearch: '', // 搜索图片关键字
        purl: '', // 图片位置
        pyear: '', // 图片制作时间为何年
        plength: '', // 图片大小 如3M
        author: ''// 图片作者
      }
      ]

    }
  },
  methods: {
    submitInfo () {
      const _this = this
      axios.put(this.api + '/picture/updateOne', _this.pictures).then(function (resp) {
        console.log(resp.data)
      })
      _this.$router.push('/manager')
      window.location.reload()
    }
  },

  created () {
    const _this = this
    axios.get(this.api + '/picture/getOne/' + this.$route.query.id).then(function (resp) { // this.$route.query.id编程式导航传来的
      _this.pictures = resp.data
    })
  }
}
</script>

<style scoped>

</style>
