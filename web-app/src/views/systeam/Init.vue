<template>
<div id="Init">
  <el-menu  class="el-menu-demo" mode="horizontal" style="margin-bottom: 50px;background-color: #1d1e72">
    <el-menu-item style="font-size: 20px ;margin-left: 50px;color: rgb(64, 158, 255);margin-right: 1000px;" >
      <i  style="font-size: 60px;margin-right: 30px ;color: rgb(64, 158, 255)"></i>
      监狱管理系统
    </el-menu-item>
    <el-menu-item style="font-size: 20px ;margin-left: 50px;color: rgb(64, 158, 255)">系统初始化</el-menu-item>
  </el-menu>
  <el-form :model="user" :rules="rules" ref="user" l-width="100px" class="demo-ruleForm" style="width: 30%;margin:0 auto; margin-bottom: 100px" >
    <el-form-item label="账号名" prop="accountName">
      <el-input v-model="user.accountName"></el-input>
    </el-form-item>
    <el-form-item label="真实姓名" prop="name">
      <el-input v-model="user.name"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="user.password"></el-input>
    </el-form-item>
    <el-form-item  prop="role">
      <el-input v-model="user.password" value="Role_user" v-show="false"></el-input>
    </el-form-item>
    <el-form-item label="电话号码" prop="phoneNumber">
      <el-input v-model="user.phoneNumber"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="resource">
      <el-radio-group v-model="user.sex">
        <el-radio label="男"></el-radio>
        <el-radio label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="家庭住址" prop="address">
      <el-input v-model="user.address"></el-input>
    </el-form-item>
    <el-form-item style="text-align: center">
      <el-button type="primary" @click="addAdmin('user')">确 定</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>

import {init} from "@/api/admin";

export default {
  name: "Init",
  data(){
    return{
      user:{
        "id": "",
        "accountName": "",
        "name": "",
        "password": "",
        "role": "",
        "phoneNumber": "",
        "sex": "",
        "address": ""
      },
        rules: {

      accountName: [
        { required: true, message: '请输入账号名', trigger: 'blur' }
      ],
          name: [
        { required: true, message: '请输入管理员姓名', trigger: 'blur' },
      ],
          password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ],
          phoneNumber: [
        { required: true, message: '请输入手机号码', trigger: 'blur' }
      ],
          sex: [
        { required: true, message: '请选择性别', trigger: 'blur' }
      ],
    }
    }
  },
  methods:{
    addAdmin(formName){
      this.dialogVisible2=false
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.user.role="Role_admin"
          init(this.user).then((res) =>{
            if (res.status) {
              this.$message({
                message: '系统初始化成功',
                type: 'success'
              });
            }
            this.$router.push('/login')
          })
        } else {
          alert('error submit!!');
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
#Init{
  min-width: 400px;
  min-height: 900px;
  background: url("https://tse1-mm.cn.bing.net/th/id/R-C.17ccf2941dd9f65f023507380b1da165?rik=%2bzp8%2fWDtIAAakw&riu=http%3a%2f%2fwww.uimaker.com%2fuploads%2fuserup%2f0%2f1413012235-43G.png&ehk=vhaGFw6qnT8C8tkoqMmDnf2zNlZmpqj430A7gamAupQ%3d&risl=&pid=ImgRaw&r=0");

}
</style>