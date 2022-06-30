package com.api.repository;

import com.api.model.entity.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrisonerRepository extends JpaRepository<Prisoner,String> {

}
