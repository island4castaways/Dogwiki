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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	
	@ManyToOne
	@JoinColumn(name = "writer_id", referencedColumnName = "userid")
	private UserEntity user;
	
	@Column(length = 255, nullable = false)
	private String content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date regdate;
	
	@Column
	private int hit;
	
	@OneToMany(mappedBy = "board_basic", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private List<CommentEntity> comments;
}
