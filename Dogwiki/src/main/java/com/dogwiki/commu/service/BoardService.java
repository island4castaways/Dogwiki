package com.dogwiki.commu.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	public List<BoardEntity> selectAll(){
		return repository.findAll();
	}
	
	public Optional<BoardEntity> selectOne(Integer num) {
		return repository.findById(num);
				
	}
	
	@Transactional(readOnly = true)
	public Page<BoardEntity> pageList(Pageable pageable){
		return repository.findAll(pageable);
	}
}
