import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'


export const constantRoutes = [{
        path: '/login',
        component: () =>
            import('@/views/login/index'),
        hidden: true
    },

    {
        path: '/404',
        component: () =>
            import ('@/views/404'),
        hidden: true
    },

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [{
            path: 'dashboard',
            name: 'Dashboard',
            component: () =>
                import ('@/views/dashboard/index'),
            meta: { title: '首页', icon: 'dashboard' }
        }]
    },

    {
        path: '/brand',
        component: Layout,
        redirect: '/brand/list',
        name: 'Example',
        meta: { title: '品牌管理', icon: 'el-icon-s-help' },
        children: [{
                path: 'list',
                name: 'list',
                component: () =>
                    import ('@/views/brand/index'),
                meta: { title: '品牌列表', icon: 'table' }
            },
            {
                path: 'add',
                name: 'add',
                component: () =>
                    import ('@/views/brand/add'),
                meta: { title: '添加品牌', icon: 'tree' }
            },
            {
                path: 'edit/:id',
                name: 'edit',
                hidden: true,
                component: () =>
                    import ('@/views/brand/edit'),
                meta: { title: '编辑品牌', icon: 'tree' }
            }
        ]
    },
    {
        path: '/category',
        component: Layout,
        redirect: '/category/list',
        name: 'category',
        meta: { title: '分类管理', icon: 'el-icon-s-help' },
        children: [{
            path: 'list',
            name: 'category',
            component: () =>
                import ('@/views/category/index'),
            meta: { title: '分类列表', icon: 'table' }
        }]
    },



    // 404 page must be placed at the end !!!
    { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router