package com.dogwiki.commu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.entity.PictureEntity;

public interface PictureRepository extends JpaRepository<PictureEntity, Integer> {
	Page<PictureEntity> findALLByCategory(Integer category, Pageable pageable);
	
	@Query(value = "select p from PictureEntity p "
			+ "where p.title like %:search%")
	Page<PictureEntity> getListWithQuery(@Param("search")String search, Pageable pageable);
}
