package com.api.service;

import com.api.model.entity.Jail;

import java.util.List;

public interface JailService {

    Jail addJail(Jail jail);

    List<Jail> findAllJail();
}
