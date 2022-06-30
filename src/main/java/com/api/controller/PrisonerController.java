package com.api.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.api.model.entity.Admin;
import com.api.model.entity.Prisoner;
import com.api.repository.PrisonerRepository;
import com.api.service.AdminService;
import com.api.service.PrisonerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/prisoner")
public class PrisonerController {

    @Resource
    PrisonerService prisonerService;

    @Resource
    PrisonerRepository prisonerRepository;

    //新增犯人,更新犯人信息
    @PostMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public Prisoner addPrisoner(@RequestBody Prisoner prisoner){
        System.out.println(prisoner);
        return prisonerService.addPrisoner(prisoner);
    }

    //获取所有犯人信息
    @GetMapping("")
    @SaCheckRole(value = {"Role_admin","Role_user"}, mode = SaMode.OR)
    public List<Prisoner> findAllPrisoner(){
        return prisonerService.findAllPrisoner();
    }

    //删除犯人
    @DeleteMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public void deletePrisoner(@RequestBody String id) throws Exception {
        System.out.println(id);
        prisonerService.deletePrisonerById(id);
    }

    //修改犯人信息
    @PutMapping("")
    @SaCheckRole(value = {"Role_admin"}, mode = SaMode.OR)
    public Prisoner updatePrisoner(@RequestBody Prisoner prisoner) throws Exception {
        System.out.println(prisoner);
        if (!prisonerRepository.findById(prisoner.getId()).isPresent()){
            throw new Exception("要修改的犯人信息不存在");
     }
        return prisonerService.addPrisoner(prisoner);
    }

}
