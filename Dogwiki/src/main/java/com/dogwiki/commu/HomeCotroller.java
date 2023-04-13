package com.dogwiki.commu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.entity.PictureEntity;
import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.service.BoardService;
import com.dogwiki.commu.service.PictureService;
import com.dogwiki.commu.service.TrainingService;

@Controller
public class HomeCotroller {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	TrainingService trainingService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<PictureEntity> picEntity = pictureService.homePicBoard();
		List<BoardEntity> board2Entity = boardService.homeBoard(2); 
		List<BoardEntity> board3Entity = boardService.homeBoard(3); 
		TrainingEntity trainingEntity = trainingService.homeTraining();
		model.addAttribute("picEntity", picEntity);
		model.addAttribute("board2Entity", board2Entity);
		model.addAttribute("board3Entity", board3Entity);
		model.addAttribute("trainingEntity", trainingEntity);
		return "/home";
	}
	
	
	
//	@GetMapping("/user")
//	public String home() {
//		System.out.println("Home Controller has started");
//		return "/user_join";
//	}

}
