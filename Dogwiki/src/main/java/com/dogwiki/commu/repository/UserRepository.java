package com.dogwiki.commu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dogwiki.commu.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	UserEntity findByUserid(String userid); //생성
	
	Boolean existsByUserid(String userid); //존재 여부
	
	UserEntity findByUseridAndPw(String userid,String pw); //검증
	
 
}
