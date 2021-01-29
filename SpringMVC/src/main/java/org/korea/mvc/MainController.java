package org.korea.mvc;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.korea.mvc.dto.MemberDTO;
import org.korea.mvc.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private MemberService memberService;
	
	// 자동으로 AutoWire가 적용되어 있음 생성시 자동으로 역주입을 해줌(Bean에 등록된 클래스만)
	public MainController(MemberService memberService) {
		this.memberService = memberService;
		System.out.println("Maincontroller Constructor");
	}

	@RequestMapping("/")	// url만 입력했을 때 ww.google.com 요상태
	public String main() {
		System.out.println("main()");
		return "main";
	}
	
	@RequestMapping("/login.do")
	public String login() {
		System.out.println("login()");
		return "login";
	}
	
	@RequestMapping("/loginAction.do")	
	public String loginAction(HttpServletRequest request, HttpSession session) {
//	public String loginAction(@RequestParam("id") String id, @RequestParam("pass") String pass, HttpSession session) {
		System.out.println("loginAction()");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberDTO dto = memberService.login(id, pass);
		System.out.println(dto.toString());
		session.setAttribute("id", id);
		session.setAttribute("pass", pass);
		return "main";
	}
	
	@RequestMapping("/ajax.do")
	public String ajax(HttpServletResponse response) {
		System.out.println("ajax()");
		String str = Calendar.getInstance().getTime().toString();
		System.out.println(str);
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	// 이동할 페이지 없음
	}
}
