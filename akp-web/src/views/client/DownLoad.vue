<template>
  <div>
    <h1 style="text-align: center">{{msg}}</h1>
    <div>
      <img :src="pictures.purl" alt="无图" width="50%">
    </div>
    <!--疑问：a标签中的href我是用pictures.purl为什么不能实现下载，而是打开新的网页显示图片，而使用跨域方式时可以下载图片？-->
    <a :href="psrc"  :download="pictures.ptitle">点击下载</a>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Download',
  data () {
    return {
      api: this.$store.state.api,
      msg: '下载图片页面',
      psrc: '',
      pictures: {
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

    }
  },
  created () {
    const _this = this
    axios.get(_this.api + '/picture/getOne/' + this.$route.query.pid).then(function (resp) { // this.$route.query.id编程式导航传来的
      console.log('单张图片')
      console.log(resp)
      // http://localhost:8080/staticphoto/cf3bbede-a246-4a1a-8037-79b873520aee.jpg
      let endUrl = resp.data.purl
      const f = endUrl.lastIndexOf('/')
      endUrl = endUrl.slice(f + 1)
      console.log(endUrl)
      _this.psrc = '/staticphoto/' + endUrl
      console.log('图片下载路径')
      console.log(_this.psrc)
      _this.pictures = resp.data
    })
  }
}
</script>

<style scoped>

</style>
