package com.api.service;

import com.api.model.entity.Prisoner;

import java.util.List;

public interface PrisonerService {

    Prisoner addPrisoner(Prisoner prisoner);

    List<Prisoner> findAllPrisoner();

    void deletePrisonerById(String id) throws Exception;
}
