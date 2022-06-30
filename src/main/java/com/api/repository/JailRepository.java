package com.api.repository;

import com.api.model.entity.Jail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JailRepository extends JpaRepository<Jail,String> {


}
