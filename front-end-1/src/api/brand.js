import request from '@/utils/request'

export function getBrands(pageNum) {
    return request({
        url: '/brand/all',
        method: 'get',
        params: { pageNum }
    })
}

export function getBrandById(id) {
    return request({
        url: '/brand/get',
        method: 'get',
        params: { id }
    })
}

export function uaddBrand(form) {
    form.categoryId = form.categoryId[2]
    return request({
        url: '/brand/add',
        method: 'post',
        params: form
    })
}

export function editBrand(form) {
    return request({
        url: '/brand/edit',
        method: 'post',
        data: form
    })
}

export function deleteBrand(data) {
    // form.categoryId = form.categoryId[2]
    return request({
        url: '/brand/delete',
        method: 'post',
        data: data
    })
}