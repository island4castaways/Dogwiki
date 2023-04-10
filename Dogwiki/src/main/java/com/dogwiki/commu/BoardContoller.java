package com.dogwiki.commu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.entity.CommentEntity;
import com.dogwiki.commu.service.BoardService;
import com.dogwiki.commu.service.CommentService;

@Controller
@RequestMapping("/board")
public class BoardContoller {
	
	@Autowired
	private BoardService brdService;
	
	@Autowired
	private CommentService cmtService;
	
	@GetMapping("/board_list")
	public String board_list(Model model, @PageableDefault(size=10, sort="num", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value="category", required = false, defaultValue = "2") int category,
			@RequestParam(value="search", required= false) String search) {
		System.out.println(category);
		
		Page<BoardEntity> boardpage;
		if(search!=null) {
			if(category==0) {
				boardpage = brdService.search_board(search, pageable);
			}else {
				boardpage = brdService.search_board(search, category, pageable);
			}
		}else {
			if(category == 0) {
				boardpage = brdService.pageList(pageable);
			}else {
				boardpage = brdService.board_select_category(category, pageable);
			}
		}
		
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
        
        model.addAttribute("cate",category);
        model.addAttribute("filter",search);
        
		return "/board/board_list";
	}
	
	@RequestMapping(value="/board_content", method =RequestMethod.GET)
	public String board_content(@RequestParam("num") Integer num,@RequestParam("page") int page, Model model) {
		System.out.println(num);
		Optional<BoardEntity> content = brdService.selectOne(num);
		BoardEntity boardContent = content.get();
		List<CommentEntity> cmtList = cmtService.selectByBoard_basic(num);
		model.addAttribute("boardContent", boardContent);
		model.addAttribute("page",page);
		model.addAttribute("cmtList", cmtList);
		return "/board/board_content";
		
	}
	
	@RequestMapping(value = "/board_write", method=RequestMethod.GET)
	public String board_write( Model model) {
		return "/board/board_write";
	}
	@RequestMapping(value = "/board_write", method=RequestMethod.POST)
	public String board_write_ok( Model model, BoardEntity board) {
		System.out.println(board);
		brdService.board_create(board);
		return "redirect:/board/board_list?category="+board.getCategory();
	}
	
	@RequestMapping(value = "/board_modify")
	public String board_modify(@RequestParam("num") Integer num, Model model) {
		Optional<BoardEntity> content = brdService.selectOne(num);
		BoardEntity boardContent = content.get();
		model.addAttribute("boardContent", boardContent);
		return "/board/board_modify";
	}
	
	@RequestMapping("/board_update")
	public String board_update(BoardEntity boardEntity) {
		brdService.update(boardEntity);
		return "redirect:/board/board_list?category="+boardEntity.getCategory();
	}
	
	@RequestMapping("/board_delete")
	public String board_delete(@RequestParam("num") int num, @RequestParam("category") int category) {
		brdService.board_delete(num);
		return "redirect:/board/board_list?&category="+category;
	}
}
