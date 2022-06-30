import request from "axios"
import { Message } from 'element-ui'


const tokenValue = localStorage.getItem("satoken")
const service = request.create({
    baseURL: 'http://localhost:8086/api',
    timeout: 50000,
    //不使用vue自带的store的原因是：有时会出现token数据刷新不及时的情况
    // headers: {'satoken': tokenValue}
    headers: {'satoken': localStorage.getItem("satoken")}
});

service.interceptors.response.use(
    response => {
        const res = response.data;
        console.log(res)
        //判断response状态
        if (!res.status) {
            if (res.msg!=null){
                Message.error(res.msg)
            }else {
                Message.error('请求失败');
            }
        }

        // if (res.code === 403) router.push("/403")

        return res
    },

    error => {
        alert("请求错误")
        message.error(error)
        // router.push('/500')
        return Promise.reject(error)
    }
);

export default service