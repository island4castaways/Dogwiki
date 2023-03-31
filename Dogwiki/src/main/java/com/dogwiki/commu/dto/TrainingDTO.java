package com.dogwiki.commu.dto;

import java.sql.Timestamp;

import com.dogwiki.commu.entity.TrainingEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainingDTO {
	
	private String trProf;
	private String trTitle;
	private Timestamp trDate;
	private int trHit;
	
	//entity to dto 가능하게 하는 생성자
	public TrainingDTO(final TrainingEntity entity) {
		this.trProf = entity.getTrProf();
		this.trTitle = entity.getTrTitle();
		this.trDate = entity.getTrDate();
		this.trHit = entity.getTrHit();
	}
	
	public static TrainingEntity toEntity(final TrainingDTO dto) {
		return TrainingEntity.builder()
				.trProf(dto.getTrProf())
				.trTitle(dto.getTrTitle())
				.trDate(dto.getTrDate())
				.trHit(dto.getTrHit())
				.build();
	}

}
