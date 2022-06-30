const state = {
    token: localStorage.getItem("satoken"),
    role: localStorage.getItem("role"),
}


const mutations = {

    //保存用户token
    saveToken(state, token) {
        state.token = token
        localStorage.setItem("satoken", token)
    },

    saveRole(state, role) {
        state.role = role
        localStorage.setItem("role", role)
    },

    userLogout(state) {
        state.details = null
        state.token = null
        localStorage.removeItem("satoken")
        localStorage.removeItem("role")
    }

}

const actions = {}


export default {
    namespaced: true,
    state,
    getters:{
        getRole: function (state) {
            return state.role;  //返回当前用户角色
        }
    },
    mutations,
    actions
}