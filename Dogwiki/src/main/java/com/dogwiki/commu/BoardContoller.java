package com.dogwiki.commu;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String board_list(Model model, 
			@PageableDefault(size=10, sort="num", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value="category", required = false, defaultValue = "2") int category,
			@RequestParam(value="search", required= false) String search, @RequestParam(value="user", required=false) String userid) {
		System.out.println(category);
		
		
		Page<BoardEntity> boardpage;
		
		if(userid!=null) {
			boardpage=brdService.mypage_board(userid, pageable);
		}else if(search!=null) {
			boardpage = brdService.search_board(search, category, pageable);
		}else {
			boardpage = brdService.board_select_category(category, pageable);
		}
		
		model.addAttribute("page", boardpage.toList());
        model.addAttribute("boardList", boardpage);
        
        int pageNumber = boardpage.getPageable().getPageNumber();	//현재 페이지
        int pageSize = boardpage.getPageable().getPageSize();
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
	
	@RequestMapping(value="/board_content", method = RequestMethod.GET)
	public String board_content(@RequestParam("num") Integer num, 
			@RequestParam("page") int page, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(num);
		
		Optional<BoardEntity> op = brdService.selectOne(num);
		BoardEntity entity = null;
		Cookie hitCoo = null;
		boolean cooExists = false;
		
		if(op.isPresent()) {
			entity = op.get();
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("hit" + entity.getNum())) {
					cooExists = true;
				}
			}
			if(!cooExists) {	//쿠키 없을 때 hit + 1 -> update, new Cookie
				entity.setHit(entity.getHit() + 1);
				brdService.update(entity);
				hitCoo = new Cookie("hit" + entity.getNum(), "" + entity.getHit());
				hitCoo.setMaxAge(60 * 5);	//5분
				response.addCookie(hitCoo);
			}
			model.addAttribute("boardContent", entity);
		}
		
		model.addAttribute("page", page);

		List<CommentEntity> cmtList = cmtService.selectByBoard_basic(num);
		model.addAttribute("cmtList", cmtList);
		
		return "/board/board_content";
	}
	
	@PostMapping("/board_comment")
	public String board_comment(@RequestParam("category") String category, 
			@RequestParam("search") String search,
			@RequestParam("page") String page, 
			@RequestParam("board_num") Integer board_num, 
			@RequestParam(value = "cmtNum", defaultValue = "0", required = false) Integer cmtNum, 
			@RequestParam("cmtWriter") String cmtWriter, 
			@RequestParam("cmtContent") String cmtContent) {
		if(cmtNum != 0) {
			CommentEntity entity = cmtService.selectOne(cmtNum).get();
			entity.setCmtContent(cmtContent);
			cmtService.updateOne(entity);
			return "redirect:/board/board_content?category=" + category 
					+ "&search=" + search + "&num=" + board_num + "&page=" + page;
		}
		if(cmtContent != null && !cmtContent.isEmpty()) {
			CommentEntity entity = CommentEntity.builder()
					.board_basic(BoardEntity.builder()
							.num(board_num)
							.build())
					.cmtWriter(cmtWriter)
					.cmtContent(cmtContent)
					.build();
			cmtService.insert(entity);
		}
		if(category.isEmpty() || category == null) category = "";
		if(search.isEmpty() || search == null) search = "";
		return "redirect:/board/board_content?category=" + category 
				+ "&search=" + search + "&num=" + board_num + "&page=" + page;
	}
	
	@PostMapping("/board_comment_delete")
	public String board_comment_delete(
			@RequestParam("category") String category, 
			@RequestParam("search") String search, 
			@RequestParam("page") String page, 
			@RequestParam("board_num") String board_num, 
			@RequestParam("cmtNum") Integer cmtNum) {
		cmtService.deleteById(cmtNum);
		if(category.isEmpty() || category == null) category = "";
		if(search.isEmpty() || search == null) search = "";
		return "redirect:/board/board_content?category=" + category 
				+ "&search=" + search + "&num=" + board_num + "&page=" + page;
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public String board_write(Model model, @RequestParam("category") int category) {
		if(category==1) {
			return "/pic_board/pic_board_write";
		}
		return "/board/board_write";
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.POST)
	public String board_write_ok(Model model, BoardEntity board) {
		System.out.println(board);		
		brdService.board_create(board);
		return "redirect:/board/board_list?category="+board.getCategory();
	}
	
	@RequestMapping(value = "/board_modify")
	public String board_modify(@RequestParam("num") Integer num, 
			Model model) {
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
	public String board_delete(@RequestParam("num") int num, 
			@RequestParam("category") int category) {
//		cmtService.deleteByBoard_num(num);
		brdService.board_delete(num);
		return "redirect:/board/board_list?&category="+category;
	}
}
