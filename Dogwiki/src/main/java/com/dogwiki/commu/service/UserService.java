package com.dogwiki.commu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwiki.commu.entity.UserEntity;
import com.dogwiki.commu.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; // 실제 Entity 작업하는 객체

	// 사용자 생성
	public UserEntity create(final UserEntity userEntity) {

		// 유효성 체크(valid 검증)
		if (userEntity == null || userEntity.getUsername() == null) {
			System.out.println("1번");
			throw new RuntimeException("Invalid arguments");
		}

		return userRepository.save(userEntity); // 사용자 저장 및 결과 반환...
	}

	// 사용자 인증(존재여부.로그인)
	public UserEntity getByCredentials(final String userid, final String password) {
		return userRepository.findByUseridAndPw(userid, password);
	}

	// id로 entity객체 반환
	public UserEntity validate(final String userid) {
		return userRepository.findByUserid(userid);
	}

	// id 존재 여부 확인
	public Boolean validateId(final String userid) {
		return userRepository.existsByUserid(userid);
	}

	// pw수정
	public Boolean pwmodify(final String userid, final String oldpw, final String newpw) {
		UserEntity entity = userRepository.findByUserid(userid);
		if (entity != null && entity.getPw().equals(oldpw)) {
			entity.setPw(newpw);
			userRepository.save(entity);
			return true;
		}
		return false;
	}

	public Boolean deleteUser(final String userid, final String pw) {
		UserEntity entity = userRepository.findByUserid(userid);
		if (entity != null && entity.getPw().equals(pw)) {
			userRepository.delete(entity);
			return true;
		}
		return false;
	}

	public Boolean modify(final String userid, final String username, final String phone, final String email) {
		// id로 엔티티를 찾고 그 entity의 userid가 session의 userid와 같으면 수정한 부분 저장 후 성공
		UserEntity entity = userRepository.findByUserid(userid);

		if (entity != null && entity.getUserid().equals(userid)) {
			entity.setUserid(userid);
			entity.setUsername(username);
			entity.setPhone(phone);
			entity.setEmail(email);
			userRepository.save(entity);
			return true;
		}
		return false;
	}

}