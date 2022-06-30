<template>
<div id="Header">
  <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
  >
    <el-menu-item index="1" style="color: black;font-size: 20px">监狱管理系统</el-menu-item>
    <el-menu-item index="4" class="nowTime"> <span>{{newDate}}</span></el-menu-item>
    <el-submenu index="2">
      <template slot="title">设置</template>
      <el-menu-item index="2-1" @click="logout">退出登录</el-menu-item>
    </el-submenu>
  </el-menu>
</div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      // 当前时间
      newDate: new Date(),
      activeIndex: '1',
      activeIndex2: '1'

    }
  },
  mounted () {
    let that = this
    this.timer = setInterval(function () {
      that.newDate = new Date().toLocaleString()
    })
  },
// 销毁时清除计时器
  beforeDestroy: function () {
    if (this.timer) {
      clearInterval(this.timer)
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout(){
      this.$router.push('/login')
      this.$store.commit('user/userLogout')
    }
  }
}
</script>

<style scoped>
.nowTime{
  margin-left: 900px !important;
}
</style>