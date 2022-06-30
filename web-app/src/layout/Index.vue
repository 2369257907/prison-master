<template>
  <div id="Index">
    <el-container>
      <el-header style="margin-bottom: 10px"><Header></Header></el-header>
      <el-container>
        <el-aside width="300px"><Aside></Aside></el-aside>
        <el-container>
          <el-main><Main></Main></el-main>
          <el-footer>     <Footer></Footer>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Footer from "@/layout/Footer";
import Aside from "@/layout/Aside";
import Main from "@/layout/Main";
import Header from "@/layout/Header";

export default {
  name: "Index",
  components: {
    Footer,
    Aside,
    Main,
    Header
  },
}
</script>

<style scoped>
el-main{
  height: 1000px;
}
.el-header{
  padding: 0 !important;
}
</style>