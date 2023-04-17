package com.dogwiki.commu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dogwiki.commu.entity.HeartEntity;
import com.dogwiki.commu.entity.UserEntity;

public interface HeartRepository extends JpaRepository<HeartEntity, Integer> {
	
	@Query("SELECT h.user FROM heart h WHERE h.picture.picnum=:picnum")
	List<UserEntity> getUserListByPicnum(@Param("picnum") int picnum);
	
	@Query("SELECT h FROM heart h WHERE h.picture.picnum=:picnum AND h.user.userid=:userid")
	HeartEntity getHeartEntity(@Param("picnum") int picnum, @Param("userid") String userid);

}
