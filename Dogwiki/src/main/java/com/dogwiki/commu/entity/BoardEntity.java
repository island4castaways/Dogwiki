package com.dogwiki.commu.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private Integer category;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 40, nullable = false)
	private String writer_id;
	
	@Column(length = 255, nullable = false)
	private String content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date regdate;
	
	@Column
	private int hit;
	
	@OneToMany(mappedBy = "board_basic", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<CommentEntity> comments;
}
