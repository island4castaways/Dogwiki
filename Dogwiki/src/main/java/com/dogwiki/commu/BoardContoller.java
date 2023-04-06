package com.dogwiki.commu;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardContoller {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board_list")
	public String board_list(Model model, @PageableDefault(size=10, sort="num", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<BoardEntity> boardpage = service.pageList(pageable);
		
		model.addAttribute("page", boardpage.toList());
        model.addAttribute("boardList", boardpage);
        
        int pageNumber=boardpage.getPageable().getPageNumber();	//현재 페이지
        int pageSize =boardpage.getPageable().getPageSize();
        int totalPage = boardpage.getTotalPages();
        int startPage = (int)Math.floor(pageNumber / pageSize) * pageSize + 1;
        int tempEndPage = startPage+pageSize-1;
        int endPage = (tempEndPage > totalPage) ? totalPage : tempEndPage;
        
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("tempEndPage",tempEndPage);
        model.addAttribute("endPage",endPage);
		return "/board/board_list";
	}
	
	@RequestMapping(value="/board_content", method =RequestMethod.GET)
	public String board_content(@RequestParam("num") Integer num,@RequestParam("page") int page, Model model) {
		System.out.println(num);
		Optional<BoardEntity> content = service.selectOne(num);
		BoardEntity boardContent = content.get();
		model.addAttribute("boardContent", boardContent);
		model.addAttribute("page",page);
		return "/board/board_content";
		
	}
	
	@RequestMapping(value = "/board_write", method=RequestMethod.GET)
	public String board_write( Model model) {
		return "/board/board_write";
	}
	
	@RequestMapping(value = "/board_modify")
	public String board_modify(@RequestParam("num") Integer num, Model model) {
		Optional<BoardEntity> content = service.selectOne(num);
		BoardEntity boardContent = content.get();
		model.addAttribute("boardContent", boardContent);
		return "/board/board_modify";
	}
	
	
}
