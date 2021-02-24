<template>
<div>
  <div class="hello">
    <div>
      <h3>上传单个文件</h3>
      <form enctype="multipart/form-data">
        <table class="vtable">
          <tr>
            <td>
              选择图片
            </td>
            <td>
              <input type="file" @change="getFile($event)">
            </td>
          </tr>
          <tr>
            <td>
              图片名称
            </td>
            <td>
              <input type="text" v-model="formDataPicture.picture.ptitle">
            </td>
          </tr>
          <tr>
            <td>
              图片高宽（如500*400）
            </td>
            <td>
              <input type="text" v-model="formDataPicture.picture.psize" disabled="true">
            </td>
          </tr>
          <tr>
            <td>
              图片类型
            </td>
            <td>
              <select v-model="formDataPicture.picture.ptype">
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
              <input type="text" v-model="formDataPicture.picture.psearch">
            </td>
          </tr>
          <tr>
            <td>
              制作年份（如2018）
            </td>
            <td>
              <input type="text" v-model="formDataPicture.picture.pyear">
            </td>
          </tr>
          <tr>
            <td>
              图片大小（如4K）
            </td>
            <td>
              <input type="text" v-model="formDataPicture.picture.plength" disabled="true">
            </td>
          </tr>
          <tr>
            <td>
              作者
            </td>
            <td>
              <input type="text" v-model="formDataPicture.picture.author">
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <button class="button button-primary button-pill button-small" @click="submit($event)">提交</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>

  <div>
    <h3>上传多个文件</h3>
    <form enctype="multipart/form-data" id="fs">
      图片类型:
      <select v-model="formDataPicture.picture.ptype">
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
      </select> <br>
      <input type="file" @change="getFile2($event)"><br>
      <input type="file" @change="getFile3($event)"><br>
      <input type="file" @change="getFile4($event)"><br>
      <input type="file" @change="getFile5($event)"><br>
      <input type="file" @change="getFile6($event)"><br>
      <button  type="button"  @click="submit2($event)">提交</button>
    </form>
  </div>
</div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'HelloWorld',
  data () {
    return {
      api: this.$store.state.api,
      msg: '添加图片页面',
      picturePath: '', // 根据磁盘路径添加图片
      files0: '',
      files1: '',
      files2: '',
      files3: '',
      files4: '',

      formDataPicture: {
        picture: {
          ptitle: '', // 图片名称
          psize: '', // 图片高宽 如500*400
          ptype: '', // 图片类型（如风景、人物、动漫。。。）
          psearch: '', // 搜索图片关键字
          // purl: '', // 图片位置
          pyear: '未知', // 图片制作时间为何年
          plength: '', // 图片大小 如3M
          author: '未知'// 图片作者
        },
        file: ''
      }
    }
  },
  methods: {
    getFile2: function (event) {
      this.files0 = event.target.files[0]
    },
    getFile3: function (event) {
      this.files1 = event.target.files[0]
    },
    getFile4: function (event) {
      this.files2 = event.target.files[0]
    },
    getFile5: function (event) {
      this.files3 = event.target.files[0]
    },
    getFile6: function (event) {
      this.files4 = event.target.files[0]
    },
    submit2: function (event) {
      // 阻止元素发生默认的行为
      event.preventDefault()
      const _this = this
      const formData = new FormData()

      var list = []
      list[0] = this.files0
      list[1] = this.files1
      list[2] = this.files2
      list[3] = this.files3
      list[4] = this.files4
      for (var i = 0; i < 5; i++) {
        console.log('第' + i + '个')
        console.log(list[i])
      }
      for (var j = 0; j < 5; j++) {
        formData.append('file' + j, list[j])
      }
      formData.append('ptype', this.formDataPicture.picture.ptype)
      axios.post(this.api + '/picture/addMore', formData)
        .then(function (response) {
          // 后端return的数据在response中
          alert(response.data)
          _this.$router.push('/manager')
        })
        .catch(function (error) {
          alert('上传失败')
          console.log(error)
        })
    },

    getFile: function (event) {
      this.formDataPicture.file = event.target.files[0]
      console.log(this.file)
    },
    submit: function (event) {
      // 阻止元素发生默认的行为
      event.preventDefault()
      const _this = this
      const formData = new FormData()
      formData.append('file', this.formDataPicture.file)
      formData.append('ptitle', this.formDataPicture.picture.ptitle)
      formData.append('psize', this.formDataPicture.picture.psize)
      formData.append('ptype', this.formDataPicture.picture.ptype)
      formData.append('psearch', this.formDataPicture.picture.psearch)
      // formData.append('purl', this.formDataPicture.picture.purl)
      formData.append('pyear', this.formDataPicture.picture.pyear)
      formData.append('plength', this.formDataPicture.picture.plength)
      formData.append('author', this.formDataPicture.picture.author)
      console.log(formData)
      console.log(formData)
      axios.post(this.api + '/picture/add', formData)
        .then(function (response) {
          // 后端return的数据在response中
          alert(response.data)
          _this.$router.push('/manager')
        })
        .catch(function (error) {
          alert('上传失败')
          console.log(error)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
