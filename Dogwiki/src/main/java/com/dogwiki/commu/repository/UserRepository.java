package com.dogwiki.commu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dogwiki.commu.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	UserEntity findByUserid(String userid); //생성
	
	Boolean existsByUserid(String userid); //id 중복 확인
	
	Boolean existsByEmail(String email);	//email 중복 확인
	 
	UserEntity findByUseridAndPw(String userid,String pw); //검증
	
}
