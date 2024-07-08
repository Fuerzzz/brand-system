<template>
  <div class="app-container">
    <el-tree
    :props="props"
    :load="loadNode"
    lazy
    show-checkbox>
  </el-tree>

  </div>
  
</template>

<script>
  import {getCategories,getCategoriesByParentId} from '@/api/category.js'

  export default {
    data() {
      return {
        props: {
          label: 'name',
          children: 'zones',
          isLeaf: 'leaf'
        },
      };
    },
    created:function(){

    },
    methods: {
      loadNode(node, resolve) {
        if (node.level === 0) {       getCategories().then(res=>{
            resolve(res.data.items)
          })
        }
        if (node.level > 0){
          getCategoriesByParentId(node.data.id).then(res=>{
              resolve(res.data.items)
          })
        };
      }
    }
  };
</script>
