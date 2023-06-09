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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogwiki.commu.entity.HeartEntity;
import com.dogwiki.commu.entity.PictureEntity;
import com.dogwiki.commu.entity.UserEntity;
import com.dogwiki.commu.service.HeartService;
import com.dogwiki.commu.service.PictureService;
import com.dogwiki.commu.service.UserService;

@Controller
@RequestMapping("/pic_board")
public class PictureController {
	
    @Autowired
    private PictureService picService;
    
    @Autowired
    private HeartService hrtService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/write")
    public String boardWriteForm(HttpSession session, 
    		RedirectAttributes rttr) {
    	if(session.getAttribute("userid") == null) {
    		rttr.addFlashAttribute("msg", "글쓰기 권한이 없습니다. 로그인 해주세요.");
    		return "redirect:/login";
    	}
        return "pic_board/pic_board_write";
    }

    //여기에도 MultipartFile file 받아줌 //예외처리
    @PostMapping("/write")
    public String boardWritePro(PictureEntity picEntity, 
    		Model model, MultipartFile file, 
    		RedirectAttributes rttr) throws Exception {
    	if(file.isEmpty()) {
    		//message
    		rttr.addFlashAttribute("msg", "사진은 필수사항입니다.");
    		return "redirect:/pic_board/write";
    	}
    	System.out.println(file);
    	picService.write(picEntity, file);
    	
        rttr.addAttribute("msg", "글 작성이 완료되었습니다.");

        return "redirect:/pic_board/pic_list?category=1";
    }

    @GetMapping("/pic_list")
    public String boardList(Model model, 
    		@PageableDefault(size = 10, sort = "picnum", direction = Sort.Direction.DESC)Pageable pageable, 
    		@RequestParam(value = "category", required = false, defaultValue = "1") int category, 
    		@RequestParam(value = "search", required = false) String search, 
    		@RequestParam(value = "page", required = false, defaultValue = "0") String page) {
    	Page<PictureEntity> picPage;
    	
    	if(search!=null) {
    		picPage = picService.searchPicBoard(search, pageable);
    	} else {
    		picPage = picService.picBoardList(pageable, category);
    	}
    	List<PictureEntity> picList = picPage.getContent();
    	model.addAttribute("list" , picList);
    	
    	int pageNumber = picPage.getPageable().getPageNumber();	//현재 페이지
        int pageSize = picPage.getPageable().getPageSize();
        int totalPage = picPage.getTotalPages();
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
        return "pic_board/pic_board_list";
    }
    
    @RequestMapping("/pic_content")
    public String pictureContent(Model model, HttpSession session, 
    		@RequestParam("num") int picnum, 
    		@RequestParam("page") String page, 
    		HttpServletRequest request, HttpServletResponse response) {
    	Optional<PictureEntity> op = picService.selectOnePicture(picnum);
		PictureEntity entity = null;
		Cookie hitCoo = null;
		boolean cooExists = false;
		
		if(op.isPresent()) {
			entity = op.get();
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("hit" + entity.getPicnum())) {
					cooExists = true;
				}
			}
			if(!cooExists) {	//쿠키 없을 때 hit + 1 -> update, new Cookie
				entity.setHit(entity.getHit() + 1);
				picService.updatePicture(entity);
				hitCoo = new Cookie("hit" + entity.getPicnum(), "" + entity.getHit());
				hitCoo.setMaxAge(60 * 5);	//5분
				response.addCookie(hitCoo);
			}
			model.addAttribute("picEntity", entity);
		}
		
    	String userid = (String)session.getAttribute("userid");
    	boolean heartExists = false;
    	for(UserEntity user : hrtService.getUserList(picnum)) {
    		if(user.getUserid().equals(userid)) {
    			heartExists = true;
    		}
    	}
    	model.addAttribute("heartExists", heartExists);
		
		model.addAttribute("page", page);
    	return "pic_board/pic_board_content";
    }
    
    @RequestMapping("/pic_delete")
    public String pictureDelete(@RequestParam("picnum") Integer picnum) {
    	picService.deletePicture(picnum);
    	return "redirect:/pic_board/pic_list?category=1";
    }
    
    @PostMapping("/pic_heart")
    public String pictureJoa(Model model, HttpSession session, 
    		@RequestParam(value = "category", defaultValue = "1", required = false) String category, 
    		@RequestParam(value = "search", required = false) String search, 
    		@RequestParam("page") String page, 
    		@RequestParam("picnum") Integer picnum, 
    		HttpServletRequest request, HttpServletResponse response) {
    	PictureEntity picEntity = picService.selectOnePicture(picnum).get();
    	String userid = (String)session.getAttribute("userid");
    	boolean heartExists = false;
    	for(UserEntity user : hrtService.getUserList(picnum)) {
    		if(user.getUserid().equals(userid)) {
    			heartExists = true;
    			HeartEntity tempEntity = hrtService.SelectOne(picnum, userid);
    			hrtService.deleteOne(tempEntity.getHrtNum());
    			picEntity.setHeart(picEntity.getHeart() - 1);
    			picService.saveOne(picEntity);
    		}
    	}
    	if(!heartExists) {
    		HeartEntity hrtEntity = HeartEntity.builder()
    				.picture(picEntity)
    				.user(userService.selectById(userid))
    				.build();
    		hrtService.insertOne(hrtEntity);
    		picEntity.setHeart(picEntity.getHeart() + 1);
    		picService.saveOne(picEntity);
    	}
    	return "redirect:/pic_board/pic_content?category=" + category 
				+ "&search=" + search + "&num=" + picnum + "&page=" + page;
    }
	
}