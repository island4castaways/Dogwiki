package com.dogwiki.commu;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogwiki.commu.dto.PageDTO;
import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.service.TrainingService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	private TrainingService trService;
	
	@GetMapping
	public String main(Model model, 
			@RequestParam(value = "pn", defaultValue = "0", required = false) int pageNum,
			@RequestParam(value = "ps", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sb", defaultValue = "trId", required = false) String sortBy, 
			@RequestParam(value = "st", required = false) String trProf, 
			@RequestParam(value = "search", required = false) String search, 
			HttpServletRequest request, HttpServletResponse response) {
		List<TrainingEntity> list = null;
		String msg = null;
		int total = 0;
		boolean latestCooExist = false;
		
		for(Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals("latestCoo")) {
				latestCooExist = true;
			}
		}
		
		if(!latestCooExist) {
			Cookie latestCoo = new Cookie("latestCoo", "true");
			latestCoo.setMaxAge(60 * 60 * 12);	//12시간
			response.addCookie(latestCoo);
		}
		
		
		if((trProf == null || trProf.isEmpty()) 
				&& (search == null || search.isEmpty())) {
			total = trService.countTotal();
			list = trService
					.selectAll(pageNum, pageSize, sortBy, !latestCooExist)
					.getContent();
		} else if(trProf != null && (search == null || search.isEmpty())) {
			total = trService.countByTrProf(trProf);
			msg = trProf + " 모아 보기";
			list = trService
					.selectByTrProf(trProf, pageNum, pageSize, sortBy);
		} else if(search != null && trProf.isEmpty()) {
			total = trService.countByTrTitle(search);
			msg = "제목으로 검색 결과";
			list = trService
					.selectByTrTitle(search, pageNum, pageSize, sortBy);
		} else if(trProf != null && search != null) {
			total = trService.countByTrProfAndTrTitle(trProf, search);
			msg = trProf + " 모아 보기 검색 결과";
			list = trService
					.selectByTrProfAndTrTitle(trProf, search, 
							pageNum, pageSize, sortBy);
		}
		
		System.out.println("total pageNum : " + total);
		PageDTO page = new PageDTO(pageNum, pageSize, total);
		model.addAttribute("page", page);
		
		if(msg != null) model.addAttribute("msg", msg);

		model.addAttribute("st", trProf);
		model.addAttribute("search", search);
		
		model.addAttribute("list", list);
		
		return "/training/training";
	}
			
	@GetMapping("/content")
	public String content(@RequestParam("num") Integer trId, 
			@RequestParam("pn") Integer pn, 
			@RequestParam(value = "st", required = false) String trProf, 
			@RequestParam(value = "search", required = false) String search, 
			HttpServletResponse response, HttpServletRequest request, 
			Model model) {
		Optional<TrainingEntity> op = trService.selectOne(trId);

		TrainingEntity en = null;
		String error = null;
		Cookie hitCoo = null;
		boolean cooExists = false;
		
		if(op.isPresent()) {
			en = op.get();
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("hit" + en.getTrId())) {
					cooExists = true;
				}
			}
			if(!cooExists) {	//쿠키 없을 때 hit + 1 -> update, new Cookie
				en.setTrHit(en.getTrHit() + 1);
				trService.updateOne(en);
				hitCoo = new Cookie("hit" + en.getTrId(), "" + en.getTrHit());
				hitCoo.setMaxAge(60 * 5);	//5분
				response.addCookie(hitCoo);
			}
			model.addAttribute("en", en);
		} else {
			error = "searching entity doesn't exist.";
			model.addAttribute("msg", error);
		}
		
		model.addAttribute("pn", pn);
		model.addAttribute("st", trProf);
		model.addAttribute("search", search);
		
		return "/training/content";
	}
	
}
