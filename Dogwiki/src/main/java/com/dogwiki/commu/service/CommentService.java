package com.dogwiki.commu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.entity.CommentEntity;
import com.dogwiki.commu.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {
	
	@Autowired
	private CommentRepository cmtRepository;
	
	public List<CommentEntity> selectByBoard_basic(Integer board_num) {
		return cmtRepository.getByBoard_num(board_num);
	}
	
	public CommentEntity insert(CommentEntity entity) {
		return cmtRepository.save(entity);
	}
	
	public Optional<CommentEntity> selectOne(Integer cmtNum) {
		return cmtRepository.findById(cmtNum);
	}
	
	public CommentEntity updateOne(CommentEntity entity) {
		return cmtRepository.save(entity);
	}
	
	public void deleteById(Integer cmtNum) {
		cmtRepository.deleteById(cmtNum);
	}
	
	public void deleteByBoard_num(int board_num) {
		cmtRepository.getDeleteByBoard_num((Integer)board_num);
	}

}
