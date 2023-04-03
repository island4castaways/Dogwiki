package com.dogwiki.commu.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogwiki.commu.service.TrainingService;

import lombok.Data;

@Data
public class PageDTO {
	
	@Autowired
	private TrainingService trService;
	
	private int pageNum;
	private int pageSize;
	private int startNum;
	private int endNum;
	private int totalEl;
	private int totalPg;
	private boolean hasPrev;
	private boolean hasNext;
	
	public PageDTO(int pageNum, int pageSize, long totalEl) {
		//pageNum 0부터 시작
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		if(pageNum <= 10) this.startNum = 1;
		else this.startNum = (int)((pageNum + 1) / pageSize) * pageSize + 1;
		this.totalPg = (int)(totalEl / pageSize) * pageSize + ((int)totalEl % pageSize);
		if(pageNum == totalPg - 1) {
			this.endNum = pageNum;
		} else {
			this.endNum = startNum + 9;
		}
		if((pageNum + 1) > pageSize) this.hasPrev = true;
		if((pageNum + 1) <= (int)(totalEl / pageSize) * pageSize) this.hasNext = true;
	}

}
