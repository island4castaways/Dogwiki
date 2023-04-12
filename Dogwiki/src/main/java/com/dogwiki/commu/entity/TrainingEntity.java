package com.dogwiki.commu.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Training")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer trId;
	
	@Column(length = 40, nullable = false)
	private String trProf;
	
	@Column(length = 255, nullable = false)
	private String trTitle;
	
	@Column(length = 100, nullable = false)
	private String trUrl;
	
	@CreationTimestamp
	private Timestamp trDate;
	
	@Column
	private int trHit;

}
