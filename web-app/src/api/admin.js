import service from "@/utils/request";


//登录
export function submitLogin(data){
    return service({
        url: '/admin/login/',
        method: 'post',
        data: data
    })
}

//查询所有操作员信息
export function FindAllUser(){
    return service({
        url: '/admin',
        method: 'get'
    })
}

//删除操作员
export function DeleteUser(id){
    return service({
        url: '/admin',
        method: 'delete',
        data:id
    })
}

//新增操作员
export function SaveUser(data) {
    return service({
        url: '/admin',
        method: 'post',
        data: data
    })
}

//修改操作员信息
export function UpdateUser(data) {
    return service({
        url: '/admin',
        method: 'put',
        data: data
    })
}

//新增操作员，系统初始化
export function init(data) {
    return service({
        url: '/admin/init',
        method: 'post',
        data: data
    })
}




