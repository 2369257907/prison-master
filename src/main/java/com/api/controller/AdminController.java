package com.api.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.api.annotation.DisableBaseResponse;
import com.api.config.MyPasswordEncoder;
import com.api.model.dto.LoginDto;
import com.api.model.entity.Admin;
import com.api.repository.AdminRepository;
import com.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @Autowired
    MyPasswordEncoder passwordEncoder;

    @Resource
    AdminRepository adminRepository;

    //新增操作员
    @PostMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public Admin addUser(@RequestBody Admin admin) throws Exception {
        if (adminRepository.findAdminsByAccountName(admin.getAccountName()).isPresent()){
            throw new Exception("该用户名已经被使用");
        }
        System.out.println(admin);
        return adminService.saveAdmin(admin);
    }

    //修改操作员信息
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    @PutMapping("")
    public Admin updateUser(@RequestBody Admin admin) throws Exception {
        if (!adminRepository.findAdminsByAccountName(admin.getAccountName()).isPresent()){
            throw new Exception("要修改的用户不存在");
        }
        System.out.println(admin);
        return adminService.saveAdmin(admin);
    }

    //系统初始化，新增管理员
    @PostMapping("/init")
    public Admin addAdmin(@RequestBody Admin admin) throws Exception {

        //系统只有一个管理员
        if (adminService.findAdminByRole("Role_admin").size()==0){
            return adminService.saveAdmin(admin);
        }else {
            throw new Exception("管理员已经存在了");
        }
    }

    //获取所有操作员信息
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    @GetMapping("")
    public List<Admin> findAllUser(){
        return adminService.findAdminByRole("Role_user");
    }

    //删除用户
    @DeleteMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public void deleteUser(@RequestBody String id) throws Exception {
        System.out.println(id);
        adminService.deleteUserById(id);
    }


    //用户登录
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDto loginDto) throws Exception {
        System.out.println(loginDto);
        return adminService.doLogin(loginDto);
    }



}
