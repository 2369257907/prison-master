package com.api.service.Impl;

import com.api.model.entity.Admin;
import com.api.model.entity.Jail;
import com.api.model.entity.Prisoner;
import com.api.repository.JailRepository;
import com.api.service.JailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JailServiceImpl implements JailService {

    @Resource
    JailRepository jailRepository;

    //新增监狱信息
    @Override
    public Jail addJail(Jail jail){
        return jailRepository.save(jail);
    }

    //获取监狱信息
    @Override
    public List<Jail> findAllJail(){
        return jailRepository.findAll();
    }


}
