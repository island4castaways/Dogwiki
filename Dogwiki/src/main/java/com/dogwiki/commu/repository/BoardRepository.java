package com.dogwiki.commu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogwiki.commu.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	
	List<BoardEntity> findALLByCategory(Integer category);
	
	Page<BoardEntity> findALLByCategory(Integer category, Pageable pageable);
	
	@Query(value = "select b from Board b "
			+ "where b.category = :category and b.title like %:search%")
	Page<BoardEntity> getListWithQuery(@Param("category") int category,@Param("search")String search, Pageable pageable);
	

	@Query(value = "select b from Board b "
			+"where b.title like %:search%")
	Page<BoardEntity> getListWithQuery(@Param("search")String search, Pageable pageable);
}
