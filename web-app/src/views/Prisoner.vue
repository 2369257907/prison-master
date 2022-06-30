<template>
  <div id="Prisoner">
    <el-button @click="dialogVisible2=true" type="primary" v-if="isShowOperation">新增犯人</el-button>
    <el-table
        :data="prisonerList"
        stripe
        style="width: 100%">
      <el-table-column
          prop="name"
          label="姓名"
          width="100">
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄">
      </el-table-column>
      <el-table-column
          prop="idCard"
          label="身份证号">
      </el-table-column>
      <el-table-column
          prop="inTime"
          label="入狱时间">
      </el-table-column>
      <el-table-column
          prop="origin"
          label="籍贯">
      </el-table-column>
      <el-table-column
          prop="accusation"
          label="罪名">
      </el-table-column>
      <el-table-column
          prop="sentence"
          label="刑期（天）">
      </el-table-column>
      <el-table-column
          prop="inJai"
          label="是否在监狱中">
              <template slot-scope="scope">
                <el-tag :type="scope.row.inJai === true ? 'success' : 'danger'"> {{ scope.row.inJai === true ? '在狱中' : '出狱' }}</el-tag>
              </template>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="150"
          v-if="isShowOperation"
      >
        <template slot-scope="scope">
          <el-button @click="deletePrisoner(scope.row.id)" type="text" size="small">删除</el-button>
          <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="text" @click="changeStatus(scope.row)">{{ scope.row.inJai === true ? '出狱' : '入狱' }} </el-button>
          <el-button type="text" @click="realPosition(scope.row)">实时定位 </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
        title="新增犯人"
        :visible.sync="dialogVisible2"
        width="30%">
      <el-form :model="prisoner" :rules="rules" ref="prisoner" l-width="100px" class="demo-ruleForm">

        <el-form-item label="真实姓名" prop="name">
          <el-input v-model="prisoner.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="prisoner.age"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="origin">
          <el-input v-model="prisoner.origin"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="resource">
          <el-radio-group v-model="prisoner.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="罪名" prop="accusation">
          <el-input v-model="prisoner.accusation"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="prisoner.idCard"></el-input>
        </el-form-item>
        <el-form-item label="入狱时间" prop="inTime">
          <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期"
                          v-model="prisoner.inTime"></el-date-picker>
        </el-form-item>
        <el-form-item label="刑期(天)" prop="sentence">
          <el-input v-model="prisoner.sentence"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible2 = false">取 消</el-button>
    <el-button type="primary" @click="AddPrisoner('prisoner')">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog
        title="编辑犯人信息"
        :visible.sync="dialogVisible"
        width="30%">
      <el-form :model="prisoner" :rules="rules" ref="prisoner" l-width="100px" class="demo-ruleForm">

        <el-form-item label="真实姓名" prop="name">
          <el-input v-model="prisoner.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="prisoner.age"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="origin">
          <el-input v-model="prisoner.origin"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="resource">
          <el-radio-group v-model="prisoner.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="罪名" prop="accusation">
          <el-input v-model="prisoner.accusation"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="prisoner.idCard"></el-input>
        </el-form-item>
        <el-form-item label="入狱时间" prop="inTime">
          <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期"
                          v-model="prisoner.inTime"></el-date-picker>
        </el-form-item>
        <el-form-item label="刑期(天)" prop="sentence">
          <el-input v-model="prisoner.sentence"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updatePrisoner('prisoner')">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {FindAllPrisoner, AddPrisoner, UpdatePrisoner, DeletePrisoner} from "@/api/prisoner";

export default {
  name: "Prisoner",
  data() {
    return {
      dialogVisible: false,
      dialogVisible2: false,
      isShowOperation: false,
      prisonerList: [],
      prisoner: {
        "id": "",
        "name": "",
        "age": "",
        "sex": "",
        "origin": "",
        "idCard": "",
        "accusation": "",
        "inTime": "",
        "sentence": "",
        "inJai": true
      },
      rules: {
        name: [
          {required: true, message: '请输入囚犯姓名', trigger: 'blur'}
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'blur'}
        ],
        origin: [
          {required: true, message: '请输入籍贯', trigger: 'blur'}
        ],
        accusation: [
          {required: true, message: '请输入罪名', trigger: 'blur'}
        ],
        idCard: [
          {required: true, message: '请输入身份证号', trigger: 'blur'}
        ],
        inTime: [
          {required: true, message: '请选择入狱时间', trigger: 'blur'}
        ],
        sentence: [
          {required: true, message: '请输入刑期', trigger: 'blur'}
        ],
      }
    }
  },
  mounted() {
    this.loadTableData();
    this.checkIsShowOperation()
  },
  methods: {
    checkIsShowOperation(){
      var role = this.$store.state.user.role;
      if (role ==="Role_admin"){
        this.isShowOperation=true;
      }
    },
    loadTableData() {
      FindAllPrisoner().then((res) => {
        if (res.status) {
          this.prisonerList = res.data
          console.log(res.data)
        }
        setTimeout(() => {
        }, 600)
      })
    },
    AddPrisoner(formName) {
      this.dialogVisible2 = false
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.prisoner)
          AddPrisoner(this.prisoner).then((res) => {
            if (res.status) {
              this.$message({
                message: '添加犯人成功',
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
    handleEdit(row) {
      this.prisoner = row
      this.dialogVisible=true
      console.log(row)
    },
    updatePrisoner(formName){
      this.dialogVisible = false
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.prisoner)
          UpdatePrisoner(this.prisoner).then((res) => {
            if (res.status) {
              this.$message({
                message: '修改犯人信息成功',
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
    deletePrisoner(id){
      this.$confirm('此操作将删除该犯人，是否确定', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        DeletePrisoner(id).then((res) => {
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
    changeStatus(row){
      this.prisoner = row
      var msg = this.prisoner.inJai ===true ? "出狱成功":"入狱成功"
      this.prisoner.inJai=!this.prisoner.inJai

      UpdatePrisoner(this.prisoner).then((res) => {
        if (res.status) {
          this.$message({
            message: msg,
            type: 'success'
          });
          this.loadTableData()
        }
      })
    },
    realPosition(row){
      this.$router.push('/realposition')
    }

  }
}
</script>

<style scoped>

</style>