<template>
<div id="Aside">
  <el-row class="tac">
    <el-col :span="24">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          :router="true"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="min-height: 800px"
      >
        <el-submenu v-for="(item, index) in menus" :index=index.toString() v-show="item.isShow">
          <template slot="title">
            <i :class="item.icon"></i>
            <span>{{ item.title }}</span>
          </template>
          <el-menu-item v-for="menu in item.children" :index="menu.path" v-show="menu.isShow">
            {{menu.title}}
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-col>
  </el-row></div>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return {
      menus: [
        {
          title: '人员管理',
          icon: 'el-icon-user-solid',
          children: [
            {title: '操作员管理', path: '/user',showRoles: ["Role_admin"],isShow:false},
            {title: '犯人管理', path: '/prisoner',showRoles: ["Role_user", "Role_admin"],isShow:false},
          ],
          showRoles: ["Role_user", "Role_admin"],
          isShow: true,
        },
        {
          title: '报警管理',
          icon: 'el-icon-s-order',
          children: [
            {title: '报警信息', path: '/warn',showRoles: ["Role_user", "Role_admin"],isShow:false},
          ],
          showRoles: ["Role_user","Role_admin"],
          isShow: true,
        },
        {
          title: '监狱信息管理',
          icon: 'el-icon-s-home',
          children: [
            {title: '监狱信息', path: '/jailInfo',isShow:true,showRoles: ["Role_user", "Role_admin"]},
          ],
          showRoles: ["Role_user","Role_admin"],
          isShow: true,
        },
      ],
    }
  },
  mounted() {
    console.log("角色"+this.$store.state.user.role)
    this.chekIsShow()
  },
  methods: {
    chekIsShow(){
      var role = this.$store.state.user.role;
      for (var i=0;i<this.menus.length;i++){
        if (this.menus[i].showRoles.includes(role)){this.menus[i].isShow=true}
        for (var k=0;k<this.menus[i].children.length;k++){
          if (this.menus[i].children[k].showRoles.includes(role)){
            this.menus[i].children[k].isShow=true;
          }
        }
      }
    },
  }
}
</script>

<style scoped>
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>