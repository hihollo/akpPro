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
  name: 'VariousType', // 点击各种不同类型图片跳转至此
  data () {
    return {
      api: this.$store.state.api,
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
  methods: {
    handleCurrentChange (val) {
      // alert('当前页:' + val)
      const _this = this
      axios.get(this.api + '/picture/getPictureByType' + '?currentPage=' + _this.currentPage1 + '&' + 'ptype=' + this.$route.query.type).then(function (resp) {
        console.log(resp)
        _this.pictures = resp.data
      })
    },
    downLoadPicture (val) {
      // alert(val)
      this.$router.push({
        path: '/download',
        query: {
          pid: val
        }
      })
    }
  },
  created () {
    const _this = this
    axios.get(this.api + '/picture/getPictureByType' + '?currentPage=' + _this.currentPage1 + '&' + 'ptype=' + this.$route.query.type).then(function (resp) {
      console.log(resp)
      _this.pictures = resp.data
    })
  },
  destroyed () {
    this.pictures = null
  }
}
</script>

<style scoped>

</style>
