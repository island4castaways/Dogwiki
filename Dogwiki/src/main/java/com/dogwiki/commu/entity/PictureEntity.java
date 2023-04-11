package com.dogwiki.commu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="picture")
public class PictureEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int picnum;
	
	@Column(length = 200, nullable = false)
	private String filename;
		
	@Column(length = 500, nullable = false)
	private String filepath;
	
	@Column
	private Integer category;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="pic_writer_id")
	private UserEntity user;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date pic_regdate;
	
	@Column
	private int hit;
	
}
