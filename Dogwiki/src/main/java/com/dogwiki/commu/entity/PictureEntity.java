package com.dogwiki.commu.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "picture")
public class PictureEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int picnum;
	
	@Column(length = 500, nullable = false)
	private String filename;
		
	@Column(length = 500, nullable = false)
	private String filepath;
	
	@Column
	private Integer category;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "pic_writer_id", referencedColumnName = "userid")
	private UserEntity user;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date pic_regdate;
	
	@Column
	private int hit;
	
	@Column
	private int heart;
	
	@OneToMany(mappedBy = "picture", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<HeartEntity> hearts;
	
}
