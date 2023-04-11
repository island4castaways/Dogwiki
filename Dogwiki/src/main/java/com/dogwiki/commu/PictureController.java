package com.dogwiki.commu;


import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.dogwiki.commu.entity.PictureEntity;
import com.dogwiki.commu.service.PictureService;

@Controller
@RequestMapping("/pic_board")
public class PictureController {
    @Autowired
    private PictureService picService;

    @GetMapping("/write") //어떤 url로 접근할 것인지 정해주는 어노테이션 //localhost:8080/board/write
    public String boardWriteForm() {
        return "pic_board/pic_board_write";
    }

    //여기에도 MultipartFile file 받아줌 //예외처리
    @PostMapping("/write")
    public String boardWritePro(PictureEntity picEntity, Model model, MultipartFile file)throws Exception{
    	System.out.println(file);
    	picService.write(picEntity, file);

        //메세지띄우기2
        model.addAttribute("message","글작성이 완료되었습니다");
        model.addAttribute("searchUrl","/board/list");

        return "redirect:/pic_board/pic_list?category=1";
    }

    @GetMapping("/pic_list")
    public String boardList(Model model, 
    		@PageableDefault(size=10, sort="picnum", direction = Sort.Direction.DESC) Pageable pageable
    		,@RequestParam(value = "category", required = false)int category){
    	List<PictureEntity> picList = picService.picBoardList(pageable, category).getContent();
    	System.out.println(picList);
    	model.addAttribute("list" , picList);
        return "pic_board/pic_board_list";
    }
    
    @RequestMapping("/pic_content")
    public String pictureContent(@RequestParam("num") int picnum, Model model) {
    	PictureEntity picEntity= picService.selectOnePicture(picnum);
    	model.addAttribute("picEntity", picEntity);
    	return "pic_board/pic_board_content";
    }
    
    @RequestMapping("/pic_delete")
    public String pictureDelete(@RequestParam("picnum") Integer picnum) {
    	picService.deletePicture(picnum);
    	return "pic_board/pic_board_list";
    }
//
//    @GetMapping("/board/view") //localhost:8080/board/view?id=1 //(get방식 파라미터)
//    public String boardView(Model model, Integer id){
//        model.addAttribute("testboard", picService.boardview(id));
//        return "boardview";
//    }
//
//    @GetMapping("/board/delete")
//    public String boardDelete(Integer id){
//
//    	picService.boardDelete(id);
//        //게시물삭제하고 게시물리스트로 넘어가야하므로
//        return "redirect:/board/list";
//    }
//
//    //PathVariable이라는 것은 modify 뒤에있는 {id}부분이 인식이되서 Integer형태의 id로 들어온다는것
//    @GetMapping("/board/modify/{id}")
//    public String boardModify(@PathVariable("id") Integer id, Model model){
//
//        //상세페이지에 있는 내용과, 수정페이지의 내용이 같기때문에 위 코드와 같은 것을 확인할수있다
//        model.addAttribute("testboard", picService.boardview(id));
//
//        return "boardmodify";
//    }
//
//    //수정부분에도 MultipartFile 와 throw IOEException 추가
//    @PostMapping("/board/update/{id}")
//    public String boardUpdate(@PathVariable("id") Integer id, PictureEntity picEntity, MultipartFile file)throws Exception {
//        //기존에있던글이 담겨져서온다.
//    	picEntity boardTemp = picService.boardview(id);
//
//        //기존에있던 내용을 새로운 내용으로 덮어씌운다.
//        boardTemp.setTitle(board.getTitle());
//        boardTemp.setContent(board.getContent());
//
//        picService.write(boardTemp, file); //추가 → 수정한내용을 boardService의 write부분에 넣기
//
//        return "redirect:/board/list";
//    }
	
}
