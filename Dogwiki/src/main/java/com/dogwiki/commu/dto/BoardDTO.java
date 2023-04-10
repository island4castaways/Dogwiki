package com.dogwiki.commu.dto;

import com.dogwiki.commu.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	private int num;
	private String category;
	private String title;
	private String writer_id;
	private String content;
	private String regdate;
	private int hit;
	
	public BoardDTO(final BoardEntity entity) {
		this.num = entity.getNum();
		
	}
}
