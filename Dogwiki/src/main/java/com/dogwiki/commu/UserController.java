package com.dogwiki.commu;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogwiki.commu.entity.UserEntity;
import com.dogwiki.commu.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/login")
	public String user_login_get() { 
		return"/user/login";
	}

	//로그인
	@PostMapping("/login")
	public String user_login_post(@RequestParam("userid") String userid,@RequestParam("pw") String pw, 
			HttpSession session,Model model) {  

		try {
			if(service.validateId(userid)) {
				UserEntity entity = service.validate(userid) ;
				if(entity.getPw().equals(pw)) {
					session.setAttribute("userid", entity.getUserid());
					session.setAttribute("username", entity.getUsername());

					return "user/mypage";
				}
			}
			model.addAttribute("msg","로그인 실패");
			return"/user/login";
		} catch (Exception e) {
			return"/user/login";
		}
	}

	@GetMapping("/join")
	public String user_join_get() {
		return"/user/join";
	}

	//회원가입
	@PostMapping("/join")
	public String user_join_post(@RequestParam("userid") String userid,
			@RequestParam("pw") String pw,@RequestParam("username") String username,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			HttpSession session, Model model) {
		//id,email 가 사용중인지 확인 후 값들을 받아서 entity생성 한 후 session을 가지고 return 
		try {
			if(service.validateId(userid)) {

				throw new RuntimeException("Invalide argument.");
			}else {
				UserEntity entity = UserEntity.builder()
						.userid(userid)
						.pw(pw)
						.username(username)
						.phone(phone)
						.email(email)
						.build();

				service.create(entity);

				session.setAttribute("userid", entity.getUserid());
				session.setAttribute("username", entity.getUsername());

				//rttr.addFlashAttribute("msg", "회원가입을 성공했습니다.");
				model.addAttribute("msg", "회원가입을 성공했습니다.");
				return "/user/join";
			}
		}catch (Exception e) {
			model.addAttribute("errorMessage", "유효하지 않은 형식입니다.");
			return"/user/join";
		}

	} 

	//my page
	@GetMapping("/mypage")
	public String mypage_get(HttpSession session) {
		if(session.getAttribute("userid") == null) {
			return "/user/login";
		}
		return "/user/mypage";
	}
	
	@PostMapping("/mypage")
	public String mypage(HttpSession session) {
		if(session.getAttribute("userid") == null) {
			return "/user/login";
		}
		return "/user/mypage";
	}


	//회원 비밀번호 변경
	//현재 비밀번호가 맞을 경우에 변경비밀번호로 업데이트
	@GetMapping("/user_change_pw")
	public String user_change_pw(HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "/user/login";
		}
		return"/user/user_change_pw";
	}

	@PostMapping("/user_change_pw")
	public String user_change_pw_ok( // 마이페이지로 이동
			HttpSession session,
			@RequestParam("newpw") String newpw,
			@RequestParam("oldpw") String oldpw,
			Model model) {

		String userid = (String) session.getAttribute("userid");

		if(userid == null) {
			return "/user/login";
		}
		Boolean result = service.pwmodify(userid, oldpw, newpw);

		if (result == true) { // 로그인 성공 : 1) 세션을 생성(id, name) 2)mypage로 이동
			model.addAttribute("msg", "비밀번호 변경을 완료했습니다.");
			return "/user/mypage";
		} else {
			model.addAttribute("msg", "비밀번호 변경을 실패했습니다.");
			return "/user/mypage";
		}

	}


	//회원 탈퇴
	@GetMapping("/user_delete")
	public String user_delete(HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "/user/login";
		}
		return"/user/user_delete";
	}

	@PostMapping("/user_delete")
	public String user_delete_check(@RequestParam("pw") String pw,
			HttpSession session , Model model,RedirectAttributes rttr) {

		String userid = (String) session.getAttribute("userid");

		if(userid == null) {
			return"/user/login";
		}
		Boolean result = service.deleteUser(userid, pw);

		if(result == true) {
			rttr.addFlashAttribute("msg", "회원 삭제를 성공했습니다.");
			return"redirect:/login";
		}else {
			model.addAttribute("msg", "비밀번호가 맞지 않습니다.");
			return "/user/user_delete";
		}

	}

	// 정보 수정
	@GetMapping("/user_update")
	public String user_update( // 마이페이지로 이동
			HttpSession session, 
			Model model) {

		if(session.getAttribute("userid")==null) {
			return "/user/login";
		}
		String userid = (String)session.getAttribute("userid"); 

		UserEntity entity = service.validate(userid);

		model.addAttribute("entity",entity);
		return "/user/user_update";
	}

	@PostMapping("/user_update")
	public String user_update_ok( // 마이페이지로 이동
			HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			Model model) {

		String userid = (String)session.getAttribute("userid"); 
		
		Boolean result = service.modify(userid, username, phone, email);
		model.addAttribute("result",result); //model에 entity정보 넘김
		
		System.out.println(result);
		if(result == true) {
			
			model.addAttribute("msg", "회원 수정을 완료했습니다.");
			return "/user/mypage";
		}
		model.addAttribute("msg", "회원 수정을 실패했습니다.");
		return "/user/user_update";
	}

	@GetMapping("/user_logout")
	public String logout(HttpSession session,Model model) {
		if(session.getAttribute("userid")==null) {
			return "/user/login";
		}
		session.removeAttribute("userid"); // 특정 세션 삭제
		session.invalidate(); // 전체 세션 삭제

		model.addAttribute("msg", "로그아웃 되었습니다.다시 로그인해 주세요");
		return "/user/login";
	}


}
