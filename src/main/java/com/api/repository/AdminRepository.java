package com.api.repository;

import com.api.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {

    List<Admin> findByRole(String role);

    Optional<Admin> findAdminsByAccountName(String accountName);

}
