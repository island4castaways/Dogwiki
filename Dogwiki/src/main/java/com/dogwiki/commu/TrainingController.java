package com.dogwiki.commu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogwiki.commu.dto.PageDTO;
import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.service.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	private TrainingService trService;
	
	@GetMapping
	public String testPaging(Model model, 
			@RequestParam(value = "pn", defaultValue = "0", required = false) int pageNum,
			@RequestParam(value = "ps", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sb", defaultValue = "trId", required = false) String sortBy, 
			@RequestParam(value = "st", required = false) String trProf) {
		
		PageDTO page = new PageDTO(pageNum, pageSize, trService.countTotal());
		model.addAttribute("page", page);

		List<TrainingEntity> list = null;
		String msg = null;
		if(trProf == null) {
			Page<TrainingEntity> pages = trService.selectAll(pageNum, pageSize, sortBy);
			list = pages.getContent();
		} else {
			msg = "검색 결과";
			list = trService.selectByTrProf(trProf, pageNum, pageSize, sortBy);
			model.addAttribute("msg", msg);
		}
		model.addAttribute("list", list);
		
		return "/training/training";
	}
			
	@GetMapping("/content")
	public String content(@RequestParam("num") Integer trId, 
			@RequestParam("pn") Integer pn, 
			Model model) {
		Optional<TrainingEntity> op = trService.selectOne(trId);
		TrainingEntity en = null;
		String error = null;
		
		if(op.isPresent()) {
			en = op.get();
			model.addAttribute("en", en);
		} else {
			error = "searching entity doesn't exist.";
			model.addAttribute("msg", error);
		}
		
		model.addAttribute("pn", pn);
		
		return "/training/content";
	}
	
}
