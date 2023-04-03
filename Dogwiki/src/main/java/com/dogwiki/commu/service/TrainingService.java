package com.dogwiki.commu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.repository.TrainingRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TrainingService {
	
	@Autowired
	private TrainingRepository trRepository;
	
	public String testService() {
		TrainingEntity entity = TrainingEntity.builder()
				.trProf("test")
				.trTitle("test1")
				.trDate(null)
				.trHit(0)
				.build();
		trRepository.save(entity);
		
		TrainingEntity savedEntity = trRepository
				.findById(entity.getTrId())
				.get();
		log.info(savedEntity.getTrId());
		log.info(savedEntity.getTrProf());
		log.info(savedEntity.getTrTitle());
		log.info(savedEntity.getTrDate());
		log.info(savedEntity.getTrHit());
		
		return savedEntity.getTrTitle();
	}
	
	public Page<TrainingEntity> selectAll(int pageNum, 
			int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, 
				Sort.by(sortBy).descending());
		return trRepository.findAll(pageable);
	}
	
	public long countTotal() {
		return trRepository.count();
	}
	
	public List<TrainingEntity> selectByTrProf(String trProf, 
			int pageNum, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, 
				Sort.by(sortBy).descending());
		return trRepository.findByTrProf(trProf, pageable);
	}
	
	public Optional<TrainingEntity> selectOne(Integer trId) {
		return trRepository.findById(trId);
	}

}
