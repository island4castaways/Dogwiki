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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "comment")
@Entity(name = "comment")
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer com_num;
	
	@ManyToOne
    @JoinColumn(name="board_num")
	private BoardEntity board_basic;
	
	@Column(length = 40, nullable = false)
	private String com_writer_id;
	
	@Column(length = 255, nullable = false)
	private String com_content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date com_regdate;
}
