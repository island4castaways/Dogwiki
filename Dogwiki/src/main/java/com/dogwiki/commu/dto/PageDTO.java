package com.dogwiki.commu.dto;

import lombok.Data;

@Data
public class PageDTO {
	
	private int pageNum;
	private int pageSize;
	private int startPage;
	private int endPage;
	private int total;
	private int realEnd;
	private boolean hasPrev;
	private boolean hasNext;
	
	public PageDTO(int pageNum, int pageSize, int total) {
		//pageNum 0부터 시작
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		
		this.startPage = (int)((pageNum + 1) / pageSize) * pageSize + 1;
		this.realEnd = (int)Math.ceil(total / (double)pageSize);
		
		this.endPage = startPage + pageSize -1;
		if(endPage > realEnd) this.endPage = realEnd;
		
		if(pageNum < pageSize) hasPrev = false;
		if(pageNum > (int)(realEnd / pageSize)) hasNext = false;
	}

}
