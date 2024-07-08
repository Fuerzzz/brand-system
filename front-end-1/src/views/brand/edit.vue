<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="品牌名称">
        <el-input v-model="form.name"/>
      </el-form-item>
      <el-form-item label="品牌图片">
        <!-- 上传图片组件 action为接收上传的后端地址 -->
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/brand/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          name="img"
        >
          <img v-if="form.image" :src="form.image" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="品牌字母">
        <el-input v-model="form.letter"></el-input>
      </el-form-item>

      <el-form-item label="所属分类">
        <el-cascader :props="props" v-model="form.categoryId"></el-cascader>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {getCategories,getCategoriesByParentId} from '@/api/category'
import {editBrand,getBrandById} from '@/api/brand'
export default {
  data() {
    return {
      form:{
        name:"",
        letter:"",
        image: "",
        categoryId:"",
      },
      props: {
        lazy: true,
        lazyLoad: this.lazyLoad,
      },
      id:""
    };
  },
  created(){
    if(this.$route.params.id){
      this.id = this.$route.params.id
      getBrandById(this.id).then(res=>{
        this.form = res.data.brand;
      })
    }
  },
  methods: {
    onSubmit() {
      editBrand({...this.form,id:this.id}).then((res) => {
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.$router.push("/brand/list");
      });
    },
    handleAvatarSuccess(res) {
      this.form.image = "http://localhost:8080/" + res;
    },
    beforeAvatarUpload() {},
    lazyLoad(node, resolve) {
      const { level } = node;
      if(level == 0){
        getCategories().then(res=>{
          const categories = res.data.items.map(item=>{
            return {
              value: item.id,
              label: item.name,
              leaf: false,
            }
          })
          resolve(categories);
        })
      // 子节点
      }else{
        getCategoriesByParentId(node.data.value).then(res=>{
          const categories = res.data.items.map(item=>{
            return {
              value: item.id,
              label: item.name,
              leaf: level >= 2,
            }
          })
          resolve(categories);
        })
      }
    },
  },
};
</script>


<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>