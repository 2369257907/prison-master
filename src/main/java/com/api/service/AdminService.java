package com.api.service;

import com.api.model.dto.LoginDto;
import com.api.model.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    Admin saveAdmin(Admin admin) throws Exception;

    List<Admin> findAdminByRole(String role);

    void deleteUserById(String id) throws Exception;

    Map<String, Object> doLogin(LoginDto loginDto) throws Exception;

    Admin findAdminByID(String id) throws Exception;
}
