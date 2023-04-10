package com.dogwiki.commu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		return cmtRepository.getListWithQuery(board_num);
	}

}
