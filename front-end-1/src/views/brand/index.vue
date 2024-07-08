<template>
  <div class="app-container">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="ID" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column label="名称" width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>首字母: {{ scope.row.letter }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!-- 点击按钮时，将id传入方法 -->
          <el-button size="mini" @click="handleEdit(scope.row.id)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @current-change="changePage"
      v-if="isShow"
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
    >
    </el-pagination>
  </div>
</template>

<script>
import { getBrands, editBrand, deleteBrand } from "@/api/brand.js";
import {getBrandById} from '@/api/brand'
export default {
  data() {
    return {
      form:{
        name:"",
        letter:"",
        image: "",
        categoryId:"",
      },
      total: 1,
      pageSize:1,
      // 默认不显示分页
      isShow: false,
      tableData: [],
    };
  },
  created: function () {
    getBrands().then((response) => {
      this.tableData = response.data.items.records;
      this.total = response.data.items.total;
      this.pageSize = response.data.items.size;
      this.isShow = true;
    });
  },
  methods: {
    handleEdit(id) {
      console.log(id);
      this.$router.push("/brand/edit/" + id);
    },
    handleDelete(id) {
      deleteBrand({id:id}).then(res=>{
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      })
     
    },
    changePage(pageNum) {
      getBrands(pageNum).then((response) => {
        this.tableData = response.data.items.records;
    });
    },
  },
};
</script>
