<template>
  <div>
    <Header></Header>
    <div style="margin-top: 5px;background-color: #eee7ee">
      <el-button v-for="type in polytype" :key="type.name" style="margin-left: 50px" @click="toDif(type)" :id="type.name">{{type.value}}</el-button>
    </div>
    <div style="margin-top: 10px">
      <!--如果不绑定key值，传递不同的参数到同一路由时，该路由中的内容不会改变（我理解为dom的标签没发生改变，不会刷新该路由中的内容）-->
      <router-view  :key="reload"></router-view>
    </div>
  </div>

</template>

<script>
// import axios from 'axios'
import Header from '../../components/client/Header'
export default {
  name: 'HomePage',
  data () {
    return {
      reload: false,
      clientWidth: document.body.clientWidth,
      // clientHeight: document.body.clientHeight,
      clientHeight: document.documentElement.clientHeight,
      currentPage1: 1,
      polytype: [{
        name: 'main',
        value: '首页'
      }, {
        name: 'scenery',
        value: '风景'
      }, {
        name: 'belle',
        value: '美女'
      }, {
        name: 'game',
        value: '游戏'
      }, {
        name: 'cartoon',
        value: '动漫'
      }, {
        name: 'star',
        value: '影视'
      }, {
        name: 'car',
        value: '汽车'
      }, {
        name: 'animal',
        value: '动物'
      }, {
        name: 'figure',
        value: '人物'
      }, {
        name: 'food',
        value: '美食'
      }, {
        name: 'religion',
        value: '宗教'
      }, {
        name: 'background',
        value: '背景'
      }],
      picture: [{
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
  components: {
    Header
  },
  methods: {
    toDif (val) {
      this.butChangeColor(val) // 点击修改button颜色
      this.reload = new Date().getTime()
      // alert(val.name)
      if (val.name === 'main') {
        this.$router.push({
          path: '/home/main'
        })
      } else {
        this.$router.push({
          path: '/home/varioustype',
          query: {
            type: val.value
          }
        })
      }
    },
    butChangeColor (val) {
      for (let i = 0; i < this.polytype.length; i++) {
        const b = document.getElementById(this.polytype[i].name)
        b.style.background = ''
      }
      const but = document.getElementById(val.name)
      but.style.background = 'rgba(43,41,255,0.67)'
    }
  },
  mounted () {
    const s = document.getElementById('main')
    s.style.background = 'rgba(43,41,255,0.67)'
  }
}
</script>

<style lang="scss" scoped>

</style>
