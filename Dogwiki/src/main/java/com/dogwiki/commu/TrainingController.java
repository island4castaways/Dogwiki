package com.dogwiki.commu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/training")
	public String main(Model model, Pageable pageable) {
		System.out.println("Training Main page has been called");
		Page<TrainingEntity> list = trService.selectAll(pageable);
		model.addAttribute("list", list);
		return "/training/training";
	}

}
