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
	private UserService userService;

	@GetMapping("/login")
	public String user_login_get(HttpSession session) {
		if (session.getAttribute("userid") != null) {
			return "/user/mypage";
		}
		return "/user/login";
	}

	// 로그인
	@PostMapping("/login")
	public String user_login_post(@RequestParam("userid") String userid, @RequestParam("pw") String pw,
			HttpSession session, Model model, RedirectAttributes rttr) {

		try {
			if (userService.validateId(userid)) {
				UserEntity entity = userService.selectById(userid);
				if(entity.getPw().equals(pw)) {
					session.setAttribute("userid", entity.getUserid());
					session.setAttribute("username", entity.getUsername());

					return "user/mypage";
				}
			}
			rttr.addFlashAttribute("msg", "로그인 실패");
			return "redirect:/login";
		} catch (Exception e) {
			return "/user/login";
		}
	}

	@GetMapping("/join")
	public String user_join_get(HttpSession session) {
		if (session.getAttribute("userid") != null) {
			return "/user/mypage";
		}
		return "/user/join";
	}

	// 회원가입
	@PostMapping("/join")
	public String user_join_post(@RequestParam("userid") String userid, 
			@RequestParam("pw") String pw,
			@RequestParam("username") String username, 
			@RequestParam("phone") String phone,
			@RequestParam("email") String email, 
			HttpSession session, Model model, 
			RedirectAttributes rttr) {
		//id, email 사용중인지 확인 후 값들을 받아서 entity생성 한 후 session을 가지고 return
		if(userService.validateId(userid)) {
			rttr.addFlashAttribute("msg", "이미 사용 중인 아이디입니다.");
			return "redirect:/join";
		} else if(userService.validateEmail(email)) {
			rttr.addFlashAttribute("msg", "이미 사용 중인 이메일입니다.");
			return "redirect:/join";
		} else {
			UserEntity entity = UserEntity.builder().userid(userid).pw(pw).username(username).phone(phone)
					.email(email).build();
			userService.create(entity);

			// rttr.addFlashAttribute("msg", "회원가입을 성공했습니다.");
			rttr.addFlashAttribute("msg", "회원가입을 성공했습니다.");
			return "redirect:/login";
		}
	}

	// my page
	@GetMapping("/mypage")
	public String mypage_get(HttpSession session) {
		if (session.getAttribute("userid") == null) {
			return "/user/login";
		}
		return "/user/mypage";
	}

	@PostMapping("/mypage")
	public String mypage(HttpSession session) {
		if (session.getAttribute("userid") == null) {
			return "/user/login";
		}
		return "/user/mypage";
	}

	// 회원 비밀번호 변경
	// 현재 비밀번호가 맞을 경우에 변경비밀번호로 업데이트
	@GetMapping("/user_change_pw")
	public String user_change_pw(HttpSession session) {
		if (session.getAttribute("userid") == null) {
			return "/user/login";
		}
		return "/user/user_change_pw";
	}

	@PostMapping("/user_change_pw")
	public String user_change_pw_ok( // 마이페이지로 이동
			HttpSession session, @RequestParam("newpw") String newpw, @RequestParam("oldpw") String oldpw, Model model,
			RedirectAttributes rttr) {
		String userid = (String) session.getAttribute("userid");
		if (userid == null) {
			return "/user/login";
		}

		Boolean result = userService.pwmodify(userid, oldpw, newpw);
		if (result == true) { // 로그인 성공 : 1) 세션을 생성(id, name) 2)mypage로 이동
			rttr.addFlashAttribute("msg", "비밀번호 변경을 완료했습니다.");
			return "redirect:/mypage";
		} else {
			rttr.addFlashAttribute("msg", "비밀번호 변경을 실패했습니다.");
			return "redirect:/mypage";
		}
	}

	// 회원 탈퇴
	@GetMapping("/user_delete")
	public String user_delete(HttpSession session) {
		if (session.getAttribute("userid") == null) {
			return "/user/login";
		}
		return "/user/user_delete";
	}

	@PostMapping("/user_delete")
	public String user_delete_check(@RequestParam("pw") String pw, HttpSession session, Model model,
			RedirectAttributes rttr) {
		String userid = (String) session.getAttribute("userid");
		if (userid == null) {
			return "/user/login";
		}

		Boolean result = userService.deleteUser(userid, pw);
		if (result) {
			rttr.addFlashAttribute("msg", "회원 삭제를 성공했습니다.");
			session.invalidate();
			return "redirect:/login";
		} else {
			rttr.addFlashAttribute("msg", "비밀번호가 맞지 않습니다.");
			return "redirect:/user_delete";
		}
	}

	// 정보 수정
	@GetMapping("/user_update")
	public String user_update( // 마이페이지로 이동
			HttpSession session, Model model) {
		if (session.getAttribute("userid") == null) {
			return "/user/login";
		}
		String userid = (String) session.getAttribute("userid");
		UserEntity entity = userService.selectById(userid);

		model.addAttribute("entity", entity);
		return "/user/user_update";
	}

	@PostMapping("/user_update")
	public String user_update_ok( // 마이페이지로 이동
			HttpSession session, @RequestParam("username") String username, @RequestParam("phone") String phone,
			@RequestParam("email") String email, Model model, RedirectAttributes rttr) {
		String userid = (String) session.getAttribute("userid");
		Boolean result = userService.modify(userid, username, phone, email);
		if (result == true) {
			session.setAttribute("username", username);
			rttr.addFlashAttribute("msg", "회원 수정을 완료했습니다.");
			return "redirect:/mypage";
		}
		rttr.addFlashAttribute("msg", "회원 수정을 실패했습니다.");
		return "redirect:/user_update";
	}

	@GetMapping("/user_logout")
	public String logout(HttpSession session, Model model, RedirectAttributes rttr) {
		if (session.getAttribute("userid") == null) {
			return "/user/login";
		}
		session.removeAttribute("userid"); // 특정 세션 삭제
		session.invalidate(); // 전체 세션 삭제

		rttr.addFlashAttribute("msg", "로그아웃 되었습니다.다시 로그인해 주세요");
		return "redirect:/login";
	}

}