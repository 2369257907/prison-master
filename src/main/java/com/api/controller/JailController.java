package com.api.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.api.model.entity.Admin;
import com.api.model.entity.Jail;
import com.api.model.entity.Prisoner;
import com.api.service.JailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/jail")
public class JailController {

    @Resource
    JailService jailService;
    //新增监狱,更新监狱信息
    @PostMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public Jail addJail(@RequestBody Jail jail){
        System.out.println(jail);
        return jailService.addJail(jail);
    }


    //获取监狱信息
    @GetMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public Jail findjail() throws Exception {
        List<Jail> jails = jailService.findAllJail();
        if (jails.size()==0){
            throw new Exception("暂无监狱信息");
        }else {
            return jails.get(0);
        }
    }
}
