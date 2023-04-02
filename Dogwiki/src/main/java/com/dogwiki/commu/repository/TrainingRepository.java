package com.dogwiki.commu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwiki.commu.entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer> {
	
	List<TrainingEntity> findByTrProf(String trProf, Pageable pageable);

}
