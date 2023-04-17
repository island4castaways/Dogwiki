package com.dogwiki.commu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "heart")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "heart")
public class HeartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hrtNum;
	
	@ManyToOne
	@JoinColumn(name = "pic_num", referencedColumnName = "picnum")
	private PictureEntity picture;
	
	@ManyToOne
	@JoinColumn(name = "hrt_user_id", referencedColumnName = "userid")
	private UserEntity user;
	
}
