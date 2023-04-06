package com.dogwiki.commu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwiki.commu.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
}
