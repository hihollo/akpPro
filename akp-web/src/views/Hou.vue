<template>
  <div>
   <h2>hou</h2>
    <h2 v-for="p in pictures" :key="p.pid">
      {{p.pid}} -- {{p.purl}}
    </h2>
  </div>
</template>

<script>

import axios from 'axios'
export default {
  name: 'HomePage',
  data () {
    return {
      clientWidth: document.body.clientWidth,
      // clientHeight: document.body.clientHeight,
      clientHeight: document.documentElement.clientHeight,
      currentPage1: 1,
      pictures: [{
        pid: '',
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
  created () {
    const _this = this
    axios.get(this.api + '/picture/getAll').then(function (resp) { // 这种是前端决了跨域问题
      console.log(resp)
      console.log('后端返回数据')
      _this.pictures = resp.data
    })
  }
}
</script>

<style lang="scss" scoped>

</style>
