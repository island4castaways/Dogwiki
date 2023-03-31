package com.dogwiki.commu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwiki.commu.entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer> {

}
