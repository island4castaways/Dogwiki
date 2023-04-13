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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity(name = "comment")
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cmtNum;
	
	@ManyToOne
    @JoinColumn(name = "board_num", referencedColumnName = "num")
	private BoardEntity board_basic;
	
	@ManyToOne
	@JoinColumn(name = "cmt_writer_id", referencedColumnName = "userid")
	private UserEntity user;
	
	@Column(length = 255, nullable = false)
	private String cmtContent;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date cmtDate;
}
