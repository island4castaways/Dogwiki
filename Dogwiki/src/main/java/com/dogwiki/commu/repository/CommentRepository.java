package com.dogwiki.commu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogwiki.commu.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	@Query("SELECT c FROM comment c WHERE c.board_basic.num=:board_num ORDER BY c.com_num ASC")
	List<CommentEntity> getListWithQuery (@Param("board_num") Integer board_num);

}
