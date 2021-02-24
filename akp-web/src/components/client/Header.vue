<template>
  <div style="text-align: right;background: #3d8de6">
    <el-input v-model="input" placeholder="搜索图片" style="width: 250px"></el-input>
    <el-button type="primary">搜索</el-button>
  </div>
</template>

<script>

export default {
  data () {
    return {
      input: ''
    }
  }
}

</script>

<style lang="scss" scoped>

</style>
