package com.api.service.Impl;


import com.api.config.MyPasswordEncoder;
import com.api.model.entity.Admin;
import com.api.model.entity.Prisoner;
import com.api.repository.AdminRepository;
import com.api.repository.PrisonerRepository;
import com.api.service.PrisonerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrisonerServiceImpl implements PrisonerService {
    @Resource
    PrisonerRepository prisonerRepository;


    //新增犯人，修改犯人信息
    @Override
    public Prisoner addPrisoner(Prisoner prisoner){
        return prisonerRepository.save(prisoner);
    }

    //获取所有犯人信息
    @Override
    public List<Prisoner> findAllPrisoner(){
        return prisonerRepository.findAll();
    }

    //删除犯人
    @Override
    public void deletePrisonerById(String id) throws Exception {
        if (!prisonerRepository.findById(id).isPresent()){
            throw new Exception("要删除的犯人不存在");
        }
        prisonerRepository.deleteById(id);
    }
}
