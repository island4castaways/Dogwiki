package com.dogwiki.commu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeCotroller {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("Home Controller has started");
		return "home";
	}

}
