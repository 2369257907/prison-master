import service from "@/utils/request";

export function FindAllPrisoner(){
    return service({
        url: '/prisoner',
        method: 'get'
    })
}

export function AddPrisoner(data){
    return service({
        url: '/prisoner',
        method: 'post',
        data: data
    })
}

export function UpdatePrisoner(data){
    return service({
        url: '/prisoner',
        method: 'put',
        data: data
    })
}

export function DeletePrisoner(id){
    return service({
        url: '/prisoner',
        method: 'delete',
        data: id
    })
}