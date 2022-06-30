package com.api.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.api.model.entity.Admin;
import com.api.service.AdminService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SatokenConfig implements StpInterface {

    @Resource
    AdminService adminService;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @SneakyThrows
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 查询用户角色角色
        List<String> list = new ArrayList<String>();
        String uid = (String) StpUtil.getLoginId();
        Admin admin  = adminService.findAdminByID(uid);
        list.add(admin.getRole());
        return list;
    }


}
