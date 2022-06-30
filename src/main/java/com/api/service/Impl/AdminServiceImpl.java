package com.api.service.Impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.api.config.MyPasswordEncoder;
import com.api.model.dto.LoginDto;
import com.api.model.entity.Admin;
import com.api.repository.AdminRepository;
import com.api.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminRepository adminRepository;

    @Resource
    MyPasswordEncoder passwordEncoder;

    //新增用户
    @Override
    public Admin saveAdmin(Admin admin) throws Exception {

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    //根据role查询用户信息
    @Override
    public List<Admin> findAdminByRole(String role){
        return adminRepository.findByRole(role);
    }

    //根据id删除用户
    @Override
    public void deleteUserById(String id) throws Exception {
        if (!adminRepository.findById(id).isPresent()){
            throw new Exception("要删除的用户不存在");
        }
        adminRepository.deleteById(id);
    }

    //根据id查找用户
    @Override
    public Admin findAdminByID(String id) throws Exception {
        if (adminRepository.findById(id).isPresent()){
            return adminRepository.findById(id).orElse(null);
        }else {
            throw new Exception("该用户不存在");
        }
    }

    //校验登录，登录成功赋予token
    @Override
    public Map<String, Object> doLogin(LoginDto loginDto) throws Exception {
        Optional<Admin> user= adminRepository.findAdminsByAccountName(loginDto.getAccountName());

        if (user.isPresent()){
//            if (passwordEncoder.matches(loginDto.getPassword(),user.get().getPassword()))
//            if (user.get().getPassword().equals(loginDto.getPassword())){
            if (passwordEncoder.matches(loginDto.getPassword(),user.get().getPassword())){
                System.out.println("授权登录");
                StpUtil.login(user.get().getId());
                System.out.println(StpUtil.getTokenInfo());
                SaTokenInfo token = StpUtil.getTokenInfo();

                HashMap<String,Object> map = new HashMap<>(4) ;
                String tokenValue = token.getTokenValue();
                String loginId = (String) token.getLoginId();

                map.put("tokenName",token.getTokenName());
                map.put("tokenValue",tokenValue);
                map.put("loginId",loginId);
                map.put("role",user.get().getRole());
                return map;
            }
        }
        throw new Exception("账号或密码错误");
    }
}
