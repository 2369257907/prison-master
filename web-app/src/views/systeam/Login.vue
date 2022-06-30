<template>
  <div id="Login">
    <el-menu  class="el-menu-demo" mode="horizontal" style="margin-bottom: 50px;background-color: #1d1e72" >
      <el-menu-item style="font-size: 20px ;margin-left: 50px;color: rgb(64, 158, 255);margin-right: 1000px">
        <i  style="font-size: 60px;margin-right: 30px ;color: #045197z"></i>
        监狱管理系统
      </el-menu-item>
      <el-menu-item style="font-size: 20px ;margin-left: 50px;color: rgb(64, 158, 255)">用户登录</el-menu-item>
    </el-menu>
    <div class="formbox">
      <el-form :model="form"  status-icon :rules="rules" ref="form" label-width="100px" class="demo-ruleForm"  style="width: 400px">
        <el-form-item label="账号" prop="accountName">
          <el-input  v-model="form.accountName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" autocomplete="off" @keyup.enter.native="submitForm('form')"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 80px">
          <el-button type="primary" @click="submitForm('form')" style="text-align: center">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {submitLogin} from "@/api/admin";

export default {
  name: "Login",
  data(){
    return{
      form: {
        accountName: '',
        password: '',
      },
      rules: {
        accountName: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      },
    }
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          submitLogin(this.form).then((res) => {
            if (res.status) {

              console.log("tokenName: "+ res.data.tokenName)
              console.log("tokenValue: "+ res.data.tokenValue)
              console.log("role: " + res.data.role)

              this.$store.commit('user/saveRole', res.data.role)
              this.$store.commit('user/saveToken', res.data.tokenValue)

              //获取store的内容的格式
              console.log(this.$store.state.user.role)
              console.log(this.$store.state.user.token)

              this.$message({
                message: '登录成功',
                type: 'success'
              });
              this.$router.push('/')
              setTimeout(() => {
              }, 800)
            }
          })
          console.log(this.form)

        } else {
          alert('error submit!!')
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>
.formbox{
  position: absolute;
  left:50%;
  top:50%;
  transform: translate(-50%, -50%);
}
#Login{
  min-width: 400px;
  min-height: 900px;
}
#Login{
  width: 100%;
  height: 100%;
  background: url("https://tse1-mm.cn.bing.net/th/id/R-C.17ccf2941dd9f65f023507380b1da165?rik=%2bzp8%2fWDtIAAakw&riu=http%3a%2f%2fwww.uimaker.com%2fuploads%2fuserup%2f0%2f1413012235-43G.png&ehk=vhaGFw6qnT8C8tkoqMmDnf2zNlZmpqj430A7gamAupQ%3d&risl=&pid=ImgRaw&r=0");
}
</style>