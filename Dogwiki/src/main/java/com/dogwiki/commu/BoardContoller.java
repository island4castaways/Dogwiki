package com.dogwiki.commu;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.entity.CommentEntity;
import com.dogwiki.commu.entity.UserEntity;
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
			@RequestParam(value="search", required= false) String search) {
		Page<BoardEntity> boardpage;
		
		if(search != null) {
			boardpage = brdService.search_board(search, category, pageable);
		} else {
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
        
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("tempEndPage", tempEndPage);
        model.addAttribute("endPage", endPage);
        
        model.addAttribute("category", category);
        model.addAttribute("search", search);
        
		return "/board/board_list";
	}
	
	@RequestMapping(value = "/board_content", method = RequestMethod.GET)
	public String board_content(@RequestParam("num") Integer num, 
			@RequestParam(value="page",  required = false, defaultValue = "0") int page, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
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
			@RequestParam(value = "search", required = false) String search,
			@RequestParam("page") String page, 
			CommentEntity entity, 
			HttpSession session, RedirectAttributes rttr) {
		if(session.getAttribute("userid") == null) {
			rttr.addFlashAttribute("msg", "댓글 쓰기 권한이 없습니다. 로그인 해주세요.");
			return "redirect:/login";
		}
		
		if(entity.getCmtContent() != null && !entity.getCmtContent().isEmpty()) {
			if(entity.getCmtNum() != null) {
				CommentEntity original = cmtService.selectOne(entity.getCmtNum()).get();
				original.setCmtContent(entity.getCmtContent());
				cmtService.saveOne(original);
			} else {
				cmtService.saveOne(entity);
			}
		}
		
		return "redirect:/board/board_content?category=" + category 
				+ "&search=" + search + "&num=" + entity.getBoard_basic().getNum() + "&page=" + page;
	}
	
	@PostMapping("/board_comment_delete")
	public String board_comment_delete(
			@RequestParam("category") String category, 
			@RequestParam(value = "search", required = false) String search, 
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
	public String board_write(@RequestParam("category") int category, 
			Model model, HttpSession session, RedirectAttributes rttr) {
		if(session.getAttribute("userid") == null) {
			rttr.addFlashAttribute("msg", "글쓰기 권한이 없습니다. 로그인 해주세요.");
			return "redirect:/login";
		}
		if(category == 1) {
			return "/pic_board/pic_board_write";
		}
		return "/board/board_write";
	}
	
	@PostMapping("/board_write")
	public String board_write(BoardEntity boardEntity) {
		brdService.board_create(boardEntity);
		return "redirect:/board/board_list?category=" + boardEntity.getCategory();
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
		return "redirect:/board/board_list?category=" + boardEntity.getCategory();
	}
	
	@RequestMapping("/board_delete")
	public String board_delete(@RequestParam("num") int num, 
			@RequestParam("category") int category) {
		brdService.board_delete(num);
		return "redirect:/board/board_list?&category=" + category;
	}
}
