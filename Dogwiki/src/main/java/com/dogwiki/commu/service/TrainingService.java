package com.dogwiki.commu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.repository.TrainingRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TrainingService {
	
	@Autowired
	private TrainingRepository tr_repository;
	
	public String testService() {
		TrainingEntity entity = TrainingEntity.builder()
				.trProf("test")
				.trTitle("test1")
				.trDate(null)
				.trHit(0)
				.build();
		tr_repository.save(entity);
		
		TrainingEntity savedEntity = tr_repository
				.findById(entity.getTrId())
				.get();
		log.info(savedEntity.getTrId());
		log.info(savedEntity.getTrProf());
		log.info(savedEntity.getTrTitle());
		log.info(savedEntity.getTrDate());
		log.info(savedEntity.getTrHit());
		
		return savedEntity.getTrTitle();
	}
	
	public Page<TrainingEntity> selectAll(Pageable pageable) {
		return tr_repository.findAll(pageable);
	}

}
