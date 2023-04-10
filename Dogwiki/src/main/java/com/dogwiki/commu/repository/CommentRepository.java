package com.dogwiki.commu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dogwiki.commu.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}