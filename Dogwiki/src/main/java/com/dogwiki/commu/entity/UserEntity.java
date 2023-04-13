package com.dogwiki.commu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

	@Id //primary key
	@Column(length = 40,nullable = false)
	private String userid;
	
	@Column(length = 40,nullable = false)
	private String pw;
	
	@Column(nullable = false)
	private String username;
	
	@Column(length = 40,nullable = false)
	private String phone;
	
	@Column(length = 40,nullable = false,unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private List<BoardEntity> boards;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private List<PictureEntity> pictures;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private List<CommentEntity> comments;
}
