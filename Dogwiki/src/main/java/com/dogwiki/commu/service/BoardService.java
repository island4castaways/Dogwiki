package com.dogwiki.commu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.entity.PictureEntity;
import com.dogwiki.commu.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository brdRepository;
	
	public List<BoardEntity> selectAll() {
		return brdRepository.findAll();
	}
	
	public Optional<BoardEntity> selectOne(Integer num) {
		return brdRepository.findById(num);
	}
	
	@Transactional(readOnly = true)
	public Page<BoardEntity> pageList(Pageable pageable) {
		return brdRepository.findAll(pageable);
	}
	
	public Optional<BoardEntity> update(BoardEntity entity) {
		validate(entity);
		
		final Optional<BoardEntity> original = brdRepository.findById(entity.getNum());
		
		original.ifPresent(board->{
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			
			brdRepository.save(board);
		});
		
		return brdRepository.findById(entity.getNum());
	}
	
	public void validate(final BoardEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getNum() == null) {
			log.warn("Unkwon user");
			throw new RuntimeException("Unkown user");
		}
	}
	
	public boolean board_delete (Integer num) {
		Optional<BoardEntity> board = brdRepository.findById(num);
		BoardEntity entity = board.get();
		validate(entity);
		try {
			brdRepository.delete(entity);
			return true;
		} catch(Exception e) {
			log.error("error deleting entity", entity.getNum(),e);
			return false;
			
		}
	}
	
	public boolean board_create(final BoardEntity entity) {
		brdRepository.save(entity);
		return true;
	}
	
	public Page<BoardEntity> board_select_category(Integer category, Pageable pageable) {
		brdRepository.findALLByCategory(category, pageable);
		return brdRepository.findALLByCategory(category, pageable);
	}
	
	public Page<BoardEntity> search_board(String search, Integer category, Pageable pageable) {
		return brdRepository.getListWithQuery(category, search, pageable);
	}
	
	public Page<BoardEntity> mypage_board(String userid, Pageable pageable) {
		return brdRepository.getListWithQuery(userid, pageable);
	}
	
	public List<BoardEntity> homeBoard(Integer category){
		return brdRepository.findTop5ByCategoryOrderByHitDesc(category);
	}
}