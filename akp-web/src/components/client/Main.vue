<template>
    <div>
      <el-image
        v-for="picture in pictures"
        :key="picture.pid"
        style="width: 400px; height: 208px;margin-left: 70px"
        :src="picture.purl" @click="downLoadPicture(picture.pid)"></el-image>

      <div style="text-align: center">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage1"
          :page-size="100"
          layout="prev, pager, next, jumper"
          :total="1000">
        </el-pagination>
      </div>
    </div>
</template>

<script>

import axios from 'axios'
export default {
  name: 'HomePage',
  data () {
    return {
      api: this.$store.state.api,
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
    axios.get(this.api + '/picture/picturePage/' + _this.currentPage1).then(function (resp) { // 这种是后端已解决了跨域问题
    // axios.get('/picture/picturePage/' + _this.currentPage1).then(function (resp) { // 这种是前端决了跨域问题
      console.log(resp)
      _this.pictures = resp.data
      console.log('获取的数据')
      console.log(_this.pictures)
    })
  },
  methods: {
    downLoadPicture (val) {
      // alert(val)
      this.$router.push({
        path: '/download',
        query: {
          pid: val
        }
      })
    },
    handleCurrentChange (val) {
      // alert('当前页:' + val)
      const _this = this
      axios.get(this.api + '/picture/picturePage/' + val).then(function (resp) {
        console.log(resp)
        _this.pictures = resp.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
