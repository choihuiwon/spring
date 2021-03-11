package board;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.MemberDTO;
import board.service.MemberService;

@Controller
public class MemberController {
	private MemberService service;

	public MemberController(MemberService service) {
		super();
		this.service = service;
	}
	

	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("loginAction.do")
	public String loginAction(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberDTO dto = service.login(id, pass);
		if(dto == null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('로그인 실패');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("age", dto.getAge());
			session.setAttribute("grade_name", dto.getGrade());
			return "main";
		}
		return null;
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}

	@RequestMapping("memberUpdateView.do")
	public String memberUpdateView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if(session.getAttribute("login") == null || (boolean)session.getAttribute("login") == false) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('로그인 후 이용하실 수 있습니다.');location.href='login.do';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			MemberDTO dto = service.selectMember((String)session.getAttribute("id"));
			request.setAttribute("dto", dto);
			return "member/member_update_view";
		}
		return null;
	}
	
	@RequestMapping("memberUpdateAction.do")
	public String memberUpdateAction(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String id = (String) session.getAttribute("id");
		if(id == null) {
			// 세션이 유효하지 않음
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('세션이 만료되었습니다.');location.href='login.do';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String pass = request.getParameter("passwd");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		int count = service.memberUpdateAction(id,pass,name,age);
		if(count == 0) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('정보 수정에 실패했습니다.');location.href='memberUpdateView.do';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			MemberDTO dto = service.login(id, pass);
			// 세션 최신화
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("age", dto.getAge());
			session.setAttribute("grade_name", dto.getGrade());
			return "main";
		}
		return null;
	}
}
