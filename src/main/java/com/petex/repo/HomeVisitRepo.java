package com.petex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petex.entity.HomeVisitEntity;

public interface HomeVisitRepo extends JpaRepository<HomeVisitEntity, Integer>{

}
