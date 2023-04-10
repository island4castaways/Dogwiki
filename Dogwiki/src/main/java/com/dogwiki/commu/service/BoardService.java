package com.dogwiki.commu.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

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
	
	public Optional<BoardEntity> update(BoardEntity entity){
		validate(entity);
		
		final Optional<BoardEntity> original = repository.findById(entity.getNum());
		
		original.ifPresent(board->{
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			
			repository.save(board);
		});
		
		return repository.findById(entity.getNum());
	}
	
	public void validate(final BoardEntity entity) {
		if(entity==null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getNum()==null) {
			log.warn("Unkwon user");
			throw new RuntimeException("Unkown user");
		}
	}
	
	public boolean board_delete (Integer num) {
		Optional<BoardEntity> board = repository.findById(num);
		BoardEntity entity = board.get();
		validate(entity);
		try {
			repository.delete(entity);
			return true;
		}catch(Exception e) {
			log.error("error deleting entity", entity.getNum(),e);
			return false;
			
		}
	}
	
	public boolean board_create(final BoardEntity entity) {
		entity.setCategory(2);
		repository.save(entity);
		System.out.println(entity);
//		if(boardEntity.getNum()==null) {
//			log.warn("unkown user");
//			throw new RuntimeException("Unkown user");
//		}
		return true;
	}
	
	public Page<BoardEntity> board_select_category(Integer category, Pageable pageable) {
//		return repository.findAll(category);
		repository.findALLByCategory(category, pageable);
		return repository.findALLByCategory(category, pageable);
	}
	
	public Page<BoardEntity> search_board(String search, Integer category, Pageable pageable){
		return repository.getListWithQuery(category, search, pageable);
	}
	
	
}
