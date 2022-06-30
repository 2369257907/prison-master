<template>
<div id="Warn">
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
      <!--              <template slot-scope="{ row }">-->
      <!--                {{ row.inJai === true ? '在狱中' : '出狱' }}-->
      <!--              </template>-->
      <template slot-scope="scope">
        <el-tag :type="scope.row.inJai === true ? 'success' : 'danger'"> {{ scope.row.inJai === true ? '在狱中' : '出狱' }}</el-tag>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
import {FindAllPrisoner} from "@/api/prisoner";

export default {
  name: "Warn",
  data(){
    return{
      prisonerList: [],
    }
},
  mounted() {
    this.loadTableData();
  },
  methods: {
    loadTableData() {
      FindAllPrisoner().then((res) => {
        if (res.status) {
          // this.prisonerList = res.data
          console.log(res.data)
          for (var i=0;i<res.data.length;i++){
            if (res.data[i].inJai ===false){
              this.prisonerList.push(res.data[i]);
            }
          }
        if (this.prisonerList.length!==0){
          this.$message({
            message: '警告!当前有'+this.prisonerList.length+"名犯人不在监狱",
            type: 'warning'
          });
        }
          console.log(this.prisonerList)
        }
        setTimeout(() => {
        }, 600)
      })
    }
  }
}
</script>

<style scoped>

</style>