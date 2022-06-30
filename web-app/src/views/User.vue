<template>
<div id="User">
  <el-button @click="dialogVisible2=true"  type="primary">新增操作员</el-button>
  <el-table
      :data="userList"
      stripe
      style="width: 100%">
    <el-table-column
        prop="accountName"
        label="账号名称"
        width="180">
    </el-table-column>
    <el-table-column
        prop="name"
        label="真实姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="sex"
        label="性别"
        width="180">
    </el-table-column>
    <el-table-column
        prop="address"
        label="家庭住址"
        width="180">
    </el-table-column>
    <el-table-column
        prop="phoneNumber"
        label="手机号码">
    </el-table-column>
    <el-table-column
        prop="role"
        label="角色">
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="100">
      <template slot-scope="scope">
        <el-button @click="deleteUser(scope.row.id)" type="text" size="small" >删除</el-button>
        <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
      title="修改操作员信息"
      :visible.sync="dialogVisible2"
      width="30%"
      :before-close="handleClose">
    <el-form :model="user" :rules="rules" ref="user" l-width="100px" class="demo-ruleForm">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="user.name"></el-input>
      </el-form-item>
      <el-form-item label="电话号码" prop="name">
        <el-input v-model="user.phoneNumber"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="name">
        <el-input v-model="user.sex"></el-input>
      </el-form-item>
      <el-form-item label="家庭住址" prop="name">
        <el-input v-model="user.address"></el-input>
      </el-form-item>
    </el-form>    <span slot="footer" class="dialog-footer">
    <el-button @click="resetForm('user')">取 消</el-button>
    <el-button type="primary" @click="submitForm('user')">确 定</el-button>
  </span>
  </el-dialog>

  <el-dialog
      title="新增操作员"
      :visible.sync="dialogVisible2"
      width="30%">
    <el-form :model="user" :rules="rules" ref="user" l-width="100px" class="demo-ruleForm">
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
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible2=false">取 消</el-button>
    <el-button type="primary" @click="addUser('user')">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
import {FindAllUser,UpdateUser,SaveUser,DeleteUser} from "@/api/admin";

export default {
  name: "User",
  data() {
    return {
      dialogVisible: false,
      dialogVisible2: false,
      userList:[],
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
          { required: true, message: '请输入操作员姓名', trigger: 'blur' },
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
  mounted() {
    this.loadTableData()
  },
  methods:{
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    loadTableData() {
      FindAllUser().then((res) => {
        if (res.status) {
          this.userList=res.data
          console.log(res.data)
        }
      })
    },
    handleClick(row) {
      console.log(row);
    },
    handleEdit(row){
      this.user =row;
      this.dialogVisible=true;
    },
    submitForm(formName) {
      this.dialogVisible=false
      this.$refs[formName].validate((valid) => {
        if (valid) {
          UpdateUser(this.user).then((res) =>{
            if (res.status) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.loadTableData()
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      this.resetForm("user")
    },
    addUser(formName){
      this.dialogVisible2=false
      this.$refs[formName].validate((valid) => {
        if (valid) {
            this.user.role="Role_user"
            SaveUser(this.user).then((res) =>{
            if (res.status) {
              this.$message({
                message: '添加操作员成功',
                type: 'success'
              });
              this.loadTableData()
            }
          })
        } else {
          alert('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.dialogVisible=false;
      this.$refs[formName].resetFields();
    },
    deleteUser(id){
      this.$confirm('此操作将删除该操作员，是否确定', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        DeleteUser(id).then((res) => {
          console.log("id是"+id)
          if (res.status) {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.loadTableData()
            console.log(res.data)
          }
          setTimeout(() => {
          }, 600)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

  }
}
</script>

<style scoped>

</style>