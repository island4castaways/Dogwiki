package com.dogwiki.commu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwiki.commu.entity.PictureEntity;

public interface PictureRepository extends JpaRepository<PictureEntity, Integer> {
	Page<PictureEntity> findALLByCategory(Integer category, Pageable pageable);
}
