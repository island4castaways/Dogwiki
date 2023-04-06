package com.dogwiki.commu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "board_basic")
@Entity(name = "Board")
public class BoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer num;
	
	@Column(length = 40, nullable = false)
	private String category;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 40, nullable = false)
	private String writer_id;
	
	@Column(length = 255, nullable = false)
	private String content;
	
	@Column()
	@CreatedDate
	private String regdate;
	
	@Column
	private int hit;
}
