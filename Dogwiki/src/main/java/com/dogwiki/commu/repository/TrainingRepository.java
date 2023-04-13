package com.dogwiki.commu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dogwiki.commu.entity.TrainingEntity;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer> {
	
	Boolean existsByTrUrl(String url);
	
	List<TrainingEntity> findByTrProf(String trProf, Pageable pageable);
	
	@Query("SELECT tr FROM TrainingEntity tr WHERE tr.trTitle LIKE %:search%")
	Page<TrainingEntity> getByTrTitle(@Param("search") String search, Pageable pageable);
	
	@Query("SELECT tr FROM TrainingEntity tr WHERE tr.trProf=:trProf AND tr.trTitle LIKE %:search%")
	Page<TrainingEntity> getByTrProfAndTrTitle(@Param("trProf") String trProf, @Param("search") String search, Pageable pageable);
	
	long countByTrProf(String trProf);
	
	@Query("SELECT count(tr) FROM TrainingEntity tr WHERE tr.trTitle LIKE %:search%")
	long getCountByTrTitle(@Param("search") String search);
	
	@Query("SELECT count(tr) FROM TrainingEntity tr WHERE tr.trProf=:trProf AND tr.trTitle LIKE %:search%")
	long getCountByTrProfAndTrTitle(@Param("trProf") String trProf, @Param("search") String search);

}
