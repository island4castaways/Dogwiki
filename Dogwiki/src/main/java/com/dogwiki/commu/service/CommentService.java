package com.dogwiki.commu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {
	
	@Autowired
	private CommentRepository repository;
}
