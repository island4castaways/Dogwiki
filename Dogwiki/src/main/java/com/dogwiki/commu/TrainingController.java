package com.dogwiki.commu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.service.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	private TrainingService trService;
	
//	@GetMapping("/testTraining")
//	public ResponseEntity<?> testService() {
//		String st = trService.testService();
//		List<String> list = new ArrayList<>();
//		list.add(st);
//		list.add("service test completed!\n"
//				+ "And response has successed!");
//		ResponseDTO<String> response = ResponseDTO.<String>builder()
//				.data(list).build();
//		return ResponseEntity.ok().body(response);
//	}
//	
//	@GetMapping("/training")
//	public ResponseEntity<?> main() {
//		System.out.println("Training Main page has been called");
//		List<TrainingEntity> entities = trService.selectAll();
//		List<TrainingDTO> dtos = entities.stream()
//				.map(TrainingDTO::new).collect(Collectors.toList());
//		ResponseDTO<TrainingDTO> response = ResponseDTO.<TrainingDTO>builder()
//				.data(dtos).build();
//		return ResponseEntity.ok().body(response);
//	}
	
	@GetMapping
	public String main(Model model, Integer pn, 
			String st) {
		System.out.println("Training Main page has been called");
		
		if(pn == null) pn = 1;
		Integer pageNum = pn;
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("trId").descending());
		String trProf = st;
		List<TrainingEntity> list = null;
		String msg = null;
		
		if(st == null) {
			Page<TrainingEntity> page = trService.selectAll(pageable);
			list = new ArrayList<>();
			for(TrainingEntity en : page) {
				list.add(en);
			}
		} else {
			msg = "검색 결과";
			list = trService.selectByTrProf(trProf, pageable);
			model.addAttribute("msg", msg);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("pn", pageNum);
		
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
