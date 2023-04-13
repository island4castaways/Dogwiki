package com.dogwiki.commu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.entity.HeartEntity;
import com.dogwiki.commu.entity.UserEntity;
import com.dogwiki.commu.repository.HeartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeartService {
	
	@Autowired
	private HeartRepository hrtRepository;
	
	public List<UserEntity> getUserList(int picnum) {
		return hrtRepository.getUserListByPicnum(picnum);
	}
	
	public HeartEntity SelectOne(int picnum, String userid) {
		return hrtRepository.getHeartEntity(picnum, userid);
	}
	
	public void deleteOne(Integer hrtNum) {
		hrtRepository.deleteById(hrtNum);
	}
	
	public HeartEntity insertOne(HeartEntity entity) {
		return hrtRepository.save(entity);
	}

}
