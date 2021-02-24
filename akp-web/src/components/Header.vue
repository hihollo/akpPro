<template>
 <div>
   <span>{{msg}}</span>
 </div>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      msg: '图片管理主页'
    }
  }
}
</script>

<style lang="scss" scoped>
span{
  font-weight: bold;
  font-size: 25px;
}
</style>
