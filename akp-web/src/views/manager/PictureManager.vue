<template>
    <div >
      <div  id="boxFixed">
        <Header class="head" :style="{ height: clientHeight/18 +'px'}" ></Header>
        <hr>
        <div class="search" :style="{ height: clientHeight/18 +'px'}"  >
          <span style="font-size: 20px">搜索图片:</span> <input type="text" style="height: 25px" v-model="position">
          <button class="but" @click="findPosition(position)">搜索</button>
          <button class="but" @click="addPicture" :style="{marginLeft: clientWidth/2 + 'px'}">添加图片</button>
          <button class="but" @click="deleteAllPicture()" style="margin-left: 10px">删除所有图片</button>
        </div>
      </div>

        <table class="vtable" :style="{ width: clientWidth - 100 + 'px',marginTop:clientHeight/18*2 +18 +'px' }">
          <tr>
            <td>ID</td>
            <td>图片</td>
            <td><div class="table_td">图片名称</div></td>
            <td>图片高宽</td>
            <td>图片类型</td>
            <td>关键字</td>
            <td>制作日期</td>
            <td>图片大小</td>
            <td>作者</td>
            <td>操作</td>
          </tr>
          <tr v-for="picture in pictures" :key="picture.pid" :id="'i' + picture.pid">
            <td>{{picture.pid}}</td>
            <td><img :src="picture.purl" alt="小黄鸭" width="70px" height="50px"></td>
            <td><div class="table_td">{{picture.ptitle}}</div></td>
            <td>{{picture.psize}}</td>
            <td>{{picture.ptype}}</td>
            <td>{{picture.psearch}}</td>
            <td>{{picture.pyear}}</td>
            <td>{{picture.plength}}</td>
            <td>{{picture.author}}</td>
            <td><button @click="updatePicture(picture.pid)">修改</button> <button @click="deletePicture(picture.pid)">删除</button></td>
          </tr>
        </table>
    </div>
</template>

<script>
import axios from 'axios'
import Header from '../../components/Header'
export default {
  name: 'PictureManager',
  components: {
    Header
  },
  data () {
    return {
      api: this.$store.state.api,
      isFixed: false,
      offsetTop: 0,
      clientWidth: document.body.clientWidth,
      // clientHeight: document.body.clientHeight,
      clientHeight: document.documentElement.clientHeight,
      position: '', // 搜索框搜索内容
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
    deletePicture (par) {
      const _this = this
      let txt
      if (confirm('您确定要删除该图片吗？？')) {
        txt = 'ture'
      } else {
        txt = 'false'
      }
      if (txt === 'ture') {
        axios.delete(this.api + '/picture/deleteOne/' + par).then(function (resp) {
          console.log('删除图片返回的信息' + resp)
          if (resp.data === 'success') {
            _this.$alert('id为：' + par + '删除成功。', '消息', {
              confirmButtonText: '确定',
              callback: action => {
                console.log(_this.$router)
                // _this.$router.push('/manager') /* 跳转到BookManage页面 */
                window.location.reload()
              }
            })
          } else {
            alert('删除图片失败！！')
          }
        })
      } else {
        alert('未删除！')
      }
    },
    updatePicture (par) {
      this.$router.push({
        path: '/update',
        query: {
          id: par
        }
      })
    },
    findPosition (target) { // 根据搜索跳到页面指定位置
      // alert(target)
      target = '#' + 'i' + target // 标签id不能以数字开头
      const toElement = document.querySelector(target) // 获取文档中 id="demo" 的元素：
      console.log(toElement)
      toElement.scrollIntoView(this.scrollIntoViewOptions)
    },
    initHeight () { // 吸顶效果
      // 设置或获取位于对象最顶端和窗口中可见内容的最顶端之间的距离 (被卷曲的高度)
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      // 如果被卷曲的高度大于吸顶元素到顶端位置 的距离
      this.isFixed = scrollTop > this.offsetTop
    },
    addPicture () {
      this.$router.push('/add')
    },
    deleteAllPicture () {
      var txt = 'false'
      const _this = this
      if (confirm('您确定要清空所有图片吗!！')) {
        txt = 'ture'
      } else {
        txt = 'false'
      }
      if (txt === 'ture') {
        axios.delete(this.api + '/picture/deleteAll').then(function (resp) {
          console.log(resp)
          if (resp.data === 'success') {
            alert('清空图片成功..')
            _this.picture = null
            window.local.reload()
          } else {
            alert('清空图片失败！！')
          }
        })
      } else {
        alert('未删除图片..')
      }
    }
  },
  created () {
    const _this = this
    axios.get(this.api + '/picture/getAll').then(function (resp) {
      console.log(resp)
      _this.pictures = resp.data
    })
  },
  mounted () {
    console.log('屏幕宽为：' + this.clientWidth)
    console.log('屏幕高为: ' + this.clientHeight)
    window.addEventListener('scroll', this.initHeight)
  },
  destroyed () {
    window.removeEventListener('scroll', this.onScroll, true)
  }

}
</script>

<style lang="scss" scoped>
  #boxFixed{
    position: fixed;
    width: 100%;
    top:0px;
    background: #d9d9d9;
    z-index: 5;
  }
.head{
  text-align: center;
}
table tr tr,
table tr td {
  border: 1px solid #0b0507;
}

table {
  min-height: 25px;
  line-height: 25px;
  text-align: center;
  border-collapse: collapse;
  padding: 2px;
}
.table_td {
  width: 80px;
  height: 100%;
  overflow:hidden;
  display:-webkit-box;
  -webkit-line-clamp:2;
  -webkit-box-orient:vertical;
}
.but{
  width: 100px;
  height: 30px;
}
</style>
