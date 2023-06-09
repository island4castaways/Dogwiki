package com.dogwiki.commu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.TrainingData;
import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.repository.TrainingRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TrainingService {
	
	@Autowired
	private TrainingRepository trRepository;
		
	public Page<TrainingEntity> selectAll(int pageNum, 
			int pageSize, String sortBy, boolean start) {
		if(start) {
			TrainingData data = new TrainingData();
			List<String> kangUrl = data.readData(data.getKANG_URL(), "trUrl");
			List<String> kangTitle = data.readData(data.getKANG_URL(), "trTitle");
			for(int i = kangUrl.size() - 1; i >= 0; i--) {
				String tempUrl = kangUrl.get(i);
				if(!trRepository.existsByTrUrl(tempUrl)) {
					TrainingEntity en = TrainingEntity.builder()
							.trProf("강형욱")
							.trTitle(kangTitle.get(i))
							.trUrl(kangUrl.get(i))
							.build();
					trRepository.save(en);
				}
			}
			List<String> seolUrl = data.readData(data.getSEOL_URL(), "trUrl");
			List<String> seolTitle = data.readData(data.getSEOL_URL(), "trTitle");
			for(int i = seolUrl.size() - 1; i >= 0; i--) {
				String tempUrl = seolUrl.get(i);
				if(!trRepository.existsByTrUrl(tempUrl)) {
					TrainingEntity en = TrainingEntity.builder()
							.trProf("설채현")
							.trTitle(seolTitle.get(i))
							.trUrl(seolUrl.get(i))
							.build();
					trRepository.save(en);
				}
			}
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize, 
				Sort.by(sortBy).descending());
		return trRepository.findAll(pageable);
	}
	
	public List<TrainingEntity> selectByTrProf(String trProf, 
			int pageNum, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, 
				Sort.by(sortBy).descending());
		return trRepository.findByTrProf(trProf, pageable);
	}
	
	public List<TrainingEntity> selectByTrTitle(String search, 
			int pageNum, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).descending());
		Page<TrainingEntity> page = trRepository.getByTrTitle(search, pageable);
		List<TrainingEntity> returnList = page.getContent();
		return returnList;
	}
	
	public List<TrainingEntity> selectByTrProfAndTrTitle(
			String trProf, String search, 
			int pageNum, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).descending());
		Page<TrainingEntity> page = trRepository.getByTrProfAndTrTitle(trProf, search, pageable);
		List<TrainingEntity> returnList = page.getContent();
		return returnList;
	}
	
	public int countTotal() {
		return (int)trRepository.count();
	}
	
	public int countByTrProf(String trProf) {
		return (int)trRepository.countByTrProf(trProf);
	}
	
	public int countByTrTitle(String search) {
		return (int)trRepository.getCountByTrTitle(search);
	}
	
	public int countByTrProfAndTrTitle(String trProf, String search) {
		return (int)trRepository.getCountByTrProfAndTrTitle(trProf, search);
	}
	
	public Optional<TrainingEntity> selectOne(Integer trId) {
		return trRepository.findById(trId);
	}
	
	public TrainingEntity updateOne(TrainingEntity entity) {
		return trRepository.save(entity);
	}
	
	public TrainingEntity homeTraining() {
		return trRepository.findFirstByOrderByTrIdDesc();
	}

}
